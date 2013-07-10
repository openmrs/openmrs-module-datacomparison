package org.openmrs.module.datacomparison;

import java.util.Map;

public class RowMeta {

	private String propertyName;
	private Map<String, ElementMeta> metaItems;
	private boolean isSimilar;
	private int level;
	
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public String getPropertyName() {
		return this.propertyName;
	}
	
	public void setMetaItems(Map<String, ElementMeta> metaItems) {
		this.metaItems = metaItems;
	}
	
	public Map<String, ElementMeta> getMetaItems() {
		return metaItems;
	}
	
	public void setIsSimilar(boolean isSimilar) {
		this.isSimilar = isSimilar;
	}
	
	public boolean getIsSimilar() {
		return isSimilar;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}

}