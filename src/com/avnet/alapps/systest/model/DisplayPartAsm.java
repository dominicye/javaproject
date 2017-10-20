package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstPartAsmExcluded;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultItem;
import com.avnet.alapps.model.alapps.MinAstPartAsm;
import com.avnet.alapps.model.alapps.MinAstPartAsmAttr;
import com.avnet.alapps.model.alapps.MinAstTestResult;
import com.avnet.alapps.model.alapps.MinAstTestResultItem;

public class DisplayPartAsm {
	
	private BigDecimal partId;
	private String partNum;
	private String serialNum;
	
	private BigDecimal partAsmId;
	private String typeDs;
	private String typeNm;
	private String xmlTag;
	private List<DisplayPartAsm> partAsms = new ArrayList<DisplayPartAsm>();
	private List<DisplayPartAsmAttr> partAsmAttrs = new ArrayList<DisplayPartAsmAttr>();
	private List<DisplayExcludedPart> excludedPartAsms = new ArrayList<DisplayExcludedPart>();
	private List<DisplayTestResultItem> testResults = new ArrayList<DisplayTestResultItem>();
	private List<DisplayTestExtractedValue> extractedValues = new ArrayList<DisplayTestExtractedValue>();
	private List<BigDecimal> filterDisplayTestResultIds = new ArrayList<BigDecimal>();
	
	public class TestSystemSummary {
		public String testSystemName = null;
		public boolean hasPass = false;
		public Date passDate = null;
		public boolean hasFail = false;
		public Date failDate = null;
		public boolean hasAbort = false;
		public Date abortDate = null;
	}
	
	private class DisplayPartAsmComparator implements Comparator<DisplayPartAsm> {
	    @Override
	    public int compare(DisplayPartAsm o1, DisplayPartAsm o2) {
	    	return o1.getTypeDs().compareTo(o2.getTypeDs());
	    }
	}
	
	private class NodeAscDisplayPartAsmComparator implements Comparator<DisplayPartAsm> {
	    @Override
	    public int compare(DisplayPartAsm o1, DisplayPartAsm o2) {
	    	String o1Location = "";
	    	String o2Location = "";
	    	boolean o1LocationIsNode = false;
	    	boolean o2LocationIsNode = false;
	    	
	    	if ( "NODE".equalsIgnoreCase(o1.getTypeNm()) ) {
	    		o1LocationIsNode = true;
	    	}
	    	
	    	if ( "NODE".equalsIgnoreCase(o2.getTypeNm()) ) {
	    		o2LocationIsNode = true;
	    	}
	    	
	    	for ( DisplayPartAsmAttr attr : o1.getPartAsmAttrs() ) {
	    		if ( "LOCATION".equalsIgnoreCase(attr.getAttrNm()) ) {
	    			o1Location = attr.getValueTx();
	    			break;
	    		}
	    	}
	    	
	    	for ( DisplayPartAsmAttr attr : o2.getPartAsmAttrs() ) {
	    		if ( "LOCATION".equalsIgnoreCase(attr.getAttrNm()) ) {
	    			o2Location = attr.getValueTx();
	    			break;
	    		}
	    	}
	    	
	    	if ( !o1LocationIsNode && !o2LocationIsNode ) { //non-nodes by type name ascending
	    		return o1.getTypeDs().compareTo(o2.getTypeDs());
	    	}
	    	else if ( !o1LocationIsNode && o2LocationIsNode ) { //non-nodes before nodes
	    		return -1;
	    	}
	    	else if ( o1LocationIsNode && !o2LocationIsNode ) { //nodes after non-nodes
	    		return 1;
	    	}
	 
	    	return o1Location.compareTo(o2Location); //node locations ascending
	    }
	}
	
	private class DisplayPartAsmAttrComparator implements Comparator<DisplayPartAsmAttr> {
	    @Override
	    public int compare(DisplayPartAsmAttr o1, DisplayPartAsmAttr o2) {
	    	return o1.getAttrNm().compareTo(o2.getAttrNm());
	    }
	}
	
	private class DisplayTestResultItemDateDescendingComparator implements Comparator<DisplayTestResultItem> {
	    @Override
	    public int compare(DisplayTestResultItem o1, DisplayTestResultItem o2) {
	    	return o2.getDate().compareTo(o1.getDate());
	    }
	}
	
	private class DisplayTestExtractedValueDateDescendingComparator implements Comparator<DisplayTestExtractedValue> {
	    @Override
	    public int compare(DisplayTestExtractedValue o1, DisplayTestExtractedValue o2) {
	    	return o2.getDate().compareTo(o1.getDate());
	    }
	}
	
