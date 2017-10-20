package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.avnet.alapps.common.Util;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultCode;
import com.avnet.alapps.model.alapps.AstTestResultItem;

public class DisplayTestResult {
	private BigDecimal testId = null;
	private String client = null;
	private String resultCode = null;
	private Date date = null;
	private List<DisplayTestResultItem> resultItemList = new ArrayList<DisplayTestResultItem>();
	private static final String xmlTag = "test_result";
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a z");
	
	private Date startTime = null;
	private Date stopTime = null;
	
	public DisplayTestResult(AstTestResult tr) {
		this.testId = tr.getTestResultId();
		this.client = tr.getTestSystemNm();
		this.resultCode = tr.getAstTestResultCode().getCodeNm();
		this.date = tr.getCreateDt();
		
		this.startTime = tr.getResultStart();
		this.stopTime = tr.getResultStop();
		
		if ( tr.getAstTestResultItems() != null && tr.getAstTestResultItems().size() > 0 ) {
			for ( Object o : tr.getAstTestResultItems() ) {
				AstTestResultItem tri = (AstTestResultItem)o;
				this.resultItemList.add(new DisplayTestResultItem(tri));
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
	
	

	public String toTestXML() {
		
		if ( this.resultItemList != null && this.resultItemList.size() > 0 ) {
			Collections.sort(this.resultItemList, new Util.DisplayTestResultItemComparator());
		}
		
		String dateString = "";
		String startString = "";
		String stopString = "";
		if ( this.date != null ) {
			dateString = this.dateFormat.format(this.date);
		}
		
		if ( this.startTime != null ) {
			startString = this.dateFormat.format(this.startTime);
		}
		
		if ( this.stopTime != null ) {
			stopString = this.dateFormat.format(this.stopTime);
		}
		
		StringBuilder s = new StringBuilder()
			.append("<client_").append(xmlTag).append(">")
			.append("<test_id>").append(this.testId.toPlainString()).append("</test_id>")
			.append("<client>").append(this.client).append("</client>")
			.append("<result_code>").append(this.resultCode).append("</result_code>")
			.append("<date>").append(dateString).append("</date>")
			
			.append("<result_start>").append(startString).append("</result_start>")
			.append("<result_stop>").append(stopString).append("</result_stop>")
			;

			s.append("<test_result_items>");
			for ( DisplayTestResultItem ri : this.resultItemList ) {
				s.append(ri.toTestXML());
			}
			s.append("</test_result_items>");
		
			s.append("</client_").append(xmlTag).append(">")
		;
		return s.toString();
	}

}
