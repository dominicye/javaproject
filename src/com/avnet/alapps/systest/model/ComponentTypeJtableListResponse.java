package com.avnet.alapps.systest.model;

import java.util.List;
import com.avnet.alapps.systest.model.JtableCompType;;


public class ComponentTypeJtableListResponse extends AbstractJtableListResponse {
	
	@SuppressWarnings("unchecked")
	public List<JtableCompType> getRecords() {
		return (List<JtableCompType>) this.records;
	}

	public void setRecords(List<JtableCompType> records) {
		this.records = records;
	}

}
