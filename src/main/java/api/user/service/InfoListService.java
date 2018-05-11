package api.user.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.InfoList;
import admin.entity.api.Position;
import admin.entity.api.PositionCity;
import admin.entity.api.PositionCounty;
import admin.entity.api.PositionProvince;
import admin.entity.api.PositionTown;
import admin.entity.api.RadomNickName;

public interface InfoListService {

	// api begin
	/**
	 * 获取信息列表
	 * 
	 * @param map
	 * @return
	 */
	List<InfoList> clientInfoList(Map<String, Object> map);

	/**
	 * 根据infoid获取详细信息
	 * 
	 * @param infoId
	 * @return
	 */
	InfoList clientInfoDetail(String infoId);

	/**
	 * 获取随机昵称和图片
	 * 
	 * @return
	 */
	RadomNickName clientRadomNickName();

	// admin begin
	/**
	 * 获取常见问题列表
	 * 
	 * @param infoList
	 * @param pageBean
	 * @return
	 */
	PageBean<InfoList> baseQuestion(InfoList infoList, PageBean<InfoList> pageBean);

	/**
	 * 获取常见问题详细
	 * 
	 * @param infoList
	 * @return
	 */
	InfoList baseQuestionDetail(InfoList infoList);

	/**
	 * 更新常见问题
	 * 
	 * @param infoList
	 * @return
	 */
	Integer baseQuestionUpdate(InfoList infoList);

	/**
	 * 新增常见问题
	 * 
	 * @param infoList
	 * @return
	 */
	Integer baseQuestionAdd(InfoList infoList);

	/**
	 * 获取随机图片列表
	 * 
	 * @param radomNickName
	 * @param pageBean
	 * @return
	 */
	PageBean<RadomNickName> radomImg(RadomNickName radomNickName, PageBean<RadomNickName> pageBean);

	/**
	 * 获取随机图片详细
	 * 
	 * @param radomNickName
	 * @return
	 */
	RadomNickName radomImgDetail(RadomNickName radomNickName);

	/**
	 * 更新随机图片
	 * 
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgUpdate(RadomNickName radomNickName);

	/**
	 * 新增随机图片
	 * 
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgAdd(RadomNickName radomNickName);

	/**
	 * 判断标题是否存在
	 * @param infoList
	 * @return
	 */
	Integer isSameTitle(InfoList infoList);

	/**
	 * 判断昵称是否存在
	 * @param radomNickName
	 * @return
	 */
	Integer isSameNickName(RadomNickName radomNickName);

	/**
	 * 批量删除常见问题
	 * @param infoList
	 * @return
	 */
	Integer baseQuestionDelete(InfoList infoList);

	/**
	 * 批量删除随机图片昵称
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgDelete(RadomNickName radomNickName);

}
