package com.avnet.alapps.model.dbconnect;

// Generated Jan 4, 2016 4:31:43 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ItsUnitDetail generated by hbm2java
 */
public class ItsUnitDetail implements java.io.Serializable {

	private long itsUnitDetailId;
	private Integer itsPartId;
	private int itsUnitTypeId;
	private String itsPartMfgcode;
	private String itsPartMfgpartno;
	private Integer amsTechMinutes;
	private Short unitBuildSteps;
	private String techTimeSysBuildFlg;
	private String buildStepsSysGenFlg;
	private String insertUser;
	private Date insertDate;
	private String updateUser;
	private Date updateDate;
	private String itsInvoiceNo;
	private String itsScn;
	private BigDecimal benchTimeMinutesQt;
	private Short dcrBuildStepsQt;
	private Integer itsScheduleId;
	private Short maxConnectedStepNo;
	private String unitSerialNumber;
	private String itsLineItemNo;
	private String batchNo;
	private String sapMaterialNo;
	private String partDescription;
	private String countryOfOriginCd;
	private String dataCaptureRequiredFl;
	private Integer sourceErpId;
	private String unitDateLotCd;
	private String consolidatedUnitFl;
	private BigDecimal consolidatedUnitQt;
	private BigDecimal dateLotWeeklyBuildQt;
	private String connectorContactQt;
	private BigDecimal dcrTouchMinuteQt;
	private BigDecimal pfndMinuteQt;
	private Set dataCaptureDetails = new HashSet(0);

	public ItsUnitDetail() {
	}

	public ItsUnitDetail(long itsUnitDetailId, int itsUnitTypeId,
			String insertUser, Date insertDate, String consolidatedUnitFl) {
		this.itsUnitDetailId = itsUnitDetailId;
		this.itsUnitTypeId = itsUnitTypeId;
		this.insertUser = insertUser;
		this.insertDate = insertDate;
		this.consolidatedUnitFl = consolidatedUnitFl;
	}

	public ItsUnitDetail(long itsUnitDetailId, Integer itsPartId,
			int itsUnitTypeId, String itsPartMfgcode, String itsPartMfgpartno,
			Integer amsTechMinutes, Short unitBuildSteps,
			String techTimeSysBuildFlg, String buildStepsSysGenFlg,
			String insertUser, Date insertDate, String updateUser,
			Date updateDate, String itsInvoiceNo, String itsScn,
			BigDecimal benchTimeMinutesQt, Short dcrBuildStepsQt,
			Integer itsScheduleId, Short maxConnectedStepNo,
			String unitSerialNumber, String itsLineItemNo, String batchNo,
			String sapMaterialNo, String partDescription,
			String countryOfOriginCd, String dataCaptureRequiredFl,
			Integer sourceErpId, String unitDateLotCd,
			String consolidatedUnitFl, BigDecimal consolidatedUnitQt,
			BigDecimal dateLotWeeklyBuildQt, String connectorContactQt,
			BigDecimal dcrTouchMinuteQt, BigDecimal pfndMinuteQt,
			Set dataCaptureDetails) {
		this.itsUnitDetailId = itsUnitDetailId;
		this.itsPartId = itsPartId;
		this.itsUnitTypeId = itsUnitTypeId;
		this.itsPartMfgcode = itsPartMfgcode;
		this.itsPartMfgpartno = itsPartMfgpartno;
		this.amsTechMinutes = amsTechMinutes;
		this.unitBuildSteps = unitBuildSteps;
		this.techTimeSysBuildFlg = techTimeSysBuildFlg;
		this.buildStepsSysGenFlg = buildStepsSysGenFlg;
		this.insertUser = insertUser;
		this.insertDate = insertDate;
		this.updateUser = updateUser;
		this.updateDate = updateDate;
		this.itsInvoiceNo = itsInvoiceNo;
		this.itsScn = itsScn;
		this.benchTimeMinutesQt = benchTimeMinutesQt;
		this.dcrBuildStepsQt = dcrBuildStepsQt;
		this.itsScheduleId = itsScheduleId;
		this.maxConnectedStepNo = maxConnectedStepNo;
		this.unitSerialNumber = unitSerialNumber;
		this.itsLineItemNo = itsLineItemNo;
		this.batchNo = batchNo;
		this.sapMaterialNo = sapMaterialNo;
		this.partDescription = partDescription;
		this.countryOfOriginCd = countryOfOriginCd;
		this.dataCaptureRequiredFl = dataCaptureRequiredFl;
		this.sourceErpId = sourceErpId;
		this.unitDateLotCd = unitDateLotCd;
		this.consolidatedUnitFl = consolidatedUnitFl;
		this.consolidatedUnitQt = consolidatedUnitQt;
		this.dateLotWeeklyBuildQt = dateLotWeeklyBuildQt;
		this.connectorContactQt = connectorContactQt;
		this.dcrTouchMinuteQt = dcrTouchMinuteQt;
		this.pfndMinuteQt = pfndMinuteQt;
		this.dataCaptureDetails = dataCaptureDetails;
	}

