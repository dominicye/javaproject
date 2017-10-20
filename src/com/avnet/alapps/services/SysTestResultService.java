package com.avnet.alapps.services;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.naming.InitialContext;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;
import com.avnet.alapps.model.alapps.AstCompTypeAttrHome;
import com.avnet.alapps.model.alapps.AstCompTypeHome;
import com.avnet.alapps.model.alapps.AstDataSourceHome;
import com.avnet.alapps.model.alapps.AstDataTypeHome;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttrHome;
import com.avnet.alapps.model.alapps.AstPartAsmHome;
import com.avnet.alapps.model.alapps.AstPartAttrHome;
import com.avnet.alapps.model.alapps.AstPartHome;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultCode;
import com.avnet.alapps.model.alapps.AstTestResultCodeHome;
import com.avnet.alapps.model.alapps.AstTestResultHome;
import com.avnet.alapps.model.alapps.AstTestResultItem;
import com.avnet.alapps.model.alapps.AstTestResultItemHome;


public class SysTestResultService  {
	private static Logger log = Logger.getLogger(SysTestResultService.class);
	private AstCompTypeHome astCompTypeHome = null; 
	private AstCompTypeAttrHome astCompTypeAttrHome = null; 
	private AstPartHome astPartHome = null;
	private AstPartAttrHome astPartAttrHome = null;
	private AstDataTypeHome astDataTypeHome = null;
	private AstDataSourceHome astDataSourceHome = null;
	private AstPartAsmHome astPartAsmHome = null;
	private AstPartAsmAttrHome astPartAsmAttrHome = null;
	private AstTestResultCodeHome astTestResultCodeHome = null;
	private AstTestResultHome astTestResultHome = null;
	private AstTestResultItemHome astTestResultItemHome = null;
	
	private static String nextTestResultIdSequence = "select ALAPPS_OWN.AST_TEST_RESULT_ID.nextval from dual";
	private static String nextTestResultItemIdSequence = "select ALAPPS_OWN.AST_TEST_RESULT_ITEM_ID.nextval from dual";
	
	
	public SysTestResultService() {
		astCompTypeHome = new AstCompTypeHome();
		astCompTypeAttrHome = new AstCompTypeAttrHome();
		astPartHome = new AstPartHome();
		astPartAttrHome = new AstPartAttrHome();
		astDataTypeHome = new AstDataTypeHome();
		astDataSourceHome = new AstDataSourceHome();
		astPartAsmHome = new AstPartAsmHome();
		astPartAsmAttrHome = new AstPartAsmAttrHome();
		astTestResultCodeHome = new AstTestResultCodeHome();
		astTestResultHome = new AstTestResultHome();
		astTestResultItemHome = new AstTestResultItemHome();
	}
	
	
	
	/*
	 * ********************************************
	 * Sequences
	 * ********************************************
	 */
	private BigDecimal getNextTestResultIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextTestResultIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextTestResultItemIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextTestResultItemIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
 
