package com.avnet.alapps.model.alapps;

// Generated Apr 20, 2016 1:55:16 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AstCompTypeAttr generated by hbm2java
 */
public class MinAstCompTypeAttr implements java.io.Serializable {

	private BigDecimal compTypeAttrId;
	private AstDataType astDataType;
	private AstDataSource astDataSource;
	private String attrNm;
	private String attrDs;
	private String xmlTag;
	private String activeFl;
	private String keyFl;
	private String editableFl;

	public MinAstCompTypeAttr() {
	}

	public MinAstCompTypeAttr(BigDecimal compTypeAttrId, AstDataType astDataType,
			AstDataSource astDataSource, 
			String attrNm, String attrDs, String activeFl, String editableFl) {
		this.compTypeAttrId = compTypeAttrId;
		this.astDataType = astDataType;
		this.astDataSource = astDataSource;
		this.attrNm = attrNm;
		this.attrDs = attrDs;
		this.activeFl = activeFl;
		this.editableFl = editableFl;
	}

	public MinAstCompTypeAttr(BigDecimal compTypeAttrId, AstDataType astDataType,
			AstDataSource astDataSource, 
			String attrNm, String attrDs, String xmlTag, String activeFl,
			String keyFl, String editableFl) {
		this.compTypeAttrId = compTypeAttrId;
		this.astDataType = astDataType;
		this.astDataSource = astDataSource;
		this.attrNm = attrNm;
		this.attrDs = attrDs;
		this.xmlTag = xmlTag;
		this.activeFl = activeFl;
		this.keyFl = keyFl;
		this.editableFl = editableFl;
	}

	public BigDecimal getCompTypeAttrId() {
		return this.compTypeAttrId;
	}

	public void setCompTypeAttrId(BigDecimal compTypeAttrId) {
		this.compTypeAttrId = compTypeAttrId;
	}

	public AstDataType getAstDataType() {
		return this.astDataType;
	}

	public void setAstDataType(AstDataType astDataType) {
		this.astDataType = astDataType;
	}

	public AstDataSource getAstDataSource() {
		return this.astDataSource;
	}

	public void setAstDataSource(AstDataSource astDataSource) {
		this.astDataSource = astDataSource;
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

	public String getEditableFl() {
		return editableFl;
	}

	public void setEditableFl(String editableFl) {
		this.editableFl = editableFl;
	}


}