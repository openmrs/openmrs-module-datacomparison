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

/**
 * This DTO class is used to represent an objects with simple properties. 
 */
public class SimpleObject {
	
	private int simpleInt;
	private float simpleFloat;
	private Boolean simpleBoolean;
	private Date simpleDate;
	private String simpleString;
	
	/**
	 * Set integer value.
	 * 
	 * @param simpleInt The integer value to set.
	 */
	public void setSimpleInt(int simpleInt) {
		this.simpleInt = simpleInt;
	}
	
	/**
	 * Get integer value.
	 * 
	 * @return The simpleInt.
	 */
	public int getSimpleInt() {
		return simpleInt;
	}
	
	/**
	 * Set float value.
	 * 
	 * @param simpleFloat The float value to set.
	 */
	public void setSimpleFloat(float simpleFloat) {
		this.simpleFloat = simpleFloat;
	}
	
	/**
	 * Get float value.
	 * 
	 * @return The simpleFloat
	 */
	public float getSimpleFloat() {
		return simpleFloat;
	}
	
	/**
	 * Set Boolean value.
	 * 
	 * @param simpleBoolean The Boolean to set.
	 */
	public void setSimpleBoolean(Boolean simpleBoolean) {
		this.simpleBoolean = simpleBoolean;
	}
	
	/**
	 * Get Boolean value.
	 * 
	 * @return simpleBoolean;
	 */
	public Boolean getSimpleBoolean() {
		return simpleBoolean;
	}
	
	/**
	 * Set Date value.
	 * 
	 * @param simpleDate The Date to set.
	 */
	public void setSimpleDate(Date simpleDate) {
		this.simpleDate = simpleDate;
	}
	
	/**
	 * Get Date value.
	 * 
	 * @return The simpleDate.
	 */
	public Date getSimpleDate() {
		return simpleDate;
	}
	
	/**
	 * Set String value.
	 * 
	 * @param simpleString The String to set.
	 */
	public void setSimpleString(String simpleString) {
		this.simpleString = simpleString;
	}
	
	/**
	 * Get String value.
	 * 
	 * @return The simpleString.
	 */
	public String getSimpleString() {
		return simpleString;
	}
	
}
