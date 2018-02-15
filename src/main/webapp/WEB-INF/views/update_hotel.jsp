<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<center>
		<h1>Update Existing Hotel</h1>

		<hr />

		<form:form action="updateHotelProcess" modelAttribute="upHotel">
			<table>
				<tr>
					<th>Hotel Id:</th>
					<td><form:input type="text" name="hotelId" path="hotelId"
							value="${upHotel.hotelId}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th>Hotel Name:</th>
					<td><form:input type="text" name="hotelName" path="hotelName"
							value="${upHotel.hotelName}"></form:input></td>
				</tr>
				<tr>
					<th>Total Rooms:</th>
					<td><form:input type="number" name="totalRooms"
							path="totalRooms" value="${upHotel.totalRooms}"></form:input></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><form:input type="text" name="status" path="status"
							value="${upHotel.status}"></form:input></td>
				</tr>
				<tr>
					<th>City:</th>
					<td><form:input type="text" name="city" path="city"
							value="${upHotel.city}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update" /></td>
				</tr>
			</table>
		</form:form>
	</center>

	<hr />

	<a href="listOfHotel">Back to List</a>
	<script
		src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.1.1.min.js"></script>
</body>
</html>