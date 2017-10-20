package com.avnet.alapps.systest.model;

public class NutanixPackingSlipLine {
	private String partNumber = null;
	private String description = null;
	private String serialNumber = null;
	private Long quantity = null;
	public NutanixPackingSlipLine(String partNumber, String description,
			String serialNumber, Long quantity) {
		super();
		this.partNumber = partNumber;
		this.description = description;
		this.serialNumber = serialNumber;
		this.quantity = quantity;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	
	
}
