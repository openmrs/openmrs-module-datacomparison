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
package org.openmrs.module.datacomparison.api.impl;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.openmrs.OpenmrsObject;
import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.datacomparison.DataComparisonConsts;
import org.openmrs.module.datacomparison.ElementMeta;
import org.openmrs.module.datacomparison.RowMeta;
import org.openmrs.module.datacomparison.api.MetaDataComparisonService;
import org.openmrs.util.Reflect;

/**
 * Default implementation of {@link MetaDataComparisonService}.
 * <p>
 * This class should not be used on its own. The current implementation should be fetched from the
 * Context via <code>Context.getService(MetaDataComparisonService.class)</code>
 * 
 * @see org.openmrs.api.context.Context
 * @see org.openmrs.module.datacomparison.api.MetaDataComparisonService
 */
public class MetaDataComparisonServiceImpl extends BaseOpenmrsService implements MetaDataComparisonService {
	
	/**
	 * @throws SecurityException 
	 * @throws NoSuchFieldException 
	 * @see org.openmrs.module.datacomparison.api.MetaDataComparisonService#getRowMetaList(java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
    public List<RowMeta> getRowMetaList(Object existingItem, Object incomingItem) throws APIException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		
		List<RowMeta> rowMetaList = new ArrayList<RowMeta>();
		
		ElementMeta existingItemMeta = null;
		ElementMeta incomingItemMeta = null;
		RowMeta rowMeta = null;
		Map<String, ElementMeta> metaItems = null;
		
		Class c = existingItem.getClass();
		List<Field> fields = Reflect.getAllFields(c);
		
		for (int i=0; i<fields.size(); i++) {
			
			existingItemMeta = new ElementMeta();
			incomingItemMeta = new ElementMeta();
			rowMeta = new RowMeta();
			metaItems = new HashMap<String, ElementMeta>();
			
			fields.get(i).setAccessible(true);
			
			Object existingItemFieldValue = fields.get(i).get(existingItem);
			Object incomingItemFieldValue = fields.get(i).get(incomingItem);
			
			if (existingItemFieldValue != null) {
				
				existingItemMeta = getElementMetaObjectAfterSetProperties(existingItemMeta, existingItemFieldValue);
                
            } else {
            	existingItemMeta.setIsComplex(false);
            	existingItemMeta.setPropertyType(DataComparisonConsts.SIMPLE_DATA_TYPE);
            	existingItemMeta.setPropertyValue(DataComparisonConsts.NULL);
            }
			
			if (incomingItemFieldValue != null) {
				
				incomingItemMeta = getElementMetaObjectAfterSetProperties(incomingItemMeta, incomingItemFieldValue);
                
			} else {
				incomingItemMeta.setIsComplex(false);
            	incomingItemMeta.setPropertyType(DataComparisonConsts.SIMPLE_DATA_TYPE);
            	incomingItemMeta.setPropertyValue(DataComparisonConsts.NULL);
			}
			
			if ((existingItemFieldValue != null) && (incomingItemFieldValue != null)) {
				if (existingItemFieldValue.equals(incomingItemFieldValue)) {
                	rowMeta.setIsSimilar(true);
                } else {
                	rowMeta.setIsSimilar(false);
                }
			} else if ((existingItemFieldValue == null) && (incomingItemFieldValue == null)) {
				rowMeta.setIsSimilar(true);
			} else {
				rowMeta.setIsSimilar(false);
			}
			
			metaItems.put("existingItem", existingItemMeta);
			metaItems.put("incomingItem", incomingItemMeta);
			
			if (existingItemMeta.getIsComplex() || incomingItemMeta.getIsComplex()) {
				metaItems = getChildElementMeta(metaItems, existingItemFieldValue, incomingItemFieldValue, c, fields.get(i).getName());
			}
			
			rowMeta.setPropertyName(fields.get(i).getName());
			rowMeta.setMetaItems(metaItems);
			rowMeta.setLevel(0);
			
			rowMetaList.add(rowMeta);
			
		}
		
		return rowMetaList;
		
	}
	
	/**
	 * @see org.openmrs.module.datacomparison.api.MetaDataComparisonService#getChildElementMeta(
	 * 		java.util.Map<String, ElementMeta>, java.lang.Object, java.lang.Object, java.lang.Class, java.lang.String)
	 */
	public Map<String, ElementMeta> getChildElementMeta(
		Map<String, ElementMeta> metaItems, Object existingItemPropertyValue, Object incomingItemPropertyValue, Class clazz, String proprtyName
	) throws APIException, NoSuchFieldException, SecurityException {
		
		ElementMeta existingElementMeta = metaItems.get("existingItem");
		ElementMeta incomingElementMeta = metaItems.get("incomingItem");
		
		List<ElementMeta> existingSubElmentMetaList = null;
		List<ElementMeta> incomingSubElmentMetaList = null;
		
		// Both property values are not null
		if ((existingItemPropertyValue != null) && (incomingItemPropertyValue != null)) {
			
			// Collection data type
			if (metaItems.get("existingItem").getPropertyType() == DataComparisonConsts.COLLECTION_DATA_TYPE) {
				
				Collection<?> existingCollectionProperty = (Collection<?>) existingItemPropertyValue;
				Collection<?> incomingCollectionProperty = (Collection<?>) incomingItemPropertyValue;
				
				// Both properties are not empty
				if ((existingCollectionProperty.size() > 0) && (incomingCollectionProperty.size() > 0)) {
					
					// Both collections are equal case
					if (existingCollectionProperty.containsAll(incomingCollectionProperty)
						&& incomingCollectionProperty.containsAll(existingCollectionProperty)
					) {
						
						Class genericClass = (Class) ((ParameterizedType) clazz.getDeclaredField(proprtyName).getGenericType()).getActualTypeArguments()[0];
						
						if (isSimpleDataType(null, genericClass)) {
							
							existingSubElmentMetaList = new ArrayList<ElementMeta>();
							
							// Just iterate the items in collection
							for (Object obj : existingCollectionProperty) {
								
								ElementMeta em = new ElementMeta();
								
								em.setIsComplex(false);
								em.setLevel(1);
								em.setPropertyType(DataComparisonConsts.SIMPLE_DATA_TYPE);
								em.setPropertyValue(obj.toString());
								em.setSubElmentMetaList(null);
								
								existingSubElmentMetaList.add(em);
								
							}
							
							// Since both are same
							existingElementMeta.setSubElmentMetaList(existingSubElmentMetaList);
							incomingElementMeta.setSubElmentMetaList(existingSubElmentMetaList);
							
							metaItems.put("existingItem", existingElementMeta);
							metaItems.put("incomingItem", incomingElementMeta);
							
						}
						
					} else {
						
						int max = Math.max(((Collection<?>) existingItemPropertyValue).size(), ((Collection<?>) incomingItemPropertyValue).size());
						
					}
					
				} else {
					
				}
				
			} else if (metaItems.get("existingItem").getPropertyType() == DataComparisonConsts.MAP_DATA_TYPE) {
				
			} else if (metaItems.get("existingItem").getPropertyType() == DataComparisonConsts.OPENMRS_DATA_TYPE) {
				
			}
			
		} else { // One property value can be null
			
		}
		
		return metaItems;
		
	}
	
