package com.avnet.alapps.model.dbconnect;

// Generated Jan 4, 2016 4:31:43 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Integration generated by hbm2java
 */
public class Integration implements java.io.Serializable {

	private IntegrationId id;
	private int itsTeamId;
	private int itsQty;
	private String itsAmsNum;
	private Date itsCreateDate;
	private String itsUlSnTrackingFlg;
	private String itsCustno;
	private String itsNotes;
	private String itsShiptoName;
	private Date itsShiptoDate;
	private String itsShiptoAddr1;
	private String itsShiptoAddr2;
	private String itsShiptoCity;
	private String itsShiptoState;
	private String itsShiptoCountry;
	private String itsShiptoZip;
	private Date itsPromiseDate;
	private String itsCompanyname;
	private Date itsPlannedShipDate;
	private Date itsAvailableToProdDate;
	private Date itsUpdateDate;
	private String itsUpdateUser;
	private Date itsRequiredDate;
	private String itsCustShipviaCode;
	private String itsWaybillno;
	private String itsCustpono;
	private String itsIbmOrder;
	private String itsEnterprise;
	private String soaNo;
	private String integrationBranchNo;
	private Date originallySetPromiseDt;
	private Date originallySetPlannedShipDt;
	private Date originallySetAtpDt;
	private Date originallySetRequiredDt;
	private String drawingTx;
	private String amsCustno;
	private Integer itsCurrentStatusTypeId;
	private Date itsCurrentStatusDt;
	private BigDecimal itsOrderValueAm;
	private BigDecimal itsTotalCostAm;
	private BigDecimal itsTotalResaleAm;
	private Date itsBookDate;
	private String fgiOrderFl;
	private String osiFl;
	private Date latestOsiPrintDt;
	private Short projectActivityId;
	private String approvingEngineerId;
	private Date approvalDt;
	private String scn;
	private Integer totalIntegrationCycleMins;
	private String invoiceNo;
	private String shipmentScheduledFl;
	private BigDecimal projectId;
	private BigDecimal elapsedCycleTimeDaysQt;
	private BigDecimal elapsedCycleTimeMinutesQt;
	private BigDecimal totalCycleTimeDaysQt;
	private BigDecimal totalCycleTimeMinutesQt;
	private Date insertDate;
	private String insertUser;
	private Date updateDate;
	private String updateUser;
	private String specialIntegrationFl;
	private String itsBilltoNm;
	private Date firstFillDt;
	private Date lastFillDt;
	private String partVerificationRequiredFl;
	private String systemType;
	private String sapMaterialNo;
	private String billToCustNo;
	private String endUserCustomerNo;
	private String sapStatusTx;
	private String endUserCustomerNm;
	private String previousIcn;
	private String splitIcnFl;
	private BigDecimal salesOrderLineItemNo;
	private String carrierNm;
	private BigDecimal integrationCostAm;
	private Character rmaFl;
	private String sourceInspectionUserid;
	private Date sourceInspectionDt;
	private String dataCaptureRequiredFl;
	private String profitCenterCd;
	private BigDecimal serviceLevelExpectDayQt;
	private Date availableToReleaseDt;
	private Date customerAbiDt;
	private Date customerResponseReceivedDt;
	private Date productionFatToAbiDt;
	private int itsBuildTypeId;
	private String chargeOnlyBuildFl;
	private String mfgCd;
	private String groupCd;
	private String shippedInvoiceNo;
	private String customerPartNo;
	private String customerRevNo;
	private String connectorFl;
	private String cableFl;
	private Set dataCaptureDetails = new HashSet(0);

	public Integration() {
	}

	public Integration(IntegrationId id, int itsTeamId, int itsQty,
			String itsAmsNum, String scn, String invoiceNo,
			String shipmentScheduledFl, String insertUser,
			String specialIntegrationFl, String partVerificationRequiredFl,
			int itsBuildTypeId) {
		this.id = id;
		this.itsTeamId = itsTeamId;
		this.itsQty = itsQty;
		this.itsAmsNum = itsAmsNum;
		this.scn = scn;
		this.invoiceNo = invoiceNo;
		this.shipmentScheduledFl = shipmentScheduledFl;
		this.insertUser = insertUser;
		this.specialIntegrationFl = specialIntegrationFl;
		this.partVerificationRequiredFl = partVerificationRequiredFl;
		this.itsBuildTypeId = itsBuildTypeId;
	}

