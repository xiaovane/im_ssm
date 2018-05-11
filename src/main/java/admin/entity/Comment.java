package admin.entity;

import java.util.Date;

/**
 * @author whai
 * @Description 评论实体类
 * @Date 2017/4/19 23:10
 */
public class Comment {

    private Integer id;         //id
    private String userIp;      //评论者ip
    private String content;     //评论内容
    private Date commentDate;   //评论日期
    private Integer state;      //审核状态  0待审核 1通过 2未通过
    private WebUser WebUser;          //所评论


    public Comment() {
    }

    public Comment(Integer id, String userIp, String content, Date commentDate, Integer state, WebUser WebUser) {
        this.id = id;
        this.userIp = userIp;
        this.content = content;
        this.commentDate = commentDate;
        this.state = state;
        this.WebUser = WebUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public WebUser getWebUser() {
        return WebUser;
    }

    public void setWebUser(WebUser WebUser) {
        this.WebUser = WebUser;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", userIp='" + userIp + '\'' +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", state=" + state +
                ", WebUser=" + WebUser +
                '}';
    }
}