	public DisplayPartAsm(MinAstPartAsm astPartAsm, boolean benchMark) {
		
	
		
		if ( astPartAsm != null ) {
			this.partAsmId = astPartAsm.getPartAsmId();
			this.typeNm = astPartAsm.getAstPart().getAstCompType().getTypeNm();
			this.typeDs = astPartAsm.getAstPart().getAstCompType().getTypeDs();
			this.xmlTag = astPartAsm.getAstPart().getAstCompType().getXmlTag();
			boolean isChassis = "chassis".equalsIgnoreCase(this.typeNm);
	
			DateTime partStart = new DateTime();
			DateTime partsStart = new DateTime();
		
			for ( Object o : astPartAsm.getAstPartAsms() ) {
				MinAstPartAsm p = (MinAstPartAsm)o;
				this.partAsms.add(new DisplayPartAsm(p, benchMark));
			}
			
			DateTime partsEnd = new DateTime();
			
			//For chassis, sort child nodes by location, others components by part type
			if ( isChassis ) {
				Collections.sort(this.partAsms, new NodeAscDisplayPartAsmComparator());
			}
			else {
				Collections.sort(this.partAsms, new DisplayPartAsmComparator());
			}
			
			this.partId = astPartAsm.getAstPart().getPartId();
			for ( Object o : astPartAsm.getAstPartAsmAttrs() ) {
				MinAstPartAsmAttr a = (MinAstPartAsmAttr)o;
				partAsmAttrs.add(new DisplayPartAsmAttr(a));
				
				if ( "SERIALNUMBER".equalsIgnoreCase(a.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
					this.serialNum = a.getValueTx();
				}
				else if ( "PARTNUMBER".equalsIgnoreCase(a.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
					this.partNum = a.getValueTx();
				}
			}
			Collections.sort(partAsmAttrs, new DisplayPartAsmAttrComparator());
	
			//Handle chassis differently, populate with test result header if so
			if ( isChassis ) {
	
				for ( Object tro : astPartAsm.getAstTestResults() ) {
					MinAstTestResult tr = (MinAstTestResult)tro;
	
	
						this.testResults.add(
								new DisplayTestResultItem(
										tr.getTestResultId(), 
										tr.getTestSystemNm(), 
										tr.getAstTestResultCode().getCodeNm(), 
										tr.getCreateDt()
										)
								);
				
				}
			}
			
	
				
				//Build an summary of test result items for this component for each test system according to filter.
				//Nutanix does not want multiple specific tests for each component.
				if ( astPartAsm.getAstTestResultItems() != null && astPartAsm.getAstTestResultItems().size() > 0 ) {
					
					Map<BigDecimal, TestSystemSummary> summary = new HashMap<BigDecimal, TestSystemSummary>();
					
					for ( Object o : astPartAsm.getAstTestResultItems() ) {
						MinAstTestResultItem testResultItem = (MinAstTestResultItem)o ;
						BigDecimal trId  = testResultItem.getAstTestResult().getTestResultId();
						
						//if ( filterDisplayTestResultIds.contains(trId) ) {
								String testSysName = testResultItem.getAstTestResult().getTestSystemNm();
								if ( !isChassis  && !summary.containsKey(trId) ) {
									summary.put(trId, new TestSystemSummary());
								}
								
								if ( "PASS".equalsIgnoreCase(testResultItem.getResultCodeNm()) ) {
									//Get passing extracted values
									if ( testResultItem.getExtractedVal() != null && 
											testResultItem.getExtractedVal().trim().length() > 0 ) {
	
										//Add passing extracted value, even for the chassis. Extracted
										//results do not get summarized.
										this.extractedValues.add(
											new DisplayTestExtractedValue(
												trId,	
												testSysName, 
												testResultItem.getTestNm(), 
												testResultItem.getTestDs(), 
												testResultItem.getExtractedVal().trim(),
												testResultItem.getCreateDt())
										);
										
										//Also add a passing record, because some test systems may only 
										//send extracted pass records. Chassis still only gets header test results from
										//test system though.
										if ( !isChassis ) {
											summary.get(trId).testSystemName = testSysName;
											summary.get(trId).hasPass = true;
											summary.get(trId).passDate = testResultItem.getCreateDt();
										}
									}
									else if ( !isChassis ) { 
										//Chassis gets test result header, so dont add pass record here.
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasPass = true;
										summary.get(trId).passDate = testResultItem.getCreateDt();
									}
								}
								else if ( "ABORT".equalsIgnoreCase(testResultItem.getResultCodeNm()) ) {
									if ( !isChassis ) {
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasAbort = true;
										summary.get(trId).abortDate = testResultItem.getCreateDt();
									}
								}
								else { 
									//Some aborts will have a numeric code instead of "FAIL", so treat all others as failures.
									if ( !isChassis ) {
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasFail = true;
										summary.get(trId).failDate = testResultItem.getCreateDt();
									}
								}
						//}
					}	
					
					// Build final summary of test results according to hierarchy of status for non-chassis parts.
					if ( !isChassis ) {
						for ( BigDecimal testResultId : summary.keySet() ) {
							TestSystemSummary sysResult = summary.get(testResultId);
							String resultCode = null;
							Date resultDate = null;
	
							if ( sysResult.hasFail ) {
								resultCode = "FAIL";
								resultDate = sysResult.failDate;
							}
							else if ( sysResult.hasAbort ) {
								resultCode = "ABORT";
								resultDate = sysResult.abortDate;
							}
							else if ( sysResult.hasPass ) {
								resultCode = "PASS";
								resultDate = sysResult.passDate;
							}
							
							if ( resultCode != null ) {
								this.testResults.add( new DisplayTestResultItem(testResultId, sysResult.testSystemName, resultCode, resultDate) );
							}
						}
					}
				}
		
				// Sort the test result extracted values by date ascending
				if ( this.extractedValues != null ) {
					Collections.sort(this.extractedValues, new DisplayTestExtractedValueDateDescendingComparator());
				}
	
			
			// Sort the test results by date ascending
			if ( this.testResults != null ) {
				Collections.sort(this.testResults, new DisplayTestResultItemDateDescendingComparator());
			}
			
			if ( benchMark ) {
				if ( isChassis ) {
					DateTime partEnd  = new DateTime();
					Duration chassisDur = new Duration(partStart, partEnd);
					System.out.println("Chassis Minutes: " + String.valueOf(chassisDur.getStandardSeconds() / 60) + " (" + String.valueOf(chassisDur.getStandardSeconds()) + "s)");
					
					Duration partsDur = new Duration(partsStart, partsEnd);
					System.out.println("Sub Parts Minutes: " + String.valueOf(partsDur.getStandardSeconds() / 60) + " (" + String.valueOf(partsDur.getStandardSeconds()) + "s)");
				}
				else {
					DateTime partEnd  = new DateTime();
					Duration partDur = new Duration(partStart, partEnd);
					System.out.println("Part: "  + String.valueOf(partDur.getStandardSeconds()) + "s)");
				}
			}
		

		}
	}
	

	public DisplayPartAsm(AstPartAsm astPartAsm, boolean getTestResultsAndExtractedValuesList, List<BigDecimal> filterTestResultIds) {
	
		if ( filterTestResultIds != null ) {
			this.filterDisplayTestResultIds = filterTestResultIds;
		}
		
		if ( astPartAsm != null ) {
			this.partAsmId = astPartAsm.getPartAsmId();
			this.typeNm = astPartAsm.getAstPart().getAstCompType().getTypeNm();
			this.typeDs = astPartAsm.getAstPart().getAstCompType().getTypeDs();
			this.xmlTag = astPartAsm.getAstPart().getAstCompType().getXmlTag();
			boolean isChassis = "chassis".equalsIgnoreCase(this.typeNm);
		/*
			DateTime chassisStart = null;
			if ( isChassis ) {
				chassisStart = new DateTime();
			}
			DateTime partsStart = new DateTime();
		*/	
			for ( Object o : astPartAsm.getAstPartAsms() ) {
				AstPartAsm p = (AstPartAsm)o;
				this.partAsms.add(new DisplayPartAsm(p, getTestResultsAndExtractedValuesList, this.filterDisplayTestResultIds));
			}
		/*	
			DateTime partsEnd = new DateTime();
		*/	
			//For chassis, sort child nodes by location, others components by part type
			if ( isChassis ) {
				Collections.sort(this.partAsms, new NodeAscDisplayPartAsmComparator());
			}
			else {
				Collections.sort(this.partAsms, new DisplayPartAsmComparator());
			}
			
			this.partId = astPartAsm.getAstPart().getPartId();
			for ( Object o : astPartAsm.getAstPartAsmAttrs() ) {
				AstPartAsmAttr a = (AstPartAsmAttr)o;
				partAsmAttrs.add(new DisplayPartAsmAttr(a));
				
				if ( "SERIALNUMBER".equalsIgnoreCase(a.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
					this.serialNum = a.getValueTx();
				}
				else if ( "PARTNUMBER".equalsIgnoreCase(a.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
					this.partNum = a.getValueTx();
				}
			}
			Collections.sort(partAsmAttrs, new DisplayPartAsmAttrComparator());
			
			for ( Object o : astPartAsm.getAstPartAsmExcludeds() ) {
				AstPartAsmExcluded p = (AstPartAsmExcluded)o;
				excludedPartAsms.add(new DisplayExcludedPart(p));
			}	
			
			
			
			//Handle chassis differently, populate with test result header if so
			if ( isChassis ) {
				int idTotal = filterDisplayTestResultIds.size();
				int idFoundCount = 0;
				for ( Object tro : astPartAsm.getAstTestResults() ) {
					AstTestResult tr = (AstTestResult)tro;
					if ( filterDisplayTestResultIds.contains(tr.getTestResultId()) ) {
	
						this.testResults.add(
								new DisplayTestResultItem(
										tr.getTestResultId(), 
										tr.getTestSystemNm(), 
										tr.getAstTestResultCode().getCodeNm(), 
										tr.getCreateDt()
										)
								);
						idFoundCount++;
						if ( idFoundCount >= idTotal ) {
							break;
						}
					}
				}
			}
			
			if ( getTestResultsAndExtractedValuesList ) {
				
				//Build an summary of test result items for this component for each test system according to filter.
				//Nutanix does not want multiple specific tests for each component.
				if ( astPartAsm.getAstTestResultItems() != null && astPartAsm.getAstTestResultItems().size() > 0 ) {
					
					Map<BigDecimal, TestSystemSummary> summary = new HashMap<BigDecimal, TestSystemSummary>();
					
					for ( Object o : astPartAsm.getAstTestResultItems() ) {
						AstTestResultItem testResultItem = (AstTestResultItem)o;
						BigDecimal trId  = testResultItem.getAstTestResult().getTestResultId();
						
						if ( filterDisplayTestResultIds.contains(trId) ) {
								String testSysName = testResultItem.getAstTestResult().getTestSystemNm();
								if ( !isChassis  && !summary.containsKey(trId) ) {
									summary.put(trId, new TestSystemSummary());
								}
								
								if ( "PASS".equalsIgnoreCase(testResultItem.getResultCodeNm()) ) {
									//Get passing extracted values
									if ( testResultItem.getExtractedVal() != null && 
											testResultItem.getExtractedVal().trim().length() > 0 ) {
	
										//Add passing extracted value, even for the chassis. Extracted
										//results do not get summarized.
										this.extractedValues.add(
											new DisplayTestExtractedValue(
												trId,	
												testSysName, 
												testResultItem.getTestNm(), 
												testResultItem.getTestDs(), 
												testResultItem.getExtractedVal().trim(),
												testResultItem.getCreateDt())
										);
										
										//Also add a passing record, because some test systems may only 
										//send extracted pass records. Chassis still only gets header test results from
										//test system though.
										if ( !isChassis ) {
											summary.get(trId).testSystemName = testSysName;
											summary.get(trId).hasPass = true;
											summary.get(trId).passDate = testResultItem.getCreateDt();
										}
									}
									else if ( !isChassis ) { 
										//Chassis gets test result header, so dont add pass record here.
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasPass = true;
										summary.get(trId).passDate = testResultItem.getCreateDt();
									}
								}
								else if ( "ABORT".equalsIgnoreCase(testResultItem.getResultCodeNm()) ) {
									if ( !isChassis ) {
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasAbort = true;
										summary.get(trId).abortDate = testResultItem.getCreateDt();
									}
								}
								else { 
									//Some aborts will have a numeric code instead of "FAIL", so treat all others as failures.
									if ( !isChassis ) {
										summary.get(trId).testSystemName = testSysName;
										summary.get(trId).hasFail = true;
										summary.get(trId).failDate = testResultItem.getCreateDt();
									}
								}
						}
					}	
					
					// Build final summary of test results according to hierarchy of status for non-chassis parts.
					if ( !isChassis ) {
						for ( BigDecimal testResultId : summary.keySet() ) {
							TestSystemSummary sysResult = summary.get(testResultId);
							String resultCode = null;
							Date resultDate = null;
	
							if ( sysResult.hasFail ) {
								resultCode = "FAIL";
								resultDate = sysResult.failDate;
							}
							else if ( sysResult.hasAbort ) {
								resultCode = "ABORT";
								resultDate = sysResult.abortDate;
							}
							else if ( sysResult.hasPass ) {
								resultCode = "PASS";
								resultDate = sysResult.passDate;
							}
							
							if ( resultCode != null ) {
								this.testResults.add( new DisplayTestResultItem(testResultId, sysResult.testSystemName, resultCode, resultDate) );
							}
						}
					}
				}
		
				// Sort the test result extracted values by date ascending
				if ( this.extractedValues != null ) {
					Collections.sort(this.extractedValues, new DisplayTestExtractedValueDateDescendingComparator());
				}
			}
			
			// Sort the test results by date ascending
			if ( this.testResults != null ) {
				Collections.sort(this.testResults, new DisplayTestResultItemDateDescendingComparator());
			}
			
/*
			if ( isChassis ) {
				DateTime chassisEnd  = new DateTime();
				Duration chassisDur = new Duration(chassisStart, chassisEnd);
				System.out.println("Chassis Minutes: " + String.valueOf(chassisDur.getStandardSeconds() / 60) + " (" + String.valueOf(chassisDur.getStandardSeconds()) + "s)");
				
				Duration partsDur = new Duration(partsStart, partsEnd);
				System.out.println("Parts Minutes: " + String.valueOf(partsDur.getStandardSeconds() / 60) + " (" + String.valueOf(partsDur.getStandardSeconds()) + "s)");
			}
*/		

		}
	}

	public BigDecimal getPartAsmId() {
		return partAsmId;
	}

	public void setPartAsmId(BigDecimal partAsmId) {
		this.partAsmId = partAsmId;
	}

	public String getTypeNm() {
		return typeNm;
	}

	public void setTypeNm(String typeNm) {
		this.typeNm = typeNm;
	}

	public String getXmlTag() {
		return xmlTag;
	}

	public void setXmlTag(String xmlTag) {
		this.xmlTag = xmlTag;
	}

	public List<DisplayPartAsm> getPartAsms() {
		Collections.sort(partAsms, new DisplayPartAsmComparator());
		return partAsms;
	}

	public void setPartAsms(List<DisplayPartAsm> partAsms) {
		this.partAsms = partAsms;
	}

	public List<DisplayPartAsmAttr> getPartAsmAttrs() {
		Collections.sort(partAsmAttrs, new DisplayPartAsmAttrComparator());
		return partAsmAttrs;
	}

	public void setPartAsmAttrs(List<DisplayPartAsmAttr> partAsmAttrs) {
		this.partAsmAttrs = partAsmAttrs;
	}

	public String getTypeDs() {
		return typeDs;
	}

	public void setTypeDs(String typeDs) {
		this.typeDs = typeDs;
	}
	
	public String toXML() {
		
		if ( this.xmlTag == null || this.xmlTag.length() == 0  ) {
			return "";
		}
		
		StringBuilder s = new StringBuilder()
		.append("<").append(this.xmlTag).append(">");
		
		for ( DisplayPartAsmAttr a : this.partAsmAttrs ) {
			s.append(a.toXML());
		}
		
		for ( DisplayPartAsm p :  this.partAsms ) {
			s.append(p.toXML());
		}
		
		//if ( !"NODE".equalsIgnoreCase(this.typeNm) ) {
		s.append("<test_results>");
		if ( this.testResults != null && this.testResults.size() > 0 ) {
			for ( DisplayTestResultItem r :  this.testResults ) {
				s.append(r.toXML());
			}
		}
		s.append("</test_results>");
		
		s.append("<extracted_values>");
		if ( this.extractedValues != null && this.extractedValues.size() > 0 ) {
			for ( DisplayTestExtractedValue v :  this.extractedValues ) {
				s.append(v.toXML());
			}
		}
		s.append("</extracted_values>");
		//}
		
		s.append("</").append(this.xmlTag).append(">")
		;
		
		return s.toString();
	}

	public List<DisplayExcludedPart> getExcludedPartAsms() {
		return excludedPartAsms;
	}

	public void setExcludedPartAsms(List<DisplayExcludedPart> excludedPartAsms) {
		this.excludedPartAsms = excludedPartAsms;
	}

	public List<DisplayTestResultItem> getTestResults() {
		return testResults;
	}

	public void setTestResults(List<DisplayTestResultItem> testResults) {
		this.testResults = testResults;
	}

	public List<DisplayTestExtractedValue> getExtractedValues() {
		return extractedValues;
	}

	public void setExtractedValues(List<DisplayTestExtractedValue> extractedValues) {
		this.extractedValues = extractedValues;
	}

	public BigDecimal getPartId() {
		return partId;
	}

	public void setPartId(BigDecimal partId) {
		this.partId = partId;
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
	
	

}
