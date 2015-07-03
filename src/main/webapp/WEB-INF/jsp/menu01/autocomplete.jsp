<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flights List.</title>
</head>

<body>
<div align="center">
	这个例子 是由easyui combobox实现的， 使用的是combobox每次查询都向后台(mode:'remote')请求，返回的结果最大数是10  (在后台记录为8万条的情况下  速度是非常快的)</br></br></br></br>

	<input name="type" id="tt" class="easyui-combobox"
           			   data-options="valueField: 'value', textField: 'text', url: '${ctx}/code/getList',hasDownArrow:false,mode:'remote'">

</div>
</body>
</html>