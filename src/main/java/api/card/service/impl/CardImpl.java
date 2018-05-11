package api.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.Card;
import admin.entity.api.SellerCard;
import api.card.dao.CardDao;
import api.card.service.CardService;

@Service("cardService")
public class CardImpl implements CardService {
	@Resource
	private CardDao cardDao;

	public Integer createCard(Card card) {
		return cardDao.createCard(card);
	}

	public List<Card> selectCardList(Map map) {
		return cardDao.slectCardList(map);
	}

	public Card selectCardDetail(Card card) {
		return cardDao.slectCardDetail(card);
	}

	public Card selectCard(Card card) {
		return cardDao.selectCard(card);
	}

	public Integer deleteOneNum(Card card) {
		return cardDao.deleteOneNum(card);
	}

	public Integer insertSellerCard(SellerCard sellerCard) {
		return cardDao.insertSellerCard(sellerCard);
	}

	@Override
	public List<Card> getAllList(Map<String, Object> map) {
		return cardDao.getAllList(map);
	}

	@Override
	public PageBean<Card> CardInfoList(Card card, PageBean<Card> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("card", card);
		// 总记录放入pageBean
		pageBean.setTotal(cardDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(cardDao.cardInfoList(map));
		return pageBean;
	}

	@Override
	public Card cardInfoDetail(Card card) {
		return cardDao.cardInfoDetail(card);
	}

	@Override
	public Integer cardInfoModify(Card card) {
		return cardDao.cardInfoModify(card);
	}

	@Override
	public Integer cardInfoLook(Card card) {
		return cardDao.cardInfoLook(card);
	}

}
