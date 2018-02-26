<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HelloWorld page</title>

<style>
.error {
	color: red;
}
</style>

</head>
<body>
	<center>

		<hr />
		<form:form action="authLogin" modelAttribute="credentials">
			<table>
				<tr>
					<th>User ID:</th>
					<td><form:input type="text" name="userId" path="userId"></form:input></td>
				</tr>
				<tr>
					<th>Password:</th>
					<td><form:input type="password" name="password"
							path="password"></form:input></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Submit" />&nbsp;
						<input type="reset" value="Reset" /></td>
				</tr>
				<tr>
					<td></td>
					<td class="error">${cause}</td>
				</tr>
			</table>
		</form:form>

	</center>
</body>
</html>