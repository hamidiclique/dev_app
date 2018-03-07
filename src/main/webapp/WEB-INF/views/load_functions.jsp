<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACL demo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="modulemap" value="${modmap}" />
	<c:set var="functionmap" value="${funmap}" />
	<c:set var="modkey" value="${module}" />

	<center>
		<h1><c:out value="${modmap[modkey]}" /></h1>
		<a href="changePassword">Change Password</a><br> <a
			href="userLogout">Logout</a>

		<table class="TableContent" width="60%" align="center">
			<tr>
				<th class="HeaderTableData">S/NO</th>
				<th></th>
			</tr>
			<c:forEach items="${funlist}" var="fnc" varStatus="count">
				<tr>
					<c:url var="link" value="viewScreen">
						<c:param name="moduleId" value="${module}" />
						<c:param name="functionId" value="${fnc}" />
					</c:url>
					<c:set var="funkey">${fnc}</c:set>
					<td class="FormInputColor">${count.count}</td>
					<td class="FormInputColor"><a href="${contextPath}/${link}"><c:out
								value="${functionmap[funkey]}" /></a></td>
				</tr>
			</c:forEach>
		</table>

	</center>

</body>
</html>