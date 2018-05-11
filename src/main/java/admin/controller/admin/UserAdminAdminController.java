package admin.controller.admin;

import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import admin.entity.UserAdmin;
import admin.service.UserAdminService;
import admin.util.DateUtil;
import admin.util.MD5Util;
import admin.util.PathUtil;
import admin.util.ResponseUtil;

/**
 * @author whai
 * @Description 博主信息管理控制层
 * @Date 2017/4/20 10:55
 */
@Controller
@RequestMapping(value = "/admin/UserAdmin")
public class UserAdminAdminController {

    @Resource
    private UserAdminService UserAdminService;

    //获取管理员相关信息
    @RequestMapping(value = "getUserAdminInfo")
    public String getUserAdminData(HttpServletResponse response,UserAdmin userAdmin) throws Exception {
        UserAdmin UserAdmin = UserAdminService.getUserAdminData(userAdmin);
        String jsonStr = JSONObject.toJSONString(UserAdmin);
        JSONObject object = JSONObject.parseObject(jsonStr);
        ResponseUtil.write(response,object);
        return null;
    }

    //更新管理员信息
    @RequestMapping(value = "saveUserAdmin",method = RequestMethod.POST)
    public String saveUserAdmin(@RequestParam(value = "imageFile",required = false) MultipartFile imageFile, UserAdmin UserAdmin,
                              HttpServletResponse response) throws Exception {
        //判断是否有上图片 有就更新
        if(!imageFile.isEmpty()){
            String filePath = PathUtil.getRootPath(); //获取服务器根路径
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "/src/main/webapp/static/userImages/" + imageName));
            UserAdmin.setImageName(imageName);
        }
        int resultTotal = UserAdminService.updateUserAdmin(UserAdmin);
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
    
    //新增管理员信息
    @RequestMapping(value = "addUserAdmin",method = RequestMethod.POST)
    public void addUserAdmin(@RequestParam(value = "imageFile",required = false) MultipartFile imageFile, UserAdmin UserAdmin,
                              HttpServletResponse response) throws Exception {
    	JSONObject result = new JSONObject();
    	//判断用户是否存在
    	Integer isAdmin=UserAdminService.isExitAdmin(UserAdmin);
    	if (isAdmin>0) {
    		 result.put("isExit", false);
    		 return;
		}
        //判断是否有上图片 有就更新
        if(!imageFile.isEmpty()){
            String filePath = PathUtil.getRootPath(); //获取服务器根路径
            String imageName = DateUtil.getCurrentDateStr() + "." + imageFile.getOriginalFilename().split("\\.")[1];
            imageFile.transferTo(new File(filePath + "/src/main/webapp/static/userImages/" + imageName));
            UserAdmin.setImageName(imageName);
        }
        
        UserAdmin.setPassword(MD5Util.md5(UserAdmin.getPassword(), "xp"));
        int resultTotal = UserAdminService.addAdmin(UserAdmin);
        
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
       
    }

    //更新博主密码
    @RequestMapping(value = "modtifyPassword",method = RequestMethod.POST)
    public String modityUserAdminPassword(UserAdmin UserAdmin,HttpServletResponse response) throws Exception {
        //加密
        String newPassword = MD5Util.md5(UserAdmin.getPassword(),"xp");
        UserAdmin.setPassword(newPassword);
        int resultTotal = UserAdminService.updateUserAdmin(UserAdmin);
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }
}
