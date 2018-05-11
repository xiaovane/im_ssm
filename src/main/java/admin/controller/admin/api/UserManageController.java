package admin.controller.admin.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.GsonBuilder;

import admin.entity.PageBean;
import admin.entity.api.UserInfo;
import admin.util.MD5Util;
import admin.util.ResultInfoUtil;
import admin.util.Validation;
import api.emchat.api.impl.EasemobIMUsers;
import api.emchat.api.impl.EasemobSendMessage;
import api.user.controller.UserOperationApiController;
import api.user.service.UserApiService;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.UserName;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/user")
public class UserManageController {

	@Resource
	private UserApiService userApiService;
	private static final Logger logger = LoggerFactory.getLogger(UserOperationApiController.class);

	/**
	 * 前台注册的用户相关信息
	 * 
	 * @param page
	 * @param rows
	 * @param userInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userInfo")
	public String userInfo(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, UserInfo userInfo,
			HttpServletResponse response) throws Exception {

		PageBean<UserInfo> pageBean = new PageBean<UserInfo>(Integer.parseInt(page), Integer.parseInt(rows));

		// 根据 用户id、用户手机号或者用户昵称查询
		pageBean = userApiService.listUserInfo(userInfo, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取(群)好友列表或者黑名单列表
	 * 
	 * @param userId
	 * @param blackStatus
	 * @param page
	 * @param rows
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userFrined")
	public String userFrined(@RequestParam(value = "userId", required = false) String userId,
			@RequestParam(value = "groupId", required = false) String groupId,
			@RequestParam(value = "isGroup", required = false) String isGroup,
			@RequestParam(value = "blackStatus", required = false) String blackStatus,
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		// 封装参数
		Map<String, Object> map = new HashMap<String, Object>();
		PageBean<UserInfo> pageBean = new PageBean<UserInfo>(Integer.parseInt(page), Integer.parseInt(rows));
		// 判断是群还是用户
		if (Integer.parseInt(isGroup)==1) {
			map.put("groupId", groupId);
			map.put("status", Integer.parseInt(blackStatus)+1);
			pageBean = userApiService.listGroupInfo(map, pageBean);	
		}
		else{
			map.put("userId", userId);
			map.put("blackStatus", Integer.parseInt(blackStatus));
			pageBean = userApiService.listFriendInfo(map, pageBean);
		}

		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 给用户发送消息
	 * 
	 * @param userId
	 * @param content
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/userSendMsg")
	public String userSendMsg(@RequestParam(value = "userId", required = true) String userId,
			@RequestParam(value = "content", required = true) String content, HttpServletResponse response)
			throws Exception {
		try {
			Msg msg = new Msg();
			MsgContent msgContent = new MsgContent();
			EasemobSendMessage easemobSendMessage = new EasemobSendMessage();

			msgContent.type(MsgContent.TypeEnum.TXT).msg(content);
			UserName userName = new UserName();
			userName.add(userId);

			Map<String, Object> ext = new HashMap<String, Object>();
			ext.put("test_key", "test_value");

			msg.from("stringa").target(userName).targetType("users").msg(msgContent).ext(ext);
			System.out.println(new GsonBuilder().create().toJson(msg));

			Object result = easemobSendMessage.sendMessage(msg);
			System.out.println(result);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 如果发送成功就返回成功
		ResultInfoUtil.resultSuccess(response, "success");
		return null;
	}

	/**
	 * 重置密码
	 * 
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/userResetPwd")
	public void userResetPwd(HttpServletResponse response, UserInfo user) throws Exception {

		// 调用环信加修改密码接口
		try {
			NewPassword psd = new NewPassword().newpassword(MD5Util.md5(user.getPwd(), "xp"));
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(user.getUserId(), psd);
			logger.info(result.toString());

		} catch (Exception e) {
			ResultInfoUtil.resultSuccess(response, "false");
			return;
		}

		try {
			// 调用成功则往数据库里插入数据
			user.setPwd(MD5Util.md5(user.getPwd(), "xp"));
			Integer result = userApiService.modifyPwd(user);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultInfoUtil.resultSuccess(response, "success");

	}

	/**
	 * 禁用用户
	 * 
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/userDeac")
	public void userDeac(HttpServletResponse response, UserInfo user) throws Exception {

		// 调用环信禁用或者启用用户
		try {

			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.deactivateIMUser(user.getUserId());
			logger.info(result.toString());

		} catch (Exception e) {
			ResultInfoUtil.resultSuccess(response, "false");
			return;
		}

		try {
			// 调用成功，则开启和禁用用户
			Integer result = userApiService.userDeac(user);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		ResultInfoUtil.resultSuccess(response, "success");

	}

}
