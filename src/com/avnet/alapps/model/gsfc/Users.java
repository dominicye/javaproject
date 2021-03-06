package com.avnet.alapps.model.gsfc;

// Generated Apr 14, 2015 5:50:13 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

	private BigDecimal userId;
	private Contact contact;
	private UserRole userRole;
	private Users users;
	private String activeFl;
	private String avnetGlobalUserId;
	private Date createDt;
	private BigDecimal createUserId;
	private String internalUserFl;
	private Date lastLoginDt;
	private String loginNm;
	private String passwordTx;
	private Date updateDt;
	private BigDecimal updateUserId;
	private BigDecimal selfInspectionQt;
	private Set userses = new HashSet(0);
	private Set userContacts = new HashSet(0);
	private Set userOperations = new HashSet(0);

	public Users() {
	}

	public Users(BigDecimal userId, Contact contact, String activeFl,
			Date createDt, BigDecimal createUserId, Date updateDt,
			BigDecimal updateUserId) {
		this.userId = userId;
		this.contact = contact;
		this.activeFl = activeFl;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
	}

	public Users(BigDecimal userId, Contact contact, UserRole userRole,
			Users users, String activeFl, String avnetGlobalUserId,
			Date createDt, BigDecimal createUserId, String internalUserFl,
			Date lastLoginDt, String loginNm, String passwordTx, Date updateDt,
			BigDecimal updateUserId, BigDecimal selfInspectionQt, Set userses,
			Set userContacts, Set userOperations) {
		this.userId = userId;
		this.contact = contact;
		this.userRole = userRole;
		this.users = users;
		this.activeFl = activeFl;
		this.avnetGlobalUserId = avnetGlobalUserId;
		this.createDt = createDt;
		this.createUserId = createUserId;
		this.internalUserFl = internalUserFl;
		this.lastLoginDt = lastLoginDt;
		this.loginNm = loginNm;
		this.passwordTx = passwordTx;
		this.updateDt = updateDt;
		this.updateUserId = updateUserId;
		this.selfInspectionQt = selfInspectionQt;
		this.userses = userses;
		this.userContacts = userContacts;
		this.userOperations = userOperations;
	}

	public BigDecimal getUserId() {
		return this.userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public Contact getContact() {
		return this.contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public String getActiveFl() {
		return this.activeFl;
	}

	public void setActiveFl(String activeFl) {
		this.activeFl = activeFl;
	}

	public String getAvnetGlobalUserId() {
		return this.avnetGlobalUserId;
	}

	public void setAvnetGlobalUserId(String avnetGlobalUserId) {
		this.avnetGlobalUserId = avnetGlobalUserId;
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

	public String getInternalUserFl() {
		return this.internalUserFl;
	}

	public void setInternalUserFl(String internalUserFl) {
		this.internalUserFl = internalUserFl;
	}

	public Date getLastLoginDt() {
		return this.lastLoginDt;
	}

	public void setLastLoginDt(Date lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	public String getLoginNm() {
		return this.loginNm;
	}

	public void setLoginNm(String loginNm) {
		this.loginNm = loginNm;
	}

	public String getPasswordTx() {
		return this.passwordTx;
	}

	public void setPasswordTx(String passwordTx) {
		this.passwordTx = passwordTx;
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

	public BigDecimal getSelfInspectionQt() {
		return this.selfInspectionQt;
	}

	public void setSelfInspectionQt(BigDecimal selfInspectionQt) {
		this.selfInspectionQt = selfInspectionQt;
	}

	public Set getUserses() {
		return this.userses;
	}

	public void setUserses(Set userses) {
		this.userses = userses;
	}

	public Set getUserContacts() {
		return this.userContacts;
	}

	public void setUserContacts(Set userContacts) {
		this.userContacts = userContacts;
	}

	public Set getUserOperations() {
		return this.userOperations;
	}

	public void setUserOperations(Set userOperations) {
		this.userOperations = userOperations;
	}

}
