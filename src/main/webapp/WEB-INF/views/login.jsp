<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><%@ page
	language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo App</title>
<link href="${pageContext.request.contextPath}/resources/css/table.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/login_page.css"
	rel="stylesheet">

<style>
.error {
	color: red;
}
</style>

</head>
<body>	
		<table width="60%" align="center">
			<tr>
				<td class="TitleHeader" >Login</td>
			</tr>			
		</table>
		<form:form action="authLogin" modelAttribute="credentials">
			<div class="login-block">

				<form:input type="text" id="userId" path="userId"
					placeholder="Username"></form:input>

				<form:input type="password" id="password" placeholder="Password"
					path="password"></form:input>

				<button type="submit" value="Submit">Submit</button>

				<p class="error">${cause}</p>

			</div>
		</form:form>	
</body>
</html>