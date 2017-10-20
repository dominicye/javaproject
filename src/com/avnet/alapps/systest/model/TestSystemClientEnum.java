package com.avnet.alapps.systest.model;

import java.util.ArrayList;
import java.util.List;

public enum TestSystemClientEnum {
	IST ("IST", "Initial System Test", true), 
	BURNIN ("Burn In", "Burn In Test", true), 
	ORT ("ORT", "ORT", true), 
	CONFIG ("Config", "Config", true), 
	FST ("FST", "Final System Test", false), 
	IMAGING ("Imaging", "Imaging Test", false), 
	OBA ("OBA", "OBA", true),
	RUNIN ("Run In", "Run In Test", true),
	FRU ("FRU Test", "FRU Test", false),
	ONEOFF ("One Off", "One Off Test", false)
	;

	private String name;
	private String description;
	private boolean useAbortLogic;
	
	TestSystemClientEnum(String name, String description, boolean useAbortLogic) {
		this.name = name;
		this.description = description;
		this.useAbortLogic = useAbortLogic;
	}
	
	public static List<String> getNameList() {
		List<String> list = new ArrayList<String>();
		for ( TestSystemClientEnum client : TestSystemClientEnum.values() ) {
			list.add(client.getName());
		}
		return list;
	}
	
	public static String getNameListString() {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for ( TestSystemClientEnum client : TestSystemClientEnum.values() ) {
			if ( !first ) {
				sb.append(",");
			}
			sb.append(client.getName());
			first = false;
		}
		return sb.toString();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isUseAbortLogic() {
		return useAbortLogic;
	}

	public void setUseAbortLogic(boolean useAbortLogic) {
		this.useAbortLogic = useAbortLogic;
	}
	
	public static TestSystemClientEnum getTestSystemClientByName(String name) {
		TestSystemClientEnum returnTc = null;
		for ( TestSystemClientEnum tc : TestSystemClientEnum.values() ) {
			if ( tc.getName().equalsIgnoreCase(name) ) {
				returnTc = tc;
				break;
			}
		}
		return returnTc;
	}
	
	
}
