package admin.controller.admin.api;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.PageBean;
import admin.entity.UserAdmin;
import admin.entity.api.AdminPower;
import admin.entity.api.Menu;
import admin.service.UserAdminService;
import admin.util.MD5Util;
import admin.util.ResultInfoUtil;
import api.user.controller.UserOperationApiController;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/userAdmin")
public class UserAdminManageController {

	@Resource
	private UserAdminService adminApiService;
	private static final Logger logger = LoggerFactory.getLogger(UserOperationApiController.class);

	/**
	 * 获取管理员列表
	 * 
	 * @param page
	 * @param rows
	 * @param userAdmin
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userAdminList")
	public String userAdminList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, UserAdmin userAdmin,
			HttpServletResponse response) throws Exception {

		PageBean<UserAdmin> pageBean = new PageBean<UserAdmin>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = adminApiService.listUserAdminInfo(userAdmin, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 重置管理员密码
	 * 
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/userInitPwd")
	public void userInitPwd(HttpServletResponse response,HttpServletRequest request, UserAdmin user) throws Exception {

		try {
			user.setUserName(request.getParameter("userId"));
			user.setPassword(request.getParameter("status"));
			//密码初始化
			user.setPassword(MD5Util.md5(user.getPassword(), "xp"));
			Integer result = adminApiService.initPasswod(user);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 新增admin
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/addAdmin")
	public void addAdmin(HttpServletResponse response, UserAdmin user) throws Exception {

		try {
			// 调用成功，则开启和禁用用户
			Integer result = adminApiService.isExitAdmin(user);
			if (!result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "isExit");
				return;
			}
			
			Integer resultAdmin = adminApiService.addAdmin(user);
			if (!resultAdmin.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 启用和禁用admin
	 * @param response
	 * @param user
	 * @throws Exception
	 */
	@RequestMapping(value = "/userDeac")
	public void forbidAdmin(HttpServletResponse response,HttpServletRequest request, UserAdmin user) throws Exception {

		try {
			user.setUserName(request.getParameter("userId"));
			user.setIsAble( Integer.parseInt(request.getParameter("status")));
			// 调用成功，则开启和禁用用户
			Integer result = adminApiService.forbidAdmin(user);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	//模块授权
	/**
	 * 获取模块列表
	 */
	@RequestMapping("/memuList")
	public String memuList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Menu menu,
			HttpServletResponse response) throws Exception {

		PageBean<Menu> pageBean = new PageBean<Menu>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = adminApiService.listMenuInfo(menu, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}
	
	/**
	 * 批量模块授权
	 * @param response
	 * @param adminPower
	 * @throws Exception
	 */
	@RequestMapping(value = "/addMenu")
	public void addMenu(HttpServletResponse response, HttpServletRequest request, AdminPower adminPower) throws Exception {

		try {
			//先判断是否存在授权记录，如果存在，就先删除授权
			Integer isExitRecord=adminApiService.isExitRecord(adminPower);
			if (isExitRecord>0) {
				Integer delteRecord=adminApiService.delteRecord(adminPower);
				if (delteRecord.equals(0)) {
					ResultInfoUtil.resultSuccess(response, "false");
					return;
				}
			}
			//插入授权记录
			String msg=request.getParameter("menu");
			String []menu=msg.split(",");
			 for (int i = 0; i < menu.length; i++) {			
				adminPower.setMenu(menu[i]);
				Integer result = adminApiService.addMenu(adminPower);
				if (result.equals(0)) {

					ResultInfoUtil.resultSuccess(response, "false");
					return;
				}
			}
		
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/getMenu")
	public void getMenu(HttpServletResponse response, HttpServletRequest request, AdminPower adminPower) throws Exception {

		try {
			//获取当前用户的权限列表
			List<AdminPower> menuList=adminApiService.getMenu(adminPower);
			if (menuList.equals(null)) {
				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.jsonObject(response, menuList);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
