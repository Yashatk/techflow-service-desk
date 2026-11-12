package com.techflow.ws.sys.domain.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonDTO extends BaseDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6706754445763535877L;
	
	@JsonProperty(value="idinsertoperator")
	private Integer createdBy;
	//@JsonProperty(access = Access.WRITE_ONLY)
	//@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	@JsonProperty(value="insertdate")
	private Date createdIn;
	@JsonProperty(value="idupdateoperator")
	private Integer modifiedBy;
	@JsonProperty(value="updatedate")
	private Date modifiedIn;
	
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedIn() {
		return createdIn;
	}
	public void setCreatedIn(Date createdIn) {
		this.createdIn = createdIn;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getModifiedIn() {
		return modifiedIn;
	}
	public void setModifiedIn(Date modifiedIn) {
		this.modifiedIn = modifiedIn;
	}
	
}
