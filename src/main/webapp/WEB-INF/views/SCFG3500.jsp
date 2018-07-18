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
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/modal.css">
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
				var atmcbtn = document.getElementById("atmc");
				$("#pid").val($(this).val());
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
				<th class="HeaderTableData" style="display: none;">MACHINE</th>
				<th class="HeaderTableData" style="display: none;">REMOTE
					ADDRESS</th>
				<th class="HeaderTableData" style="display: none;">BRANCH</th>
				<th class="HeaderTableData" style="display: none;">STREET</th>
				<th class="HeaderTableData" style="display: none;">CITY</th>
				<th class="HeaderTableData" style="display: none;">STATE</th>
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
					<td class="CellClassCustom" align="center"><input type="radio"
						name="actvAtmRadio" value="${actvatm.pid}"></td>
					<td class="CellClassCustom">${actvatm.pid}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.machine}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.remote_address}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.branch_name}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.street}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.city}</td>
					<td class="CellClassCustom" style="display: none;">${actvatm.state}</td>
					<td class="CellClassCustom">NCR</td>
					<td class="CellClassCustom">${actvatm.branch_name},
						${actvatm.street}, ${actvatm.city}, ${actvatm.state}</td>
					<td class="CellClassCustom" align="center"><div
							id="maroon-box"></div></td>
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
					</c:forEach> <a id="atmc"><input class="button" type="button"
						value="ATMC Options" /></a> <!-- <button id="atmc">Open Modal</button> -->
					<%-- <a href="${uponelvl}"><input class="button" type="button" value="Return" /></a> --%>
				</td>
			</tr>
		</table>

		<div id="atmcModal" class="modal">

			<!-- Modal content -->
			<div class="modal-content">
				<div class="modal-header">
					<span class="close">&times;</span>
					<p>ATMC Options</p>
				</div>
				<div class="modal-body">
					<div style="display: none">
						<input type="text" id="pid" value="0">
					</div>
					<div id="grid-container"></div>
				</div>
			</div>

		</div>


	</div>

	<script type="text/javascript">
		// Get the modal
		var modal = document.getElementById('atmcModal');			
		// Get the <span> element that closes the modal
		var span = document.getElementsByClassName("close")[0];
		// When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		    modal.style.display = "none";
		}		
		// When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		    if (event.target == modal) {		    	
		    	modal.style.display = "none";		        
		    }
		}
		$("#atmc").click(function() {
			var selpid = $("#pid").val();
			if (selpid > 100) {
			    //alert("selected ATM ID:" + selpid);
			    $.ajax({
	        		type: "POST",
	        		url: "${pageContext.request.contextPath}/getCmdStatusForSelectedATM",
	        		data: { pid: selpid },
	        		dataType: "json",
	            	success: function(data){	            		
	            		$('#grid-container').html("");
		            	var testhtml = '';
		            	$.each(data, function (index, value) {
		            		var res = index.split("-");
		            		var cmd_id = res[0].trim();
		            		var cmd_desc = res[1].trim();
		            		var cmdstatus = value;
		            		switch(cmdstatus) {
		            		    case 1:
		            		    	testhtml += '<div class="pending">'
		            		        testhtml += '<a id="'+cmd_id+'" class="selcmd">'+cmd_desc+'</a><br>';
		            		        testhtml += '</div>'
		            		        break;
		            		    case 2:
		            		    	testhtml += '<div class="done">'
		            		    	testhtml += '<span id="'+cmd_id+'" class="deactivated">'+cmd_desc+'</span><br>';
		            		    	testhtml += '</div>'
		            		    	break;		            		    
		            		    default:
		            		    	break;
		            		}
		            	});
		            	$('#grid-container').append(testhtml);
	            		modal.style.display = "block";
	            	},
	            	error: function(jqXHR, textStatus, errorThrown) {
	                	alert(errorThrown);
	            	}
	        	}); 			    
			}
			else {
				alert("You must select an ATM to view ATMC Options, Please try again.");
			}
		});
		$('#grid-container').on('click', '.selcmd', function(e){
		    //alert("clicked");
			var selpid = $("#pid").val();
			var cmd_code = this.id;			
	        var command = this.text;
			$.ajax({
	        	type: "POST",
	        	url: "${pageContext.request.contextPath}/updateCmdStatusForSelectedATM",
	        	data: { cmdcode: cmd_code, pid: selpid },
	        	dataType: "text",
	            success: function(data) {
	            	if (data.trim() == "200") {
	            		alert(command + "Request for ATM ID: " + selpid + " has been successfully submitted, Please note ATMC options for this ATM will remain disabled unitl request completed.")
	            		setTimeout(function() {// wait for 5 secs(2)
		            		location.reload(); // then reload the page.(3)
		            	}, 1000);
	            	}
	            	else {
	            		alert("Something went wrong while updating CMD status for this ATM, Please try again.");
	            	}
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                alert(errorThrown);
	            }
	        });
		});
	</script>

</body>
</html>