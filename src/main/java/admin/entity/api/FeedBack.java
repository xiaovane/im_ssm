package admin.entity.api;

import java.sql.Date;

public class FeedBack {
	
	private Integer id;
    private  String userId;
    private String friendId;
    private String content;
    private String feedBackTime;
    private String replyContent;
    private Integer lookStatus;
	private String infoId;
	private Integer type;
	//关联后台字段显示
	private String feedBackType;
	private String feedBackStatus;
	private String nickName;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFriendId() {
		return friendId;
	}
	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFeedBackTime() {
		return feedBackTime;
	}
	public void setFeedBackTime(String feedBackTime) {
		this.feedBackTime = feedBackTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getLookStatus() {
		return lookStatus;
	}
	public void setLookStatus(Integer lookStatus) {
		this.lookStatus = lookStatus;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getFeedBackType() {
		return feedBackType;
	}
	public void setFeedBackType(String feedBackType) {
		this.feedBackType = feedBackType;
	}
	public String getFeedBackStatus() {
		return feedBackStatus;
	}
	public void setFeedBackStatus(String feedBackStatus) {
		this.feedBackStatus = feedBackStatus;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
}
	