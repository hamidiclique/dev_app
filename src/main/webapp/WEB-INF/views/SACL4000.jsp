<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="rightPart">
		<table width="80%" align="center">
			<tr>
				<td class="PageHeader">${functionDesc}</td>
			</tr>
		</table>
		<br>
		<table class="TableContent" width="80%" align="center">
			<tr>
				<th class="HeaderTableData" style="display: block">Parameter ID</th>
				<th class="HeaderTableData">Parameter Description</th>
				<th class="HeaderTableData" width="20%">Parameter Value</th>
			</tr>
			<c:forEach items="${slpList}" var="slp">
				<c:url var="updateLink" value="editLoginParamValues">
					<c:param name="paramId" value="${slp.paramId}" />
				</c:url>
				<tr>
					<td class="CellClass" style="display: block">${slp.paramId}</td>
					<td class="CellClass" style="text-align: left">&nbsp;&nbsp;&nbsp;${slp.paramDesc}</td>
					<td class="CellClass">${slp.paramValue}</td>
					<%-- <td class="CellClass"><a href="${contextPath}/${updateLink}">Update</a></td> --%>
				</tr>
			</c:forEach>
		</table>
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
	<script type="text/javascript">
	$(function(){
		console.log( "document loaded" );
    	<c:forEach items="${btnList}" var="btn">
			<c:if test="${btn.buttonDesc eq 'UPDATE'}">
				console.log("update btn found");
				var a = document.getElementById(${btn.buttonDef});
				a.href = "${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid=";						
			</c:if>				
		</c:forEach>
		});
</script>

</body>
</html>