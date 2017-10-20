package com.avnet.alapps.systest.model;

public class NutanixCustomerPackingSlipLine {
	private String partNumber = null;
	private String systemSerialNumber = null;
	private String nodeSerialNumber = null;
	public NutanixCustomerPackingSlipLine(String partNumber,
			String systemSerialNumber, String nodeSerialNumber) {
		super();
		this.partNumber = partNumber;
		this.systemSerialNumber = systemSerialNumber;
		this.nodeSerialNumber = nodeSerialNumber;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getSystemSerialNumber() {
		return systemSerialNumber;
	}
	public void setSystemSerialNumber(String systemSerialNumber) {
		this.systemSerialNumber = systemSerialNumber;
	}
	public String getNodeSerialNumber() {
		return nodeSerialNumber;
	}
	public void setNodeSerialNumber(String nodeSerialNumber) {
		this.nodeSerialNumber = nodeSerialNumber;
	}
	
	
}
