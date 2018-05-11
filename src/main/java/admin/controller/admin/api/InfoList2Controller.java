package admin.controller.admin.api;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;

import admin.entity.PageBean;
import admin.entity.UserAdmin;
import admin.entity.api.InfoList;
import admin.entity.api.RadomNickName;
import admin.util.DateUtil;
import admin.util.MD5Util;
import admin.util.PathUtil;
import admin.util.ResultInfoUtil;
import admin.util.Validation;
import api.user.controller.UserOperationApiController;
import api.user.service.InfoListService;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/infoList")
public class InfoList2Controller {

	@Resource
	private InfoListService infoListService;
	private static final Logger logger = LoggerFactory.getLogger(UserOperationApiController.class);

	/**
	 * 获取常见问题和投诉好友问题列表
	 * 
	 * @param page
	 * @param rows
	 * @param infoList
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/baseQuestion")
	public String baseQuestion(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, InfoList infoList,
			HttpServletResponse response) throws Exception {

		PageBean<InfoList> pageBean = new PageBean<InfoList>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = infoListService.baseQuestion(infoList, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取常见问题和好友列表详细
	 * 
	 * @param response
	 * @param request
	 * @param infoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/baseQuestionDetail")
	public void baseQuestionDetail(HttpServletResponse response, HttpServletRequest request, InfoList infoList)
			throws Exception {

		try {
			InfoList list = infoListService.baseQuestionDetail(infoList);
			ResultInfoUtil.jsonObject(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改常见问题和投诉好友信息
	 * 
	 * @param response
	 * @param request
	 * @param infoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/baseQuestionUpdate")
	public void baseQuestionUpdate(HttpServletResponse response, HttpServletRequest request, InfoList infoList)
			throws Exception {

		try {

			infoList.setUrl("/info/infoList.html?infoid=" + infoList.getInfoId() + "");
			Integer result = infoListService.baseQuestionUpdate(infoList);
			if (result.equals(0)) {
				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 新增好友投诉问题或常见问题
	 * 
	 * @param response
	 * @param request
	 * @param infoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/baseQuestionAdd")
	public void baseQuestionAdd(HttpServletResponse response, HttpServletRequest request, InfoList infoList)
			throws Exception {

		try {
			// 判断昵称是否重复
			Integer isSameTitle = infoListService.isSameTitle(infoList);
			if (isSameTitle > 0) {
				ResultInfoUtil.resultSuccess(response, "same");
				return;
			}

			String guid = UUID.randomUUID().toString();
			infoList.setInfoId(guid);
			infoList.setUrl("/info/infoList.html?infoid=" + guid + "");
			Integer result = infoListService.baseQuestionAdd(infoList);
			if (result.equals(0)) {
				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 批量删除相关数据
	 * 
	 * @param response
	 * @param request
	 * @param infoList
	 * @throws Exception
	 */
	@RequestMapping(value = "/baseQuestionDelete")
	public void baseQuestionDelete(HttpServletResponse response, HttpServletRequest request, InfoList infoList)
			throws Exception {

		try {
			String msg = request.getParameter("msg");
			String[] menu = msg.split(",");
			for (int i = 0; i < menu.length; i++) {
				infoList.setId(Integer.parseInt(menu[i]));
				Integer result = infoListService.baseQuestionDelete(infoList);
				if (result.equals(0)) {

					ResultInfoUtil.resultSuccess(response, "false");
					return;
				}
			}

			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取随机图片和昵称列表
	 * 
	 * @param page
	 * @param rows
	 * @param radomNickName
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/radomImg")
	public String radomImg(@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "rows", required = false) String rows, RadomNickName radomNickName,
			HttpServletResponse response) throws Exception {

		PageBean<RadomNickName> pageBean = new PageBean<RadomNickName>(Integer.parseInt(page), Integer.parseInt(rows));

		pageBean = infoListService.radomImg(radomNickName, pageBean);
		ResultInfoUtil.jsonPram(response, pageBean);
		return null;
	}

	/**
	 * 获取随机昵称和图片详细
	 * 
	 * @param response
	 * @param request
	 * @param radomNickName
	 * @throws Exception
	 */
	@RequestMapping(value = "/radomImgDetail")
	public void radomImgDetail(HttpServletResponse response, HttpServletRequest request, RadomNickName radomNickName)
			throws Exception {

		try {
			RadomNickName list = infoListService.radomImgDetail(radomNickName);
			ResultInfoUtil.jsonObject(response, list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 修改随机昵称和图片
	 * 
	 * @param response
	 * @param request
	 * @param radomNickName
	 * @throws Exception
	 */
	@RequestMapping(value = "/radomImgUpdate")
	public void radomImgUpdate(@RequestParam(value = "image", required = false) MultipartFile imageFile,
			@RequestParam(value = "title", required = false) String title,
			@RequestParam(value = "guid", required = false) String guid, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		try {
			RadomNickName radomNickName = new RadomNickName();
			String imageUrl = "/userImages/radom/";
			String imagePath = request.getServletContext().getRealPath("/") + imageUrl;
			String returnStr = Validation.uploadFile(imagePath, imageFile);

			if ("false".equals(returnStr)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}

			// 上传成功后把图片路径保存到数据库中
			radomNickName.setImage(imageUrl + returnStr);
			radomNickName.setGuid(guid);
			radomNickName.setNickName(title);
			// //判断昵称是否重复
			// Integer isSameNickName =
			// infoListService.isSameNickName(radomNickName);
			// if (isSameNickName>0) {
			// ResultInfoUtil.resultSuccess(response, "same");
			// return;
			// }

			Integer result = infoListService.radomImgUpdate(radomNickName);
			if (result.equals(0)) {
				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 随机图片和昵称
	 * 
	 * @param response
	 * @param request
	 * @param radomNickName
	 * @throws Exception
	 */
	@RequestMapping(value = "/radomImgAdd")
	public void radomImgAdd(@RequestParam(value = "image", required = false) MultipartFile imageFile,
			@RequestParam(value = "title", required = false) String title, HttpServletResponse response,
			HttpServletRequest request) throws Exception {

		try {
			RadomNickName radomNickName = new RadomNickName();
			radomNickName.setNickName(title);
			// 判断昵称是否重复
			Integer isSameNickName = infoListService.isSameNickName(radomNickName);
			if (isSameNickName > 0) {
				ResultInfoUtil.resultSuccess(response, "same");
				return;
			}

			String imageUrl = "/userImages/radom/";
			String imagePath = request.getServletContext().getRealPath("/") + imageUrl;
			String returnStr = Validation.uploadFile(imagePath, imageFile);

			if ("false".equals(returnStr)) {

				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}

			// 上传成功后把图片路径保存到数据库中
			radomNickName.setGuid(UUID.randomUUID().toString());
			radomNickName.setImage(imageUrl + returnStr);
			Integer result = infoListService.radomImgAdd(radomNickName);
			if (result.equals(0)) {
				ResultInfoUtil.resultSuccess(response, "false");
				return;
			}
			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 批量删除
	 * @param response
	 * @param request
	 * @param radomNickName
	 * @throws Exception
	 */
	@RequestMapping(value = "/radomImgDelete")
	public void radomImgDelete(HttpServletResponse response, HttpServletRequest request, RadomNickName radomNickName)
			throws Exception {

		try {
			String msg = request.getParameter("msg");
			String[] menu = msg.split(",");
			for (int i = 0; i < menu.length; i++) {
				radomNickName.setId(Integer.parseInt(menu[i]));
				Integer result = infoListService.radomImgDelete(radomNickName);
				if (result.equals(0)) {

					ResultInfoUtil.resultSuccess(response, "false");
					return;
				}
			}

			ResultInfoUtil.resultSuccess(response, "success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
