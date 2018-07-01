<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/checkbox.js"></script>	
<script type="text/javascript">

</script>
<style>
.required {
	color: red;
}
#side ul {
	height: 1000px;
}
</style>
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
		<form:form action="submit-function-group-maintenance-update"
			modelAttribute="ffMapDto" onsubmit="return countCheckboxes();">
			<table class="TableContent" width="80%" align="center">
				<tr style="display: none">
					<td><form:input type="text" path="module" hidden="true"
							readonly="true"></form:input></td>
					<td><form:input type="text" path="function" hidden="true"
							readonly="true"></form:input></td>
					<td><form:input type="text" path="screen" hidden="true"
							readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Function Group ID:</th>
					<td class="FormInputColor"><form:input type="text"
							name="functiongrpId" path="functiongrpId" required="true"
							readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Function Group Description:</th>
					<td class="FormInputColor"><form:input type="text"
							name="functiongrpDesc" path="functiongrpDesc" required="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Module Name:</th>
					<td class="FormInputColor">${module.moduleId}-${module.moduleDesc}</td>
				</tr>
			</table>
			<br>
			<table id="functionsTable" class="TableContent" width="80%" align="center">
				<tr>
					<th class="HeaderTableData">Function ID</th>
					<th class="HeaderTableData">Function Name</th>
					<!-- <th class="HeaderTableData">true / false</th> -->
					<th class="HeaderTableData">Administer</th>
				</tr>
				<c:forEach items="${allFunctions}" var="fun">
					<tr>
						<td class="FormInputColor"><div style="text-align: center">${fun.functionId}</div></td>
						<td class="FormInputColor">&nbsp;${fun.functionDesc}</td>
						<c:set var="functionKey">${fun.functionId}</c:set>
						<%-- <td><c:out value="${switchMap[functionKey]}" /></td> --%>
						<c:choose>
							<c:when test="${switchMap[functionKey]}">
								<td class="FormInputColor">
									<div style="text-align: center">
										<form:checkbox path="functionList" value="${functionKey}"
											checked="checked" />
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td class="FormInputColor">
									<div style="text-align: center">
										<form:checkbox path="functionList" value="${functionKey}" />
									</div>
								</td>
							</c:otherwise>
						</c:choose>
					</tr>
				</c:forEach>
			</table>			
			<table align="center" width="80%">
				<tr>
					<td>
						<p>
							<input type="submit" class="button" value="Update" /> <input
								type="button" class="button" onclick='selectAll()'
								value="Select All" /> <input type="button" class="button"
								onclick='unselectAll()' value="Unselect All" /> <a
								href="${uponelvl}"><input class="button" type="button"
								value="Return" /></a>
						</p>
					</td>
				</tr>
			</table>
		</form:form>
	</div>

</body>
</html>