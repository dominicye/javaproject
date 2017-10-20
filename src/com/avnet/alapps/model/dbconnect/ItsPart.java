package com.avnet.alapps.model.dbconnect;

// Generated Apr 13, 2016 5:16:21 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * ItsPart generated by hbm2java
 */
public class ItsPart implements java.io.Serializable {

	private int itsPartId;
	private ItsPart itsPart;
	private String itsInvoiceNo;
	private String itsScn;
	private int itsPartProjectbomId;
	private String itsPartUlSn;
	private String itsPartSn;
	private String itsPartMfgpartno;
	private String itsPartMfgcode;
	private String itsPartDescription;
	private String itsPartTechBadgeno;
	private String itsBottom;
	private String itsImcPartFlg;
	private String itsCutbackFlg;
	private String itsSerialOverrideFlg;
	private String itsLineitemno;
	private BigDecimal itsPartCost;
	private BigDecimal itsPartResale;
	private String itsPartDupserialFlg;
	private String itsPartScanFlg;
	private String itsPartChassisFlg;
	private String itsPartControlno;
	private String scn;
	private String invoiceNo;
	private Date insertDate;
	private String insertUser;
	private Date updateDate;
	private String updateUser;
	private String consolidatedPartFl;
	private BigDecimal consolidatedPartQt;
	private String modifiedQtIcnFl;
	private BigDecimal itsUnitDetailId;
	private String custPartNo;
	private BigDecimal scnCustomerLineNo;
	private String custDrawingTx;
	private String custReferenceTx01;
	private String partVerificationTx;
	private String partVerificationRequiredFl;
	private String autoTestPartFl;
	private String sapMaterialNo;
	private String serialNoRequiredFl;
	private String reservationLineItem;
	private String reservationNo;
	private Character goodsMvmtFl;
	private String countryOfOriginCd;
	private String dataCaptureRequiredFl;
	private String batchNo;
	private Integer sourceErpId;
	private String storageLocationId;
	private String componentUom;
	private String movementTypeCd;
	private Set itsParts = new HashSet(0);
	private Set dataCaptureDetails = new HashSet(0);

	public ItsPart() {
	}

	public ItsPart(int itsPartId, String itsInvoiceNo, String itsScn,
			int itsPartProjectbomId, String itsPartMfgpartno,
			String itsPartMfgcode, String itsBottom, String scn,
			String invoiceNo, String insertUser, String consolidatedPartFl,
			String modifiedQtIcnFl, String partVerificationRequiredFl) {
		this.itsPartId = itsPartId;
		this.itsInvoiceNo = itsInvoiceNo;
		this.itsScn = itsScn;
		this.itsPartProjectbomId = itsPartProjectbomId;
		this.itsPartMfgpartno = itsPartMfgpartno;
		this.itsPartMfgcode = itsPartMfgcode;
		this.itsBottom = itsBottom;
		this.scn = scn;
		this.invoiceNo = invoiceNo;
		this.insertUser = insertUser;
		this.consolidatedPartFl = consolidatedPartFl;
		this.modifiedQtIcnFl = modifiedQtIcnFl;
		this.partVerificationRequiredFl = partVerificationRequiredFl;
	}