	public Integration(IntegrationId id, int itsTeamId, int itsQty,
			String itsAmsNum, Date itsCreateDate, String itsUlSnTrackingFlg,
			String itsCustno, String itsNotes, String itsShiptoName,
			Date itsShiptoDate, String itsShiptoAddr1, String itsShiptoAddr2,
			String itsShiptoCity, String itsShiptoState,
			String itsShiptoCountry, String itsShiptoZip, Date itsPromiseDate,
			String itsCompanyname, Date itsPlannedShipDate,
			Date itsAvailableToProdDate, Date itsUpdateDate,
			String itsUpdateUser, Date itsRequiredDate,
			String itsCustShipviaCode, String itsWaybillno, String itsCustpono,
			String itsIbmOrder, String itsEnterprise, String soaNo,
			String integrationBranchNo, Date originallySetPromiseDt,
			Date originallySetPlannedShipDt, Date originallySetAtpDt,
			Date originallySetRequiredDt, String drawingTx, String amsCustno,
			Integer itsCurrentStatusTypeId, Date itsCurrentStatusDt,
			BigDecimal itsOrderValueAm, BigDecimal itsTotalCostAm,
			BigDecimal itsTotalResaleAm, Date itsBookDate, String fgiOrderFl,
			String osiFl, Date latestOsiPrintDt, Short projectActivityId,
			String approvingEngineerId, Date approvalDt, String scn,
			Integer totalIntegrationCycleMins, String invoiceNo,
			String shipmentScheduledFl, BigDecimal projectId,
			BigDecimal elapsedCycleTimeDaysQt,
			BigDecimal elapsedCycleTimeMinutesQt,
			BigDecimal totalCycleTimeDaysQt,
			BigDecimal totalCycleTimeMinutesQt, Date insertDate,
			String insertUser, Date updateDate, String updateUser,
			String specialIntegrationFl, String itsBilltoNm, Date firstFillDt,
			Date lastFillDt, String partVerificationRequiredFl,
			String systemType, String sapMaterialNo, String billToCustNo,
			String endUserCustomerNo, String sapStatusTx,
			String endUserCustomerNm, String previousIcn, String splitIcnFl,
			BigDecimal salesOrderLineItemNo, String carrierNm,
			BigDecimal integrationCostAm, Character rmaFl,
			String sourceInspectionUserid, Date sourceInspectionDt,
			String dataCaptureRequiredFl, String profitCenterCd,
			BigDecimal serviceLevelExpectDayQt, Date availableToReleaseDt,
			Date customerAbiDt, Date customerResponseReceivedDt,
			Date productionFatToAbiDt, int itsBuildTypeId,
			String chargeOnlyBuildFl, String mfgCd, String groupCd,
			String shippedInvoiceNo, String customerPartNo,
			String customerRevNo, String connectorFl, String cableFl,
			Set dataCaptureDetails) {
		this.id = id;
		this.itsTeamId = itsTeamId;
		this.itsQty = itsQty;
		this.itsAmsNum = itsAmsNum;
		this.itsCreateDate = itsCreateDate;
		this.itsUlSnTrackingFlg = itsUlSnTrackingFlg;
		this.itsCustno = itsCustno;
		this.itsNotes = itsNotes;
		this.itsShiptoName = itsShiptoName;
		this.itsShiptoDate = itsShiptoDate;
		this.itsShiptoAddr1 = itsShiptoAddr1;
		this.itsShiptoAddr2 = itsShiptoAddr2;
		this.itsShiptoCity = itsShiptoCity;
		this.itsShiptoState = itsShiptoState;
		this.itsShiptoCountry = itsShiptoCountry;
		this.itsShiptoZip = itsShiptoZip;
		this.itsPromiseDate = itsPromiseDate;
		this.itsCompanyname = itsCompanyname;
		this.itsPlannedShipDate = itsPlannedShipDate;
		this.itsAvailableToProdDate = itsAvailableToProdDate;
		this.itsUpdateDate = itsUpdateDate;
		this.itsUpdateUser = itsUpdateUser;
		this.itsRequiredDate = itsRequiredDate;
		this.itsCustShipviaCode = itsCustShipviaCode;
		this.itsWaybillno = itsWaybillno;
		this.itsCustpono = itsCustpono;
		this.itsIbmOrder = itsIbmOrder;
		this.itsEnterprise = itsEnterprise;
		this.soaNo = soaNo;
		this.integrationBranchNo = integrationBranchNo;
		this.originallySetPromiseDt = originallySetPromiseDt;
		this.originallySetPlannedShipDt = originallySetPlannedShipDt;
		this.originallySetAtpDt = originallySetAtpDt;
		this.originallySetRequiredDt = originallySetRequiredDt;
		this.drawingTx = drawingTx;
		this.amsCustno = amsCustno;
		this.itsCurrentStatusTypeId = itsCurrentStatusTypeId;
		this.itsCurrentStatusDt = itsCurrentStatusDt;
		this.itsOrderValueAm = itsOrderValueAm;
		this.itsTotalCostAm = itsTotalCostAm;
		this.itsTotalResaleAm = itsTotalResaleAm;
		this.itsBookDate = itsBookDate;
		this.fgiOrderFl = fgiOrderFl;
		this.osiFl = osiFl;
		this.latestOsiPrintDt = latestOsiPrintDt;
		this.projectActivityId = projectActivityId;
		this.approvingEngineerId = approvingEngineerId;
		this.approvalDt = approvalDt;
		this.scn = scn;
		this.totalIntegrationCycleMins = totalIntegrationCycleMins;
		this.invoiceNo = invoiceNo;
		this.shipmentScheduledFl = shipmentScheduledFl;
		this.projectId = projectId;
		this.elapsedCycleTimeDaysQt = elapsedCycleTimeDaysQt;
		this.elapsedCycleTimeMinutesQt = elapsedCycleTimeMinutesQt;
		this.totalCycleTimeDaysQt = totalCycleTimeDaysQt;
		this.totalCycleTimeMinutesQt = totalCycleTimeMinutesQt;
		this.insertDate = insertDate;
		this.insertUser = insertUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.specialIntegrationFl = specialIntegrationFl;
		this.itsBilltoNm = itsBilltoNm;
		this.firstFillDt = firstFillDt;
		this.lastFillDt = lastFillDt;
		this.partVerificationRequiredFl = partVerificationRequiredFl;
		this.systemType = systemType;
		this.sapMaterialNo = sapMaterialNo;
		this.billToCustNo = billToCustNo;
		this.endUserCustomerNo = endUserCustomerNo;
		this.sapStatusTx = sapStatusTx;
		this.endUserCustomerNm = endUserCustomerNm;
		this.previousIcn = previousIcn;
		this.splitIcnFl = splitIcnFl;
		this.salesOrderLineItemNo = salesOrderLineItemNo;
		this.carrierNm = carrierNm;
		this.integrationCostAm = integrationCostAm;
		this.rmaFl = rmaFl;
		this.sourceInspectionUserid = sourceInspectionUserid;
		this.sourceInspectionDt = sourceInspectionDt;
		this.dataCaptureRequiredFl = dataCaptureRequiredFl;
		this.profitCenterCd = profitCenterCd;
		this.serviceLevelExpectDayQt = serviceLevelExpectDayQt;
		this.availableToReleaseDt = availableToReleaseDt;
		this.customerAbiDt = customerAbiDt;
		this.customerResponseReceivedDt = customerResponseReceivedDt;
		this.productionFatToAbiDt = productionFatToAbiDt;
		this.itsBuildTypeId = itsBuildTypeId;
		this.chargeOnlyBuildFl = chargeOnlyBuildFl;
		this.mfgCd = mfgCd;
		this.groupCd = groupCd;
		this.shippedInvoiceNo = shippedInvoiceNo;
		this.customerPartNo = customerPartNo;
		this.customerRevNo = customerRevNo;
		this.connectorFl = connectorFl;
		this.cableFl = cableFl;
		this.dataCaptureDetails = dataCaptureDetails;
	}

