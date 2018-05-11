package admin.entity.api;

import java.util.List;

public class GroupInfo {
	// api begin
	private Integer id;
    private String groupId;
    private String groupImage;
    private String groupNickName;
    private String createName;
    private Integer peopleNum;
    private Integer groupLevel;
    private Integer groupSize;
    private String createTime;
    private Integer status;
    private String description;
    private Integer isAdmin;
    private List<UserInfo> member;
    
    
	public List<UserInfo> getMember() {
		return member;
	}
	public void setMember(List<UserInfo> member) {
		this.member = member;
	}
	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	// admin begin
    private String groupStatus;
    private String groupLev;
    private Integer isVote;
    public Integer getIsVote() {
		return isVote;
	}
	public void setIsVote(Integer isVote) {
		this.isVote = isVote;
	}
	public String getTopGroupId() {
		return topGroupId;
	}
	public void setTopGroupId(String topGroupId) {
		this.topGroupId = topGroupId;
	}
	private String topGroupId;
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
	public String getGroupImage() {
		return groupImage;
	}
	public void setGroupImage(String groupImage) {
		this.groupImage = groupImage;
	}
	public String getGroupNickName() {
		return groupNickName;
	}
	public void setGroupNickName(String groupNickName) {
		this.groupNickName = groupNickName;
	}
	public String getCreateName() {
		return createName;
	}
	public void setCreateName(String createName) {
		this.createName = createName;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public Integer getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(Integer groupLevel) {
		this.groupLevel = groupLevel;
	}
	public Integer getGroupSize() {
		return groupSize;
	}
	public void setGroupSize(Integer groupSize) {
		this.groupSize = groupSize;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getGroupStatus() {
		return groupStatus;
	}
	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}
	public String getGroupLev() {
		return groupLev;
	}
	public void setGroupLev(String groupLev) {
		this.groupLev = groupLev;
	}
	
	
	
}
