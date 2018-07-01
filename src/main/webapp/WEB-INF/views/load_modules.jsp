<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
</head>
<style>
	a
</style>
<body>

	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="modulemap" value="${modmap}" />
	<div class="rightPart">
		<table width="80%" align="center">
			<tr>
				<td class="PageHeader">Main Menu</td>
			</tr>
		</table>
		<br>
		<table class="TableContent" align="center" width="80%">
			<col width="80">
			<tr>
				<th class="HeaderTableData">S/NO</th>
				<th></th>
			</tr>
			<c:forEach items="${funmodmap}" var="funmodmap" varStatus="count">
				<tr>
					<c:url var="link" value="viewFunctions">
						<c:param name="mid" value="${funmodmap.key}" />
						<c:param name="fid" value="" />
						<c:param name="sid" value="" />
					</c:url>
					<c:set var="moduleKey">${funmodmap.key}</c:set>
					<td class="FormInputColor">${count.count}</td>
					<td class="FormInputColorSentence"><a href="${contextPath}/${link}"><c:out
								value="${modulemap[moduleKey]}" /></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>