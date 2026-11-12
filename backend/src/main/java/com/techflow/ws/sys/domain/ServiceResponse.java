package com.techflow.ws.sys.domain;

import java.time.Instant;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

public class ServiceResponse<I> {

	private Integer code;
	private I obj;
	private Instant startDate;
	private Instant endDate;
	private String message;
	private HttpStatus httpStatus;
	private HttpHeaders httpHeaders;
	private Long totalRows;
	private Long processed;
	
	public ServiceResponse() {
		this.code = 0;
		this.startDate = Instant.now();
		this.message = "";
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.httpHeaders = new HttpHeaders();
	}
	
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public I getObj() {
		return obj;
	}
	public void setObj(I obj) {
		this.obj = obj;
		this.endDate = Instant.now();
	}
	public Instant getStartDate() {
		return startDate;
	}
	public void setStartDate(Instant startDate) {
		this.startDate = startDate;
	}
	public Instant getEndDate() {
		return endDate;
	}
	public void setEndDate(Instant endDate) {
		this.endDate = endDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
		this.endDate = Instant.now();
	}
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public void setErrorMessage(String prefix, String suffix, List<String> fields) {
		String errorMessage = "";
		String sep = "";
		for (String field : fields) {
			errorMessage += sep + field;
			sep = ", ";
		}
		this.message = prefix + errorMessage + suffix;
		this.endDate = Instant.now();
	}
	
	public void setOK() {
		this.code = 1;
		this.httpStatus = HttpStatus.OK;
		this.endDate = Instant.now();
	}
	
	public void setError() {
		this.code = -1;
		this.httpStatus = HttpStatus.BAD_REQUEST;
		this.endDate = Instant.now();
	}

	public Long getTotalRows() {
		return totalRows;
	}

	public void setTotalRows(Long totalRows) {
		this.totalRows = totalRows;
	}

	public Long getProcessed() {
		return processed;
	}

	public void setProcessed(Long processed) {
		this.processed = processed;
	}

	public HttpHeaders getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public boolean isOK() {
		return this.code == 1;
	}

	
	
}
