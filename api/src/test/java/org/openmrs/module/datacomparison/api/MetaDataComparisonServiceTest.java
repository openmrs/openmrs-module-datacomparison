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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openmrs.api.context.Context;
import org.openmrs.module.datacomparison.DataComparisonConsts;
import org.openmrs.module.datacomparison.ElementMeta;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * This class tests methods in the MetaDataComparisonService class
 */
public class MetaDataComparisonServiceTest extends BaseModuleContextSensitiveTest {
	
	private MetaDataComparisonService metaDataComparisonService = null;
	
	@Before
	public void runBeforeAllTests() throws Exception {
		if (metaDataComparisonService == null) {
			metaDataComparisonService = Context.getService(MetaDataComparisonService.class);
		}
	}
	
	@Test
	public void getChildElementMetaForNotNullChilddMeta() {
		
		Map<String, ElementMeta> metaItems = new HashMap<String, ElementMeta>();
		ElementMeta existingItem = new ElementMeta();
		ElementMeta incomingItem = new ElementMeta();
		
		existingItem.setIsComplex(true);
		existingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		incomingItem.setIsComplex(true);
		incomingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		metaItems.put("existingItem", existingItem);
		metaItems.put("incomingItem", incomingItem);
		
		List<String> existingItemPropertyValue = new ArrayList<String>();
		List<String> incomingItemPropertyValue = new ArrayList<String>();
		
		existingItemPropertyValue.add("ABC");
		existingItemPropertyValue.add("123");
		
		incomingItemPropertyValue.add("Dilgur");
		incomingItemPropertyValue.add("John");
		
		Map<String, ElementMeta> result = metaDataComparisonService.getChildElementMeta(metaItems, (Object) existingItemPropertyValue, (Object) incomingItemPropertyValue);
		
		ElementMeta resultedExistingMeta = (ElementMeta) result.get("existingItem");
		ElementMeta resultedIncomingMeta = (ElementMeta) result.get("incomingItem");
		
		Assert.assertNotNull(resultedExistingMeta.getSubElmentMetaList());
		Assert.assertNotNull(resultedIncomingMeta.getSubElmentMetaList());
		
	}
	
	@Test
	public void getChildElementMetaForEqualChilddMeta() {
		
		Map<String, ElementMeta> metaItems = new HashMap<String, ElementMeta>();
		ElementMeta existingItem = new ElementMeta();
		ElementMeta incomingItem = new ElementMeta();
		
		existingItem.setIsComplex(true);
		existingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		incomingItem.setIsComplex(true);
		incomingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		metaItems.put("existingItem", existingItem);
		metaItems.put("incomingItem", incomingItem);
		
		List<String> existingItemPropertyValue = new ArrayList<String>();
		List<String> incomingItemPropertyValue = new ArrayList<String>();
		
		existingItemPropertyValue.add("Dilgur");
		existingItemPropertyValue.add("John");
		existingItemPropertyValue.add("Jemmy");
		
		incomingItemPropertyValue.add("Dilgur");
		incomingItemPropertyValue.add("John");
		incomingItemPropertyValue.add("Jemmy");
		
		Map<String, ElementMeta> result = metaDataComparisonService.getChildElementMeta(metaItems, (Object) existingItemPropertyValue, (Object) incomingItemPropertyValue);
		
		ElementMeta resultedExistingMeta = (ElementMeta) result.get("existingItem");
		ElementMeta resultedIncomingMeta = (ElementMeta) result.get("incomingItem");
		
		Assert.assertNotNull(resultedExistingMeta.getSubElmentMetaList());
		Assert.assertNotNull(resultedIncomingMeta.getSubElmentMetaList());
		Assert.assertTrue(resultedExistingMeta.equals(resultedIncomingMeta));
		Assert.assertEquals(3, resultedExistingMeta.getSubElmentMetaList().size());
		Assert.assertEquals("John", resultedExistingMeta.getSubElmentMetaList().get(1));
		
	}
	
