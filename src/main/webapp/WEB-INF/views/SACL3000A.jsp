<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/add_new_user.js"></script>
<title>ACL demo</title>
<script>
	$(document).ready(function() {
		$("#accountExpDate").datepicker();
	});
</script>
<style>
.required {
	color: red;
}
</style>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE" />
	<table width="80%" align="center">
		<tr>
			<td class="PageHeader">${functionDesc}</td>
		</tr>
		<tr>
			<td><a href="changePassword">Change Password</a>&nbsp;&nbsp;&nbsp;<a
				href="userLogout">Logout</a></td>
		</tr>
	</table>
	<form:form action="submit-user-id-maintenance-add"
		modelAttribute="user">
		<table class="TableContent" width="80%" align="center">
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>User
					ID:</th>
				<td class="FormInputColor"><form:input type="text"
						name="userId" path="userId" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>User
					Name:</th>
				<td class="FormInputColor"><form:input type="text"
						name="userName" path="userName" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>Staff
					ID:</th>
				<td class="FormInputColor"><form:input type="text"
						name="staffId" path="staffId" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>New
					Password:</th>
				<td class="FormInputColor"><form:input type="password"
						name="password" id="password" path="password" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>Confirm
					Password:</th>
				<td class="FormInputColor"><form:input type="password"
						name="confirmpassword" id="confirmpassword" path=""
						required="true"></form:input><span id='message'></span></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Password Never Expires:</th>
				<td class="FormInputColor"><form:select path="passwordExpiry">
						<form:options items="${commonStatus}" />
					</form:select></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Status:</th>
				<td class="FormInputColor"><form:select path="status">
						<form:options items="${statusOptions}" />
					</form:select></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Department:</th>
				<td class="FormInputColor"><form:input type="text"
						name="department" path="department"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">View Sensitive Data:</th>
				<td class="FormInputColor"><form:select
						path="viewSensitiveData">
						<form:options items="${commonStatus}" />
					</form:select></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Designation:</th>
				<td class="FormInputColor"><form:input type="text"
						name="designation" path="designation"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Email:</th>
				<td class="FormInputColor"><form:input type="email"
						name="email" path="email"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Contact Number:</th>
				<td class="FormInputColor"><form:input type="text"
						name="contactNumber" path="contactNumber"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>User
					Role ID:</th>
				<td class="FormInputColor"><form:select path="userRoleId">
						<form:options items="${userRoleOptions}" />
					</form:select></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>Account
					Expiry Date:</th>
				<td class="FormInputColor"><form:input id="accountExpDate"
						name="accountExpDate" path="accountExpDate"></form:input>
					(MM/DD/YYYY)</td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>View
					PAN:</th>
				<td class="FormInputColor"><form:select path="viewPan">
						<form:options items="${panMaskOptions}" />
					</form:select></td>
			</tr>
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>Print
					PAN:</th>
				<td class="FormInputColor"><form:select path="printPan">
						<form:options items="${panMaskOptions}" />
					</form:select></td>
			</tr>
		</table>
		<table align="center" width="80%">
			<tr>
				<td>
					<p>
						<input type="submit" class="button" id="btnAddNewUser" value="Add" />
						<input type="reset" class="button" value="Reset" />
						<%--<c:forEach items="${btnList}" var="btn">
							<c:choose>
								<c:when test="${btn.buttonDesc eq delete}">
									<a id="${btn.buttonDef}"
										onclick="if(!(confirm('Are you sure want to delete?'))) return false">${btn.buttonDesc}</a>
								</c:when>
								<c:otherwise>
									<a id="${btn.buttonDef}">${btn.buttonDesc}</a>
								</c:otherwise>
							</c:choose>
						</c:forEach> --%>
					</p>
				</td>
			</tr>
		</table>
	</form:form>


	<a style="display: none" href="listOfHotel">Back to List</a>

</body>
</html>