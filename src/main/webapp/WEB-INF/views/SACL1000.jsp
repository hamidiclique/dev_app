<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<script type="text/javascript">
	$(function(){
		console.log( "document loaded" );
    	<c:forEach items="${btnList}" var="btn">
			<c:if test="${btn.buttonDesc eq 'ADD'}">
				console.log("add btn found");
				var a = document.getElementById(${btn.buttonDef});
				a.href = "${pageContext.request.contextPath}/common?mid=${moduleId}&fid=${functionId}&sid=${btn.resultPage}&pid=";						
			</c:if>				
		</c:forEach>
		$('input[type="radio"]').click(function(){
			if ($(this).is(':checked'))
		    {
		    	<c:forEach items="${btnList}" var="btn">
		    		<c:choose>
						<c:when test="${btn.buttonDesc eq 'ADD'}">
							console.log("dont override add button link");
						</c:when>
						<c:otherwise>
							var b = document.getElementById(${btn.buttonDef});
							b.href = "${pageContext.request.contextPath}/common?mid=${moduleId}&fid=${functionId}&sid=${btn.resultPage}&pid="+$(this).val();
						</c:otherwise>
					</c:choose>	
				</c:forEach>
				
		    }
		  });
		});
</script>
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
		<tr>
			<td>Total: <c:out value="${listsize}"></c:out></td>
		</tr>
	</table>
	<table class="TableContent" width="80%" align="center">
		<tr>
			<th class="HeaderTableData">Select</th>
			<th class="HeaderTableData">Function Group ID</th>
			<th class="HeaderTableData">Function Group Description</th>
		</tr>
		<c:forEach items="${fungrpList}" var="fg">
			<%-- <c:url var="updateLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url>
				<c:url var="deleteLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url> --%>
			<tr>
				<td class="CellClass"><input type="radio" name="fgRadio" value="${fg.functiongrpId}"></td>
				<td class="CellClass">&nbsp;${fg.functiongrpId}</td>
				<td class="CellClass">&nbsp;${fg.functiongrpDesc}</td>
				<%-- <td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?))) return false">Delete</a></td> --%>
			</tr>
		</c:forEach>
	</table>
	<br><br>
	<table width="80%" align="center">
		<tr>
			<td><c:forEach items="${btnList}" var="btn">
					<c:choose>
						<c:when test="${btn.buttonDesc eq delete}">
							<a id="${btn.buttonDef}"
								onclick="if(!(confirm('Are you sure want to delete?'))) return false">
								<input class="button" type="button" value="${btn.buttonDesc}" />
							</a>
						</c:when>
						<c:otherwise>
							<a id="${btn.buttonDef}"> <input class="button" type="button"
								value="${btn.buttonDesc}" />
							</a>
						</c:otherwise>
					</c:choose>
				</c:forEach></td>
		</tr>
	</table>

</body>
</html>