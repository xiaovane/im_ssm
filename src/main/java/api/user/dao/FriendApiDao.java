package api.user.dao;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.Friend;
import admin.entity.api.UserInfo;

public interface FriendApiDao {
	// api begin
	/**
	 * 添加好友
	 * 
	 * @param friend
	 * @return
	 */
	public Integer addFriend(Friend friend);

	/**
	 * 删除好友
	 * 
	 * @param friend
	 * @return
	 */
	public Integer deleteFriend(Friend friend);

	/**
	 * 加入黑名单
	 * 
	 * @param friend
	 * @return
	 */
	public Integer addBlackFriend(Friend friend);

	/**
	 * 移除黑名单
	 * 
	 * @param friend
	 * @return
	 */
	public Integer removeBlackFriend(Friend friend);

	/**
	 * 好友点赞
	 * 
	 * @param friend
	 * @return
	 */
	public Integer thumbFriend(Friend friend);

	/**
	 * 获取点赞数
	 * 
	 * @param friend
	 * @return
	 */
	public Integer getThumbCount(Friend friend);

	/**
	 * 获取是否点赞
	 * 
	 * @param friend
	 * @return
	 */
	public Integer getIsThumb(Friend friend);

	/**
	 * 点赞
	 * 
	 * @param friend
	 * @return
	 */
	public Integer thumb(Friend friend);

	/**
	 * 投诉好友
	 * 
	 * @param feedBack
	 * @return
	 */
	public Integer complainFriend(FeedBack feedBack);

	/**
	 * 设置星标好友
	 * 
	 * @param friend
	 * @return
	 */
	public Integer setStar(Friend friend);

	/**
	 * 给好友设置备注名
	 * 
	 * @param friend
	 * @return
	 */
	public Integer setBakName(Friend friend);

	// admin begin
	/**
	 * 获取反馈、投诉总数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 获取反馈、投诉列表
	 * 
	 * @param map
	 * @return
	 */
	public List<FeedBack> listFeedBack(Map<String, Object> map);

	/**
	 * 获取反馈、投诉详细页面
	 * 
	 * @param feedBack
	 * @return
	 */
	public FeedBack feedBackContent(FeedBack feedBack);

	/**
	 * 回复反馈、投诉内容
	 * 
	 * @param feedBack
	 * @return
	 */
	public Integer feedBackReply(FeedBack feedBack);

}
