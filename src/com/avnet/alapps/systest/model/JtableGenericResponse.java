package com.avnet.alapps.systest.model;

public class JtableGenericResponse {
	 	private String result;
	    private Object record;
	    private String message;
	    
	    public JtableGenericResponse(){}

	    public JtableGenericResponse(String result) {
	        this.result = result;
	    }

	    public JtableGenericResponse(String result, Object record) {
	        this.result = result;
	        this.record = record;
	    }

	    public JtableGenericResponse(String result, String message) {
	        this.result = result;
	        this.message = message;
	    }

	    public String getResult() {
	        return result;
	    }

	    public void setResult(String result) {
	        this.result = result;
	    }

	    public Object getRecord() {
	        return record;
	    }

	    public void setRecord(Object record) {
	        this.record = record;
	    }   


	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
