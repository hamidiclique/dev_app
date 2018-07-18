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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body, html {
	height: 100%;
	margin: 0px;
}
</style>
</head>
<body>
	<div id="login-bg">
		<div id="login-element">
			<!-- <table width="60%" align="center">
				<tr>
					<td class="TitleHeader">Login</td>
				</tr>
			</table> -->
			<div>
				<form:form action="authLogin" modelAttribute="credentials">
					<div class="login-block">

						<div id="login-elem-title">Account Login</div>
						<form:input type="text" id="userId" path="userId"
							placeholder="Username"></form:input>

						<form:input type="password" id="password" placeholder="Password"
							path="password"></form:input>

						<button type="submit" value="Submit">Submit</button>

						<c:if test="${not empty cause}">
							<div class="error"><i class="fa fa-warning"></i>&nbsp;${cause}</div>
						</c:if>
						

					</div>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>