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

import java.util.Map;

/**
 * This DTO class contains meta data of a row in comparison view,
 * which represent the same property data of each meta data object.
 */
public class RowMeta {

	private String propertyName;
	private Map<String, ElementMeta> metaItems;
	private boolean isSimilar;
	private int level;
	
	/**
	 * Set the property name.
	 * 
	 * @param propertyName The string to set.
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	/**
	 * Get property name.
	 * 
	 * @return The propertyName.
	 */
	public String getPropertyName() {
		return this.propertyName;
	}
	
	/**
	 * Set the map, containing ElementMeta objects related to given meta data objects.
	 * 
	 * @param metaItems
	 */
	public void setMetaItems(Map<String, ElementMeta> metaItems) {
		this.metaItems = metaItems;
	}
	
	/**
	 * Get the map, containing ElementMeta objects related to given meta data objects.
	 * 
	 * @return The metaItems.
	 */
	public Map<String, ElementMeta> getMetaItems() {
		return metaItems;
	}
	
	/**
	 * Set the equality status of same property in the given objects.
	 * 
	 * @param isSimilar The boolean value to set.
	 */
	public void setIsSimilar(boolean isSimilar) {
		this.isSimilar = isSimilar;
	}
	
	/**
	 * Get the equality status of same property in the given objects.
	 * 
	 * @return The isSimilar. 
	 */
	public boolean getIsSimilar() {
		return isSimilar;
	}
	
	/**
	 * Set the level of the representing property.
	 * 
	 * @param level The integer to set.
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Get the level of the representing property.
	 * 
	 * @return The level.
	 */
	public int getLevel() {
		return level;
	}

}