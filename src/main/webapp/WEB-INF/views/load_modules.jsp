<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACL demo</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="modulemap" value="${modmap}" />


	<center>
		<h1>Main Menu</h1>
		<a href="changePassword">Change Password</a><br> <a
			href="userLogout">Logout</a>
		<hr />

		<table border="1">
			<tr>
				<th>S/NO</th>
				<th></th>
			</tr>
			<c:forEach items="${funmodmap}" var="funmodmap" varStatus="count">
				<tr>
					<c:url var="link" value="viewFunctions">
						<c:param name="moduleId" value="${funmodmap.key}" />
					</c:url>
					<c:set var="moduleKey">${funmodmap.key}</c:set>
					<td>${count.count}</td>
					<td><a href="${contextPath}/${link}"><c:out
								value="${modulemap[moduleKey]}" /></a></td>
				</tr>
			</c:forEach>
		</table>

	</center>

</body>
</html>