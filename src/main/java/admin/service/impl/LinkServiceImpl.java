package admin.service.impl;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import admin.dao.LinkDao;
import admin.entity.Link;
import admin.entity.PageBean;
import admin.service.LinkService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by xp on 2017/4/20.
 */
@Service
public class LinkServiceImpl implements LinkService{

    @Resource
    private LinkDao linkDao;

    public List<Link> getTotalData() {
        return linkDao.getTotalData();
    }

    public PageBean<Link> listByPage(PageBean<Link> pageBean) {
        pageBean.setResult(linkDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        pageBean.setTotal(linkDao.getTotalCount());
        return pageBean;
    }

    public Long getTotalCount() {
        return linkDao.getTotalCount();
    }

    public Integer addLink(Link link) {
        return linkDao.addLink(link);
    }

    public Integer deleteLink(Integer id) {
        return linkDao.deleteLink(id);
    }

    public Integer updateLink(Link link) {
        return linkDao.updateLink(link);
    }
}
