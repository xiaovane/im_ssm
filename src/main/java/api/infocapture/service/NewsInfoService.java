package api.infocapture.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import admin.entity.PageBean;
import admin.entity.api.NewsInfo;

public interface NewsInfoService {
	// api begin
	/**
	 * 获取列表信息
	 * 
	 * @param newsInfo
	 * @return
	 */
	public List<NewsInfo> getNewsInfoList(NewsInfo newsInfo);

	/**
	 * 获取详细页面信息
	 * 
	 * @param newsInfo
	 * @return
	 */
	public NewsInfo getNewsInfoDetail(NewsInfo newsInfo);

	/**
	 * 插入抓取的信息
	 * 
	 * @param newsInfo
	 * @return
	 */
	public Integer addNewsInfo(NewsInfo newsInfo);

	/**
	 * 获取新闻信息列表
	 * 
	 * @param pageIndex
	 * @param pageSize
	 * @param categoryNum
	 * @return
	 */
	public List<NewsInfo> selectInfo(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize,
			@Param("categoryNum") Integer categoryNum);

	/**
	 * 获取新闻详细页，供前台h5展示
	 * 
	 * @param detail
	 * @return
	 */
	public NewsInfo newsDetail(NewsInfo detail);

	// admin begin
	/**
	 * 获取新闻列表
	 * 
	 * @param map
	 * @param pageBean
	 * @return
	 */
	public PageBean<NewsInfo> newsInfoList(Map<String, Object> map, PageBean<NewsInfo> pageBean);

	/**
	 * 获取新闻详细
	 * 
	 * @param newsInfo
	 * @return
	 */
	public NewsInfo newsInfoContent(NewsInfo newsInfo);

	/**
	 * 修改新闻
	 * 
	 * @param newsInfo
	 * @return
	 */
	public Integer newsInfoModify(NewsInfo newsInfo);

	/**
	 * 插入数据到详细页面
	 * 
	 * @param newsInfo
	 * @return
	 */
	public Integer addNewsDetail(NewsInfo newsInfo);

	/**
	 * 抓取的时候判断标题是否存在
	 * @param title
	 * @return
	 */
	public Integer isTitle(String title);

	/**
	 * 新增新闻
	 * @param newsInfo
	 * @return
	 */
	public Integer newsInfoAdd(NewsInfo newsInfo);

	/**
	 * 批量删除新闻
	 * @param newsInfo
	 * @return
	 */
	public Integer newsInfoDelete(NewsInfo newsInfo);

	/**
	 * 判断新闻标题是否存在
	 * @param newsInfo
	 * @return
	 */
	public Integer isExitTitle(NewsInfo newsInfo);

}
