package api.user.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.Position;
import admin.entity.api.PositionCity;
import admin.entity.api.PositionCounty;
import admin.entity.api.PositionProvince;
import admin.entity.api.PositionTown;
import admin.util.Validation;
import api.user.service.PositionService;

@Controller
@RequestMapping("clientPosition")
public class PositionController {
	@Resource
	private PositionService positionService;
	private String token = "";

	/**
	 * 获取省份，并返回id和name
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/clientPositonProvice")
	public void positonProvice(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<PositionProvince> list = positionService.selectProvince();
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取省份信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取省份信息成功", list);
	}

	/**
	 * 获取城市相关信息
	 * 
	 * @param request
	 * @param response
	 * @param city
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientPositonCity")
	public void positonCity(HttpServletRequest request, HttpServletResponse response, PositionCity city)
			throws Exception {

		if (city.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		List<PositionCity> list = positionService.selectCity(city);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取城市信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取城市信息成功", list);
	}

	/**
	 * 获取区县信息
	 * 
	 * @param request
	 * @param response
	 * @param county
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientPositonCounty")
	public void positonCounty(HttpServletRequest request, HttpServletResponse response, PositionCounty county)
			throws Exception {

		if (county.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		List<PositionCounty> list = positionService.selectCounty(county);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取区县信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取区县信息成功", list);
	}

	/**
	 * 获取街道的信息
	 * 
	 * @param request
	 * @param response
	 * @param town
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientPositonTown")
	public void positonTown(HttpServletRequest request, HttpServletResponse response, PositionTown town)
			throws Exception {

		if (town.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		List<PositionTown> list = positionService.selectTown(town);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取街道信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取街道信息成功", list);
	}

	/**
	 * 获取小区信息
	 * 
	 * @param request
	 * @param response
	 * @param town
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientPositonXiaoQu")
	public void clientPositonXiaoQu(HttpServletRequest request, HttpServletResponse response, Position position )
			throws Exception {

		if (position.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		List<Position> list = positionService.clientPositonXiaoQu(position);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取小区信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取小区信息成功", list);
	}

	/**
	 * 模糊搜索小区信息
	 * 
	 * @param request
	 * @param response
	 * @param town
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientSearchXiaoQu")
	public void clientSearchXiaoQu(HttpServletRequest request, HttpServletResponse response, Position position)
			throws Exception {

		if (position.equals(null)) {
			Validation.postJson(response, token, "101", "参数不能为空", "false");
			return;
		}

		List<Position> list = positionService.clientSearchXiaoQu(position);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "搜索小区信息失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "搜索小区信息成功", list);
	}

}
