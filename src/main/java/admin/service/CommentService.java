package admin.service;

import admin.entity.Comment;
import admin.entity.PageBean;

import java.util.List;
import java.util.Map;

/**
 * @author whai
 * @Description 评论service接口
 * @Date 2017/4/20 08:17
 */
public interface CommentService {
    /**
     * 分页查询评论信息
     * @param pageBean
     * @return
     */
    public PageBean<Comment> listByPage(PageBean<Comment> pageBean);

    /**
     * 获取总记录数目
     * @param map
     * @return
     */
    public Long getTotal(Map<String ,Object> map);

    /**
     * 根据id查询评论信息
     * @param id
     * @return
     */
    public Comment getById(Integer id);

    /**
     * 添加评论信息
     * @param comment
     * @return
     */
    public Integer saveComment(Comment comment);

    /**
     * 根据id删除评论
     * @param id
     * @return
     */
    public Integer deleteComment(Integer id);

    /**
     * 更改评论审核状态
     * @param comment
     * @return
     */
    public Integer updateComment(Comment comment);

    /**
     * 删除评论信息通过id
     * @param WebUserId
     * @return
     */
    public Long deleteCommentByWebUserId(Integer WebUserId);

    /**
     * 查询所有评论消息
     * @param map
     * @return
     */
    public List<Comment> getCommentData(Map<String,Object> map);
}
