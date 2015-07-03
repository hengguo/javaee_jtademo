
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp"%>
<%@ include file="inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
    $(function(){
    	$("#tabs").tabs('add',{
            title: "百度搜索",
            content: '<iframe style="width:100%;height:100%;" scrolling="auto" frameborder="0" src="http://www.baidu.com"></iframe>',
            closable: true,
            icon: ''
        });
    });
    </script>
<head>
<title>Insert title here</title>
</head>
<body class="easyui-layout">
  <div region="north" style="height: 80px;">
    <!-- 页面头部 -->
    <h1>***管理系统</h1>
  </div>

  <div region="west" split="true" style="width: 220px" title="导航菜单"> 页面左侧（菜单）</div>

  <div region="center">
    <div id="tabs" class="easyui-tabs" fit="true" border="false">
      <div title="欢迎使用" iconCls="icon-cancel">
        <h1 style="font-size: 24px;">欢迎！</h1>
        <h1 style="font-size: 24px; color: red;">Welcome ！</h1>
      </div>
    </div>
  </div>

  <div region="east" style="width: 100px;">页面右侧</div>
  <div region="south">页面底部</div>
</body>
</html>
