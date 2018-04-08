<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="rightPart">
		<div id="alert-div" class="visible">
			<table align="center" width=100%">
				<tr>
					<td width="98%">${message}</td>
					<td><button onclick="fadeout()">X</button></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>