<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/box.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/atm.css">
<script>
	/* 	$(function(){
	 console.log( "document loaded" );
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
	 }); */
	function reloadPage() {
		location.reload();
	};
</script>
<style>
.required {
	color: red;
}

#fatal {
	color: #FF0000 !important;
}

#ok {
	color: #32CD32 !important;
}

#warning {
	color: #03039B !important;
}
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="rightPart">
		<table width="80%" align="center">
			<tr>
				<td class="PageHeader">${functionDesc}</td>
			</tr>
		</table>
		<table width="80%" align="center">
			<col width="700">
			<tr>
				<td><input type="image"
					src="${pageContext.request.contextPath}/resources/img/refresh.png"
					alt="Submit" width="30" height="30" onclick="reloadPage()"
					style="opacity: 0.30;"></td>
				<td>
					<div>
						<div id="dark-blue-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Warning</td>
				<td>
					<div>
						<div id="green-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Ok</td>
				<td>
					<div>
						<div id="red-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Fatal</td>
			</tr>
		</table>
		<table width="80%" align="center" id="table-one">
			<tr>
				<th class="TableHeadData">ID</th>
				<th class="TableHeadData">Terminal ID</th>
				<th class="TableHeadData">Location</th>
				<th class="TableHeadData">Start Time</th>
				<th class="TableHeadData">Device</th>
				<th class="TableHeadData">Status</th>
				<th class="TableHeadData">Reason</th>
			</tr>
			<c:forEach items="${terminalEventList}" var="termevnt">
				<c:choose>
					<c:when test="${termevnt.status eq 'OPEN'}">
						<tr id="ok">
							<td>&nbsp;${termevnt.id}</td>
							<td>&nbsp;${termevnt.terminalID}</td>
							<td>&nbsp;${termevnt.location}</td>
							<td>&nbsp;${termevnt.device}</td>
							<td>&nbsp;${termevnt.startTime}</td>
							<td>&nbsp;${termevnt.status}</td>
							<td>&nbsp;${termevnt.reason}</td>
						</tr>
					</c:when>
					<c:when test="${termevnt.status eq 'CLOSED'}">
						<tr id="fatal">
							<td>&nbsp;${termevnt.id}</td>
							<td>&nbsp;${termevnt.terminalID}</td>
							<td>&nbsp;${termevnt.location}</td>
							<td>&nbsp;${termevnt.device}</td>
							<td>&nbsp;${termevnt.startTime}</td>
							<td>&nbsp;${termevnt.status}</td>
							<td>&nbsp;${termevnt.reason}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<td>&nbsp;${termevnt.id}</td>
							<td>&nbsp;${termevnt.terminalID}</td>
							<td>&nbsp;${termevnt.location}</td>
							<td>&nbsp;${termevnt.device}</td>
							<td>&nbsp;${termevnt.startTime}</td>
							<td>&nbsp;${termevnt.status}</td>
							<td>&nbsp;${termevnt.reason}</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</c:forEach>
		</table>
		<br>
		<table width="80%" align="center">
			<tr>
				<td><c:forEach items="${btnList}" var="btn">
						<%-- 						<c:choose>
							<c:when test="${btn.buttonDesc eq delete}">
								<a id="${btn.buttonDef}"
									onclick="if(!(confirm('Are you sure want to delete?'))) return false">
									<input class="button" type="button" value="${btn.buttonDesc}" />
								</a>
							</c:when>
							<c:otherwise>
								<a id="${btn.buttonDef}"><input class="button" type="button"
									value="${btn.buttonDesc}" /> </a>
							</c:otherwise>
						</c:choose> --%>
						<a
							href="${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="><input
							class="button" type="button" value="Return" /></a>
					</c:forEach> <a href="${uponelvl}"><input class="button" type="button"
						value="Return" /></a>
			</tr>
		</table>
	</div>

</body>
</html>