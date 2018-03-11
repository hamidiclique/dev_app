<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
<title>ACL demo</title>
<!-- custom resources -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/SACL1000A.js"></script>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<c:set var="delete" value="DELETE" />
	<table width="80%" align="center">
		<tr>
			<td class="PageHeader">${functionDesc}</td>
		</tr>
		<tr>
			<td><a href="changePassword">Change Password</a>&nbsp;&nbsp;&nbsp;<a
				href="userLogout">Logout</a></td>
		</tr>
	</table>
	<form:form action="submit-function-group-maintenance-add"
		modelAttribute="ffMapDto" onsubmit="return countCheckboxes();">
		<table class="TableContent" width="80%" align="center">
			<tr style="display: none">
				<td><form:input type="text" path="function" hidden="true"
						readonly="true"></form:input></td>
				<td><form:input type="text" path="screen" hidden="true"
						readonly="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Function Group ID:</th>
				<td class="FormInputColor"><form:input type="text"
						name="functiongrpId" path="functiongrpId" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Function Group Description:</th>
				<td class="FormInputColor"><form:input type="text"
						name="functiongrpDesc" path="functiongrpDesc" required="true"></form:input></td>
			</tr>
			<tr>
				<th class="FormCellBGColor">Module Name:</th>
				<td class="FormInputColor"><form:select path="module"
						id="module" name="module" class="selmodule">
						<form:option value="" selected="selected">Please select a module</form:option>
						<c:forEach items="${allModules}" var="m">
							<form:option value="${m.moduleId}">${m.moduleDesc}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>
		</table>
		<br>
		<table id="functionsTable" class="TableContent" width="80%"
			align="center">
			<tr>
				<th class="HeaderTableData">Function ID</th>
				<th class="HeaderTableData">Function Name</th>
				<th class="HeaderTableData">Administer</th>
			</tr>
			<tbody id="funLoad">				
			</tbody>
		</table>
		<br>
		<table align="center" width="80%">
			<tr>
				<td>
					<p>
						<input type="submit" class="button" value="Add" /> <input
							type="button" class="button" onclick='selectAll()'
							value="Select All" /> <input type="button" class="button"
							onclick='unselectAll()' value="Unselect All" />
					</p>
				</td>
			</tr>
		</table>
	</form:form>

	<script type="text/javascript">
		$(".selmodule").change(function() {
			var moduleId = this.value;			
	        $.ajax({
	        	type: "POST",
	        	url: "${pageContext.request.contextPath}/getAllFunctionsForSelectedModule",
	        	data: { module: moduleId },
	        	dataType: "json",
	            success: function(data) {	            
	            	//alert("success");
	            	$('#funLoad').html("");
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
                    $('#funLoad').append(trHTML);
	            },
	            error: function(jqXHR, textStatus, errorThrown) {
	                alert(errorThrown);
	            }
	        });
		});
	</script>
</body>
</html>