package api.user.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.Position;
import admin.entity.api.PositionCity;
import admin.entity.api.PositionCounty;
import admin.entity.api.PositionProvince;
import admin.entity.api.PositionTown;

public interface PositionService {

	// api begin
	/**
	 * 选取省份
	 * 
	 * @return
	 */
	List<PositionProvince> selectProvince();

	/**
	 * 选取城市
	 * 
	 * @param city
	 * @return
	 */
	List<PositionCity> selectCity(PositionCity city);

	/**
	 * 选取区镇
	 * 
	 * @param town
	 * @return
	 */
	List<PositionTown> selectTown(PositionTown town);

	/**
	 * 选取区县市
	 * 
	 * @param county
	 * @return
	 */
	List<PositionCounty> selectCounty(PositionCounty county);

	// admin begin
	/**
	 * 获取小区列表
	 * 
	 * @param position
	 * @param pageBean
	 * @return
	 */
	PageBean<Position> positionList(Position position, PageBean<Position> pageBean);

	/**
	 * 获取小区详细
	 * 
	 * @param position
	 * @return
	 */
	Position positionContent(Position position);

	/**
	 * 小区信息修改
	 * 
	 * @param position
	 * @return
	 */
	Integer positionModify(Position position);

	/**
	 * 获取小区列表
	 * 
	 * @param position
	 * @return
	 */
	List<Position> clientPositonXiaoQu(Position position);

	/**
	 * 模糊查询小区信息
	 * 
	 * @param position
	 * @return
	 */
	List<Position> clientSearchXiaoQu(Position position);

	/**
	 * 新增小区信息
	 * 
	 * @param position
	 * @return
	 */
	Integer positionAdd(Position position);

	/**
	 * 批量删除小区信息
	 * 
	 * @param position
	 * @return
	 */
	Integer positionDelete(Position position);

	/**
	 * 新增时判断小区名称是否存在
	 * @param position
	 * @return
	 */
	Integer isExitTitle(Position position);

}
