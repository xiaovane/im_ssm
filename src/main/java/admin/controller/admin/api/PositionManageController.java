package admin.controller.admin.api;

import java.math.BigInteger;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.PageBean;
import admin.entity.api.FeedBack;
import admin.entity.api.Position;
import admin.util.ResultInfoUtil;
import api.user.service.PositionService;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/position")
public class PositionManageController {

	@Resource
	private PositionService apiService;

	/**
	 * 获取小区列表
	 * 
	 * @param page
	 * @param rows
	 * @param position
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/positionList")
	public String positionList(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, Position position,
			HttpServletResponse response) throws Exception {

		PageBean<Position> pageBean = new PageBean<Position>(Integer.parseInt(page), Integer.parseInt(rows));

		// 小区名查询
		pageBean = apiService.positionList(position, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取小区详细
	 * 
	 * @param position
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/positionContent")
	public String positionContent(Position position, HttpServletResponse response) throws Exception {
		Position positionDetail = apiService.positionContent(position);
		ResultInfoUtil.jsonObject(response, positionDetail);
		return null;
	}

	/**
	 * 修改
	 * 
	 * @param position
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/positionModify")
	public String positionModify(Position position, HttpServletResponse response) throws Exception {
		Integer result = apiService.positionModify(position);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");

		} else {
			ResultInfoUtil.resultSuccess(response, "success");
		}

		return null;
	}

	/**
	 * 新增小区信息
	 * 
	 * @param position
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/positionAdd")
	public void positionAdd(Position position, HttpServletResponse response) throws Exception {
		// 判断小区名字是否已经存在
		Integer isExitTitle = apiService.isExitTitle(position);
		if (!isExitTitle.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "isExit");
			return;
		}
		//新增小区
		position.setXiaoQuId(UUID.randomUUID().toString());
		Integer result = apiService.positionAdd(position);
		if (result.equals(0)) {
			ResultInfoUtil.resultSuccess(response, "false");

		} else {
			ResultInfoUtil.resultSuccess(response, "success");
		}

	}

	/**
	 * 批量删除小区信息
	 * 
	 * @param position
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/positionDelete")
	public void positionDelete(Position position, HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		String msg = request.getParameter("msg");
		String[] menu = msg.split(",");
		for (int i = 0; i < menu.length; i++) {
			position.setId(Integer.parseInt(menu[i]));
			Integer result = apiService.positionDelete(position);
			if (result.equals(0)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
		}

		ResultInfoUtil.resultSuccess(response, "success");
	}
}
