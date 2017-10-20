package com.avnet.alapps.model.gsfc;

// Generated Apr 14, 2015 5:50:13 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Operation generated by hbm2java
 */
public class Operation implements java.io.Serializable {

	private BigDecimal operationId;
	private Date createDt;
	private BigDecimal createUserId;
	private String internalOperationFl;
	private String operationCd;
	private String operationDs;
	private String operationNm;
	private Date updateDt;
	private BigDecimal updateUserId;
	private Set userOperations = new HashSet(0);
	private Set userRoleOperations = new HashSet(0);

	public Operation() {
	}

	public Operation(BigDecimal operationId, Date createDt,
			BigDecimal createUserId, String operationDs, String operationNm,
			Date updateDt, BigDecimal updateUserId) {
		this.operationId = operationId;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.operationDs = operationDs;
		this.operationNm = operationNm;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
	}

	public Operation(BigDecimal operationId, Date createDt,
			BigDecimal createUserId, String internalOperationFl,
			String operationCd, String operationDs, String operationNm,
			Date updateDt, BigDecimal updateUserId, Set userOperations,
			Set userRoleOperations) {
		this.operationId = operationId;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.internalOperationFl = internalOperationFl;
		this.operationCd = operationCd;
		this.operationDs = operationDs;
		this.operationNm = operationNm;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
		this.userOperations = userOperations;
		this.userRoleOperations = userRoleOperations;
	}

	public BigDecimal getOperationId() {
		return this.operationId;
	}

	public void setOperationId(BigDecimal operationId) {
		this.operationId = operationId;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public BigDecimal getCreateUserId() {
		return this.createUserId;
	}

	public void setCreateUserId(BigDecimal createUserId) {
		this.createUserId = createUserId;
	}

	public String getInternalOperationFl() {
		return this.internalOperationFl;
	}

	public void setInternalOperationFl(String internalOperationFl) {
		this.internalOperationFl = internalOperationFl;
	}

	public String getOperationCd() {
		return this.operationCd;
	}

	public void setOperationCd(String operationCd) {
		this.operationCd = operationCd;
	}

	public String getOperationDs() {
		return this.operationDs;
	}

	public void setOperationDs(String operationDs) {
		this.operationDs = operationDs;
	}

	public String getOperationNm() {
		return this.operationNm;
	}

	public void setOperationNm(String operationNm) {
		this.operationNm = operationNm;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public BigDecimal getUpdateUserId() {
		return this.updateUserId;
	}

	public void setUpdateUserId(BigDecimal updateUserId) {
		this.updateUserId = updateUserId;
	}

	public Set getUserOperations() {
		return this.userOperations;
	}

	public void setUserOperations(Set userOperations) {
		this.userOperations = userOperations;
	}

	public Set getUserRoleOperations() {
		return this.userRoleOperations;
	}

	public void setUserRoleOperations(Set userRoleOperations) {
		this.userRoleOperations = userRoleOperations;
	}

}
