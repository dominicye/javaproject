package com.avnet.alapps.systest.model;

import java.util.List;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;

public class ComponentTypeAttrJtableListResponse extends AbstractJtableListResponse {
	
	@SuppressWarnings("unchecked")
	public List<JtableCompTypeAttr> getRecords() {
		return (List<JtableCompTypeAttr>) this.records;
	}

	public void setRecords(List<JtableCompTypeAttr> records) {
		this.records = records;
	}

}
