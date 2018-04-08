<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
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

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE" />
	<div class="rightPart">
	<table width="80%" align="center">
		<tr>
			<td class="PageHeader">${functionDesc}</td>
		</tr>
	</table>
	<br>
	<form:form action="submit-user-id-maintenance-update" modelAttribute="user">
		<table class="TableContent" width="80%" align="center">
			<tr>
				<th class="FormCellBGColor"><span class="required">*</span>User
					ID:</th>
				<td class="FormInputColor"><form:input type="text"
						name="userId" path="userId" required="true" readonly="true"></form:input></td>
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
			<tr>
		</table>
		<table align="center" width="80%">
			<tr>
				<td>
					<p>
						<input type="submit" class="button" id="" value="Update" />						
						<a href="${uponelvl}"><input class="button" type="button" value="Return" /></a>
					</p>
				</td>
			</tr>
		</table>
	</form:form>
	</div>

</body>
</html>