	public IntegrationId getId() {
		return this.id;
	}

	public void setId(IntegrationId id) {
		this.id = id;
	}

	public int getItsTeamId() {
		return this.itsTeamId;
	}

	public void setItsTeamId(int itsTeamId) {
		this.itsTeamId = itsTeamId;
	}

	public int getItsQty() {
		return this.itsQty;
	}

	public void setItsQty(int itsQty) {
		this.itsQty = itsQty;
	}

	public String getItsAmsNum() {
		return this.itsAmsNum;
	}

	public void setItsAmsNum(String itsAmsNum) {
		this.itsAmsNum = itsAmsNum;
	}

	public Date getItsCreateDate() {
		return this.itsCreateDate;
	}

	public void setItsCreateDate(Date itsCreateDate) {
		this.itsCreateDate = itsCreateDate;
	}

	public String getItsUlSnTrackingFlg() {
		return this.itsUlSnTrackingFlg;
	}

	public void setItsUlSnTrackingFlg(String itsUlSnTrackingFlg) {
		this.itsUlSnTrackingFlg = itsUlSnTrackingFlg;
	}

	public String getItsCustno() {
		return this.itsCustno;
	}

	public void setItsCustno(String itsCustno) {
		this.itsCustno = itsCustno;
	}

	public String getItsNotes() {
		return this.itsNotes;
	}

