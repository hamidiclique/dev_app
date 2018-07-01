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

#side ul {
	height: 905px;
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
		<table class="TableContent" width="80%" align="center" id="table-one">
			<col width="100">
			<col width="100">
			<col width="100">
			<tr>
				<th class="FormTitleBGColor" colspan="6">Transaction Search
					Template</th>
			</tr>
			<tr>
				<th><div>
						<span class="required">*</span>&nbsp;Template Name:
					</div></th>
				<td><select>
						<option value="">&lt; NEW &gt;</option>
				</select></td>
				<td><input type="text" class="fullwidthinp" required></input></td>
			</tr>
		</table>
		<div class="separator"></div>
		<table class="TableContent" width="80%" align="center" id="table-one">
			<col width="218">
			<col width="280">
			<col width="218">
			<col width="280">
			<tr>
				<th><div>
						<span class="required">*</span>&nbsp;From-Date (dd/mm/yyyy):
					</div></th>
				<td><input type="text" class="fullwidthinp" required></input></td>
				<th><div>
						<span class="required">*</span>&nbsp;To-Date (dd/mm/yyyy):
					</div></th>
				<td><input type="text" class="fullwidthinp" required></input></td>
			</tr>
			<tr>
				<th>&nbsp;From-Time (hh:mm:ss):</th>
				<td><input type="text" class="fullwidthinp" value="00:00:00"></input></td>
				<th>&nbsp;To-Time (hh:mm:ss):</th>
				<td><input type="text" class="fullwidthinp" value="23:59:59"></input></td>
			</tr>
			<tr>
				<th>&nbsp;Relationship ID:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
				<th>&nbsp;Customer ID:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
			</tr>
			<tr>
				<th>&nbsp;Account ID (From - To):</th>
				<td><input type="text" class="halfwidthinp" required></input>&nbsp;
					<input type="text" class="halfwidthinp" required></input></td>
				<th>&nbsp;STAN:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
			</tr>
			<tr>
				<th>&nbsp;Destination Channel:</th>
				<td><select>
						<option value="">&lt; All &gt;</option>
				</select></td>
				<th>&nbsp;Merchant:</th>
				<td><select>
						<option value="">&lt; All &gt;</option>
				</select></td>
			</tr>
			<tr>
				<th>&nbsp;Status:</th>
				<td><select>
						<option value="">&lt; All &gt;</option>
				</select></td>
				<th>&nbsp;Response Code:</th>
				<td><select disabled>
						<option value="">&lt; All &gt;</option>
				</select></td>
			</tr>
			<tr>
				<th>&nbsp;Transaction Amount:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
				<th>&nbsp;IMD Code:</th>
				<td><input type="text" class="fullwidthinp" required></input></td>
			</tr>
			<tr>
				<th>&nbsp;Company Name:</th>
				<td><select>
						<option value="">&lt; All &gt;</option>
				</select></td>
				<th></th>
				<td></td>
			</tr>
		</table>
		<div class="separator"></div>
		<table class="TableContent" width="80.50%" align="center"
			id="table-zero">
			<tr>
				<td>
					<table class="TableContent" width="33%" align="center"
						id="table-3-1">
						<tr>
							<th class="FormTitleBGColor" colspan="3">Transaction Code</th>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>00</td>
							<td>Purchase</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>01</td>
							<td>Withdrawal</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>05</td>
							<td>VISA-05</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>06</td>
							<td>VISA-06</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>07</td>
							<td>E-Commerce</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>11</td>
							<td>Quashi Cash</td>
						</tr>
					</table>
					<table class="TableContent" width="34%" align="center"
						id="table-3-2">
						<tr>
							<th class="FormTitleBGColor" colspan="3">Product</th>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>PCLSC</td>
							<td>PCLSC</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>PCMPS</td>
							<td>PCMPS</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>PFMNA</td>
							<td>PFMNA</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>PRECO</td>
							<td>PRECO</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>VCSAL</td>
							<td>VCSAL</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>VDCLS</td>
							<td>VDCLS</td>
						</tr>
					</table>
					<table class="TableContent" width="33%" align="center"
						id="table-3-3">
						<tr>
							<th class="FormTitleBGColor" colspan="3">Transaction
								Currency</th>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>050</td>
							<td>BDT</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>840</td>
							<td>USD</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<div class="separator"></div>
		<table class="TableContent" width="80.50%" align="center"
			id="table-zero">
			<tr>
				<td>
					<table class="TableContent" width="33%" align="center"
						id="table-3-1">
						<tr>
							<th class="FormTitleBGColor" colspan="3">Transaction Code</th>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>00</td>
							<td>Purchase</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>01</td>
							<td>Withdrawal</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>05</td>
							<td>VISA-05</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>06</td>
							<td>VISA-06</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>07</td>
							<td>E-Commerce</td>
						</tr>
						<tr>
							<td><input type="checkbox" name="" value=""></td>
							<td>11</td>
							<td>Quashi Cash</td>
						</tr>
					</table>
				</td>
			</tr>
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
						<a href="${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="><input class="button" type="button" value="Search" /></a>
					</c:forEach>
					<%-- <a href="${uponelvl}"><input class="button" type="button" value="Return" /></a> --%>					
			</tr>
		</table>
	</div>

</body>
</html>