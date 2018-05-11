package api.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.InfoList;
import admin.entity.api.RadomNickName;
import admin.util.Validation;
import api.user.service.InfoListService;

@Controller
@RequestMapping("infoList")
public class InfoListController {
	@Resource
	private InfoListService infoListService;
	private String token = "";


	/**
	 * 获取信息列表
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientInfoList")
	public void clientInfoList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String type = request.getParameter("type");
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("pageIndex", (Integer.parseInt(pageIndex)-1)* Integer.parseInt(pageSize));
		map.put("pageSize", Integer.parseInt(pageIndex)*Integer.parseInt(pageSize));
		map.put("type", type);
			
		List<InfoList> list = infoListService.clientInfoList(map);
		if (list.equals(null)) {
			Validation.postJson(response, token, "160", "获取信息列表失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取信息列表成功", list);
	}
	
	/**
	 * 根据infoid获取信息详细
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientInfoDetail")
	public void clientInfoDetail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String infoId = request.getParameter("infoId");
		
		InfoList detail = infoListService.clientInfoDetail(infoId);
		if (detail.equals(null)) {
			Validation.postJson(response, token, "160", "获取信息详细失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取信息详细成功", detail);
	}

	/**
	 * 获取随机昵称和图片
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/clientRadomNickName")
	public void clientRadomNickName(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RadomNickName detail = infoListService.clientRadomNickName();
		if (detail.equals(null)) {
			Validation.postJson(response, token, "160", "获取随机昵称和图片失败", "false");
			return;
		}
		Validation.postJson(response, token, "100", "获取随机昵称和图片成功", detail);
	}

}
