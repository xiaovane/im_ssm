package api.user.dao;

import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;

public interface GroupUsersApiDao {

	/**
	 * 申请加入群组
	 * @param groupUsers
	 * @return
	 */
	public	Integer addGroup(GroupUsers groupUsers);

	/**
	 * 从群组里减人
	 * @param groupUsers
	 * @return
	 */
	public Integer deleteGroupUser(GroupUsers groupUsers);

	/**
	 * 更新群组状态
	 * @param groupUsers
	 * @return
	 */
	public Integer updateGroupUser(GroupUsers groupUsers);

	

}
