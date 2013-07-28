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
package org.openmrs.module.datacomparison;

import java.util.List;

/**
 * This DTO class contains the meta data of a property in 
 * given meta data object in data comparison view.
 */
public class ElementMeta {

	private String propertyValue;
	private int level;
	private boolean isComplex;
	private int propertyType;
	private boolean isSimilar;
	private List<ElementMeta> subElmentMetaList = null;
	
	/**
	 * Set the property value of particular element.
	 * 
	 * @param propertyValue The String to set.
	 */
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	/**
	 * Get the property value.
	 * 
	 * @return The propertyValue.
	 */
	public String getPropertyValue() {
		return propertyValue;
	}
	
	/**
	 * Set the level of element.
	 * 
	 * @param level The integer to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Get the level of element.
	 * 
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}
	
	/**
	 * Set the element complex status.
	 * 
	 * @param isComplex The boolean value to set.
	 */
	public void setIsComplex(boolean isComplex) {
		this.isComplex = isComplex;
	}
	
	/**
	 * Get the element complex status.
	 * 
	 * @return The isComplex.
	 */
	public boolean getIsComplex() {
		return isComplex;
	}
	
	/**
	 * Set the property type of the element.
	 * 
	 * @param propertyType The integer to set.
	 */
	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}
	
	/**
	 * Get the property type of the element.
	 * 
	 * @return The propertyType.
	 */
	public int getPropertyType() {
		return propertyType;
	}
	
	/**
	 * Set the meta list of child elements for a non-simple data type property.
	 * 
	 * @param subElmentMetaList The list of ElementMeta objects.
	 */
	public void setSubElmentMetaList(List<ElementMeta> subElmentMetaList) {
	    this.subElmentMetaList = subElmentMetaList;
    }
	
	/**
	 * Get the meta list of child elements in a non-simple data type property.
	 * 
	 * @return List of ElementMeta objects.
	 */
	public List<ElementMeta> getSubElmentMetaList() {
	    return subElmentMetaList;
    }
	
	/**
	 * Set the similarity status of the element, comparing relevant child of collection type properties.
	 * 
	 * @param isSimilar The boolean value to set.
	 */
	public void setIsSimilar(boolean isSimilar) {
	    this.isSimilar = isSimilar;
    }
	
	/**
	 * Get the similarity status of the element, comparing relevant child of collection type properties.
	 * 
	 * @return The isSimilar.
	 */
	public boolean getIsSimilar() {
	    return isSimilar;
    }
	
}

