package api.vote.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.Vote;
import admin.entity.api.VoteChoice;
import admin.entity.api.VoteInfo;

public interface VoteDao {

	// api begin
	/**
	 * 创建投票
	 * 
	 * @param vote
	 * @return
	 */
	public Integer ctrateVote(Vote vote);

	/**
	 * 创建投票选项
	 * 
	 * @param voteChoice
	 * @return
	 */

	public Integer createVoteChoice(VoteChoice voteChoice);

	/**
	 * 用户投票
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
	 * 投票列表
	 * 
	 * @param map
	 * @return
	 */
	public List<VoteInfo> voteInfoList(Map<String, Object> map);

	/**
	 * 是否投票过
	 * 
	 * @param voteInfo
	 * @return
	 */
	public Integer selectVoteIs(VoteInfo voteInfo);

	/**
	 * 投票选项
	 * 
	 * @param voteInfo
	 * @return
	 */
	public Integer selectVoteInfoIs(VoteInfo voteInfo);

	/**
	 * 判断投票是否存在
	 * 
	 * @param vote
	 * @return
	 */
	public Integer isExitVote(Vote vote);

	// admin begin

	/**
	 * 获取分页总数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 获取vote列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Vote> voteInfoListAdmin(Map<String, Object> map);

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
	 * 获取分页总数
	 * 
	 * @param map
	 * @return
	 */
	public long getvoteItemTotal(Map<String, Object> map);

	/**
	 * 获取vote选项
	 * 
	 * @param voteChoice
	 * @return
	 */
	public List<Vote> voteItem(Map<String, Object> map);

	/**
	 * 保存vote选项
	 * 
	 * @param vote
	 * @return
	 */
	public Integer ItemSave(VoteChoice vote);

	/**
	 * 获取统计列表
	 * 
	 * @param map
	 * @return
	 */
	public List<VoteInfo> voteItemStatic(Map<String, Object> map);

	/**
	 * 统计总数
	 * 
	 * @param map
	 * @return
	 */
	public long getvoteItemStatic(Map<String, Object> map);

	/**
	 * 获取统计结果
	 * 
	 * @param voteInfo
	 * @return
	 */
	public List<VoteInfo> resultStatic(VoteInfo voteInfo);

	/**
	 * 获取统计列表
	 * 
	 * @return
	 */
	public List<Vote> getVoteResultList();

}
