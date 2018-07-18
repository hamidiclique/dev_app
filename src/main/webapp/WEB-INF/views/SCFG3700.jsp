<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script type="text/javascript">
	$(function(){
		console.log( "document loaded" );
    	<c:forEach items="${btnList}" var="btn">
			<c:if test="${btn.buttonDesc eq 'ADD'}">
				console.log("add btn found");
				var a = document.getElementById(${btn.buttonDef});
				a.href = "${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid=";						
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
							b.href = "${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="+$(this).val();
						</c:otherwise>
					</c:choose>	
				</c:forEach>				
		    }
		  });
		});
</script>
</head>
<body>
	<c:set var="delete" value="DELETE" />
	<div class="rightPart">
		<table width="80%" align="center">
			<tr>
				<td class="PageHeader">${functionDesc}</td>
			</tr>			
			<tr>
				<td>Total: <c:out value="${listsize}"></c:out></td>
			</tr>
		</table>
		<br>
		<table class="TableContent" width="80%" align="center">
			<tr>
				<th class="HeaderTableData">Select</th>
				<th class="HeaderTableData">Branch ID</th>
				<th class="HeaderTableData">Branch Name</th>
				<th class="HeaderTableData">Address</th>
				<th class="HeaderTableData">City</th>
				<th class="HeaderTableData">Phone</th>
			</tr>
			<c:forEach items="${branchList}" var="branch">
				<%-- <c:url var="updateLink" value="updateUserInfo">
					<c:param name="userId" value="${usr.userId}" />
				</c:url>
				<c:url var="deleteLink" value="removeUser">
					<c:param name="userId" value="${usr.userId}" />
				</c:url> --%>
				<tr>
					<td class="CellClass"><input type="radio" name="branchRadio"
						value="${branch.branchId}"></td>
					<td class="CellClass">${branch.branchId}</td>
					<td class="CellClass">${branch.branchName}</td>
					<td class="CellClass">${branch.branchAddr}</td>
					<td class="CellClass">${branch.branchCity}</td>
					<td class="CellClass">${branch.branchPhone}</td>
					<%-- <td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?))) return false">Delete</a></td> --%>
				</tr>
			</c:forEach>
		</table>
		<br> <br>
		<table width="80%" align="center">
			<tr>
				<td><c:forEach items="${btnList}" var="btn">
						<c:choose>
							<c:when test="${btn.buttonDesc eq delete}">
								<a id="${btn.buttonDef}"
									onclick="if(!(confirm('Are you sure you want to delete?'))) return false">
									<input class="button" type="button" value="${btn.buttonDesc}" />
								</a>
							</c:when>
							<c:otherwise>
								<a id="${btn.buttonDef}"> <input class="button"
									type="button" value="${btn.buttonDesc}" />
								</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<%-- <a href="${uponelvl}"><input class="button" type="button" value="Return" /></a> --%>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>