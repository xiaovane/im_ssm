package api.card.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.CardInfo;
import admin.entity.api.SellerCard;

public interface CardInfoDao {

	// api begin
	/**
	 * 获取用户卡券
	 * 
	 * @param cardInfo
	 * @return
	 */
	public Integer selectCardInfo(CardInfo cardInfo);

	/**
	 * 领取卡券
	 * 
	 * @param cardInfo
	 * @return
	 */
	public Integer addUserCardInfo(CardInfo cardInfo);

	/**
	 * 获取用户卡券列表
	 * 
	 * @param map
	 * @return
	 */
	public List<CardInfo> cardInfoList(Map<String, Object> map);

	/**
	 * 获取卡券详细
	 * 
	 * @param cardInfo
	 * @return
	 */
	public List<CardInfo> cardInfoDetail(CardInfo cardInfo);

	/**
	 * 更新卡券
	 * 
	 * @param sellerCard
	 * @return
	 */
	Integer updateUserCard(SellerCard sellerCard);

	/**
	 * 判断是否扫码过了
	 * 
	 * @param sellerCard
	 * @return
	 */
	public Integer isScan(SellerCard sellerCard);

	// admin begin
	/**
	 * 获取卡券总数
	 * 
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String, Object> map);

	/**
	 * 获取卡券列表
	 * 
	 * @param map
	 * @return
	 */
	public List<CardInfo> userCardInfoList(Map<String, Object> map);

	/**
	 * 获取卡券详细
	 * 
	 * @param cardInfo
	 * @return
	 */
	public CardInfo userCardInfoDetail(CardInfo cardInfo);

	/**
	 * 修改卡券
	 * 
	 * @param cardInfo
	 * @return
	 */
	public Integer userCardInfoModify(CardInfo cardInfo);

	/**
	 * 赠送好友卡券
	 * @param rMap
	 * @return
	 */
	public Integer giveCardList(Map<String, String> rMap);

	

}
