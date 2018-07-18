<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<%-- <script
	src="${pageContext.request.contextPath}/resources/js/SCFG3700U.js"></script> --%>
<script>
	$(document).ready(function() {
		$("#atmInstallDate").datepicker();
	});
</script>
<style>
.required {
	color: red;
}

.hidden {
	display: none;
}

td>input {
	width: 98%;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="rightPart">
		<c:choose>
			<c:when test="${empty message}">
				<table width="80%" align="center">
					<tr>
						<td class="PageHeader">${functionDesc}</td>
					</tr>
				</table>
				<br>
				<form:form action="" modelAttribute="branch">
					<table class="TableContent" width="80%" align="center">
						<tr class="hidden">
							<th class="FormCellBGColor">ID:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchId" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Branch Name:</th>
							<td class="FormInputColor" colspan="3"><form:input
									type="text" path="branchName" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Address 1:</th>
							<td class="FormInputColor" colspan="3"><form:input
									type="text" path="branchAddr" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Address 2:</th>
							<td class="FormInputColor" colspan="3"><form:input
									type="text" path="branchAddr2" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">City:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchCity" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Region:</th>
							<td class="FormInputColor"><form:select path="region"
									disabled="true">
									<form:options items="${circuitOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Email 1:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchEmail1" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Email 2:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchEmail2" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Mobile:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchMobile" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Phone:</th>
							<td class="FormInputColor"><form:input type="text"
									path="branchPhone" readonly="true"></form:input></td>
						</tr>
						<tr class="hidden">
							<th class="FormCellBGColor">ISO:</th>
							<td class="FormInputColor"><form:input type="text"
									path="iso" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Officer Name 1:</th>
							<td class="FormInputColor"><form:input type="text"
									path="officerName1" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Officer Name 2:</th>
							<td class="FormInputColor"><form:input type="text"
									path="officerName2" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Custodian 1 Title:</th>
							<td class="FormInputColor"><form:input type="text"
									path="keyCustodian1Title" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Custodian 2 Title:</th>
							<td class="FormInputColor"><form:input type="text"
									path="keyCustodian2Title" required="true" readonly="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">Custodian 1 Name:</th>
							<td class="FormInputColor"><form:input type="text"
									path="keyCustodian1Name" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Custodian 2 Name:</th>
							<td class="FormInputColor"><form:input type="text"
									path="keyCustodian2Name" required="true" readonly="true"></form:input></td>
						</tr>
						<tr class="hidden">
							<th class="FormCellBGColor">Sequence:</th>
							<td class="FormInputColor"><form:input type="text"
									path="sequence" required="true" readonly="true"></form:input></td>
							<th class="FormCellBGColor">Sequence Length:</th>
							<td class="FormInputColor"><form:input type="text"
									path="sequenceLen" required="true" readonly="true"></form:input></td>
						</tr>
					</table>
					<table align="center" width="80%">
						<tr>
							<td>
								<p>
									<a href="${uponelvl}"><input class="button" type="button" value="Return" /> </a>
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