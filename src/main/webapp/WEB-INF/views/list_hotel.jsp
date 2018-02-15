<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
	<center>
		<h1>Hotel Details</h1>
		<p>
			<a href="addHotel">Add New Hotel</a>
		</p>


		<hr />

		<table border="1">
			<tr>
				<th>Hotel</th>
				<th>Total Rooms</th>
				<th>City</th>
				<th>Status</th>				
			</tr>
			<c:forEach items="${htlList}" var="htl">
				<c:url var="updateLink" value="updateHotelData">
					<c:param name="hotelId" value="${htl.hotelId}" />
				</c:url>

				<c:url var="deleteLink" value="removeHotel">
					<c:param name="hotelId" value="${htl.hotelId}" />
				</c:url>
				<tr>
					<td>${htl.hotelName}</td>
					<td>${htl.totalRooms}</td>
					<td>${htl.city}</td>
					<td>${htl.status}</td>
					<td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure want to delete this Employee permanently?'))) return false">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</center>

	<hr />

</body>
</html>