package api.user.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.GroupGroup;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.UserInfo;

public interface GroupApiService {

	// api begin
	/**
	 * 创建群组
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer creatGroup(GroupInfo groupInfo);

	/**
	 * 删除群组
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer deleteGroup(GroupInfo groupInfo);

	/**
	 * 修改群组
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer modifyGroup(GroupInfo groupInfo);

	/**
	 * 申请加入群组
	 * 
	 * @param groupUsers
	 * @return
	 */
	public Integer addGroup(GroupUsers groupUsers);

	/**
	 * 从群组里减人
	 * 
	 * @param groupUsers
	 * @return
	 */
	public Integer deleteGroupUser(GroupUsers groupUsers);

	/**
	 * 更新群组状态
	 * 
	 * @param groupUsers
	 * @return
	 */
	public Integer updateGroupUser(GroupUsers groupUsers);

	/**
	 * 判断是否存在重复
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer isLikeUser(GroupInfo groupInfo);

	/**
	 * 判断群id是否已经存在
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer isLikeGroup(GroupInfo groupInfo);

	/**
	 * 判断业委会群是否存在
	 * 
	 * @return
	 */
	public Integer isExistGroupYwh(String ywh);

	/**
	 * 通过昵称获取群id
	 * 
	 * @param nickName
	 * @return
	 */
	public String getGroupIdByName(String nickName);

	/**
	 * 通过userId获取群组
	 * 
	 * @param userId
	 * @return
	 */
	public List<GroupInfo> getGroupInfoList(String userId);

	/**
	 * 获取系统6个群
	 * @param userId
	 * @return
	 */
	public List<GroupInfo> getAdminGroupList(String userId);


	// admin begin
	/**
	 * 获取群组列表
	 * 
	 * @param groupInfo
	 * @param pageBean
	 * @return
	 */
	public PageBean<UserInfo> listGroupInfo(GroupInfo groupInfo, PageBean<UserInfo> pageBean);

	/**
	 * 获取上级群id
	 * @param groupId
	 * @return
	 */
	public GroupInfo getDetailGroup(String groupId);

	/**
	 * 创建群与群之间的关系
	 * @param groupGroup
	 * @return
	 */
	public Integer linkGroup2(GroupGroup groupGroup);

	/**
	 * 获取群组详细
	 * @param groupId
	 * @return
	 */
	public GroupInfo getAdminGroupDetail(String groupId);

	
	

}
