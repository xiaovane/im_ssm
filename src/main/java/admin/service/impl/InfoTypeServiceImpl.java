package admin.service.impl;

import org.springframework.stereotype.Service;
import admin.dao.InfoTypeDao;
import admin.entity.InfoType;
import admin.entity.PageBean;
import admin.service.InfoTypeService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xp on 2017/4/14.
 * @author whai
 * @Description 类别service接口实现类
 */
@Service
public class InfoTypeServiceImpl implements InfoTypeService{

    @Resource
    private InfoTypeDao InfoTypeDao;

    public PageBean<InfoType> listByPage(PageBean<InfoType> pageBean) {
        //查询分页结果
        pageBean.setResult(InfoTypeDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(InfoTypeDao.getTotal());
        return pageBean;
    }

    public Long getTotal() {
        return InfoTypeDao.getTotal();
    }

    public Integer addInfoType(InfoType InfoType) {
        return InfoTypeDao.addInfoType(InfoType);
    }

    public Integer updateInfoType(InfoType InfoType) {
        return InfoTypeDao.updateInfoType(InfoType);
    }

    public Integer deleteInfoType(Integer id) {
        return InfoTypeDao.deleteInfoType(id);
    }

    public List<InfoType> getInfoTypeData(){return  InfoTypeDao.getInfoTypeData();}

}
