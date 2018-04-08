<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	href="${pageContext.request.contextPath}/resources/css/table.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fade.css">

<style type="text/css">
</style>
</head>
<body>
	<table width="100%" class="TableNav" align="center">
		<tr>
			<td class=""><img
				src="${pageContext.request.contextPath}/resources/img/applogo.png"
				alt="logo" id="logo" title="app"></td>
		</tr>
	</table>
	<table width="100%" class="TableBelowNav" align="center">
		<tr>
			<th class="TableBelowNavHeader" width="85%">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
			<th class="TableBelowNavHeader" width="10%"></th>
			<th class="TableBelowNavHeader" width="5%"><a href="logout">Logout</a></th>
		</tr>
	</table>
	<div class="rightPartNoMargin">
		<div id="multi-login-div" class="visible">
			<table align="center">
				<tr>
					<td>${message}</td>
				</tr>
			</table>
		</div>
		<form:form action="makeOtherSessionsInvalid" modelAttribute="usdto">
			<table class="" width="90%" align="center">
				<tr style="display: none">
					<th class="FormCellBGColor">User ID:</th>
					<td class=""><form:input type="hidden" name="userId"
							path="userId" required="true" readonly="true"></form:input></td>
				</tr>
				<tr style="display: none">
					<th class="FormCellBGColor">Existing Session ID:</th>
					<td class=""><form:input type="hidden" name="exSessionId"
							path="exSessionId" required="true" readonly="true"></form:input></td>
				</tr>
				<tr style="display: none">
					<th class="FormCellBGColor">New Session ID:</th>
					<td class=""><form:input type="hidden" name="sessionId"
							path="sessionId" required="true" readonly="true"></form:input></td>
				</tr>
			</table>
			<br>
			<table width="90%" align="center">
				<tr>
					<td><button class="button" type="submit" value="Submit">Continue</button>&nbsp;
						<a href="logout"><input class="button" type="button"
							value="Abort" /></a></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>