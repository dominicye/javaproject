package com.avnet.alapps.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.springframework.transaction.annotation.Transactional;

import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.model.gsfcreport.Customer;
import com.avnet.alapps.model.gsfcreport.CustomerHome;
import com.avnet.alapps.model.gsfcreport.FirstArticle;
import com.avnet.alapps.model.gsfcreport.FirstArticleHome;
import com.avnet.alapps.model.gsfcreport.Site;
import com.avnet.alapps.model.gsfcreport.SiteHome;
import com.avnet.alapps.model.gsfcreport.SourceWorkOrder;
import com.avnet.alapps.model.gsfcreport.SourceWorkOrderHome;
import com.avnet.alapps.model.gsfcreport.WorkOrder;
import com.avnet.alapps.model.gsfcreport.WorkOrderHome;
import com.avnet.alapps.model.gsfcreport.WorkOrderStatusHst;
import com.avnet.alapps.model.gsfcreport.WorkOrderStatusHstHome;
import com.avnet.alapps.report.model.ProgramRevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocationCustomerDivision;

public class GSFCFinancialReportService {
	private static Logger log = Logger.getLogger(GSFCFinancialReportService.class);
	private CustomerHome customerHome = null;
	private FirstArticleHome firstArticleHome = null;
	private SiteHome siteHome = null;
	private SourceWorkOrderHome sourceWorkOrderHome = null;
	private WorkOrderHome workOrderHome = null;
	private WorkOrderStatusHstHome workOrderStatusHstHome = null;
	