	public void setItsNotes(String itsNotes) {
		this.itsNotes = itsNotes;
	}

	public String getItsShiptoName() {
		return this.itsShiptoName;
	}

	public void setItsShiptoName(String itsShiptoName) {
		this.itsShiptoName = itsShiptoName;
	}

	public Date getItsShiptoDate() {
		return this.itsShiptoDate;
	}

	public void setItsShiptoDate(Date itsShiptoDate) {
		this.itsShiptoDate = itsShiptoDate;
	}

	public String getItsShiptoAddr1() {
		return this.itsShiptoAddr1;
	}

	public void setItsShiptoAddr1(String itsShiptoAddr1) {
		this.itsShiptoAddr1 = itsShiptoAddr1;
	}

	public String getItsShiptoAddr2() {
		return this.itsShiptoAddr2;
	}

	public void setItsShiptoAddr2(String itsShiptoAddr2) {
		this.itsShiptoAddr2 = itsShiptoAddr2;
	}

	public String getItsShiptoCity() {
		return this.itsShiptoCity;
	}

	public void setItsShiptoCity(String itsShiptoCity) {
		this.itsShiptoCity = itsShiptoCity;
	}

	public String getItsShiptoState() {
		return this.itsShiptoState;
	}

	public void setItsShiptoState(String itsShiptoState) {
		this.itsShiptoState = itsShiptoState;
	}

	public String getItsShiptoCountry() {
		return this.itsShiptoCountry;
	}

	public void setItsShiptoCountry(String itsShiptoCountry) {
		this.itsShiptoCountry = itsShiptoCountry;
	}

	public String getItsShiptoZip() {
		return this.itsShiptoZip;
	}

	public void setItsShiptoZip(String itsShiptoZip) {
		this.itsShiptoZip = itsShiptoZip;
	}

	public Date getItsPromiseDate() {
		return this.itsPromiseDate;
	}

	public void setItsPromiseDate(Date itsPromiseDate) {
		this.itsPromiseDate = itsPromiseDate;
	}

	public String getItsCompanyname() {
		return this.itsCompanyname;
	}

	public void setItsCompanyname(String itsCompanyname) {
		this.itsCompanyname = itsCompanyname;
	}

	public Date getItsPlannedShipDate() {
		return this.itsPlannedShipDate;
	}

	public void setItsPlannedShipDate(Date itsPlannedShipDate) {
		this.itsPlannedShipDate = itsPlannedShipDate;
	}

