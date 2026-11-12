package com.techflow.ws.sys.domain;

import java.awt.Dimension;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

public class ListObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1913183093093512357L;
	
	private List<String> objects;
	private String target;
	private Dimension dimension;
	
	@JsonAlias({"quality", "compressRatio"})
	private float floatValue;
	
	@JsonAlias({"keepAspectRatio"})
	private Boolean boolValue;
	
	public List<String> getObjects() {
		return objects;
	}
	public void setObjects(List<String> objects) {
		this.objects = objects;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Dimension getDimension() {
		return dimension;
	}
	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	public float getFloatValue() {
		return floatValue;
	}
	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}
	public Boolean getBoolValue() {
		return boolValue;
	}
	public void setBoolValue(Boolean boolValue) {
		this.boolValue = boolValue;
	}
	
	
}
