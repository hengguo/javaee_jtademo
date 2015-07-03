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
	<h1>Welcome to the Flight Booking Service</h1>
	<p>We have the following Flights available now:</p>
    <p>Legs  --  TotalCost  -- TotalTravelTime </p>
    
		<c:forEach items="${flights}" var="flight">
		${flight.numberOfLegs} - $${flight.totalCost} - ${flight.totalTravelTime}
			
		</c:forEach>
	<p><a href="${ctx}/flights/searchView">Go Back</a>
</body>
</html>