package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.avnet.alapps.model.alapps.AstTempBom;
import com.avnet.alapps.model.alapps.AstTempBomPart;

public class DisplayBom {
	private BigDecimal bomId;
	private String partNum;
	private String serialNum;
	private String ICN;
	private BigDecimal parentBomId;
	//private Map<String, DisplayBomPart> bomPartMapByPartNumber;
	private List<DisplayBomPart> bomPartList;
	private List<DisplayExcludedPart> excludedPartList;
	private JtablePart part = null;
	
	private class DisplayBomPartComparator implements Comparator<DisplayBomPart> {
	    @Override
	    public int compare(DisplayBomPart o1, DisplayBomPart o2) {
	    	if ( o1.getPart() != null && o2.getPart() != null ) {
	    		int val = o1.getPart().getCompType().getTypeNm().compareTo(o2.getPart().getCompType().getTypeNm());
	    		if ( val == 0 ) {
	    			val = o1.getPartNum().compareTo(o2.getPartNum());
	    		}

	    		return val;
	    	}
	    	else {
	    		return o1.getPartNum().compareTo(o2.getPartNum());
	    	}
	    }
	}
	
	private class DisplayExcludedPartComparator implements Comparator<DisplayExcludedPart> {
	    @Override
	    public int compare(DisplayExcludedPart o1, DisplayExcludedPart o2) {
	    	if ( o1.getPartNumber() != null && o2.getPartNumber() != null ) {
	    		return o1.getPartNumber().compareTo(o2.getPartNumber());
	    	}
	    	else {
	    		return 0;
	    	}
	    }
	}
	
	public DisplayBom(BigDecimal bomId, String partNum, String serialNum, String ICN,
			BigDecimal parentBomId) {
		super();
		this.bomId = bomId;
		this.partNum = partNum;
		this.serialNum = serialNum;
		this.ICN = ICN;
		this.parentBomId = parentBomId;
		//this.bomPartMapByPartNumber = new HashMap<String, DisplayBomPart>();
		this.bomPartList = new ArrayList<DisplayBomPart>();
		this.excludedPartList = new ArrayList<DisplayExcludedPart>();
	}
	
	
	public DisplayBom(AstTempBom b) {
		this.bomId = b.getBomId();
		this.partNum = b.getPartNum();
		this.serialNum = b.getSerialNum();
		this.ICN = b.getIcn();
		this.parentBomId = b.getParentBomId();
		//this.bomPartMapByPartNumber = new HashMap<String, DisplayBomPart>();
		this.bomPartList = new ArrayList<DisplayBomPart>();
		for ( Object bp : b.getAstTempBomParts() ) {
			this.addBomPart((AstTempBomPart)bp);
		}
		this.excludedPartList = new ArrayList<DisplayExcludedPart>();
	}
	
	private void addBomPart(AstTempBomPart bp) {
		if ( this.bomPartList == null ) {
			this.bomPartList = new ArrayList<DisplayBomPart>();
		}
		this.bomPartList.add(new DisplayBomPart(bp));
	}
	
	public void addBomPart(DisplayBomPart bp) {
		if ( this.bomPartList == null ) {
			this.bomPartList = new ArrayList<DisplayBomPart>();
		}
		this.bomPartList.add(bp);
	}
	
	public void addExcludedPart(DisplayExcludedPart ep) {
		if ( this.excludedPartList == null ) {
			this.excludedPartList = new ArrayList<DisplayExcludedPart>();
		}
		this.excludedPartList.add(ep);
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

	public BigDecimal getParentBomId() {
		return parentBomId;
	}

	public void setParentBomId(BigDecimal parentBomId) {
		this.parentBomId = parentBomId;
	}


	public List<DisplayBomPart> getBomPartList() {
		Collections.sort(bomPartList, new DisplayBomPartComparator());
		return bomPartList;
	}


	public void setBomPartList(List<DisplayBomPart> bomPartList) {
		this.bomPartList = bomPartList;
	}

	

	public List<DisplayExcludedPart> getExcludedPartList() {
		Collections.sort(this.excludedPartList, new DisplayExcludedPartComparator());
		return excludedPartList;
	}


	public void setExcludedPartList(List<DisplayExcludedPart> excludedPartList) {
		this.excludedPartList = excludedPartList;
	}


	public JtablePart getPart() {
		return part;
	}


	public void setPart(JtablePart part) {
		this.part = part;
	}


	public String getICN() {
		return ICN;
	}


	public void setICN(String icn) {
		ICN = icn;
	}

/*
	public Map<String, DisplayBomPart> getBomPartMapByPartNumber() {
		return bomPartMapByPartNumber;
	}


	public void setBomPartMapByPartNumber(
			Map<String, DisplayBomPart> bomPartMapByPartNumber) {
		this.bomPartMapByPartNumber = bomPartMapByPartNumber;
	}
*/	
	

}
