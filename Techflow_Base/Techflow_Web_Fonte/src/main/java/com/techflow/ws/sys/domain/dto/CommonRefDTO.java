package com.techflow.ws.sys.domain.dto;

public abstract class CommonRefDTO extends CommonDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7362497061136086643L;
	
	private Integer id;
	private Integer st;
	private String code;
	private String name;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Integer return the st
     */
    public Integer getSt() {
        return st;
    }

    /**
     * @param st the st to set
     */
    public void setSt(Integer st) {
        this.st = st;
    }

}
