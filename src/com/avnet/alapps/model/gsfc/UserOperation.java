package com.avnet.alapps.model.gsfc;

// Generated Apr 14, 2015 5:50:13 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * UserOperation generated by hbm2java
 */
public class UserOperation implements java.io.Serializable {

	private BigDecimal userOperationId;
	private Users users;
	private Operation operation;
	private Date createDt;
	private BigDecimal createUserId;
	private Date updateDt;
	private BigDecimal updateUserId;

	public UserOperation() {
	}

	public UserOperation(BigDecimal userOperationId, Users users,
			Operation operation, Date createDt, BigDecimal createUserId,
			Date updateDt, BigDecimal updateUserId) {
		this.userOperationId = userOperationId;
		this.users = users;
		this.operation = operation;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
	}

	public BigDecimal getUserOperationId() {
		return this.userOperationId;
	}

	public void setUserOperationId(BigDecimal userOperationId) {
		this.userOperationId = userOperationId;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Operation getOperation() {
		return this.operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
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

}