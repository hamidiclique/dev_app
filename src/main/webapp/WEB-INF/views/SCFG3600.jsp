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

#table-zero {
	background-color: transparent;
}

#table-3-1, #table-3-2 {
	border-right: 5px solid #FFF;
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
		<table class="TableContent" width="80%" align="center" id="table-one">
			<tr>
				<th class="FormTitleBGColor" colspan="6">Event Search</th>
			</tr>
			<tr>
				<th>&nbsp;Device Type:</th>
				<td><select>
						<option value="">ATM</option>
				</select></td>
				<th>&nbsp;Device:</th>
				<td><select>
						<option value="">ATM</option>
				</select></td>
				<th>&nbsp;Status:</th>
				<td><select>
						<option value="">&lt; ALL &gt;</option>
				</select></td>
			</tr>
			<tr>
				<th>&nbsp;Event ID:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
				<th><div>
						<span class="required">*</span>&nbsp;From Start Time:
					</div> <span>(dd/MM/yyyy hh:mm)</span></th>
				<td><input type="text" class="fullwidthinp"
					value="01/06/2018 00:00"></input></td>
				<th><div>
						<span class="required">*</span>&nbsp;To Start Time:
					</div> <span>(dd/MM/yyyy hh:mm)</span></th>
				<td><input type="text" class="fullwidthinp"
					value="05/06/2018 23:59"></input></td>
			</tr>
			<tr>
				<th>&nbsp;Event ID:</th>
				<td><select>
						<option value="">&lt; ALL &gt;</option>
				</select></td>
				<td colspan="4" align="right"><c:forEach items="${btnList}"
						var="btn">
						<a
							href="${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="><input
							class="button" type="button" value="Search" /></a>
					</c:forEach> <input type="reset" class="button" value="Reset" /></td>
			</tr>
		</table>
		<div class="separator"></div>
		<table class="term-event-table" width="80%" align="center">
			<tr>
				<td><div class="term-event-block">
						<div class="term-event-msg">Difference between Start Date
							and End Date should not be more than 7 days.</div>
					</div></td>
		</table>
		<br>
		<%-- 		<table width="80%" align="center">
			<tr>
				<td><c:forEach items="${btnList}" var="btn"> --%>
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
		<%-- <a
							href="${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="><input
							class="button" type="button" value="Search" /></a>
					</c:forEach> </td> <a href="${uponelvl}"><input class="button" type="button" value="Return" /></a>
			</tr>
		</table> --%>
	</div>

</body>
</html>