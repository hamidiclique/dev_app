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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">

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
				href="#">Change Password</a></th>
			<th class="TableBelowNavHeader" width="5%"><a href="logout">Logout</a></th>
		</tr>
	</table>

	<div class="rightPartNoMargin">
		<table class="TableContent" width="90%" align="center">
			<tr>
				<th class="FormCellBGColor">User Name :</th>
				<td class="FormInputColor">&nbsp;${cursor.userName}</td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Last Login :</th>
				<td class="FormInputColor">&nbsp;${cursor.lastLogin}</td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Last Password Change :</th>
				<td class="FormInputColor">&nbsp;${cursor.passwordCreDate}<br></td>
			</tr>
		</table>
		<br> <br>
		<table width="90%" align="center">
			<tr>
				<td colspan="2" class="item">This system is for the use of
					authorized users only. Individuals using this computer system
					without authority, or in excess of their authority, are subject to
					having all of their activities on this system monitored and
					recorded by system personnel. In the course of monitoring
					individuals improperly using this system, or in the course of
					system maintenance, the activities of authorized users may also be
					monitored. Anyone using this system expressly consents to such
					monitoring and is advised that if such monitoring reveals possible
					evidence of criminal activity, system personnel may provide the
					evidence of such monitoring to law enforcement officials</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td><a href="showModules"><input class="button"
						type="button" value="Continue" /></a></td>
			</tr>
		</table>
	</div>

</body>
</html>