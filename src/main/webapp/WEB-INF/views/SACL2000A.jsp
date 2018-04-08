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
	src="${pageContext.request.contextPath}/resources/js/SACL2000A.js"></script>
<style type="text/css">
#lstBox1, #lstBox2 {
	height: 200px;
	width: 180px;
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
		<form:form action="submit-user-role-maintenance-add"
			modelAttribute="rfMapDto" name="SACL2000A"
			onsubmit="return doUpdate();">
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
					<th class="FormCellBGColor">User Role ID:</th>
					<td class="FormInputColor"><form:input type="text"
							name="roleId" path="roleId" required="true"></form:input></td>
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
							<form:select path="leftoutFungrpList" id='lstBox1' name='lstBox1'>
								<c:forEach items="${outbox}" var="elem">
									<form:option value="${elem.functiongrpId}">${elem.functiongrpId}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</td>
					<td class="FormInputColor">
						<div align="center">
							<input type='button' id='btnAllRight'
								value='&nbsp;&gt;&gt;&nbsp;' /><br>
							<br> <input type='button' id='btnRight'
								value='&nbsp;&gt;&nbsp;' /><br>
							<br> <input type='button' id='btnLeft'
								value='&nbsp;&lt;&nbsp;' /><br>
							<br> <input type='button' id='btnAllLeft'
								value='&nbsp;&lt;&lt;&nbsp;' /><br>
							<br>
						</div>
					</td>
					<td class="FormInputColor">
						<div align="center">
							<form:select path="fungrpList" id='lstBox2' name='lstBox2'
								multiple="true">
								<c:forEach items="${inbox}" var="elem">
									<form:option value="${elem}">${elem}</form:option>
								</c:forEach>
							</form:select>
						</div>
					</td>
				</tr>
			</table>
			<table align="center" width="80%">
				<tr>
					<td>
						<p>
							<input type="submit" class="button" value="Add" />
							<a href="${uponelvl}"><input class="button" type="button" value="Return" /></a>
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
	</div>

</body>
</html>