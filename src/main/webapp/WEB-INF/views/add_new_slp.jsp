<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

<style>
.required {
	color: red;
}
</style>

</head>

<body>
	
		<center>
			<h1>Add New System Login Parameter</h1>

			<hr />

			<form:form action="saveSysLoginParam" modelAttribute="slp">
				<table>
					<tr>
						<th><span class="required">*</span>Parameter Description:</th>
						<td><form:input type="text" name="paramDesc" path="paramDesc" maxlength="100"
								required="true"></form:input></td>
					</tr>
					<tr>
						<th><span class="required">*</span>Parameter Value:</th>
						<td><form:input type="text" name="paramValue" path="paramValue"
								required="true" maxlength="3" ></form:input></td>
					</tr>
					<tr>
						<td></td>
						<td><input type="submit" value="Save" id="btnAddLoginParam" />&nbsp;
							<input type="reset" value="Reset" /></td>
					</tr>					
				</table>
			</form:form>
		</center>


	<a style="display: none" href="listOfHotel">Back to List</a>

</body>
</html>