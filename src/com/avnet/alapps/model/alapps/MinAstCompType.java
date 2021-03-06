package com.avnet.alapps.model.alapps;

// Generated Apr 20, 2016 1:55:16 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AstCompType generated by hbm2java
 */
public class MinAstCompType implements java.io.Serializable {

	private BigDecimal compTypeId;
	private String typeNm;
	private String typeDs;
	private String xmlTag;
	private String activeFl;


	public MinAstCompType() {
	}

	public MinAstCompType(BigDecimal compTypeId, String typeNm, String typeDs,
			String activeFl) {
		this.compTypeId = compTypeId;
		this.typeNm = typeNm;
		this.typeDs = typeDs;
		this.activeFl = activeFl;
	}

	public MinAstCompType(BigDecimal compTypeId,
			String typeNm, String typeDs, String xmlTag, String activeFl) {
		this.compTypeId = compTypeId;
		this.typeNm = typeNm;
		this.typeDs = typeDs;
		this.xmlTag = xmlTag;
		this.activeFl = activeFl;
	}

	public BigDecimal getCompTypeId() {
		return this.compTypeId;
	}

	public void setCompTypeId(BigDecimal compTypeId) {
		this.compTypeId = compTypeId;
	}


	public String getTypeNm() {
		return this.typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	public String getTypeDs() {
		return this.typeDs;
	}

	public void setTypeDs(String typeDs) {
		this.typeDs = typeDs;
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



}
