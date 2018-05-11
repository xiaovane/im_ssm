package admin.entity.api;

public class UserInfo {
    //begin api
	private Integer id;
	private Integer useType;
	private String userId;
	private String mobile;
	private String nickName;
	public String pwd;
	private String provice;
	private String dcity;
	private String xcity;
	private String qcity;
	private String village;
	private String building;
	private String unit;
	private String room;
	private String device;
	private String userImage;
	private String status;
	private Integer thumb;
	private String regist;
	private String loginTime;
	private Integer isHidden;
	private String user;
	//新增好友关系字段
	private Integer blackStatus;
	private Integer isStar;
	private String bakName;
	
	public String getBakName() {
		return bakName;
	}
	public void setBakName(String bakName) {
		this.bakName = bakName;
	}
	//begin admin
	private String userType;
	private String userThumb;
	private String userStatus;

	public Integer getBlackStatus() {
		return blackStatus;
	}
	public void setBlackStatus(Integer blackStatus) {
		this.blackStatus = blackStatus;
	}
	public Integer getIsStar() {
		return isStar;
	}
	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUseType() {
		return useType;
	}
	public void setUseType(Integer useType) {
		this.useType = useType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getProvice() {
		return provice;
	}
	public void setProvice(String provice) {
		this.provice = provice;
	}
	public String getDcity() {
		return dcity;
	}
	public void setDcity(String dcity) {
		this.dcity = dcity;
	}
	public String getXcity() {
		return xcity;
	}
	public void setXcity(String xcity) {
		this.xcity = xcity;
	}
	public String getQcity() {
		return qcity;
	}
	public void setQcity(String qcity) {
		this.qcity = qcity;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getRoom() {
		return room;
	}
	public void setRoom(String room) {
		this.room = room;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getUserImage() {
		return userImage;
	}
	public void setUserImage(String userImage) {
		this.userImage = userImage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getThumb() {
		return thumb;
	}
	public void setThumb(Integer thumb) {
		this.thumb = thumb;
	}
	public String getRegist() {
		return regist;
	}
	public void setRegist(String regist) {
		this.regist = regist;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public Integer getIsHidden() {
		return isHidden;
	}
	public void setIsHidden(Integer isHidden) {
		this.isHidden = isHidden;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUserThumb() {
		return userThumb;
	}
	public void setUserThumb(String userThumb) {
		this.userThumb = userThumb;
	}
	public String getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
	
}
