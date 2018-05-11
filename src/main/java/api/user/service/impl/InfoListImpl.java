package api.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.InfoList;
import admin.entity.api.RadomNickName;
import api.user.dao.InfoListDao;
import api.user.service.InfoListService;

@Service("infoListService")
public class InfoListImpl  implements InfoListService{
	
	@Resource
	private InfoListDao infoListDao;


	


	@Override
	public List<InfoList> clientInfoList(Map<String, Object> map) {
		return infoListDao.clientInfoList(map);
	}


	@Override
	public InfoList clientInfoDetail(String infoId) {
		return infoListDao.clientInfoDetail(infoId);
	}


	@Override
	public RadomNickName clientRadomNickName() {
		return infoListDao.RadomNickName();
	}


	@Override
	public PageBean<InfoList> baseQuestion(InfoList infoList, PageBean<InfoList> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("infoList", infoList);
		// 总记录放入pageBean
		pageBean.setTotal(infoListDao.getBaseQuestionTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(infoListDao.baseQuestion(map));
		return pageBean;
	}


	@Override
	public InfoList baseQuestionDetail(InfoList infoList) {
		return infoListDao.baseQuestionDetail(infoList);
	}


	@Override
	public Integer baseQuestionUpdate(InfoList infoList) {
		return infoListDao.baseQuestionUpdate(infoList);
	}


	@Override
	public Integer baseQuestionAdd(InfoList infoList) {
		return infoListDao.baseQuestionAdd(infoList);
	}


	@Override
	public PageBean<RadomNickName> radomImg(RadomNickName radomNickName, PageBean<RadomNickName> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("radomNickName", radomNickName);
		// 总记录放入pageBean
		pageBean.setTotal(infoListDao.getRadomImgTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(infoListDao.radomImg(map));
		return pageBean;
	}


	@Override
	public RadomNickName radomImgDetail(RadomNickName radomNickName) {
		return infoListDao.radomImgDetail(radomNickName);
	}


	@Override
	public Integer radomImgUpdate(RadomNickName radomNickName) {
		return infoListDao.radomImgUpdate(radomNickName);
	}


	@Override
	public Integer radomImgAdd(RadomNickName radomNickName) {
		return infoListDao.radomImgAdd(radomNickName);
	}


	@Override
	public Integer isSameTitle(InfoList infoList) {
		return infoListDao.isSameTitle(infoList);
	}


	@Override
	public Integer isSameNickName(RadomNickName radomNickName) {
		return infoListDao.isSameNickName(radomNickName);
	}


	@Override
	public Integer baseQuestionDelete(InfoList infoList) {
		return infoListDao.baseQuestionDelete(infoList);
	}


	@Override
	public Integer radomImgDelete(RadomNickName radomNickName) {
		return infoListDao.radomImgDelete(radomNickName);
	}

}
