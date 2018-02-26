<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACL demo</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<center>
		<h1>System Login Parameters Maintenance</h1>
		
		<hr />

		<table border="1">
			<tr>
				<th>Parameter ID</th>
				<th>Description</th>
				<th>Value</th>				
			</tr>
			<c:forEach items="${slpList}" var="slp">
				<c:url var="updateLink" value="editLoginParamValues">
					<c:param name="paramId" value="${slp.paramId}" />
				</c:url>
				<tr>
					<td>${slp.paramId}</td>
					<td>${slp.paramDesc}</td>
					<td>${slp.paramValue}</td>					
					<td><a href="${contextPath}/${updateLink}">Update</a></td>
				</tr>
			</c:forEach>
		</table>

	</center>

</body>
</html>