package com.avnet.alapps.model.dbconnect;

// Generated Aug 22, 2016 12:57:05 PM by Hibernate Tools 4.3.1

import java.math.BigDecimal;

/**
 * ProductionOrderHeaderId generated by hbm2java
 */
public class ProductionOrderHeaderId implements java.io.Serializable {

	private BigDecimal productionOrderNo;
	private int sourceErpId;

	public ProductionOrderHeaderId() {
	}

	public ProductionOrderHeaderId(BigDecimal productionOrderNo, int sourceErpId) {
		this.productionOrderNo = productionOrderNo;
		this.sourceErpId = sourceErpId;
	}

	public BigDecimal getProductionOrderNo() {
		return this.productionOrderNo;
	}

	public void setProductionOrderNo(BigDecimal productionOrderNo) {
		this.productionOrderNo = productionOrderNo;
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
		if (!(other instanceof ProductionOrderHeaderId))
			return false;
		ProductionOrderHeaderId castOther = (ProductionOrderHeaderId) other;

		return ((this.getProductionOrderNo() == castOther
				.getProductionOrderNo()) || (this.getProductionOrderNo() != null
				&& castOther.getProductionOrderNo() != null && this
				.getProductionOrderNo()
				.equals(castOther.getProductionOrderNo())))
				&& (this.getSourceErpId() == castOther.getSourceErpId());
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getProductionOrderNo() == null ? 0 : this
						.getProductionOrderNo().hashCode());
		result = 37 * result + this.getSourceErpId();
		return result;
	}

}