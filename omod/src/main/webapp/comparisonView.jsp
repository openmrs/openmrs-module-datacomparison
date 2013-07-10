<%@ include file="/WEB-INF/template/include.jsp"%>

<%@ include file="/WEB-INF/template/header.jsp"%>

<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/datacomparison/css/datacomparison.css"/>

<h2><spring:message code="datacomparison.index.title" /></h2>

<br/>

<div id="comparisonViewContainer">
	<div id="classDescription">
		<label>Type</label>&nbsp;:&nbsp;<label class="className">${className}</label>
	</div>
	<br />
	<table>
		<c:choose>
			<c:when test="${rowMetaList != null }">
				<c:forEach var="rowMeta" items="${rowMetaList}" varStatus="status">
					<c:set var="cssClass" value="${(rowMeta.isSimilar eq true) ? 'green' : 'red'}" />
					<c:set var="imageSrc" value="${(rowMeta.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
					<tr class="${cssClass}" >
						<td>
							<img src="${pageContext.request.contextPath}${imageSrc}" class="imageClass" />
							<label class="propertyName">${rowMeta.propertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${rowMeta.metaItems['existingItem'].propertyValue}</label>
						</td>
						<td>
							<img src="${pageContext.request.contextPath}${imageSrc}" class="imageClass" />
							<label class="propertyName">${rowMeta.propertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${rowMeta.metaItems['incomingItem'].propertyValue}</label>
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>No Results Found</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
