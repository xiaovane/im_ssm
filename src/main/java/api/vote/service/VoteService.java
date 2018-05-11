package api.vote.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.Vote;
import admin.entity.api.VoteChoice;
import admin.entity.api.VoteInfo;

public interface VoteService {

	// api begin
	/**
	 * 创建vote
	 * 
	 * @param vote
	 * @return
	 */
	public Integer ctrateVote(Vote vote);

	/**
	 * 创建选项
	 * 
	 * @param voteChoice
	 * @return
	 */
	public Integer createVoteChoice(VoteChoice voteChoice);

	/**
	 * 投票
	 * 
	 * @param voteInfo
	 * @return
	 */
	public Integer addVoteInfo(VoteInfo voteInfo);

	/**
	 * 统计
	 * 
	 * @param voteInfo
	 * @return
	 */
	public List<VoteInfo> voteStatistics(VoteInfo voteInfo);

	/**
	 * 投票信息列表
	 * 
	 * @param map
	 * @return
	 */
	public List<VoteInfo> voteInfoList(Map<String, Object> map);

	/**
	 * 判断是否投票过
	 * 
	 * @param voteInfo
	 * @return
	 */
	public Integer selectVoteIs(VoteInfo voteInfo);

	/**
	 * 判断是否已经存在
	 * 
	 * @param voteInfo
	 * @return
	 */

	public Integer selectVoteInfoIs(VoteInfo voteInfo);
	
	/**
	 * 判断是否存在相同名称的投票
	 * @param vote
	 * @return
	 */

	public Integer isExitVote(Vote vote);

	// admin begin
	/**
	 * 获取vote列表
	 * 
	 * @param vote
	 * @param pageBean
	 * @return
	 */
	public PageBean<Vote> voteInfoList(Vote vote, PageBean<Vote> pageBean);

	/**
	 * 获取vote详细
	 * 
	 * @param vote
	 * @return
	 */
	public Vote voteInfoDetail(Vote vote);

	/**
	 * vote修改
	 * 
	 * @param vote
	 * @return
	 */
	public Integer voteInfoModify(Vote vote);

	/**
	 * 获取投票选项
	 * 
	 * @param voteChoice
	 * @param pageBean
	 * @return
	 */
	public PageBean<Vote> voteItem(VoteChoice voteChoice, PageBean<Vote> pageBean);

	/**
	 * 保存vote具体项
	 * 
	 * @param voteChoice
	 * @return
	 */
	public Integer ItemSave(VoteChoice voteChoice);

	/**
	 * 投票统计
	 * 
	 * @param voteChoice
	 * @param pageBean
	 * @return
	 */
	public PageBean<VoteInfo> voteItemStatics(VoteInfo voteChoice, PageBean<VoteInfo> pageBean);

	/**
	 * 获取统计结果
	 * @param voteInfo
	 * @return
	 */
	public List<VoteInfo> resultStatic(VoteInfo voteInfo);

	/**
	 * 获取所有投票列表
	 * @return
	 */
	public List<Vote> getVoteResultList();

}
