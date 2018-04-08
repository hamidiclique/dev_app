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
	src="${pageContext.request.contextPath}/resources/js/SACL6000.js"></script>	
<script type="text/javascript">

</script>
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
		<form:form action="submit-view-active-users" modelAttribute="aldto" onsubmit="return countCheckboxes();">
			<table class="TableContent" width="80%" align="center">
				<tr style="display: none">
					<td><form:input type="text" path="module" hidden="true"
							readonly="true"></form:input></td>
					<td><form:input type="text" path="function" hidden="true"
							readonly="true"></form:input></td>
					<td><form:input type="text" path="screen" hidden="true"
							readonly="true"></form:input></td>
				</tr>				
			</table>
			<table id="" class="TableContent" width="80%" align="center">
				<tr>
					<th class="HeaderTableData"></th>
					<th class="HeaderTableData">User Name</th>
					<th class="HeaderTableData">Contact Number</th>
					<th class="HeaderTableData">Screen ID</th>
					<th class="HeaderTableData">Screen Description</th>
					<th class="HeaderTableData">Last Activity Time</th>					
					<th class="HeaderTableData">Batch-Run Indicator</th>
				</tr>
				<c:forEach items="${actvUsrLst}" var="actvusr">
					<tr>
						<td class="FormInputColor"><div style="text-align: center"><form:checkbox path="activeUserList" value="${actvusr.userId}" /></div></td>
						<td class="FormInputColor">&nbsp;${actvusr.userId}</td>
						<td class="FormInputColor">&nbsp;${actvusr.contactNumber}</td>
						<td class="FormInputColor">&nbsp;${actvusr.screenId}</td>
						<td class="FormInputColor">&nbsp;${actvusr.screenDesc}</td>
						<td class="FormInputColor">&nbsp;${actvusr.lastActiveTime}</td>
						<td class="FormInputColor">&nbsp;${actvusr.batchRunFlag}</td>
					</tr>
				</c:forEach>
			</table>			
			<table align="center" width="80%">
				<tr>
					<td>
						<p>
							<input type="submit" class="button" value="Force Logout User" /> <input type="button" class="button" onclick='selectAll()'
								value="Select All" /> <input type="button" class="button"
								onclick='unselectAll()' value="Unselect All" /> <a
								href="${uponelvl}"><input class="button" type="button"
								value="Return" /></a>
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