package api.user.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.GroupGroup;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.UserInfo;

public interface GroupApiDao {
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
	 * 判断是否和用户id重复
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer isLikeUser(GroupInfo groupInfo);

	/**
	 * 判断是否存在群id
	 * 
	 * @param groupInfo
	 * @return
	 */
	public Integer isLikeGroup(GroupInfo groupInfo);
	
	/**
	 * 获取系统群
	 * @param userId
	 * @return
	 */
	public List<GroupInfo> getAdminGroupList(String userId);
	
	// admin begin
	/**
	 * 获取的群组总数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 群组列表
	 * 
	 * @param map
	 * @return
	 */
	public List<UserInfo> listGroupInfo(Map<String, Object> map);

	/**
	 * 判断业委会群是否存在
	 * 
	 * @param ywh
	 * @return
	 */
	public Integer isExistGroupYwh(String ywh);

	
	/**
	 * 更加nickName获取群id
	 * 
	 * @param nickName
	 * @return
	 */
	public String getGroupIdByName(String nickName);

	/**
	 * 根据userId获取群组
	 * @param userId
	 * @return
	 */
	public List<GroupInfo> getGroupInfoList(String userId);

	/**
	 * 获取上级group
	 * @param groupId
	 * @return
	 */
	public GroupInfo getDetailGroup(String groupId);

	/**
	 * 创建群之间的关系
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
