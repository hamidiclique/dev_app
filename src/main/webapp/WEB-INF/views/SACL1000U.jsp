<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<title>ACL demo</title>
<!-- custom resources -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/checkbox.js"></script>	
<script type="text/javascript">

</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE" />

	<center>
		<h1>${functionDesc}</h1>
		<a href="changePassword">Change Password</a><br> <a
			href="userLogout">Logout</a>

		<form:form action="submit-function-group-maintenance-update" modelAttribute="ffMapDto" onsubmit="return countCheckboxes();">
			<table class="TableContent" width="50%">
				<tr style="display:none">					
					<td><form:input type="text" path="module" hidden="true" readonly="true"></form:input></td>
					<td><form:input type="text" path="function" hidden="true" readonly="true"></form:input></td>
					<td><form:input type="text" path="screen" hidden="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Function Group ID:</th>
					<td class="FormInputColor"><form:input type="text" name="functiongrpId"
							path="functiongrpId" required="true" readonly="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Function Group Description:</th>
					<td class="FormInputColor"><form:input type="text" name="functiongrpDesc"
							path="functiongrpDesc" required="true"></form:input></td>
				</tr>
				<tr>
					<th class="FormCellBGColor">Module Name:</th>
					<td class="FormInputColor">${module.moduleId}-${module.moduleDesc}</td>
				</tr>
			</table>
			<br>
			<table id="functionsTable" class="TableContent" width="50%">
				<tr>
					<th class="HeaderTableData">Function ID</th>
					<th class="HeaderTableData">Function Name</th>
					<!-- <th class="HeaderTableData">true / false</th> -->
					<th class="HeaderTableData">Administer</th>
				</tr>
				<c:forEach items="${allFunctions}" var="fun">
					<tr>
						<td class="FormInputColor"><div style="text-align:center">${fun.functionId}</div></td>
						<td class="FormInputColor">&nbsp;${fun.functionDesc}</td>
						<c:set var="functionKey">${fun.functionId}</c:set>
						<%-- <td><c:out value="${switchMap[functionKey]}" /></td> --%>
						<c:choose>
							<c:when test="${switchMap[functionKey]}">
								<td class="FormInputColor">
									<div style="text-align:center">
										<form:checkbox path="functionList" value="${functionKey}" checked="checked"/>
									</div>
								</td>
							</c:when>
							<c:otherwise>
								<td class="FormInputColor">
									<div style="text-align:center">
										<form:checkbox path="functionList" value="${functionKey}"/>
									</div>
								</td>
							</c:otherwise>
						</c:choose>						
					</tr>
				</c:forEach>	
			</table>
			<p>
				<input type="submit" class="button" value="Update" />
				<c:forEach items="${btnList}" var="btn">
					<c:choose>
						<c:when test="${btn.buttonDesc eq delete}">
							<a id="${btn.buttonDef}"
								onclick="if(!(confirm('Are you sure want to delete?'))) return false">${btn.buttonDesc}</a>
						</c:when>
						<c:otherwise>
							<a id="${btn.buttonDef}">${btn.buttonDesc}</a>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<input type="button" class="button" onclick='selectAll()' value="Select All" /> 
				<input type="button" class="button" onclick='unselectAll()' value="Unselect All" />
			</p>
		</form:form>
	</center>

</body>
</html>