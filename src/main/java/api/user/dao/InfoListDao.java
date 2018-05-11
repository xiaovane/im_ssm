package api.user.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.InfoList;
import admin.entity.api.RadomNickName;

public interface InfoListDao {

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
	RadomNickName RadomNickName();

	// admin begin
	/**
	 * 获取常见问题分页总数
	 * 
	 * @param map
	 * @return
	 */
	long getBaseQuestionTotal(Map<String, Object> map);

	/**
	 * 获取常见问题分页列表
	 * 
	 * @param map
	 * @return
	 */
	List<InfoList> baseQuestion(Map<String, Object> map);

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
	 * 获取随机昵称图片总数
	 * 
	 * @param map
	 * @return
	 */
	long getRadomImgTotal(Map<String, Object> map);

	/**
	 * 获取随机图片昵称列表
	 * 
	 * @param map
	 * @return
	 */
	List<RadomNickName> radomImg(Map<String, Object> map);

	/**
	 * 获取随机图片昵称详细
	 * 
	 * @param radomNickName
	 * @return
	 */
	RadomNickName radomImgDetail(RadomNickName radomNickName);

	/**
	 * 更新随机图片详细
	 * 
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgUpdate(RadomNickName radomNickName);

	/**
	 * 新增随机图片详细
	 * 
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgAdd(RadomNickName radomNickName);

	/**
	 * 判断名称是否重复
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
	 * 批量删除随机昵称图片
	 * @param radomNickName
	 * @return
	 */
	Integer radomImgDelete(RadomNickName radomNickName);

}
