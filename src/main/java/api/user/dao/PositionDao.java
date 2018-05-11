package api.user.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.Position;
import admin.entity.api.PositionCity;
import admin.entity.api.PositionCounty;
import admin.entity.api.PositionProvince;
import admin.entity.api.PositionTown;

public interface PositionDao {

	// api begin
	/**
	 * 获取省份
	 * 
	 * @return
	 */
	List<PositionProvince> selectProvince();

	/**
	 * 获取城市
	 * 
	 * @param city
	 * @return
	 */
	List<PositionCity> selectCity(PositionCity city);

	/**
	 * 获取区镇
	 * 
	 * @param town
	 * @return
	 */
	List<PositionTown> selectTown(PositionTown town);

	/**
	 * 获取区县市
	 * 
	 * @param county
	 * @return
	 */
	List<PositionCounty> selectCounty(PositionCounty county);

	// admin begin
	/**
	 * 获取小区列表总数
	 * 
	 * @param map
	 * @return
	 */
	long getTotal(Map<String, Object> map);

	/**
	 * 获取小区列表
	 * 
	 * @param map
	 * @return
	 */
	List<Position> positionList(Map<String, Object> map);

	/**
	 * 修改小区信息
	 * 
	 * @param position
	 * @return
	 */
	Integer positionModify(Position position);

	/**
	 * 获取小区详细信息
	 * 
	 * @param position
	 * @return
	 */
	Position positionContent(Position position);

	/**
	 * 获取小区信息
	 * 
	 * @param position
	 * @return
	 */
	List<Position> clientPositonXiaoQu(Position position);

	/**
	 * 模糊搜索小区信息
	 * 
	 * @param position
	 * @return
	 */
	List<Position> clientSearchXiaoQu(Position position);

	/**
	 * 新增小区信息
	 * @param position
	 * @return
	 */
	Integer positionAdd(Position position);

	/**
	 * 批量删除小区信息
	 * @param position
	 * @return
	 */
	Integer positionDelete(Position position);

	/**
	 * 新增小区时判断小区是否存在
	 * @param position
	 * @return
	 */
	Integer isExitTitle(Position position);

}
