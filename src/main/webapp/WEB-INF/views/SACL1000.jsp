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
	<c:set var="delete" value="DELETE"/>	
		
	<center>
		<h1>User ID Maintenance</h1>
		<a href="changePassword">Change Password</a><br> 
		<a href="userLogout">Logout</a>
		
		<p>Total: <c:out value="${listsize}"></c:out></p>
		<table border="1">
			<tr>
				<th>Select</th>
				<th>Function Group ID</th>
				<th>Function Group Description</th>				
			</tr>
			<c:forEach items="${fungrpList}" var="fg">
				<%-- <c:url var="updateLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url>
				<c:url var="deleteLink" value="">
					<c:param name="functiongrpId" value="${fg.functiongrpId}" />
				</c:url> --%>
				<tr>
					<td><input type="radio" name="fgRadio" value="${fg.functiongrpId}"></td>
					<td>${fg.functiongrpId}</td>
					<td>${fg.functiongrpDesc}</td>					
					<%-- <td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?))) return false">Delete</a></td> --%>
				</tr>
			</c:forEach>
		</table>
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