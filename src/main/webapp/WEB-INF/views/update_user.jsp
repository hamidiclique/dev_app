<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
	
<style>
.required {
	color: red;
}
</style>

<script>
	$(document).ready(function() {
		$("#accountExpDate").datepicker();
	});
</script>

</head>

<body>
	<center>
		<h1>Update Existing User</h1>

		<hr />

		<form:form action="submitUpdatedUserInfo" modelAttribute="upUser">
			<table>
				<tr>
					<th><span class="required">*</span>User ID:</th>
					<td><form:input type="text" name="userId" path="userId"
							required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th><span class="required">*</span>User Name:</th>
					<td><form:input type="text" name="userName" path="userName"
							required="true"></form:input></td>
				</tr>
				<tr>
					<th><span class="required">*</span>Staff ID:</th>
					<td><form:input type="text" name="staffId" path="staffId"
							required="true"></form:input></td>
				</tr>
				<tr>
					<th>Password Never Expires:</th>
					<td><form:select path="passwordExpiry">
							<form:options items="${commonStatus}" />
						</form:select></td>
				</tr>
				<tr>
					<th>Status:</th>
					<td><form:select path="status">
							<form:options items="${statusOptions}" />	
						</form:select></td>
				</tr>
				<tr>
					<th>Department:</th>
					<td><form:input type="text" name="department"
							path="department"></form:input></td>
				</tr>
				<tr>
					<th>View Sensitive Data:</th>
					<td><form:select path="viewSensitiveData">
							<form:options items="${commonStatus}" />
						</form:select></td>
				</tr>
				<tr>
					<th>Designation:</th>
					<td><form:input type="text" name="designation"
							path="designation"></form:input></td>
				</tr>
				<tr>
					<th>Email:</th>
					<td><form:input type="email" name="email" path="email"></form:input></td>
				</tr>
				<tr>
					<th>Contact Number:</th>
					<td><form:input type="text" name="contactNumber"
							path="contactNumber"></form:input></td>
				</tr>
				<tr>
					<th><span class="required">*</span>User Role ID:</th>
					<td><form:select path="userRoleId">
							<form:options items="${userRoleOptions}" />
						</form:select></td>
				</tr>
				<tr>
					<th><span class="required">*</span>Account Expiry Date:</th>
					<td><form:input id="accountExpDate" name="accountExpDate"
							path="accountExpDate"></form:input> (MM/DD/YYYY)</td>
				</tr>
				<tr>
					<th><span class="required">*</span>View PAN:</th>
					<td><form:select path="viewPan">
							<form:options items="${panMaskOptions}" />
						</form:select></td>
				</tr>
				<tr>
					<th><span class="required">*</span>Print PAN:</th>
					<td><form:select path="printPan">
							<form:options items="${panMaskOptions}" />
						</form:select></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Update" id="" />&nbsp;
						<a href="user-id-maintenance">Return</a></td>
				</tr>
			</table>
		</form:form>
	</center>
	
</body>
</html>