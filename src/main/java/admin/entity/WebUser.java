package admin.entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description 实体
 * @author whai
 *
 */
public class WebUser {

	private Integer id;
	private String title;
	private String summary;
	private Date releaseDate;
	private Integer clickHit;
	private Integer replyHit;
	private String content;
	private String contentNoTag; //不带标签内容，用于Lucene索引中
	private String keyWord; //关键字，用空格隔开
	
	private InfoType InfoType; //类型
	private Integer WebUserCount; //数量，非实际属性，用于根据发布日期归档查询
	private String releaseDateStr; //发布日期的字符串，只取年月
	
	private List<String> imageList = new LinkedList<String>();//里存的图片，主要用于展示缩略图

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Integer getClickHit() {
		return clickHit;
	}

	public void setClickHit(Integer clickHit) {
		this.clickHit = clickHit;
	}

	public Integer getReplyHit() {
		return replyHit;
	}

	public void setReplyHit(Integer replyHit) {
		this.replyHit = replyHit;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public InfoType getInfoType() {
		return InfoType;
	}

	public void setInfoType(InfoType InfoType) {
		this.InfoType = InfoType;
	}

	public Integer getWebUserCount() {
		return WebUserCount;
	}

	public void setWebUserCount(Integer WebUserCount) {
		this.WebUserCount = WebUserCount;
	}

	public String getReleaseDateStr() {
		return releaseDateStr;
	}

	public void setReleaseDateStr(String releaseDateStr) {
		this.releaseDateStr = releaseDateStr;
	}

	public List<String> getImageList() {
		return imageList;
	}

	public void setImageList(List<String> imageList) {
		this.imageList = imageList;
	}

	public String getContentNoTag() {
		return contentNoTag;
	}

	public void setContentNoTag(String contentNoTag) {
		this.contentNoTag = contentNoTag;
	}
	
}
