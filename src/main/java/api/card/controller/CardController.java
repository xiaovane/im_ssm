package api.card.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.Card;
import admin.entity.api.SellerCard;
import admin.util.Validation;
import api.card.service.CardInfoService;
import api.card.service.CardService;
import api.user.service.UserApiService;

@Controller
@RequestMapping("clientCard")
public class CardController {
	@Resource
	private CardService cardService;
	@Resource
	private CardInfoService cardInfoService;
	@Resource
	private UserApiService userApiService;
	private String token = "";
	
	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currentTime = new Date();


	/**
	 * 创建卡券
	 * 
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "clientCreateCard")
	public void createCard(HttpServletRequest request, HttpServletResponse response, Card card) throws Exception {
		if (card.equals(null)) {
			Validation.postJson(response, token, "101", "参数为空", "false");
			return;
		}
		// 创建卡券
		try {
			// 设定创建卡券时间
			card.setCreateTime(formatter.format(currentTime));
			Integer result = cardService.createCard(card);
			if (result.equals(0)) {
				Validation.postJson(response, token, "160", "创建卡券失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(card.getUserId());
			Validation.postJson(response, token, "100", "创建卡券成功", "true");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取审核通过的卡券列表
	 * 
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "clientCardList")
	public void getCardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		Map<String, Object> map = new HashMap<String, Object>();

		if (userId.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
		} else {
			// 封装不同类型的参数，解决因为参数类型不同导致的报错问题
			map.put("pageIndex", (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(pageSize));
			map.put("pageSize", Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
			map.put("userId", userId);

			List<Card> list = cardService.selectCardList(map);
			if (list.equals(null)) {
				Validation.postJson(response, token, "160", "获取卡券失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(userId);
			Validation.postJson(response, token, "100", "获取卡券成功", list);
		}
	}

	/**
	 * 获取审核通过的卡券详细表
	 * 
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "clientCardDetail")
	public void getCardDetail(HttpServletRequest request, HttpServletResponse response, Card card) throws Exception {
		if (card.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
		} else {
			Card detail = cardService.selectCardDetail(card);
			if (detail.equals(null)) {
				Validation.postJson(response, token, "160", "获取卡券信息失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(card.getUserId());
			Validation.postJson(response, token, "100", "获取卡券信息成功", detail);
		}
	}

	/**
	 * 扫码卡券
	 * 
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "scanCard")
	public void scanCard(HttpServletRequest request, HttpServletResponse response, SellerCard sellerCard)
			throws Exception {
		if (sellerCard.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
		} else {
			// 判断是否已经扫码过了
			Integer isScan = cardInfoService.isScan(sellerCard);
			if (isScan.equals(0)) {
				Validation.postJson(response, token, "124", "您已经扫码过了", "false");
				return;
			}
			// 执行把用户的卡券的状态置为0，不可用
			Integer updateUserCard = cardInfoService.updateUserCard(sellerCard);
			if (updateUserCard.equals(0)) {
				Validation.postJson(response, token, "160", "扫码卡券失败", "false");
				return;
			}
			Integer insertSellerCard = cardService.insertSellerCard(sellerCard);
			if (insertSellerCard.equals(0)) {
				Validation.postJson(response, token, "160", "插入用户卡券信息失败", "false");
				return;
			}
			// 数据库中查询token
			token = userApiService.getToken(sellerCard.getUserId());
			Validation.postJson(response, token, "100", "扫码卡券成功", "true");
		}
	}

	/**
	 * 获取所有商户创建的卡券列表信息
	 * 
	 * @param request
	 * @param response
	 * @param card
	 * @throws Exception
	 */
	@RequestMapping(value = "sellerCardList")
	public void sellerCardList(HttpServletRequest request, HttpServletResponse response, Card card) throws Exception {
		if (card.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
		} else {
			// 每个商户取一条展示，取出全部商户的信息
			String pageIndex = request.getParameter("pageIndex");
			String pageSize = request.getParameter("pageSize");

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageIndex", (Integer.parseInt(pageIndex) - 1)* Integer.parseInt(pageSize));
			map.put("pageSize", Integer.parseInt(pageIndex) * Integer.parseInt(pageSize));
			map.put("city", card.getCity());
			List<Card> list = cardService.getAllList(map);
			if (list.equals(null)) {
				Validation.postJson(response, token, "160", "获取商户列表失败", "false");
				return;
			}

			// 数据库中查询token
			token = userApiService.getToken(card.getUserId());
			Validation.postJson(response, token, "100", "获取商户列表成功", list);
		}
	}

}
