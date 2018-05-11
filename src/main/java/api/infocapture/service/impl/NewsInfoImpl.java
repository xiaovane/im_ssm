package api.infocapture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.NewsInfo;
import api.infocapture.dao.NewsInfoDao;
import api.infocapture.service.NewsInfoService;

@Service("newsInfoService")
public class NewsInfoImpl implements NewsInfoService {

	@Resource
	private NewsInfoDao newsInfoDao;

	/**
	 * 获取列表信息
	 */
	public List<NewsInfo> getNewsInfoList(NewsInfo newsInfo) {
		return newsInfoDao.getNewsInfoList(newsInfo);
	}

	/**
	 * 获取详细信息
	 */
	public NewsInfo getNewsInfoDetail(NewsInfo newsInfo) {
		return newsInfoDao.getNewsInfoDetail(newsInfo);
	}

	/**
	 * 添加信息
	 */
	public Integer addNewsInfo(NewsInfo newsInfo) {
		return newsInfoDao.addNewsInfo(newsInfo);
	}

	/**
	 * 获取新闻信息
	 */
	public List<NewsInfo> selectInfo(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize,
			@Param("categoryNum") Integer categoryNum) {
		return newsInfoDao.selectInfo(pageIndex, pageSize, categoryNum);
	}

	@Override
	public NewsInfo newsInfoContent(NewsInfo newsInfo) {
		return newsInfoDao.newsInfoContent(newsInfo);
	}

	@Override
	public Integer newsInfoModify(NewsInfo newsInfo) {
		return newsInfoDao.newsInfoModify(newsInfo);
	}

	@Override
	public PageBean<NewsInfo> newsInfoList(Map<String, Object> map, PageBean<NewsInfo> pageBean) {
		Map<String, Object> mapNew = new HashMap<String, Object>();
		// 设置查询条件
		mapNew.put("newsInfo", map);

		// 总记录放入pageBean
		pageBean.setTotal(newsInfoDao.getTotal(mapNew));
		mapNew.put("start", pageBean.getStart());
		mapNew.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(newsInfoDao.newsInfoList(mapNew));
		return pageBean;
	}

	@Override
	public NewsInfo newsDetail(NewsInfo detail) {
		return newsInfoDao.newsDetail(detail);
	}

	@Override
	public Integer addNewsDetail(NewsInfo newsInfo) {
		return newsInfoDao.addNewsDetail(newsInfo);
	}

	@Override
	public Integer isTitle(String title) {
		return newsInfoDao.isTitle(title);
	}

	@Override
	public Integer newsInfoAdd(NewsInfo newsInfo) {
		// 同时插入
		Integer isTrue = 0;
		Integer result = newsInfoDao.addNewsInfo(newsInfo);
		if (!result.equals(0)) {
			Integer result2 = newsInfoDao.addNewsDetail(newsInfo);
			if (!result2.equals(0)) {
				isTrue = 1;
			}
		}
		return isTrue;
	}

	@Override
	public Integer newsInfoDelete(NewsInfo newsInfo) {

		return newsInfoDao.newsInfoDelete(newsInfo);
	}

	@Override
	public Integer isExitTitle(NewsInfo newsInfo) {
		return newsInfoDao.isExitTitle(newsInfo);
	}

}