	public ItsPart(int itsPartId, ItsPart itsPart, String itsInvoiceNo,
			String itsScn, int itsPartProjectbomId, String itsPartUlSn,
			String itsPartSn, String itsPartMfgpartno, String itsPartMfgcode,
			String itsPartDescription, String itsPartTechBadgeno,
			String itsBottom, String itsImcPartFlg, String itsCutbackFlg,
			String itsSerialOverrideFlg, String itsLineitemno,
			BigDecimal itsPartCost, BigDecimal itsPartResale,
			String itsPartDupserialFlg, String itsPartScanFlg,
			String itsPartChassisFlg, String itsPartControlno, String scn,
			String invoiceNo, Date insertDate, String insertUser,
			Date updateDate, String updateUser, String consolidatedPartFl,
			BigDecimal consolidatedPartQt, String modifiedQtIcnFl,
			BigDecimal itsUnitDetailId, String custPartNo,
			BigDecimal scnCustomerLineNo, String custDrawingTx,
			String custReferenceTx01, String partVerificationTx,
			String partVerificationRequiredFl, String autoTestPartFl,
			String sapMaterialNo, String serialNoRequiredFl,
			String reservationLineItem, String reservationNo,
			Character goodsMvmtFl, String countryOfOriginCd,
			String dataCaptureRequiredFl, String batchNo, Integer sourceErpId,
			String storageLocationId, String componentUom,
			String movementTypeCd, Set itsParts, Set dataCaptureDetails) {
		this.itsPartId = itsPartId;
		this.itsPart = itsPart;
		this.itsInvoiceNo = itsInvoiceNo;
		this.itsScn = itsScn;
		this.itsPartProjectbomId = itsPartProjectbomId;
		this.itsPartUlSn = itsPartUlSn;
		this.itsPartSn = itsPartSn;
		this.itsPartMfgpartno = itsPartMfgpartno;
		this.itsPartMfgcode = itsPartMfgcode;
		this.itsPartDescription = itsPartDescription;
		this.itsPartTechBadgeno = itsPartTechBadgeno;
		this.itsBottom = itsBottom;
		this.itsImcPartFlg = itsImcPartFlg;
		this.itsCutbackFlg = itsCutbackFlg;
		this.itsSerialOverrideFlg = itsSerialOverrideFlg;
		this.itsLineitemno = itsLineitemno;
		this.itsPartCost = itsPartCost;
		this.itsPartResale = itsPartResale;
		this.itsPartDupserialFlg = itsPartDupserialFlg;
		this.itsPartScanFlg = itsPartScanFlg;
		this.itsPartChassisFlg = itsPartChassisFlg;
		this.itsPartControlno = itsPartControlno;
		this.scn = scn;
		this.invoiceNo = invoiceNo;
		this.insertDate = insertDate;
		this.insertUser = insertUser;
		this.updateDate = updateDate;
		this.updateUser = updateUser;
		this.consolidatedPartFl = consolidatedPartFl;
		this.consolidatedPartQt = consolidatedPartQt;
		this.modifiedQtIcnFl = modifiedQtIcnFl;
		this.itsUnitDetailId = itsUnitDetailId;
		this.custPartNo = custPartNo;
		this.scnCustomerLineNo = scnCustomerLineNo;
		this.custDrawingTx = custDrawingTx;
		this.custReferenceTx01 = custReferenceTx01;
		this.partVerificationTx = partVerificationTx;
		this.partVerificationRequiredFl = partVerificationRequiredFl;
		this.autoTestPartFl = autoTestPartFl;
		this.sapMaterialNo = sapMaterialNo;
		this.serialNoRequiredFl = serialNoRequiredFl;
		this.reservationLineItem = reservationLineItem;
		this.reservationNo = reservationNo;
		this.goodsMvmtFl = goodsMvmtFl;
		this.countryOfOriginCd = countryOfOriginCd;
		this.dataCaptureRequiredFl = dataCaptureRequiredFl;
		this.batchNo = batchNo;
		this.sourceErpId = sourceErpId;
		this.storageLocationId = storageLocationId;
		this.componentUom = componentUom;
		this.movementTypeCd = movementTypeCd;
		this.itsParts = itsParts;
		this.dataCaptureDetails = dataCaptureDetails;
	}

	public int getItsPartId() {
		return this.itsPartId;
	}

	public void setItsPartId(int itsPartId) {
		this.itsPartId = itsPartId;
	}

	public ItsPart getItsPart() {
		return this.itsPart;
	}

