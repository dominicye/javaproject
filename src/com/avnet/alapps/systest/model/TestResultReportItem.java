package com.avnet.alapps.systest.model;

import java.math.BigDecimal;
import java.util.Date;

public class TestResultReportItem {
	private Date createDate;
	private BigDecimal testSessionId;
	private String testSystemName;
	private String testResultCode;
	private String chassisSerialNumber;
	private BigDecimal touchLevel;
	private String testName;
	private String testDescription;
	private String testResultItemCode;
	private String testResultItemCodeName;
	private String testResultItemCodeDescription;
	private String componentSerialNumber;
	private String nutanixTopLevelPartNumber;
	
	
	
	
	public TestResultReportItem(Date createDate, BigDecimal testSessionId,
			String testSystemName, String testResultCode,
			String chassisSerialNumber, BigDecimal touchLevel, String testName,
			String testDescription, String testResultItemCode,
			String testResultItemCodeName,
			String testResultItemCodeDescription, String componentSerialNumber, String nutanixTopLevelPartNumber) {
		super();
		this.createDate = createDate;
		this.testSessionId = testSessionId;
		this.testSystemName = testSystemName;
		this.testResultCode = testResultCode;
		this.chassisSerialNumber = chassisSerialNumber;
		this.touchLevel = touchLevel;
		this.testName = testName;
		this.testDescription = testDescription;
		this.testResultItemCode = testResultItemCode;
		this.testResultItemCodeName = testResultItemCodeName;
		this.testResultItemCodeDescription = testResultItemCodeDescription;
		this.componentSerialNumber = componentSerialNumber;
		this.nutanixTopLevelPartNumber = nutanixTopLevelPartNumber;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public BigDecimal getTestSessionId() {
		return testSessionId;
	}
	public void setTestSessionId(BigDecimal testSessionId) {
		this.testSessionId = testSessionId;
	}
	public String getTestSystemName() {
		return testSystemName;
	}
	public void setTestSystemName(String testSystemName) {
		this.testSystemName = testSystemName;
	}
	public String getTestResultCode() {
		return testResultCode;
	}
	public void setTestResultCode(String testResultCode) {
		this.testResultCode = testResultCode;
	}
	public String getChassisSerialNumber() {
		return chassisSerialNumber;
	}
	public void setChassisSerialNumber(String chassisSerialNumber) {
		this.chassisSerialNumber = chassisSerialNumber;
	}
	public BigDecimal getTouchLevel() {
		return touchLevel;
	}
	public void setTouchLevel(BigDecimal touchLevel) {
		this.touchLevel = touchLevel;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestDescription() {
		return testDescription;
	}
	public void setTestDescription(String testDescription) {
		this.testDescription = testDescription;
	}
	public String getTestResultItemCode() {
		return testResultItemCode;
	}
	public void setTestResultItemCode(String testResultItemCode) {
		this.testResultItemCode = testResultItemCode;
	}
	public String getTestResultItemCodeName() {
		return testResultItemCodeName;
	}
	public void setTestResultItemCodeName(String testResultItemCodeName) {
		this.testResultItemCodeName = testResultItemCodeName;
	}
	public String getTestResultItemCodeDescription() {
		return testResultItemCodeDescription;
	}
	public void setTestResultItemCodeDescription(
			String testResultItemCodeDescription) {
		this.testResultItemCodeDescription = testResultItemCodeDescription;
	}
	public String getComponentSerialNumber() {
		return componentSerialNumber;
	}
	public void setComponentSerialNumber(String componentSerialNumber) {
		this.componentSerialNumber = componentSerialNumber;
	}
	public String getNutanixTopLevelPartNumber() {
		return nutanixTopLevelPartNumber;
	}
	public void setNutanixTopLevelPartNumber(
			String nutanixTopLevelPartNumber) {
		this.nutanixTopLevelPartNumber = nutanixTopLevelPartNumber;
	}
	
	
	/*
	 "TO_CHAR(TR.CREATE_DT, 'DD-MON-YY HH24:MI') AS CREATE_DATE, " +
		"TR.TEST_RESULT_ID AS TEST_SESSION_ID, " +
		"TR.TEST_SYSTEM_NM, " +
		"TRC.CODE_NM AS TEST_RESULT_CODE, " +
		"PAAT.VALUE_TX AS CHASSIS_SERIAL_NUMBER, " +
		"PA.TOUCH_LEVEL, " +
		"TRI.TEST_NM AS TEST_NAME, " +
		"TRI.TEST_DS AS TEST_DESC, " +
		"TRIC.CODE_NM AS TEST_RESULT_ITEM_CODE, " +
		"TRI.RESULT_CODE_NM AS TEST_RESULT_ITEM_CODE_NAME, " +
		"TRI.RESULT_CODE_DS AS TEST_RESULT_ITEM_CODE_DESC, " +
		"A.VALUE_TX AS COMPONENT_SERIAL_NUMBER
	 */

}
