package admin.service.impl;

import org.springframework.stereotype.Service;
import admin.dao.UserAdminDao;
import admin.entity.PageBean;
import admin.entity.UserAdmin;
import admin.entity.api.AdminPower;
import admin.entity.api.Menu;
import admin.service.UserAdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * Created by xp on 2017/4/13.
 */
@Service
public class UserAdminServiceImpl implements UserAdminService{

    @Resource
    private UserAdminDao UserAdminDao;

    public UserAdmin getUserAdminData(UserAdmin userAdmin) {
        return UserAdminDao.getUserAdminData(userAdmin);
    }

    public UserAdmin getUserAdminByName(String username) {
        return UserAdminDao.getUserAdminByName(username);
    }

    public Integer updateUserAdmin(UserAdmin UserAdmin) {
        return UserAdminDao.updateUserAdmin(UserAdmin);
    }

	@Override
	public PageBean<UserAdmin> listUserAdminInfo(UserAdmin userAdmin, PageBean<UserAdmin> pageBean) {
		Map<String,Object> map = new HashMap<String,Object>();
		//设置查询条件
		map.put("userAdmin",userAdmin);
		//总记录放入pageBean
		pageBean.setTotal(UserAdminDao.getAdminListTotal(map));
		map.put("start",pageBean.getStart());
		map.put("end",pageBean.getEnd());
		//把分页结果放入pageBean
		pageBean.setResult(UserAdminDao.listUserAdminInfo(map));
		return pageBean;
	}

	@Override
	public Integer initPasswod(UserAdmin user) {
		 return UserAdminDao.initPasswod(user);
	}

	@Override
	public Integer isExitAdmin(UserAdmin user) {
		 return UserAdminDao.isExitAdmin(user);
	}

	@Override
	public Integer addAdmin(UserAdmin user) {
		 return UserAdminDao.addAdmin(user);
	}

	@Override
	public Integer forbidAdmin(UserAdmin user) {
		 return UserAdminDao.forbidAdmin(user);
	}

	@Override
	public PageBean<Menu> listMenuInfo(Menu menu, PageBean<Menu> pageBean) {
		Map<String,Object> map = new HashMap<String,Object>();
		//设置查询条件
		map.put("menu",menu);
		//总记录放入pageBean
		pageBean.setTotal(UserAdminDao.getMenuTotal(map));
		map.put("start",pageBean.getStart());
		map.put("end",pageBean.getEnd());
		//把分页结果放入pageBean
		pageBean.setResult(UserAdminDao.menu(map));
		return pageBean;
	}

	@Override
	public Integer addMenu(AdminPower adminPower) {
		 return UserAdminDao.addMenu(adminPower);
	}

	@Override
	public Integer isExitRecord(AdminPower adminPower) {
		 return UserAdminDao.isExitRecord(adminPower);
	}

	@Override
	public Integer delteRecord(AdminPower adminPower) {
		 return UserAdminDao.delteRecord(adminPower);
	}

	@Override
	public List<AdminPower>  getMenu(AdminPower adminPower) {
		return UserAdminDao.getMenu(adminPower);
	}

	
}
