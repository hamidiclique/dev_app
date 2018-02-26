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
		<h1>Update Existing Login Parameter</h1>

		<hr />

		<form:form action="submitUpdatedLoginParams" modelAttribute="updateSlp">
			<table>
				<tr>
					<th>Param Id:</th>
					<td><form:input type="text" name="paramId" path="paramId"
							value="${updateSlp.paramId}" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th>Param Desc:</th>
					<td><form:input type="text" name="paramDesc" path="paramDesc"
							value="${updateSlp.paramDesc}"></form:input></td>
				</tr>	
				<tr>
					<th>Param Value:</th>
					<td><form:input type="text" name="paramValue" path="paramValue"
							value="${updateSlp.paramValue}"></form:input></td>
				</tr>				
				<tr>
					<td></td>
					<td><input type="submit" value="Update" /></td>
				</tr>
			</table>
		</form:form>
	</center>

	<hr />

</body>
</html>