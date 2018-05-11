package api.card.service;

import java.util.List;
import java.util.Map;

import admin.entity.PageBean;
import admin.entity.api.Card;
import admin.entity.api.SellerCard;

public interface CardService {

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
	List<Card> selectCardList(Map map);

	/**
	 * 获取卡券详情
	 * 
	 * @param card
	 * @return
	 */
	Card selectCardDetail(Card card);

	/**
	 * 查询卡券
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
	 * 插入卡券
	 * 
	 * @param sellerCard
	 * @return
	 */
	Integer insertSellerCard(SellerCard sellerCard);

	/**
	 * 获取所有卡券列表
	 * 
	 * @param map
	 * @return
	 */
	List<Card> getAllList(Map<String, Object> map);

	// admin begin
	/**
	 * 获取卡券列表
	 * 
	 * @param card
	 * @param pageBean
	 * @return
	 */
	PageBean<Card> CardInfoList(Card card, PageBean<Card> pageBean);

	/**
	 * 获取卡券详情
	 * 
	 * @param card
	 * @return
	 */
	Card cardInfoDetail(Card card);

	/**
	 * 修改卡券
	 * 
	 * @param card
	 * @return
	 */
	Integer cardInfoModify(Card card);

	/**
	 * 卡券审核
	 * @param card
	 * @return
	 */
	Integer cardInfoLook(Card card);

}