	public Date getItsAvailableToProdDate() {
		return this.itsAvailableToProdDate;
	}

	public void setItsAvailableToProdDate(Date itsAvailableToProdDate) {
		this.itsAvailableToProdDate = itsAvailableToProdDate;
	}

	public Date getItsUpdateDate() {
		return this.itsUpdateDate;
	}

	public void setItsUpdateDate(Date itsUpdateDate) {
		this.itsUpdateDate = itsUpdateDate;
	}

	public String getItsUpdateUser() {
		return this.itsUpdateUser;
	}

	public void setItsUpdateUser(String itsUpdateUser) {
		this.itsUpdateUser = itsUpdateUser;
	}

	public Date getItsRequiredDate() {
		return this.itsRequiredDate;
	}

	public void setItsRequiredDate(Date itsRequiredDate) {
		this.itsRequiredDate = itsRequiredDate;
	}

	public String getItsCustShipviaCode() {
		return this.itsCustShipviaCode;
	}

	public void setItsCustShipviaCode(String itsCustShipviaCode) {
		this.itsCustShipviaCode = itsCustShipviaCode;
	}

	public String getItsWaybillno() {
		return this.itsWaybillno;
	}

	public void setItsWaybillno(String itsWaybillno) {
		this.itsWaybillno = itsWaybillno;
	}

	public String getItsCustpono() {
		return this.itsCustpono;
	}

	public void setItsCustpono(String itsCustpono) {
		this.itsCustpono = itsCustpono;
	}

	public String getItsIbmOrder() {
		return this.itsIbmOrder;
	}

	public void setItsIbmOrder(String itsIbmOrder) {
		this.itsIbmOrder = itsIbmOrder;
	}

	public String getItsEnterprise() {
		return this.itsEnterprise;
	}

	public void setItsEnterprise(String itsEnterprise) {
		this.itsEnterprise = itsEnterprise;
	}

	public String getSoaNo() {
		return this.soaNo;
	}

	public void setSoaNo(String soaNo) {
		this.soaNo = soaNo;
	}

	public String getIntegrationBranchNo() {
		return this.integrationBranchNo;
	}

	public void setIntegrationBranchNo(String integrationBranchNo) {
		this.integrationBranchNo = integrationBranchNo;
	}

	public Date getOriginallySetPromiseDt() {
		return this.originallySetPromiseDt;
	}

	public void setOriginallySetPromiseDt(Date originallySetPromiseDt) {
		this.originallySetPromiseDt = originallySetPromiseDt;
	}

	public Date getOriginallySetPlannedShipDt() {
		return this.originallySetPlannedShipDt;
	}

	public void setOriginallySetPlannedShipDt(Date originallySetPlannedShipDt) {
		this.originallySetPlannedShipDt = originallySetPlannedShipDt;
	}

	public Date getOriginallySetAtpDt() {
		return this.originallySetAtpDt;
	}

	public void setOriginallySetAtpDt(Date originallySetAtpDt) {
		this.originallySetAtpDt = originallySetAtpDt;
	}

	public Date getOriginallySetRequiredDt() {
		return this.originallySetRequiredDt;
	}

	public void setOriginallySetRequiredDt(Date originallySetRequiredDt) {
		this.originallySetRequiredDt = originallySetRequiredDt;
	}

	public String getDrawingTx() {
		return this.drawingTx;
	}

	public void setDrawingTx(String drawingTx) {
		this.drawingTx = drawingTx;
	}

	public String getAmsCustno() {
		return this.amsCustno;
	}

	public void setAmsCustno(String amsCustno) {
		this.amsCustno = amsCustno;
	}

	public Integer getItsCurrentStatusTypeId() {
		return this.itsCurrentStatusTypeId;
	}

	public void setItsCurrentStatusTypeId(Integer itsCurrentStatusTypeId) {
		this.itsCurrentStatusTypeId = itsCurrentStatusTypeId;
	}

	public Date getItsCurrentStatusDt() {
		return this.itsCurrentStatusDt;
	}

	public void setItsCurrentStatusDt(Date itsCurrentStatusDt) {
		this.itsCurrentStatusDt = itsCurrentStatusDt;
	}

