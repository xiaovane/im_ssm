package api.infocapture.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import api.infocapture.service.GetCaptureInfoService;
import api.infocapture.service.NewsInfoService;
import admin.entity.api.CaptureInfo;
import admin.entity.api.NewsInfo;
import admin.util.SpringContextUtil;
import admin.util.Validation;

public class TutorialCrawler extends BreadthCrawler {
	// 普通类调用service
	GetCaptureInfoService getCaptureInfo = (GetCaptureInfoService) SpringContextUtil.getBean("getCaptureInfoService");
	NewsInfoService impl = (NewsInfoService) SpringContextUtil.getBean("newsInfoService");
	// 初始化实体类
	CaptureInfo captureInfoL = new CaptureInfo();
	NewsInfo newsInfo = new NewsInfo();

	// 调用Crawler的sdk
	public TutorialCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);

	}

	public void visit(Page page, CrawlDatums next) {
		// 取出配置的抓取信息页面
		List<CaptureInfo> info = getCaptureInfo.getInfo(captureInfoL);

		// 遍历循环取出抓取的配置内容去相应的网页抓取
		for (CaptureInfo captureInfo : info) {
			Date currentTime = new Date();
			// 日期格式化
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String dateString = formatter.format(currentTime);
			// 判断是否符合配置的规则
			if (page.matchUrl("" + captureInfo.getUrlDetail() + "")) {
				String title = page.select("" + captureInfo.getTitle() + "").first().text();
				String content = page.select("" + captureInfo.getContent() + "", 0).html();
				String from = captureInfo.getfromUrl();

				// System.out.println("title:" + title + "content:" + content);
				// 判断是否存在重复，如果重复就跳过直接执行下一条
				Integer isTitle = impl.isTitle(title);
				if (isTitle > 0) {
					continue;
				}

				// 给实体赋值
				String guid = UUID.randomUUID().toString();
				newsInfo.setInfoGuid(guid);
				newsInfo.setTitle(title);
				newsInfo.setContent(content);
				newsInfo.setInfoDate(dateString);
				newsInfo.setZhuanZai(from);
				newsInfo.setCategoryNum(captureInfo.getCategoryNum());
				newsInfo.setCategoryName(captureInfo.getUrlName());
				newsInfo.setUrl("/info/newsInfo.html?infoId=" + guid + "");

				// 插入到数据库
				Integer result = impl.addNewsInfo(newsInfo);
				if (result.equals(0)) {
					System.out.println("信息抓取失败");
					return;
				}

				// 插入内容到数据库
				Integer resultDetail = impl.addNewsDetail(newsInfo);
				if (resultDetail.equals(0)) {
					System.out.println("信息抓取失败");
					return;
				}

				System.out.println("信息抓取成功");

			}

		}

	}

}
