<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/SCFG3300.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/box.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<script
	src="${pageContext.request.contextPath}/resources/js/SCFG3300.js"></script>
<script>
	
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
		</table>
		<br>
		<table width="80%" align="center">
			<tr>
				<td class="SummaryFormCell"><b>Total : 3</b></td>
				<td class="SummaryFormCell"><div id="green-box"></div></td>
				<td class="SummaryFormCell"><b>In Service - 0</b></td>
				<td class="SummaryFormCell"><div id="red-box"></div></td>
				<td class="SummaryFormCell"><b>Out of Service - 0</b></td>
				<td class="SummaryFormCell"><div id="maroon-box"></div></td>
				<td class="SummaryFormCell"><b>Link Down - 3</b></td>
				<td class="SummaryFormCell"><div id="gold-box"></div></td>
				<td class="SummaryFormCell"><b>Supervisory - 0 </b></td>
				<td class="SummaryFormCell"><div id="grey-box"></div></td>
				<td class="SummaryFormCell"><b>Non operational - 0 </b></td>
				<td class="SummaryFormCell"><div id="fuchsia-box"></div></td>
				<td class="SummaryFormCell"><b>Unknown - 0 </b></td>
			</tr>
		</table>
		<table width="80%" align="center">
			<tr>
				<td>
					<div id="tabs-container">
						<ul class="tabs-menu">
							<li class="current"><a href="#tab-1">State</a></li>
							<li><a href="#tab-2">Cash</a></li>
							<li><a href="#tab-3">Device</a></li>
							<li><a href="#tab-4">Supply</a></li>
							<li><a href="#tab-5">Make</a></li>
							<li><a href="#tab-6">Merge</a></li>
						</ul>
						<div class="tab">
							<div id="tab-1" class="tab-content">
								<p class="paragraph">Lorem ipsum dolor sit amet, consectetur adipiscing elit.
									Aliquam sit amet purus urna. Proin dictum fringilla enim, sit
									amet suscipit dolor dictum in. Maecenas porttitor, est et
									malesuada congue, ligula elit fermentum massa, sit amet porta
									odio est at velit. Sed nec turpis neque. Fusce at mi felis, sed
									interdum tortor.</p>
							</div>
							<div id="tab-2" class="tab-content">
								<p class="paragraph">Donec semper dictum sem, quis pretium sem malesuada non.
									Proin venenatis orci vel nisl porta sollicitudin. Pellentesque
									sit amet massa et orci malesuada facilisis vel vel lectus.
									Etiam tristique volutpat auctor. Morbi nec massa eget sem
									ultricies fermentum id ut ligula.</p>

							</div>
							<div id="tab-3" class="tab-content">
								<p class="paragraph">Duis egestas fermentum ipsum et commodo. Proin bibendum
									consectetur elit, hendrerit porta mi dictum eu. Vestibulum
									adipiscing euismod laoreet. Vivamus lobortis tortor a odio
									consectetur pulvinar. Proin blandit ornare eros dictum
									fermentum. Class aptent taciti sociosqu ad litora torquent per
									conubia nostra, per inceptos himenaeos.</p>
							</div>
							<div id="tab-4" class="tab-content">
								<p class="paragraph">Proin sollicitudin tincidunt quam, in egestas dui
									tincidunt non. Maecenas tempus condimentum mi, sed convallis
									tortor iaculis eu. Cras dui dui, tempor quis tempor vitae,
									ullamcorper in justo. Integer et lorem diam. Quisque consequat
									lectus eget urna molestie pharetra.</p>
							</div>
							<div id="tab-5" class="tab-content">
								<p class="paragraph">Make sollicitudin tincidunt quam, in egestas dui
									tincidunt non. Maecenas tempus condimentum mi, sed convallis
									tortor iaculis eu. Cras dui dui, tempor quis tempor vitae,
									ullamcorper in justo. Integer et lorem diam. Quisque consequat
									lectus eget urna molestie pharetra.</p>
							</div>
							<div id="tab-6" class="tab-content">
								<p class="paragraph">Merge sollicitudin tincidunt quam, in egestas dui
									tincidunt non. Maecenas tempus condimentum mi, sed convallis
									tortor iaculis eu. Cras dui dui, tempor quis tempor vitae,
									ullamcorper in justo. Integer et lorem diam. Quisque consequat
									lectus eget urna molestie pharetra.</p>
							</div>
						</div>
					</div>
				</td>
			</tr>
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
					</c:forEach> <%-- <a href="${uponelvl}"><input class="button" type="button" value="Return" /></a> --%>
				</td>
			</tr>
		</table>
	</div>

</body>
</html>