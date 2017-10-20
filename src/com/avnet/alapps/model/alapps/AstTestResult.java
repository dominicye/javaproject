package com.avnet.alapps.model.alapps;

// Generated Jun 13, 2016 4:44:09 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AstTestResult generated by hbm2java
 */
public class AstTestResult implements java.io.Serializable {

	private BigDecimal testResultId;
	private AstPartAsm astPartAsm;
	private AstTestResultCode astTestResultCode;
	private String testStation;
	private String testerName;
	private String operatorId;
	private Date createDt;
	private String testSystemNm;
	private Date resultStart;
	private Date resultStop;
	private Set astTestResultItems = new HashSet(0);

	public AstTestResult() {
	}

	public AstTestResult(BigDecimal testResultId,
			AstTestResultCode astTestResultCode, Date createDt) {
		this.testResultId = testResultId;
		this.astTestResultCode = astTestResultCode;
		this.createDt = createDt;
	}

	public AstTestResult(BigDecimal testResultId, AstPartAsm astPartAsm,
			AstTestResultCode astTestResultCode, String testStation,
			String testerName, String operatorId, Date createDt,
			String testSystemNm, Date resultStart, Date resultStop,
			Set astTestResultItems) {
		this.testResultId = testResultId;
		this.astPartAsm = astPartAsm;
		this.astTestResultCode = astTestResultCode;
		this.testStation = testStation;
		this.testerName = testerName;
		this.operatorId = operatorId;
		this.createDt = createDt;
		this.testSystemNm = testSystemNm;
		this.resultStart = resultStart;
		this.resultStop = resultStop;
		this.astTestResultItems = astTestResultItems;
	}

	public BigDecimal getTestResultId() {
		return this.testResultId;
	}

	public void setTestResultId(BigDecimal testResultId) {
		this.testResultId = testResultId;
	}

	public AstPartAsm getAstPartAsm() {
		return this.astPartAsm;
	}

	public void setAstPartAsm(AstPartAsm astPartAsm) {
		this.astPartAsm = astPartAsm;
	}

	public AstTestResultCode getAstTestResultCode() {
		return this.astTestResultCode;
	}

	public void setAstTestResultCode(AstTestResultCode astTestResultCode) {
		this.astTestResultCode = astTestResultCode;
	}

	public String getTestStation() {
		return this.testStation;
	}

	public void setTestStation(String testStation) {
		this.testStation = testStation;
	}

	public String getTesterName() {
		return this.testerName;
	}

	public void setTesterName(String testerName) {
		this.testerName = testerName;
	}

	public String getOperatorId() {
		return this.operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getTestSystemNm() {
		return this.testSystemNm;
	}

	public void setTestSystemNm(String testSystemNm) {
		this.testSystemNm = testSystemNm;
	}

	public Date getResultStart() {
		return this.resultStart;
	}

	public void setResultStart(Date resultStart) {
		this.resultStart = resultStart;
	}

	public Date getResultStop() {
		return this.resultStop;
	}

	public void setResultStop(Date resultStop) {
		this.resultStop = resultStop;
	}

	public Set getAstTestResultItems() {
		return this.astTestResultItems;
	}

	public void setAstTestResultItems(Set astTestResultItems) {
		this.astTestResultItems = astTestResultItems;
	}

}
