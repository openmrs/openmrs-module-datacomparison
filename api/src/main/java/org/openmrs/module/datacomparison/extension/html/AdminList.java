/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.datacomparison.extension.html;

import java.util.HashMap;
import java.util.Map;

import org.openmrs.api.context.Context;
import org.openmrs.module.Extension;
import org.openmrs.module.web.extension.AdministrationSectionExt;

/**
 * This class defines the links that will appear on the administration page under the
 * "datacomparison.title" heading. This extension is enabled by defining (uncommenting) it in the
 * /metadata/config.xml file.
 */
public class AdminList extends AdministrationSectionExt {
	
	private static final String MESSAGE_CODE_DATACOMPARISON_TITLE = "datacomparison.title";
	
	private static final String MESSAGE_CODE_COMPARISON_VIEW_LINK = "datacomparison.link.comparisonview";
	
	private static final String COMAPARISON_VIEW_URL = "module/datacomparison/datacomparisonmoduleLink.form";
	
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getMediaType()
	 */
	public Extension.MEDIA_TYPE getMediaType() {
		return Extension.MEDIA_TYPE.html;
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getTitle()
	 */
	public String getTitle() {
		return MESSAGE_CODE_DATACOMPARISON_TITLE;
	}
	
	/**
	 * @see org.openmrs.module.web.extension.AdministrationSectionExt#getLinks()
	 */
	public Map<String, String> getLinks() {
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put(
			COMAPARISON_VIEW_URL,
			Context.getMessageSourceService().getMessage(MESSAGE_CODE_COMPARISON_VIEW_LINK)
		);
		
		map.put(
			"module/datacomparison/datacomparisonmoduleLink-simpleData.form",
			"Simple Comparison View"
		);
		
		return map;
	}
	
}
