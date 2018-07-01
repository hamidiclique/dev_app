<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/box.css">
<script type="text/javascript">
	$(function(){
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
		});
</script>
<style>
.required {
	color: red;
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
			<tr>
				<td>Total: <c:out value="${listsize}"></c:out></td>
			</tr>
		</table>
		<br>
		<table class="TableContent" width="80%" align="center">
			<tr>
				<th class="HeaderTableData">Select</th>
				<th class="HeaderTableData">ATM ID</th>
				<th class="HeaderTableData" style="display:  none;">MACHINE</th>
				<th class="HeaderTableData" style="display:  none;">REMOTE ADDRESS</th>
				<th class="HeaderTableData" style="display:  none;">BRANCH</th>
				<th class="HeaderTableData" style="display:  none;">STREET</th>
				<th class="HeaderTableData" style="display:  none;">CITY</th>
				<th class="HeaderTableData" style="display:  none;">STATE</th>
				<th class="HeaderTableData">MAKE</th>
				<th class="HeaderTableData">LOCATION</th>
				<th class="HeaderTableData">STATE</th>
				<th class="HeaderTableData">DEVICES</th>
				<th class="HeaderTableData">SUPPLIES</th>
				<th class="HeaderTableData">CASH</th>
				<th class="HeaderTableData">CONFIG LOAD</th>
				<th class="HeaderTableData">FATAL DEVICE</th>
			</tr>
			<c:forEach items="${activeAtmList}" var="actvatm">
				<%-- <c:url var="updateLink" value="updateUserInfo">
					<c:param name="userId" value="${usr.userId}" />
				</c:url>
				<c:url var="deleteLink" value="removeUser">
					<c:param name="userId" value="${usr.userId}" />
				</c:url> --%>
				<tr>
					<td class="CellClassCustom" align="center"><input type="radio" name="actvAtmRadio"
						value="${actvatm.pid}"></td>
					<td class="CellClassCustom">${actvatm.pid}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.machine}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.remoteAddress}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.branchName}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.street}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.city}</td>
					<td class="CellClassCustom" style="display:  none;">${actvatm.state}</td>
					<td class="CellClassCustom">NCR</td>
					<td class="CellClassCustom">${actvatm.branchName}, ${actvatm.street}, ${actvatm.city}, ${actvatm.state}</td>
					<td class="CellClassCustom" align="center"><div id="maroon-box"></div></td>
					<td class="CellClassCustom" align="center"><div id="red-box"></div></td>
					<td class="CellClassCustom" align="center"><div id="red-box"></div></td>
					<td class="CellClassCustom" align="center"><div id="green-box"></div></td>
					<td class="CellClassCustom" align="center"><div id="green-box"></div></td>
					<td class="CellClassCustom">N/A</td>
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