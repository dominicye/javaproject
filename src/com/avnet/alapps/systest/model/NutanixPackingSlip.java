package com.avnet.alapps.systest.model;

import java.util.List;

public class NutanixPackingSlip {
	private String productionOrderNumber = null;
	//private String deliveryNote = null;
	private String salesOrderNumber = null;
	private String nutanixOrderNumber = null;
	private String shippingCarrier = null;
	private String customerOrderNumber = null;
	private String shipToContactName = null;
	private String shipToContactPhone = null;
	private String shipToName = null;
	private String shipToAttention = null;
	private String shipToAddress1 = null;
	private String shipToAddress2 = null;
	private String shipToAddress3 = null;
	private String shipToCity = null;
	private String shipToState = null;
	private String shipToZip = null;
	private String shipToCountry = null;
	private String shipViaName = null;
	private List<NutanixPackingSlipLine> nutanixPackingSlipLines = null;
	private List<NutanixCustomerPackingSlipLine> nutanixCustomerPackingSlipLines = null;
	private List<NutanixPackingSlipWaybill> nutanixPackingSlipWaybills = null;
	public String getNutanixOrderNumber() {
		return nutanixOrderNumber;
	}
	public void setNutanixOrderNumber(String nutanixOrderNumber) {
		this.nutanixOrderNumber = nutanixOrderNumber;
	}
	public String getShippingCarrier() {
		return shippingCarrier;
	}
	public void setShippingCarrier(String shippingCarrier) {
		this.shippingCarrier = shippingCarrier;
	}
	public String getCustomerOrderNumber() {
		return customerOrderNumber;
	}
	public void setCustomerOrderNumber(String customerOrderNumber) {
		this.customerOrderNumber = customerOrderNumber;
	}
	public String getShipToContactName() {
		return shipToContactName;
	}
	public void setShipToContactName(String shipToContactName) {
		this.shipToContactName = shipToContactName;
	}
	public String getShipToContactPhone() {
		return shipToContactPhone;
	}
	public void setShipToContactPhone(String shipToContactPhone) {
		this.shipToContactPhone = shipToContactPhone;
	}
	public String getShipToName() {
		return shipToName;
	}
	public void setShipToName(String shipToName) {
		this.shipToName = shipToName;
	}
	public String getShipToAttention() {
		return shipToAttention;
	}
	public void setShipToAttention(String shipToAttention) {
		this.shipToAttention = shipToAttention;
	}
	public String getShipToAddress1() {
		return shipToAddress1;
	}
	public void setShipToAddress1(String shipToAddress1) {
		this.shipToAddress1 = shipToAddress1;
	}
	public String getShipToAddress2() {
		return shipToAddress2;
	}
	public void setShipToAddress2(String shipToAddress2) {
		this.shipToAddress2 = shipToAddress2;
	}
	public String getShipToAddress3() {
		return shipToAddress3;
	}
	public void setShipToAddress3(String shipToAddress3) {
		this.shipToAddress3 = shipToAddress3;
	}
	public String getShipToCity() {
		return shipToCity;
	}
	public void setShipToCity(String shipToCity) {
		this.shipToCity = shipToCity;
	}
	public String getShipToState() {
		return shipToState;
	}
	public void setShipToState(String shipToState) {
		this.shipToState = shipToState;
	}
	public String getShipToZip() {
		return shipToZip;
	}
	public void setShipToZip(String shipToZip) {
		this.shipToZip = shipToZip;
	}
	public String getShipToCountry() {
		return shipToCountry;
	}
	public void setShipToCountry(String shipToCountry) {
		this.shipToCountry = shipToCountry;
	}
	public List<NutanixPackingSlipLine> getNutanixPackingSlipLines() {
		return nutanixPackingSlipLines;
	}
	public void setNutanixPackingSlipLines(
			List<NutanixPackingSlipLine> nutanixPackingSlipLines) {
		this.nutanixPackingSlipLines = nutanixPackingSlipLines;
	}
	public List<NutanixCustomerPackingSlipLine> getNutanixCustomerPackingSlipLines() {
		return nutanixCustomerPackingSlipLines;
	}
	public void setNutanixCustomerPackingSlipLines(
			List<NutanixCustomerPackingSlipLine> nutanixCustomerPackingSlipLines) {
		this.nutanixCustomerPackingSlipLines = nutanixCustomerPackingSlipLines;
	}
	public String getSalesOrderNumber() {
		return salesOrderNumber;
	}
	public void setSalesOrderNumber(String salesOrderNumber) {
		this.salesOrderNumber = salesOrderNumber;
	}
	/*
	public String getDeliveryNote() {
		return deliveryNote;
	}
	public void setDeliveryNote(String deliveryNote) {
		this.deliveryNote = deliveryNote;
	}
	*/
	
	public String getProductionOrderNumber() {
		return productionOrderNumber;
	}
	public String getShipViaName() {
		return shipViaName;
	}
	public void setShipViaName(String shipViaName) {
		this.shipViaName = shipViaName;
	}
	public void setProductionOrderNumber(String productionOrderNumber) {
		this.productionOrderNumber = productionOrderNumber;
	}
	public List<NutanixPackingSlipWaybill> getNutanixPackingSlipWaybills() {
		return nutanixPackingSlipWaybills;
	}
	public void setNutanixPackingSlipWaybills(
			List<NutanixPackingSlipWaybill> nutanixPackingSlipWaybills) {
		this.nutanixPackingSlipWaybills = nutanixPackingSlipWaybills;
	}
	
	
}