	/*
	 * ********************************************
	 * Test Result Code
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public AstTestResultCode getTestResultCodeByName(String codeName) throws Exception {
		AstTestResultCode code = new AstTestResultCode();
		code.setCodeNm(codeName);
		List<AstTestResultCode> list = this.astTestResultCodeHome.findByExample(code);
		if ( list != null && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

	
	/*
	 * ********************************************
	 * Test Results
	 * ********************************************
	 */
	
	
	public void saveTestResults(
			AstTestResult testResultHeader,
			Map<String, List<AstTestResultItem>> componentResultItemsBySerialNumber
			) throws Exception {
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
			
			BigDecimal testResultId = this.getNextTestResultIdSequence();
			testResultHeader.setTestResultId(testResultId);
			
			this.astTestResultHome.persist(testResultHeader);
			
			for ( String key : componentResultItemsBySerialNumber.keySet() ) {
				List<AstTestResultItem> itemList = componentResultItemsBySerialNumber.get(key);
				if  ( itemList != null ) {
					for ( AstTestResultItem item : itemList ) {
						if ( item.getAstPartAsm() == null ) {
							throw new Exception(
									"Submitted test result serialnumber (required for component types) or location (required for nodes) does not match valid value. " +
									"Submitted value = " + key +
									". All test results rejected."
								);
						}
						else {
							BigDecimal testResultItemId =  getNextTestResultItemIdSequence();
							item.setTestResultItemId(testResultItemId);
							item.setAstTestResult(testResultHeader);
							this.astTestResultItemHome.persist(item);
						}
					}
				}
			}
			
			/*
			for ( List<AstTestResultItem> itemList : componentResultItemsBySerialNumber.values() ) {
				if  ( itemList != null ) {
					for ( AstTestResultItem item : itemList ) {
						BigDecimal testResultItemId =  getNextTestResultItemIdSequence();
						item.setTestResultItemId(testResultItemId);
						item.setAstTestResult(testResultHeader);
						this.astTestResultItemHome.persist(item);
					}
				}
			}
			*/
	
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<AstTestResult> getTestResultsByPartAsm(AstPartAsm astPartAsm) {
		AstTestResult r = new AstTestResult();
		r.setAstPartAsm(astPartAsm);
		List<AstTestResult> l = this.astTestResultHome.findByExample(r);
		if ( l == null || l.size() == 0 ) {
			return null;
		}
		else {
			return l;
		}
	}
	
	@SuppressWarnings("unchecked")
	public AstTestResult getLatestTestResultByPartAsm(AstPartAsm astPartAsm) {
		
		//TODO: to enable filter of latest per test system, will need to filter on 'testSystemNm'
		
		AstTestResult returnTestResult = null;
		Criteria q = this.getALAPPSSession().createCriteria(AstTestResult.class);
		q.add(Restrictions.eq("astPartAsm.partAsmId", astPartAsm.getPartAsmId()));
		q.addOrder(Order.desc("createDt"));
		List<AstTestResult> list = q.list();
		if ( list != null && list.size() > 0 ) {
			returnTestResult = list.get(0);
		}
		return returnTestResult;
	}
	
	@SuppressWarnings("unchecked")
	public ArrayList<AstTestResult> getAllTestResultByPartAsm(AstPartAsm astPartAsm) {
		Criteria q = this.getALAPPSSession().createCriteria(AstTestResult.class);
		q.add(Restrictions.eq("astPartAsm.partAsmId", astPartAsm.getPartAsmId()));
		q.addOrder(Order.desc("createDt"));
		return (ArrayList)q.list();
	}
	

	public AstTestResult getTestResultByTestResultId(BigDecimal testResultId) {
		Criteria q = this.getALAPPSSession().createCriteria(AstTestResult.class);
		q.add(Restrictions.eq("testResultId", testResultId));
		return (AstTestResult) q.uniqueResult();
	}
	
	/*
	 * ********************************************
	 * Hibernate
	 * ********************************************
	 */

	public AstCompTypeHome getAstCompTypeHome() {
		return astCompTypeHome;
	}

	public void setAstCompTypeHome(AstCompTypeHome astCompTypeHome) {
		this.astCompTypeHome = astCompTypeHome;
	}
	
	public AstCompTypeAttrHome getAstCompTypeAttrHome() {
		return astCompTypeAttrHome;
	}

	public void setAstCompTypeAttrHome(AstCompTypeAttrHome astCompTypeAttrHome) {
		this.astCompTypeAttrHome = astCompTypeAttrHome;
	}
	
	public AstPartHome getAstPartHome() {
		return astPartHome;
	}

	public void setAstPartHome(AstPartHome astPartHome) {
		this.astPartHome = astPartHome;
	}

	public AstPartAttrHome getAstPartAttrHome() {
		return astPartAttrHome;
	}

	public void setAstPartAttrHome(AstPartAttrHome astPartAttrHome) {
		this.astPartAttrHome = astPartAttrHome;
	}
	
	

	public AstDataTypeHome getAstDataTypeHome() {
		return astDataTypeHome;
	}

	public void setAstDataTypeHome(AstDataTypeHome astDataTypeHome) {
		this.astDataTypeHome = astDataTypeHome;
	}

	public AstDataSourceHome getAstDataSourceHome() {
		return astDataSourceHome;
	}

	public void setAstDataSourceHome(AstDataSourceHome astDataSourceHome) {
		this.astDataSourceHome = astDataSourceHome;
	}

	public AstPartAsmHome getAstPartAsmHome() {
		return astPartAsmHome;
	}

	public void setAstPartAsmHome(AstPartAsmHome astPartAsmHome) {
		this.astPartAsmHome = astPartAsmHome;
	}

	public AstPartAsmAttrHome getAstPartAsmAttrHome() {
		return astPartAsmAttrHome;
	}

	public void setAstPartAsmAttrHome(AstPartAsmAttrHome astPartAsmAttrHome) {
		this.astPartAsmAttrHome = astPartAsmAttrHome;
	}
	
	

	public AstTestResultCodeHome getAstTestResultCodeHome() {
		return astTestResultCodeHome;
	}

	public void setAstTestResultCodeHome(AstTestResultCodeHome astTestResultCodeHome) {
		this.astTestResultCodeHome = astTestResultCodeHome;
	}

	public AstTestResultHome getAstTestResultHome() {
		return astTestResultHome;
	}

	public void setAstTestResultHome(AstTestResultHome astTestResultHome) {
		this.astTestResultHome = astTestResultHome;
	}

	public AstTestResultItemHome getAstTestResultItemHome() {
		return astTestResultItemHome;
	}

	public void setAstTestResultItemHome(AstTestResultItemHome astTestResultItemHome) {
		this.astTestResultItemHome = astTestResultItemHome;
	}
	

	protected Session getALAPPSSession() {
		try {
			SessionFactory sf =
				(SessionFactory) new InitialContext().lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_ALAPPS);
			return sf.getCurrentSession();
		} catch (Exception e) {
			log.error("Could not locate ALAPPSSessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate ALAPPSSessionFactory in JNDI");
		}
	}
}
