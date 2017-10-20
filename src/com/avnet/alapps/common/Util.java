package com.avnet.alapps.common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.systest.model.DisplayTestResultItem;
import com.avnet.alapps.systest.model.NutanixCustomerPackingSlipLine;
import com.avnet.alapps.systest.model.NutanixPackingSlipLine;

public class Util {
	
	public static class BigDecimalDescendingComparator implements Comparator<BigDecimal> {
	    @Override
	    public int compare(BigDecimal o1, BigDecimal o2) {
	    	return o2.compareTo(o1);
	    }
	}
	
	public static class BigDecimalAscendingComparator implements Comparator<BigDecimal> {
	    @Override
	    public int compare(BigDecimal o1, BigDecimal o2) {
	    	return o1.compareTo(o2);
	    }
	}
	
	public static class NutanixPackingSlipLineComparator implements Comparator<NutanixPackingSlipLine> {
	    @Override
	    public int compare(NutanixPackingSlipLine o1, NutanixPackingSlipLine o2) {
	    	return o1.getPartNumber().compareTo(o2.getPartNumber());
	    }
	}
	
	public static class NutanixCustomerPackingSlipLineComparator implements Comparator<NutanixCustomerPackingSlipLine> {
	    @Override
	    public int compare(NutanixCustomerPackingSlipLine o1, NutanixCustomerPackingSlipLine o2) {
	    	int c1 = o1.getPartNumber().compareTo(o2.getPartNumber());
	    	if ( c1 == 0 ) {
	    		int c2 = o1.getSystemSerialNumber().compareTo(o2.getSystemSerialNumber());
	    		if ( c2 == 0  ) {
	    			return o1.getNodeSerialNumber().compareTo(o2.getNodeSerialNumber());
	    		}
	    		else {
	    			return c2;
	    		}
	    	}
	    	else {
	    		return c1;
	    	}
	    }
	}
	
	public static class DisplayTestResultItemComparator implements Comparator<DisplayTestResultItem> {
	    @Override
	    public int compare(DisplayTestResultItem o1, DisplayTestResultItem o2) {
	    	int returnVal = -1;
	    	String nl1 = o1.getNodeLocation();
	    	String nl2 = o2.getNodeLocation();
	    	
	    	if ( nl1 == null && nl2 == null ) {
	    		returnVal = 0;
	    	}
	    	else if ( nl1 == null && nl2 != null ) {
	    		returnVal = -1;
	    	}
	    	else if ( nl1 != null && nl2 == null ) {
	    		returnVal = 1;
	    	}
	    	else {
	    		returnVal = nl1.compareTo(nl2);
	    	}
	    	return returnVal;
	    }
	}
	
	public static List<String> generateMacs(String startMacString, int totalMacs) throws Exception  {
		if ( startMacString != null ) {
			startMacString = startMacString.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");
			if ( startMacString.length() != 12 ) {
				throw new Exception("Malformed MAC address supplied: " + startMacString);
			}
		}
		else {
			throw new Exception("No MAC address supplied");
		}
		List<String> macList = new ArrayList<String>();
		final String fistThreeOctetsString = startMacString.substring(0, 6);
		BigInteger lastThreeOctetsInt = new BigInteger(startMacString.substring(6, 12), 16);
		macList.add(startMacString);
		for ( int i = 1; i < totalMacs; i++ ) {
			lastThreeOctetsInt = lastThreeOctetsInt.add(BigInteger.ONE);
			macList.add(String.format(fistThreeOctetsString + "%02X", lastThreeOctetsInt));
		}
		return macList;
	} 

}
