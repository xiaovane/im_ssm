package api.card.dao;

import java.util.List;
import java.util.Map;

import admin.entity.api.Card;
import admin.entity.api.SellerCard;

public interface CardDao {
	// api begin
	/**
	 * 创建卡券
	 * 
	 * @param card
	 * @return
	 */
	Integer createCard(Card card);

	/**
	 * 获取卡券列表
	 * 
	 * @param map
	 * @return
	 */
	List<Card> slectCardList(Map map);

	/**
	 * 获取卡券详细
	 * 
	 * @param card
	 * @return
	 */
	Card slectCardDetail(Card card);

	/**
	 * 获取卡券实体
	 * 
	 * @param card
	 * @return
	 */
	Card selectCard(Card card);

	/**
	 * 删除卡券
	 * 
	 * @param card
	 * @return
	 */
	Integer deleteOneNum(Card card);

	/**
	 * 新增卡券
	 * 
	 * @param sellerCard
	 * @return
	 */
	Integer insertSellerCard(SellerCard sellerCard);

	/**
	 * 获取所有商户卡券列表
	 * 
	 * @param map
	 * @return
	 */
	List<Card> getAllList(Map<String, Object> map);

	// admin begin
	/**
	 * 获取总数，用于分页
	 * 
	 * @param map
	 * @return
	 */
	long getTotal(Map<String, Object> map);

	/**
	 * 获取卡券列表
	 * 
	 * @param map
	 * @return
	 */
	List<Card> cardInfoList(Map<String, Object> map);

	/**
	 * 获取卡券详细
	 * 
	 * @param card
	 * @return
	 */
	Card cardInfoDetail(Card card);

	/**
	 * 卡券修改
	 * 
	 * @param card
	 * @return
	 */
	Integer cardInfoModify(Card card);

	/**
	 * 审核卡券
	 * @param card
	 * @return
	 */
	Integer cardInfoLook(Card card);

}
