package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;

public class JtableCompTypeAttr { //extends AstCompTypeAttr { //Very complicated issue. Wont work!

	private BigDecimal compTypeAttrId;
	private BigDecimal compTypeId;
	private String compTypeNm;
	private String compTypeDs;
	private BigDecimal dataSourceId;
	private String dataSourceNm;
	private String dataSourceDs;
	private BigDecimal dataTypeId;
	private String dataTypeNm;
	private String dataTypeDs;
	private String attrNm;
	private String attrDs;
	private String xmlTag;
	private String activeFl;
	private String keyFl;
	private String editableFl;

	
	public JtableCompTypeAttr() {
		// TODO Auto-generated constructor stub
	}
	
	public JtableCompTypeAttr(AstCompTypeAttr attr) {
		super();
		this.setActiveFl(attr.getActiveFl());
		this.setAttrDs(attr.getAttrDs());
		this.setAttrNm(attr.getAttrNm());
		this.setCompTypeAttrId(attr.getCompTypeAttrId());
		this.setKeyFl(attr.getKeyFl());
		this.setXmlTag(attr.getXmlTag());
		this.compTypeId = attr.getAstCompType().getCompTypeId();
		this.compTypeNm = attr.getAstCompType().getTypeNm();
		this.compTypeDs = attr.getAstCompType().getTypeDs();
		this.dataTypeNm = attr.getAstDataType().getTypeNm();
		this.dataSourceNm = attr.getAstDataSource().getSourceNm();
		this.dataSourceDs = attr.getAstDataSource().getSourceDs();
		this.dataSourceId = attr.getAstDataSource().getDataSourceId();
		this.dataTypeId = attr.getAstDataType().getDataTypeId();
		this.editableFl = attr.getEditableFl();
	}
	
	public BigDecimal getCompTypeAttrId() {
		return this.compTypeAttrId;
	}

	public void setCompTypeAttrId(BigDecimal compTypeAttrId) {
		this.compTypeAttrId = compTypeAttrId;
	}

	public String getAttrNm() {
		return this.attrNm;
	}

	public void setAttrNm(String attrNm) {
		this.attrNm = attrNm;
	}

	public String getAttrDs() {
		return this.attrDs;
	}

	public void setAttrDs(String attrDs) {
		this.attrDs = attrDs;
	}

	public String getXmlTag() {
		return this.xmlTag;
	}

	public void setXmlTag(String xmlTag) {
		this.xmlTag = xmlTag;
	}

	public String getActiveFl() {
		return this.activeFl;
	}

	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}

	public String getKeyFl() {
		return this.keyFl;
	}

	public void setKeyFl(String keyFl) {
		this.keyFl = keyFl;
	}

	public BigDecimal getCompTypeId() {
		return compTypeId;
	}

	public void setCompTypeId(BigDecimal compTypeId) {
		this.compTypeId = compTypeId;
	}

	public BigDecimal getDataSourceId() {
		return dataSourceId;
	}

	public void setDataSourceId(BigDecimal dataSourceId) {
		this.dataSourceId = dataSourceId;
	}

	public BigDecimal getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(BigDecimal dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getCompTypeNm() {
		return compTypeNm;
	}

	public void setCompTypeNm(String compTypeNm) {
		this.compTypeNm = compTypeNm;
	}

	public String getCompTypeDs() {
		return compTypeDs;
	}

	public void setCompTypeDs(String compTypeDs) {
		this.compTypeDs = compTypeDs;
	}

	public String getDataSourceNm() {
		return dataSourceNm;
	}

	public void setDataSourceNm(String dataSourceNm) {
		this.dataSourceNm = dataSourceNm;
	}

	public String getDataSourceDs() {
		return dataSourceDs;
	}

	public void setDataSourceDs(String dataSourceDs) {
		this.dataSourceDs = dataSourceDs;
	}

	public String getDataTypeNm() {
		return dataTypeNm;
	}

	public void setDataTypeNm(String dataTypeNm) {
		this.dataTypeNm = dataTypeNm;
	}

	public String getDataTypeDs() {
		return dataTypeDs;
	}

	public void setDataTypeDs(String dataTypeDs) {
		this.dataTypeDs = dataTypeDs;
	}

	public String getEditableFl() {
		return editableFl;
	}

	public void setEditableFl(String editableFl) {
		this.editableFl = editableFl;
	}

}
