<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<div class="rightPart">
		<div id="alert-div" class="visible">
			<table align="center" width=100%">
				<tr>
					<td width="98%">${message}</td>
					<td><button onclick="fadeout()">X</button></td>
				</tr>
			</table>
		</div>
		<br>
		<table align="center" width="80%">
			<tr>
				<td>
					<p>
						<a href="showModules"><input class="button"	type="button" value="Continue" /></a>
					</p>
				</td>
			</tr>
		</table>
	</div>
</body>
</html>