package com.avnet.alapps.report.model;

import java.math.BigDecimal;

public class RevenueVolumeLocationCustomerDivision {
	
	private String location = null;
	private String programOwner = null;
	private BigDecimal customerNumber = null;
	private String shipTo = null;
	private String division = null;
	private BigDecimal serviceRevenue = null;
	private BigDecimal salesRevenue = null;
	private BigDecimal unitVolume = null;
	private BigDecimal workOrderVolume = null;
	
	public RevenueVolumeLocationCustomerDivision(String location,
			String programOwner, BigDecimal customerNumber, String shipTo,
			String division, BigDecimal serviceRevenue, BigDecimal salesRevenue,
			BigDecimal unitVolume, BigDecimal workOrderVolume) {

		this.location = location;
		this.programOwner = programOwner;
		this.customerNumber = customerNumber;
		this.shipTo = shipTo;
		this.division = division;
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

	public BigDecimal getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(BigDecimal customerNumber) {
		this.customerNumber = customerNumber;
	}

	public String getShipTo() {
		return shipTo;
	}

	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
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
