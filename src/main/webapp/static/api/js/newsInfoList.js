/**
 * @add:各个页面需要的个性化js
 * @author:whai
 * @version:1.0
 */

// ====================begin feedBackManage============================
$(function(){
	//初始化
	var time=new Date();
	dgNewsLoad("../captureInfo/newsInfoList.do","","2010/10/10",time.toLocaleDateString());
	
})

function dgNewsLoad(url,title,startTime,endTime){	
	// 获取datagrid的实例
	/*var grid = $("#dg").datagrid();
　　　$.post(url,{	   
	    "title":title,
	    "startTime":startTime,
	    "endTime":endTime,
	    "page":1,
	    "rows":10,
　　　}, function (data) {
　			　grid.datagrid("loadData", data);
　　　}, 'json');*/
	
	$("#dg").datagrid({  
	    url:url,  
	    pageSize:10,  
	    queryParams: {  
	    	"title":title,
		    "startTime":startTime,
		    "endTime":endTime,
	    },  
	   
	});  
}




// 自定义选项列表操作
function formatOperation(val, row) {
	var content = "<option ></option>";
	content += "<option value=\"newsInfoModify.jsp?infoGuid=" + row.infoGuid
			+ "&param=1\" >修改新闻信息</option>";
	
	var html = "<select id=\"opr\" class=\"icon-item\"   onchange=\"oprFunc(this,'captureInfo')\">"
			+ content + "</select>";

	return html;

}



function searchNews() {
	var time=new Date();
	var statTime=$("#c_startTime").datebox("getValue");
	var endTime=$("#c_endTime").datebox("getValue");
	
	if (statTime==""||statTime==null) {
		statTime="2010/10/10";
	}
	if (endTime==""||endTime==null) {
		endTime=time.toLocaleDateString();
	}
	dgNewsLoad("../captureInfo/newsInfoList.do",$("#c_newsTitle").val(),statTime,endTime);
}

