package  api.infocapture.service;

import java.util.List;

import admin.entity.PageBean;
import admin.entity.api.CaptureInfo;
import admin.entity.api.FeedBack;
import admin.entity.api.NewsInfo;

public interface GetCaptureInfoService {

	// admin begin
	/**
	 * 获取抓取的配置内容
	 * @param captureInfo
	 * @return
	 */
	public List<CaptureInfo> getInfo(CaptureInfo captureInfo);

	/**
	 * 获取抓取配置列表
	 * @param captureInfo
	 * @param pageBean
	 * @return
	 */
	public PageBean<CaptureInfo> captureInfoList(CaptureInfo captureInfo, PageBean<CaptureInfo> pageBean);

	/**
	 * 获取抓取信息配置内容
	 * @param captureInfo
	 * @return
	 */
	public CaptureInfo captureInfoContent(CaptureInfo captureInfo);

	/**
	 * 修改配置
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoModify(CaptureInfo captureInfo);

	/**
	 * 新增抓取配置
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoAdd(CaptureInfo captureInfo);

	/**
	 * 批量删除抓取配置
	 * @param captureInfo
	 * @return
	 */
	public Integer captureInfoDelete(CaptureInfo captureInfo);

	
}
