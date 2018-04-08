<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
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
					<td class="CellClass"><input type="radio" name="fgRadio"
						value="${fg.functiongrpId}"></td>
					<td class="CellClass">&nbsp;${fg.functiongrpId}</td>
					<td class="CellClass">&nbsp;${fg.functiongrpDesc}</td>
					<%-- <td><a href="${contextPath}/${updateLink}">Update</a> | <a href="${deleteLink}" onclick="if(!(confirm('Are you sure?))) return false">Delete</a></td> --%>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
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