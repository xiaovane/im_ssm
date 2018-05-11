package api.card.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.Card;
import admin.entity.api.CardInfo;
import admin.util.Validation;
import api.card.service.CardInfoService;
import api.card.service.CardService;
import api.user.service.UserApiService;

@Controller
@RequestMapping("clientUserCard")
public class UserCardController {

	@Resource
	private CardService cardService;
	@Resource
	private CardInfoService cardInfoService;
	@Resource
	private UserApiService userApiService;
	private String token = "";

	/**
	 * 领取卡券
	 * 
	 * @param request
	 * @param response
	 * @param cardInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "userCardClick")
	@Transactional(propagation = Propagation.REQUIRED)
	public void userCardClick(HttpServletRequest request, HttpServletResponse response, CardInfo cardInfo)
			throws Exception {
		// 判断参数是否为空
		if (cardInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数为空", "false");
			return;
		}

		// 是否领取的卡券是否过期
		Card card = new Card();
		card.setCardId(cardInfo.getCardId());
		Card resultCard = cardService.selectCard(card);
		if (resultCard.equals(null)) {
			Validation.postJson(response, token, "112", "卡券不存在或者已过期", "false");
			return;
		}

		// 判断卡券是否领取完
		else if (resultCard.getNum().equals(0)) {
			Validation.postJson(response, token, "120", "您手慢了一步，卡券已经领取完了", "false");
			return;
		}

		// 判断是否领取过了
		Integer resultCardInfo = cardInfoService.selectCardInfo(cardInfo);
		if (!resultCardInfo.equals(0)) {
			Validation.postJson(response, token, "113", "卡券已经领取过了", "false");
			return;
		}

		// 领取卡券
		try {

			Integer resultUserCard = cardInfoService.addUserCardInfo(cardInfo);
			if (resultUserCard.equals(0)) {
				Validation.postJson(response, token, "160", "卡券已经领取失败", "false");
				return;
			}

			// 往卡券里面的数据减一
			Integer deleteOneNum = cardService.deleteOneNum(card);
			if (deleteOneNum.equals(0)) {
				Validation.postJson(response, token, "160", "卡券数量减1失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(cardInfo.getUserId());
			Validation.postJson(response, token, "100", "卡券已经领取成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户卡券列表
	 * 
	 * @param request
	 * @param response
	 * @param cardInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/userCardList")
	public void userCardList(HttpServletRequest request, HttpServletResponse response, CardInfo cardInfo)
			throws Exception {

		if (cardInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageIndex", (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
		map.put("userId", cardInfo.getUserId());

		try {
			List<CardInfo> list = cardInfoService.cardInfoList(map);
			if (list.equals(null)) {
				Validation.postJson(response, token, "160", "获取用户卡券列表失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(cardInfo.getUserId());
			Validation.postJson(response, token, "100", "获取用户卡券列表成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取用户卡券详细
	 * 
	 * @param request
	 * @param response
	 * @param cardInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/userCardDetail")
	public void userCardDetail(HttpServletRequest request, HttpServletResponse response, CardInfo cardInfo)
			throws Exception {

		if (cardInfo.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		try {
			List<CardInfo> list = cardInfoService.cardInfoDetail(cardInfo);
			if (list.equals(null)) {
				Validation.postJson(response, token, "160", "获取用户卡券详细页失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(cardInfo.getUserId());
			Validation.postJson(response, token, "100", "获取用户卡券详细页成功", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 赠送给朋友卡券
	 * 
	 * @param request
	 * @param response
	 * @param cardInfo
	 * @throws Exception
	 */
	@RequestMapping(value = "/giveCardList")
	public void giveCardList(HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			String userId = request.getParameter("userId");
			String friendId = request.getParameter("friendId");
			String cardId = request.getParameter("cardId");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId",userId);
			map.put("friendId", friendId);
			map.put("cardId", cardId);

			Integer result = cardInfoService.giveCardList(map);
			if (result.equals(0)) {
				Validation.postJson(response, token, "127", "赠送卡券失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(userId);
			Validation.postJson(response, token, "100", "赠送卡券成功", "true");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
