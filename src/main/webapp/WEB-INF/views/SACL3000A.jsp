<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/SACL3000A.js"></script>

<script>
	$(document).ready(function() {
		$("#accountExpDate").datepicker();
	});
</script>
<style>
.required {
	color: red;
}
.hint {
	display: inline;
    font-weight: bold;
    color: #ff5722;
}
</style>
</head>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE" />
	<div class="rightPart">
		<c:choose>
			<c:when test="${empty message}">
				<table width="80%" align="center">
					<tr>
						<td class="PageHeader">${functionDesc}</td>
					</tr>
				</table>
				<br>
				<input id="checkPasswdValid" type="hidden" readonly="readonly" value="${testPasswdValid}">		
				<input id="minpwdlen" type="hidden" readonly="readonly" value="${passMinSize}">
				<input id="maxpwdlen" type="hidden" readonly="readonly" value="${passMaxSize}">
				
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
							<td class="FormInputColor"><div class="tooltip">
									<form:input type="password" name="password" id="password"
										path="password" required="true"></form:input>
									<c:if test="${testPasswdValid == 1}">
										<span class="tooltiptext">minimum&nbsp;<span>${passMinSize}</span>&nbsp;characters,
											must contain uppercase &amp; lowercase letters, a number
											&amp; a special character&nbsp;&nbsp;&nbsp;
									</span></c:if>
								</div> <br>
							<span class="hint" id="pwdHint"></span></td>
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
							<td class="FormInputColor"><form:select
									path="passwordExpiry">
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
									<a href="${uponelvl}"><input class="button" type="button" value="Return" /></a>
								</p>
							</td>
						</tr>
					</table>
				</form:form>
			</c:when>
			<c:otherwise>
				<div id="alert-div" class="visible">
					<table align="center" width=100%">
						<tr>
							<td width="98%">${message}</td>
							<td><button onclick="fadeout()">X</button></td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>