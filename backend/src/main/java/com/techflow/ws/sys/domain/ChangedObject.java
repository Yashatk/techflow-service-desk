package com.techflow.ws.sys.domain;

import java.util.ArrayList;
import java.util.List;

public class ChangedObject {
	private List<String> changed = new ArrayList<>();
	//private CommonDTO dto;
	
	public void add(String field) {
		this.changed.add(field);
	}
	
	public List<String> getChanged() {
		return changed;
	}
	public void setChanged(List<String> changed) {
		this.changed = changed;
	}
	
	public Boolean changed() {
		return changed.size() > 0;
	}
	
	
}
