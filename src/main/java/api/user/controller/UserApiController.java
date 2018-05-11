package api.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.sql.visitor.functions.Now;

import admin.entity.api.GroupGroup;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.UserInfo;
import admin.util.MD5Util;
import admin.util.Validation;
import api.emchat.api.impl.EasemobChatGroup;
import api.emchat.api.impl.EasemobIMUsers;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import api.user.utils.Sms;
import io.swagger.client.model.Group;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;

@Controller
@RequestMapping("/clientUser")
public class UserApiController {

	@Resource
	private UserApiService apiService;
	@Resource
	private GroupApiService apiGroupService;
	private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
	private static final Logger logger = LoggerFactory.getLogger(UserApiController.class);
	private String token = "";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currentTime = new Date();

	/**
	 * 注册前验证
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkUser")
	public void checkUser(HttpServletRequest request, HttpServletResponse response, UserInfo user) throws Exception {
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		// 1、验证注册是否合法
		if (!Validation.validateName(user.getUserId())) {
			Validation.postJson(response, token, "102", "用户id必须是6~10位的数字和字母", "false");
			return;
		}

		if (!Validation.validateMobile(user.getMobile().toString())) {
			Validation.postJson(response, token, "103", "手机号格式不正确", "false");
			return;
		}

		if (!Validation.validatePwd(user.getPwd())) {
			Validation.postJson(response, token, "104", "密码必须是6~10位的数字和字母", "false");
			return;
		}

		// 2、注册时唯一性判断
		if (validateUserOnly(user.getUserId())) {
			Validation.postJson(response, token, "105", "用户ID已经存在", "false");
			return;

		}

		if (validateMobileOnly(user.getMobile())) {
			Validation.postJson(response, token, "106", "手机号已经被注册", "false");
			return;
		}

//		if (validateHome(user)) {
//			Validation.postJson(response, token, "107", "您填写的小区信息已经存在", "false");
//			return;
//		}

		Validation.postJson(response, token, "100", "验证成功", "true");
	}

	/**
	 * 用户注册
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	@RequestMapping(value = "/regist")
	public void regist(HttpServletRequest request, HttpServletResponse response, UserInfo user, GroupInfo groupInfo,
			GroupUsers groupUsers) throws Exception {
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		// 调用环信注册接口，调用成功就往数据库里插入用户记录
		try {

			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			RegisterUsers users = new RegisterUsers();

			User user1 = new User().username(user.getUserId()).password(MD5Util.md5(user.getPwd(), "xp"));
			users.add(user1);
			Object result = easemobIMUsers.createNewIMUserSingle(users);

			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 给业务端插入数据记录
		try {
			// 给密码进行加密处理
			user.setPwd(MD5Util.md5(user.getPwd(), "xp"));
			user.setRegist(formatter.format(currentTime));
			// 调用环信接口成功则去往数据库插入记录
			Integer result = apiService.registUser(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "注册失败", "false");
				return;
			}

			// 往数据库中插入token
			token = UUID.randomUUID().toString();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("token", token);
			Integer insertToken = apiService.setToken(map);

			if (insertToken.equals(0)) {
				Validation.postJson(response, token, "160", "插入token失败", "false");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		Validation.postJson(response, token, "100", "注册成功", user);

	}

	/**
	 * 补充用户信息
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/registUpdate")
	public void registUpdate(HttpServletRequest request, HttpServletResponse response, UserInfo user,
			GroupInfo groupInfo, GroupUsers groupUsers) throws Exception {
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		
		if (validateHome(user)) {
			Validation.postJson(response, token, "107", "您填写的小区信息已经存在", "false");
			return;
		}

		try {

			// 更新数据库插入记录
			Integer result = apiService.registUpdate(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "补充信息失败", "false");
				return;
			}
			// 从数据库中获取token
			token = apiService.getToken(user.getUserId());

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 判断是商户注册还是业主注册，便民服务注册不加群
		if (user.getUseType().equals(1)) {
			// 判断群是否存在，如果存在就直接加人，如果不存在就创建
			try {
				GroupGroup groupGroup = new GroupGroup();
				String ywh = user.getVillage() + "业委会群";
				String lz = user.getVillage() + "楼长群";
				String dyz = user.getVillage() + "单元长群";
				String cy = user.getVillage() + "小区成员群";
				String lq = user.getVillage() + user.getBuilding() + "楼群";
				String bdy = user.getVillage() + user.getBuilding() + user.getUnit() + "单元群";
				Integer isExistGroup = apiGroupService.isExistGroupYwh(ywh);
				if (isExistGroup.equals(0)) {
					// 调用环信加修创建群组的接口
					// 下面三个群，状态默认为0
					groupUsers.setStatus(0);
					// 创建业委会群
					if (!createGroup(ywh, ywh, user.getUserId(), "/userImages/group/ywh.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 业委会groupId
					String tempYwh = groupInfo.getGroupId();

					// 创建楼长群
					if (!createGroup(lz, lz, user.getUserId(), "/userImages/group/lz.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 楼长groupId
					String tempLz = groupInfo.getGroupId();
					// 创建楼长群和业委会群之间的关系
					groupGroup.setTopGroupId(tempYwh);
					groupGroup.setGroupId(tempLz);

					Integer linkLz1 = apiGroupService.linkGroup2(groupGroup);
					if (linkLz1.equals(0)) {
						Validation.postJson(response, token, "160", "创建楼长群和业委会群之间的关系失败", "false");
						return;
					}

					// 创建单元长群
					if (!createGroup(dyz, dyz, user.getUserId(), "/userImages/group/dyz.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 单元长groupId
					String tempDyz = groupInfo.getGroupId();

					// 下面三个群，成员可以投票和发言
					groupUsers.setStatus(1);
					// 创建小区成员群
					if (!createGroup(cy, cy, user.getUserId(), "/userImages/group/cy.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 创建楼群
					if (!createGroup(lq, lq, user.getUserId(), "/userImages/group/lq.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 楼群groupId
					String tempLq = groupInfo.getGroupId();
					// 创建楼群和楼长群之间的关系
					groupGroup.setTopGroupId(tempLz);
					groupGroup.setGroupId(tempLq);

					Integer linkLz2 = apiGroupService.linkGroup2(groupGroup);
					if (linkLz2.equals(0)) {
						Validation.postJson(response, token, "160", "创建楼群和楼长群之间的关系失败", "false");
						return;
					}
					// 创建单元群
					if (!createGroup(bdy, bdy, user.getUserId(), "/userImages/group/bdy.png", groupInfo, groupUsers)) {
						Validation.postJson(response, token, "160", "创建群失败", "false");
						return;
					}

					// 本单元groupId
					String tempBdy = groupInfo.getGroupId();

					// 创建楼单元群和单元长群之间的关系
					groupGroup.setTopGroupId(tempDyz);
					groupGroup.setGroupId(tempBdy);
					Integer linkLz3 = apiGroupService.linkGroup2(groupGroup);

					if (linkLz3.equals(0)) {
						Validation.postJson(response, token, "160", "创建楼单元群和单元长群之间的关系失败", "false");
						return;
					}

				} else {
					// 往业委会群里加人
					// 下面三个群，状态默认为0
					groupUsers.setStatus(0);
					String groupIdYwh = getGroupId(ywh);
					if (!addNewGroup(groupIdYwh, user.getUserId(), groupUsers)) {
						Validation.postJson(response, token, "160", "群里加人失败", "false");
						return;
					}

					// 往楼长群里加人
					String groupIdLz = getGroupId(lz);
					if (!addNewGroup(groupIdLz, user.getUserId(), groupUsers)) {
						Validation.postJson(response, token, "160", "群里加人失败", "false");
						return;
					}

					// 往单元长群里加人
					String groupIdDyz = getGroupId(dyz);
					if (!addNewGroup(groupIdDyz, user.getUserId(), groupUsers)) {
						Validation.postJson(response, token, "160", "群里加人失败", "false");
						return;
					}

					// 下面三个群，成员可以投票和发言
					groupUsers.setStatus(1);
					// 往小区成员群里加人
					String groupIdCy = getGroupId(cy);
					if (!addNewGroup(groupIdCy, user.getUserId(), groupUsers)) {
						Validation.postJson(response, token, "160", "群里加人失败", "false");
						return;
					}

					// 判断楼群是否存在
					Integer isExistLq = apiGroupService.isExistGroupYwh(lq);
					if (isExistLq.equals(0)) {

						// 创建楼群
						if (!createGroup(lq, lq, user.getUserId(), "/userImages/group/lq.png", groupInfo, groupUsers)) {
							Validation.postJson(response, token, "160", "创建群失败", "false");
							return;
						}

						// 创建群的时候，同时创建群之间的关系
						// 创建楼群和楼长群之间的关系
						groupGroup.setTopGroupId(groupIdLz);
						groupGroup.setGroupId(getGroupId(lq));
						Integer linkLz4 = apiGroupService.linkGroup2(groupGroup);
						if (linkLz4.equals(0)) {
							Validation.postJson(response, token, "160", "创建楼群和楼长群之间的关系失败", "false");
							return;
						}
					} else {
						// 往楼群里加人
						String groupIdLq = getGroupId(lq);
						if (!addNewGroup(groupIdLq, user.getUserId(), groupUsers)) {
							Validation.postJson(response, token, "160", "群里加人失败", "false");
							return;
						}
					}

					// 判断本单元群是否存在
					Integer isExistBdy = apiGroupService.isExistGroupYwh(bdy);
					if (isExistBdy.equals(0)) {

						// 创建单元群
						if (!createGroup(bdy, bdy, user.getUserId(), "/userImages/group/bdy.png", groupInfo,
								groupUsers)) {
							Validation.postJson(response, token, "160", "创建群失败", "false");
							return;
						}

						// 创建单元长群和本单元群之间的关系
						groupGroup.setTopGroupId(groupIdDyz);
						groupGroup.setGroupId(getGroupId(bdy));
						Integer linkLz5 = apiGroupService.linkGroup2(groupGroup);
						if (linkLz5.equals(0)) {
							Validation.postJson(response, token, "160", "创建本单元和单元长群之间的关系失败", "false");
							return;
						}
					} else {
						// 往本单元里加人
						String groupIdBdy = getGroupId(bdy);
						if (!addNewGroup(groupIdBdy, user.getUserId(), groupUsers)) {
							Validation.postJson(response, token, "160", "群里加人失败", "false");
							return;
						}
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		Validation.postJson(response, token, "100", "完成补充信息成功", user);

	}

	/**
	 * 用户登录
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/login")
	public void userLogin(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo)
			throws Exception {
		if (userInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 登录用户名和密码
		String user = request.getParameter("user");
		userInfo.setUserId(user);
		userInfo.setMobile(user);
		userInfo.setPwd(MD5Util.md5(userInfo.getPwd(), "xp"));

		if (validateLogin(userInfo)) {
			Validation.postJson(response, token, "108", "用户名或密码不正确", "false");
			return;
		}

		// =================暂时注释掉=======================================
		// if (validateDevice(userInfo)) {
		// Validation.postJson(response, "109", "您不在同一台设备上登录", "false");
		// return;
		// }

		// 登录成功则返回用户所有数据
		try {

			// 从数据库中获取token
			token = apiService.getToken(userInfo.getUserId());
			UserInfo info = apiService.getUserInfoByUserId(userInfo);
			if (info.equals(null)) {
				Validation.postJson(response, token, "160", "登录失败", "false");
				return;
			}

			Validation.postJson(response, token, "100", "登录成功", info);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 发送短信接口
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/smsSend")
	public void smsSend(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 判断手机号是否注册
		// Integer isMobile=apiService.isMobile(request.getParameter("mobile"));
		// if (isMobile.equals(0)) {
		// Validation.postJson(response, token,"123", "手机号未注册！", "false");
		// return;
		// }
		Integer radom = (int) ((Math.random() * 9 + 1) * 1000);
		String mobile = request.getParameter("mobile");
		String content = "{\"code\":" + radom + "}";

		String param = "template=416829&mobile=" + mobile + "&content=" + content + "";
		try {

			if (Sms.smsSendMsg(param)) {
				// 发送成功后，就把验证码存储在session里面，方便调用验证
				// HttpSession httpSession = request.getSession();
				// httpSession.setAttribute("msgCode", radom);
				// httpSession.setAttribute("mobile", mobile);

				Map<String, Object> map = new HashMap<String, Object>();
				map.put("code", radom);
				map.put("mobile", mobile);

				Validation.postJson(response, token, "100", "短信发送成功！", map);
			} else {
				Validation.postJson(response, token, "160", "短信发送失败！", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 批量发送短信
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/smsSendBacth")
	public void smsSendBacth(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 逗号隔开
		Integer msg=1234;
		String mobile = request.getParameter("mobile");
		String content = "{\"code\":" + msg + "}";

		String param = "template=416829&mobile=" + mobile + "&content=" + content + "";
		try {

			if (Sms.smsSendMsg(param)) {

				Map<String, Object> map = new HashMap<String, Object>();

				Validation.postJson(response, token, "100", "批量短信发送成功！", "true");
			} else {
				Validation.postJson(response, token, "160", "批量短信发送失败！", "false");
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/smsCode")
	public void smsCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Integer code = Integer.parseInt(request.getParameter("code"));
		String mobile = request.getParameter("mobile");
		HttpSession httpSession = request.getSession();
		Integer msgCode = (Integer) httpSession.getAttribute("code");
		String msgMobile = (String) httpSession.getAttribute("mobile");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("code", code);
		map.put("mobile", mobile);
		map.put("msgCode", msgCode);
		map.put("msgMobile", msgMobile);
		if (smsCodeCheck(code, msgCode) && smsCodeCheck(mobile, msgMobile)) {
			Validation.postJson(response, token, "200", "验证码验证成功！", map);
			// 成功则清除session的
			request.getSession().setAttribute("code", null);
		} else {
			Validation.postJson(response, token, "160", "验证码验证失败！", map);
		}

	}

	// 注册时判断

	/**
	 * 判断UserId是否已经存在
	 * 
	 * @param userId
	 * @return
	 */
	public boolean validateUserOnly(String userId) {

		Integer num = apiService.inCountUser(userId);
		// 已经存在
		if (num.equals(0)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断手机号是否已经注册
	 * 
	 * @param mobile
	 * @return
	 */
	public boolean validateMobileOnly(String mobile) {
		Integer num = apiService.inCountMobile(mobile);
		// 已经存在
		if (num.equals(0)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断用户的小区信息是否已经存在
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean validateHome(UserInfo userInfo) {
		Integer num = apiService.inCountHome(userInfo);
		if (num.equals(0)) {
			return false;
		}
		return true;
	}

	// 登录时判断
	/**
	 * 判断用户登录是否正确
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean validateLogin(UserInfo userInfo) {
		Integer num = apiService.inCountUserLogin(userInfo);
		if (!num.equals(0)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断是否在同一台设备上登录
	 * 
	 * @param userInfo
	 * @return
	 */
	public boolean validateDevice(UserInfo userInfo) {
		Integer num = apiService.inCountDevice(userInfo);
		if (!num.equals(0)) {
			return false;
		}
		return true;
	}

	/**
	 * 验证码验证
	 * 
	 * @param code
	 * @param radom
	 * @return
	 */
	public boolean smsCodeCheck(Object code, Object radom) {
		boolean flag = false;

		try {

			if (code.equals(radom)) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return flag;
	}

	/**
	 * 注册时第一个人创建群
	 * 
	 * @param nickName
	 * @param desc
	 * @param createName
	 * @param groupImg
	 * @param groupInfo
	 * @return
	 */
	public boolean createGroup(String nickName, String desc, String createName, String groupImg, GroupInfo groupInfo,
			GroupUsers groupUsers) {
		boolean flag = true;
		groupInfo.setGroupNickName(nickName);
		groupInfo.setDescription(desc);
		groupInfo.setCreateName(createName);
		groupInfo.setGroupImage(groupImg);
		groupInfo.setIsAdmin(1);
		groupInfo.setCreateTime(formatter.format(currentTime));
		groupUsers.setUserId(createName);

		// 调用环信加修创建群组的接口
		try {
			Group group = new Group();
			group.groupname(groupInfo.getGroupNickName()).desc(groupInfo.getDescription())._public(true).maxusers(1000)
					.approval(false).owner(groupInfo.getCreateName());
			Object result = easemobChatGroup.createChatGroup(group);
			logger.info(result.toString());

			// 设置groupId
			String[] groupNew = result.toString().split(",");
			String[] groupNewId = {};
			for (String data : groupNew) {
				if (data.indexOf("data") > 0) {
					groupNewId = data.split(":");
					String groupId = groupNewId[2].substring(2, groupNewId[2].length() - 5);
					groupInfo.setGroupId(groupId);
					groupUsers.setGroupId(groupId);
				}
			}
		} catch (Exception e) {
			flag = false;
		}

		// 调用成功则往数据库里插入数据
		try {

			Integer result = apiGroupService.creatGroup(groupInfo);
			if (result.equals(0)) {
				flag = false;
			}

			// 默认创建者往群里加人
			Integer resultGroupUsers = apiGroupService.addGroup(groupUsers);
			if (resultGroupUsers.equals(0)) {
				flag = false;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 如果群存在，就直接加群，不用再创建群
	 * 
	 * @param gtoupId
	 * @param userId
	 * @param groupUsers
	 * @return
	 * @throws Exception
	 */
	public boolean addNewGroup(String gtoupId, String userId, GroupUsers groupUsers) throws Exception {

		boolean flag = true;
		groupUsers.setGroupId(gtoupId);
		groupUsers.setUserId(userId);
		// 调用环信加群组添加人员的接口
		try {

			Object result = easemobChatGroup.addSingleUserToChatGroup(groupUsers.getGroupId(), groupUsers.getUserId());
			logger.info(result.toString());
		} catch (Exception e) {

			flag = false;
		}

		// 调用成功则往数据库里添加数据
		Integer result = apiGroupService.addGroup(groupUsers);
		if (result.equals(0)) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 通过nickName获取群id
	 * 
	 * @param nickName
	 * @return
	 */
	public String getGroupId(String nickName) {

		String groupId = apiGroupService.getGroupIdByName(nickName);
		if (groupId.equals(null)) {
			return "false";
		}
		return groupId;
	}
}
