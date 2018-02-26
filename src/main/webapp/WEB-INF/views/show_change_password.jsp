<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ACL demo</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<script
	src="${pageContext.request.contextPath}/resources/js/change_password.js"></script>

</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<center>
		<h1>Change Password</h1>
		<!-- <a href="changePassword">Change Password</a><br> -->
		<a href="userLogout">Logout</a>
		<hr />

		<form:form action="" modelAttribute="cpdto">
			<table>
				<tr style="display:none">
					<th>User ID:</th>
					<td><form:input type="text" name="userId" path="userId"
							required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th>User Name:</th>
					<td><form:input type="text" name="userName" path="userName"
							required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th>Current Password:</th>
					<td><form:input type="password" name="password" path="password"
							required="true"></form:input></td>
				</tr>
				<tr>
					<th>New Password:</th>
					<td><form:input type="password" name="newPassword" path="newPassword" id="newPassword"
							required="true"></form:input></td>
				</tr>
				<tr>
					<th>Confirm New Password:</th>
					<td><form:input type="password" name="confirmPassword" path="confirmPassword" id="confirmPassword"
							required="true"></form:input><span
							id='message'></span></td>
				</tr>
				<tr>
						<td></td>
						<td><input type="submit" value="Save" id="btnChangePwd" />&nbsp;
							<input type="reset" value="Reset" /></td>
					</tr>
			</table>
		</form:form>
	</center>

</body>
</html>