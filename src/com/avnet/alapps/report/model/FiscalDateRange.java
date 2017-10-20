package com.avnet.alapps.report.model;

public class FiscalDateRange {
	private String yearToDateStart = null;
	private String monthStart = null;
	private String monthEnd = null;

	public FiscalDateRange(String yearToDateStart, String monthStart, String monthEnd) {
		this.yearToDateStart = yearToDateStart;
		this.monthStart = monthStart;
		this.monthEnd = monthEnd;
	}
	
	public String getYearToDateStart() {
		return yearToDateStart;
	}
	public void setYearToDateStart(String yearToDateStart) {
		this.yearToDateStart = yearToDateStart;
	}
	public String getMonthStart() {
		return monthStart;
	}
	public void setMonthStart(String monthStart) {
		this.monthStart = monthStart;
	}
	public String getMonthEnd() {
		return monthEnd;
	}
	public void setMonthEnd(String monthEnd) {
		this.monthEnd = monthEnd;
	}
	
}