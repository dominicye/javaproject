package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultCode;
import com.avnet.alapps.model.alapps.AstTestResultItem;

public class DisplayTestResultItem {
	private BigDecimal testId = null;
	private String client = null;
	private String resultCode = null;
	private Date date = null;
	private static final String xmlTag = "test_result";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a z");

	//For debugging xml output
	private String testName = null;
	private String testDescription = null;
	private String extractedValue = null;
	private String partNumber = null;
	private String serialNumber = null;
	private String location = null;
	private String partType = null;
	private String failCode = null;
	private String failDescription = null;
	private boolean isFailure = false;
	private String nodeLocation = null;
	//
	
	
	public DisplayTestResultItem(BigDecimal testId, String client, String resultCode, Date date) {
		super();
		this.testId = testId;
		this.client = client;
		this.resultCode = resultCode;
		this.date = date;
	}
	
	public DisplayTestResultItem(AstTestResultItem tri) {
		this.testId = tri.getAstTestResult().getTestResultId();
		this.client = tri.getAstTestResult().getTestSystemNm();
		this.resultCode = tri.getAstTestResultCode().getCodeNm();
		
		if ( this.resultCode != null && "FAIL".equalsIgnoreCase(this.resultCode) ) {
			this.isFailure = true;
			this.failCode = tri.getResultCodeNm();
			this.failDescription = tri.getResultCodeDs();
		}
		
		this.date = tri.getCreateDt();
		this.testName = tri.getTestNm();
		this.testDescription = tri.getTestDs();
		this.extractedValue = tri.getExtractedVal();
		
		
		
		if ( tri.getAstPartAsm() != null ) {
			AstPartAsm pasm = tri.getAstPartAsm();
			this.partType = 
				tri.getAstPartAsm().getAstPart().getAstCompType().getTypeNm();
			for ( Object o : pasm.getAstPartAsmAttrs() ) {
				AstPartAsmAttr pasmAttr = (AstPartAsmAttr)o;
				String attrName = 
					pasmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
				if ( attrName.equalsIgnoreCase("serialnumber") ) {
					this.serialNumber = pasmAttr.getValueTx();
				}
				else if ( attrName.equalsIgnoreCase("partnumber") ) {
					this.partNumber = pasmAttr.getValueTx();
				}
				else if ( attrName.equalsIgnoreCase("location") ) {
					this.location = pasmAttr.getValueTx();
				}
			}
			
			AstPartAsm parentPasm = pasm.getAstPartAsm();
			if ( parentPasm != null && "NODE".equalsIgnoreCase(parentPasm.getAstPart().getAstCompType().getTypeNm()) ) {
				for ( Object o : parentPasm.getAstPartAsmAttrs() ) {
					AstPartAsmAttr pasmAttr = (AstPartAsmAttr)o;
					String attrName = 
						pasmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
					if ( attrName.equalsIgnoreCase("location") ) {
						this.nodeLocation = pasmAttr.getValueTx();
						break;
					}
				}
			}
			
		}
		
	}
	
	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
	}
	
	public String getResultCode() {
		return resultCode;
	}
	
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public static String getXmlTag() {
		return xmlTag;
	}

	public BigDecimal getTestId() {
		return testId;
	}

	public void setTestId(BigDecimal testId) {
		this.testId = testId;
	}

	public String toXML() {
		String dateString = "";
		if ( this.date != null ) {
			dateString = this.dateFormat.format(this.date);
		}
		StringBuilder s = new StringBuilder()
			.append("<").append(xmlTag).append(">")
			.append("<test_id>").append(this.testId.toPlainString()).append("</test_id>")
			.append("<client>").append(this.client).append("</client>")
			.append("<result_code>").append(this.resultCode).append("</result_code>")
			.append("<date>").append(dateString).append("</date>")
			.append("</").append(xmlTag).append(">")
		;
		return s.toString();
	}
	
	public String toTestXML() {
		String dateString = "";
		if ( this.date != null ) {
			dateString = this.dateFormat.format(this.date);
		}
		StringBuilder s = new StringBuilder()
			.append("<").append(xmlTag).append(">")
			.append("<test_name>").append( (this.testName != null) ? this.testName : "" ).append("</test_name>")
			.append("<test_description>").append( (this.testDescription != null) ? this.testDescription : "").append("</test_description>")
			.append("<result_code>").append(this.resultCode).append("</result_code>");
			
			if ( this.isFailure ) {
				s.append("<fail_code>").append(this.failCode).append("</fail_code>")
				.append("<fail_description>").append(this.failDescription).append("</fail_description>");
			}
		
			s.append("<extracted_value>").append( (this.extractedValue != null) ? this.extractedValue : "").append("</extracted_value>")
			.append("<date>").append(dateString).append("</date>")
			.append("<component_type>").append( ( this.partType != null ) ? this.partType  : ""  ).append("</component_type>")
			.append("<component_location>").append(  (this.location != null) ? this.location : ""  ).append("</component_location>")
			.append("<component_partnumber>").append( (this.partNumber != null) ? this.partNumber : "" ).append("</component_partnumber>")
			.append("<component_serialnumber>").append( (this.serialNumber != null) ? this.serialNumber : "" ).append("</component_serialnumber>");
			
			if ( this.nodeLocation != null ) {
				s.append("<node_location>").append(this.nodeLocation).append("</node_location>");
			}
			
			s.append("</").append(xmlTag).append(">")
		;
		return s.toString();
	}

	public String getNodeLocation() {
		return nodeLocation;
	}
	
	
}
