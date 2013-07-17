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
package org.openmrs.module.datacomparison.api;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.datacomparison.RowMeta;

/**
 * Contains methods exposing the core functionality related to Metadata Comparison Module.
 * It is an API that can be used outside of the module.
 * <p>
 * Usage example:<br>
 * <code>
 * Context.getService(MetaDataComparisonService.class).getRowMetaList(existingItem, incomingItem);
 * </code>
 * 
 * @see org.openmrs.api.context.Context
 */
public interface MetaDataComparisonService extends OpenmrsService {
	
	/**
	 * Gets the object comparison result list.
	 * 
	 * @param existingItem the existing metadata object
	 * @param incomingItem the incoming metadata object
	 * @return List of RowMeta objects having comparison results
	 * @throws APIException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	List<RowMeta> getRowMetaList(Object existingItem, Object incomingItem) throws APIException, IllegalArgumentException, IllegalAccessException;
	
}