	public void setItsPart(ItsPart itsPart) {
		this.itsPart = itsPart;
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

	public int getItsPartProjectbomId() {
		return this.itsPartProjectbomId;
	}

	public void setItsPartProjectbomId(int itsPartProjectbomId) {
		this.itsPartProjectbomId = itsPartProjectbomId;
	}

	public String getItsPartUlSn() {
		return this.itsPartUlSn;
	}

	public void setItsPartUlSn(String itsPartUlSn) {
		this.itsPartUlSn = itsPartUlSn;
	}

	public String getItsPartSn() {
		return this.itsPartSn;
	}

	public void setItsPartSn(String itsPartSn) {
		this.itsPartSn = itsPartSn;
	}

	public String getItsPartMfgpartno() {
		return this.itsPartMfgpartno;
	}

	public void setItsPartMfgpartno(String itsPartMfgpartno) {
		this.itsPartMfgpartno = itsPartMfgpartno;
	}

	public String getItsPartMfgcode() {
		return this.itsPartMfgcode;
	}

	public void setItsPartMfgcode(String itsPartMfgcode) {
		this.itsPartMfgcode = itsPartMfgcode;
	}

	public String getItsPartDescription() {
		return this.itsPartDescription;
	}

	public void setItsPartDescription(String itsPartDescription) {
		this.itsPartDescription = itsPartDescription;
	}

	public String getItsPartTechBadgeno() {
		return this.itsPartTechBadgeno;
	}

	public void setItsPartTechBadgeno(String itsPartTechBadgeno) {
		this.itsPartTechBadgeno = itsPartTechBadgeno;
	}

	public String getItsBottom() {
		return this.itsBottom;
	}

	public void setItsBottom(String itsBottom) {
		this.itsBottom = itsBottom;
	}

	public String getItsImcPartFlg() {
		return this.itsImcPartFlg;
	}

	public void setItsImcPartFlg(String itsImcPartFlg) {
		this.itsImcPartFlg = itsImcPartFlg;
	}

	public String getItsCutbackFlg() {
		return this.itsCutbackFlg;
	}

	public void setItsCutbackFlg(String itsCutbackFlg) {
		this.itsCutbackFlg = itsCutbackFlg;
	}

	public String getItsSerialOverrideFlg() {
		return this.itsSerialOverrideFlg;
	}

	public void setItsSerialOverrideFlg(String itsSerialOverrideFlg) {
		this.itsSerialOverrideFlg = itsSerialOverrideFlg;
	}

	public String getItsLineitemno() {
		return this.itsLineitemno;
	}

	public void setItsLineitemno(String itsLineitemno) {
		this.itsLineitemno = itsLineitemno;
	}

	public BigDecimal getItsPartCost() {
		return this.itsPartCost;
	}

	public void setItsPartCost(BigDecimal itsPartCost) {
		this.itsPartCost = itsPartCost;
	}

	public BigDecimal getItsPartResale() {
		return this.itsPartResale;
	}

	public void setItsPartResale(BigDecimal itsPartResale) {
		this.itsPartResale = itsPartResale;
	}

	public String getItsPartDupserialFlg() {
		return this.itsPartDupserialFlg;
	}

	public void setItsPartDupserialFlg(String itsPartDupserialFlg) {
		this.itsPartDupserialFlg = itsPartDupserialFlg;
	}

	public String getItsPartScanFlg() {
		return this.itsPartScanFlg;
	}

	public void setItsPartScanFlg(String itsPartScanFlg) {
		this.itsPartScanFlg = itsPartScanFlg;
	}

	public String getItsPartChassisFlg() {
		return this.itsPartChassisFlg;
	}

	public void setItsPartChassisFlg(String itsPartChassisFlg) {
		this.itsPartChassisFlg = itsPartChassisFlg;
	}

	public String getItsPartControlno() {
		return this.itsPartControlno;
	}

	public void setItsPartControlno(String itsPartControlno) {
		this.itsPartControlno = itsPartControlno;
	}

	public String getScn() {
		return this.scn;
	}

	public void setScn(String scn) {
		this.scn = scn;
	}

	public String getInvoiceNo() {
		return this.invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public String getConsolidatedPartFl() {
		return this.consolidatedPartFl;
	}

	public void setConsolidatedPartFl(String consolidatedPartFl) {
		this.consolidatedPartFl = consolidatedPartFl;
	}

	public BigDecimal getConsolidatedPartQt() {
		return this.consolidatedPartQt;
	}

	public void setConsolidatedPartQt(BigDecimal consolidatedPartQt) {
		this.consolidatedPartQt = consolidatedPartQt;
	}

	public String getModifiedQtIcnFl() {
		return this.modifiedQtIcnFl;
	}

	public void setModifiedQtIcnFl(String modifiedQtIcnFl) {
		this.modifiedQtIcnFl = modifiedQtIcnFl;
	}

	public BigDecimal getItsUnitDetailId() {
		return this.itsUnitDetailId;
	}

	public void setItsUnitDetailId(BigDecimal itsUnitDetailId) {
		this.itsUnitDetailId = itsUnitDetailId;
	}

	public String getCustPartNo() {
		return this.custPartNo;
	}

	public void setCustPartNo(String custPartNo) {
		this.custPartNo = custPartNo;
	}

	public BigDecimal getScnCustomerLineNo() {
		return this.scnCustomerLineNo;
	}

	public void setScnCustomerLineNo(BigDecimal scnCustomerLineNo) {
		this.scnCustomerLineNo = scnCustomerLineNo;
	}

	public String getCustDrawingTx() {
		return this.custDrawingTx;
	}

	public void setCustDrawingTx(String custDrawingTx) {
		this.custDrawingTx = custDrawingTx;
	}

	public String getCustReferenceTx01() {
		return this.custReferenceTx01;
	}

	public void setCustReferenceTx01(String custReferenceTx01) {
		this.custReferenceTx01 = custReferenceTx01;
	}

	public String getPartVerificationTx() {
		return this.partVerificationTx;
	}

	public void setPartVerificationTx(String partVerificationTx) {
		this.partVerificationTx = partVerificationTx;
	}

	public String getPartVerificationRequiredFl() {
		return this.partVerificationRequiredFl;
	}

	public void setPartVerificationRequiredFl(String partVerificationRequiredFl) {
		this.partVerificationRequiredFl = partVerificationRequiredFl;
	}

	public String getAutoTestPartFl() {
		return this.autoTestPartFl;
	}

	public void setAutoTestPartFl(String autoTestPartFl) {
		this.autoTestPartFl = autoTestPartFl;
	}

	public String getSapMaterialNo() {
		return this.sapMaterialNo;
	}

	public void setSapMaterialNo(String sapMaterialNo) {
		this.sapMaterialNo = sapMaterialNo;
	}

	public String getSerialNoRequiredFl() {
		return this.serialNoRequiredFl;
	}

	public void setSerialNoRequiredFl(String serialNoRequiredFl) {
		this.serialNoRequiredFl = serialNoRequiredFl;
	}

	public String getReservationLineItem() {
		return this.reservationLineItem;
	}

	public void setReservationLineItem(String reservationLineItem) {
		this.reservationLineItem = reservationLineItem;
	}

	public String getReservationNo() {
		return this.reservationNo;
	}

	public void setReservationNo(String reservationNo) {
		this.reservationNo = reservationNo;
	}

	public Character getGoodsMvmtFl() {
		return this.goodsMvmtFl;
	}

	public void setGoodsMvmtFl(Character goodsMvmtFl) {
		this.goodsMvmtFl = goodsMvmtFl;
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

	public String getBatchNo() {
		return this.batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public Integer getSourceErpId() {
		return this.sourceErpId;
	}

	public void setSourceErpId(Integer sourceErpId) {
		this.sourceErpId = sourceErpId;
	}

	public String getStorageLocationId() {
		return this.storageLocationId;
	}

	public void setStorageLocationId(String storageLocationId) {
		this.storageLocationId = storageLocationId;
	}

	public String getComponentUom() {
		return this.componentUom;
	}

	public void setComponentUom(String componentUom) {
		this.componentUom = componentUom;
	}

	public String getMovementTypeCd() {
		return this.movementTypeCd;
	}

	public void setMovementTypeCd(String movementTypeCd) {
		this.movementTypeCd = movementTypeCd;
	}

	public Set getItsParts() {
		return this.itsParts;
	}

	public void setItsParts(Set itsParts) {
		this.itsParts = itsParts;
	}

	public Set getDataCaptureDetails() {
		return this.dataCaptureDetails;
	}

	public void setDataCaptureDetails(Set dataCaptureDetails) {
		this.dataCaptureDetails = dataCaptureDetails;
	}

}
