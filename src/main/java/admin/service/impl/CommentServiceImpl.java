package admin.service.impl;

import org.springframework.stereotype.Service;
import admin.dao.CommentDao;
import admin.entity.Comment;
import admin.entity.PageBean;
import admin.service.CommentService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by xp on 2017/4/20.
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Resource
    private CommentDao commentDao;


    public PageBean<Comment> listByPage(PageBean<Comment> pageBean) {
        pageBean.getMap().put("start",pageBean.getStart());
        pageBean.getMap().put("end",pageBean.getEnd());
        pageBean.setResult(commentDao.listByPage(pageBean.getMap()));
        pageBean.setTotal(commentDao.getTotal(pageBean.getMap()));
        return pageBean;
    }


    public Long getTotal(Map<String, Object> map) {
        return commentDao.getTotal(map);
    }


    public Comment getById(Integer id) {
        return commentDao.getById(id);
    }


    public Integer saveComment(Comment comment) {
        return commentDao.saveComment(comment);
    }


    public Integer deleteComment(Integer id) {
        return commentDao.deleteComment(id);
    }


    public Integer updateComment(Comment comment) {
        return commentDao.updateComment(comment);
    }


    public Long deleteCommentByWebUserId(Integer WebUserId) {
        return commentDao.deleteCommentByWebUserId(WebUserId);
    }

    public List<Comment> getCommentData(Map<String, Object> map) {
        return commentDao.listByPage(map);
    }
}
