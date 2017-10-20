package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DisplayTestExtractedValue {
	private BigDecimal testId = null;
	private String client = null;
	private String name = null;
	private String description = null;
	private String value = null;
	private static final String xmlTag = "extracted_value";
	private Date date = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy h:mm:ss a z");

	public DisplayTestExtractedValue(BigDecimal testId, String client, String name, String description, String value, Date date) {
		super();
		this.testId = testId;
		this.client = client;
		this.name = name;
		this.description = description;
		this.value = value;
		this.date = date;
	}

	public String getClient() {
		return client;
	}
	
	public void setClient(String client) {
		this.client = client;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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
			.append("<name>").append(this.name).append("</name>")
			.append("<description>").append(this.description).append("</description>")
			.append("<value>").append(this.value).append("</value>")
			.append("<date>").append(dateString).append("</date>")
			.append("</").append(xmlTag).append(">")
		;
		return s.toString();
	}
}
