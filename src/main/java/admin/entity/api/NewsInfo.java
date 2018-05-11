package admin.entity.api;

public class NewsInfo {

	private Integer id;
	private String infoGuid;
	private String title;
	private String url;	
	private String content;
	private String infoDate;
	private String author;
	private String zhuanZai;
	private String categoryNum;
	private String categoryName;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInfoGuid() {
		return infoGuid;
	}
	public void setInfoGuid(String infoGuid) {
		this.infoGuid = infoGuid;
	}
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
	public String getInfoDate() {
		return infoDate;
	}
	public void setInfoDate(String infoDate) {
		this.infoDate = infoDate;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getZhuanZai() {
		return zhuanZai;
	}
	public void setZhuanZai(String zhuanZai) {
		this.zhuanZai = zhuanZai;
	}
	public String getCategoryNum() {
		return categoryNum;
	}
	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	

}
