package admin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.UserAdmin;
import admin.entity.api.AdminPower;
import admin.entity.api.Menu;

/**
 * Created by xp on 2017/4/13.
 */

public interface UserAdminService {
	/**
	 * 查询博主信息
	 * 
	 * @return
	 */
	UserAdmin getUserAdminData(UserAdmin userAdmin);

	/**
	 * 通过用户名查询博主信息
	 * 
	 * @param username
	 * @return
	 */
	UserAdmin getUserAdminByName(String username);

	/**
	 * 更新博主信息
	 * 
	 * @param UserAdmin
	 * @return
	 */
	Integer updateUserAdmin(UserAdmin UserAdmin);

	/**
	 * 获取管理员的列表
	 * 
	 * @param userAdmin
	 * @param pageBean
	 * @return
	 */
	PageBean<UserAdmin> listUserAdminInfo(UserAdmin userAdmin, PageBean<UserAdmin> pageBean);

	/**
	 * 重置密码
	 * 
	 * @param user
	 * @return
	 */
	Integer initPasswod(UserAdmin user);

	/**
	 * 判断是否存在admin
	 * 
	 * @param user
	 * @return
	 */
	Integer isExitAdmin(UserAdmin user);

	/**
	 * 新增admin
	 * 
	 * @param user
	 * @return
	 */
	Integer addAdmin(UserAdmin user);

	/**
	 * 禁用admin
	 * 
	 * @param user
	 * @return
	 */
	Integer forbidAdmin(UserAdmin user);

	/**
	 * 菜单列表
	 * @param menu
	 * @param pageBean
	 * @return
	 */
	PageBean<Menu> listMenuInfo(Menu menu, PageBean<Menu> pageBean);

	/**
	 * 模块授权
	 * @param adminPower
	 * @return
	 */
	Integer addMenu(AdminPower adminPower);

	/**
	 * 判断是否存在记录
	 * @param adminPower
	 * @return
	 */
	Integer isExitRecord(AdminPower adminPower);

	/**
	 * 删除授权记录
	 * @param adminPower
	 * @return
	 */
	Integer delteRecord(AdminPower adminPower);

	/**
	 * 获取权限列表
	 * @param adminPower
	 * @return
	 */
	List<AdminPower> getMenu(AdminPower adminPower);
}
