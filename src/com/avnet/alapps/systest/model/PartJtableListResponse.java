package com.avnet.alapps.systest.model;

import java.util.List;
import com.avnet.alapps.systest.model.JtablePart;

public class PartJtableListResponse extends AbstractJtableListResponse {
	
	@SuppressWarnings("unchecked")
	public List<JtablePart> getRecords() {
		return (List<JtablePart>) this.records;
	}

	public void setRecords(List<JtablePart> records) {
		this.records = records;
	}

}
