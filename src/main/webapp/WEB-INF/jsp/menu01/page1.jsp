<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../inc/header.jsp"%>
<%@ include file="../inc/resource.inc"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Flight Booking Service</title>
</head>
<body>
  <h1>Welcome to the Flight Booking Service</h1>
  <form action="${ctx}/flights/search" method="post">
    <table>
     <tr>
      <td width="10%">DepartFrom:</td>
      <td width="20%"><input type="text" name="departFrom"/></td>
      <td width="10%">ArriveAt:</td>   
      <td width="20%"><input type="text" name="arriveAt"/></td>
     </tr>
      <tr>
        <td width="10%">起始时间:</td>
        <td  width="20%"><input name="departOn" id="departOn" type="text" onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'arriveOn\')}'})" class="Wdate" /></td>
        <td width="10%">结束时间:</td>
        <td width="20%"><input name="arriveOn" id="arriveOn" type="text" onFocus="WdatePicker({minDate:'#F{$dp.$D(\'departOn\')}'})" class="Wdate" /></td>
      </tr>
    </table>
    <button type="submit" value="Search">Search</button>
  </form>
</body>
</html>