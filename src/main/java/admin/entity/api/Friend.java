package admin.entity.api;

public class Friend {
	private Integer id;
	private String userId;
	private String friendId;
	private Integer blackStatus;
	private Integer isStar;
	private String bakName;

	public String getBakName() {
		return bakName;
	}

	public void setBakName(String bakName) {
		this.bakName = bakName;
	}

	public Integer getIsStar() {
		return isStar;
	}

	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}

	public Integer getBlackStatus() {
		return blackStatus;
	}

	public void setBlackStatus(Integer blackStatus) {
		this.blackStatus = blackStatus;
	}

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

}
