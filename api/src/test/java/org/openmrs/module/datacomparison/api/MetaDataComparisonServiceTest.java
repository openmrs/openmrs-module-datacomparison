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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import junit.framework.Assert;

import org.apache.poi.hssf.record.formula.functions.Char;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.ConceptAnswer;
import org.openmrs.EncounterType;
import org.openmrs.LocationAttributeType;
import org.openmrs.Order;
import org.openmrs.OrderType;
import org.openmrs.Patient;
import org.openmrs.PersonAttribute;
import org.openmrs.api.context.Context;
import org.openmrs.module.datacomparison.DataComparisonConsts;
import org.openmrs.module.datacomparison.ElementMeta;
import org.openmrs.module.datacomparison.api.impl.MetaDataComparisonServiceImpl;
import org.openmrs.test.BaseModuleContextSensitiveTest;

/**
 * This class tests methods in the MetaDataComparisonService class
 */
public class MetaDataComparisonServiceTest extends BaseModuleContextSensitiveTest {
	
	private MetaDataComparisonService metaDataComparisonService = null;
	
	private MetaDataComparisonServiceImpl metaDataComparisonServiceImpl = null;
	
	@Before
	public void runBeforeAllTests() throws Exception {
		if (metaDataComparisonService == null) {
			metaDataComparisonService = Context.getService(MetaDataComparisonService.class);
			metaDataComparisonServiceImpl = new MetaDataComparisonServiceImpl();
		}
	}
	
