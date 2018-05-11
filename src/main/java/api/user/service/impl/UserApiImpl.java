package api.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import api.user.dao.UserApiDao;
import api.user.service.UserApiService;
import admin.entity.PageBean;
import admin.entity.api.UserInfo;

@Service("userApiService")

public class UserApiImpl implements UserApiService {
	// begin api
	@Resource
	private UserApiDao userApiDao;

	public Integer inCountUser(String userId) {
		return userApiDao.inCountUser(userId);
	}

	public Integer inCountMobile(String mobile) {
		return userApiDao.inCountMobile(mobile);
	}

	public Integer inCountHome(UserInfo userInfo) {
		return userApiDao.inCountHome(userInfo);
	}

	public Integer inCountUserLogin(UserInfo userInfo) {
		return userApiDao.inCountUserLogin(userInfo);
	}

	public Integer registUser(UserInfo user) {
		return userApiDao.registUser(user);
	}

	public Integer inCountDevice(UserInfo userInfo) {
		return userApiDao.inCountDevice(userInfo);
	}

	public UserInfo getUserInfoByUserId(UserInfo userId) {
		return userApiDao.getUserInfoByUserId(userId);
	}

	public Integer modifyPwd(UserInfo user) {
		return userApiDao.modifyPwd(user);
	}

	public Integer modifyNickName(UserInfo user) {
		return userApiDao.modifyNickName(user);
	}

	public Integer checkPwd(UserInfo user) {
		return userApiDao.checkPwd(user);
	}

	public List<UserInfo> searchFriendList(Map<String, Object> map) {
		return userApiDao.searchFriendList(map);
	}

	public String getToken(String userId) {
		return userApiDao.getToken(userId);
	}

	public Integer setToken(Map<String, Object> map) {
		return userApiDao.setToken(map);
	}

	public Integer updateToken(Map<String, Object> map) {
		return userApiDao.updateToken(map);
	}

	public Integer updatePersonInfo(UserInfo user) {
		return userApiDao.updatePersonInfo(user);
	}

	public Integer hiddenPersonInfo(UserInfo user) {
		return userApiDao.hiddenPersonInfo(user);
	}

	@Override
	public List<UserInfo> getFriendList(UserInfo userInfo) {
		return userApiDao.getFriendList(userInfo);
	}

	// begin admin
	@Override
	public PageBean<UserInfo> listUserInfo(UserInfo userInfo, PageBean<UserInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("userInfo", userInfo);
		// 总记录放入pageBean
		pageBean.setTotal(userApiDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(userApiDao.listUserInfo(map));
		return pageBean;

	}

	public PageBean<UserInfo> listFriendInfo(Map<String, Object> user, PageBean<UserInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("user", user);
		// 总记录放入pageBean
		pageBean.setTotal(userApiDao.getFriendTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(userApiDao.listFriendInfo(map));
		return pageBean;
	}

	@Override
	public Integer userDeac(UserInfo user) {
		return userApiDao.userDeac(user);
	}

	@Override
	public PageBean<UserInfo> listGroupInfo(Map<String, Object> group, PageBean<UserInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("group", group);
		// 总记录放入pageBean
		pageBean.setTotal(userApiDao.getGroupTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(userApiDao.listGroupInfo(map));
		return pageBean;
	}

	@Override
	public Integer resetPwd(UserInfo user) {
		return userApiDao.resetPwd(user);
	}

	@Override
	public Integer isMobile(String parameter) {
		return userApiDao.isMobile(parameter);
	}

	@Override
	public String getUserId(UserInfo user) {
		return userApiDao.getUserId(user);
	}

	@Override
	public String getUserImg(String id) {
		return userApiDao.getUserImg(id);
	}

	@Override
	public String getUserNickName(String string) {
		return userApiDao.getUserNickName(string);
	}

	@Override
	public Integer registUpdate(UserInfo user) {
		return userApiDao.registUpdate(user);
	}

	@Override
	public List<UserInfo> getKeFu(UserInfo userInfo) {
		return userApiDao.getKeFu(userInfo);
	}
}
