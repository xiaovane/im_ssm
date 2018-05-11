package api.vote.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.sql.ast.statement.SQLWithSubqueryClause.Entry;
import com.google.gson.GsonBuilder;

import api.emchat.api.impl.EasemobSendMessage;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.Vote;
import admin.entity.api.VoteChoice;
import admin.entity.api.VoteInfo;
import admin.util.Validation;
import api.vote.service.VoteService;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;

@Controller
@RequestMapping("clientVote")
public class VoteController {

	@Resource
	private VoteService voteService;
	@Resource
	private UserApiService userApiService;
	@Resource
	private GroupApiService apiGroupService;
	private String token = "";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currentTime = new Date();

	/**
	 * 创建投票
	 * 
	 * @param request
	 * @param response
	 * @param vote
	 * @param voteChoice
	 * @throws Exception
	 */
	@RequestMapping(value = "createVote")
	public void createVote(HttpServletRequest request, HttpServletResponse response, Vote vote, VoteChoice voteChoice)
			throws Exception {
		String groupId = request.getParameter("groupId");
		String title = request.getParameter("title");
		String createId = request.getParameter("createId");
		String voteId = request.getParameter("voteId");
		String choiceId = request.getParameter("choiceId");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		// 判断投票的标题是否已经存在
		Integer isExitVote = voteService.isExitVote(vote);
		if (isExitVote > 0) {
			Validation.postJson(response, token, "125", "同名称的投票已经创建过", "false");
			return;
		}

		// 创建投票问题
		try {
			vote.setGroupId(groupId);
			vote.setTitle(title);
			vote.setCreateId(createId);
			vote.setVoteId(voteId);
			vote.setStartTime(startTime);
			vote.setEndTime(endTime);
			vote.setCreateTime(formatter.format(currentTime));
			voteService.ctrateVote(vote);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 创建投票选项
		try {

			String[] strVoteId = choiceId.split(",");
			for (String strVoteTtems : strVoteId) {

				String[] strItems = strVoteTtems.split(":");

				// 遍历循环key和value
				voteChoice.setChoiceId(strItems[0]);
				voteChoice.setChoiceValue(strItems[1]);
				voteChoice.setVoteId(voteId);

				Integer result = voteService.createVoteChoice(voteChoice);
				if (result.equals(0)) {
					Validation.postJson(response, token, "160", "创建投票问卷失败", "false");
					return;
				}
				// 数据库中查询token
				token = userApiService.getToken(createId);
				Validation.postJson(response, token, "100", "创建投票问卷成功", "true");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 投票
	 * 
	 * @param request
	 * @param response
	 * @param voteInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "voteInfo")
	public void voteInfo(HttpServletRequest request, HttpServletResponse response, VoteInfo voteInfo) throws Exception {
		// 判断调查问卷是否过期
		Integer voteIs = voteService.selectVoteIs(voteInfo);
		if (voteIs.equals(0)) {
			Validation.postJson(response, token, "114", "问卷调查已经过期", "false");
			return;
		}

		// 判断是否已经投过票
		Integer voteInfoIs = voteService.selectVoteInfoIs(voteInfo);
		if (voteInfoIs.equals(0)) {
			Validation.postJson(response, token, "115", "您已经投过票了", "false");
			return;
		}

		// 每个人选择投票选项投票
		try {

			Integer result = voteService.addVoteInfo(voteInfo);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "投票失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(voteInfo.getUserId());
			Validation.postJson(response, token, "100", "投票成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 投票统计
	 * 
	 * @param request
	 * @param response
	 * @param voteInfo
	 */
	@RequestMapping(value = "voteStatistics")
	public void voteStatistics(HttpServletRequest request, HttpServletResponse response, VoteInfo voteInfo) {
		// 获取当前的投票统计
		try {

			List<VoteInfo> result = voteService.voteStatistics(voteInfo);
			if (result.equals(null)) {
				Validation.postJson(response, token, "160", "获取当前的投票统计失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(voteInfo.getUserId());
			Validation.postJson(response, token, "100", "获取当前的投票统计成功", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取投票列表
	 * 
	 * @param request
	 * @param response
	 * @param voteInfo
	 */
	@RequestMapping(value = "voteInfoList")
	public void voteInfoList(HttpServletRequest request, HttpServletResponse response, Vote voteInfo) {
		// 获取投票的列表
		try {
			String pageIndex = request.getParameter("pageIndex");
			String pageSize = request.getParameter("pageSize");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageIndex", (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(pageSize));
			map.put("pageSize", Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
			map.put("groupId", voteInfo.getGroupId());

			List<VoteInfo> result = voteService.voteInfoList(map);
			if (result.equals(null)) {
				Validation.postJson(response, token, "160", "获取投票的列表失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(voteInfo.getCreateId());
			Validation.postJson(response, token, "100", "获取投票的列表成功", result);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
