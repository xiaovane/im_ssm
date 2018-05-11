package api.infocapture.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import admin.entity.api.CaptureInfo;

import admin.entity.api.NewsInfo;

public interface NewsInfoDao {
	// api begin
	/**
	 * 获取信息列表信息
	 * 
	 * @param newsInfo
	 * @return
	 */
	public List<NewsInfo> getNewsInfoList(NewsInfo newsInfo);

	/**
	 * 获取详细信息
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
	 * 获取新闻详细，供前台展示
	 * 
	 * @param detail
	 * @return
	 */
	public NewsInfo newsDetail(NewsInfo detail);

	// admini begin
	/**
	 * 获取总数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 获取新闻列表
	 * 
	 * @param map
	 * @return
	 */
	public List<NewsInfo> newsInfoList(Map<String, Object> map);

	/**
	 * 获取新闻内容
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
	 * 抓取信息内容插入到数据库
	 * 
	 * @param newsInfo
	 * @return
	 */
	public Integer addNewsDetail(NewsInfo newsInfo);

	/**
	 * 判断标题是否存在，存在就不插入数据库
	 * 
	 * @param title
	 * @return
	 */
	public Integer isTitle(String title);

	
	/**
	 * 批量删除新闻信息
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
