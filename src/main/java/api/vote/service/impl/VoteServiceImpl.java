package api.vote.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import admin.entity.PageBean;
import admin.entity.api.Vote;
import admin.entity.api.VoteChoice;
import admin.entity.api.VoteInfo;
import api.vote.dao.VoteDao;
import api.vote.service.VoteService;

@Service("voteService")
public class VoteServiceImpl implements VoteService {

	@Resource
	private VoteDao voteDao;

	public Integer ctrateVote(Vote vote) {
		return voteDao.ctrateVote(vote);
	}

	public Integer createVoteChoice(VoteChoice voteChoice) {
		return voteDao.createVoteChoice(voteChoice);
	}

	public Integer addVoteInfo(VoteInfo voteInfo) {
		return voteDao.addVoteInfo(voteInfo);
	}

	public List<VoteInfo> voteStatistics(VoteInfo voteInfo) {
		return voteDao.voteStatistics(voteInfo);
	}

	public List<VoteInfo> voteInfoList(Map<String, Object> map) {
		return voteDao.voteInfoList(map);
	}

	public Integer selectVoteIs(VoteInfo voteInfo) {
		return voteDao.selectVoteIs(voteInfo);
	}

	public Integer selectVoteInfoIs(VoteInfo voteInfo) {
		return voteDao.selectVoteInfoIs(voteInfo);
	}

	@Override
	public PageBean<Vote> voteInfoList(Vote vote, PageBean<Vote> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("vote", vote);
		// 总记录放入pageBean
		pageBean.setTotal(voteDao.getTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(voteDao.voteInfoListAdmin(map));
		return pageBean;
	}

	@Override
	public Vote voteInfoDetail(Vote vote) {
		return voteDao.voteInfoDetail(vote);
	}

	@Override
	public Integer voteInfoModify(Vote vote) {
		return voteDao.voteInfoModify(vote);
	}

	@Override
	public PageBean<Vote> voteItem(VoteChoice voteChoice, PageBean<Vote> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("voteChoice", voteChoice);
		// 总记录放入pageBean
		pageBean.setTotal(voteDao.getvoteItemTotal(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(voteDao.voteItem(map));
		return pageBean;
	}

	@Override
	public Integer ItemSave(VoteChoice vote) {
		return voteDao.ItemSave(vote);
	}

	@Override
	public PageBean<VoteInfo> voteItemStatics(VoteInfo voteChoice, PageBean<VoteInfo> pageBean) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 设置查询条件
		map.put("voteItemStatic", voteChoice);
		// 总记录放入pageBean
		pageBean.setTotal(voteDao.getvoteItemStatic(map));
		map.put("start", pageBean.getStart());
		map.put("end", pageBean.getEnd());
		// 把分页结果放入pageBean
		pageBean.setResult(voteDao.voteItemStatic(map));
		return pageBean;
	}

	@Override
	public Integer isExitVote(Vote vote) {
		return voteDao.isExitVote(vote);
	}

	@Override
	public List<VoteInfo> resultStatic(VoteInfo voteInfo) {
		return voteDao.resultStatic(voteInfo);
	}

	@Override
	public List<Vote> getVoteResultList() {
		return voteDao.getVoteResultList();
	}

	

}
