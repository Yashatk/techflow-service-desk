package com.techflow.ws.sys.domain;

public class CoordXYZ {
	Integer x;
	Integer y;
	Integer z;
	String code;
	
	public CoordXYZ(Integer x, Integer y) {
		super();
		this.x = x;
		this.y = y;
	}
	public CoordXYZ(Integer x, Integer y, String code) {
		super();
		this.x = x;
		this.y = y;
		this.code = code;
	}
	public CoordXYZ(Integer x, Integer y, Integer z, String code) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
		this.code = code;
	}
	public CoordXYZ() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getZ() {
		return z;
	}
	public void setZ(Integer z) {
		this.z = z;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getX() {
		return x;
	}
	public void setX(Integer x) {
		this.x = x;
	}
	public Integer getY() {
		return y;
	}
	public void setY(Integer y) {
		this.y = y;
	}
	
	
}
