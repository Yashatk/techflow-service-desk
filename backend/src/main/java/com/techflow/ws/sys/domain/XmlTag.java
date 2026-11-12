package com.techflow.ws.sys.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class XmlTag extends KeyPair {
	public XmlTag(String key, String value) {
		super(key, value);
		// TODO Auto-generated constructor stub
	}

	String comment;
	
	List<KeyPair> attributes = null;
	List<XmlTag> tags = null;
	
	@JsonIgnore
	Integer pointer = 0;
	
	public XmlTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addAttribute(KeyPair attribute) {
		if(attributes == null) {
			attributes = new ArrayList<>();
		}
		attributes.add(attribute);
	}
	
	public void addTag(XmlTag tag) {
		if(tags == null) {
			tags = new ArrayList<>();
		}
		tags.add(tag);
	}
	
	public List<KeyPair> getAttributes() {
		return attributes;
	}
	public void setAttributes(List<KeyPair> attributes) {
		this.attributes = attributes;
	}
	public List<XmlTag> getTags() {
		return tags;
	}
	public void setTags(List<XmlTag> tags) {
		this.tags = tags;
	}

	public Integer getPointer() {
		return pointer;
	}

	public void setPointer(Integer pointer) {
		this.pointer = pointer;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
