package admin.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.GsonBuilder;

import admin.entity.api.CaptureInfo;
import admin.entity.api.GroupInfo;
import admin.entity.api.GroupUsers;
import admin.entity.api.Vote;
import admin.entity.api.VoteInfo;
import api.emchat.api.impl.EasemobSendMessage;
import api.infocapture.service.GetCaptureInfoService;
import api.infocapture.service.impl.TutorialCrawler;
import api.user.service.GroupApiService;
import api.user.service.UserApiService;
import api.vote.service.VoteService;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;

public class TaskUtil {

	private ScheduledExecutorService scheduExec;

	public long start;

	public String token = "";

	public TaskUtil() {
		this.scheduExec = Executors.newScheduledThreadPool(2);
		this.start = System.currentTimeMillis();
	}

	public void timerOne() {
		scheduExec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("===================新闻抓取任务开启========================");
				// 普通类调用service
				GetCaptureInfoService getCaptureInfo = (GetCaptureInfoService) SpringContextUtil
						.getBean("getCaptureInfoService");
				CaptureInfo captureI = new CaptureInfo();
				List<CaptureInfo> info = getCaptureInfo.getInfo(captureI);
				// 遍历循环取出抓取的配置内容去相应的网页抓取
				for (CaptureInfo captureInfo : info) {

					TutorialCrawler crawler = new TutorialCrawler("crawler", true);
					crawler.addSeed("" + captureInfo.getUrlList() + "");
					crawler.addRegex("" + captureInfo.getUrlDetail() + "");

					crawler.setThreads(30);
					try {
						crawler.start(1);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}, 2000, 500, TimeUnit.MILLISECONDS);
	}

	public void timerTwo() {
		scheduExec.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("===============================投票统计监测任务开始====================");
				// 普通类调用service
				VoteService voteService = (VoteService) SpringContextUtil.getBean("voteService");
				UserApiService userApiService = (UserApiService) SpringContextUtil.getBean("userApiService");
				GroupApiService apiGroupService = (GroupApiService) SpringContextUtil.getBean("groupApiService");
				EasemobSendMessage easemobSendMessage = new EasemobSendMessage();
				List<Vote> list = voteService.getVoteResultList();
				List<VoteInfo> listInfo;
				if (!list.equals(null)) {
					for (Vote vote : list) {
						// 获取其中投票的统计结果
						VoteInfo voteInfo = new VoteInfo();
						voteInfo.setVoteId(vote.getVoteId());
						voteInfo.setEndTime(vote.getEndTime());
						listInfo = voteService.resultStatic(voteInfo);
						// 根据下级群获取上级群
						GroupInfo detailGroup = apiGroupService.getDetailGroup(listInfo.get(0).getGroupId());
						// 获取得票最高的人，把他的权限置为1，并给群里发信息
						GroupUsers groupUsers = new GroupUsers();
						groupUsers.setGroupId(detailGroup.getTopGroupId());
						groupUsers.setUserId(listInfo.get(0).getChoiceId());
						groupUsers.setStatus(1);
						Integer resultStatus = apiGroupService.updateGroupUser(groupUsers);

						// 给群里发信息
						try {
							Msg msg = new Msg();
							MsgContent msgContent = new MsgContent();
							msgContent.type(MsgContent.TypeEnum.TXT).msg("恭喜ID为" + listInfo.get(0).getChoiceId()
									+ "的用户成功当选，拥有在" + detailGroup.getGroupNickName() + "投票选举的权限");
							UserName userName = new UserName();
							userName.add("" + listInfo.get(0).getGroupId() + "");
							Map<String, Object> ext = new HashMap<String, Object>();
							ext.put("test_key", "test_value");
							msg.from("admin").target(userName).targetType("users").msg(msgContent).ext(ext);
							System.out.println(new GsonBuilder().create().toJson(msg));
							Object result = easemobSendMessage.sendMessage(msg);
							System.out.println(result);
						} catch (Exception e) {
							e.printStackTrace();
						}

						// 更新数据库中的token
						token = UUID.randomUUID().toString();
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("userId", groupUsers.getUserId());
						map.put("token", token);

						Integer key = userApiService.updateToken(map);

					}
				}
			}
		}, 2000, 500, TimeUnit.MILLISECONDS);
	}

}
