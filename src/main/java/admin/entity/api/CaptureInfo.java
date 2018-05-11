package admin.entity.api;

import java.sql.Date;

public class CaptureInfo {	
	private String title;
	private String content;
	private Integer id;
    private  String urlList;
    private String urlName;
    private String urlCode;
    private String fromUrl;
    private String categoryNum;
    private Integer status;
    private String date;
	private String urlDetail;
	private String infoId;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUrlList() {
		return urlList;
	}
	public void setUrlList(String urlList) {
		this.urlList = urlList;
	}
	public String getUrlName() {
		return urlName;
	}
	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}
	public String getUrlCode() {
		return urlCode;
	}
	public void setUrlCode(String urlCode) {
		this.urlCode = urlCode;
	}
	public String getfromUrl() {
		return fromUrl;
	}
	public void setfromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}
	public String getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getUrlDetail() {
		return urlDetail;
	}
	public void setUrlDetail(String urlDetail) {
		this.urlDetail = urlDetail;
	}
	public String getInfoId() {
		return infoId;
	}
	public void setInfoId(String infoId) {
		this.infoId = infoId;
	}
	
	
}
