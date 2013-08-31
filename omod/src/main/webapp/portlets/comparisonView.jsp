<prop key="**/datacomparisonmodulesportlet.portlet">datacomparisonmodulesportletController</prop>

<%@ include file="/WEB-INF/template/include.jsp"%>

<openmrs:htmlInclude file="${pageContext.request.contextPath}/moduleResources/datacomparison/css/datacomparison.css"/>

<div id="comparisonViewContainer">
	<div id="classDescription">
		<label>Type</label>&nbsp;:&nbsp;<label class="className">${model.className}</label>
	</div>
	<br />
	<table>
		<c:choose>
			<c:when test="${model.rowMetaList != null }">
				<c:forEach var="rowMeta" items="${model.rowMetaList}" varStatus="status">
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
											
											<c:set var="cssClass" value="${(subElement.isSimilar eq true) ? 'green' : 'red'}" />
											<c:set var="childPropertyName" value="${(subElement.propertyName eq null) ? '' : subElement.propertyName}" />
											
											<span class="${cssClass}">
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${childImageSrc}" class="imageClass" />
												<label class="propertyName">${childPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subElement.propertyValue}</label>
												
												
												<!-- If 2nd level childs are there, render them under the 1st level child element -->
												<c:choose>
													<c:when test="${subElement.subElmentMetaList != null}">
														<div>
															<c:forEach var="subChildElement" items="${subElement.subElmentMetaList}" varStatus="stat_3">
															
																<c:choose>
																	<c:when test="${subChildElement.propertyType eq 0}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
																	</c:when>
																	<c:when test="${subChildElement.propertyType eq 1}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/squareBracket-green.jpg' : '/moduleResources/datacomparison/images/squareBracket-red.jpg'}" />
																	</c:when>
																	<c:when test="${subChildElement.propertyType eq 3}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/curlyBracket-green.jpg' : '/moduleResources/datacomparison/images/curlyBracket-red.jpg'}" />
																	</c:when>
																</c:choose>
																
																<c:set var="cssClass" value="${(subChildElement.isSimilar eq true) ? 'green' : 'red'}" />
																<c:set var="subChildPropertyName" value="${(subChildElement.propertyName eq null) ? '' : subChildElement.propertyName}" />
																
																<span class="${cssClass}">
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${subChildImageSrc}" class="imageClass" />
																	<label class="propertyName">${subChildPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subChildElement.propertyValue}</label>
																</span>
																<br/>
																
															</c:forEach>
														</div>
													</c:when>
												</c:choose>
												<!-- End of rendering 2nd level childs -->
												
											</span>
											<br/>
											
										</c:forEach>
									</div>
								</c:when>
							</c:choose>
							<!-- End of render childs -->
							 
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
	 										
	 										<c:set var="cssClass" value="${(subElement.isSimilar eq true) ? 'green' : 'red'}" />
	 										<c:set var="childPropertyName" value="${(subElement.propertyName eq null) ? '' : subElement.propertyName}" />
											
											<span class="${cssClass}">
												&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${childImageSrc}" class="imageClass" />
												<label class="propertyName">${childPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subElement.propertyValue}</label>
												
												<!-- If 2nd level childs are there, render them under the 1st level child element -->
												<c:choose>
													<c:when test="${subElement.subElmentMetaList != null}">
														<div>
															<c:forEach var="subChildElement" items="${subElement.subElmentMetaList}" varStatus="stat_4">
															
																<c:choose>
																	<c:when test="${subChildElement.propertyType eq 0}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/icon-marble-green.gif' : '/moduleResources/datacomparison/images/icon-marble-red.gif'}" />
																	</c:when>
																	<c:when test="${subChildElement.propertyType eq 1}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/squareBracket-green.jpg' : '/moduleResources/datacomparison/images/squareBracket-red.jpg'}" />
																	</c:when>
																	<c:when test="${subChildElement.propertyType eq 3}">
																		<c:set var="subChildImageSrc" value="${(subChildElement.isSimilar eq true) ? '/moduleResources/datacomparison/images/curlyBracket-green.jpg' : '/moduleResources/datacomparison/images/curlyBracket-red.jpg'}" />
																	</c:when>
																</c:choose>
																
																<c:set var="cssClass" value="${(subChildElement.isSimilar eq true) ? 'green' : 'red'}" />
																<c:set var="subChildPropertyName" value="${(subChildElement.propertyName eq null) ? '' : subChildElement.propertyName}" />
																
																<span class="${cssClass}">
																	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="${pageContext.request.contextPath}${subChildImageSrc}" class="imageClass" />
																	<label class="propertyName">${subChildPropertyName}</label>&nbsp;:&nbsp;<label class="propertyValue">${subChildElement.propertyValue}</label>
																</span>
																<br/>
																
															</c:forEach>
														</div>
													</c:when>
												</c:choose>
												<!-- End of rendering 2nd level childs -->
												
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