	public BigDecimal getItsOrderValueAm() {
		return this.itsOrderValueAm;
	}

	public void setItsOrderValueAm(BigDecimal itsOrderValueAm) {
		this.itsOrderValueAm = itsOrderValueAm;
	}

	public BigDecimal getItsTotalCostAm() {
		return this.itsTotalCostAm;
	}

	public void setItsTotalCostAm(BigDecimal itsTotalCostAm) {
		this.itsTotalCostAm = itsTotalCostAm;
	}

	public BigDecimal getItsTotalResaleAm() {
		return this.itsTotalResaleAm;
	}

	public void setItsTotalResaleAm(BigDecimal itsTotalResaleAm) {
		this.itsTotalResaleAm = itsTotalResaleAm;
	}

	public Date getItsBookDate() {
		return this.itsBookDate;
	}

	public void setItsBookDate(Date itsBookDate) {
		this.itsBookDate = itsBookDate;
	}

	public String getFgiOrderFl() {
		return this.fgiOrderFl;
	}

	public void setFgiOrderFl(String fgiOrderFl) {
		this.fgiOrderFl = fgiOrderFl;
	}

	public String getOsiFl() {
		return this.osiFl;
	}

	public void setOsiFl(String osiFl) {
		this.osiFl = osiFl;
	}

	public Date getLatestOsiPrintDt() {
		return this.latestOsiPrintDt;
	}

	public void setLatestOsiPrintDt(Date latestOsiPrintDt) {
		this.latestOsiPrintDt = latestOsiPrintDt;
	}

	public Short getProjectActivityId() {
		return this.projectActivityId;
	}

	public void setProjectActivityId(Short projectActivityId) {
		this.projectActivityId = projectActivityId;
	}

	public String getApprovingEngineerId() {
		return this.approvingEngineerId;
	}

	public void setApprovingEngineerId(String approvingEngineerId) {
		this.approvingEngineerId = approvingEngineerId;
	}

	public Date getApprovalDt() {
		return this.approvalDt;
	}

	public void setApprovalDt(Date approvalDt) {
		this.approvalDt = approvalDt;
	}

	public String getScn() {
		return this.scn;
	}

	public void setScn(String scn) {
		this.scn = scn;
	}

	public Integer getTotalIntegrationCycleMins() {
		return this.totalIntegrationCycleMins;
	}

