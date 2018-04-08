<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>ACL Demo</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/fade_effect.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fade.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/navbar.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/tooltip.css">

</head>
<body>
	<c:set var="uponelvl" scope="request" value="${header.referer}" />
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<table width="100%" class="TableNav" align="center">
		<tr>
			<td class=""><img
				src="${pageContext.request.contextPath}/resources/img/applogo.png"
				alt="logo" id="logo" title="app"></td>
		</tr>
	</table>
	<table width="100%" class="TableBelowNav" align="center">
		<tr>
			<c:choose>
				<c:when test="${empty cursor}">
					<th class="TableBelowNavHeader" width="85%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
				</c:when>
				<c:otherwise>
					<th class="TableBelowNavHeader" width="85%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Welcome,&nbsp;${cursor.userName}</th>
				</c:otherwise>
			</c:choose>
			<th class="TableBelowNavHeader" width="10%"><a
				href="changePassword">Change Password</a></th>
			<th class="TableBelowNavHeader" width="5%"><a href="logout">Logout</a></th>
		</tr>
	</table>