package com.avnet.alapps.model.alapps;

// Generated Jan 13, 2016 4:22:01 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * AstPartAsmExcluded generated by hbm2java
 */
public class AstPartAsmExcluded implements java.io.Serializable {

	private BigDecimal partAsmExcludedId;
	private AstPartAsm astPartAsm;
	private String partNumber;
	private String serialNumber;
	private String reasonTx;
	private BigDecimal partCount;

	public AstPartAsmExcluded() {
	}

	public AstPartAsmExcluded(BigDecimal partAsmExcludedId,
			AstPartAsm astPartAsm, BigDecimal partCount) {
		this.partAsmExcludedId = partAsmExcludedId;
		this.astPartAsm = astPartAsm;
		this.partCount = partCount;
	}

	public AstPartAsmExcluded(BigDecimal partAsmExcludedId,
			AstPartAsm astPartAsm, String partNumber, String serialNumber,
			String reasonTx, BigDecimal partCount) {
		this.partAsmExcludedId = partAsmExcludedId;
		this.astPartAsm = astPartAsm;
		this.partNumber = partNumber;
		this.serialNumber = serialNumber;
		this.reasonTx = reasonTx;
		this.partCount = partCount;
	}

	public BigDecimal getPartAsmExcludedId() {
		return this.partAsmExcludedId;
	}

	public void setPartAsmExcludedId(BigDecimal partAsmExcludedId) {
		this.partAsmExcludedId = partAsmExcludedId;
	}

	public AstPartAsm getAstPartAsm() {
		return this.astPartAsm;
	}

	public void setAstPartAsm(AstPartAsm astPartAsm) {
		this.astPartAsm = astPartAsm;
	}

	public String getPartNumber() {
		return this.partNumber;
	}

	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	public String getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getReasonTx() {
		return this.reasonTx;
	}

	public void setReasonTx(String reasonTx) {
		this.reasonTx = reasonTx;
	}

	public BigDecimal getPartCount() {
		return this.partCount;
	}

	public void setPartCount(BigDecimal partCount) {
		this.partCount = partCount;
	}

}
