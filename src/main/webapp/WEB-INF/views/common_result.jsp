<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<title>ACL demo</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/fade.css">

<style>
#alert-div {
	text-align: center;
	background-color: gold;
	font-family: Arial;
	font-size: 14px;
	font-weight: bold;
	color: #006699;
}
</style>
</head>
<body>
	<c:choose>
		<c:when test="${empty message}">
		</c:when>
		<c:otherwise>
			<div id="alert-div" class="visible">
				<table align="center" width="100%">
					<tr>
						<td width="98%">${message}</td>
						<td><button onclick="fadeout()">X</button></td>
					</tr>
				</table>
			</div>
			<a href="showModules"><input class="button" type="button"
				value="Return Home" /></a>
		</c:otherwise>
	</c:choose>

	<script>
		function fadeout() {
			var x = document.getElementById("alert-div");
			x.setAttribute("class", "hidden");
		}
	</script>

</body>
</html>