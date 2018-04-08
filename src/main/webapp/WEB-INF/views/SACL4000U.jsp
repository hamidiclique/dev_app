<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<script
	src="${pageContext.request.contextPath}/resources/js/SACL4000U.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>	
	<div class="rightPart">
		<c:choose>
			<c:when test="${empty message}">
				<table width="80%" align="center">
					<tr>
						<td class="PageHeader">${functionDesc}</td>
					</tr>
				</table>
				<br>
				<form:form action="submit-system-login-parameters-update"
					modelAttribute="dpf">
					<table class="TableContent" width="80%" align="center" id="myTable">
						<tr>
							<th class="HeaderTableData" style="display: none">Parameter
								ID</th>
							<th class="HeaderTableData">Parameter Description</th>
							<th class="HeaderTableData" width="20%">Parameter Value</th>
						</tr>
						<c:forEach items="${dpf.desisionParams}" var="dpvar"
							varStatus="loop">
							<tr>
								<td class="CellClass" style="display: none"><form:input
										path="desisionParams[${loop.index}].paramId" required="true"
										type="hidden" readonly="true"></form:input></td>
								<td class="CellClass" style="text-align: left">&nbsp;&nbsp;&nbsp;${dpvar.paramDesc}</td>
								<td class="FormInputColor"><form:input
										path="desisionParams[${loop.index}].paramValue"
										required="true" maxlength="3"></form:input></td>
							</tr>
						</c:forEach>
					</table>
					<br>
					<table width="80%" align="center">
						<tr>
							<td><input class="button" type="submit" value="Submit" /> <a
								href="${uponelvl}"><input class="button" type="button"
									value="Return" /></a></td>
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