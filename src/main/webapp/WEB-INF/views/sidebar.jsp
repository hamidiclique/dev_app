<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!-- <div id="wrapper"> -->
<div id="side">
	<ul>
		<li class=""><a class="MenuMain" href="showModules">Main</a></li>
		<c:choose>
			<c:when test="${not empty modmap && empty funmap}">
				<c:set var="modulemap" value="${modmap}" />
				<c:forEach items="${funmodmap}" var="funmodmap" varStatus="count">
					<c:url var="link" value="viewFunctions">
						<c:param name="mid" value="${funmodmap.key}" />
						<c:param name="fid" value="" />
						<c:param name="sid" value="" />
					</c:url>
					<c:set var="moduleKey">${funmodmap.key}</c:set>
					<%-- <td class="FormInputColor"><a href="${contextPath}/${link}"><c:out
								value="${modulemap[moduleKey]}" /></a></td> --%>
					<li class=""><a class="MenuOther"
						href="${contextPath}/${link}"><c:out
								value="${modulemap[moduleKey]}" /></a></li>

				</c:forEach>
			</c:when>
			<c:when test="${not empty modmap && not empty funmap}">
				<li class=""><a class="MenuMain" href="showModules">Up One
						Level&nbsp;&nbsp;<img alt="arrow"
						src="${contextPath}/resources/img/up.png" height="10" width="12">
				</a></li>
				<c:set var="modulemap" value="${modmap}" />
				<c:forEach items="${funmodmap}" var="funmodmap" varStatus="count">
					<c:url var="link" value="viewFunctions">
						<c:param name="mid" value="${funmodmap.key}" />
						<c:param name="fid" value="" />
						<c:param name="sid" value="" />
					</c:url>
					<c:set var="moduleKey">${funmodmap.key}</c:set>
					<li class=""><a class="MenuOther"
						href="${contextPath}/${link}"><c:out
								value="${modulemap[moduleKey]}" /></a></li>
				</c:forEach>
			</c:when>
			<c:when test="${empty modmap && not empty funmap}">
				<c:choose>
					<c:when test="${empty url}">						
						<c:url var="goUp" value="viewFunctions">
							<c:param name="mid" value="${module}" />
							<c:param name="fid" value="" />
							<c:param name="sid" value="" />
						</c:url>
						<li class=""><a class="MenuMain" href="${contextPath}/${goUp}">Up
							One Level&nbsp;&nbsp;<img alt="arrow"
							src="${contextPath}/resources/img/up.png" height="10" width="12">
					</a></li>
					</c:when>
					<c:otherwise>
						<c:url var="goUp" value="viewScreen">
							<c:param name="moduleId" value="${module}" />
							<c:param name="functionId" value="${parentfunction}" />
						</c:url>
						<li class=""><a class="MenuMain" href="${contextPath}/${goUp}">Up
							One Level&nbsp;&nbsp;<img alt="arrow"
							src="${contextPath}/resources/img/up.png" height="10" width="12">
						</a></li>	
					</c:otherwise>
				</c:choose>
				<c:set var="functionmap" value="${funmap}" />
				<c:forEach items="${funlist}" var="fnc" varStatus="count">
					<c:url var="link" value="viewScreen">
						<c:param name="moduleId" value="${module}" />
						<c:param name="functionId" value="${fnc}" />
					</c:url>
					<c:set var="funkey">${fnc}</c:set>
					<li class=""><a class="MenuOther"
						href="${contextPath}/${link}"><c:out
								value="${functionmap[funkey]}" /></a></li>

				</c:forEach>
			</c:when>
			<c:otherwise>
			</c:otherwise>
		</c:choose>
	</ul>
</div>


