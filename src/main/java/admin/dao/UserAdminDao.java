package admin.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import admin.entity.WebUser;
import admin.entity.api.AdminPower;
import admin.entity.api.Menu;
import admin.entity.UserAdmin;

/**
 * Created by xp on 2017/4/13. 博主dao接口
 */
@Repository
public interface UserAdminDao {

	/**
	 * 查询博主信息
	 * @param userAdmin 
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
	 * 初始化密码
	 * 
	 * @param user
	 * @return
	 */
	Integer initPasswod(UserAdmin user);

	/**
	 * 获取admin列表总数
	 * 
	 * @param map
	 * @return
	 */
	long getAdminListTotal(Map<String, Object> map);

	/**
	 * 获取admin 列表
	 * 
	 * @param map
	 * @return
	 */
	List<UserAdmin> listUserAdminInfo(Map<String, Object> map);

	/**
	 * admin是否存在
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
	 * 获取模块菜单列表
	 * 
	 * @param map
	 * @return
	 */
	List<Menu> menu(Map<String, Object> map);

	/**
	 * 批量给模块添加权限
	 * 
	 * @param adminPower
	 * @return
	 */
	Integer addMenu(AdminPower adminPower);

	/**
	 * 获取菜单列表的总数
	 * 
	 * @param map
	 * @return
	 */
	long getMenuTotal(Map<String, Object> map);

	/**
	 * 判断是否存在记录
	 * 
	 * @param adminPower
	 * @return
	 */
	Integer isExitRecord(AdminPower adminPower);

	/**
	 * 删除记录
	 * 
	 * @param adminPower
	 * @return
	 */
	Integer delteRecord(AdminPower adminPower);

	/**
	 * 获取当前用户的权限列表
	 * @param adminPower
	 * @return
	 */
	List<AdminPower> getMenu(AdminPower adminPower);

}
