package admin.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import admin.dao.WebUserDao;
import admin.entity.InfoType;
import admin.entity.UserAdmin;
import admin.entity.Link;
import admin.service.WebUserService;
import admin.service.InfoTypeService;
import admin.service.UserAdminService;
import admin.service.LinkService;

@Component
/**
 * @author whai
 * @Description 监听程序初始化
 * @Date 2017/4/23 21:48
 */
public class InitUserAdminData implements ServletContextListener, ApplicationContextAware {

	private static ApplicationContext applicationContext;

	public void contextInitialized(ServletContextEvent sce) {
		// 暂时注释掉，不需要
		// //先获取servlet上下文
		// ServletContext application = sce.getServletContext();
		// //同上，获取类别信息service
		// InfoTypeService InfoTypeService =
		// applicationContext.getBean(InfoTypeService.class);
		// //获取博主信息service
		// UserAdminService UserAdminService =
		// applicationContext.getBean(UserAdminService.class);
		// //获取友情链接service
		// LinkService linkService =
		// applicationContext.getBean(LinkService.class);
		// //获取service
		// WebUserService WebUserService =
		// applicationContext.getBean(WebUserService.class);
		// //获取信息
		// List<InfoType> InfoTypeList = InfoTypeService.getInfoTypeData();
		// application.setAttribute("InfoTypeList", InfoTypeList);
		// //获取博主信息
		// UserAdmin UserAdmin = UserAdminService.getUserAdminData();
		// //隐藏密码
		// //UserAdmin.setPassword(null);
		// application.setAttribute("UserAdmin",UserAdmin);
		// //获取友情链接信息
		// List<Link> linkList = linkService.getTotalData();
		// application.setAttribute("linkList",linkList);
		// application.setAttribute("WebUserList",WebUserService);

	}

	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		InitUserAdminData.applicationContext = applicationContext;
	}

}
