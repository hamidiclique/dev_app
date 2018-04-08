<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<style type="text/css">
.error {
	color: #FF5722;
}
</style>
</head>
<body>
	<div class="rightPart">
		<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<c:choose>
			<c:when test="${empty error}">
			</c:when>
			<c:otherwise>
				<div id="alert-div" class="visible">
					<table align="center" width=100%">
						<tr>
							<td width="98%"><span class="error">${error}</span></td>
							<td><button onclick="fadeout()">X</button></td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>