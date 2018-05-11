package admin.controller.admin.api;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.PageBean;
import admin.entity.api.Vote;
import admin.entity.api.VoteChoice;
import admin.entity.api.VoteInfo;
import admin.util.ResultInfoUtil;
import api.vote.service.VoteService;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/voteInfo")
public class VoteInfoManageController {

	@Resource
	private VoteService apiService;
	

	/**
	 * 获取创建投票列表
	 * 
	 * @param page
	 * @param rows
	 * @param vote
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voteInfoList")
	public String voteInfoList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Vote vote, HttpServletResponse response)
			throws Exception {

		PageBean<Vote> pageBean = new PageBean<Vote>(Integer.parseInt(page), Integer.parseInt(rows));

		// 根据 群id、群昵称查询
		pageBean = apiService.voteInfoList(vote, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取创建投票详细
	 * 
	 * @param vote
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voteInfoDetail")
	public String voteInfoDetail(Vote vote, HttpServletResponse response) throws Exception {
		Vote voteInfoDetail = apiService.voteInfoDetail(vote);
		ResultInfoUtil.jsonObject(response, voteInfoDetail);
		return null;
	}

	/**
	 * 获取创建投票修改
	 * 
	 * @param vote
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voteInfoModify")
	public String voteInfoModify(Vote vote, HttpServletResponse response) throws Exception {
		Integer result = apiService.voteInfoModify(vote);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");
		}		
		return null;
	}
	
	/**
	 * 获取投票选项
	 * @param voteChoice
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/voteItem")
	public String voteItem(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, VoteChoice voteChoice, HttpServletResponse response)
			throws Exception {

		PageBean<Vote> pageBean = new PageBean<Vote>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = apiService.voteItem(voteChoice, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}
	
	/**
	 * 保存vote具体项
	 * @param vote
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/ItemSave")
	public String ItemSave(VoteChoice voteChoice, HttpServletResponse response) throws Exception {
		Integer result = apiService.ItemSave(voteChoice);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");
		}		
		return null;
	}
	
	@RequestMapping("/voteItemStatics")
	public String voteItemStatics(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, VoteInfo voteChoice, HttpServletResponse response)
			throws Exception {

		PageBean<VoteInfo> pageBean = new PageBean<VoteInfo>(Integer.parseInt(page), Integer.parseInt(rows));
		pageBean = apiService.voteItemStatics(voteChoice, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}
}
