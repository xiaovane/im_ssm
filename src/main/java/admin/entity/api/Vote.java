package admin.entity.api;

import java.sql.Date;

public class Vote {

	// api begin
	private Integer id;
	private String groupId;
	private String createId;
	private String title;
	private String startTime;
	private String endTime;
	private Integer status;
	private String voteId;
	private String createTime;
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	// admin begin
	private String voteStatus;
	private String groupNickName;
	private String nickName;
	private Integer voteType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getVoteId() {
		return voteId;
	}

	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getVoteStatus() {
		return voteStatus;
	}

	public void setVoteStatus(String voteStatus) {
		this.voteStatus = voteStatus;
	}

	public String getGroupNickName() {
		return groupNickName;
	}

	public void setGroupNickName(String groupNickName) {
		this.groupNickName = groupNickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getVoteType() {
		return voteType;
	}

	public void setVoteType(Integer voteType) {
		this.voteType = voteType;
	}

}
