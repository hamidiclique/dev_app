<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo App Login Info</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">

<style type="text/css">
</style>
</head>
<body>
	<a href="userLogout">Logout</a>
	<table class="TableContent" width="90%" align="center">
		<tr>
			<th class="FormCellBGColor">User Name :</th>
			<td class="FormInputColor">&nbsp;${user.userName}</td>
		</tr>
		<tr>
			<th class="FormCellBGColor">Last Login :</th>
			<td class="FormInputColor">&nbsp;${user.lastLogin}</td>
		</tr>
		<tr>
			<th class="FormCellBGColor">Last Password Change :</th>
			<td class="FormInputColor">&nbsp;${user.passwordCreDate}<br></td>
		</tr>
	</table>
	<br>
	<br>
	<table width="90%" align="center">
		<tr>
			<td colspan="2" class="item">This system is for the use of
				authorized users only. Individuals using this computer system
				without authority, or in excess of their authority, are subject to
				having all of their activities on this system monitored and recorded
				by system personnel. In the course of monitoring individuals
				improperly using this system, or in the course of system
				maintenance, the activities of authorized users may also be
				monitored. Anyone using this system expressly consents to such
				monitoring and is advised that if such monitoring reveals possible
				evidence of criminal activity, system personnel may provide the
				evidence of such monitoring to law enforcement officials</td>
		</tr>
		<tr>
			<td><a href="showModules"><input class="button"
					type="button" value="Continue" /></a></td>
		</tr>
	</table>

</body>
</html>