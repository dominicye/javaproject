package com.avnet.alapps.systest.model;

import java.util.Date;

public class NutanixPackingSlipWaybill {
	private String deliveryNote = null;
	private String carrierName = null;
	private String waybill = null;
	private Date shipDate = null;
	
	public NutanixPackingSlipWaybill(String deliveryNote, String carrierName,
			String waybill, Date shipDate) {
		super();
		this.deliveryNote = deliveryNote;
		this.carrierName = carrierName;
		this.waybill = waybill;
		this.shipDate = shipDate;
	}

	public String getDeliveryNote() {
		return deliveryNote;
	}

	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}

	public String getCarrierName() {
		return carrierName;
	}

	public void setCarrierName(String carrierName) {
		this.carrierName = carrierName;
	}

	public String getWaybill() {
		return waybill;
	}

	public void setWaybill(String waybill) {
		this.waybill = waybill;
	}

	public Date getShipDate() {
		return shipDate;
	}

	public void setShipDate(Date shipDate) {
		this.shipDate = shipDate;
	}
	
	
}
