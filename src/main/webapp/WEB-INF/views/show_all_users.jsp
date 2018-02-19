<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<center>
		<h1>User ID Maintenance</h1>
		<p style="display:none">
			<a href="addHotel">Add New Hotel</a>
		</p>


		<hr />

		<table border="1">
			<tr>
				<th>User ID</th>
				<th>User Name</th>
				<th>Status</th>
				<th>Department</th>
				<th>Designation</th>				
			</tr>
			<c:forEach items="${userList}" var="usr">
				<c:url var="updateLink" value="updateUserInfo">
					<c:param name="userId" value="${usr.userId}" />
				</c:url>

				<c:url var="deleteLink" value="removeUser">
					<c:param name="userId" value="${usr.userId}" />
				</c:url>
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