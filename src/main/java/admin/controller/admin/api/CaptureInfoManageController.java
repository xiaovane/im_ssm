package admin.controller.admin.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.PageBean;
import admin.entity.api.CaptureInfo;
import admin.entity.api.NewsInfo;
import admin.util.ResultInfoUtil;
import api.infocapture.service.GetCaptureInfoService;
import api.infocapture.service.NewsInfoService;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/captureInfo")
public class CaptureInfoManageController {

	@Resource
	private GetCaptureInfoService apiService;

	@Resource
	private NewsInfoService apiNewsService;

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date currentTime = new Date();
	
	/**
	 * 获取抓取配置信息列表
	 * 
	 * @param page
	 * @param rows
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captureInfoList")
	public String captureInfoList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, CaptureInfo captureInfo,
			HttpServletResponse response) throws Exception {

		PageBean<CaptureInfo> pageBean = new PageBean<CaptureInfo>(Integer.parseInt(page), Integer.parseInt(rows));

		// 根据 title查询
		pageBean = apiService.captureInfoList(captureInfo, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取抓取配置信息
	 * 
	 * @param page
	 * @param rows
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captureInfoContent")
	public String captureInfoContent(CaptureInfo captureInfo, HttpServletResponse response) throws Exception {
		CaptureInfo captureInfoDetail = apiService.captureInfoContent(captureInfo);
		ResultInfoUtil.jsonObject(response, captureInfoDetail);
		return null;
	}

	/**
	 * 抓取配置信息修改
	 * 
	 * @param feedBack
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captureInfoModify")
	public String captureInfoModify(CaptureInfo captureInfo, HttpServletResponse response) throws Exception {
		Integer result = apiService.captureInfoModify(captureInfo);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else{
			ResultInfoUtil.resultSuccess(response, "success");
		}
		
		return null;
	}
	
	/**
	 * 新增抓取信息
	 * @param captureInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captureInfoAdd")
	public String captureInfoAdd(CaptureInfo captureInfo, HttpServletResponse response) throws Exception {
		captureInfo.setInfoId(UUID.randomUUID().toString());
		Integer result = apiService.captureInfoAdd(captureInfo);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else{
			ResultInfoUtil.resultSuccess(response, "success");
		}

		return null;
	}
	
	/**
	 * 批量删除配置信息
	 * @param captureInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/captureInfoDelete")
	public void captureInfoDelete(CaptureInfo captureInfo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		String msg = request.getParameter("msg");
		String[] menu = msg.split(",");
		for (int i = 0; i < menu.length; i++) {
			captureInfo.setId(Integer.parseInt(menu[i]));
			Integer result = apiService.captureInfoDelete(captureInfo);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
		}

		ResultInfoUtil.resultSuccess(response, "success");
		
	}

	/**
	 * 获取新闻列表
	 * 
	 * @param page
	 * @param rows
	 * @param newsInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsInfoList")
	public String newsInfoList(@RequestParam(value = "page", required = false) String page,@RequestParam(value="startTime",required=false) String startTime,@RequestParam(value="endTime",required=false) String endTime,
			@RequestParam(value = "rows", required = false) String rows, NewsInfo newsInfo,
			HttpServletResponse response) throws Exception {

		PageBean<NewsInfo> pageBean = new PageBean<NewsInfo>(Integer.parseInt(page), Integer.parseInt(rows));

        //封装参数
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("news", newsInfo);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		pageBean = apiNewsService.newsInfoList(map, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取新闻详细
	 * 
	 * @param newsInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsInfoContent")
	public String newsInfoContent(NewsInfo newsInfo, HttpServletResponse response) throws Exception {
		NewsInfo newsInfoContent = apiNewsService.newsInfoContent(newsInfo);
		ResultInfoUtil.jsonObject(response, newsInfoContent);
		return null;
	}

	/**
	 * 修改新闻
	 * 
	 * @param newsInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsInfoModify")
	public String newsInfoModify(NewsInfo newsInfo, HttpServletResponse response) throws Exception {
		Integer result = apiNewsService.newsInfoModify(newsInfo);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");	
		}		
		return null;
	}
	
	/**
	 * 新增新闻信息
	 * @param newsInfo
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/newsInfoAdd")
	public void newsInfoAdd(NewsInfo newsInfo, HttpServletResponse response) throws Exception {
		//判断标题是否存在
		Integer isExitTitle=apiNewsService.isExitTitle(newsInfo);
		if (!isExitTitle.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "exit");
			return;
		}
		
		newsInfo.setInfoGuid(UUID.randomUUID().toString());
		newsInfo.setUrl("/info/newsInfo.html?id="+newsInfo.getInfoGuid()+"");
		//newsInfo.setInfoDate(formatter.format(currentTime));
		Integer result = apiNewsService.newsInfoAdd(newsInfo);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");
		}
		else {
			ResultInfoUtil.resultSuccess(response, "success");	
		}		
		
	}
	
	/**
	 * 批量删除新闻信息
	 * @param newsInfo
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping("/newsInfoDelete")
	public void newsInfoDelete(NewsInfo newsInfo, HttpServletResponse response,HttpServletRequest request) throws Exception {
		
		String msg = request.getParameter("msg");
		String[] menu = msg.split(",");
		for (int i = 0; i < menu.length; i++) {
			newsInfo.setId(Integer.parseInt(menu[i]));
			Integer result = apiNewsService.newsInfoDelete(newsInfo);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
		}

		ResultInfoUtil.resultSuccess(response, "success");
		
	}
	
}
