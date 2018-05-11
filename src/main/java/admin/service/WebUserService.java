package admin.service;

import java.util.List;
import java.util.Map;

import admin.entity.WebUser;
import admin.entity.PageBean;

/**
 * @author whai
 * @Description Service接口
 */
public interface WebUserService {


    // 分页查询
    public List<WebUser> listWebUser(Map<String,Object> map);

    // 分页查询
    public PageBean<WebUser> listWebUser(String title,PageBean<WebUser> pageBean);

    // 根据类型的id查询该类型下数量
    public Integer getWebUserByTypeId(Integer typeId);

    //添加
    public Integer saveWebUser(WebUser WebUser);

    //更新
    public Integer updateWebUser(WebUser WebUser);

    //通过id删除
    public Integer deleteWebUser(Integer id);

    //通过id获取
    public WebUser getById(Integer id);

    long getTotal(Map<String, Object> map);

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
