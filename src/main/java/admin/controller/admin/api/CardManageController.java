package admin.controller.admin.api;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.PageBean;
import admin.entity.api.Card;
import admin.entity.api.CardInfo;
import admin.util.ResultInfoUtil;
import api.card.service.CardInfoService;
import api.card.service.CardService;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/cardInfo")
public class CardManageController {

	@Resource
	private CardService apiService;
	@Resource
	private CardInfoService cardInfoApiService;

	/**
	 * 获取卡券列表
	 * 
	 * @param page
	 * @param rows
	 * @param card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cardInfoList")
	public String cardInfoList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Card card, HttpServletResponse response)
			throws Exception {

		PageBean<Card> pageBean = new PageBean<Card>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = apiService.CardInfoList(card, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取卡券详细
	 * 
	 * @param card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cardInfoDetail")
	public String cardInfoDetail(Card card, HttpServletResponse response) throws Exception {
		Card cardInfoDetail = apiService.cardInfoDetail(card);
		ResultInfoUtil.jsonObject(response, cardInfoDetail);
		return null;
	}

	/**
	 * 修改卡券
	 * 
	 * @param Card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cardInfoModify")
	public String cardInfoModify(Card card, HttpServletResponse response) throws Exception {
		Integer result = apiService.cardInfoModify(card);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");
		}
		
		return null;
	}
	
	/**
	 * 审核卡券
	 * @param card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/cardInfoLook")
	public String cardInfoLook(Card card, HttpServletResponse response) throws Exception {
		Integer result = apiService.cardInfoLook(card);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");
		}
		return null;
	}

	// 用户卡券
	/**
	 * 获取用户卡券列表
	 * 
	 * @param page
	 * @param rows
	 * @param card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userCardInfoList")
	public String userCardInfoList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, CardInfo cardInfo,
			HttpServletResponse response) throws Exception {

		PageBean<CardInfo> pageBean = new PageBean<CardInfo>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = cardInfoApiService.userCardInfoList(cardInfo, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取用户卡券详细
	 * 
	 * @param card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userCardInfoDetail")
	public String userCardInfoDetail(CardInfo cardInfo, HttpServletResponse response) throws Exception {
		CardInfo userCardDetail = cardInfoApiService.userCardInfoDetail(cardInfo);
		ResultInfoUtil.jsonObject(response, userCardDetail);
		return null;
	}

	/**
	 * 修改用户卡券
	 * 
	 * @param Card
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userCardInfoModify")
	public String userCardInfoModify(CardInfo cardInfo, HttpServletResponse response) throws Exception {
		Integer result = cardInfoApiService.userCardInfoModify(cardInfo);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else{
			ResultInfoUtil.resultSuccess(response, "success");
		}		
		return null;
	}
}
