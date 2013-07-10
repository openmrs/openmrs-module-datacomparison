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

public class SimpleObject {
	
	private int simpleInt;
	private float simpleFloat;
	private Boolean simpleBoolean;
	private Date simpleDate;
	private String simpleString;
	
	public void setSimpleInt(int simpleInt) {
		this.simpleInt = simpleInt;
	}
	
	public int getSimpleInt() {
		return simpleInt;
	}
	
	public void setSimpleFloat(float simpleFloat) {
		this.simpleFloat = simpleFloat;
	}
	
	public float getSimpleFloat() {
		return simpleFloat;
	}
	
	public void setSimpleBoolean(Boolean simpleBoolean) {
		this.simpleBoolean = simpleBoolean;
	}
	
	public Boolean getSimpleBoolean() {
		return simpleBoolean;
	}
	
	public void setSimpleDate(Date simpleDate) {
		this.simpleDate = simpleDate;
	}
	
	public Date getSimpleDate() {
		return simpleDate;
	}
	
	public void setSimpleString(String simpleString) {
		this.simpleString = simpleString;
	}
	
	public String getSimpleString() {
		return simpleString;
	}
	
}
