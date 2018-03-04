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
<script type="text/javascript">

</script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE"/>	
		
	<center>
		<h1>User ID Maintenance</h1>
		<a href="changePassword">Change Password</a><br> 
		<a href="userLogout">Logout</a>
		
		<p>Total: <c:out value="${listsize}"></c:out></p>
		<form:form action="submitUpdatedUserInfo" modelAttribute="functionGroup">
			<table>
				<tr>
					<th>Function Group ID: </th>
					<td><form:input type="text" name="functiongrpId" path="functiongrpId"
							required="true" readonly="true"></form:input></td>
				</tr>			
				<tr>			
					<th>Function Group Description: </th>
					<td><form:input type="text" name="functiongrpDesc" path="functiongrpDesc"
							required="true"></form:input></td>
				</tr>			
				<tr>			
					<th>Module Name: </th>
					<td>${module.moduleId}-${module.moduleDesc}</td>
				</tr>			
				<%-- 			<c:forEach items="${fungrpList}" var="fg">
				<c:url var="updateLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url>
				<c:url var="deleteLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url>
				<tr>
					<td><input type="radio" name="fgRadio" value="${fg.functiongrpId}"></td>
					<td>${fg.functiongrpId}</td>
					<td>${fg.functiongrpDesc}</td>					
					<td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?))) return false">Delete</a></td>
				</tr>
			</c:forEach> --%>
			</table>
		</form:form>
		<p>
			<c:forEach items="${btnList}" var="btn">
				<c:choose>
					<c:when test="${btn.buttonDesc eq delete}">
						<a id="${btn.buttonDef}" onclick="if(!(confirm('Are you sure want to delete?'))) return false">${btn.buttonDesc}</a>						
					</c:when>
					<c:otherwise>
						<a id="${btn.buttonDef}">${btn.buttonDesc}</a>
					</c:otherwise>
				</c:choose>				
			</c:forEach>
		</p>

	</center>

</body>
</html>