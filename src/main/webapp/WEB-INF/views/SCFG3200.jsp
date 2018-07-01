<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/SCFG3100.js"></script>
<script>
	$(document).ready(function() {
		$("#atmInstallDate").datepicker();
	});
</script>
<style>
.required {
	color: red;
}

#side ul {
	height: 815px;
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
				<input id="checkPasswdValid" type="hidden" readonly="readonly"
					value="${testPasswdValid}">
				<input id="minpwdlen" type="hidden" readonly="readonly"
					value="${passMinSize}">
				<input id="maxpwdlen" type="hidden" readonly="readonly"
					value="${passMaxSize}">

				<form:form action="submit-add-new-atm" modelAttribute="atmDef">
					<table class="TableContent" width="80%" align="center">
						<%-- <tr>
							<th class="FormCellBGColor"><span class="required">*</span>ATM
								ID:</th>
							<td class="FormInputColor"><form:input type="text"
									name="pid" path="pid" required="true"></form:input></td>
						</tr> --%>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Hostname:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="64" name="machine" path="machine" required="true"></form:input></td>
						</tr>

						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Branch:</th>
							<td class="FormInputColor"><form:select path="branch">
									<form:options items="${branchOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>City:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="13" name="city" path="city" required="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>IP
								Address:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="127" name="remoteAddress" path="remoteAddress"
									required="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Comms
								Type:</th>
							<td class="FormInputColor"><form:select path="proto">
									<form:options items="${protoOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Remote
								Port:</th>
							<td class="FormInputColor"><form:select path="remotePort">
									<form:options items="${remotePortOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Local
								Port:</th>
							<td class="FormInputColor"><form:select path="localPort">
									<form:options items="${localPortOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Initiator:</th>
							<td class="FormInputColor"><form:select path="initiator">
									<form:options items="${initOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Format:</th>
							<td class="FormInputColor"><form:select path="format">
									<form:options items="${formatOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Denomination
								Value:</th>
							<td class="FormInputColor"><span>#1</span> <form:select
									path="d1val">
									<form:options items="${dvalOptions}" />
								</form:select>&nbsp;&nbsp; <span>#2</span> <form:select path="d2val">
									<form:options items="${dvalOptions}" />
								</form:select>&nbsp;&nbsp; <span>#3</span> <form:select path="d3val">
									<form:options items="${dvalOptions}" />
								</form:select>&nbsp;&nbsp; <span>#4</span> <form:select path="d4val">
									<form:options items="${dvalOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Street:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="18" name="street" path="street" required="true"></form:input></td>
						</tr>

						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>State:</th>
							<td class="FormInputColor"><form:select path="state">
									<form:options items="${stateOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Circuit:</th>
							<td class="FormInputColor"><form:select path="circuit">
									<form:options items="${circuitOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor">A/C No:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="16" name="acct" path="acct"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Tchar:</th>
							<td class="FormInputColor"><form:select path="tchar">
									<form:options items="${tcharOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Acquiring
								BIN:</th>
							<td class="FormInputColor"><form:input type="text"
									maxlength="15" name="loader" path="loader" required="true"></form:input></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Pin
								Block Format:</th>
							<td class="FormInputColor"><form:select path="pinblkFmt">
									<form:options items="${pinblkFmtOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>PCI
								Compliant:</th>
							<td class="FormInputColor"><form:select path="pciCompliant">
									<form:options items="${pciCompliantOptions}" />
								</form:select></td>
						</tr>
						<tr>
							<th class="FormCellBGColor"><span class="required">*</span>Capture
								Card:</th>
							<td class="FormInputColor"><form:select path="attribute">
									<form:options items="${cardCaptureOptions}" />
								</form:select></td>
						</tr>
					</table>
					<table align="center" width="80%">
						<tr>
							<td>
								<p>
									<input type="submit" class="button" id="btnAddNewUser"
										value="Add" /> <input type="reset" class="button"
										value="Reset" /> <a href="${uponelvl}"><input
										class="button" type="button" value="Return" /> </a>
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