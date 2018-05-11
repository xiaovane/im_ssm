package api.user.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;

import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.util.Validation;
import api.emchat.api.impl.EasemobChatGroup;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import io.swagger.client.model.Group;
import io.swagger.client.model.ModifyGroup;
import io.swagger.client.model.UserName;
import io.swagger.client.model.UserNames;

@Controller
@RequestMapping("/clientUserGroup")
public class UserGroupApiController {

	@Resource
	private GroupApiService apiService;
	@Resource
	private UserApiService apiUserService;
	private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
	private static final Logger logger = LoggerFactory.getLogger(UserGroupApiController.class);
	private String token = "";

	/**
	 * 创建群组
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/createGroup")
	public void createGroup(HttpServletRequest request, HttpServletResponse response, GroupInfo groupInfo,
			GroupUsers groupUsers) throws Exception {

		if (groupInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		// 判断群Id是否和用户id相同
		Integer isLike = apiService.isLikeUser(groupInfo);
		if (isLike > 0) {
			Validation.postJson(response, token, "121", "群id和用户id重复", "false");
			return;
		}

		// 判断群Id是否存在
		Integer isLikeGroup = apiService.isLikeGroup(groupInfo);
		if (isLike > 0) {
			Validation.postJson(response, token, "122", "群id已经存在", "false");
			return;
		}

		// 调用环信加修创建群组的接口
		try {
			// 批量获取群成员
			UserName userList = new UserName();
			String[] user = groupUsers.getUserId().split(",");
			for (String id : user) {
				userList.add(id);
			}

			// 批量获取成员的昵称
			String nickName = "";
			int num = user.length;
			if (num > 3) {
				num = 3;
			}
			for (int i = 0; i < num; i++) {
				nickName += apiUserService.getUserNickName(user[i]) + "、";
			}

			Group group = new Group();
			group.groupname(UUID.randomUUID() + nickName).desc(nickName)._public(true).maxusers(1000).approval(false)
					.owner(groupInfo.getCreateName()).members(userList);
			Object result = easemobChatGroup.createChatGroup(group);
			logger.info(result.toString());

			// 设置groupId
			String[] groupNew = result.toString().split(",");
			String[] groupNewId = {};
			for (String data : groupNew) {
				if (data.indexOf("data") > 0) {
					groupNewId = data.split(":");
					groupInfo.setGroupId(groupNewId[2].substring(2, groupNewId[2].length() - 5));
					groupInfo.setGroupNickName(nickName);
					groupInfo.setIsAdmin(0);
				}
			}
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里插入数据
		try {
			// 遍历循环获取成员的图像
			String imageUrl = "/userImages/group/" + UUID.randomUUID() + ".png";
			// 图片输出路径
			String imagePath = request.getServletContext().getRealPath("/") + imageUrl;
			String[] userImg = groupUsers.getUserId().split(",");

			List<String> list = new ArrayList<String>();
			for (int i = 0; i < userImg.length; i++) {
				list.add(request.getServletContext().getRealPath("/")
						+ apiUserService.getUserImg(userImg[i]).replace("/", "\\"));
			}

			// 获取成员的图片生成新图片，插入数据库
			if (!Validation.getCombinationOfhead(list, imagePath)) {
				Validation.postJson(response, token, "160", "创建群组失败", "false");
				return;

			}

			groupInfo.setGroupImage(imageUrl);
			Integer result = apiService.creatGroup(groupInfo);

			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "创建群组失败", "false");
				return;
			}

			// 默认创建者往群里加人
			groupUsers.setGroupId(groupInfo.getGroupId());
			groupUsers.setUserId(groupInfo.getCreateName());
			Integer resultGroupUsers = apiService.addGroup(groupUsers);
			if (resultGroupUsers.equals(0)) {
				Validation.postJson(response, token, "160", "创建群组失败", "false");
				return;
			}
			token = apiUserService.getToken(groupInfo.getCreateName());
			Validation.postJson(response, token, "100", "创建群组成功", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 删除群组
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteUserGroup")
	public void deleteGroup(HttpServletRequest request, HttpServletResponse response, GroupInfo groupInfo)
			throws Exception {
		if (groupInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加删除群组的接口
		try {

			Object result = easemobChatGroup.deleteChatGroup(groupInfo.getGroupId());
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里删除数据
		Integer result = apiService.deleteGroup(groupInfo);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "删除群组失败", "false");
			return;
		}

		token = apiUserService.getToken(groupInfo.getCreateName());
		Validation.postJson(response, token, "100", "删除群组成功", "true");
	}

	/**
	 * 修改群组
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/modifyGroup")
	public void modifyGroup(HttpServletRequest request, HttpServletResponse response, GroupInfo groupInfo)
			throws Exception {
		if (groupInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信添加修改群组的接口
		try {
			ModifyGroup group = new ModifyGroup();
			group.description(groupInfo.getDescription()).groupname(groupInfo.getGroupNickName())
					.maxusers(groupInfo.getGroupSize());
			Object result = easemobChatGroup.modifyChatGroup(groupInfo.getGroupId(), group);
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 生成图片，插入数据库
		if (groupInfo.getGroupImage() != null && groupInfo.getGroupImage() != "") {
			groupInfo.setGroupImage(Validation.getImagesUrl(request, groupInfo.getGroupImage()));
		}

		Integer result = apiService.modifyGroup(groupInfo);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "修改群组失败", "false");
			return;
		}

		token = apiUserService.getToken(groupInfo.getCreateName());
		Validation.postJson(response, token, "100", "修改群组成功", "true");
	}

	/**
	 * 申请加入群组
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/addGroup")
	public void addGroup(HttpServletRequest request, HttpServletResponse response, GroupUsers groupUsers)
			throws Exception {

		if (groupUsers.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加群组添加人员的接口
		try {

			Object result = easemobChatGroup.addSingleUserToChatGroup(groupUsers.getGroupId(), groupUsers.getUserId());
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里添加数据
		Integer result = apiService.addGroup(groupUsers);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "申请加入群组失败", "false");
		}
		token = apiUserService.getToken(groupUsers.getUserId());
		Validation.postJson(response, token, "100", "申请加入群组成功", "true");
	}

	/**
	 * 申请加入群组，批量
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/addGroupBatch")
	public void addGroupBatch(HttpServletRequest request, HttpServletResponse response, GroupUsers groupUsers)
			throws Exception {

		if (groupUsers.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加群组添加人员的接口
		try {

			UserNames userNames = new UserNames();
			UserName userList = new UserName();
			String[] user = groupUsers.getUserId().split(",");
			for (String id : user) {
				userList.add(id);
			}
			userNames.usernames(userList);
			Object result = easemobChatGroup.addBatchUsersToChatGroup(groupUsers.getGroupId(), userNames);
			logger.info(result.toString());

		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里添加数据
		// 批量加人
		String[] userBatch = groupUsers.getUserId().split(",");
		for (String idMore : userBatch) {
			// 加入到userId中
			groupUsers.setUserId(idMore);
			Integer result = apiService.addGroup(groupUsers);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "申请加入群组失败", "false");
			}
		}

		token = apiUserService.getToken(groupUsers.getUserId());
		Validation.postJson(response, token, "100", "申请加入群组成功", "true");
	}

	/**
	 * 退出群组
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteGroup")
	public void deleteGroup(HttpServletRequest request, HttpServletResponse response, GroupUsers groupUsers)
			throws Exception {
		if (groupUsers.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加群组删减人员的接口
		try {

			Object result = easemobChatGroup.removeSingleUserFromChatGroup(groupUsers.getGroupId(),
					groupUsers.getUserId());
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里删减数据
		Integer result = apiService.deleteGroupUser(groupUsers);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "退出群组失败", "false");
			return;
		}
		token = apiUserService.getToken(groupUsers.getUserId());
		Validation.postJson(response, token, "100", "退出群组成功", "true");
	}

	/**
	 * 群组人员添加黑名单
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/addBlackGroupUser")
	public void addBlackGroupUser(HttpServletRequest request, HttpServletResponse response, GroupUsers groupUsers)
			throws Exception {

		if (groupUsers.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加群组添加黑名单人员的接口
		try {

			Object result = easemobChatGroup.addSingleBlockUserToChatGroup(groupUsers.getGroupId(),
					groupUsers.getUserId());
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里更新数据
		groupUsers.setStatus(2);
		Integer result = apiService.updateGroupUser(groupUsers);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "群组人员添加黑名单失败", "false");
			return;
		}
		token = apiUserService.getToken(groupUsers.getUserId());
		Validation.postJson(response, token, "100", "群组人员添加黑名单成功", "true");
	}

	/**
	 * 群组人员减去黑名单
	 * 
	 * @param request
	 * @param response
	 * @param groupInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteBlackGroupUser")
	public void deleteBlackGroupUser(HttpServletRequest request, HttpServletResponse response, GroupUsers groupUsers)
			throws Exception {

		if (groupUsers.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}
		// 调用环信加群组添加黑名单人员的接口
		try {

			Object result = easemobChatGroup.removeSingleBlockUserFromChatGroup(groupUsers.getGroupId(),
					groupUsers.getUserId());
			logger.info(result.toString());
		} catch (Exception e) {
			Validation.postJson(response, token, "150", "环信接口调用失败", "false");
			return;
		}

		// 调用成功则往数据库里更新数据
		groupUsers.setStatus(1);
		Integer result = apiService.updateGroupUser(groupUsers);
		if (result.equals(0)) {
			Validation.postJson(response, token, "160", "群组人员移除黑名单失败", "false");
			return;
		}
		token = apiUserService.getToken(groupUsers.getUserId());
		Validation.postJson(response, token, "100", "群组人员移除黑名单成功", "true");
	}

}
