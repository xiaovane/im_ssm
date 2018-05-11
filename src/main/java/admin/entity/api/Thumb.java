package admin.entity.api;

public class Thumb {
	private String userId;
	private String friendId;
	private Integer thumb;
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
	public Integer getThumb() {
		return thumb;
	}
	public void setThumb(Integer thumb) {
		this.thumb = thumb;
	}

}
