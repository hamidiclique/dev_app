<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="s"%>

<c:choose>
	<c:when test="${empty message}">
	</c:when>
	<c:otherwise>
		<div id="alert-div" class="visible">
			<table align="center" width="100%">
				<tr>
					<td width="98%">${message}</td>
					<td><button onclick="fadeout()">X</button></td>
				</tr>
			</table>
		</div>
	</c:otherwise>
</c:choose>