	public long getItsUnitDetailId() {
		return this.itsUnitDetailId;
	}

	public void setItsUnitDetailId(long itsUnitDetailId) {
		this.itsUnitDetailId = itsUnitDetailId;
	}

	public Integer getItsPartId() {
		return this.itsPartId;
	}

	public void setItsPartId(Integer itsPartId) {
		this.itsPartId = itsPartId;
	}

	public int getItsUnitTypeId() {
		return this.itsUnitTypeId;
	}

	public void setItsUnitTypeId(int itsUnitTypeId) {
		this.itsUnitTypeId = itsUnitTypeId;
	}

	public String getItsPartMfgcode() {
		return this.itsPartMfgcode;
	}

	public void setItsPartMfgcode(String itsPartMfgcode) {
		this.itsPartMfgcode = itsPartMfgcode;
	}

	public String getItsPartMfgpartno() {
		return this.itsPartMfgpartno;
	}

	public void setItsPartMfgpartno(String itsPartMfgpartno) {
		this.itsPartMfgpartno = itsPartMfgpartno;
	}

	public Integer getAmsTechMinutes() {
		return this.amsTechMinutes;
	}

	public void setAmsTechMinutes(Integer amsTechMinutes) {
		this.amsTechMinutes = amsTechMinutes;
	}

	public Short getUnitBuildSteps() {
		return this.unitBuildSteps;
	}

	public void setUnitBuildSteps(Short unitBuildSteps) {
		this.unitBuildSteps = unitBuildSteps;
	}

	public String getTechTimeSysBuildFlg() {
		return this.techTimeSysBuildFlg;
	}

	public void setTechTimeSysBuildFlg(String techTimeSysBuildFlg) {
		this.techTimeSysBuildFlg = techTimeSysBuildFlg;
	}

	public String getBuildStepsSysGenFlg() {
		return this.buildStepsSysGenFlg;
	}

	public void setBuildStepsSysGenFlg(String buildStepsSysGenFlg) {
		this.buildStepsSysGenFlg = buildStepsSysGenFlg;
	}

	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getItsInvoiceNo() {
		return this.itsInvoiceNo;
	}

	public void setItsInvoiceNo(String itsInvoiceNo) {
		this.itsInvoiceNo = itsInvoiceNo;
	}

	public String getItsScn() {
		return this.itsScn;
	}

	public void setItsScn(String itsScn) {
		this.itsScn = itsScn;
	}

	public BigDecimal getBenchTimeMinutesQt() {
		return this.benchTimeMinutesQt;
	}

	public void setBenchTimeMinutesQt(BigDecimal benchTimeMinutesQt) {
		this.benchTimeMinutesQt = benchTimeMinutesQt;
	}

	public Short getDcrBuildStepsQt() {
		return this.dcrBuildStepsQt;
	}

	public void setDcrBuildStepsQt(Short dcrBuildStepsQt) {
		this.dcrBuildStepsQt = dcrBuildStepsQt;
	}

