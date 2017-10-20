package com.avnet.alapps.systest.model;

import java.lang.reflect.Field;
import java.math.BigDecimal;


import com.avnet.alapps.model.alapps.AstCompType;

public class JtableCompType { //extends AstCompType { //Complicated issue. Will not work with jtable

	private BigDecimal compTypeId;
	private String typeNm;
	private String typeDs;
	private String xmlTag;
	private String activeFl;
	private BigDecimal parentCompTypeId;
	private JtableCompType parentCompType;


	public JtableCompType() {
		// TODO Auto-generated constructor stub
	}

	public JtableCompType(AstCompType compType) {
		this.compTypeId = compType.getCompTypeId();
		// Something wrong with the generated hibernate
		if ( compType.getAstCompType() != null ) {
			this.parentCompTypeId = compType.getAstCompType().getCompTypeId();
			this.parentCompType = new JtableCompType(compType.getAstCompType());
		}
		this.typeNm = compType.getTypeNm();
		this.typeDs = compType.getTypeDs();
		this.xmlTag = compType.getXmlTag();
		this.activeFl = compType.getActiveFl();
	}

	/*
	public String toLogString() throws Exception {
		StringBuilder sb = new StringBuilder();
		for ( Field f : this.getClass().getFields() ) {
			String type = f.getType().getSimpleName();
			String name = f.getName();
			String value = null;
			if ( type.equals("BigDecimal") ) {
				BigDecimal v = (BigDecimal)f.get(this);
				value = ( v != null ) ? v.toPlainString() : null;
			}
			else if ( type.equals("String") ) {
				value = (String)f.get(this);
			}
			//else if ( type.equals("JtableCompType") ) {
			//	JtableCompType v = (JtableCompType)f.get(this);
			//}
			sb.append("|").append(name).append("=").append(value);
		}
		return sb.toString();
	}
	*/

	public BigDecimal getCompTypeId() {
		return compTypeId;
	}

	public void setCompTypeId(BigDecimal compTypeId) {
		this.compTypeId = compTypeId;
	}

	public BigDecimal getParentCompTypeId() {
		return parentCompTypeId;
	}

	public void setParentCompTypeId(BigDecimal parentCompTypeId) {
		this.parentCompTypeId = parentCompTypeId;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	public String getTypeDs() {
		return typeDs;
	}

	public void setTypeDs(String typeDs) {
		this.typeDs = typeDs;
	}

	public String getXmlTag() {
		return xmlTag;
	}

	public void setXmlTag(String xmlTag) {
		this.xmlTag = xmlTag;
	}

	public String getActiveFl() {
		return activeFl;
	}

	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}

	public JtableCompType getParentCompType() {
		return parentCompType;
	}

	public void setParentCompType(JtableCompType parentCompType) {
		this.parentCompType = parentCompType;
	}

	

}
