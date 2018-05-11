package api.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.Position;
import admin.entity.api.PositionCity;
import admin.entity.api.PositionCounty;
import admin.entity.api.PositionProvince;
import admin.entity.api.PositionTown;
import api.user.dao.PositionDao;
import api.user.service.PositionService;

@Service("positionService")
public class PositionImpl  implements PositionService{
	
	@Resource
	private PositionDao positionDao;

	
	public List<PositionProvince> selectProvince() {
		return positionDao.selectProvince();
	}

	
	public List<PositionCity> selectCity(PositionCity city) {
		return positionDao.selectCity(city);
	}

	
	public List<PositionTown> selectTown(PositionTown town) {
		return positionDao.selectTown(town);
	}

	
	public List<PositionCounty> selectCounty(PositionCounty county) {
		return positionDao.selectCounty(county);
	}


	@Override
	public PageBean<Position> positionList(Position position, PageBean<Position> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("position", position);
		// 总记录放入pageBean
		pageBean.setTotal(positionDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(positionDao.positionList(map));
		return pageBean;
	}


	@Override
	public Position positionContent(Position position) {
		return positionDao.positionContent(position);
	}


	@Override
	public Integer positionModify(Position position) {
		return positionDao.positionModify(position);
	}


	@Override
	public List<Position> clientPositonXiaoQu(Position position) {
		return positionDao.clientPositonXiaoQu(position);
	}


	@Override
	public List<Position> clientSearchXiaoQu(Position position) {
		return positionDao.clientSearchXiaoQu(position);
	}


	@Override
	public Integer positionAdd(Position position) {
		return positionDao.positionAdd(position);
	}


	@Override
	public Integer positionDelete(Position position) {
		return positionDao.positionDelete(position);
	}


	@Override
	public Integer isExitTitle(Position position) {
		return positionDao.isExitTitle(position);
	}

	

}
