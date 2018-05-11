package admin.dao;

import java.util.List;
import java.util.Map;

import admin.entity.WebUser;

/**
 * @Description Dao接口
 * @author whai
 *
 */
public interface WebUserDao {


	// 分页查询
	public List<WebUser> listWebUser(Map<String, Object> map);

	// 获取总记录数
	public Long getTotal(Map<String ,Object> map);

	// 根据类型的id查询该类型下数量
	public Integer getWebUserByTypeId(Integer typeId);

	//添加
	public Integer saveWebUser(WebUser WebUser);

	//更新
	public Integer updateWebUser(WebUser WebUser);

	public Integer deleteWebUser(Integer id);

	//通过id获取
	public WebUser getById(Integer id);

	/**
	 * 获取上一篇
	 * @param id
	 * @return
	 */
	WebUser getPrevWebUser(Integer id);
	/**
	 * 获取下一篇
	 * @param id
	 * @return
	 */
	WebUser getNextWebUser(Integer id);
}