	/**
	 * Set the ElementMeta properties using the relevant metadata object property,
	 * and return ElementMeta object.
	 * 
	 * @param elementMetaItem the meta data for the considering object property
	 * @param data the considering property value
	 * @return ElementMeta 
	 */
	private ElementMeta getElementMetaObjectAfterSetProperties(ElementMeta elementMetaItem, Object data) {
		
		if (isSimpleDataType(data, null)) {
        	
			elementMetaItem.setIsComplex(false);
			elementMetaItem.setPropertyType(DataComparisonConsts.SIMPLE_DATA_TYPE);
			elementMetaItem.setPropertyValue(data.toString());
        	
        } else if (Reflect.isCollection(data)) {
        	
        	// Dummy code for testing
        	elementMetaItem.setPropertyType(DataComparisonConsts.COLLECTION_DATA_TYPE);
        	elementMetaItem.setPropertyValue("Collection Data Type");
        	elementMetaItem.setIsComplex(true);
        	
        } else if (isMap(data, null)) {
        	
        	// Dummy code for testing
        	elementMetaItem.setPropertyType(DataComparisonConsts.MAP_DATA_TYPE);
        	elementMetaItem.setPropertyValue("Map Data Type");
        	elementMetaItem.setIsComplex(true);
        	
        } else if (isOpenMrsObject(data, null)) {
        	
        	// Dummy code for testing
        	elementMetaItem.setPropertyType(DataComparisonConsts.OPENMRS_DATA_TYPE);
        	elementMetaItem.setPropertyValue("OpenMRS object");
        	elementMetaItem.setIsComplex(true);
        	
        } else {
        	
        	// Dummy code for testing
        	elementMetaItem.setPropertyType(3);
        	elementMetaItem.setPropertyValue("Undefined Property");
        	elementMetaItem.setIsComplex(false);
        	
        }
		
		return elementMetaItem;
		
	}
	
	/**
	 * Check whether the given object property value
	 * can be directly accessed or not.
	 * 
	 * @param data the object property value
	 * @return boolean
	 */
	private boolean isSimpleDataType(Object data, Class c) {
		
		Class clazz;
		
		if (data != null) {
			clazz = data.getClass();
		} else if (c != null) {
			clazz = c;
		} else {
			return false;
		}
		
		return clazz.isPrimitive() ||
			clazz == java.lang.Long.class ||
			clazz == java.lang.String.class ||
			clazz == java.lang.Integer.class ||
			clazz == java.lang.Boolean.class ||
			clazz == java.lang.Float.class ||
			clazz == java.lang.Double.class ||
			clazz == java.lang.Byte.class ||
			clazz == java.lang.Character.class ||
			clazz == java.lang.Short.class ||
			clazz == java.util.Date.class ||
			clazz == java.sql.Timestamp.class;
		
	}
	
	/**
	 * Check whether the object is OpenMRS DTO.
	 * 
	 * @param data the Object to check
	 * @return boolean
	 */
	private boolean isOpenMrsObject(Object data, Class c) {
		
		Class clazz;
		
		if (data != null) {
			clazz = data.getClass();
		} else if (c != null) {
			clazz = c;
		} else {
			return false;
		}
		
		return OpenmrsObject.class.isAssignableFrom(clazz);
		
	}
	
	/**
	 * Check whether the object is Map.
	 * 
	 * @param data the Object to check
	 * @return boolean
	 */
	private boolean isMap(Object data, Class c) {
		
		Class clazz;
		
		if (data != null) {
			clazz = data.getClass();
		} else if (c != null) {
			clazz = c;
		} else {
			return false;
		}
		
		return Map.class.isAssignableFrom(clazz);
		
	}
	
}
