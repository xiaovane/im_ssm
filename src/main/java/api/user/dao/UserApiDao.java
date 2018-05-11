package api.user.dao;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.UserInfo;

public interface UserApiDao {
	// begin api
	/**
	 * 判断用户id是否存在
	 * 
	 * @param userId
	 * @return
	 */
	public Integer inCountUser(String userId);

	/**
	 * 判断用户mobile是否存在
	 * 
	 * @param mobile
	 * @return
	 */
	public Integer inCountMobile(String mobile);

	/**
	 * 判断小区信息是否注册
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer inCountHome(UserInfo userInfo);

	/**
	 * 判断用户登录是否正确
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer inCountUserLogin(UserInfo userInfo);

	/**
	 * 用户注册
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer registUser(UserInfo userInfo);

	/**
	 * 用户填写用户名和密码登录
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer getUserInfoByName(UserInfo userInfo);

	/**
	 * 同一设备判断
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer inCountDevice(UserInfo userInfo);

	/**
	 * 用户登录成功后返回信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(UserInfo userId);

	/**
	 * 用户修改密码
	 * 
	 * @param user
	 * @return
	 */
	public Integer modifyPwd(UserInfo user);

	/**
	 * 重置用户密码
	 * 
	 * @param user
	 * @return
	 */

	public Integer resetPwd(UserInfo user);

	/**
	 * 修改用户昵称
	 * 
	 * @param user
	 * @return
	 */

	public Integer modifyNickName(UserInfo user);

	/**
	 * 判断原密码是否正确
	 * 
	 * @param user
	 * @return
	 */
	public Integer checkPwd(UserInfo user);

	/**
	 * 查询好友
	 * 
	 * @param map
	 * @return
	 */
	public List<UserInfo> searchFriendList(Map<String, Object> map);

	/**
	 * 获取token
	 * 
	 * @param user
	 * @return
	 */
	public String getToken(String user);

	/**
	 * 设置token
	 * 
	 * @param map
	 * @return
	 */
	public Integer setToken(Map<String, Object> map);

	/**
	 * 更新token
	 * 
	 * @param map
	 * @return
	 */
	public Integer updateToken(Map<String, Object> map);

	/**
	 * 更新个人信息
	 * 
	 * @param user
	 * @return
	 */
	public Integer updatePersonInfo(UserInfo user);

	/**
	 * 隐藏个人信息
	 * 
	 * @param user
	 * @return
	 */
	public Integer hiddenPersonInfo(UserInfo user);

	/**
	 * 获取好友列表
	 * 
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> getFriendList(UserInfo userInfo);

	/**
	 * 判断手机号是否注册
	 * @param parameter
	 * @return
	 */
	public Integer isMobile(String parameter);
	
	/**
	 * 根据手机号获取userID
	 * @param user
	 * @return
	 */
	public String getUserId(UserInfo user);
	
	/**
	 * 补充用户信息
	 * @param user
	 * @return
	 */
	public Integer registUpdate(UserInfo user);
	
	/**
	 * 根据群id获取成员信息
	 * @param groupId
	 * @return
	 */
	public List<UserInfo> listUserInfoByGroupId(String groupId);

	// begin admin
	/**
	 * 获取用户相关信息
	 * 
	 * @param userInfo
	 * @param pageBean
	 * @return
	 */
	public List<UserInfo> listUserInfo(Map<String, Object> map);

	/**
	 * 获取总页数
	 * 
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String, Object> map);

	/**
	 * 获取总数进行分页
	 * 
	 * @param map
	 * @return
	 */
	public long getFriendTotal(Map<String, Object> map);

	/**
	 * 获取好友列表
	 * 
	 * @param map
	 * @return
	 */
	public List<UserInfo> listFriendInfo(Map<String, Object> map);

	/**
	 * 禁启用用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer userDeac(UserInfo user);

	/**
	 * 获取群组成员总数
	 * 
	 * @param map
	 * @return
	 */
	public long getGroupTotal(Map<String, Object> map);

	/**
	 * 获取群组成员列表
	 * 
	 * @param map
	 * @return
	 */
	public List<UserInfo> listGroupInfo(Map<String, Object> map);

	/**
	 * 获取用户图片
	 * @param id
	 * @return
	 */
	public String getUserImg(String id);

	/**
	 * 获取用户昵称
	 * @param string
	 * @return
	 */
	public String getUserNickName(String string);

	/**
	 * 搜索客服
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> getKeFu(UserInfo userInfo);

	

}
