package com.avnet.alapps.systest.model;

import java.math.BigDecimal;

import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.MinAstPartAsmAttr;


public class DisplayPartAsmAttr {
	private BigDecimal compTypeAttrId;
	private BigDecimal partAsmId;
	private BigDecimal partAttrId;
	private BigDecimal partAsmAttrId;
	private String attrNm;
	private String attrDs;
	private String xmlTag;
	private String dataSourceNm;
	private String dataTypeNm;
	private String valueTx;
	private String editableFl;
	
	public DisplayPartAsmAttr(MinAstPartAsmAttr astPartAsmAttr) {
		if ( astPartAsmAttr != null ) {
			this.compTypeAttrId = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getCompTypeAttrId();
			this.partAttrId = astPartAsmAttr.getAstPartAttr().getPartAttrId();
			//this.partAsmId = astPartAsmAttr.getAstPartAsm().getPartAsmId();
			this.partAsmAttrId = astPartAsmAttr.getPartAsmAttrId();
			this.xmlTag = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getXmlTag();
			this.attrNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
			this.attrDs = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrDs();
			this.dataSourceNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAstDataSource().getSourceNm();
			this.dataTypeNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAstDataType().getTypeNm();
			this.valueTx = astPartAsmAttr.getValueTx();
			this.editableFl = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getEditableFl();
		}
	}
	
	public DisplayPartAsmAttr(AstPartAsmAttr astPartAsmAttr) {
		if ( astPartAsmAttr != null ) {
			this.compTypeAttrId = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getCompTypeAttrId();
			this.partAttrId = astPartAsmAttr.getAstPartAttr().getPartAttrId();
			this.partAsmId = astPartAsmAttr.getAstPartAsm().getPartAsmId();
			this.partAsmAttrId = astPartAsmAttr.getPartAsmAttrId();
			this.xmlTag = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getXmlTag();
			this.attrNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
			this.attrDs = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrDs();
			this.dataSourceNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAstDataSource().getSourceNm();
			this.dataTypeNm = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAstDataType().getTypeNm();
			this.valueTx = astPartAsmAttr.getValueTx();
			this.editableFl = astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getEditableFl();
		}
	}

	public BigDecimal getPartAsmId() {
		return partAsmId;
	}

	public void setPartAsmId(BigDecimal partAsmId) {
		this.partAsmId = partAsmId;
	}

	public BigDecimal getPartAsmAttrId() {
		return partAsmAttrId;
	}

	public void setPartAsmAttrId(BigDecimal partAsmAttrId) {
		this.partAsmAttrId = partAsmAttrId;
	}

	public String getAttrNm() {
		return attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getXmlTag() {
		return xmlTag;
	}

	public void setXmlTag(String xmlTag) {
		this.xmlTag = xmlTag;
	}

	public String getValueTx() {
		return valueTx;
	}

	public void setValueTx(String valueTx) {
		this.valueTx = valueTx;
	}

	public String getAttrDs() {
		return attrDs;
	}

	public void setAttrDs(String attrDs) {
		this.attrDs = attrDs;
	}

	public BigDecimal getCompTypeAttrId() {
		return compTypeAttrId;
	}

	public void setCompTypeAttrId(BigDecimal compTypeAttrId) {
		this.compTypeAttrId = compTypeAttrId;
	}

	public BigDecimal getPartAttrId() {
		return partAttrId;
	}

	public void setPartAttrId(BigDecimal partAttrId) {
		this.partAttrId = partAttrId;
	}

	public String getDataSourceNm() {
		return dataSourceNm;
	}

	public void setDataSourceNm(String dataSourceNm) {
		this.dataSourceNm = dataSourceNm;
	}

	public String getDataTypeNm() {
		return dataTypeNm;
	}

	public void setDataTypeNm(String dataTypeNm) {
		this.dataTypeNm = dataTypeNm;
	}

	public String getEditableFl() {
		return editableFl;
	}

	public void setEditableFl(String editableFl) {
		this.editableFl = editableFl;
	}

	public String toXML() {
		if ( this.xmlTag == null || this.xmlTag.length() == 0  ) {
			return "";
		}
		String val = "";
		if ( this.valueTx != null ) {
			val = this.valueTx;
		}
		return "<" + this.xmlTag + ">" + val + "</" + this.xmlTag + ">";
	}

}
