<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/change_password.js"></script>

<style>
.error {
	color: red;
}
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
	<div class="rightPartNoMargin">
		<table width="90%" align="center">
			<tr>
				<td class="PageHeader">Change Password</td>
			</tr>			
		</table>
		<br>
		<c:if test="${not empty alert}">
			<div id="alert-div" class="visible" style="width:90%">
				<table align="center">
					<tr>
						<td width="98%">${alert}</td>
						<td><button onclick="fadeout()">X</button></td>
					</tr>
				</table>
			</div>
			<br>
		</c:if>		
		<input id="checkPasswdValid" type="hidden" readonly="readonly" value="${testPasswdValid}">
		<input id="minpwdlen" type="hidden" readonly="readonly"	value="${passMinSize}"> 
		<input id="maxpwdlen" type="hidden"	readonly="readonly" value="${passMaxSize}">
		
		<form:form action="handlePasswordChange" modelAttribute="cpdto">
			<table class="TableContent" width="90%" align="center">
				<tr style="display: none">
					<th class="FormCellBGColor">User ID:</th>
					<td class="FormInputColor"><form:input type="text"
							name="userId" path="userId" required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">User Name:</th>
					<td class="FormInputColor"><form:input type="text"
							name="userName" path="userName" required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor"><span class="required">*</span>Current
						Password:</th>
					<td class="FormInputColor"><form:input type="password"
							name="password" path="password" required="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor"><span class="required">*</span>New
						Password:</th>
					<td class="FormInputColor"><div class="tooltip">
							<form:input type="password" name="newPassword" path="newPassword"
								id="newPassword" required="true"></form:input>
							<c:if test="${testPasswdValid == 1}">
								<span class="tooltiptext">minimum&nbsp;<span>${passMinSize}</span>&nbsp;characters,
								must contain uppercase &amp; lowercase letters, a number &amp; a
								special character&nbsp;&nbsp;&nbsp;
							</span></c:if>
						</div> <br> <span class="hint" id="pwdHint"></span></td>
				</tr>
				<tr>
					<th class="FormCellBGColor"><span class="required">*</span>Confirm
						New Password:</th>
					<td class="FormInputColor"><form:input type="password"
							name="confirmPassword" path="confirmPassword"
							id="confirmPassword" required="true"></form:input><span
						id='message'></span></td>
				</tr>				
			</table>
			<br>
			<table width="90%" align="center">
				<tr>
					<td><input class="button" type="submit" value="Save"
						id="btnChangePwd" />&nbsp; <input class="button" type="reset"
						value="Reset" /></td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>