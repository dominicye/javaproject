package com.avnet.alapps.model.dbconnect;

// Generated Jan 4, 2016 4:31:43 PM by Hibernate Tools 4.3.1

/**
 * IntegrationId generated by hbm2java
 */
public class IntegrationId implements java.io.Serializable {

	private String itsInvoiceNo;
	private String itsScn;
	private int sourceErpId;

	public IntegrationId() {
	}

	public IntegrationId(String itsInvoiceNo, String itsScn, int sourceErpId) {
		this.itsInvoiceNo = itsInvoiceNo;
		this.itsScn = itsScn;
		this.sourceErpId = sourceErpId;
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

	public int getSourceErpId() {
		return this.sourceErpId;
	}

	public void setSourceErpId(int sourceErpId) {
		this.sourceErpId = sourceErpId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof IntegrationId))
			return false;
		IntegrationId castOther = (IntegrationId) other;

		return ((this.getItsInvoiceNo() == castOther.getItsInvoiceNo()) || (this
				.getItsInvoiceNo() != null
				&& castOther.getItsInvoiceNo() != null && this
				.getItsInvoiceNo().equals(castOther.getItsInvoiceNo())))
				&& ((this.getItsScn() == castOther.getItsScn()) || (this
						.getItsScn() != null && castOther.getItsScn() != null && this
						.getItsScn().equals(castOther.getItsScn())))
				&& (this.getSourceErpId() == castOther.getSourceErpId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getItsInvoiceNo() == null ? 0 : this.getItsInvoiceNo()
						.hashCode());
		result = 37 * result
				+ (getItsScn() == null ? 0 : this.getItsScn().hashCode());
		result = 37 * result + this.getSourceErpId();
		return result;
	}

}
