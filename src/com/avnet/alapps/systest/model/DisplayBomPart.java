package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.util.Comparator;

import com.avnet.alapps.model.alapps.AstTempBomPart;


public class DisplayBomPart {
	private BigDecimal partId;
	private BigDecimal bomId;
	private String partNum;
	private String serialNum;
	private String partDs;
	private JtablePart part = null;
	
	
	
	public DisplayBomPart() {
		super();
	}
	
	public DisplayBomPart(AstTempBomPart bp) {
		this.setBomId(bp.getAstTempBom().getBomId());
		this.setPartDs(bp.getPartDs());
		this.setPartId(bp.getPartId());
		this.setPartNum(bp.getPartNum());
		this.setSerialNum(bp.getSerialNum());
	}
	
	public DisplayBomPart(BigDecimal partId, BigDecimal bomId, String partNum,
			String serialNum, String partDs) {
		super();
		this.partId = partId;
		this.bomId = bomId;
		this.partNum = partNum;
		this.serialNum = serialNum;
		this.partDs = partDs;
	}
	
	public BigDecimal getPartId() {
		return partId;
	}
	public void setPartId(BigDecimal partId) {
		this.partId = partId;
	}
	public BigDecimal getBomId() {
		return bomId;
	}
	public void setBomId(BigDecimal bomId) {
		this.bomId = bomId;
	}
	public String getPartNum() {
		return partNum;
	}
	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}
	public String getSerialNum() {
		return serialNum;
	}
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	public String getPartDs() {
		return partDs;
	}
	public void setPartDs(String partDs) {
		this.partDs = partDs;
	}

	public JtablePart getPart() {
		return part;
	}

	public void setPart(JtablePart part) {
		this.part = part;
	}
	
	
}
