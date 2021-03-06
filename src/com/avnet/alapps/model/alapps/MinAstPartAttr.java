package com.avnet.alapps.model.alapps;

// Generated Apr 20, 2016 1:55:16 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AstPartAttr generated by hbm2java
 */
public class MinAstPartAttr implements java.io.Serializable {

	private BigDecimal partAttrId;
	private MinAstCompTypeAttr astCompTypeAttr;
	private String valueTx;



	public MinAstPartAttr() {
	}

	public MinAstPartAttr(BigDecimal partAttrId, 
			MinAstCompTypeAttr astCompTypeAttr) {
		this.partAttrId = partAttrId;
		this.astCompTypeAttr = astCompTypeAttr;
	}

	public MinAstPartAttr(BigDecimal partAttrId,
			MinAstCompTypeAttr astCompTypeAttr, String valueTx) {
		this.partAttrId = partAttrId;
		this.astCompTypeAttr = astCompTypeAttr;
		this.valueTx = valueTx;
	}

	public BigDecimal getPartAttrId() {
		return this.partAttrId;
	}

	public void setPartAttrId(BigDecimal partAttrId) {
		this.partAttrId = partAttrId;
	}


	public MinAstCompTypeAttr getAstCompTypeAttr() {
		return this.astCompTypeAttr;
	}

	public void setAstCompTypeAttr(MinAstCompTypeAttr astCompTypeAttr) {
		this.astCompTypeAttr = astCompTypeAttr;
	}

	public String getValueTx() {
		return this.valueTx;
	}

	public void setValueTx(String valueTx) {
		this.valueTx = valueTx;
	}


}
