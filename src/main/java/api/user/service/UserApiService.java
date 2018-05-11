package api.user.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.GroupInfo;
import admin.entity.api.UserInfo;

public interface UserApiService {

	// begin api接口
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
	public Integer inCountHome(UserInfo map);

	/**
	 * 判断用户登录是否正确
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer inCountUserLogin(UserInfo userInfo);

	/**
	 * 判断设备是否相同
	 * 
	 * @param userInfo
	 * @return
	 */
	public Integer inCountDevice(UserInfo userInfo);

	/**
	 * 注册用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer registUser(UserInfo user);

	/**
	 * 登录成功后用户信息返回
	 * 
	 * @param userId
	 * @return
	 */
	public UserInfo getUserInfoByUserId(UserInfo UserInfo);

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
	 * 好友查询
	 * 
	 * @param map
	 * @return
	 */
	public List<UserInfo> searchFriendList(Map<String, Object> map);

	/**
	 * 更新token
	 * 
	 * @param map
	 * @return
	 */
	public Integer setToken(Map<String, Object> map);

	/**
	 * 获取
	 * 
	 * @param userId
	 * @return
	 */
	public String getToken(String userId);

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
	 * 手机号是否注册
	 * 
	 * @param parameter
	 * @return
	 */
	public Integer isMobile(String parameter);

	/**
	 * 获取成员图片进行合成新的图片
	 * 
	 * @param id
	 * @return
	 */
	public String getUserImg(String id);

	/**
	 * 根据用户id获取用户昵称
	 * 
	 * @param string
	 * @return
	 */
	public String getUserNickName(String string);

	/**
	 * 注册用户补充信息
	 * 
	 * @param user
	 * @return
	 */
	public Integer registUpdate(UserInfo user);

	// begin admin
	/**
	 * 获取用户的相关信息列表
	 * 
	 * @param userInfo
	 * @param pageBean
	 * @return
	 */
	public PageBean<UserInfo> listUserInfo(UserInfo userInfo, PageBean<UserInfo> pageBean);

	/**
	 * 获取好友列表信息
	 * 
	 * @param friend
	 * @param pageBean
	 * @return
	 */
	public PageBean<UserInfo> listFriendInfo(Map<String, Object> map, PageBean<UserInfo> pageBean);

	/**
	 * 禁用用户
	 * 
	 * @param user
	 * @return
	 */
	public Integer userDeac(UserInfo user);

	/**
	 * 获取群组用户
	 * 
	 * @param map
	 * @param pageBean
	 * @return
	 */
	public PageBean<UserInfo> listGroupInfo(Map<String, Object> map, PageBean<UserInfo> pageBean);

	/**
	 * 根据mobie获取userId
	 * 
	 * @param user
	 * @return
	 */
	public String getUserId(UserInfo user);

	/**
	 * 搜索省份客服
	 * @param userInfo
	 * @return
	 */
	public List<UserInfo> getKeFu(UserInfo userInfo);

}
