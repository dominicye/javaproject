package com.avnet.alapps.systest.model;

import java.math.BigDecimal;

import com.avnet.alapps.model.alapps.AstPart;
import com.avnet.alapps.model.alapps.AstPartAttr;


public class JtablePartAttr { // extends AstPartAttr {

	private BigDecimal partAttrId;
	private BigDecimal partId;
	private BigDecimal compTypeAttrId;
	private JtableCompTypeAttr compTypeAttr;
	private String valueTx;
	private String keyFl = null;
	private String activeFl = null;

	public JtablePartAttr() {
		super();
	}
	
	public JtablePartAttr(AstPartAttr pa) {
		super();
		this.setPartAttrId(pa.getPartAttrId());
		this.setPartId(pa.getAstPart().getPartId());
		this.setCompTypeAttrId(pa.getAstCompTypeAttr().getCompTypeAttrId());
		this.setCompTypeAttr(new JtableCompTypeAttr(pa.getAstCompTypeAttr()));
		this.setValueTx(pa.getValueTx());
		this.activeFl = pa.getAstCompTypeAttr().getActiveFl();
		this.keyFl = pa.getAstCompTypeAttr().getKeyFl();
	}
	
	

	public JtablePartAttr(BigDecimal partAttrId, BigDecimal partId,
			BigDecimal compTypeAttrId, String valueTx, String keyFl,
			String activeFl) {
		super();
		this.partAttrId = partAttrId;
		this.partId = partId;
		this.compTypeAttrId = compTypeAttrId;
		this.valueTx = valueTx;
		this.keyFl = keyFl;
		this.activeFl = activeFl;
	}

	public BigDecimal getPartAttrId() {
		return partAttrId;
	}

	public void setPartAttrId(BigDecimal partAttrId) {
		this.partAttrId = partAttrId;
	}

	public BigDecimal getPartId() {
		return partId;
	}

	public void setPartId(BigDecimal partId) {
		this.partId = partId;
	}

	public BigDecimal getCompTypeAttrId() {
		return compTypeAttrId;
	}

	public void setCompTypeAttrId(BigDecimal compTypeAttrId) {
		this.compTypeAttrId = compTypeAttrId;
	}

	public String getValueTx() {
		return valueTx;
	}

	public void setValueTx(String valueTx) {
		this.valueTx = valueTx;
	}

	public String getKeyFl() {
		return keyFl;
	}

	public void setKeyFl(String keyFl) {
		this.keyFl = keyFl;
	}

	public String getActiveFl() {
		return activeFl;
	}

	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}

	public JtableCompTypeAttr getCompTypeAttr() {
		return compTypeAttr;
	}

	public void setCompTypeAttr(JtableCompTypeAttr compTypeAttr) {
		this.compTypeAttr = compTypeAttr;
	}
	
}
