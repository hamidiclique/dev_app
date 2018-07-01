<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/atm_dashboard.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/box.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/loader.css">	
<script
	src="${pageContext.request.contextPath}/resources/js/anychart-bundle.min.js"></script>
<script src="http://cdnjs.cloudflare.com/ajax/libs/modernizr/2.8.2/modernizr.js"></script>	
<style>
#side ul {
	height: 760px;
}

.required {
	color: red;
}

.table-bordered {
	border: 1px solid #DCDCDC;
	border-spacing: 0px;
}

.anychart-credits {
	display: none;
}

#barc {
	height: 318px;
}

#piec {
	height: 255px;
}
</style>
<script>
	$(window).load(function() {
		// Animate loader off screen
		$(".se-pre-con").fadeOut("slow");;
	});
	anychart.onDocumentLoad(function() {

		stage = anychart.graphics.create("barc");

		layer = stage.layer();
		// set data
		var data_1 = [
			{x: "Total", value: 18, fill:"#A9A9A9"},
			{x: "In Service", value: 12, fill:"#32CD32"},
			{x: "Out of Service", value: 2, fill:"#FF0000"},
			{x: "Link Down", value: 3, fill:"#800000"},
			{x: "Supervisory", value: 0, fill:"#FFD700"},
			{x: "Non Operational", value: 1, fill:"#336699"},
			{x: "Unknown", value: 0, fill:"#FF00FF"}
		];

		// chart type
		var chart_1 = anychart.bar();
		chart_1.width(480);
		chart_1.height(320);
		//chart_1.title("Terminal Summary");
		//chart_1.padding(40, 0, 0, 75);
		chart_1.padding(20, 10, 20, 10);
		var series_1 = chart_1.bar(data_1);
		series_1.stroke(null);

		chart_1.container(layer);
		chart_1.draw();

		// create data
		var data_2 = [	
			{x: "Low Cash", value: 4, fill:"#FFD700"},
			{x: "Normal Cash", value: 7, fill:"#32CD32"},
			{x: "Out of Cash", value: 1, fill:"#FF0000"}
		];

		// create a chart and set the data
		chart_2 = anychart.pie(data_2);
		chart_2.width(520);
		chart_2.height(255);
		//chart_2.title("Terminal Cash Summary");
		//chart_2.padding(40, 0, 0, 75);
		// set the container id
		chart_2.container("piec");
		// initiate drawing the chart
		chart_2.draw();
	});
</script>
</head>
<body>
	<div class="se-pre-con"></div>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="rightPart">
		<table width="80%" align="center">
			<tr>
				<td class="PageHeader">${functionDesc}</td>
			</tr>
		</table>
		<br>
		<table width="80%" align="center">
			<tr>
				<td>
					<table width="100%" class="table-bordered">
						<tr>
							<th class="FormTitleBGColor" colspan="6">Terminal Summary</th>
						</tr>
						<tr>
							<td><div id="barc"></div></td>
						</tr>
					</table>
				</td>
				<td><div style="height: 350px;">
					<table width="100%" class="table-bordered">
						
						<tr>
							<th class="FormTitleBGColor" colspan="6">Terminal Detail</th>
						</tr>
						<tr>
							<th class="FormTitleBGColorEven">State</th>
							<th class="FormTitleBGColorEven">Representations</th>
							<th class="FormTitleBGColorEven">No. of ATMs</th>
						</tr>
						<tr>
							<td class="FormInputColor">Total</td>
							<td align="center" class="FormInputColor"><div id="grey-box"></div></td>
							<td class="FormInputColor">3</td>
						</tr>
						<tr>
							<td class="FormInputColorEven">In Service</td>
							<td align="center" class="FormInputColorEven"><div id="green-box"></div></td>
							<td class="FormInputColorEven">0</td>
						</tr>
						<tr>
							<td class="FormInputColor">Out of Service</td>
							<td align="center" class="FormInputColor"><div id="red-box"></div></td>
							<td class="FormInputColor">0</td>
						</tr>
						<tr>
							<td class="FormInputColorEven">Link Down</td>
							<td align="center" class="FormInputColorEven"><div id="maroon-box"></div></td>
							<td class="FormInputColorEven">3</td>
						</tr>
						<tr>
							<td class="FormInputColor">Supervisory</td>
							<td align="center" class="FormInputColor"><div id="gold-box"></div></td>
							<td class="FormInputColor">0</td>
						</tr>
						<tr>
							<td class="FormInputColorEven">Non Opertional</td>
							<td align="center" class="FormInputColorEven"><div id="blue-box"></div></td>
							<td class="FormInputColorEven">0</td>
						</tr>
						<tr>
							<td class="FormInputColor">Unknown</td>
							<td align="center" class="FormInputColor"><div id="fuchsia-box"></div></td>
							<td class="FormInputColor">0</td>
						</tr>
						<tr>
							<td class="FormInputColorEven"></td>
							<td align="center" class="FormInputColorEven"></td>
							<td class="FormInputColorEven"></td>
						</tr>
						<tr>
							<td class="FormInputColorEven"></td>
							<td align="center" class="FormInputColorEven"></td>
							<td class="FormInputColorEven"></td>
						</tr>						
					</table></div>
				</td>
			</tr>
			<tr>
				<td>
					<table width="100%" class="table-bordered">
						<tr>
							<th class="FormTitleBGColor" colspan="6">Terminal Cash
								Summary</th>
						</tr>
						<tr>
							<td><div id="piec"></div></td>
						</tr>
					</table>
				</td>
				<td><div style="height: 288px;">
					<table width="100%" class="table-bordered">
						<tr>
							<th class="FormTitleBGColor" colspan="6">Terminal Cash
								Detail</th>
						</tr>
						<tr>
							<th class="FormTitleBGColorEven">State</th>
							<th class="FormTitleBGColorEven">Representations</th>
							<th class="FormTitleBGColorEven">No. of ATMs</th>
						</tr>
						<tr>
							<td class="FormInputColor">Total</td>
							<td align="center" class="FormInputColor"><div id="grey-box"></div></td>
							<td class="FormInputColor">12</td>
						</tr>
						<tr>
							<td class="FormInputColorEven">Normal Cash</td>
							<td align="center" class="FormInputColorEven"><div id="green-box"></div></td>
							<td class="FormInputColorEven">7</td>
						</tr>
						<tr>
							<td class="FormInputColor">Low Cash</td>
							<td align="center" class="FormInputColor"><div id="gold-box"></div></td>
							<td class="FormInputColor">4</td>
						</tr>
						<tr>
							<td class="FormInputColorEven">Out of Cash</td>
							<td align="center" class="FormInputColorEven"><div id="red-box"></div></td>
							<td class="FormInputColorEven">1</td>
						</tr>
						<tr>
							<td class="FormInputColor"></td>
							<td align="center" class="FormInputColor"></td>
							<td class="FormInputColor"></td>
						</tr>
						<tr>
							<td class="FormInputColorEven"></td>
							<td align="center" class="FormInputColorEven"></td>
							<td class="FormInputColorEven"></td>
						</tr>
						<tr>
							<td class="FormInputColorEven"></td>
							<td align="center" class="FormInputColorEven"></td>
							<td class="FormInputColorEven"></td>
						</tr>							
					</table></div>
				</td>
			</tr>
		</table>
		<br>
	</div>
</html>