	public void setTotalIntegrationCycleMins(Integer totalIntegrationCycleMins) {
		this.totalIntegrationCycleMins = totalIntegrationCycleMins;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getShipmentScheduledFl() {
		return this.shipmentScheduledFl;
	}

	public void setShipmentScheduledFl(String shipmentScheduledFl) {
		this.shipmentScheduledFl = shipmentScheduledFl;
	}

	public BigDecimal getProjectId() {
		return this.projectId;
	}

	public void setProjectId(BigDecimal projectId) {
		this.projectId = projectId;
	}

	public BigDecimal getElapsedCycleTimeDaysQt() {
		return this.elapsedCycleTimeDaysQt;
	}

	public void setElapsedCycleTimeDaysQt(BigDecimal elapsedCycleTimeDaysQt) {
		this.elapsedCycleTimeDaysQt = elapsedCycleTimeDaysQt;
	}

	public BigDecimal getElapsedCycleTimeMinutesQt() {
		return this.elapsedCycleTimeMinutesQt;
	}

	public void setElapsedCycleTimeMinutesQt(
			BigDecimal elapsedCycleTimeMinutesQt) {
		this.elapsedCycleTimeMinutesQt = elapsedCycleTimeMinutesQt;
	}

	public BigDecimal getTotalCycleTimeDaysQt() {
		return this.totalCycleTimeDaysQt;
	}

	public void setTotalCycleTimeDaysQt(BigDecimal totalCycleTimeDaysQt) {
		this.totalCycleTimeDaysQt = totalCycleTimeDaysQt;
	}

	public BigDecimal getTotalCycleTimeMinutesQt() {
		return this.totalCycleTimeMinutesQt;
	}

	public void setTotalCycleTimeMinutesQt(BigDecimal totalCycleTimeMinutesQt) {
		this.totalCycleTimeMinutesQt = totalCycleTimeMinutesQt;
	}

	public Date getInsertDate() {
		return this.insertDate;
	}

	public void setInsertDate(Date insertDate) {
		this.insertDate = insertDate;
	}

	public String getInsertUser() {
		return this.insertUser;
	}

	public void setInsertUser(String insertUser) {
		this.insertUser = insertUser;
	}

	public Date getUpdateDate() {
		return this.updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getUpdateUser() {
		return this.updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getSpecialIntegrationFl() {
		return this.specialIntegrationFl;
	}

	public void setSpecialIntegrationFl(String specialIntegrationFl) {
		this.specialIntegrationFl = specialIntegrationFl;
	}

	public String getItsBilltoNm() {
		return this.itsBilltoNm;
	}

	public void setItsBilltoNm(String itsBilltoNm) {
		this.itsBilltoNm = itsBilltoNm;
	}

	public Date getFirstFillDt() {
		return this.firstFillDt;
	}

	public void setFirstFillDt(Date firstFillDt) {
		this.firstFillDt = firstFillDt;
	}

	public Date getLastFillDt() {
		return this.lastFillDt;
	}

	public void setLastFillDt(Date lastFillDt) {
		this.lastFillDt = lastFillDt;
	}

	public String getPartVerificationRequiredFl() {
		return this.partVerificationRequiredFl;
	}

	public void setPartVerificationRequiredFl(String partVerificationRequiredFl) {
		this.partVerificationRequiredFl = partVerificationRequiredFl;
	}

	public String getSystemType() {
		return this.systemType;
	}

	public void setSystemType(String systemType) {
		this.systemType = systemType;
	}

	public String getSapMaterialNo() {
		return this.sapMaterialNo;
	}

	public void setSapMaterialNo(String sapMaterialNo) {
		this.sapMaterialNo = sapMaterialNo;
	}

	public String getBillToCustNo() {
		return this.billToCustNo;
	}

	public void setBillToCustNo(String billToCustNo) {
		this.billToCustNo = billToCustNo;
	}

	public String getEndUserCustomerNo() {
		return this.endUserCustomerNo;
	}

	public void setEndUserCustomerNo(String endUserCustomerNo) {
		this.endUserCustomerNo = endUserCustomerNo;
	}

	public String getSapStatusTx() {
		return this.sapStatusTx;
	}

	public void setSapStatusTx(String sapStatusTx) {
		this.sapStatusTx = sapStatusTx;
	}

	public String getEndUserCustomerNm() {
		return this.endUserCustomerNm;
	}

	public void setEndUserCustomerNm(String endUserCustomerNm) {
		this.endUserCustomerNm = endUserCustomerNm;
	}

	public String getPreviousIcn() {
		return this.previousIcn;
	}

	public void setPreviousIcn(String previousIcn) {
		this.previousIcn = previousIcn;
	}

	public String getSplitIcnFl() {
		return this.splitIcnFl;
	}

	public void setSplitIcnFl(String splitIcnFl) {
		this.splitIcnFl = splitIcnFl;
	}

	public BigDecimal getSalesOrderLineItemNo() {
		return this.salesOrderLineItemNo;
	}

	public void setSalesOrderLineItemNo(BigDecimal salesOrderLineItemNo) {
		this.salesOrderLineItemNo = salesOrderLineItemNo;
	}

	public String getCarrierNm() {
		return this.carrierNm;
	}

	public void setCarrierNm(String carrierNm) {
		this.carrierNm = carrierNm;
	}

	public BigDecimal getIntegrationCostAm() {
		return this.integrationCostAm;
	}

	public void setIntegrationCostAm(BigDecimal integrationCostAm) {
		this.integrationCostAm = integrationCostAm;
	}

	public Character getRmaFl() {
		return this.rmaFl;
	}

	public void setRmaFl(Character rmaFl) {
		this.rmaFl = rmaFl;
	}

	public String getSourceInspectionUserid() {
		return this.sourceInspectionUserid;
	}

	public void setSourceInspectionUserid(String sourceInspectionUserid) {
		this.sourceInspectionUserid = sourceInspectionUserid;
	}

	public Date getSourceInspectionDt() {
		return this.sourceInspectionDt;
	}

	public void setSourceInspectionDt(Date sourceInspectionDt) {
		this.sourceInspectionDt = sourceInspectionDt;
	}

	public String getDataCaptureRequiredFl() {
		return this.dataCaptureRequiredFl;
	}

	public void setDataCaptureRequiredFl(String dataCaptureRequiredFl) {
		this.dataCaptureRequiredFl = dataCaptureRequiredFl;
	}

	public String getProfitCenterCd() {
		return this.profitCenterCd;
	}

	public void setProfitCenterCd(String profitCenterCd) {
		this.profitCenterCd = profitCenterCd;
	}

	public BigDecimal getServiceLevelExpectDayQt() {
		return this.serviceLevelExpectDayQt;
	}

	public void setServiceLevelExpectDayQt(BigDecimal serviceLevelExpectDayQt) {
		this.serviceLevelExpectDayQt = serviceLevelExpectDayQt;
	}

	public Date getAvailableToReleaseDt() {
		return this.availableToReleaseDt;
	}

	public void setAvailableToReleaseDt(Date availableToReleaseDt) {
		this.availableToReleaseDt = availableToReleaseDt;
	}

	public Date getCustomerAbiDt() {
		return this.customerAbiDt;
	}

	public void setCustomerAbiDt(Date customerAbiDt) {
		this.customerAbiDt = customerAbiDt;
	}

	public Date getCustomerResponseReceivedDt() {
		return this.customerResponseReceivedDt;
	}

	public void setCustomerResponseReceivedDt(Date customerResponseReceivedDt) {
		this.customerResponseReceivedDt = customerResponseReceivedDt;
	}

	public Date getProductionFatToAbiDt() {
		return this.productionFatToAbiDt;
	}

	public void setProductionFatToAbiDt(Date productionFatToAbiDt) {
		this.productionFatToAbiDt = productionFatToAbiDt;
	}

	public int getItsBuildTypeId() {
		return this.itsBuildTypeId;
	}

	public void setItsBuildTypeId(int itsBuildTypeId) {
		this.itsBuildTypeId = itsBuildTypeId;
	}

	public String getChargeOnlyBuildFl() {
		return this.chargeOnlyBuildFl;
	}

	public void setChargeOnlyBuildFl(String chargeOnlyBuildFl) {
		this.chargeOnlyBuildFl = chargeOnlyBuildFl;
	}

	public String getMfgCd() {
		return this.mfgCd;
	}

	public void setMfgCd(String mfgCd) {
		this.mfgCd = mfgCd;
	}

	public String getGroupCd() {
		return this.groupCd;
	}

	public void setGroupCd(String groupCd) {
		this.groupCd = groupCd;
	}

	public String getShippedInvoiceNo() {
		return this.shippedInvoiceNo;
	}

	public void setShippedInvoiceNo(String shippedInvoiceNo) {
		this.shippedInvoiceNo = shippedInvoiceNo;
	}

	public String getCustomerPartNo() {
		return this.customerPartNo;
	}

	public void setCustomerPartNo(String customerPartNo) {
		this.customerPartNo = customerPartNo;
	}

	public String getCustomerRevNo() {
		return this.customerRevNo;
	}

	public void setCustomerRevNo(String customerRevNo) {
		this.customerRevNo = customerRevNo;
	}

	public String getConnectorFl() {
		return this.connectorFl;
	}

	public void setConnectorFl(String connectorFl) {
		this.connectorFl = connectorFl;
	}

	public String getCableFl() {
		return this.cableFl;
	}

	public void setCableFl(String cableFl) {
		this.cableFl = cableFl;
	}

	public Set getDataCaptureDetails() {
		return this.dataCaptureDetails;
	}

	public void setDataCaptureDetails(Set dataCaptureDetails) {
		this.dataCaptureDetails = dataCaptureDetails;
	}

}