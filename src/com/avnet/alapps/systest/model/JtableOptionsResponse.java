package com.avnet.alapps.systest.model;

import java.util.List;

public class JtableOptionsResponse {
	private String result;
    private String message;
    private List<JtableOptionBean> options;
    
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<JtableOptionBean> getOptions() {
		return options;
	}
	public void setOptions(List<JtableOptionBean> options) {
		this.options = options;
	}
	
}