	public Integer getItsScheduleId() {
		return this.itsScheduleId;
	}

	public void setItsScheduleId(Integer itsScheduleId) {
		this.itsScheduleId = itsScheduleId;
	}

	public Short getMaxConnectedStepNo() {
		return this.maxConnectedStepNo;
	}

	public void setMaxConnectedStepNo(Short maxConnectedStepNo) {
		this.maxConnectedStepNo = maxConnectedStepNo;
	}

	public String getUnitSerialNumber() {
		return this.unitSerialNumber;
	}

	public void setUnitSerialNumber(String unitSerialNumber) {
		this.unitSerialNumber = unitSerialNumber;
	}

	public String getItsLineItemNo() {
		return this.itsLineItemNo;
	}

	public void setItsLineItemNo(String itsLineItemNo) {
		this.itsLineItemNo = itsLineItemNo;
	}

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getSapMaterialNo() {
		return this.sapMaterialNo;
	}

	public void setSapMaterialNo(String sapMaterialNo) {
		this.sapMaterialNo = sapMaterialNo;
	}

	public String getPartDescription() {
		return this.partDescription;
	}

	public void setPartDescription(String partDescription) {
		this.partDescription = partDescription;
	}

	public String getCountryOfOriginCd() {
		return this.countryOfOriginCd;
	}

	public void setCountryOfOriginCd(String countryOfOriginCd) {
		this.countryOfOriginCd = countryOfOriginCd;
	}

	public String getDataCaptureRequiredFl() {
		return this.dataCaptureRequiredFl;
	}

	public void setDataCaptureRequiredFl(String dataCaptureRequiredFl) {
		this.dataCaptureRequiredFl = dataCaptureRequiredFl;
	}

	public Integer getSourceErpId() {
		return this.sourceErpId;
	}

	public void setSourceErpId(Integer sourceErpId) {
		this.sourceErpId = sourceErpId;
	}

	public String getUnitDateLotCd() {
		return this.unitDateLotCd;
	}

	public void setUnitDateLotCd(String unitDateLotCd) {
		this.unitDateLotCd = unitDateLotCd;
	}

	public String getConsolidatedUnitFl() {
		return this.consolidatedUnitFl;
	}

	public void setConsolidatedUnitFl(String consolidatedUnitFl) {
		this.consolidatedUnitFl = consolidatedUnitFl;
	}

	public BigDecimal getConsolidatedUnitQt() {
		return this.consolidatedUnitQt;
	}

	public void setConsolidatedUnitQt(BigDecimal consolidatedUnitQt) {
		this.consolidatedUnitQt = consolidatedUnitQt;
	}

	public BigDecimal getDateLotWeeklyBuildQt() {
		return this.dateLotWeeklyBuildQt;
	}

	public void setDateLotWeeklyBuildQt(BigDecimal dateLotWeeklyBuildQt) {
		this.dateLotWeeklyBuildQt = dateLotWeeklyBuildQt;
	}

	public String getConnectorContactQt() {
		return this.connectorContactQt;
	}

	public void setConnectorContactQt(String connectorContactQt) {
		this.connectorContactQt = connectorContactQt;
	}

	public BigDecimal getDcrTouchMinuteQt() {
		return this.dcrTouchMinuteQt;
	}

	public void setDcrTouchMinuteQt(BigDecimal dcrTouchMinuteQt) {
		this.dcrTouchMinuteQt = dcrTouchMinuteQt;
	}

	public BigDecimal getPfndMinuteQt() {
		return this.pfndMinuteQt;
	}

	public void setPfndMinuteQt(BigDecimal pfndMinuteQt) {
		this.pfndMinuteQt = pfndMinuteQt;
	}

	public Set getDataCaptureDetails() {
		return this.dataCaptureDetails;
	}

	public void setDataCaptureDetails(Set dataCaptureDetails) {
		this.dataCaptureDetails = dataCaptureDetails;
	}

}