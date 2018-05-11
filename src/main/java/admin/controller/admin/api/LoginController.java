package admin.controller.admin.api;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import admin.entity.UserAdmin;
import admin.service.UserAdminService;
import admin.util.MD5Util;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author whai
 * @Description 博主控制层前台 不需要shiro认证
 * @Date 2017/4/20 17:25
 */
@Controller
@RequestMapping("/LoginInfo")
public class LoginController {

	@Resource
	private UserAdminService UserAdminService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(UserAdmin UserAdmin, HttpServletRequest request) {

		// 获取登录实体
		Subject subject = SecurityUtils.getSubject();
		// 获取加密后密码
		String password = MD5Util.md5(UserAdmin.getPassword(), "xp");

		// 获取用户密码登录token
		UsernamePasswordToken token = new UsernamePasswordToken(UserAdmin.getUserName(), password);
		try {
			// 根据token登录 会调用MyRealm中的doGetAuthenticationInfo方法进行身份认证
			subject.login(token);
			request.setAttribute("WebUserer", UserAdmin);
			return "redirect:/admin/api/manage.jsp";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			// 提示信息
			request.setAttribute("errorInfo", "用户名或密码错误");
			return "login";

		}
	}

	// 注释：暂时不需要-2017-12-22
	// @RequestMapping("/aboutme")
	// public ModelAndView abouotMe() {
	// ModelAndView modelAndView = new ModelAndView();
	// UserAdmin UserAdmin = UserAdminService.getUserAdminData();
	// modelAndView.addObject("UserAdmin", UserAdmin);
	// modelAndView.addObject("commonPage",
	// "foreground/UserAdmin/UserAdminInfo.jsp");
	// modelAndView.addObject("title", "关于博主 - ");
	// modelAndView.setViewName("mainTemp");
	// return modelAndView;
	// }
	//
	// @RequestMapping(value = "/myalbum")
	// public ModelAndView myAlbum() {
	// ModelAndView modelAndView = new ModelAndView();
	// //要写一个相册的service获取相册
	// //要建一个相册表
	// //....
	// modelAndView.addObject("commonPage", "foreground/UserAdmin/myAlbum.jsp");
	// modelAndView.setViewName("mainTemp");
	// return modelAndView;
	// }
	//
	// @RequestMapping("/resource")
	// public ModelAndView resource() {
	// ModelAndView modelAndView = new ModelAndView();
	// //
	// //....
	// modelAndView.addObject("commonPage",
	// "foreground/UserAdmin/resource.jsp");
	// modelAndView.setViewName("mainTemp");
	// return modelAndView;
	// }
	//

}
