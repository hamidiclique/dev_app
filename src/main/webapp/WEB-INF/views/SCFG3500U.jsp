<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/atm_dashboard.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/box.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/dropdown.css">
<%-- <script
	src="${pageContext.request.contextPath}/resources/js/dropdown.js"></script> --%>
<style>
#side ul {
	height: 800px;
}

.required {
	color: red;
}

#confloadval {
	color: #0000FF;
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
		<br>

		<!-- start -->
		<table width="80%" align="center">
			<tr>
				<td colspan="2">
					<table width="100%" align="center" id="table-one">
						<tr>
							<th colspan="8" class="FormTitleBGColor">Basic Informtion</th>
						</tr>
						<tr>
							<td>&nbsp;Name</td>
							<td>&nbsp;0001/0001</td>
							<td>&nbsp;IP Address</td>
							<td>&nbsp;10.100.53.139</td>
							<td>&nbsp;Branch</td>
							<td>&nbsp;Nobigonj Branch</td>
							<td>&nbsp;Location</td>
							<td>&nbsp;Test NCR Bits Dhaka</td>
						</tr>
						<tr>
							<td>&nbsp;Comms Type</td>
							<td>&nbsp;TCP/IP</td>
							<td>&nbsp;Status</td>
							<td><div id="red-box"></div></td>
							<td>&nbsp;Config Load</td>
							<td><div style="display: inline-flex;">
									<div id="green-box"></div>
									&nbsp;<span id="confloadval">100%</span>
								</div></td>
							<td>&nbsp;Image file</td>
							<td>&nbsp;</td>
						</tr>
						<tr>
							<td>&nbsp;ATM Make</td>
							<td>&nbsp;NCR</td>
							<td>&nbsp;Comms</td>
							<td><div id="red-box"></div></td>
							<td>&nbsp;Key Status</td>
							<td>&nbsp;Loaded</td>
							<td>&nbsp;Supervisor</td>
							<td>&nbsp;</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td><div style="overflow-y: auto;">
						<table width="100%" align="center" id="table-two">
							<tr>
								<th colspan="4" class="FormTitleBGColor">Device Health</th>
							</tr>
							<tr>
								<th>&nbsp;<u>Device Name</u></th>
								<th align="center"><u>Status</u></th>
								<th>&nbsp;<u>Device Name</u></th>
								<th align="center"><u>Status</u></th>
							</tr>
							<tr>
								<td>&nbsp;Cash Dispenser</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;BNA</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;Cassette 1</td>
								<td align="center"><div id="red-box"></div></td>
								<td>&nbsp;Cassette 1</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;Cassette 2</td>
								<td align="center"><div id="red-box"></div></td>
								<td>&nbsp;Cassette 2</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;Cassette 3</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;Cassette 3</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;Cassette 4</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;Cassette 4</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;MCRW</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;Encryptor</td>
								<td align="center"><div id="green-box"></div></td>
							</tr>
							<tr>

								<td>&nbsp;Receipt Printer</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;Journal Printer</td>
								<td align="center"><div id="green-box"></div></td>
							</tr>
							<tr>
								<td>&nbsp;Hight Order Comms</td>
								<td align="center"><div id="green-box"></div></td>
								<td>&nbsp;System Disk</td>
								<td align="center"><div id="grey-box"></div></td>
							</tr>
						</table>
					</div></td>
				<td>
					<table width="100%" align="center" id="table-three">
						<tr>
							<th colspan="4" class="FormTitleBGColor">Supplies Health</th>
						</tr>
						<tr>
							<th>&nbsp;<u>Device Name</u></th>
							<th align="center"><u>Status</u></th>
							<th>&nbsp;<u>Device Name</u></th>
							<th align="center"><u>Status</u></th>
						</tr>
						<tr>
							<td>&nbsp;Reject Bin</td>
							<td align="center"><div id="gold-box"></div></td>
							<td>&nbsp;BNA</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;Cassette 1</td>
							<td align="center"><div id="red-box"></div></td>
							<td>&nbsp;Cassette 1</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;Cassette 2</td>
							<td align="center"><div id="red-box"></div></td>
							<td>&nbsp;Cassette 2</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;Cassette 3</td>
							<td align="center"><div id="green-box"></div></td>
							<td>&nbsp;Cassette 3</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;Cassette 4</td>
							<td align="center"><div id="green-box"></div></td>
							<td>&nbsp;Cassette 4</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;Card Capture Bin</td>
							<td align="center"><div id="grey-box"></div></td>
							<td>&nbsp;Encryptor</td>
							<td align="center"><div id="gold-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;RCPT Paper</td>
							<td align="center"><div id="grey-box"></div></td>
							<td>&nbsp;Jrnl Paper</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
						<tr>
							<td>&nbsp;RCPT Print-Head</td>
							<td align="center"><div id="grey-box"></div></td>
							<td>&nbsp;JRNL Ribbon</td>
							<td align="center"><div id="grey-box"></div></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td>
					<table width="100%" align="center" id="table-four">
						<tr>
							<th colspan="5" class="FormTitleBGColor">Cash Status
								(Withdrawal)</th>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;Cass. 1</td>
							<td>&nbsp;Cass. 2</td>
							<td>&nbsp;Cass. 3</td>
							<td>&nbsp;Cass. 4</td>
						</tr>
						<tr>
							<td>&nbsp;Currency</td>
							<td>&nbsp;BDT</td>
							<td>&nbsp;BDT</td>
							<td>&nbsp;BDT</td>
							<td>&nbsp;BDT</td>
						</tr>
						<tr>
							<td>&nbsp;Denominations</td>
							<td>&nbsp;500</td>
							<td>&nbsp;1000</td>
							<td>&nbsp;1000</td>
							<td>&nbsp;1000</td>
						</tr>
						<tr>
							<td>&nbsp;Threshold</td>
							<td>&nbsp;50</td>
							<td>&nbsp;50</td>
							<td>&nbsp;50</td>
							<td>&nbsp;50</td>
						</tr>
						<tr>
							<td>&nbsp;Dispensed</td>
							<td>&nbsp;0</td>
							<td>&nbsp;21</td>
							<td>&nbsp;43</td>
							<td>&nbsp;0</td>
						</tr>
						<tr>
							<td>&nbsp;Rejected</td>
							<td>&nbsp;1</td>
							<td>&nbsp;10</td>
							<td>&nbsp;2</td>
							<td>&nbsp;2</td>
						</tr>
						<tr>
							<td>&nbsp;Remaining</td>
							<td>&nbsp;499</td>
							<td>&nbsp;469</td>
							<td>&nbsp;455</td>
							<td>&nbsp;498</td>
						</tr>
					</table>
				</td>
				<td>
					<table width="100%" align="center" id="table-five">
						<tr>
							<th colspan="4" class="FormTitleBGColor">Cash Status
								(Deposit)</th>
						</tr>
						<tr>
							<td>&nbsp;Refunded</td>
							<td>&nbsp;0</td>
							<td>&nbsp;Rejected</td>
							<td>&nbsp;0</td>
						</tr>
						<tr>
							<td>&nbsp;Encashed</td>
							<td>&nbsp;0</td>
							<td>&nbsp;Escrowed</td>
							<td>&nbsp;0</td>

						</tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>

						</tr>
					</table> <br>
					<table width="100%" align="center" id="table-six">
						<tr>
							<th colspan="4" class="FormTitleBGColor">Others</th>
						</tr>
						<tr>
							<td>&nbsp;Cards Cpatured</td>
							<td>&nbsp;0</td>
							<td>&nbsp;CPM Bin 2</td>
							<td>&nbsp;0</td>
						</tr>
						<tr>
							<td>&nbsp;CPM Bin 1</td>
							<td>&nbsp;0</td>
							<td>&nbsp;CPM Bin 3</td>
							<td>&nbsp;0</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<!-- end -->
		<br>
		<table align="center" width="80%">
			<tr>
				<td>
					<div class="dropup">
						<button class="dropbtn">ATMC OPTIONS</button>
						<div class="dropup-content">
							<c:forEach items="${cmdcodemap}" var="entry">
								<c:choose>
									<c:when test="${entry.value eq 1}">
										<c:set var="keyparts" value="${fn:split(entry.key, '-')}" />
										<%-- b.href = "${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="+$(this).val(); --%>
										<a class="selcmd" id="${keyparts[0]}"
											onclick="if(!(confirm('Are you sure you want to ${keyparts[1]}?'))) return false">
											<%-- <input class="" type="button" value="${keyparts[1]}" /> --%>${keyparts[1]}
										</a>
									</c:when>
									<c:when test="${entry.value eq 2}">
										<c:set var="keyparts" value="${fn:split(entry.key, '-')}" />
										<%-- b.href = "${pageContext.request.contextPath}/common?mid=${module}&fid=${function}&sid=${btn.resultPage}&pid="+$(this).val(); --%>
										<a id="" class="deactivated"> <%-- <input class="" type="button" value="${keyparts[1]}" disabled /> --%>${keyparts[1]}
										</a>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</c:forEach>
						</div>
					</div> <a href="${uponelvl}"><input class="button" type="button"
						value="Return" /></a>
				</td>
			</tr>
		</table>
	
	</div>
	
	<script type="text/javascript">
		/* $(".selmodule").change(function() { */
		$(".selcmd").click(function() {	
			var cmd_code = this.id;			
	        $.ajax({
	        	type: "POST",
	        	url: "${pageContext.request.contextPath}/updateCmdStatusForSelectedATM",
	        	data: { cmdcode: cmd_code, pid: ${pid} },
	        	dataType: "text",
	            success: function(data) {
	            	if (data.trim() == "200") {
	            		setTimeout(function() {// wait for 5 secs(2)
		            		location.reload(); // then reload the page.(3)
		            	}, 1000);
	            	}
	            	else {
	            		alert("Something went wrong while updating CMD status for this ATM, Please try again.");
	            	}	            	
/* 	            	$('#funLoad').html("");
	            	var trHTML = '';
                    for (row in data) {
                            trHTML +=
                                '<tr><td class="FormInputColor"><div style="text-align: center">'
                                + data[row].functionId
                                + '</div></td><td class="FormInputColor">&nbsp;'
                                + data[row].functionDesc
                                + '</td><td class="FormInputColor"><div style="text-align: center"><input type="checkbox" name="functionList" value=' 
                                + data[row].functionId
                                + '></div></td></tr>';
                        }                    
                    $('#funLoad').append(trHTML); */
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                alert(errorThrown);
	            }
	        });
		});
	</script>
	
</html>