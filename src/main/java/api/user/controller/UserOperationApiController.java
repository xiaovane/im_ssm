package api.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.GroupInfo;
import admin.entity.api.UserInfo;
import admin.util.MD5Util;
import admin.util.Validation;
import api.emchat.api.impl.EasemobIMUsers;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;

@Controller
@RequestMapping("/clientUserOperation")
public class UserOperationApiController {

	@Resource
	private UserApiService apiService;
	@Resource
	private GroupApiService apiGroupService;
	private static final Logger logger = LoggerFactory.getLogger(UserOperationApiController.class);
	private String token = "";

	/**
	 * 修改密码
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPwd")
	public void modifyPwd(HttpServletRequest request, HttpServletResponse response, UserInfo user) throws Exception {
		String pwd = request.getParameter("pwd");
		String newPwd = request.getParameter("newPwd");
		String checkPwd = request.getParameter("checkPwd");
		// 判断参数是否错误
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数错误", "false");
			return;
		}
		// 判断输入的密码是否一致
		if (!newPwd.equals(checkPwd)) {
			Validation.postJson(response, token, "110", "两次输入的密码不一致", "false");
			return;
		}

		// 判断输入的原密码是否正确
		user.setPwd(MD5Util.md5(pwd, "xp"));
		Integer pwdCount = apiService.checkPwd(user);
		if (pwdCount.equals(0)) {
			Validation.postJson(response, token, "110", "原密码输入不正确", "false");
			return;
		}

		// 调用环信加修改密码接口
		try {
			NewPassword psd = new NewPassword().newpassword(MD5Util.md5(newPwd, "xp"));
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(user.getUserId(), psd);
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		try {
			// 调用成功则往数据库里插入数据
			user.setPwd(MD5Util.md5(newPwd, "xp"));
			Integer result = apiService.modifyPwd(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "修改密码失败", "false");
				return;
			}

			token = apiService.getToken(user.getUserId());
			Validation.postJson(response, token, "100", "修改密码成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改昵称
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyNickName")
	public void modifyNickName(HttpServletRequest request, HttpServletResponse response, UserInfo user)
			throws Exception {
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加修改昵称接口
		try {
			Nickname nName = new Nickname().nickname(user.getNickName());
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.modifyIMUserNickNameWithAdminToken(user.getUserId(), nName);
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里插入数据
		try {

			Integer result = apiService.modifyNickName(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "修改昵称失败", "false");
				return;
			}
			token = apiService.getToken(user.getUserId());
			Validation.postJson(response, token, "100", "修改昵称成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 密码加密接口
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/md5")
	public void md5(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pwd = request.getParameter("pwd");
		if (pwd.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 加密返回加密的密码
		try {

			Validation.postJson(response, token, "100", "密码加密成功", MD5Util.md5(pwd, "xp"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 精确搜索好友
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchFriendList")
	public void searchFriendList(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo)
			throws Exception {
		String user = request.getParameter("user");
		String userId = request.getParameter("userId");
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			// userInfo.setUserId(user);
			// userInfo.setMobile(user);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", user);
			map.put("mobile", user);
			map.put("userId", userId);

			List<UserInfo> list = apiService.searchFriendList(map);
			if (list.equals(null)) {
				Validation.postJson(response, token, "117", "搜索的好友不存在", "false");
				return;
			}

			Validation.postJson(response, token, "100", "好友搜索成功", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取好友列表
	 * 
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/getFriendList")
	public void getFriendList(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo)
			throws Exception {
		if (userInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			Map<String, List> map = new HashMap<String, List>();

			List<UserInfo> list = apiService.getFriendList(userInfo);
			if (list.equals(null)) {
				Validation.postJson(response, token, "118", "获取好友列表失败", "false");
				return;
			}

			List<GroupInfo> gropList = apiGroupService.getGroupInfoList(userInfo.getUserId());
			if (gropList.equals(null)) {
				Validation.postJson(response, token, "118", "获取群组列表失败", "false");
				return;
			}

			map.put("friendList", list);
			map.put("groupList", gropList);
			Validation.postJson(response, token, "100", "获取好友和群组列表成功", map);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 *获取系统群列表
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdminGroupList")
	public void getAdminGroupList(HttpServletRequest request, HttpServletResponse response, UserInfo userInfo)
			throws Exception {
		if (userInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			List<GroupInfo> gropList = apiGroupService.getAdminGroupList(userInfo.getUserId());
			if (gropList.equals(null)) {
				Validation.postJson(response, token, "118", "获取群组列表失败", "false");
				return;
			}
			
			Validation.postJson(response, token, "100", "获取群组列表成功", gropList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取群组详细
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdminGroupDetail")
	public void getAdminGroupDetail(HttpServletRequest request, HttpServletResponse response, GroupInfo groupInfo)
			throws Exception {
		if (groupInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			GroupInfo gropList = apiGroupService.getAdminGroupDetail(groupInfo.getGroupId());
			if (gropList.equals(null)) {
				Validation.postJson(response, token, "118", "获取群组详细失败", "false");
				return;
			}
			
			Validation.postJson(response, token, "100", "获取群详细表成功", gropList);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 重置密码
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/restPwd")
	public void resetPwd(HttpServletRequest request, HttpServletResponse response, UserInfo user) throws Exception {
		String newPwd = request.getParameter("newPwd");
		String checkPwd = request.getParameter("checkPwd");
		// 判断参数是否错误
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数错误", "false");
			return;
		}
		// 判断输入的密码是否一致
		if (!newPwd.equals(checkPwd)) {
			Validation.postJson(response, token, "110", "两次输入的密码不一致", "false");
			return;
		}

		// 根据mobile去找userId
		String	getUserId = apiService.getUserId(user);
		// 调用环信加修改密码接口
		try {
			NewPassword ps = new NewPassword().newpassword(MD5Util.md5(newPwd, "xp"));
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(getUserId,ps);
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		try {
			// 调用成功则往数据库里插入数据
			user.setPwd(MD5Util.md5(newPwd, "xp"));
			Integer result = apiService.resetPwd(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "重置密码失败", "false");
				return;
			}

			token = apiService.getToken(user.getUserId());
			Validation.postJson(response, token, "100", "重置密码成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改个人信息
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyPersonInfo")
	public void modifyPersonInfo(HttpServletRequest request, HttpServletResponse response, UserInfo user)
			throws Exception {
		// 判断参数是否错误
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数错误", "false");
			return;
		}

		try {

			// 生成图片
			if (user.getUserImage() != null && user.getUserImage() != "") {
				user.setUserImage(Validation.getImagesUrl(request, user.getUserImage()));
			}

			// 调用成功则往数据库里插入数据
			Integer result = apiService.updatePersonInfo(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "个人信息修改失败", "false");
				return;
			}

			token = apiService.getToken(user.getUserId());
			Validation.postJson(response, token, "100", "个人信息修改成功", user.getUserImage());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 隐藏个人信息
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/hiddenInfo")
	public void hiddenInfo(HttpServletRequest request, HttpServletResponse response, UserInfo user) throws Exception {
		// 判断参数是否错误
		if (user.equals(null)) {
			Validation.postJson(response, token, "101", "参数错误", "false");
			return;
		}

		try {
			// 调用成功则往数据库里插入数据
			Integer result = apiService.hiddenPersonInfo(user);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "隐藏信息修改失败", "false");
				return;
			}

			// 更新数据库中的token
			token = UUID.randomUUID().toString();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", user.getUserId());
			map.put("token", token);

			Integer key = apiService.updateToken(map);
			if (key.equals(0)) {
				Validation.postJson(response, token, "160", "更新token失败", "false");
				return;
			}
			Validation.postJson(response, token, "100", "隐藏个人信息修改成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	/**
	 * 获取客服列表
	 * @param request
	 * @param response
	 * @param userInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/getKeFu")
	public void getKeFu(HttpServletRequest request, HttpServletResponse response,UserInfo userInfo)
			throws Exception {
		if (userInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			
			List<UserInfo> list = apiService.getKeFu(userInfo);
			if (list.equals(null)) {
				Validation.postJson(response, token, "126", "搜索的客服不存在", "false");
				return;
			}

			Validation.postJson(response, token, "100", "客服搜索成功", list);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
