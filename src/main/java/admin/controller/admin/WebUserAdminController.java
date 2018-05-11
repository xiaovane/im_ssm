package admin.controller.admin;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin.entity.WebUser;
import admin.entity.PageBean;
import admin.lucene.WebUserIndex;
import admin.service.WebUserService;
import admin.service.CommentService;
import admin.util.ResponseUtil;

/**
 * @author whai
 * @Description 管理员Controller层
 */
@Controller
@RequestMapping("/admin/WebUser")
public class WebUserAdminController {

    @Resource
    private WebUserService WebUserService;
    @Resource
    private CommentService commentService;
    @Resource
    private WebUserIndex WebUserIndex;
    //后台分页查询信息
    @RequestMapping("/listWebUser")
    public String listWebUser(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            WebUser s_WebUser,
            HttpServletResponse response) throws Exception {

        PageBean<WebUser> pageBean = new PageBean<WebUser>(Integer.parseInt(page), Integer.parseInt(rows));

        pageBean = WebUserService.listWebUser(s_WebUser.getTitle(), pageBean);

        //创建json对象
        JSONObject result = new JSONObject();
        //设置json序列化日期格式
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //禁止对象循环引用
        //使用默认日期格式化
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        //得到json数组
        JSONArray array = JSON.parseArray(jsonStr);
        //把结果放入json
        result.put("rows", array);
        result.put("total", pageBean.getTotal());
        //返回
        ResponseUtil.write(response, result);
        return null;
    }

    //更新或者新增
    @RequestMapping(value = "/save")
    public String saveWebUser(WebUser WebUser,HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if(WebUser.getId()!=null){
            //更新操作
            resultTotal = WebUserService.updateWebUser(WebUser);
            //更新索引
            WebUserIndex.updateIndex(WebUser);
        }else{
            //新增操作
            resultTotal = WebUserService.saveWebUser(WebUser);
            //添加索引
            WebUserIndex.addIndex(WebUser);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    //删除
    @RequestMapping(value = "delete")
    public String deleteWebUser(@RequestParam("ids")String ids,HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for(int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            //先删除所关联的评论
            commentService.deleteCommentByWebUserId(id);
            WebUserService.deleteWebUser(id);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    //通过id获取
    @RequestMapping(value = "get")
    public String getById(@RequestParam("id") String id,HttpServletResponse response) throws Exception {

        WebUser WebUser = WebUserService.getById(Integer.parseInt(id));
        String jsonStr = JSONObject.toJSONString(WebUser);
        JSONObject result = JSONObject.parseObject(jsonStr);
        ResponseUtil.write(response, result);
        return null;
    }

}
