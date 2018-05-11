package  api.infocapture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.CaptureInfo;
import admin.entity.api.FeedBack;
import api.infocapture.dao.GetCaptureInfoDao;

import api.infocapture.service.GetCaptureInfoService;

@Service("getCaptureInfoService")
public class GetCaptureInfoImpl implements GetCaptureInfoService{
	@Resource
	private GetCaptureInfoDao getCaptureInfoDao;

	public List<CaptureInfo>  getInfo(CaptureInfo captureInfo) {
		return getCaptureInfoDao.selectConf(captureInfo);
	}

	@Override
	public PageBean<CaptureInfo> captureInfoList(CaptureInfo captureInfo, PageBean<CaptureInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("captureInfo", captureInfo);
		// 总记录放入pageBean
		pageBean.setTotal(getCaptureInfoDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(getCaptureInfoDao.captureInfoList(map));
		return pageBean;
	}

	@Override
	public CaptureInfo captureInfoContent(CaptureInfo captureInfo) {
		return getCaptureInfoDao.captureInfoContent(captureInfo);
	}

	@Override
	public Integer captureInfoModify(CaptureInfo captureInfo) {
		return getCaptureInfoDao.captureInfoModify(captureInfo);
	}

	@Override
	public Integer captureInfoAdd(CaptureInfo captureInfo) {
		return getCaptureInfoDao.captureInfoAdd(captureInfo);
	}

	@Override
	public Integer captureInfoDelete(CaptureInfo captureInfo) {
		return getCaptureInfoDao.captureInfoDelete(captureInfo);
	}
 
	
}