	@Test
	public void dummyTest() {
		Assert.assertTrue(true);
	}
	
/*	
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
	public void getChildElementMetaForEqualChildMeta() {
		
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
	public void getChildElementMetaForPartiallyEqualChildMeta() {
		
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
	public void getChildElementMetaForNonEqualChildMeta() {
		
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
	
	@Test
	public void getChildElementMetaForNullChild() {
		
		Map<String, ElementMeta> metaItems = new HashMap<String, ElementMeta>();
		ElementMeta existingItem = new ElementMeta();
		ElementMeta incomingItem = new ElementMeta();
		
		existingItem.setIsComplex(true);
		existingItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
		
		incomingItem.setIsComplex(false);
		incomingItem.setPropertyType(DataComparisonConsts.SIMPLE_DATA_TYPE);
		
		metaItems.put("existingItem", existingItem);
		metaItems.put("incomingItem", incomingItem);
		
		List<String> existingItemPropertyValue = new ArrayList<String>();
		List<String> incomingItemPropertyValue = new ArrayList<String>();
		
		existingItemPropertyValue.add("Dilgur");
		existingItemPropertyValue.add("Edger");
		existingItemPropertyValue.add("Torrin");
		
		Map<String, ElementMeta> result = metaDataComparisonService.getChildElementMeta(metaItems, (Object) existingItemPropertyValue, (Object) incomingItemPropertyValue);
		
		ElementMeta resultedExistingMeta = (ElementMeta) result.get("existingItem");
		ElementMeta resultedIncomingMeta = (ElementMeta) result.get("incomingItem");
		
		Assert.assertNotNull(resultedExistingMeta.getSubElmentMetaList());
		Assert.assertNull(resultedIncomingMeta);
		Assert.assertFalse(resultedExistingMeta.equals(resultedIncomingMeta));
		Assert.assertEquals(3, resultedExistingMeta.getSubElmentMetaList().size());
		
	}
*/	
	
	
	/////////////////////////////////////////////////////////////////////
	//                  Tests for private methods.
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * @see MetaDataComparisonServiceImpl#isSimpleDataType(Object, Class)
	 * @verifies True results with property value parameter
	 */
	@Test
	public void testIsSimpleDataTypeWhenPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {true, null});
		argsMap.put(1, new Object[] {(byte) 5, null});
		argsMap.put(2, new Object[] {'C', null});
		argsMap.put(3, new Object[] {(short) 4 , null});
		argsMap.put(4, new Object[] {1, null});
		argsMap.put(5, new Object[] {3.23f, null});
		argsMap.put(6, new Object[] {12L, null});
		argsMap.put(7, new Object[] {(double) 5.32, null});
		argsMap.put(8, new Object[] {(Boolean) false, null});
		argsMap.put(9, new Object[] {new Byte("7"), null});
		argsMap.put(10, new Object[] {new Character('e'), null});
		argsMap.put(11, new Object[] {new Short("3"), null});
		argsMap.put(12, new Object[] {new Integer(63), null});
		argsMap.put(13, new Object[] {new Float(23.56), null});
		argsMap.put(14, new Object[] {new Long(9), null});
		argsMap.put(15, new Object[] {new Double(10.23), null});
		argsMap.put(16, new Object[] {"String", null});
		argsMap.put(17, new Object[] {new Date(), null});
		argsMap.put(18, new Object[] {new Timestamp(12), null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isSimpleDataType", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isSimpleDataType(Object, Class)
	 * @verifies True results with property type parameter
	 */
	@Test
	public void testIsSimpleDataTypeWhenClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, boolean.class});
		argsMap.put(1, new Object[] {null, byte.class});
		argsMap.put(2, new Object[] {null, char.class});
		argsMap.put(3, new Object[] {null, short.class});
		argsMap.put(4, new Object[] {null, int.class});
		argsMap.put(5, new Object[] {null, float.class});
		argsMap.put(6, new Object[] {null, long.class});
		argsMap.put(7, new Object[] {null, double.class});
		argsMap.put(8, new Object[] {null, java.lang.Boolean.class});
		argsMap.put(9, new Object[] {null, java.lang.Byte.class});
		argsMap.put(10, new Object[] {null, java.lang.Character.class});
		argsMap.put(11, new Object[] {null, java.lang.Short.class});
		argsMap.put(12, new Object[] {null, java.lang.Integer.class});
		argsMap.put(13, new Object[] {null, java.lang.Float.class});
		argsMap.put(14, new Object[] {null, java.lang.Long.class});
		argsMap.put(15, new Object[] {null, java.lang.Double.class});
		argsMap.put(16, new Object[] {null, java.lang.String.class});
		argsMap.put(17, new Object[] {null, java.util.Date.class});
		argsMap.put(18, new Object[] {null, java.sql.Timestamp.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isSimpleDataType", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isSimpleDataType(Object, Class)
	 * @verifies False results with property value parameter
	 */
	@Test
	public void testIsSimpleDataTypeForFalseWhenComplexPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		List<String> list = new ArrayList<String>();
		list.add("element");
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "value");
		
		Patient patient = new Patient();
		
		argsMap.put(0, new Object[] {list, null});
		argsMap.put(1, new Object[] {map, null});
		argsMap.put(2, new Object[] {patient, null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isSimpleDataType", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isSimpleDataType(Object, Class)
	 * @verifies False results with property type parameter
	 */
	@Test
	public void testIsSimpleDataTypeForFalseWhenComplexClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, java.util.List.class});
		argsMap.put(1, new Object[] {null, java.util.Map.class});
		argsMap.put(2, new Object[] {null, org.openmrs.Patient.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isSimpleDataType", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isSimpleDataType(Object, Class)
	 * @verifies False results with null parameters
	 */
	@Test
	public void testIsSimpleDataTypeForFalseWhenBothArgumentsAreNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Method method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isSimpleDataType", new Class[] {Object.class, Class.class});
		method.setAccessible(true);
		boolean result = (Boolean) method.invoke(metaDataComparisonServiceImpl, new Object[] {null, null});
		
		Assert.assertFalse(result);
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isMap(Object, Class)
	 * @verifies True results with property value parameter
	 */
	@Test
	public void testIsMapWhenPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, String> hashMap = new HashMap<Integer, String>();
		hashMap.put(1, "Value1");
		
		Map<Integer, String> treeMap = new TreeMap<Integer, String>();
		treeMap.put(1, "Value2");
		
		Map<Integer, String> linkedHashMap = new LinkedHashMap<Integer, String>();
		linkedHashMap.put(1, "Value3");
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {hashMap, null});
		argsMap.put(1, new Object[] {treeMap, null});
		argsMap.put(2, new Object[] {linkedHashMap, null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isMap", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isMap(Object, Class)
	 * @verifies True results with property type parameter
	 */
	@Test
	public void testIsMapWhenClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, java.util.Map.class});
		argsMap.put(1, new Object[] {null, java.util.HashMap.class});
		argsMap.put(2, new Object[] {null, java.util.LinkedHashMap.class});
		argsMap.put(3, new Object[] {null, java.util.TreeMap.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isMap", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isMap(Object, Class)
	 * @verifies False results with property value parameter
	 */
	@Test
	public void testIsMapForFalseWhenPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		List<String> list = new ArrayList<String>();
		list.add("Element");
		
		Patient patient = new Patient();
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {"String", null});
		argsMap.put(1, new Object[] {1, null});
		argsMap.put(2, new Object[] {true, null});
		argsMap.put(3, new Object[] {new Double(23.5), null});
		argsMap.put(4, new Object[] {new Byte("8"), null});
		argsMap.put(5, new Object[] {new Date(), null});
		argsMap.put(6, new Object[] {new Timestamp(12), null});
		argsMap.put(7, new Object[] {list, null});
		argsMap.put(8, new Object[] {patient, null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isMap", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isMap(Object, Class)
	 * @verifies False results with property type parameter
	 */
	@Test
	public void testIsMapForFalseWhenClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, java.lang.String.class});
		argsMap.put(1, new Object[] {null, char.class});
		argsMap.put(2, new Object[] {null, boolean.class});
		argsMap.put(3, new Object[] {null, java.lang.Short.class});
		argsMap.put(4, new Object[] {null, java.lang.Double.class});
		argsMap.put(5, new Object[] {null, java.util.Date.class});
		argsMap.put(6, new Object[] {null, java.sql.Timestamp.class});
		argsMap.put(7, new Object[] {null, java.util.ArrayList.class});
		argsMap.put(8, new Object[] {null, org.openmrs.Patient.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isMap", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isMap(Object, Class)
	 * @verifies False results with property null parameters
	 */
	@Test
	public void testIsMapForFalseWhenBothArgumentsAreNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Method method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isMap", new Class[] {Object.class, Class.class});
		method.setAccessible(true);
		boolean result = (Boolean) method.invoke(metaDataComparisonServiceImpl, new Object[] {null, null});
		
		Assert.assertFalse(result);
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isOpenMrsObject(Object, Class)
	 * @verifies True results with property value parameter
	 */
	@Test
	public void testIsOpenMrsObjectWhenPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {new Patient(), null});
		argsMap.put(1, new Object[] {new ConceptAnswer(), null});
		argsMap.put(2, new Object[] {new LocationAttributeType(), null});
		argsMap.put(3, new Object[] {new EncounterType(), null});
		argsMap.put(4, new Object[] {new PersonAttribute(), null});
		argsMap.put(5, new Object[] {new Order(), null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isOpenMrsObject", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isOpenMrsObject(Object, Class)
	 * @verifies True results with property type parameter
	 */
	@Test
	public void testIsOpenMrsObjectWhenClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, org.openmrs.ConceptMap.class});
		argsMap.put(1, new Object[] {null, org.openmrs.Drug.class});
		argsMap.put(2, new Object[] {null, org.openmrs.PatientIdentifier.class});
		argsMap.put(3, new Object[] {null, org.openmrs.PersonAddress.class});
		argsMap.put(4, new Object[] {null, org.openmrs.EncounterProvider.class});
		argsMap.put(5, new Object[] {null, org.openmrs.RelationshipType.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isOpenMrsObject", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertTrue(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isOpenMrsObject(Object, Class)
	 * @verifies False results with property value parameter
	 */
	@Test
	public void testIsOpenMrsObjectForFalseWhenPropertyValueIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		List<String> list = new ArrayList<String>();
		list.add("Element");
		
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "Value");
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {"String", null});
		argsMap.put(1, new Object[] {1, null});
		argsMap.put(2, new Object[] {true, null});
		argsMap.put(3, new Object[] {new Double(23.5), null});
		argsMap.put(4, new Object[] {new Byte("8"), null});
		argsMap.put(5, new Object[] {new Date(), null});
		argsMap.put(6, new Object[] {new Timestamp(12), null});
		argsMap.put(7, new Object[] {list, null});
		argsMap.put(8, new Object[] {map, null});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isOpenMrsObject", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isOpenMrsObject(Object, Class)
	 * @verifies False results with property type parameter
	 */
	@Test
	public void testIsOpenMrsObjectForFalseWhenClassTypeIsAvailable() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Map<Integer, Object[]> argsMap = new HashMap<Integer, Object[]>();
		
		argsMap.put(0, new Object[] {null, java.lang.String.class});
		argsMap.put(1, new Object[] {null, char.class});
		argsMap.put(2, new Object[] {null, boolean.class});
		argsMap.put(3, new Object[] {null, java.lang.Short.class});
		argsMap.put(4, new Object[] {null, java.lang.Double.class});
		argsMap.put(5, new Object[] {null, java.util.Date.class});
		argsMap.put(6, new Object[] {null, java.sql.Timestamp.class});
		argsMap.put(7, new Object[] {null, java.util.ArrayList.class});
		argsMap.put(8, new Object[] {null, java.util.Map.class});
		
		Method method;
		boolean result;
		
		for (int i=0; i<argsMap.size(); i++) {
			
			method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isOpenMrsObject", new Class[] {Object.class, Class.class});
			method.setAccessible(true);
			result = (Boolean) method.invoke(metaDataComparisonServiceImpl, argsMap.get(i));
			
			Assert.assertFalse(result);
			
		}
		
	}
	
	/**
	 * @see MetaDataComparisonServiceImpl#isOpenMrsObject(Object, Class)
	 * @verifies False results with property null parameters
	 */
	@Test
	public void testIsOpenMrsObjectForFalseWhenBothArgumentsAreNull() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Class arr[] = {Object.class, Class.class};
		
		Method method = metaDataComparisonServiceImpl.getClass().getDeclaredMethod("isOpenMrsObject", new Class[] {Object.class, Class.class});
		method.setAccessible(true);
		boolean result = (Boolean) method.invoke(metaDataComparisonServiceImpl, new Object[] {null, null});
		
		Assert.assertFalse(result);
		
	}
	
}
