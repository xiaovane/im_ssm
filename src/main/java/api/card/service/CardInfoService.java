package api.card.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.Card;
import admin.entity.api.CardInfo;
import admin.entity.api.SellerCard;

public interface CardInfoService {

	// api begin
	/**
	 * 获取卡券是否存在
	 * 
	 * @param cardInfo
	 * @return
	 */
	Integer selectCardInfo(CardInfo cardInfo);

	/**
	 * 新增卡券
	 * 
	 * @param cardInfo
	 * @return
	 */
	Integer addUserCardInfo(CardInfo cardInfo);

	/**
	 * 获取卡券列表
	 * 
	 * @param map
	 * @return
	 */
	List<CardInfo> cardInfoList(Map<String, Object> map);

	/**
	 * 获取卡券详情
	 * 
	 * @param cardInfo
	 * @return
	 */
	List<CardInfo> cardInfoDetail(CardInfo cardInfo);

	/**
	 * 更新卡券
	 * 
	 * @param sellerCard
	 * @return
	 */
	Integer updateUserCard(SellerCard sellerCard);

	/**
	 * 判断是否已经扫码过了
	 * 
	 * @param sellerCard
	 * @return
	 */

	Integer isScan(SellerCard sellerCard);

	// admin begin
	/**
	 * 获取用户卡券列表
	 * 
	 * @param cardInfo
	 * @param pageBean
	 * @return
	 */
	PageBean<CardInfo> userCardInfoList(CardInfo cardInfo, PageBean<CardInfo> pageBean);

	/**
	 * 获取用户卡券详细
	 * 
	 * @param cardInfo
	 * @return
	 */
	CardInfo userCardInfoDetail(CardInfo cardInfo);

	/**
	 * 修改用户卡券
	 * 
	 * @param cardInfo
	 * @return
	 */
	Integer userCardInfoModify(CardInfo cardInfo);

	/**
	 * 赠送卡券
	 * @param map
	 * @return
	 */
	Integer giveCardList(Map<String, Object> map);

}
