package com.avnet.alapps.report.model;

import java.math.BigDecimal;

public class RevenueVolumeLocation {
	private String location = null;
	private BigDecimal serviceRevenue = null;
	private BigDecimal salesRevenue = null;
	private BigDecimal unitVolume = null;
	private BigDecimal workOrderVolume = null;
	
	public RevenueVolumeLocation(String location, BigDecimal serviceRevenue,
			BigDecimal salesRevenue, BigDecimal unitVolume, BigDecimal workOrderVolume) {
		this.location = location;
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
