package admin.listener;

import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import admin.util.TaskUtil;

@Component
public class TaskManager implements ServletContextListener, ApplicationContextAware{
	private static ApplicationContext applicationContext;
	
	/**
	 * 定时器
	 */
	private Timer timer;

	/**
	 * 在Web应用启动时初始化任务
	 */
	public void contextInitialized(ServletContextEvent event) {
		TaskUtil test = new TaskUtil();
		//test.timerOne();
		//test.timerTwo();
		
	}

	/**
	 * 在Web应用结束时停止任务
	 */
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel(); // 定时器销毁
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		TaskManager.applicationContext = applicationContext;
	}

}
