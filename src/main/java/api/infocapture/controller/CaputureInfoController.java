package  api.infocapture.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import admin.entity.api.CaptureInfo;

import admin.entity.api.NewsInfo;
import admin.util.ResultInfoUtil;
import admin.util.Validation;
import api.infocapture.service.GetCaptureInfoService;
import api.infocapture.service.NewsInfoService;
import api.infocapture.service.impl.TutorialCrawler;

@Controller
@RequestMapping("/clientCaputeInfo")
public class CaputureInfoController {
	@Resource
	private GetCaptureInfoService getCaptureInfo;
	@Resource
	private NewsInfoService newsInfoService;
	private CaptureInfo captureInfo = new CaptureInfo();
	
	private String token="";

	/**
	 * 信息抓取并插入数据库初始化
	 * 
	 * @param args
	 * @throws Exception
	 */
	@RequestMapping("/clientCaptureNews")
	public void captureNews(String[] args) throws Exception {

		List<CaptureInfo> info = getCaptureInfo.getInfo(captureInfo);
		//遍历循环取出抓取的配置内容去相应的网页抓取
		for (CaptureInfo captureInfo : info) {

			TutorialCrawler crawler = new TutorialCrawler("crawler", true);
			crawler.addSeed("" + captureInfo.getUrlList() + "");
			crawler.addRegex("" + captureInfo.getUrlDetail() + "");

			crawler.setThreads(30);
			crawler.start(2);
		}

	}

	/**
	 * 获取新闻列表
	 * @param request
	 * @param response
	 * @param newsInfo
	 */
	@RequestMapping("clientNewsList")
	public void newsList(HttpServletRequest request, HttpServletResponse response, NewsInfo newsInfo) {
		String pageIndex = request.getParameter("pageIndex");
		String pageSize = request.getParameter("pageSize");
		String categoryNum = request.getParameter("categoryNum");
		try {
			List<NewsInfo> map=	newsInfoService.selectInfo((Integer.parseInt(pageIndex)-1)*Integer.parseInt(pageSize),Integer.parseInt(pageIndex)*Integer.parseInt(pageSize), Integer.parseInt(categoryNum));
			if (map.equals(null)) {
				Validation.postJson(response,token, "100", "获取新闻列表信息失败", "false");
				return;
			}
			Validation.postJson(response,token, "100", "获取新闻列表信息成功", map);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * 获取新闻详细，供前台h5展示
	 * @param request
	 * @param response
	 * @param newsInfo
	 */
	@RequestMapping("clientNewsDetail")
	public void clientNewsDetail(HttpServletRequest request, HttpServletResponse response, NewsInfo newsDetail) {
		try {
			NewsInfo detail=	newsInfoService.newsDetail(newsDetail);
			ResultInfoUtil.jsonObject(response, detail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
