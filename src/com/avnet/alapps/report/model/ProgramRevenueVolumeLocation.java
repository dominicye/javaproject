package com.avnet.alapps.report.model;

import java.math.BigDecimal;

public class ProgramRevenueVolumeLocation {
	
	
	private String location = null;
	private String programOwner = null;
	private String program = null;
	private BigDecimal serviceRevenue = null;
	private BigDecimal salesRevenue = null;
	private BigDecimal unitVolume = null;
	private BigDecimal workOrderVolume = null;
	
	public ProgramRevenueVolumeLocation(String location, String programOwner,
			String program, BigDecimal serviceRevenue, BigDecimal salesRevenue,
			BigDecimal unitVolume, BigDecimal workOrderVolume) {
		this.location = location;
		this.programOwner = programOwner;
		this.program = program;
		this.serviceRevenue = serviceRevenue;
		this.salesRevenue = salesRevenue;
		this.unitVolume = unitVolume;
		this.workOrderVolume = workOrderVolume;
	}
	
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getProgramOwner() {
		return programOwner;
	}
	public void setProgramOwner(String programOwner) {
		this.programOwner = programOwner;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public BigDecimal getServiceRevenue() {
		return serviceRevenue;
	}
	public void setServiceRevenue(BigDecimal serviceRevenue) {
		this.serviceRevenue = serviceRevenue;
	}
	public BigDecimal getSalesRevenue() {
		return salesRevenue;
	}
	public void setSalesRevenue(BigDecimal salesRevenue) {
		this.salesRevenue = salesRevenue;
	}
	public BigDecimal getUnitVolume() {
		return unitVolume;
	}
	public void setUnitVolume(BigDecimal unitVolume) {
		this.unitVolume = unitVolume;
	}
	public BigDecimal getWorkOrderVolume() {
		return workOrderVolume;
	}
	public void setWorkOrderVolume(BigDecimal workOrderVolume) {
		this.workOrderVolume = workOrderVolume;
	}
	
	

}
