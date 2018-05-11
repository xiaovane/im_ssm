package api.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import api.user.dao.FriendApiDao;
import api.user.dao.GroupApiDao;
import api.user.dao.GroupUsersApiDao;
import api.user.dao.UserApiDao;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import admin.entity.PageBean;
import admin.entity.api.GroupGroup;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.UserInfo;

@Service("groupApiService")

public class GroupApiImpl implements GroupApiService {

	@Resource
	private GroupApiDao groupApiDao;
	@Resource
	private GroupUsersApiDao groupUsersApiDao;

	@Resource
	private UserApiDao userInfo;

	public Integer creatGroup(GroupInfo groupInfo) {
		return groupApiDao.creatGroup(groupInfo);
	}

	public Integer deleteGroup(GroupInfo groupInfo) {
		return groupApiDao.deleteGroup(groupInfo);
	}

	public Integer modifyGroup(GroupInfo groupInfo) {
		return groupApiDao.modifyGroup(groupInfo);
	}

	public Integer addGroup(GroupUsers groupUsers) {
		return groupUsersApiDao.addGroup(groupUsers);
	}

	public Integer deleteGroupUser(GroupUsers groupUsers) {
		return groupUsersApiDao.deleteGroupUser(groupUsers);
	}

	public Integer updateGroupUser(GroupUsers groupUsers) {
		return groupUsersApiDao.updateGroupUser(groupUsers);
	}

	@Override
	public PageBean<UserInfo> listGroupInfo(GroupInfo groupInfo, PageBean<UserInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("groupInfo", groupInfo);
		// 总记录放入pageBean
		pageBean.setTotal(groupApiDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(groupApiDao.listGroupInfo(map));
		return pageBean;
	}

	@Override
	public Integer isLikeUser(GroupInfo groupInfo) {
		return groupApiDao.isLikeUser(groupInfo);
	}

	@Override
	public Integer isLikeGroup(GroupInfo groupInfo) {
		return groupApiDao.isLikeGroup(groupInfo);
	}

	@Override
	public Integer isExistGroupYwh(String ywh) {
		return groupApiDao.isExistGroupYwh(ywh);
	}

	@Override
	public String getGroupIdByName(String nickName) {
		return groupApiDao.getGroupIdByName(nickName);
	}

	@Override
	public List<GroupInfo> getGroupInfoList(String userId) {
		// 获取群列表
		List<GroupInfo> groupList = groupApiDao.getGroupInfoList(userId);
		for (GroupInfo groupInfo : groupList) {
			List<UserInfo> listUserInfo = userInfo.listUserInfoByGroupId(groupInfo.getGroupId());
			groupInfo.setMember(listUserInfo);
		}
		
		return groupList;

	}

	@Override
	public GroupInfo getDetailGroup(String groupId) {
		return groupApiDao.getDetailGroup(groupId);
	}

	@Override
	public Integer linkGroup2(GroupGroup groupGroup) {
		return groupApiDao.linkGroup2(groupGroup);
	}

	@Override
	public List<GroupInfo> getAdminGroupList(String userId) {
		List<GroupInfo> groupList = groupApiDao.getAdminGroupList(userId);
		for (GroupInfo groupInfo : groupList) {
			List<UserInfo> listUserInfo = userInfo.listUserInfoByGroupId(groupInfo.getGroupId());
			groupInfo.setMember(listUserInfo);
		}
		
		return groupList;
	}

	@Override
	public GroupInfo getAdminGroupDetail(String groupId) {
		GroupInfo groupInfo =groupApiDao.getAdminGroupDetail(groupId);
		List<UserInfo> listUserInfo = userInfo.listUserInfoByGroupId(groupId);
		groupInfo.setMember(listUserInfo);
		return groupInfo;
	}

}
