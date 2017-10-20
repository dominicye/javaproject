package com.avnet.alapps.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.transaction.annotation.Transactional;


import com.avnet.alapps.model.gsfc.UpromHost;
import com.avnet.alapps.model.gsfc.UpromHostHome;


public class UpromService  {

	private UpromHostHome upromHostHome = null; 
	
	public UpromService() {
		upromHostHome = new UpromHostHome();
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<UpromHost> getUpromHosts(boolean showOnlyActive, boolean showOnlyMaster, boolean showOnlyNonMaster) {

		UpromHost h = new UpromHost();
		if (showOnlyActive) h.setActiveFl("Y");
		if (showOnlyMaster) h.setMasterFl("Y");
		if (showOnlyNonMaster) h.setMasterFl("N");
		return (List<UpromHost>)upromHostHome.findByExample(h);

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String, UpromHost> getUpromHostsMapByHost(boolean showOnlyActive, boolean showOnlyMaster, boolean showOnlyNonMaster) {
		UpromHost h = new UpromHost();
		if (showOnlyActive) h.setActiveFl("Y");
		if (showOnlyMaster) h.setMasterFl("Y");
		if (showOnlyNonMaster) h.setMasterFl("N");
		ArrayList<UpromHost> list = (ArrayList<UpromHost>)upromHostHome.findByExample(h);
	
		Map<String, UpromHost> map = new HashMap<String, UpromHost>();
		for (UpromHost s : list) {
			map.put(s.getHostNm(), s);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<String, UpromHost> getUpromHostsMapByName(boolean showOnlyActive, boolean showOnlyMaster, boolean showOnlyNonMaster, boolean upperKeys) {
		UpromHost h = new UpromHost();
		if (showOnlyActive) h.setActiveFl("Y");
		if (showOnlyMaster) h.setMasterFl("Y");
		if (showOnlyNonMaster) h.setMasterFl("N");
		ArrayList<UpromHost> list = (ArrayList<UpromHost>)upromHostHome.findByExample(h);
	
		Map<String, UpromHost> map = new HashMap<String, UpromHost>();
		for (UpromHost s : list) {
			if ( upperKeys ) {
				map.put(s.getHostDs().toUpperCase(), s);
			}
			else {
				map.put(s.getHostDs(), s);
			}
		}
		return map;
	}
	
	@Transactional(readOnly = true)
	public UpromHost getMasterSite() {
		UpromHost masterHost = null;
	    List<UpromHost> masterHostList = this.getUpromHosts(true, true, false);
	    if ( masterHostList != null && masterHostList.size() > 0 ) {
	    	masterHost = masterHostList.get(0);
	    }
	    return masterHost;
	}

	public UpromHostHome getUpromHostHome() {
		return upromHostHome;
	}

	public void setUpromHostHome(UpromHostHome upromHostHome) {
		this.upromHostHome = upromHostHome;
	}

}
