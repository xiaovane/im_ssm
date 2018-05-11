package admin.controller;

import org.apache.lucene.search.IndexSearcher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import admin.entity.WebUser;
import admin.entity.Comment;
import admin.lucene.WebUserIndex;
import admin.service.WebUserService;
import admin.service.CommentService;
import admin.util.PageUtil;
import admin.util.StringUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author whai
 * @Description 前台访问控制层
 * @Date 2017/4/21 09:11
 */
@Controller
@RequestMapping(value = "WebUser")
public class WebUserController {

    @Resource
    private WebUserService WebUserService;
    @Resource
    private CommentService commentService;
    @Resource
    private WebUserIndex WebUserIndex;

    // 请求详细信息
    @RequestMapping("/articles/{id}")
    public ModelAndView details(@PathVariable("id") Integer id,
                                HttpServletRequest request) {

        ModelAndView modelAndView = new ModelAndView();
        WebUser WebUser = WebUserService.getById(id); // 根据id获取

        // 获取关键字
        String keyWords = WebUser.getKeyWord();
        if (StringUtil.isNotEmpty(keyWords)) {
            String[] strArray = keyWords.split(" ");
            List<String> keyWordsList = StringUtil.filterWhite(Arrays
                    .asList(strArray));
            modelAndView.addObject("keyWords", keyWordsList);
        } else {
            modelAndView.addObject("keyWords", null);
        }

        modelAndView.addObject("WebUser", WebUser);
        WebUser.setClickHit(WebUser.getClickHit() + 1); // 将访问量加1
        WebUserService.updateWebUser(WebUser); // 更新

        // 查询评论信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("WebUserId", WebUser.getId());
        map.put("state", 1);
        List<Comment> commentList = commentService.getCommentData(map);

        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("commonPage", "foreground/WebUser/WebUserDetail.jsp");
        modelAndView.addObject("title", WebUser.getTitle() + " - ");

        // 存入上一篇和下一篇的显示代码
        modelAndView.addObject("pageCode", PageUtil.getPrevAndNextPageCode(
                WebUserService.getPrevWebUser(id), WebUserService.getNextWebUser(id),
                request.getServletContext().getContextPath()));

        modelAndView.setViewName("mainTemp");

        return modelAndView;
    }


    // 根据关键字查询信息
    @RequestMapping("/search")
    public ModelAndView search(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "page", required = false) String page,
            HttpServletRequest request) throws Exception {

        int pageSize = 10;
        ModelAndView modelAndView = new ModelAndView();
        List<WebUser> WebUserIndexList = WebUserIndex.searchWebUser(q);
        if(page == null) { //page为空表示第一次搜索
            page = "1";
        }
        int fromIndex = (Integer.parseInt(page) - 1) * pageSize; // 开始索引
        int toIndex = WebUserIndexList.size() >= Integer.parseInt(page) * pageSize ? Integer
                .parseInt(page) * pageSize
                : WebUserIndexList.size();
        modelAndView.addObject("WebUserIndexList", WebUserIndexList.subList(fromIndex, toIndex));
        modelAndView.addObject("pageCode", PageUtil.getUpAndDownPageCode(
                Integer.parseInt(page), WebUserIndexList.size(), q, pageSize,
                request.getServletContext().getContextPath()));
        modelAndView.addObject("q", q); // 用于数据的回显
        modelAndView.addObject("resultTotal", WebUserIndexList.size()); // 查询到的总记录数
        modelAndView.addObject("commonPage", "foreground/WebUser/searchResult.jsp");
        modelAndView.addObject("title", "搜索'" + q + "'的结果 - ");
        modelAndView.setViewName("mainTemp");
        return modelAndView;
    }
}
