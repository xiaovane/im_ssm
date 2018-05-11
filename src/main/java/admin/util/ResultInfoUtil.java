package admin.util;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import admin.entity.PageBean;

public class ResultInfoUtil {

	/*
	 * 提取的json的序列化
	 */
	public  static <T> void jsonPram( HttpServletResponse response,PageBean<T> pageBean) throws Exception{
		

		// 创建json对象
		JSONObject result = new JSONObject();
		// 设置json序列化日期格式
		JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
		// 禁止对象循环引用
		// 使用默认日期格式化
		String jsonStr = JSONObject.toJSONString(pageBean.getResult(), SerializerFeature.DisableCircularReferenceDetect,
				SerializerFeature.WriteDateUseDateFormat);
		// 得到json数组
		JSONArray array = JSON.parseArray(jsonStr);
		// 把结果放入json
		result.put("rows", array);
		result.put("total", pageBean.getTotal());
		// 返回
		ResponseUtil.write(response, result);
	}
	
	
	/**
	 * 返回成功
	 * @param response
	 * @throws Exception
	 */
	public static void resultSuccess(HttpServletResponse response,String msg) throws Exception{
		JSONObject result = new JSONObject();
		result.put(msg, true);
		ResponseUtil.write(response, result);
	}
	
	/**
	 * 获取实体对象详细信息
	 * @param response
	 * @param object
	 * @throws Exception
	 */
	public  static void jsonObject(HttpServletResponse response,Object object) throws Exception{		
		JSONObject result = new JSONObject();
		result.put("data", object);
		ResponseUtil.write(response, result);
	}
}
