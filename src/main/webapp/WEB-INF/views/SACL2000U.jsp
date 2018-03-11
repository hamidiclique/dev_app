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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script src="${pageContext.request.contextPath}/resources/js/select_box.js"></script>	
<style type="text/css">
	#lstBox1, #lstBox2 {
		height: 200px;
		width: 180px;
	}
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
	<form:form action="submit-user-role-maintenance-update" modelAttribute="rfMapDto" name="SACL2000U" onsubmit="return doUpdate();">
		<table class="TableContent" width="80%" align="center">
			<tr style="display: none">
				<td><form:input type="text" path="module" hidden="true"	readonly="true"></form:input></td>
				<td><form:input type="text" path="function" hidden="true" readonly="true"></form:input></td>
				<td><form:input type="text" path="screen" hidden="true" readonly="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">User Role ID:</th>
				<td class="FormInputColor"><form:input type="text"
						name="roleId" path="roleId" required="true"
						readonly="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">User Role Name:</th>
				<td class="FormInputColor"><form:input type="text"
						name="roleDesc" path="roleDesc" required="true"></form:input></td>
			</tr>			
		</table>
		<br>
		<table id="functionsTable" class="TableContent" width="80%"
			align="center">
			<tr>
				<th class="HeaderTableData" colspan=3>Function Group ID</th>				
			</tr>
			<tr>
				<td class="FormInputColor">
				<div align="center">
					<form:select path="leftoutFungrpList" id="lstBox1" name="lstBox1">
						<c:forEach items="${outbox}" var="elem">
							<form:option value="${elem}">${elem}</form:option>
						</c:forEach>
					</form:select>
				</div></td>
				<td class="FormInputColor">
					<div align="center">
						<input type="button" id="btnAllRight" value="&nbsp;&gt;&gt;&nbsp;" /><br><br>
						<input type="button" id="btnRight" value="&nbsp;&gt;&nbsp;" /><br><br>
						<input type="button" id="btnLeft" value="&nbsp;&lt;&nbsp;" /><br><br>
						<input type="button" id="btnAllLeft" value="&nbsp;&lt;&lt;&nbsp;" /><br><br>
					</div>
				</td>
				<td class="FormInputColor">
				<div align="center">
					<form:select path="fungrpList" id="lstBox2" name="lstBox2" multiple="true">
						<c:forEach items="${inbox}" var="elem">
							<form:option value="${elem}">${elem}</form:option>
						</c:forEach>
					</form:select>
				</div></td>
			</tr>
		</table>
		<table align="center" width="80%">
			<tr>
				<td>
					<p>						
						<input type="submit" class="button" value="Update" />
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

</body>
</html>