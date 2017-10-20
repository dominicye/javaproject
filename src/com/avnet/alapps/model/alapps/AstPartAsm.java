package com.avnet.alapps.model.alapps;

// Generated Apr 20, 2016 1:55:16 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * AstPartAsm generated by hbm2java
 */
public class AstPartAsm implements java.io.Serializable {

	private BigDecimal partAsmId;
	private AstPartAsm astPartAsm;
	private AstPart astPart;
	private Date createDt;
	private String createUsrId;
	private BigDecimal touchLevel;
	private BigDecimal depopFl;
	private Date updateDt;
	private String updateUsrId;
	private Set astPartAsmExcludeds = new HashSet(0);
	private Set astPartAsms = new HashSet(0);
	private Set astTestResults = new HashSet(0);
	private Set astPartAsmAttrs = new HashSet(0);
	private Set astTestResultItems = new HashSet(0);

	public AstPartAsm() {
	}

	public AstPartAsm(BigDecimal partAsmId, AstPart astPart) {
		this.partAsmId = partAsmId;
		this.astPart = astPart;
	}

	public AstPartAsm(BigDecimal partAsmId, AstPartAsm astPartAsm,
			AstPart astPart, Date createDt, String createUsrId,
			BigDecimal touchLevel, BigDecimal depopFl, Date updateDt,
			String updateUsrId, Set astPartAsmExcludeds, Set astPartAsms,
			Set astTestResults, Set astPartAsmAttrs, Set astTestResultItems) {
		this.partAsmId = partAsmId;
		this.astPartAsm = astPartAsm;
		this.astPart = astPart;
		this.createDt = createDt;
		this.createUsrId = createUsrId;
		this.touchLevel = touchLevel;
		this.depopFl = depopFl;
		this.updateDt = updateDt;
		this.updateUsrId = updateUsrId;
		this.astPartAsmExcludeds = astPartAsmExcludeds;
		this.astPartAsms = astPartAsms;
		this.astTestResults = astTestResults;
		this.astPartAsmAttrs = astPartAsmAttrs;
		this.astTestResultItems = astTestResultItems;
	}

	public BigDecimal getPartAsmId() {
		return this.partAsmId;
	}

	public void setPartAsmId(BigDecimal partAsmId) {
		this.partAsmId = partAsmId;
	}

	public AstPartAsm getAstPartAsm() {
		return this.astPartAsm;
	}

	public void setAstPartAsm(AstPartAsm astPartAsm) {
		this.astPartAsm = astPartAsm;
	}

	public AstPart getAstPart() {
		return this.astPart;
	}

	public void setAstPart(AstPart astPart) {
		this.astPart = astPart;
	}

	public Date getCreateDt() {
		return this.createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getCreateUsrId() {
		return this.createUsrId;
	}

	public void setCreateUsrId(String createUsrId) {
		this.createUsrId = createUsrId;
	}

	public BigDecimal getTouchLevel() {
		return this.touchLevel;
	}

	public void setTouchLevel(BigDecimal touchLevel) {
		this.touchLevel = touchLevel;
	}

	public BigDecimal getDepopFl() {
		return this.depopFl;
	}

	public void setDepopFl(BigDecimal depopFl) {
		this.depopFl = depopFl;
	}

	public Date getUpdateDt() {
		return this.updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public String getUpdateUsrId() {
		return this.updateUsrId;
	}

	public void setUpdateUsrId(String updateUsrId) {
		this.updateUsrId = updateUsrId;
	}

	public Set getAstPartAsmExcludeds() {
		return this.astPartAsmExcludeds;
	}

	public void setAstPartAsmExcludeds(Set astPartAsmExcludeds) {
		this.astPartAsmExcludeds = astPartAsmExcludeds;
	}

	public Set getAstPartAsms() {
		return this.astPartAsms;
	}

	public void setAstPartAsms(Set astPartAsms) {
		this.astPartAsms = astPartAsms;
	}

	public Set getAstTestResults() {
		return this.astTestResults;
	}

	public void setAstTestResults(Set astTestResults) {
		this.astTestResults = astTestResults;
	}

	public Set getAstPartAsmAttrs() {
		return this.astPartAsmAttrs;
	}

	public void setAstPartAsmAttrs(Set astPartAsmAttrs) {
		this.astPartAsmAttrs = astPartAsmAttrs;
	}

	public Set getAstTestResultItems() {
		return this.astTestResultItems;
	}

	public void setAstTestResultItems(Set astTestResultItems) {
		this.astTestResultItems = astTestResultItems;
	}

}
