package api.infocapture.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.CaptureInfo;
import admin.entity.api.FeedBack;

public interface GetCaptureInfoDao {

	/**
	 * 获取数据库里面的配置信息
	 * 
	 * @param captureInfo
	 * @return
	 */
	public List<CaptureInfo> selectConf(CaptureInfo captureInfo);

	/**
	 * 获取分页总数量
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 获取抓取配置列表
	 * 
	 * @param map
	 * @return
	 */
	public List<CaptureInfo> captureInfoList(Map<String, Object> map);

	/**
	 * 获取抓取配置详细内容
	 * 
	 * @param captureInfo
	 * @return
	 */
	public CaptureInfo captureInfoContent(CaptureInfo captureInfo);

	/**
	 * 抓取信息配置修改
	 * 
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoModify(CaptureInfo captureInfo);

	/**
	 * 新增抓取信息配置
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoAdd(CaptureInfo captureInfo);

	/**
	 * 批量删除抓取信息配置
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoDelete(CaptureInfo captureInfo);
}
