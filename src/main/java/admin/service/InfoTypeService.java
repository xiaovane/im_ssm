package admin.service;

import org.springframework.stereotype.Service;
import admin.entity.InfoType;
import admin.entity.PageBean;

import java.util.List;

/**
 * Created by xp on 2017/4/14.
 * @author whai
 * @Description 类别service接口
 */
public interface InfoTypeService {

    //分页查询
    PageBean<InfoType> listByPage(PageBean<InfoType> pageBean);

    // 获取总记录数
    public Long getTotal();

    // 添加类别
    public Integer addInfoType(InfoType InfoType);

    // 更新类别
    public Integer updateInfoType(InfoType InfoType);

    // 删除类别
    public Integer deleteInfoType(Integer id);

    public List<InfoType> getInfoTypeData();

}