	@Transactional(readOnly = true)
	public List<ProgramRevenueVolumeLocation> getProgramRevenueVolumeLocationReport(String fromDate, String toDate) {
		
		StringBuilder sql = new StringBuilder()
		.append("SELECT s.site_nm as location, ")
		.append("cus.customer_nm as program_owner,  ")
		.append("fa.CUST_FIRST_ARTICLE_PGM_NM as program, ")
		.append("round(sum(wo.shipped_qt*wo.value_add_unit_cost_am), 2) AS service_revenue,  ")
		.append("round(sum(wo.shipped_qt*swo.resale_unit_price_am), 2) AS sales_revenue,  ")
		.append("sum(wo.shipped_qt) as unit_volume,   ")
		.append("count(wo.work_order_id) as work_order_volume ")
		.append("FROM shop_floor_own.WORK_ORDER wo  ")
		.append("INNER JOIN shop_floor_own.SOURCE_WORK_ORDER swo  ")
		.append("  ON wo.SOURCE_WORK_ORDER_ID = swo.SOURCE_WORK_ORDER_ID ")
		.append("LEFT OUTER JOIN shop_floor_own.WORK_ORDER_STATUS_HST wosh  ")
		.append("  ON wo.work_order_id = wosh.work_order_id  ")
		.append(" AND wosh.work_order_status_id = 2  ")
		.append(" AND wosh.effective_thru_dt IS NULL  ")
		.append("INNER JOIN shop_floor_own.SITE s  ")
		.append("  ON wo.site_id = s.site_id  ")
		.append("LEFT OUTER JOIN shop_floor_own.FIRST_ARTICLE fa  ")
		.append("  ON wo.first_article_serial_no = fa.first_article_serial_no  ")
		.append("LEFT OUTER JOIN shop_floor_own.CUSTOMER cus  ")
		.append("  ON fa.customer_id = cus.customer_id  ")
		.append("WHERE wo.work_order_status_id = 2 ")
		.append("AND trunc(wosh.EFFECTIVE_FROM_DT)  BETWEEN  '").append(fromDate).append("'  AND  '").append(toDate).append("' ")
		.append("GROUP BY s.site_nm, cus.customer_nm, fa.CUST_FIRST_ARTICLE_PGM_NM  ")
		.append("ORDER BY s.site_nm, cus.customer_nm, fa.CUST_FIRST_ARTICLE_PGM_NM ")
		;
		SQLQuery query = this.getReportSession().createSQLQuery(sql.toString());
		
		List<ProgramRevenueVolumeLocation> recordList = new ArrayList<ProgramRevenueVolumeLocation>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
			ProgramRevenueVolumeLocation rec = 
				new ProgramRevenueVolumeLocation(
						(String)data[0], 
						(String)data[1],
						(String)data[2], 
						(BigDecimal)data[3], 
						(BigDecimal)data[4],
						(BigDecimal)data[5], 
						(BigDecimal)data[6]
						);
			recordList.add(rec);
		}
		return recordList;		
	}
	
	
	@Transactional(readOnly = true)
	public List<RevenueVolumeLocation> getRevenueVolumeLocationReport(String fromDate, String toDate) {
		
		StringBuilder sql = new StringBuilder()
		.append("SELECT site.site_nm as location,  ")
		.append("round(sum(wo.shipped_qt*wo.value_add_unit_cost_am), 2) AS service_revenue,  ")
		.append("round(sum(wo.shipped_qt*swo.resale_unit_price_am), 2) AS sales_revenue,  ")
		.append("sum(wo.shipped_qt) as unit_volume,  ") 
		.append("count(wo.work_order_id) as work_order_volume ")
		.append("FROM SHOP_FLOOR_OWN.work_order wo ") 
		.append("INNER JOIN SHOP_FLOOR_OWN.SOURCE_WORK_ORDER swo  ")
		.append("  ON wo.SOURCE_WORK_ORDER_ID = swo.SOURCE_WORK_ORDER_ID ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.WORK_ORDER_STATUS_HST wosh  ")
		.append(" ON wo.work_order_id = wosh.work_order_id  ")
		.append(" AND wosh.work_order_status_id = 2  ")
		.append(" AND wosh.effective_thru_dt IS NULL ") 
		.append("INNER JOIN SHOP_FLOOR_OWN.site site  ")
		.append("  ON wo.site_id = site.site_id  ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.first_article fa  ")
		.append("  ON wo.first_article_serial_no = fa.first_article_serial_no  ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.customer cus  ")
		.append("  ON fa.customer_id = cus.customer_id  ")
		.append("WHERE wo.work_order_status_id = 2 ")
		.append("AND trunc(wosh.EFFECTIVE_FROM_DT)  BETWEEN  '").append(fromDate).append("'  AND  '").append(toDate).append("' ")
		.append("GROUP BY site.site_nm ")
		.append("ORDER BY site.site_nm ")
		;
		SQLQuery query = this.getReportSession().createSQLQuery(sql.toString());
		List<RevenueVolumeLocation> recordList = new ArrayList<RevenueVolumeLocation>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
			RevenueVolumeLocation rec = 
				new RevenueVolumeLocation(
						(String)data[0], 
						(BigDecimal)data[1],
						(BigDecimal)data[2], 
						(BigDecimal)data[3],
						(BigDecimal)data[4]
						);
			recordList.add(rec);
		}
		return recordList;		
	}
	
	@Transactional(readOnly = true)
	public List<RevenueVolumeLocationCustomerDivision> getRevenueVolumeLocationCustomerDivisionReport(String fromDate, String toDate) {
		
		StringBuilder sql = new StringBuilder()
		.append("SELECT site.site_nm as location,   ")
		.append("cus.customer_nm as program_owner,  ") 
		.append("cus.CUSTOMER_NO as CUSTOMER_NUMBER,   ")
		.append("swo.SHIP_TO_CUSTOMER_NO as SHIP_TO,  ")
		.append("DECODE(fa.avnet_logistics_generated_fl, 'N', 'EM', 'Y', 'AL', 'EM') AS division,  ")
		.append("round(sum(wo.shipped_qt*wo.value_add_unit_cost_am), 2) AS service_revenue,   ")
		.append("round(sum(wo.shipped_qt*swo.resale_unit_price_am), 2) AS sales_revenue,   ")
		.append("sum(wo.shipped_qt) as unit_volume,    ")
		.append("count(wo.work_order_id) as work_order_volume  ")
		.append("FROM SHOP_FLOOR_OWN.work_order wo   ")
		.append("INNER JOIN SHOP_FLOOR_OWN.SOURCE_WORK_ORDER swo  ") 
		.append("  ON wo.SOURCE_WORK_ORDER_ID = swo.SOURCE_WORK_ORDER_ID  ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.WORK_ORDER_STATUS_HST wosh   ")
		.append("  ON wo.work_order_id = wosh.work_order_id   ")
		.append("  AND wosh.work_order_status_id = 2  ") 
		.append("  AND wosh.effective_thru_dt IS NULL  ") 
		.append("INNER JOIN SHOP_FLOOR_OWN.site site   ")
		.append("  ON wo.site_id = site.site_id   ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.first_article fa   ")
		.append("  ON wo.first_article_serial_no = fa.first_article_serial_no   ")
		.append("LEFT OUTER JOIN SHOP_FLOOR_OWN.customer cus   ")
		.append("  ON fa.customer_id = cus.customer_id  ") 
		.append("WHERE wo.work_order_status_id = 2  ")
		.append("AND trunc(wosh.EFFECTIVE_FROM_DT)  BETWEEN  '").append(fromDate).append("'  AND  '").append(toDate).append("' ")
		.append("GROUP BY site.site_nm, cus.customer_nm, cus.CUSTOMER_NO, swo.SHIP_TO_CUSTOMER_NO, fa.avnet_logistics_generated_fl  ")
		.append("ORDER BY site.site_nm, cus.customer_nm, cus.CUSTOMER_NO, swo.SHIP_TO_CUSTOMER_NO, fa.avnet_logistics_generated_fl  ")
		;
		SQLQuery query = this.getReportSession().createSQLQuery(sql.toString());
		List<RevenueVolumeLocationCustomerDivision> recordList = new ArrayList<RevenueVolumeLocationCustomerDivision>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
			RevenueVolumeLocationCustomerDivision rec = 
				new RevenueVolumeLocationCustomerDivision(
						(String)data[0], 
						(String)data[1],
						(BigDecimal)data[2], 
						(String)data[3],
						(String)data[4],
						(BigDecimal)data[5],
						(BigDecimal)data[6],
						(BigDecimal)data[7],
						(BigDecimal)data[8]
						);
			recordList.add(rec);
		}
		return recordList;		
	}


	public CustomerHome getCustomerHome() {
		return customerHome;
	}


	public void setCustomerHome(CustomerHome customerHome) {
		this.customerHome = customerHome;
	}


	public FirstArticleHome getFirstArticleHome() {
		return firstArticleHome;
	}


	public void setFirstArticleHome(FirstArticleHome firstArticleHome) {
		this.firstArticleHome = firstArticleHome;
	}


	public SiteHome getSiteHome() {
		return siteHome;
	}


	public void setSiteHome(SiteHome siteHome) {
		this.siteHome = siteHome;
	}


	public SourceWorkOrderHome getSourceWorkOrderHome() {
		return sourceWorkOrderHome;
	}


	public void setSourceWorkOrderHome(SourceWorkOrderHome sourceWorkOrderHome) {
		this.sourceWorkOrderHome = sourceWorkOrderHome;
	}


	public WorkOrderHome getWorkOrderHome() {
		return workOrderHome;
	}


	public void setWorkOrderHome(WorkOrderHome workOrderHome) {
		this.workOrderHome = workOrderHome;
	}


	public WorkOrderStatusHstHome getWorkOrderStatusHstHome() {
		return workOrderStatusHstHome;
	}


	public void setWorkOrderStatusHstHome(
			WorkOrderStatusHstHome workOrderStatusHstHome) {
		this.workOrderStatusHstHome = workOrderStatusHstHome;
	}
	
	protected Session getReportSession() {
		try {
			SessionFactory sf =
				(SessionFactory) new InitialContext().lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_GSFCREPORT);
			return sf.getCurrentSession();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate SessionFactory in JNDI");
		}
	}

}
