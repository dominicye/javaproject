package com.avnet.alapps.systest.model;

import java.util.List;

public class PartAttrJtableListResponse extends AbstractJtableListResponse {
	
	@SuppressWarnings("unchecked")
	public List<JtablePartAttr> getRecords() {
		return (List<JtablePartAttr>) this.records;
	}

	public void setRecords(List<JtablePartAttr> records) {
		this.records = records;
	}

}
