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

#declined {
	color: #FF0000 !important;
}

#pending {
	color: #32CD32 !important;
}

#reversed {
	color: #03039B !important;
}

#approved {
	color: #000 !important;
}

#suspect {
	color: #693604 !important;
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
			<col width="550">
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
				<td class="TableTopLegend">Reversed</td>
				<td>
					<div>
						<div id="black-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Approved</td>
				<td>
					<div>
						<div id="green-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Pending</td>
				<td>
					<div>
						<div id="red-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Declined</td>
				<td>
					<div>
						<div id="chocolate-box"></div>
					</div>
				</td>
				<td class="TableTopLegend">Suspect</td>
			</tr>
		</table>
		<table width="80%" align="center" id="table-one">
			<tr>
				<th class="TableHeadData">Relationship ID</th>
				<th class="TableHeadData">Transaction</th>
				<th class="TableHeadData">Amount</th>
				<th class="TableHeadData">date</th>
				<th class="TableHeadData">Time</th>
				<th class="TableHeadData">STAN</th>
				<th class="TableHeadData">Status</th>
				<th class="TableHeadData">Acquirer</th>
				<th class="TableHeadData">Authorizer</th>
				<th class="TableHeadData">Terminal</th>
			</tr>
			<c:forEach items="${trxnmonitorlist}" var="trxn">
				<c:choose>
					<c:when test="${trxn.statusCode eq '000'}">
						<tr id="approved">
							<td>&nbsp;${trxn.relationshipID}</td>
							<td>&nbsp;${trxn.transaction}</td>
							<td>&nbsp;${trxn.currency}&nbsp;${trxn.amount}</td>
							<td>&nbsp;${trxn.date}</td>
							<td>&nbsp;${trxn.time}</td>
							<td>&nbsp;${trxn.stan}</td>
							<td>&nbsp;${trxn.statusCode}&nbsp;-&nbsp;${trxn.statusDef}</td>
							<td>&nbsp;${trxn.acquirer}</td>
							<td>&nbsp;${trxn.authorizer}</td>
							<td>&nbsp;${trxn.terminal}</td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr id="declined">
							<td>&nbsp;${trxn.relationshipID}</td>
							<td>&nbsp;${trxn.transaction}</td>
							<td>&nbsp;${trxn.currency}&nbsp;${trxn.amount}</td>
							<td>&nbsp;${trxn.date}</td>
							<td>&nbsp;${trxn.time}</td>
							<td>&nbsp;${trxn.stan}</td>
							<td>&nbsp;${trxn.statusCode}&nbsp;-&nbsp;${trxn.statusDef}</td>
							<td>&nbsp;${trxn.acquirer}</td>
							<td>&nbsp;${trxn.authorizer}</td>
							<td>&nbsp;${trxn.terminal}</td>
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