	@Test
	public void getChildElementMetaForPartiallyEqualChilddMeta() {
		
		Map<String, ElementMeta> metaItems = new HashMap<String, ElementMeta>();
		ElementMeta existingItem = new ElementMeta();
		ElementMeta incomingItem = new ElementMeta();
		
		existingItem.setIsComplex(true);
		existingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		incomingItem.setIsComplex(true);
		incomingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		metaItems.put("existingItem", existingItem);
		metaItems.put("incomingItem", incomingItem);
		
		List<String> existingItemPropertyValue = new ArrayList<String>();
		List<String> incomingItemPropertyValue = new ArrayList<String>();
		
		existingItemPropertyValue.add("Dilgur");
		existingItemPropertyValue.add("Edger");
		existingItemPropertyValue.add("Jemmy");
		
		incomingItemPropertyValue.add("Piruwi");
		incomingItemPropertyValue.add("John");
		incomingItemPropertyValue.add("Jemmy");
		
		Map<String, ElementMeta> result = metaDataComparisonService.getChildElementMeta(metaItems, (Object) existingItemPropertyValue, (Object) incomingItemPropertyValue);
		
		ElementMeta resultedExistingMeta = (ElementMeta) result.get("existingItem");
		ElementMeta resultedIncomingMeta = (ElementMeta) result.get("incomingItem");
		
		Assert.assertNotNull(resultedExistingMeta.getSubElmentMetaList());
		Assert.assertNotNull(resultedIncomingMeta.getSubElmentMetaList());
		Assert.assertFalse(resultedExistingMeta.equals(resultedIncomingMeta));
		Assert.assertEquals(3, resultedExistingMeta.getSubElmentMetaList().size());
		Assert.assertEquals("Jemmy", resultedExistingMeta.getSubElmentMetaList().get(0));
		Assert.assertFalse(resultedExistingMeta.getSubElmentMetaList().get(1).equals(resultedIncomingMeta.getSubElmentMetaList().get(1)));
		
	}
	
	@Test
	public void getChildElementMetaForNonEqualChilddMeta() {
		
		Map<String, ElementMeta> metaItems = new HashMap<String, ElementMeta>();
		ElementMeta existingItem = new ElementMeta();
		ElementMeta incomingItem = new ElementMeta();
		
		existingItem.setIsComplex(true);
		existingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		incomingItem.setIsComplex(true);
		incomingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		metaItems.put("existingItem", existingItem);
		metaItems.put("incomingItem", incomingItem);
		
		List<String> existingItemPropertyValue = new ArrayList<String>();
		List<String> incomingItemPropertyValue = new ArrayList<String>();
		
		existingItemPropertyValue.add("Dilgur");
		existingItemPropertyValue.add("Edger");
		existingItemPropertyValue.add("Torrin");
		
		incomingItemPropertyValue.add("Piruwi");
		incomingItemPropertyValue.add("John");
		
		Map<String, ElementMeta> result = metaDataComparisonService.getChildElementMeta(metaItems, (Object) existingItemPropertyValue, (Object) incomingItemPropertyValue);
		
		ElementMeta resultedExistingMeta = (ElementMeta) result.get("existingItem");
		ElementMeta resultedIncomingMeta = (ElementMeta) result.get("incomingItem");
		
		Assert.assertNotNull(resultedExistingMeta.getSubElmentMetaList());
		Assert.assertNotNull(resultedIncomingMeta.getSubElmentMetaList());
		Assert.assertFalse(resultedExistingMeta.equals(resultedIncomingMeta));
		Assert.assertEquals(3, resultedExistingMeta.getSubElmentMetaList().size());
		Assert.assertEquals(2, resultedIncomingMeta.getSubElmentMetaList().size());
		Assert.assertFalse(resultedExistingMeta.getSubElmentMetaList().get(1).equals(resultedIncomingMeta.getSubElmentMetaList().get(1)));
		
	}
	
}
