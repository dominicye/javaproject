package com.avnet.alapps.model.dbconnect;

// Generated Apr 11, 2016 1:59:57 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * TopLevelAssembly generated by hbm2java
 */
public class TopLevelAssembly implements java.io.Serializable {

	private BigDecimal topLevelAssemblyId;
	private Project project;
	private BigDecimal topLevelAssemblyStatusId;
	private Date applCreateDt;
	private String applCreateUserid;
	private Date applUpdateDt;
	private String applUpdateUserid;
	private String customerPartNo;
	private BigDecimal currTlaRevId;
	private String customerRevNo;
	private String sapMaterialNo;
	private String tlaDs;
	private String tlaNm;
	private String lockedById;
	private String lockedFl;
	private String tlaReasonTx;

	public TopLevelAssembly() {
	}

	public TopLevelAssembly(BigDecimal topLevelAssemblyId,
			BigDecimal topLevelAssemblyStatusId, Date applCreateDt,
			String applCreateUserid) {
		this.topLevelAssemblyId = topLevelAssemblyId;
		this.topLevelAssemblyStatusId = topLevelAssemblyStatusId;
		this.applCreateDt = applCreateDt;
		this.applCreateUserid = applCreateUserid;
	}

	public TopLevelAssembly(BigDecimal topLevelAssemblyId, Project project,
			BigDecimal topLevelAssemblyStatusId, Date applCreateDt,
			String applCreateUserid, Date applUpdateDt,
			String applUpdateUserid, String customerPartNo,
			BigDecimal currTlaRevId, String customerRevNo,
			String sapMaterialNo, String tlaDs, String tlaNm,
			String lockedById, String lockedFl, String tlaReasonTx) {
		this.topLevelAssemblyId = topLevelAssemblyId;
		this.project = project;
		this.topLevelAssemblyStatusId = topLevelAssemblyStatusId;
		this.applCreateDt = applCreateDt;
		this.applCreateUserid = applCreateUserid;
		this.applUpdateDt = applUpdateDt;
		this.applUpdateUserid = applUpdateUserid;
		this.customerPartNo = customerPartNo;
		this.currTlaRevId = currTlaRevId;
		this.customerRevNo = customerRevNo;
		this.sapMaterialNo = sapMaterialNo;
		this.tlaDs = tlaDs;
		this.tlaNm = tlaNm;
		this.lockedById = lockedById;
		this.lockedFl = lockedFl;
		this.tlaReasonTx = tlaReasonTx;
	}

	public BigDecimal getTopLevelAssemblyId() {
		return this.topLevelAssemblyId;
	}

	public void setTopLevelAssemblyId(BigDecimal topLevelAssemblyId) {
		this.topLevelAssemblyId = topLevelAssemblyId;
	}

	public Project getProject() {
		return this.project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public BigDecimal getTopLevelAssemblyStatusId() {
		return this.topLevelAssemblyStatusId;
	}

	public void setTopLevelAssemblyStatusId(BigDecimal topLevelAssemblyStatusId) {
		this.topLevelAssemblyStatusId = topLevelAssemblyStatusId;
	}

	public Date getApplCreateDt() {
		return this.applCreateDt;
	}

	public void setApplCreateDt(Date applCreateDt) {
		this.applCreateDt = applCreateDt;
	}

	public String getApplCreateUserid() {
		return this.applCreateUserid;
	}

	public void setApplCreateUserid(String applCreateUserid) {
		this.applCreateUserid = applCreateUserid;
	}

	public Date getApplUpdateDt() {
		return this.applUpdateDt;
	}

	public void setApplUpdateDt(Date applUpdateDt) {
		this.applUpdateDt = applUpdateDt;
	}

	public String getApplUpdateUserid() {
		return this.applUpdateUserid;
	}

	public void setApplUpdateUserid(String applUpdateUserid) {
		this.applUpdateUserid = applUpdateUserid;
	}

	public String getCustomerPartNo() {
		return this.customerPartNo;
	}

	public void setCustomerPartNo(String customerPartNo) {
		this.customerPartNo = customerPartNo;
	}

	public BigDecimal getCurrTlaRevId() {
		return this.currTlaRevId;
	}

	public void setCurrTlaRevId(BigDecimal currTlaRevId) {
		this.currTlaRevId = currTlaRevId;
	}

	public String getCustomerRevNo() {
		return this.customerRevNo;
	}

	public void setCustomerRevNo(String customerRevNo) {
		this.customerRevNo = customerRevNo;
	}

	public String getSapMaterialNo() {
		return this.sapMaterialNo;
	}

	public void setSapMaterialNo(String sapMaterialNo) {
		this.sapMaterialNo = sapMaterialNo;
	}

	public String getTlaDs() {
		return this.tlaDs;
	}

	public void setTlaDs(String tlaDs) {
		this.tlaDs = tlaDs;
	}

	public String getTlaNm() {
		return this.tlaNm;
	}

	public void setTlaNm(String tlaNm) {
		this.tlaNm = tlaNm;
	}

	public String getLockedById() {
		return this.lockedById;
	}

	public void setLockedById(String lockedById) {
		this.lockedById = lockedById;
	}

	public String getLockedFl() {
		return this.lockedFl;
	}

	public void setLockedFl(String lockedFl) {
		this.lockedFl = lockedFl;
	}

	public String getTlaReasonTx() {
		return this.tlaReasonTx;
	}

	public void setTlaReasonTx(String tlaReasonTx) {
		this.tlaReasonTx = tlaReasonTx;
	}

}