package com.avnet.alapps.model.dbconnect;

// Generated Jan 4, 2016 4:31:43 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;
import java.util.Date;

/**
 * DataCaptureDetail generated by hbm2java
 */
public class DataCaptureDetail implements java.io.Serializable {

	private BigDecimal dataCaptureDetailId;
	private Integration integration;
	private ItsPart itsPart;
	private ItsUnitDetail itsUnitDetail;
	private DataCaptureRequirement dataCaptureRequirement;
	private String characteristicValueTx;
	private String noteTx;
	private String technicianId;
	private Date applCreateDt;
	private Date applUpdateDt;
	private String applCreateUserId;
	private String applUpdateUserId;

	public DataCaptureDetail() {
	}

	public DataCaptureDetail(BigDecimal dataCaptureDetailId,
			Integration integration,
			DataCaptureRequirement dataCaptureRequirement,
			String characteristicValueTx, String technicianId,
			Date applCreateDt, String applCreateUserId) {
		this.dataCaptureDetailId = dataCaptureDetailId;
		this.integration = integration;
		this.dataCaptureRequirement = dataCaptureRequirement;
		this.characteristicValueTx = characteristicValueTx;
		this.technicianId = technicianId;
		this.applCreateDt = applCreateDt;
		this.applCreateUserId = applCreateUserId;
	}

	public DataCaptureDetail(BigDecimal dataCaptureDetailId,
			Integration integration, ItsPart itsPart,
			ItsUnitDetail itsUnitDetail,
			DataCaptureRequirement dataCaptureRequirement,
			String characteristicValueTx, String noteTx, String technicianId,
			Date applCreateDt, Date applUpdateDt, String applCreateUserId,
			String applUpdateUserId) {
		this.dataCaptureDetailId = dataCaptureDetailId;
		this.integration = integration;
		this.itsPart = itsPart;
		this.itsUnitDetail = itsUnitDetail;
		this.dataCaptureRequirement = dataCaptureRequirement;
		this.characteristicValueTx = characteristicValueTx;
		this.noteTx = noteTx;
		this.technicianId = technicianId;
		this.applCreateDt = applCreateDt;
		this.applUpdateDt = applUpdateDt;
		this.applCreateUserId = applCreateUserId;
		this.applUpdateUserId = applUpdateUserId;
	}

	public BigDecimal getDataCaptureDetailId() {
		return this.dataCaptureDetailId;
	}

	public void setDataCaptureDetailId(BigDecimal dataCaptureDetailId) {
		this.dataCaptureDetailId = dataCaptureDetailId;
	}

	public Integration getIntegration() {
		return this.integration;
	}

	public void setIntegration(Integration integration) {
		this.integration = integration;
	}

	public ItsPart getItsPart() {
		return this.itsPart;
	}

	public void setItsPart(ItsPart itsPart) {
		this.itsPart = itsPart;
	}

	public ItsUnitDetail getItsUnitDetail() {
		return this.itsUnitDetail;
	}

	public void setItsUnitDetail(ItsUnitDetail itsUnitDetail) {
		this.itsUnitDetail = itsUnitDetail;
	}

	public DataCaptureRequirement getDataCaptureRequirement() {
		return this.dataCaptureRequirement;
	}

	public void setDataCaptureRequirement(
			DataCaptureRequirement dataCaptureRequirement) {
		this.dataCaptureRequirement = dataCaptureRequirement;
	}

	public String getCharacteristicValueTx() {
		return this.characteristicValueTx;
	}

	public void setCharacteristicValueTx(String characteristicValueTx) {
		this.characteristicValueTx = characteristicValueTx;
	}

	public String getNoteTx() {
		return this.noteTx;
	}

	public void setNoteTx(String noteTx) {
		this.noteTx = noteTx;
	}

	public String getTechnicianId() {
		return this.technicianId;
	}

	public void setTechnicianId(String technicianId) {
		this.technicianId = technicianId;
	}

	public Date getApplCreateDt() {
		return this.applCreateDt;
	}

	public void setApplCreateDt(Date applCreateDt) {
		this.applCreateDt = applCreateDt;
	}

	public Date getApplUpdateDt() {
		return this.applUpdateDt;
	}

	public void setApplUpdateDt(Date applUpdateDt) {
		this.applUpdateDt = applUpdateDt;
	}

	public String getApplCreateUserId() {
		return this.applCreateUserId;
	}

	public void setApplCreateUserId(String applCreateUserId) {
		this.applCreateUserId = applCreateUserId;
	}

	public String getApplUpdateUserId() {
		return this.applUpdateUserId;
	}

	public void setApplUpdateUserId(String applUpdateUserId) {
		this.applUpdateUserId = applUpdateUserId;
	}

}
