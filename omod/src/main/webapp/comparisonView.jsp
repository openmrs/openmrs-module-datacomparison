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
					
					<!-- Set image for each row -->
					<c:choose>
						<c:when test="${rowMeta.isSimilar eq true}">
							<c:choose>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 0) || (rowMeta.metaItems['incomingItem'].propertyType eq 0)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/icon-marble-green.gif" />
								</c:when>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 1) || (rowMeta.metaItems['incomingItem'].propertyType eq 1)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/squareBracket-green.jpg" />
								</c:when>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 3) || (rowMeta.metaItems['incomingItem'].propertyType eq 3)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/curlyBracket-green.jpg" />
								</c:when>
							</c:choose>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 0) || (rowMeta.metaItems['incomingItem'].propertyType eq 0)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/icon-marble-red.gif" />
								</c:when>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 1) || (rowMeta.metaItems['incomingItem'].propertyType eq 1)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/squareBracket-red.jpg" />
								</c:when>
								<c:when test="${(rowMeta.metaItems['existingItem'].propertyType eq 3) || (rowMeta.metaItems['incomingItem'].propertyType eq 3)}">
									<c:set var="imageSrc" value="/moduleResources/datacomparison/images/curlyBracket-red.jpg" />
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
					
					<!-- Render table rows -->
					<tr class="${cssClass}" >
						<td>
							<img src="${pageContext.request.contextPath}${imageSrc}" class="imageClass" />
							<label class="propertyName">${rowMeta.propertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${rowMeta.metaItems['existingItem'].propertyValue}</label>
							
							<!-- If child elements are there, render them inside the cell -->
							<c:choose>
								<c:when test="${rowMeta.metaItems['existingItem'].subElmentMetaList != null}">
									<div>
										<c:forEach var="subElement" items="${rowMeta.metaItems['existingItem'].subElmentMetaList}" varStatus="stat_1">
										
											<c:choose>
												<c:when test="${subElement.propertyType eq 0}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
												</c:when>
												<c:when test="${subElement.propertyType eq 1}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/squareBracket-green.jpg' : '/moduleResources/datacomparison/images/squareBracket-red.jpg'}" />
												</c:when>
												<c:when test="${subElement.propertyType eq 3}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/curlyBracket-green.jpg' : '/moduleResources/datacomparison/images/curlyBracket-red.jpg'}" />
												</c:when>
											</c:choose>
											
											<c:set var="childPropertyName" value="${(subElement.propertyName eq null) ? '' : subElement.propertyName}" />
											
											<span>
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${childImageSrc}" class="imageSrc" />
												<label class="propertyName">${childPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subElement.propertyValue}</label>
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
	 										
	 										<c:choose>
												<c:when test="${subElement.propertyType eq 0}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
												</c:when>
												<c:when test="${subElement.propertyType eq 1}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/squareBracket-green.jpg' : '/moduleResources/datacomparison/images/squareBracket-red.jpg'}" />
												</c:when>
												<c:when test="${subElement.propertyType eq 3}">
													<c:set var="childImageSrc" value="${(subElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/curlyBracket-green.jpg' : '/moduleResources/datacomparison/images/curlyBracket-red.jpg'}" />
												</c:when>
											</c:choose>
	 										
	 										<c:set var="childPropertyName" value="${(subElement.propertyName eq null) ? '' : subElement.propertyName}" />
											
											<span>
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${childImageSrc}" class="imageSrc" />
												<label class="propertyName">${childPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subElement.propertyValue}</label>
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
