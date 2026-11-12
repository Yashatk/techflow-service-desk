package com.techflow.ws.sys.domain.dto;

public class DataDTO {
    private String label;
    private Integer value;

    public DataDTO() {
        
    }

    public DataDTO(String label, Integer value) {
        this.label = label;
        this.value = value;
    }


    /**
     * @return String return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return Integer return the value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}
