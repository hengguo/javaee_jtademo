<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function exporta(){
		var iWidth=850; //弹出窗口的宽度;
		var iHeight=850; //弹出窗口的高度;
		var iTop = (window.screen.availHeight-30-iHeight)/2; //获得窗口的垂直位置;
		var iLeft = (window.screen.availWidth-10-iWidth)/2; //获得窗口的水平位置;
		window.open('${ctx }/jasper/print?type=1',"","height="+iHeight+", width="+iWidth+", top="+iTop+", left="+iLeft+"toolbar=no,menubar=no,scrollbars=no, resizable=no,location=no, status=no"); 
	}
</script>
</head>
<body>
	<div class="easyui-layout" data-options="fit:true,border:false">
	
		<div
			data-options="region:'north',title:'查询条件',border:false,collapsible:false"
			style="height: 120px; overflow: hidden;">
			<form id="searchForm">
				<table class="table table-hover table-condensed"
					style="width: 100%; padding: 7px 80px 0px 80px">
					<tr>
						<td width="10%" align="right"><a href="#" id="query"
							class="easyui-linkbutton" iconCls="icon-search">查询</a></td>
					</tr>
					<tr>
						<td><input type="button" value="报表导出测试" onclick="exporta()"></input></td>
					</tr>
					
				</table>
			</form>
		</div>

	</div>
</body>
</html>