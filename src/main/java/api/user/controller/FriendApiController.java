package api.user.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import api.emchat.api.impl.EasemobIMUsers;
import io.swagger.client.model.UserName;
import io.swagger.client.model.UserNames;
import api.user.service.FriendApiService;
import api.user.service.UserApiService;
import admin.entity.api.FeedBack;
import admin.entity.api.Friend;
import admin.util.Validation;

@Controller
@RequestMapping("/clientFriend")
public class FriendApiController {

	@Resource
	private FriendApiService apiService;
	@Resource
	private UserApiService apiUserService;
	private static final Logger logger = LoggerFactory.getLogger(FriendApiController.class);
	private String token = "";
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currentTime = new Date();

	/**
	 * 添加好友
	 * 
	 * @param request
	 * @param response
	 * @param info
	 * @throws Exception
	 */
	@RequestMapping(value = "/addFriend")
	public void addFriend(HttpServletRequest request, HttpServletResponse response, Friend friend) throws Exception {

		try {
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}

			// 调用环信加好友接口
			// try {
			// EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			// Object result =
			// easemobIMUsers.addFriendSingle(friend.getUserId(),
			// friend.getFriendId());
			// logger.info(result.toString());
			// } catch (Exception e) {
			// Validation.postJson(response,"", "150", "环信接口调用失败", "false");
			// return;
			// }

			// 调用成功则往数据库里插入数据
			Integer result = apiService.addFriend(friend);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "添加好友失败", "false");
				return;
			}
			
			//同时加入好友的id到数据库
			String friendIdNew=friend.getUserId();
			friend.setUserId(friend.getFriendId());
			friend.setFriendId(friendIdNew);
			Integer resultFriend = apiService.addFriend(friend);
			if (resultFriend.equals(0)) {
				Validation.postJson(response, token, "160", "添加好友失败", "false");
				return;
			}
			

			// 数据库中查询token
			token = apiUserService.getToken(friend.getUserId());
			Validation.postJson(response, token, "100", "添加好友成功", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除好友
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 * @throws Exception
	 */
	@RequestMapping(value = "deleteFriend")
	public void deleteFriend(HttpServletRequest request, HttpServletResponse response, Friend friend) throws Exception {
		// 调用环信接口去删除好友
		try {
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();
			Object result = easemobIMUsers.deleteFriendSingle(friend.getUserId(), friend.getFriendId());
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则删除数据记录
		try {
			Integer result = apiService.deleteFriend(friend);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "好友删除失败", "false");
				return;
			}

			// 数据库中查询token
			token = apiUserService.getToken(friend.getUserId());
			Validation.postJson(response, token, "100", "好友删除成功", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 加入黑名单
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 * @throws Exception
	 */
	@RequestMapping(value = "addBlackFriend")
	public void addBlackFriend(HttpServletRequest request, HttpServletResponse response, Friend friend)
			throws Exception {

		// 调用环信接口去把好友拉进黑名单
		try {
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

			UserNames userNames = new UserNames();
			UserName userList = new UserName();
			userList.add(friend.getFriendId());
			userNames.usernames(userList);

			Object result = easemobIMUsers.addToBlackList(friend.getUserId(), userNames);
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则让好友表的状态变成1
		Integer result = apiService.addBlackFriend(friend);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "拉入黑名单失败", "false");
			return;
		}
		// 数据库中查询token
		token = apiUserService.getToken(friend.getUserId());
		Validation.postJson(response, token, "100", "拉入黑名单成功", "true");
	}

	/**
	 * 移出黑名单
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 * @throws Exception
	 */
	@RequestMapping(value = "removeBlackFriend")
	public void removeBlackFriend(HttpServletRequest request, HttpServletResponse response, Friend friend)
			throws Exception {

		// 调用环信接口去把好友移出黑名单
		try {
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}
			EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

			Object result = easemobIMUsers.removeFromBlackList(friend.getUserId(), friend.getFriendId());
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则让好友表的状态变成0
		try {
			Integer result = apiService.removeBlackFriend(friend);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "移出黑名单失败", "false");
				return;
			}
			// 数据库中查询token
			token = apiUserService.getToken(friend.getUserId());
			Validation.postJson(response, token, "100", "移出黑名单成功", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 给好友点赞
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 * @throws Exception
	 */
	@RequestMapping(value = "thumbFriend")
	@Transactional(propagation = Propagation.REQUIRED)
	public void thumbFriend(HttpServletRequest request, HttpServletResponse response, Friend friend) throws Exception {

		try {
			String content = "点赞成功";
			// 判断参数是否为空
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}

			// 判断是否点过赞
			Integer isThumb = apiService.getIsThumb(friend);
			if (isThumb > 0) {
				Validation.postJson(response, token, "116", "您已经点过赞了", "false");
				return;
			}

			// 进行点赞操作
			Integer thumb = apiService.thumb(friend);
			if (thumb.equals(0)) {
				Validation.postJson(response, token, "160", "点赞失败", "false");
				return;
			}
			// 点赞超过三次才会刷新token
			Integer thumbCount = apiService.getThumbCount(friend);
			if (thumbCount > 2) {
				// 更新数据库中的token
				token = UUID.randomUUID().toString();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", friend.getFriendId());
				map.put("token", token);

				Integer key = apiUserService.updateToken(map);
				if (key.equals(0)) {
					Validation.postJson(response, token, "160", "更新token失败", "false");
					return;
				}

				// 给是否点赞状态变成1
				Integer result = apiService.thumbFriend(friend);
				if (result.equals(0)) {
					Validation.postJson(response, token, "160", "更新点赞状态失败", "false");
					return;
				}
				content = "您已经被好友点赞3次，可以进行群组聊天等相关功能";
			}

			Validation.postJson(response, token, "100", content, "true");
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 投诉反馈
	 * 
	 * @param request
	 * @param response
	 * @param feedBack
	 */
	@RequestMapping(value = "/feedBack")
	public void feedBack(HttpServletRequest request, HttpServletResponse response, FeedBack feedBack) {
		try {
			// 判断参数是否为空
			if (feedBack.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}
            
			//设置反馈内容id和时间
			feedBack.setInfoId(UUID.randomUUID().toString());
			feedBack.setFeedBackTime(formatter.format(currentTime));
			// 对某人进行投诉
			Integer complain = apiService.complainFriend(feedBack);
			if (complain.equals(0)) {
				Validation.postJson(response, token, "160", "投诉反馈失败", "false");
				return;
			}
			Validation.postJson(response, token, "100", "您的投诉反馈已经提交成功，请耐心等待反馈结果", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置星标好友
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 */
	@RequestMapping(value = "/setStar")
	@Transactional(propagation = Propagation.REQUIRED)
	public void setStar(HttpServletRequest request, HttpServletResponse response, Friend friend) {
		try {
			// 判断参数是否为空
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}

			try {
				friend.setIsStar(1);
				// 设为星标好友
				Integer isStar = apiService.setStar(friend);
				if (isStar.equals(0)) {
					Validation.postJson(response, token, "160", "设置星标好友失败", "false");
					return;
				}

				// 更新数据库中的token
				token = UUID.randomUUID().toString();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", friend.getUserId());
				map.put("token", token);

				Integer key = apiUserService.updateToken(map);
				if (key.equals(0)) {
					Validation.postJson(response, token, "160", "更新token失败", "false");
					return;
				}

				Validation.postJson(response, token, "100", "设置星标好友成功", "true");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 取消星标好友
	 * 
	 * @param request
	 * @param response
	 * @param friend
	 */
	@RequestMapping(value = "/setStarNO")
	@Transactional(propagation = Propagation.REQUIRED)
	public void setStarNO(HttpServletRequest request, HttpServletResponse response, Friend friend) {
		try {
			// 判断参数是否为空
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}

			try {
				friend.setIsStar(0);
				// 取消星标好友
				Integer isStar = apiService.setStar(friend);
				if (isStar.equals(0)) {
					Validation.postJson(response, token, "160", "取消星标好友失败", "false");
					return;
				}

				// 更新数据库中的token
				token = UUID.randomUUID().toString();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", friend.getUserId());
				map.put("token", token);

				Integer key = apiUserService.updateToken(map);
				if (key.equals(0)) {
					Validation.postJson(response, token, "160", "更新token失败", "false");
					return;
				}

				Validation.postJson(response, token, "100", "取消星标好友成功", "true");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 给好友设置备注名
	 * @param request
	 * @param response
	 * @param friend
	 */
	@RequestMapping(value = "/setBakName")
	@Transactional(propagation = Propagation.REQUIRED)
	public void setBakName(HttpServletRequest request, HttpServletResponse response, Friend friend) {
		try {
			// 判断参数是否为空
			if (friend.equals(null)) {
				Validation.postJson(response, token, "101", "参数不能为空", "false");
				return;
			}

			try {
				// 设置备注名
				Integer isStar = apiService.setBakName(friend);
				if (isStar.equals(0)) {
					Validation.postJson(response, token, "160", "设置备注名失败", "false");
					return;
				}

				// 更新数据库中的token
				token = UUID.randomUUID().toString();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("userId", friend.getUserId());
				map.put("token", token);

				Integer key = apiUserService.updateToken(map);
				if (key.equals(0)) {
					Validation.postJson(response, token, "160", "更新token失败", "false");
					return;
				}

				Validation.postJson(response, token, "100", "设置备注名成功", "true");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
