package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.avnet.alapps.model.alapps.AstPart;


public class JtablePart  { //extends AstPart {

	private BigDecimal partId;
	private BigDecimal compTypeId;
	private String compTypeNm;
	private String activeFl;
	private String keyValue;
	private List<JtablePartAttr> partAttrList = new ArrayList<JtablePartAttr>();
	private JtableCompType compType;
	
	public JtablePart() {
		super();
	}
	
	public JtablePart(AstPart part, String keyValue) {
		super();
		this.setPartId(part.getPartId());
		this.setActiveFl(part.getActiveFl());
		this.setCompTypeId(part.getAstCompType().getCompTypeId());
		this.setCompTypeNm(part.getAstCompType().getTypeNm());
		this.keyValue = keyValue;
		if ( part.getAstCompType() != null ) {
			this.compType = new JtableCompType(part.getAstCompType());
		}
	}

	public JtablePart(BigDecimal partId, BigDecimal compTypeId,
			String compTypeNm, String activeFl, String keyValue,
			List<JtablePartAttr> partAttrList, JtableCompType compType) {
		super();
		this.partId = partId;
		this.compTypeId = compTypeId;
		this.compTypeNm = compTypeNm;
		this.activeFl = activeFl;
		this.keyValue = keyValue;
		this.partAttrList = partAttrList;
		this.compType = compType;
	}
	
	private class JtablePartAttrComparator implements Comparator<JtablePartAttr> {
	    @Override
	    public int compare(JtablePartAttr o1, JtablePartAttr o2) {
	    	return o1.getCompTypeAttr().getAttrNm().compareTo(o2.getCompTypeAttr().getAttrNm());
	    }
	}
	
	//ascending order
	public static Comparator<JtablePart> KeyValueComparatorASC = new Comparator<JtablePart>() {
		public int compare(JtablePart p1, JtablePart p2) {
			   String kv1 = p1.getKeyValue().toUpperCase();
			   String kv2 = p2.getKeyValue().toUpperCase();
			   return kv1.compareTo(kv2);
		}
	};
	
	//descending order
	public static Comparator<JtablePart> KeyValueComparatorDESC = new Comparator<JtablePart>() {
		public int compare(JtablePart p1, JtablePart p2) {
			   String kv1 = p1.getKeyValue().toUpperCase();
			   String kv2 = p2.getKeyValue().toUpperCase();
			   return kv2.compareTo(kv1);
		}
	};


	public String getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}

	public BigDecimal getPartId() {
		return partId;
	}

	public void setPartId(BigDecimal partId) {
		this.partId = partId;
	}

	public BigDecimal getCompTypeId() {
		return compTypeId;
	}

	public void setCompTypeId(BigDecimal compTypeId) {
		this.compTypeId = compTypeId;
	}

	public String getActiveFl() {
		return activeFl;
	}

	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}

	public List<JtablePartAttr> getPartAttrList() {
		Collections.sort(partAttrList, new JtablePartAttrComparator());
		return partAttrList;
	}

	public void setPartAttrList(List<JtablePartAttr> partAttrList) {
		this.partAttrList = partAttrList;
	}

	public String getCompTypeNm() {
		return compTypeNm;
	}

	public void setCompTypeNm(String compTypeNm) {
		this.compTypeNm = compTypeNm;
	}

	public JtableCompType getCompType() {
		return compType;
	}

	public void setCompType(JtableCompType compType) {
		this.compType = compType;
	}


}
