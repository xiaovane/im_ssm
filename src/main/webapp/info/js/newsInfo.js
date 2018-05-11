/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

$(function(){
	// 初始化
	　$.post("../clientCaputeInfo/clientNewsDetail.do",{	   
	    "infoGuid":getUrlParam("id"),	  
　　　}, function (data) {
　			$("#title").html(data.data.title);
　			$("#zhuanZai").html(data.data.zhuanZai);
　			$("#infoDate").html(data.data.infoDate);
　			$("#content").html(data.data.content);
　			$("#author").html(data.data.author);
　　　}, 'json');
})


// 获取参数值
function getUrlParam(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
	var r = window.location.search.substr(1).match(reg); // 匹配目标参数
	if (r != null)
		return unescape(r[2]);
	return null; // 返回参数值
}

