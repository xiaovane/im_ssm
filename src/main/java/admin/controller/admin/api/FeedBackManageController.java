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
import com.sleepycat.je.rep.impl.GroupService;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.GroupInfo;
import admin.entity.api.UserInfo;
import admin.util.MD5Util;
import admin.util.ResultInfoUtil;
import admin.util.Validation;
import api.emchat.api.impl.EasemobIMUsers;
import api.emchat.api.impl.EasemobSendMessage;
import api.user.controller.UserOperationApiController;
import api.user.service.FriendApiService;
import api.user.service.GroupApiService;
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
@RequestMapping("/admin/feedBack")
public class FeedBackManageController {

	@Resource
	private FriendApiService apiService;

	/**
	 * 获取反馈信息列表
	 * 
	 * @param page
	 * @param rows
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/feedBackList")
	public String feedBackList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, FeedBack feedBack,
			HttpServletResponse response) throws Exception {

		PageBean<FeedBack> pageBean = new PageBean<FeedBack>(Integer.parseInt(page), Integer.parseInt(rows));

		// 根据 群id、群昵称查询
		pageBean = apiService.listFeedBack(feedBack, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取反馈、投诉内容信息
	 * 
	 * @param page
	 * @param rows
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/feedBackContent")
	public String feedBackContent(FeedBack feedBack, HttpServletResponse response) throws Exception {
		FeedBack	feedBackDetail = apiService.feedBackContent(feedBack);
		ResultInfoUtil.jsonObject(response, feedBackDetail);
		return null;
	}

	/**
	 * 回复反馈内容
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/feedBackReply")
	public String feedBackReply(FeedBack feedBack, HttpServletResponse response) throws Exception {
		Integer 	result = apiService.feedBackReply(feedBack);
		ResultInfoUtil.resultSuccess(response, "success");
		return null;
	}
}
