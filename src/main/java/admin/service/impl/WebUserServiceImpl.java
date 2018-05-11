package admin.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.dao.WebUserDao;
import admin.entity.WebUser;
import admin.entity.PageBean;
import admin.service.WebUserService;

/**
 * @Description Service实现类
 * @author whai
 *
 */
@Service("WebUserService")
public class WebUserServiceImpl implements WebUserService {

	@Resource
	private WebUserDao WebUserDao;



	public PageBean<WebUser> listWebUser(String title, PageBean<WebUser> pageBean) {
		Map<String,Object> map = new HashMap<String,Object>();
		//设置查询条件
		map.put("title",title);
		//总记录放入pageBean
		pageBean.setTotal(WebUserDao.getTotal(map));
		map.put("start",pageBean.getStart());
		map.put("end",pageBean.getEnd());
		//把分页结果放入pageBean
		pageBean.setResult(WebUserDao.listWebUser(map));
		return pageBean;
	}


	public List<WebUser> listWebUser(Map<String,Object> map) {
		return WebUserDao.listWebUser(map);
	}

	public Integer getWebUserByTypeId(Integer typeId) {
		return WebUserDao.getWebUserByTypeId(typeId);
	}

	public Integer saveWebUser(WebUser WebUser) {
		return WebUserDao.saveWebUser(WebUser);
	}

	public Integer updateWebUser(WebUser WebUser) {
		return WebUserDao.updateWebUser(WebUser);
	}

	public Integer deleteWebUser(Integer id) {
		return WebUserDao.deleteWebUser(id);
	}

	public WebUser getById(Integer id) {
		return WebUserDao.getById(id);
	}

	public long getTotal(Map<String, Object> map) {
		return WebUserDao.getTotal(map);
	}

	public WebUser getPrevWebUser(Integer id) {
		return WebUserDao.getPrevWebUser(id);
	}


	public WebUser getNextWebUser(Integer id) {
		return WebUserDao.getNextWebUser(id);
	}

}
