package admin.controller.admin.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.GsonBuilder;

import admin.entity.PageBean;
import admin.entity.api.GroupInfo;
import admin.entity.api.UserInfo;
import admin.util.ResultInfoUtil;
import api.emchat.api.impl.EasemobSendMessage;
import api.user.controller.UserOperationApiController;
import api.user.service.GroupApiService;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/group")
public class GroupManageController {

	@Resource
	private GroupApiService groupService;
	private static final Logger logger = LoggerFactory.getLogger(UserOperationApiController.class);

	@RequestMapping("/groupInfo")
	public String userInfo(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, GroupInfo groupInfo,
			HttpServletResponse response) throws Exception {

		PageBean<UserInfo> pageBean = new PageBean<UserInfo>(Integer.parseInt(page), Integer.parseInt(rows));

		// 根据 群id、群昵称查询
		pageBean = groupService.listGroupInfo(groupInfo, pageBean);
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

	
}
