<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:include page="base.jsp"></jsp:include>
<jsp:include page="sidebar.jsp"></jsp:include>
<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/atm.css">

<script
	src="${pageContext.request.contextPath}/resources/js/SCFG3200.js"></script>
<script>
	$(document).ready(function() {
		$("#atmInstallDate").datepicker();
	});
</script>
<style>
.required {
	color: red;
}

#side ul {
	height: 815px;
}

#table-two {
	border: 0px solid #DCDCDC;
}

/* #table-three {
	border: 2px solid #DCDCDC;
} */
</style>
</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<div class="rightPart">
		<c:choose>
			<c:when test="${empty message}">
				<table width="80%" align="center">
					<tr>
						<td class="PageHeader">${functionDesc}</td>
					</tr>
				</table>
				<br>

				<form:form action="" modelAttribute="">
					<table class="TableContent" width="80%" align="center"
						id="table-one">
						<tr>
							<th class="FormTitleBGColor" colspan="6">Basic Information</th>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>ATM ID:
								</div></th>
							<td><input type="text" name="pid" required></input></td>

							<th><div class="shift-right">
									<span class="required">*</span>Location:
								</div></th>
							<td><input type="text" maxlength="18" name="street" required></input></td>

							<th><div class="shift-right">
									<span class="required">*</span>Branch:
								</div></th>
							<td><select>
									<c:forEach var="item" items="${branchOptions}">
										<option value="${item.key}"><c:out
												value="${item.value}" /></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>City:
								</div></th>
							<td><input type="text" maxlength="13" name="city" required></input></td>
							<th><div class="shift-right">
									<span class="required">*</span>IP Address:
								</div></th>
							<td><input type="text" maxlength="127" name="remoteAddress"
								required></input></td>
							<th><div class="shift-right">
									<span class="required">*</span>Comms Type:
								</div></th>
							<td><select>
									<c:forEach var="item" items="${protoOptions}">
										<option value="${item.key}"><c:out
												value="${item.value}" /></option>
									</c:forEach>
							</select></td>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>Owner:
								</div></th>
							<td><input type="text" name="owner" required></input></td>
							<th><div class="shift-right">
									<span class="required">*</span>Make:
								</div></th>
							<td><select>
									<option value="">--PLEASE SELECT--</option>
							</select></td>
							<th><div class="shift-right">
									<span class="required">*</span>Model:
								</div></th>
							<td><input type="text" name="model" required></input></td>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>Max Notes Presented:
								</div></th>
							<td><select>
									<option value="">--PLEASE SELECT--</option>
							</select></td>
							<th><div class="shift-right">
									<span class="required">*</span>ATM Installation Date:
								</div></th>
							<td><input id="atmInstallDate" name="atmInstallDate"></input></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th colspan="6"></th>
						</tr>
						<tr>
							<th class="FormTitleBGColor" colspan="6">Supervisor Info</th>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>Supervisor Name:
								</div></th>
							<td><input type="text" name="model" required></input></td>
							<th><div class="shift-right">
									<span class="required">*</span>Contact:
								</div></th>
							<td><input type="text" name="model" required></input></td>
							<th><div class="shift-right">
									<span class="required">*</span>Email:
								</div></th>
							<td><input type="text" name="model" required></input></td>
						</tr>
						<tr>
							<th></th>
							<td></td>
							<th></th>
							<td></td>
							<th></th>
							<td>name@domain.com
						</tr>

						<tr>
							<th class="FormTitleBGColor" colspan="6">Application /
								Drivers</th>
						</tr>
						<tr>
							<th><div class="shift-right">
									<span class="required">*</span>Application Type:
								</div></th>
							<td><select>
									<option value="">--PLEASE SELECT--</option>
							</select></td>
							<th><div class="shift-right">
									<span class="required">*</span>Dowload Image:
								</div></th>
							<td><select>
									<option value="">--PLEASE SELECT--</option>
							</select></td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th colspan="6"></th>
						</tr>
						<tr>
							<th class="FormTitleBGColor" colspan="6">Devices</th>
						</tr>
						<tr style="background-color: #FFF;">
							<td colspan="6"><br></td>
						</tr>
						<tr>
							<th colspan="2">
								<table align="center" width="100%" id="table-two">
									<tr>
										<th class="SubFormCellBGColor">Select</th>
										<th class="SubFormCellBGColor">Device</th>
										<th class="SubFormCellBGColor">Fatal</th>
									</tr>
									<tr>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value="" checked></td>
										<td class="SubFormInputColor">Camera 1</td>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value=""></td>
									</tr>
									<tr>
										<td class="SubFormInputColorEven"><input type="checkbox"
											name="" value="" checked></td>
										<td class="SubFormInputColorEven">Camera 2</td>
										<td class="SubFormInputColorEven"><input type="checkbox"
											name="" value=""></td>
									</tr>
									<tr>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value="" checked></td>
										<td class="SubFormInputColor">Camera 3</td>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value=""></td>
									</tr>
									<tr>
										<td class="SubFormInputColorEven"><input type="checkbox"
											name="" value="" checked></td>
										<td class="SubFormInputColorEven">Camera 4</td>
										<td class="SubFormInputColorEven"><input type="checkbox"
											name="" value=""></td>
									</tr>
									<tr>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value="" checked></td>
										<td class="SubFormInputColor">Disk 1</td>
										<td class="SubFormInputColor"><input type="checkbox"
											name="" value=""></td>
									</tr>
								</table>
							</th>
							<th colspan="4">
								<div>
									<table align="center" width="40%" id="table-three">
										<tr>
											<th><input type="radio" name="transaction" value="wth" /><span
												style="vertical-align: bottom;">&nbsp;Withdrawal</span></th>
											<th><input type="radio" name="transaction" value="dep" /><span
												style="vertical-align: bottom;">&nbsp;Deposit</span></th>
										</tr>
									</table>
									<table align="center" width="100%" id="table-four">
										<tr>
											<th class="FormTitleBGColor"></th>
											<th class="FormTitleBGColor">Currency</th>
											<th class="FormTitleBGColor">Denomination</th>
											<th class="FormTitleBGColor">Threshold</th>
										</tr>
										<tr>
											<td class="SubFormInputColor"><div class="shift-right">
													<span class="required">*</span>Cassette 1:
												</div></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="pointer" items="${currencyOptions}">
														<option value="${pointer.key}"><c:out
																value="${pointer.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="item" items="${dvalOptions}">
														<option value="${item.key}"><c:out
																value="${item.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><input type="text"
												name="model" required></input></td>
										</tr>
										<tr>
											<td class="SubFormInputColor"><div class="shift-right">
													<span class="required">*</span>Cassette 2:
												</div></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="pointer" items="${currencyOptions}">
														<option value="${pointer.key}"><c:out
																value="${pointer.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="item" items="${dvalOptions}">
														<option value="${item.key}"><c:out
																value="${item.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><input type="text"
												name="model" required></input></td>
										</tr>
										<tr>
											<td class="SubFormInputColor"><div class="shift-right">
													Cassette 3:</div></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="pointer" items="${currencyOptions}">
														<option value="${pointer.key}"><c:out
																value="${pointer.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="item" items="${dvalOptions}">
														<option value="${item.key}"><c:out
																value="${item.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><input type="text"
												name="model" required></input></td>
										</tr>
										<tr>
											<td class="SubFormInputColor"><div class="shift-right">
													Cassette 4:</div></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="pointer" items="${currencyOptions}">
														<option value="${pointer.key}"><c:out
																value="${pointer.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><select>
													<!-- <option value="">--PLEASE SELECT--</option> -->
													<c:forEach var="item" items="${dvalOptions}">
														<option value="${item.key}"><c:out
																value="${item.value}" /></option>
													</c:forEach>
											</select></td>
											<td class="FormInputColor"><input type="text"
												name="model" required></input></td>
										</tr>
									</table>
								</div>
							</th>
						</tr>
					</table>
					<table align="center" width="80%">
						<tr>
							<td>
								<p>
									<input type="submit" class="button" id="btnAddNewUser"
										value="Add" /> <input type="reset" class="button"
										value="Reset" /> <%-- <a href="${uponelvl}"><input
										class="button" type="button" value="Return" /> </a> --%>
								</p>
							</td>
						</tr>
					</table>
				</form:form>
			</c:when>
			<c:otherwise>
				<div id="alert-div" class="visible">
					<table align="center" width=100%">
						<tr>
							<td width="98%">${message}</td>
							<td><button onclick="fadeout()">X</button></td>
						</tr>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>

</body>
</html>