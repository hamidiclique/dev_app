<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
	<center>
		<h1>Add New Hotel</h1>

		<hr />

		<form:form action="saveHotelProcess" modelAttribute="htl">			
			<table>
				<tr>
					<th>Hotel Name:</th>
					<td><form:input type="text" name="hotelName" path="hotelName"></form:input></td>
				</tr>
				<tr>
					<th>Total Rooms:</th>
					<td><form:input type="number" name="totalRooms" path="totalRooms"></form:input></td>
				</tr>				
				<tr>
					<th>City:</th>
					<td><form:select path="city">
							<option value="">--Select--</option>
							<option value="DAC">Dhaka</option>
							<option value="CTG">Chittagong</option>
							<option value="ZYL">Sylhet</option>
							<option value="CXB">Cox's Bazar</option>
						</form:select></td>
				</tr>				
				<tr>
					<td></td>
					<td><input type="submit" value="Save" />&nbsp; <input type="reset" value="Reset" /></td>
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