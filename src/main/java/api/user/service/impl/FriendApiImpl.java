package api.user.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.Friend;
import admin.entity.api.UserInfo;
import api.user.dao.FriendApiDao;
import api.user.service.FriendApiService;

@Service("friendApiService")

public class FriendApiImpl implements FriendApiService {

	@Resource
	private FriendApiDao friendApiDao;

	public Integer addFriend(Friend friend) {

		return friendApiDao.addFriend(friend);
	}

	public Integer deleteFriend(Friend friend) {
		return friendApiDao.deleteFriend(friend);
	}

	public Integer addBlackFriend(Friend friend) {
		return friendApiDao.addBlackFriend(friend);
	}

	public Integer removeBlackFriend(Friend friend) {
		return friendApiDao.removeBlackFriend(friend);
	}

	public Integer thumbFriend(Friend friend) {
		return friendApiDao.thumbFriend(friend);
	}

	public Integer getThumbCount(Friend friend) {
		return friendApiDao.getThumbCount(friend);
	}

	public Integer getIsThumb(Friend friend) {
		return friendApiDao.getIsThumb(friend);
	}

	public Integer thumb(Friend friend) {
		return friendApiDao.thumb(friend);
	}

	public Integer complainFriend(FeedBack feedBack) {
		return friendApiDao.complainFriend(feedBack);
	}

	@Override
	public Integer setStar(Friend friend) {
		return friendApiDao.setStar(friend);
	}

	@Override
	public PageBean<FeedBack> listFeedBack(FeedBack feedBack, PageBean<FeedBack> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("feedBack", feedBack);
		// 总记录放入pageBean
		pageBean.setTotal(friendApiDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(friendApiDao.listFeedBack(map));
		return pageBean;
	}

	@Override
	public FeedBack feedBackContent(FeedBack feedBack) {
		return friendApiDao.feedBackContent(feedBack);
	}

	@Override
	public Integer feedBackReply(FeedBack feedBack) {
		return friendApiDao.feedBackReply(feedBack);
	}

	@Override
	public Integer setBakName(Friend friend) {
		return friendApiDao.setBakName(friend);
	}

}
