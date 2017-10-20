package com.avnet.alapps.systest.model;

import java.util.List;

public abstract class AbstractJtableListResponse {

	private String result;
    private String message;
    protected List<?> records;
    private int totalRecordCount;
    

	public String getResult() {
		return result;
	}
    
	public void setResult(String result) {
		this.result = result;
	}

	public int getTotalRecordCount() {
		return totalRecordCount;
	}

	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}

	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
    
}
