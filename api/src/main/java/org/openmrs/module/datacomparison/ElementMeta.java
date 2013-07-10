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

