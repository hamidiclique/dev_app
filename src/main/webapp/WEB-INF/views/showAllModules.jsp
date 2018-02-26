<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<center>
		<h1>Main Menu</h1>
		
		<hr />

		<table border="1">
			<tr>
				<th>S/No.</th>
				<th></th>								
			</tr>
			<c:forEach items="${userList}" var="usr">				
				<tr>
					<td>${usr.userId}</td>
					<td>${usr.userName}</td>
					<td>${usr.status}</td>
					<td>${usr.department}</td>
					<td>${usr.designation}</td>
					<td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure want to delete this Employee permanently?'))) return false">Delete</a></td>
				</tr>
			</c:forEach>
		</table>

	</center>

</body>
</html>