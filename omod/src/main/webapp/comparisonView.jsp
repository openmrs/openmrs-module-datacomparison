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
							
							<c:choose>
								<c:when test="${rowMeta.metaItems['existingItem'].subElmentMetaList != null}">
									<div>
										<c:forEach var="subElement" items="${rowMeta.metaItems['existingItem'].subElmentMetaList}" varStatus="stat_1">
											<c:set var="imageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
											<span>
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${imageSrc}" class="imageSrc" />${subElement.propertyValue}
											</span>
											<br/>
										</c:forEach>
									</div>
								</c:when>
							</c:choose>
							 
						</td>
						<td>
							<img src="${pageContext.request.contextPath}${imageSrc}" class="imageClass" />
							<label class="propertyName">${rowMeta.propertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${rowMeta.metaItems['incomingItem'].propertyValue}</label>
							
							<c:choose>
								<c:when test="${rowMeta.metaItems['incomingItem'].subElmentMetaList != null}">
									<div>
	 									<c:forEach var="subElement" items="${rowMeta.metaItems['incomingItem'].subElmentMetaList}" varStatus="stat_2">
	 										<c:set var="imageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
											<span>
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${imageSrc}" class="imageSrc" />${subElement.propertyValue}
											</span>
											<br/>
										</c:forEach>
									</div>
								</c:when>
							</c:choose>
							 
						</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><spring:message code="datacomparison.messages.noresultsfound" /></tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>

<%@ include file="/WEB-INF/template/footer.jsp"%>
