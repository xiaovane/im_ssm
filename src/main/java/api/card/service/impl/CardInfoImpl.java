package api.card.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.Card;
import admin.entity.api.CardInfo;
import admin.entity.api.SellerCard;
import api.card.dao.CardInfoDao;
import api.card.service.CardInfoService;

@Service("cardInfoService")
public class CardInfoImpl implements CardInfoService {

	@Resource
	private CardInfoDao cardInfoDao;

	public Integer selectCardInfo(CardInfo cardInfo) {
		return cardInfoDao.selectCardInfo(cardInfo);
	}

	public Integer addUserCardInfo(CardInfo cardInfo) {
		return cardInfoDao.addUserCardInfo(cardInfo);
	}

	public List<CardInfo> cardInfoList(Map<String, Object> map) {
		return cardInfoDao.cardInfoList(map);
	}

	public List<CardInfo> cardInfoDetail(CardInfo cardInfo) {
		return cardInfoDao.cardInfoDetail(cardInfo);
	}

	public Integer updateUserCard(SellerCard sellerCard) {
		return cardInfoDao.updateUserCard(sellerCard);
	}

	@Override
	public PageBean<CardInfo> userCardInfoList(CardInfo cardInfo, PageBean<CardInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("card", cardInfo);
		// 总记录放入pageBean
		pageBean.setTotal(cardInfoDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(cardInfoDao.userCardInfoList(map));
		return pageBean;
	}

	@Override
	public CardInfo userCardInfoDetail(CardInfo cardInfo) {
		return cardInfoDao.userCardInfoDetail(cardInfo);
	}

	@Override
	public Integer userCardInfoModify(CardInfo cardInfo) {
		return cardInfoDao.userCardInfoModify(cardInfo);
	}

	@Override
	public Integer isScan(SellerCard sellerCard) {
		return cardInfoDao.isScan(sellerCard);
	}

	@Override
	public Integer giveCardList(Map<String, Object> map) {
		Integer result=0;
		Integer count=0;
		String []card=map.get("cardId").toString().split(",");
		//循环取出cardId
		for (int i = 0; i < card.length; i++) {
			//更新掉card的userId
			Map<String, String> rMap=new HashMap<String,String>();
			rMap.put("userId", map.get("userId").toString());
			rMap.put("friendId", map.get("friendId").toString());
			rMap.put("cardId", card[i]);
			
			Integer rResult=cardInfoDao.giveCardList(rMap);
			if (rResult>0) {
				count=i+1;
			}
			
		}
		
		if (count== card.length) {
			result=1;
		}
		return result;
	}

}
