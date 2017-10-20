package com.avnet.alapps.systest.model;

import java.math.BigDecimal;

import com.avnet.alapps.model.alapps.AstPartAsmExcluded;

public class DisplayExcludedPart {
	String partNumber = null;
	String serialNumber = null;
	String description = null;
	String comment = null;
	BigDecimal partCount = new BigDecimal(0);
	
	public DisplayExcludedPart(AstPartAsmExcluded p) {
		if ( p != null ) {
			this.partNumber = p.getPartNumber();
			this.serialNumber = p.getSerialNumber();
			this.description = "";
			this.comment = p.getReasonTx();
			this.partCount = p.getPartCount();
		}
	}
	
	public DisplayExcludedPart(String partNumber, String serialNumber, String description,
			String comment, BigDecimal partCount) {
		super();
		this.partNumber = partNumber;
		this.serialNumber = serialNumber;
		this.description = description;
		this.comment = comment;
		this.partCount = partCount;
	}
	
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public BigDecimal getPartCount() {
		return partCount;
	}

	public void setPartCount(BigDecimal partCount) {
		this.partCount = partCount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
