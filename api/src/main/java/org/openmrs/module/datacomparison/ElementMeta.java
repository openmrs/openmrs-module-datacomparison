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

import java.util.Date;

public class ElementMeta {

	private String propertyValue;
	private int level;
	private boolean isComplex;
	private int propertyType;
	
	public void setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
	}
	
	public String getPropertyValue() {
		return propertyValue;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setIsComplex(boolean isComplex) {
		this.isComplex = isComplex;
	}
	
	public boolean getIsComplex() {
		return isComplex;
	}
	
	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}
	
	public int getPropertyType() {
		return propertyType;
	}

}

