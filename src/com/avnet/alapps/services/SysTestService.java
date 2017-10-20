package com.avnet.alapps.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.InitialContext;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.common.Util;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;
import com.avnet.alapps.model.alapps.AstCompTypeAttrHome;
import com.avnet.alapps.model.alapps.AstCompTypeHome;
import com.avnet.alapps.model.alapps.AstDataSource;
import com.avnet.alapps.model.alapps.AstDataSourceHome;
import com.avnet.alapps.model.alapps.AstDataType;
import com.avnet.alapps.model.alapps.AstDataTypeHome;
import com.avnet.alapps.model.alapps.AstOrderInfo;
import com.avnet.alapps.model.alapps.AstOrderInfoHome;
import com.avnet.alapps.model.alapps.AstOrderInfoId;
import com.avnet.alapps.model.alapps.AstOrderInfoWaybill;
import com.avnet.alapps.model.alapps.AstOrderInfoWaybillHome;
import com.avnet.alapps.model.alapps.AstOrderInfoWaybillId;
import com.avnet.alapps.model.alapps.AstPart;
import com.avnet.alapps.model.alapps.AstPartAsm;
import com.avnet.alapps.model.alapps.AstPartAsmAttr;
import com.avnet.alapps.model.alapps.AstPartAsmAttrHome;
import com.avnet.alapps.model.alapps.AstPartAsmExcluded;
import com.avnet.alapps.model.alapps.AstPartAsmExcludedHome;
import com.avnet.alapps.model.alapps.AstPartAsmHome;
import com.avnet.alapps.model.alapps.AstPartAttr;
import com.avnet.alapps.model.alapps.AstPartAttrHome;
import com.avnet.alapps.model.alapps.AstPartHome;
import com.avnet.alapps.model.alapps.AstTempBom;
import com.avnet.alapps.model.alapps.AstTestResult;
import com.avnet.alapps.model.alapps.AstTestResultItem;
import com.avnet.alapps.model.alapps.MinAstPartAsm;
import com.avnet.alapps.model.dbconnect.CarrierCode;
import com.avnet.alapps.model.dbconnect.Integration;
import com.avnet.alapps.model.dbconnect.ItsPart;
import com.avnet.alapps.model.dbconnect.ItsPartHome;
import com.avnet.alapps.model.dbconnect.ItsTeam;
import com.avnet.alapps.model.dbconnect.ItsUnitDetail;
import com.avnet.alapps.model.dbconnect.Orderaddress;
import com.avnet.alapps.model.dbconnect.Orderheader;
import com.avnet.alapps.model.dbconnect.Project;
import com.avnet.alapps.model.dbconnect.TopLevelAssembly;
import com.avnet.alapps.model.dbconnect.TopLevelAssemblyHome;
import com.avnet.alapps.model.gsfc.Country;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.systest.model.DisplayBom;
import com.avnet.alapps.systest.model.DisplayBomPart;
import com.avnet.alapps.systest.model.DisplayExcludedPart;
import com.avnet.alapps.systest.model.JtableCompType;
import com.avnet.alapps.systest.model.JtableCompTypeAttr;
import com.avnet.alapps.systest.model.JtablePart;
import com.avnet.alapps.systest.model.JtablePartAttr;
import com.avnet.alapps.systest.model.NutanixPackingSlip;
import com.avnet.alapps.systest.model.NutanixPackingSlipWaybill;
import com.avnet.alapps.systest.model.TestResultReportItem;

public class SysTestService  {
	private static Logger log = Logger.getLogger(SysTestService.class);
	private AstCompTypeHome astCompTypeHome = null; 
	private AstCompTypeAttrHome astCompTypeAttrHome = null; 
	private AstPartHome astPartHome = null;
	private AstPartAttrHome astPartAttrHome = null;
	private AstDataTypeHome astDataTypeHome = null;
	private AstDataSourceHome astDataSourceHome = null;
	private AstPartAsmHome astPartAsmHome = null;
	private AstPartAsmAttrHome astPartAsmAttrHome = null;
	private AstPartAsmExcludedHome astPartAsmExcludedHome = null;
	private ItsPartHome itsPartHome = null;
	private AstOrderInfoHome astOrderInfoHome = null;
	private AstOrderInfoWaybillHome astOrderInfoWaybillHome = null;
	private TopLevelAssemblyHome topLevelAssemblyHome = null;
	//private EvolveSalesOrderDetailService evolveSalesOrderDetailService = null;
	
	private static String getChassisPartAsmIdsBySerialNumberQuery = 
		"select  distinct(apa.PART_ASM_ID), apa.CREATE_DT, apa.TOUCH_LEVEL " +
		"  from ALAPPS_OWN.AST_PART_ASM apa " + 
		"inner join ALAPPS_OWN.AST_PART ap on apa.PART_ID = ap.PART_ID " + 
	    "inner join ALAPPS_OWN.AST_COMP_TYPE act " + 
	    "  on ap.COMP_TYPE_ID = act.COMP_TYPE_ID " + 
		"  and act.TYPE_NM = 'chassis' " + 
		"inner join ALAPPS_OWN.AST_COMP_TYPE_ATTR acta " + 
	    "  on act.COMP_TYPE_ID = acta.COMP_TYPE_ID " + 
		"  and acta.ATTR_NM = 'nutanixserialnumber' " + 
		"inner join  ALAPPS_OWN.AST_PART_ATTR apatr " + 
	    " on acta.COMP_TYPE_ATTR_ID = apatr.COMP_TYPE_ATTR_ID " + 
	    " and apatr.PART_ID = ap.PART_ID " + 
	    "inner join ALAPPS_OWN.AST_PART_ASM_ATTR apat " + 
	    "  on apa.PART_ASM_ID = apat.PART_ASM_ID " + 
	    "  and apat.PART_ATTR_ID = apatr.PART_ATTR_ID " +
		"  and apat.VALUE_TX = ':chassisSerialNumber:' " + 
		"order by apa.CREATE_DT desc ";
	
	private static String getChassisPartAsmIdsByICNQuery = 
		"select  distinct(apa.PART_ASM_ID), apa.CREATE_DT " +
		"  from ALAPPS_OWN.AST_PART_ASM apa " + 
		"inner join ALAPPS_OWN.AST_PART ap on apa.PART_ID = ap.PART_ID " + 
	    "inner join ALAPPS_OWN.AST_COMP_TYPE act " + 
	    "  on ap.COMP_TYPE_ID = act.COMP_TYPE_ID " + 
		"  and act.TYPE_NM = 'chassis' " + 
		"inner join ALAPPS_OWN.AST_COMP_TYPE_ATTR acta " + 
	    "  on act.COMP_TYPE_ID = acta.COMP_TYPE_ID " + 
		"  and acta.ATTR_NM = 'ICN' " + 
		"inner join  ALAPPS_OWN.AST_PART_ATTR apatr " + 
	    " on acta.COMP_TYPE_ATTR_ID = apatr.COMP_TYPE_ATTR_ID " + 
	    " and apatr.PART_ID = ap.PART_ID " + 
	    "inner join ALAPPS_OWN.AST_PART_ASM_ATTR apat " + 
	    "  on apa.PART_ASM_ID = apat.PART_ASM_ID " + 
	    "  and apat.PART_ATTR_ID = apatr.PART_ATTR_ID " +
		"  and apat.VALUE_TX = ':icn:' " + 
		"order by apa.CREATE_DT desc ";
	
	private static String getActivePartKeyAttrValueByPartIdQuery =
		"select pa.VALUE_TX from ALAPPS_OWN.AST_PART_ATTR pa " +
		"inner join ALAPPS_OWN.AST_COMP_TYPE_ATTR cta " +
		"on pa.COMP_TYPE_ATTR_ID = cta.COMP_TYPE_ATTR_ID " +
		"and cta.ACTIVE_FL = 'Y' " +
		"and cta.KEY_FL = 'Y' " +
		"where pa.PART_ID = :partId:";	
	
	private static String getLatestFirstTouchPartAsmIdBySerialNum = 
		"SELECT MAX(PAA.PART_ASM_ID) " +
        "FROM AST_PART_ASM PA, AST_PART_ASM_ATTR PAA " +
        "WHERE PA.PART_ASM_ID = PAA.PART_ASM_ID " +
        "AND PA.TOUCH_LEVEL = 1 " +
        "AND PAA.VALUE_TX = ':serialNum:'";
		//"select part_asm_id from (select * from AST_PART_ASM_ATTR t where t.value_tx=:serialNum: order by part_asm_id desc) where rownum=1";
	
	private static String getPartByActivePartKeyAttrValueQuery = 
		"select p.PART_ID, p.COMP_TYPE_ID, ct.TYPE_DS, p.ACTIVE_FL, pa.VALUE_TX " +
		"from ALAPPS_OWN.AST_PART p " +
		"inner join ALAPPS_OWN.AST_PART_ATTR pa " +
		"  on p.PART_ID = pa.PART_ID " +
		"  and pa.VALUE_TX = ':valueTx:' " +
		"inner join ALAPPS_OWN.AST_COMP_TYPE ct " + 
		"  on p.COMP_TYPE_ID = ct.COMP_TYPE_ID " +
		"inner join ALAPPS_OWN.AST_COMP_TYPE_ATTR cta " +
		"  on pa.COMP_TYPE_ATTR_ID = cta.COMP_TYPE_ATTR_ID " +
		"  and cta.ACTIVE_FL = 'Y' and cta.KEY_FL = 'Y'";
	
	private static String activeKeyAttrCountByCompTypeIdAndCompTypeAttrIdQuery = 
		"select count(*) from ALAPPS_OWN.AST_COMP_TYPE_ATTR x where x.KEY_FL = 'Y' and x.ACTIVE_FL = 'Y' and x.COMP_TYPE_ID = " +
		":compTypeId: and x.COMP_TYPE_ATTR_ID != :compTypeAttrId:";
	
	private static String resetAllKeyFlagsToFalseForComponentTypeQuery = 
		"update ALAPPS_OWN.AST_COMP_TYPE_ATTR x set x.KEY_FL = 'N' where x.COMP_TYPE_ID = :compTypeId:";
	
	
	private static String activeKeyAttrCountByCompTypeIdQuery = 
		"select count(*) from ALAPPS_OWN.AST_COMP_TYPE_ATTR x where x.KEY_FL = 'Y' and x.ACTIVE_FL = 'Y' and x.COMP_TYPE_ID = :compTypeId:";
	
	
	private static String partAttrsByPartIdAndSourceNameQuery = 
		"select pa.PART_ATTR_ID, pa.PART_ID, pa.COMP_TYPE_ATTR_ID, pa.VALUE_TX, cta.KEY_FL, cta.ACTIVE_FL  " +
		"from ALAPPS_OWN.AST_PART_ATTR pa " +
		"inner join ALAPPS_OWN.AST_COMP_TYPE_ATTR cta on pa.COMP_TYPE_ATTR_ID = cta.COMP_TYPE_ATTR_ID " +
		"inner join ALAPPS_OWN.AST_DATA_SOURCE ds " +
		"  on cta.DATA_SOURCE_ID = ds.DATA_SOURCE_ID " +
		"  and ds.SOURCE_NM = ':sourceNm:' " +
		"where pa.PART_ID = :partId: " +
		"order by cta.ATTR_NM ASC"
		;
	
	private static String getAllAstChassisPartNumbersQuery = 
		"select p.KEY_VALUE from AST_PART p " +
		"inner join AST_COMP_TYPE ct on p.COMP_TYPE_ID = ct.COMP_TYPE_ID and ct.TYPE_NM = 'chassis'"
		;
	
	private static String deletePartAttrsByPartId = "delete from ALAPPS_OWN.AST_PART_ATTR pa where pa.PART_ID = :partId:";

	private static String getItsPartsByUnitDetailIdQuery = 
		"select " +
		"  p.ITS_PART_MFGPARTNO, " +
		"  p.ITS_PART_SN, " +
		"  p.ITS_PART_DESCRIPTION, " +
		"  dcr.REQUIREMENT_DS, " +
		"  dcd.CHARACTERISTIC_VALUE_TX " +
		"from ITS_PART p " + 
		"left outer join DATA_CAPTURE_DETAIL dcd " + 
		"  on  p.ITS_PART_ID = dcd.ITS_PART_ID " + 
		"left outer join DATA_CAPTURE_REQUIREMENT dcr " + 
		"  on dcd.DATA_CAPTURE_REQUIREMENT_ID = dcr.DATA_CAPTURE_REQUIREMENT_ID " +
		"where p.ITS_UNIT_DETAIL_ID = :itsUnitDetailId: and p.ITS_BOTTOM = 'Y' and p.COMPONENT_UOM = 'EA' " +
		"order by p.ITS_PART_MFGPARTNO ASC, p.ITS_LINEITEMNO ASC, p.ITS_PART_ID ASC"
		;
	
	private static String getItsFullProductPartDescriptionQuery = 
		"select FP.PARTDESC from FASTADM.FULL_PRODUCT FP where FP.SAP_MATERIAL_ID = LPAD(':sapMaterialNumber:', 18, '0') AND FP.PART_SOURCE_ID = 5 AND FP.SAP_PLANT_CD = 1311"
		;
	
	//private static String getEvolveItsPartsByUnitDetailIdQuery = 
	//	"select " + 
	//    "  tla.CUSTOMER_PART_NO as ITS_PART_MFGPARTNO, " +
	//	"  p.ITS_PART_SN, " +
	//	"  p.ITS_PART_DESCRIPTION, " + 
	//	"  dcr.REQUIREMENT_DS, " + 
	//	"  dcd.CHARACTERISTIC_VALUE_TX " +
	//	"  from ITS_PART p  " +
	//	"	left outer join DATA_CAPTURE_DETAIL dcd " + 
	//	"	  on  p.ITS_PART_ID = dcd.ITS_PART_ID  " + 
	//	"	left outer join DATA_CAPTURE_REQUIREMENT dcr  " +
	//	"	  on dcd.DATA_CAPTURE_REQUIREMENT_ID = dcr.DATA_CAPTURE_REQUIREMENT_ID  " +
	//	"inner join TOP_LEVEL_ASSEMBLY tla on p.ITS_PART_MFGPARTNO = tla.TOP_LEVEL_ASSEMBLY_ID and tla.CUSTOMER_PART_NO is not null " +
	//	"where p.ITS_UNIT_DETAIL_ID = :itsUnitDetailId: and p.ITS_BOTTOM = 'Y' and p.COMPONENT_UOM = 'EA' " + 
	//	"order by tla.CUSTOMER_PART_NO ASC, p.ITS_LINEITEMNO ASC, p.ITS_PART_ID ASC"
	//	;
	
	
	//private static String getFirstMfgPartNumberQuery = 
	//	"select p.ITS_PART_MFGPARTNO as VAL from ITS_PART p where p.ITS_UNIT_DETAIL_ID = :itsUnitDetailId: and p.ITS_BOTTOM = 'Y' and rownum = 1 order by p.ITS_PART_MFGPARTNO ASC"
	//	;

	
	private static String getAllImagingFSTTestResultsByDateQuery = 
		"SELECT  " +
		"TR.CREATE_DT AS CREATE_DATE, " +
		"TR.TEST_RESULT_ID AS TEST_SESSION_ID, " +
		"TR.TEST_SYSTEM_NM, " +
		"TRC.CODE_NM AS TEST_RESULT_CODE, " +
		"PAAT.VALUE_TX AS CHASSIS_SERIAL_NUMBER, " +
		"PA.TOUCH_LEVEL, " +
		"TRI.TEST_NM AS TEST_NAME, " +
		"TRI.TEST_DS AS TEST_DESC, " +
		"TRIC.CODE_NM AS TEST_RESULT_ITEM_CODE, " +
		"TRI.RESULT_CODE_NM AS TEST_RESULT_ITEM_CODE_NAME, " +
		"TRI.RESULT_CODE_DS AS TEST_RESULT_ITEM_CODE_DESC, " +
		"A.VALUE_TX AS COMPONENT_SERIAL_NUMBER, " +
		"TAAT.VALUE_TX AS NUTANIX_TOP_LEVEL_PART_NUMBER " +
		"FROM ALAPPS_OWN.AST_TEST_RESULT TR " +
		"INNER JOIN " +
		"ALAPPS_OWN.AST_TEST_RESULT_CODE TRC " +
		"ON TR.TEST_RESULT_CODE_ID = TRC.TEST_RESULT_CODE_ID " +
		"LEFT OUTER JOIN " +
		"ALAPPS_OWN.AST_TEST_RESULT_ITEM TRI " +
		"ON TR.TEST_RESULT_ID = TRI.TEST_RESULT_ID  " +
		"LEFT OUTER JOIN " +
		"ALAPPS_OWN.AST_TEST_RESULT_CODE TRIC " +
		"ON TRI.TEST_RESULT_CODE_ID = TRIC.TEST_RESULT_CODE_ID " +
		"INNER JOIN " +
		"ALAPPS_OWN.AST_PART_ASM PA " +
		"ON TR.PART_ASM_ID = PA.PART_ASM_ID  " +
		"INNER JOIN " +
		"ALAPPS_OWN.AST_PART_ASM_ATTR PAAT " +
		"ON PA.PART_ASM_ID = PAAT.PART_ASM_ID " +  
		"INNER JOIN " +
		"ALAPPS_OWN.AST_PART_ATTR PAT " +
		"ON PAAT.PART_ATTR_ID = PAT.PART_ATTR_ID " +
		"INNER JOIN  " +
		"ALAPPS_OWN.AST_COMP_TYPE_ATTR CTAT " +
		"ON  PAT.COMP_TYPE_ATTR_ID = CTAT.COMP_TYPE_ATTR_ID " +
		"AND UPPER(CTAT.ATTR_NM) = UPPER('nutanixserialnumber') " +
		"LEFT OUTER JOIN " +
		"(SELECT PAAT.PART_ASM_ID, PAAT.VALUE_TX " +
		"FROM ALAPPS_OWN.AST_PART_ASM_ATTR PAAT " +
		"INNER JOIN  " +
		"ALAPPS_OWN.AST_PART_ATTR PAT " +
		"ON PAAT.PART_ATTR_ID = PAT.PART_ATTR_ID " +
		"INNER JOIN " +
		"ALAPPS_OWN.AST_COMP_TYPE_ATTR CTAT " +
		"ON  PAT.COMP_TYPE_ATTR_ID = CTAT.COMP_TYPE_ATTR_ID " +
		"AND UPPER(CTAT.ATTR_NM) = UPPER('serialnumber')) A " +
		"ON TRI.PART_ASM_ID = A.PART_ASM_ID " +
		
	    "INNER JOIN ALAPPS_OWN.AST_PART_ASM_ATTR TAAT " +
	   " ON PA.PART_ASM_ID = TAAT.PART_ASM_ID " +
	   " INNER JOIN ALAPPS_OWN.AST_PART_ATTR TAT " +
	   " ON TAAT.PART_ATTR_ID = TAT.PART_ATTR_ID " +
	   " INNER JOIN ALAPPS_OWN.AST_COMP_TYPE_ATTR TTAT " +
	   " ON TAT.COMP_TYPE_ATTR_ID = TTAT.COMP_TYPE_ATTR_ID " +
	   " AND UPPER(TTAT.ATTR_NM) = UPPER('nutanixtoplevelpartnumber') " +

		"WHERE TRUNC(TR.CREATE_DT) = TRUNC(TO_DATE(':testDate:', 'DD-MON-YYYY')) " +
		"AND UPPER(TR.TEST_SYSTEM_NM) IN ('IMAGING', 'FST') " + 
		"ORDER BY TR.CREATE_DT DESC"
		;
	
	private static String getDistinctICNListQuery = 
		"select distinct xpat.VALUE_TX as production_order " +
		"from AST_COMP_TYPE_ATTR xcta  " +
		"inner join AST_PART_ATTR pat on xcta.COMP_TYPE_ATTR_ID = pat.COMP_TYPE_ATTR_ID " +
		"inner join AST_PART_ASM_ATTR xpat on pat.PART_ATTR_ID = xpat.PART_ATTR_ID and xpat.VALUE_TX is not null " +
		"where xcta.ATTR_NM = 'ICN' and xpat.VALUE_TX like '2%' " +
		"order by xpat.VALUE_TX asc"
		;
	
	/*
	private static String getItsCustomerSalesOrderInfoQuery =
		"select i.ITS_CUSTPONO, p.SCN_CUSTOMER_LINE_NO " +
		"from ITS_PART p " +
		"inner join INTEGRATION i on UPPER(i.ITS_INVOICE_NO) = UPPER(p.ITS_INVOICE_NO) " +
		"where  UPPER(p.ITS_PART_SN) = UPPER(':chassisSerialNumber:')  and UPPER(p.ITS_INVOICE_NO) = UPPER(':icn:')"
		;
	*/
	
	private static String getSAPOrdersWithMissingWaybillQuery = 
		"SELECT OI.SCN, OI.ICN " +
		"FROM AST_ORDER_INFO OI  " +
		"WHERE OI.ICN like '2%' and OI.SCN not like '2%' " +
		"AND OI.UPDATE_COUNT IS NULL OR OI.UPDATE_COUNT < 14 " +
		"AND (select count(*) from AST_ORDER_INFO_WAYBILL OIW where OIW.SCN = OI.SCN ) = 0 " +
		"GROUP BY OI.SCN, OI.ICN, OI.UPDATE_COUNT " +
		"ORDER by OI.SCN asc, OI.ICN asc, OI.UPDATE_COUNT asc"
		;
	
	private static String getAllSAPAssemblyIdsQuery = 
		"SELECT DISTINCT XPAT.PART_ASM_ID " +
		"FROM AST_COMP_TYPE_ATTR xcta " + 
		"INNER JOIN AST_PART_ATTR PAT ON XCTA.COMP_TYPE_ATTR_ID = PAT.COMP_TYPE_ATTR_ID " +
		"INNER JOIN AST_PART_ASM_ATTR XPAT ON PAT.PART_ATTR_ID = XPAT.PART_ATTR_ID AND XPAT.VALUE_TX IS NOT NULL " +
		"WHERE xcta.ATTR_NM = 'ICN' AND xpat.VALUE_TX LIKE '2%' " +
		"ORDER BY XPAT.PART_ASM_ID ASC"
		;
	
	
	private static String insertCrudLog = 
		"insert into ALAPPS_OWN.AST_CRUD_LOG " + 
		"(CRUD_LOG_ID, ACTION_USER_ID, ACTION_DT, ACTION_CD, OBJECT_ID, OBJECT_NM, FIELD_NM, OLD_VAL, NEW_VAL) values " +
		"(ALAPPS_OWN.AST_CRUD_LOG_ID.nextval, ':userId:', sysdate, ':actionCode:', :objectId:, ':objectName:', ':fieldName:', ':oldValue:', ':newValue:')";
	
	private static String nextCompTypeIdSequence = "select ALAPPS_OWN.AST_COMP_TYPE_ID.nextval from dual";
	private static String nextCompTypeAttrIdSequence = "select ALAPPS_OWN.AST_COMP_TYPE_ATTR_ID.nextval from dual";
	private static String nextPartIdSequence = "select ALAPPS_OWN.AST_PART_ID.nextval from dual";
	private static String nextPartAttrIdSequence = "select ALAPPS_OWN.AST_PART_ATTR_ID.nextval from dual";
	private static String nextPartAsmIdSequence = "select ALAPPS_OWN.AST_PART_ASM_ID.nextval from dual";
	private static String nextPartAsmAttrIdSequence = "select ALAPPS_OWN.AST_PART_ASM_ATTR_ID.nextval from dual";
	private static String nextPartAsmExcludedIdSequence = "select ALAPPS_OWN.AST_PART_ASM_EXCLUDED_ID.nextval from dual";
	//
	
	public SysTestService() {
		astCompTypeHome = new AstCompTypeHome();
		astCompTypeAttrHome = new AstCompTypeAttrHome();
		astPartHome = new AstPartHome();
		astPartAttrHome = new AstPartAttrHome();
		astDataTypeHome = new AstDataTypeHome();
		astDataSourceHome = new AstDataSourceHome();
		astPartAsmHome = new AstPartAsmHome();
		astPartAsmAttrHome = new AstPartAsmAttrHome();
		astPartAsmExcludedHome = new AstPartAsmExcludedHome();
		itsPartHome = new ItsPartHome();
		astOrderInfoHome = new AstOrderInfoHome();
		astOrderInfoWaybillHome = new AstOrderInfoWaybillHome();
		topLevelAssemblyHome = new TopLevelAssemblyHome();
		//evolveSalesOrderDetailService = new EvolveSalesOrderDetailService();
	}
	
	
	/*
	 * ********************************************
	 * CRUD Logger
	 * ********************************************
	 */
	@Transactional(readOnly = false)
	public void crudLogger(Session sess,
						SecurityUser user, 
						String actionCode, 
						BigDecimal objectId,
						String objectName, 
						String fieldName,
						String oldValue, 
						String newValue) {
		
		if ( oldValue != null &&  oldValue.equals(newValue) ) {
			return;
		}
		else if ( newValue != null &&  newValue.equals(oldValue) ) {
			return;
		}
		else if ( oldValue == null && newValue == null ) {
			return;
		}
		
		try {
			SQLQuery q = sess.createSQLQuery(insertCrudLog
					.replaceFirst(":userId:", user.getAvnetGlobalUserId())
					.replaceFirst(":actionCode:", actionCode)
					.replaceFirst(":objectId:", objectId.toPlainString())
					.replaceFirst(":objectName:", objectName)
					.replaceFirst(":fieldName:", fieldName)
					.replaceFirst(":oldValue:", (oldValue != null) ? oldValue : "")
					.replaceFirst(":newValue:", (newValue != null) ? newValue : "")
					);
			q.executeUpdate();
		}
		catch ( Exception e ) {
			log.error("Could not log crud event. ", e);
		}
	}
	

	/*
	 * ********************************************
	 * Sequences
	 * ********************************************
	 */
	private BigDecimal getNextCompTypeIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextCompTypeIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextCompTypeAttrIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextCompTypeAttrIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextPartIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextPartIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextPartAttrIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextPartAttrIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextPartAsmIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextPartAsmIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextPartAsmAttrIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextPartAsmAttrIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	
	private BigDecimal getNextPartAsmExcludedIdSequence() {
		SQLQuery query = 
			this.getALAPPSSession().createSQLQuery(nextPartAsmExcludedIdSequence);
		return (BigDecimal)query.uniqueResult();
	}
	

	/*
	 * ********************************************
	 * Component Type
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public AstCompType getComponentTypeByName(String componentTypeName) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompType.class)
			.add(Restrictions.eq("typeNm", componentTypeName));
		return (AstCompType) q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstCompType> getComponentTypes(boolean activeOnly, String orderBy, String orderDirection) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompType.class);
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		return q.list();
		/*
		AstCompType ct = new AstCompType();
		if (activeOnly) ct.setActiveFl("Y");
		return (List<AstCompType>)astCompTypeHome.findByExample(ct);
		*/
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JtableCompType> getComponentTypesPaginated(boolean activeOnly, String orderBy, String orderDirection, int pageIndex, int pageSize) {
		
		if ( "parentCompTypeId".equals(orderBy) ) {
			orderBy = "astCompType.compTypeId";
		}

		Criteria q = this.getALAPPSSession().createCriteria(AstCompType.class);
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		
		q.setFirstResult(pageIndex);
		q.setMaxResults(pageSize);
		
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		
		List<JtableCompType> list = new ArrayList<JtableCompType>();
		for (AstCompType ct : (List<AstCompType>)q.list() ) {
			//AstCompType pct = this.getAstCompTypeHome().findById(ct.getParentCompTypeId());
			list.add(new JtableCompType(ct));
		}
		
		return list;
	}
	
	@Transactional(readOnly = true)
	public Long getComponentTypesCount(boolean activeOnly) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompType.class);
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setProjection(Projections.rowCount());
		return (Long)q.uniqueResult();
	}
	
	
	@Transactional(readOnly = false)
	public JtableCompType addComponentType(JtableCompType ct, SecurityUser user) throws Exception {
	
		ct.setCompTypeId(this.getNextCompTypeIdSequence());
		
		AstCompType newCt = new AstCompType();
		newCt.setActiveFl(ct.getActiveFl());
		newCt.setCompTypeId(ct.getCompTypeId());
		
		AstCompType pct = this.getAstCompTypeHome().findById(ct.getParentCompTypeId());
		newCt.setAstCompType(pct);
		
		newCt.setTypeDs(ct.getTypeDs());
		newCt.setTypeNm(ct.getTypeNm());
		newCt.setXmlTag(ct.getXmlTag());
		
		newCt.setCreateDt(new Date());
		newCt.setCreateUsrId(user.getAvnetGlobalUserId());

		astCompTypeHome.persist(newCt);
		
		/*
		Session sess = this.getALAPPSSession();
		this.crudLogger(sess,user,"ADD","AstCompType","ParentCompTypeId",null,newCt.getAstCompType().getCompTypeId().toPlainString());
		this.crudLogger(sess,user,"ADD","AstCompType","TypeDs",null,newCt.getTypeDs());
		this.crudLogger(sess,user,"ADD","AstCompType","TypeNm",null,newCt.getTypeNm());
		this.crudLogger(sess,user,"ADD","AstCompType","XmlTag",null,newCt.getXmlTag());
		*/
		
		return ct;
	}
	
	@Transactional(readOnly = false)
	public JtableCompType updateComponentType(JtableCompType ct, SecurityUser user) throws Exception {
		
		AstCompType oldCt = this.getAstCompTypeHome().findById(ct.getCompTypeId());
		AstCompType newCt = new AstCompType();
		newCt.setActiveFl(ct.getActiveFl());
		newCt.setCompTypeId(ct.getCompTypeId());

		AstCompType pct = this.getAstCompTypeHome().findById(ct.getParentCompTypeId());
		newCt.setAstCompType(pct);

		newCt.setTypeDs(ct.getTypeDs());
		newCt.setTypeNm(oldCt.getTypeNm()); //dont allow update of type name
		newCt.setXmlTag(ct.getXmlTag());
		
		newCt.setCreateDt(oldCt.getCreateDt());
		newCt.setCreateUsrId(oldCt.getCreateUsrId());
		newCt.setUpdateDt(new Date());
		newCt.setUpdateUsrId(user.getAvnetGlobalUserId());
		
		astCompTypeHome.merge(newCt);
		
		/*
		String oldParentCompTypeId = ( oldCt.getAstCompType() != null) ? oldCt.getAstCompType().getCompTypeId().toPlainString() : null;
		String oldTypeDs = oldCt.getTypeDs();
		String oldTypeNm = oldCt.getTypeNm();
		String oldXmlTag = oldCt.getXmlTag();
		Session sess = this.getALAPPSSession();
		this.crudLogger(sess,user,"CHG","AstCompType","ParentCompTypeId", oldParentCompTypeId,( pct != null) ? pct.getCompTypeId().toPlainString() : null);
		this.crudLogger(sess,user,"CHG","AstCompType","TypeDs",oldTypeDs,newCt.getTypeDs());
		this.crudLogger(sess,user,"CHG","AstCompType","TypeNm",oldTypeNm,newCt.getTypeNm());
		this.crudLogger(sess,user,"CHG","AstCompType","XmlTag",oldXmlTag,newCt.getXmlTag());
		*/
		
		return ct;
	}
	
	@Transactional(readOnly = false)
	public JtableCompType deleteComponentType(JtableCompType ct, SecurityUser user) throws Exception{
		AstCompType newCt = astCompTypeHome.findById(ct.getCompTypeId());
		
		/*
		Session sess = this.getALAPPSSession();
		this.crudLogger(sess,user,"DEL","AstCompType","ParentCompTypeId",( newCt.getAstCompType() != null) ? newCt.getAstCompType().getCompTypeId().toPlainString() : null,null);
		this.crudLogger(sess,user,"DEL","AstCompType","TypeDs",newCt.getTypeDs(),null);
		this.crudLogger(sess,user,"DEL","AstCompType","TypeNm",newCt.getTypeNm(),null);
		this.crudLogger(sess,user,"DEL","AstCompType","XmlTag",newCt.getXmlTag(),null);
		*/
		
		astCompTypeHome.delete(newCt);
		ct.setActiveFl(newCt.getActiveFl());
		ct.setCompTypeId(newCt.getCompTypeId());
		
		//AstCompType pct = this.getAstCompTypeHome().findById(ct.getParentCompTypeId());
		//if ( pct != null ) {
		//	ct.setParentCompTypeId(pct.getAstCompType().getCompTypeId());
		//	ct.setParentCompType(new JtableCompType(pct));
		//}
		//newCt.setParentCompTypeId(ct.getParentCompTypeId());
		
		ct.setTypeDs(newCt.getTypeDs());
		ct.setTypeNm(newCt.getTypeNm());
		ct.setXmlTag(newCt.getXmlTag());

		return ct;
	}
	
	
	/*
	 * ********************************************
	 * Component Type Attribute
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstCompTypeAttr> getComponentTypeAttributes(BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompTypeAttr.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstCompTypeAttr> getComponentTypeAttributesBySourceName(String sourceName, BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection) {
		AstDataSource ds = this.getDataSourceByName(sourceName);
		Criteria q = (this.getALAPPSSession()).createCriteria(AstCompTypeAttr.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		q.add(Restrictions.eq("astDataSource.dataSourceId", ds.getDataSourceId()));
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JtableCompTypeAttr> getComponentTypeAttributesPaginated(BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection, int pageIndex, int pageSize) {
		
		if ( "compTypeId".equals(orderBy) ) {
			orderBy = "astCompType.compTypeId";
		}
		else if ( "dataTypeId".equals(orderBy) ) {
			orderBy = "astDataType.dataTypeId";
		}
		else if ( "dataSourceId".equals(orderBy) ) {
			orderBy = "astDataSource.dataSourceId";
		}
		
		Criteria q = this.getALAPPSSession().createCriteria(AstCompTypeAttr.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setFirstResult(pageIndex);
		q.setMaxResults(pageSize);
		
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		
		List<JtableCompTypeAttr> list = new ArrayList<JtableCompTypeAttr>();
		for ( AstCompTypeAttr attr : (List<AstCompTypeAttr>)q.list() ) {
			list.add(new JtableCompTypeAttr(attr));
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public Long getComponentTypeAttributesCount(BigDecimal compTypeId, boolean activeOnly) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompTypeAttr.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setProjection(Projections.rowCount());
		return (Long)q.uniqueResult();
	}
	
	@Transactional(readOnly = false)
	public JtableCompTypeAttr addComponentTypeAttribute(JtableCompTypeAttr cta, SecurityUser user) throws Exception {
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
	
			if ( "Y".equalsIgnoreCase(cta.getKeyFl()) && "Y".equalsIgnoreCase(cta.getActiveFl())  ) {
				
				if ( this.checkExistingActiveKeyAttr(cta.getCompTypeId(), sess)) {
					throw new Exception("You can have only one key attribute active for this component type. ");
				}
				
				//reset key flags for comp type 
				this.resetKeyFlagsForComponentType(cta.getCompTypeId(), sess);
			}
			
			if ( cta.getKeyFl() == null ) {
				cta.setKeyFl("N");
			}
			
			if ( cta.getActiveFl() == null ) {
				cta.setActiveFl("N");
			}
			
			if ( cta.getEditableFl() == null ) {
				cta.setEditableFl("N");
			}
	
			cta.setCompTypeAttrId(this.getNextCompTypeAttrIdSequence());
			
			AstCompType ct = astCompTypeHome.findById(cta.getCompTypeId());
			AstDataType dt = astDataTypeHome.findById(cta.getDataTypeId());
			AstDataSource ds = astDataSourceHome.findById(cta.getDataSourceId());
			
			AstCompTypeAttr attr = new AstCompTypeAttr();
			attr.setActiveFl(cta.getActiveFl());
			attr.setAstCompType(ct);
			attr.setAstDataSource(ds);
			attr.setAstDataType(dt);
			attr.setAttrDs(cta.getAttrDs());
			attr.setAttrNm(cta.getAttrNm());
			attr.setCompTypeAttrId(cta.getCompTypeAttrId());
			attr.setKeyFl(cta.getKeyFl());
			attr.setXmlTag(cta.getXmlTag());
			attr.setEditableFl(cta.getEditableFl());
			astCompTypeAttrHome.persist(attr);
			
			/*
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","CompTypeId",null,attr.getAstCompType().getCompTypeId().toPlainString());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","CompTypeAttrId",null,attr.getCompTypeAttrId().toPlainString());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","DataSourceId",null,attr.getAstDataSource().getDataSourceId().toPlainString());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","DataTypeId",null,attr.getAstDataType().getDataTypeId().toPlainString());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","AttrDs",null,attr.getAttrDs());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","AttrNm",null,attr.getAttrNm());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","KeyFl",null,attr.getKeyFl());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","ActiveFl",null,attr.getActiveFl());
			this.crudLogger(sess,user,"ADD","AstCompTypeAttr","XmlTag",null,attr.getXmlTag());
			*/
			
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return cta;
	}
	
	@Transactional(readOnly = false)
	public JtableCompTypeAttr updateComponentTypeAttribute(JtableCompTypeAttr cta, SecurityUser user) throws Exception {
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
			if ( "Y".equalsIgnoreCase(cta.getKeyFl()) && "Y".equalsIgnoreCase(cta.getActiveFl())  ) {
				
				if ( this.checkExistingActiveKeyAttrNotCurrent(cta.getCompTypeAttrId(), cta.getCompTypeId(), sess)) {
					throw new Exception("You can have only one key attribute active for this component type. ");
				}
				
				//reset key flags for comp type 
				this.resetKeyFlagsForComponentType(cta.getCompTypeId(), sess);
			}
			
			if ( cta.getKeyFl() == null ) {
				cta.setKeyFl("N");
			}
			
			if ( cta.getActiveFl() == null ) {
				cta.setActiveFl("N");
			}
			
			if ( cta.getEditableFl() == null ) {
				cta.setEditableFl("N");
			}
			
			AstCompType ct = astCompTypeHome.findById(cta.getCompTypeId());
			AstDataType dt = astDataTypeHome.findById(cta.getDataTypeId());
			AstDataSource ds = astDataSourceHome.findById(cta.getDataSourceId());
			AstCompTypeAttr oldAttr = astCompTypeAttrHome.findById(cta.getCompTypeAttrId());
			
			AstCompTypeAttr attr = new AstCompTypeAttr();
			attr.setActiveFl(cta.getActiveFl());
			attr.setAstCompType(ct);
			attr.setAstDataSource(ds);
			attr.setAstDataType(dt);
			attr.setAttrDs(cta.getAttrDs());
			attr.setAttrNm(oldAttr.getAttrNm()); //Dont allow update of name
			attr.setCompTypeAttrId(cta.getCompTypeAttrId());
			attr.setKeyFl(cta.getKeyFl());
			attr.setXmlTag(cta.getXmlTag());
			attr.setEditableFl(cta.getEditableFl());
			
			attr.setCreateDt(oldAttr.getCreateDt());
			attr.setCreateUsrId(oldAttr.getCreateUsrId());
			attr.setUpdateDt(new Date());
			attr.setUpdateUsrId(user.getAvnetGlobalUserId());

			astCompTypeAttrHome.merge(attr);
			
			/*
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","CompTypeId",null,attr.getAstCompType().getCompTypeId().toPlainString());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","CompTypeAttrId",null,attr.getCompTypeAttrId().toPlainString());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","DataSourceId",null,attr.getAstDataSource().getDataSourceId().toPlainString());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","DataTypeId",null,attr.getAstDataType().getDataTypeId().toPlainString());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","AttrDs",null,attr.getAttrDs());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","AttrNm",null,attr.getAttrNm());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","KeyFl",null,attr.getKeyFl());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","ActiveFl",null,attr.getActiveFl());
			this.crudLogger(sess,user,"CHG","AstCompTypeAttr","XmlTag",null,attr.getXmlTag());
			*/
	
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return cta;
	}
	
	@Transactional(readOnly = false)
	public JtableCompTypeAttr deleteComponentTypeAttribute(JtableCompTypeAttr cta, SecurityUser user) throws Exception {
		AstCompTypeAttr attr = astCompTypeAttrHome.findById(cta.getCompTypeAttrId());
		astCompTypeAttrHome.delete(attr);
		
		cta.setActiveFl(attr.getActiveFl());
		cta.setCompTypeId(attr.getAstCompType().getCompTypeId());
		cta.setDataSourceId(attr.getAstDataSource().getDataSourceId());
		cta.setDataTypeId(attr.getAstDataType().getDataTypeId());
		cta.setAttrDs(attr.getAttrDs());
		cta.setAttrNm(attr.getAttrNm());
		cta.setCompTypeAttrId(attr.getCompTypeAttrId());
		cta.setKeyFl(attr.getKeyFl());
		cta.setXmlTag(attr.getXmlTag());
		cta.setEditableFl(attr.getEditableFl());
		/*
		Session sess = this.getALAPPSSession();
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","CompTypeId",attr.getAstCompType().getCompTypeId().toPlainString(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","CompTypeAttrId",attr.getCompTypeAttrId().toPlainString(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","DataSourceId",attr.getAstDataSource().getDataSourceId().toPlainString(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","DataTypeId",attr.getAstDataType().getDataTypeId().toPlainString(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","AttrDs",attr.getAttrDs(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","AttrNm",attr.getAttrNm(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","KeyFl",attr.getKeyFl(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","ActiveFl",attr.getActiveFl(),null);
		this.crudLogger(sess,user,"DEL","AstCompTypeAttr","XmlTag",attr.getXmlTag(),null);
		*/
	
		return cta;
	}
	
	public AstCompTypeAttr getComponentTypeAttribute(BigDecimal id) {
		return astCompTypeAttrHome.findById(id);
	}
	
	
	public int resetKeyFlagsForComponentType(BigDecimal componentTypeId, Session sess) {
		SQLQuery update = sess.createSQLQuery(
				resetAllKeyFlagsToFalseForComponentTypeQuery.replaceFirst(":compTypeId:", componentTypeId.toPlainString())
				);
		return update.executeUpdate();
	}
	
	public boolean checkExistingActiveKeyAttrNotCurrent(BigDecimal compTypeAttrId, BigDecimal compTypeId, Session sess) {
		SQLQuery query = sess.createSQLQuery(
				activeKeyAttrCountByCompTypeIdAndCompTypeAttrIdQuery
					.replaceFirst(":compTypeId:", compTypeId.toPlainString())
					.replaceFirst(":compTypeAttrId:", compTypeAttrId.toPlainString())
				);
		BigDecimal count = (BigDecimal)query.uniqueResult();
		return (count.longValue() > 0);
	}
	
	public boolean checkExistingActiveKeyAttr(BigDecimal compTypeId, Session sess) {
		SQLQuery query = sess.createSQLQuery(activeKeyAttrCountByCompTypeIdQuery.replaceFirst(":compTypeId:", compTypeId.toPlainString()));
		BigDecimal count = (BigDecimal)query.uniqueResult();
		return (count.longValue() > 0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JtableCompTypeAttr> getComponentTypeAttributesByTypeName(String compTypeNm, boolean activeOnly) {
		Criteria q = this.getALAPPSSession().createCriteria(AstCompTypeAttr.class);
		//q.add(Restrictions.eq("astCompType.typeNm", compTypeNm)); //TODO: why is this not working??? need to cludge for now
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		List<JtableCompTypeAttr> list = new ArrayList<JtableCompTypeAttr>();
		for ( AstCompTypeAttr attr : (List<AstCompTypeAttr>)q.list() ) {
			if ( compTypeNm.equalsIgnoreCase(attr.getAstCompType().getTypeNm()) ) { //  <---cludge :-(
				list.add(new JtableCompTypeAttr(attr));
			}
		}
		return list;
	}
	
	/*
	 * ********************************************
	 * Data Type
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstDataType> getDataTypes() {
		Criteria q = this.getALAPPSSession().createCriteria(AstDataType.class);
		q.addOrder(Order.asc("typeNm"));
		return q.list();
	}
	
	/*
	 * ********************************************
	 * Data Source
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstDataSource> getDataSources() {
		Criteria q = this.getALAPPSSession().createCriteria(AstDataSource.class);
		q.addOrder(Order.asc("sourceNm"));
		return q.list();
	}
	

	@Transactional(readOnly = true)
	public AstDataSource getDataSourceByName(String sourceNm) {
		Criteria q = this.getALAPPSSession().createCriteria(AstDataSource.class);
		q.add(Restrictions.eq("sourceNm", sourceNm));
		return (AstDataSource)q.uniqueResult();
	}
	
	
	/*
	 * ********************************************
	 * Part
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstPart> getParts(BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection) {
		Criteria q = this.getALAPPSSession().createCriteria(AstPart.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<AstPart> getPartsPaginated(BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection, int pageIndex, int pageSize) {
		Criteria q = this.getALAPPSSession().createCriteria(AstPart.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setFirstResult(pageIndex);
		q.setMaxResults(pageSize);
		
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		return q.list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JtablePart> getJtablePartsPaginated(BigDecimal compTypeId, boolean activeOnly, String orderBy, String orderDirection, int pageIndex, int pageSize) {	
		
		if ( "compTypeId".equals(orderBy) ) {
			orderBy = "astCompType.compTypeId";
		}
		
		Session sess = this.getALAPPSSession();
		Criteria q = sess.createCriteria(AstPart.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setFirstResult(pageIndex);
		q.setMaxResults(pageSize);
		if ( "ASC".equalsIgnoreCase(orderDirection) ) {
			q.addOrder(Order.asc(orderBy));
		}
		else {
			q.addOrder(Order.desc(orderBy));
		}
		List<AstPart> astParts = q.list();
		List<JtablePart> jtableParts = new ArrayList<JtablePart>();
		if ( astParts != null ) {
			for ( AstPart p : astParts ) {
				//Get key attr value
				SQLQuery query = sess.createSQLQuery(
						getActivePartKeyAttrValueByPartIdQuery.replaceFirst(":partId:", p.getPartId().toPlainString())
						);
				jtableParts.add(new JtablePart(p, (String)query.uniqueResult()));
			}
		}
		return jtableParts;
	}
	
	@Transactional(readOnly = true)
	public Long getPartsCount(BigDecimal compTypeId, boolean activeOnly) {
		Criteria q = this.getALAPPSSession().createCriteria(AstPart.class);
		q.add(Restrictions.eq("astCompType.compTypeId", compTypeId));
		if (activeOnly) {
			q.add(Restrictions.eq("activeFl", "Y"));
		}
		q.setProjection(Projections.rowCount());
		return (Long)q.uniqueResult();
	}
	
	@Transactional(readOnly = true)
	public JtablePart getPartByActivePartKeyAttributeValue(String partKeyValue) {
		Session sess = this.getALAPPSSession();
		JtablePart jtPart = null;
		SQLQuery query = sess.createSQLQuery(
				getPartByActivePartKeyAttrValueQuery.replaceFirst(":valueTx:", partKeyValue)
				);
		Object obj = query.uniqueResult();
		if ( obj != null) {
			Object[] data = (Object[])obj;
			
			jtPart = new JtablePart(
								(BigDecimal)data[0], 
								(BigDecimal)data[1], 
								(String)data[2], 
								(String)data[3],
								(String)data[4],
								null,
					            null);
			jtPart.setPartAttrList(this.getPartAttrs(jtPart.getPartId()));
			AstCompType compType = this.getAstCompTypeHome().findById((BigDecimal)data[1]);
			
			//AstCompType parentCompType = this.getAstCompTypeHome().findById(compType.getParentCompTypeId());
			jtPart.setCompType(new JtableCompType(compType));
		}
		return jtPart;
	}
	
	@Transactional(readOnly = true)
	public AstPart getAstPartByActivePartKeyAttributeValue(String partKeyValue) {
		Session sess = this.getALAPPSSession();
		AstPart part = null;
		SQLQuery query = sess.createSQLQuery(
				getPartByActivePartKeyAttrValueQuery.replaceFirst(":valueTx:", partKeyValue)
				);
		Object obj = query.uniqueResult();
		if ( obj != null) {
			Object[] data = (Object[])obj;
			part = astPartHome.findById((BigDecimal)data[0]);
		}
		return part;
	}
	
	@Transactional(readOnly = true)
	public AstPart getPartById(BigDecimal id) {
		return astPartHome.findById(id);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public JtablePart addPartWithBlankAttributes(JtablePart p, SecurityUser user) throws Exception {
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
			Date now = new Date();
			p.setPartId(this.getNextPartIdSequence());
			
			boolean hasActivePartKeyAttr = false;
			AstCompType ct = astCompTypeHome.findById(p.getCompTypeId());
			for ( Object ctObj : ct.getAstCompTypeAttrs() ) {
				AstCompTypeAttr astCta = (AstCompTypeAttr)ctObj;
				if ( "Y".equals(astCta.getActiveFl()) && "Y".equals(astCta.getKeyFl())  ) {
					hasActivePartKeyAttr = true;
					break;
				}
			}
			if (!hasActivePartKeyAttr) {
				throw new Exception("Cannot create a part for a component type that does not have an active part key attribute.");
			}
			
			AstPart astPart = new AstPart();
			astPart.setPartId(p.getPartId());
			astPart.setAstCompType(ct);
			astPart.setActiveFl(p.getActiveFl());
			astPart.setKeyValue(p.getKeyValue());
			astPart.setCreateDt(now);
			astPart.setCreateUsrId(user.getAvnetGlobalUserId());
			astPartHome.persist(astPart); //Inheritance wont work with hibernate (this way)
			
			Criteria q = this.getALAPPSSession().createCriteria(AstCompTypeAttr.class);
			q.add(Restrictions.eq("activeFl", "Y"));
			q.add(Restrictions.eq("astCompType.compTypeId", p.getCompTypeId()));
			q.addOrder(Order.asc("compTypeAttrId"));
			List<AstCompTypeAttr> compTypeAttrList = (List<AstCompTypeAttr>)q.list();
			for ( AstCompTypeAttr cta : compTypeAttrList ) {
				AstPartAttr pa = new AstPartAttr();
				pa.setPartAttrId(this.getNextPartAttrIdSequence());
				pa.setAstCompTypeAttr(cta);
				pa.setAstPart(astPart);
				//Set the active key attribute value
				if ( "Y".equalsIgnoreCase(cta.getKeyFl()) ) {
					pa.setValueTx(p.getKeyValue());
				}
				else {
					pa.setValueTx("");
				}
				pa.setCreateDt(now);
				pa.setCreateUsrId(user.getAvnetGlobalUserId());
				astPartAttrHome.persist(pa);
			}

			/*
			this.crudLogger(sess,user,"ADD","AstPart","CompTypeId",null,astPart.getAstCompType().getCompTypeId().toPlainString());
			this.crudLogger(sess,user,"ADD","AstPart","ActiveFl",null,astPart.getActiveFl());
			this.crudLogger(sess,user,"ADD","AstPart","key",null,p.getKeyValue());
			*/
			
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return p;
	}
	
	@Transactional(readOnly = false)
	public JtablePart updatePart(JtablePart p, SecurityUser user) {
		//Only allow update of active status
		AstPart newPart = astPartHome.findById(p.getPartId());
		newPart.setActiveFl(p.getActiveFl());
		newPart.setUpdateDt(new Date());
		newPart.setUpdateUsrId(user.getAvnetGlobalUserId());
		astPartHome.merge(newPart);
		return p;
	}
	
	@Transactional(readOnly = false)
	public JtablePart deletePart(JtablePart p) throws Exception {
		
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
			SQLQuery q = sess.createSQLQuery(deletePartAttrsByPartId.replaceFirst(":partId:", p.getPartId().toPlainString()));
			q.executeUpdate();
			AstPart part = astPartHome.findById(p.getPartId());
			astPartHome.delete(part);
			
			p.setActiveFl(part.getActiveFl());
			p.setCompTypeId(part.getAstCompType().getCompTypeId());
			p.setPartId(part.getPartId());
			
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return p;
	}
	
	@Transactional(readOnly = false)
	public JtablePartAttr updatePartAttrValue(JtablePartAttr pa, SecurityUser user) {
		//Only allow update of attr value
		AstPartAttr currAttr = astPartAttrHome.findById(pa.getPartAttrId());
		Date now = new Date();
		//Also update the part key value
		if ( "Y".equalsIgnoreCase(currAttr.getAstCompTypeAttr().getActiveFl()) && 
				"Y".equalsIgnoreCase(currAttr.getAstCompTypeAttr().getKeyFl()) ) {
			AstPart p = currAttr.getAstPart();
			p.setKeyValue(pa.getValueTx());
			
			p.setUpdateDt(now);
			p.setUpdateUsrId(user.getAvnetGlobalUserId());
			
			astPartHome.merge(p);
		}
		
		currAttr.setUpdateDt(now);
		currAttr.setUpdateUsrId(user.getAvnetGlobalUserId());
		
		currAttr.setValueTx(pa.getValueTx());
		astPartAttrHome.merge(currAttr);
		return pa;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public String addPartMissingAttributes(BigDecimal partId, SecurityUser user) throws Exception {
		String message = "";
		
		//TODO: Must move transaction external to call with transaction checking
		//Transaction trans = null;
		try {
			//Session sess = this.getALAPPSSession();
			//trans = sess.beginTransaction();
			Date now = new Date();
			AstPart part = this.astPartHome.findById(partId);
			
			if ( part == null ) {
				throw new Exception("Part ID not found: " + partId);
			}
			
			long addCount = 0;
			String partNumber = "";
			for ( Object ctaObj : part.getAstCompType().getAstCompTypeAttrs() ) {
				AstCompTypeAttr compTypeAttr = (AstCompTypeAttr)ctaObj;
				if ( "Y".equalsIgnoreCase(compTypeAttr.getActiveFl()) ) {
					boolean attrFound = false;
					for ( Object paObj : part.getAstPartAttrs() ) {
						AstPartAttr partAttr = (AstPartAttr)paObj;
						if ( "Y".equalsIgnoreCase(partAttr.getAstCompTypeAttr().getKeyFl()) ) {
							partNumber = partAttr.getValueTx();
						}
						if ( partAttr.getAstCompTypeAttr().getCompTypeAttrId().equals(compTypeAttr.getCompTypeAttrId()) ) {
							attrFound = true;
							break;
						}
					}
					if ( !attrFound ) {
						AstPartAttr pa = new AstPartAttr();
						pa.setPartAttrId(this.getNextPartAttrIdSequence());
						pa.setAstCompTypeAttr(compTypeAttr);
						pa.setAstPart(part);
						pa.setValueTx("");
						pa.setCreateDt(now);
						pa.setCreateUsrId(user.getAvnetGlobalUserId());
						astPartAttrHome.persist(pa);
						addCount++;
					}
				}
			}
			message = "Part " + partNumber + " added " + String.valueOf(addCount) + " missing attributes";
			
			//trans.commit();
		}
		catch ( Exception e ) {
			//if ( trans != null ) trans.rollback();
			throw e;
		}
		return message;
	}
	
	/*
	 * ********************************************
	 * Part Attr
	 * ********************************************
	 */

	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<JtablePartAttr> getPartAttrs(BigDecimal partId) {
		Criteria q = this.getALAPPSSession().createCriteria(AstPartAttr.class);
		q.add(Restrictions.eq("astPart.partId", partId));
		q.addOrder(Order.asc("partAttrId"));
		//q.addOrder(Order.asc("astCompTypeAttr.attrNm")); //TODO: This fails		
		List<JtablePartAttr> list = new ArrayList<JtablePartAttr>();
		for ( AstPartAttr pa : (List<AstPartAttr>)q.list() ) {
			list.add(new JtablePartAttr(pa));
		}
		return list;
	}
	
	@Transactional(readOnly = true)
	public AstPartAttr getPartAttrById(BigDecimal partAttrId) {
		return astPartAttrHome.findById(partAttrId);
	}
	
	@Transactional(readOnly = true)
	public List<JtablePartAttr> getPartAttrsBySource(BigDecimal partId, String dataSourceName) {
		Session sess = this.getALAPPSSession();
		SQLQuery query = sess.createSQLQuery(
				partAttrsByPartIdAndSourceNameQuery
					.replaceFirst(":partId:", partId.toPlainString())
					.replaceFirst(":sourceNm:", dataSourceName)
				);
		
		List<JtablePartAttr> recordList = new ArrayList<JtablePartAttr>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
			JtablePartAttr rec = 
				new JtablePartAttr(
						(BigDecimal)data[0], 
						(BigDecimal)data[1],
						(BigDecimal)data[2], 
						(String)data[3],
						(String)data[4],
						(String)data[5]
					);
			recordList.add(rec);
		}
		return recordList;
	}
	
	/*
	 * ********************************************
	 * Part Asm
	 * ********************************************
	 */
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public AstPartAsm getAstPartAsmById(BigDecimal id, boolean showMissingAttrs, SecurityUser user) throws Exception {
		
		AstPartAsm partAsm = this.astPartAsmHome.findById(id);
		if ( partAsm != null && showMissingAttrs ) {
			this.addMissingPartAsmAttrs(partAsm, user);
		}
		return partAsm;
	}
	
	@SuppressWarnings("unchecked")
	public void addMissingPartAsmAttrs(AstPartAsm partAsm, SecurityUser user) throws Exception {
		AstPart part = this.astPartHome.findById(partAsm.getAstPart().getPartId());
		List<AstPartAsmAttr> newAttrList = new ArrayList<AstPartAsmAttr>();
		Date now = new Date();
		
		for ( Object paObj : part.getAstPartAttrs() ) {
			AstPartAttr pa = (AstPartAttr)paObj;
			/*
			log.info(
					"*** FOUND " + pa.getAstPart().getAstCompType().getTypeNm() + ": " + pa.getAstCompTypeAttr().getAttrNm() + ""
					);
			*/
			boolean attrFound = false;
			for ( Object pasmaObj : partAsm.getAstPartAsmAttrs() ) {
				AstPartAsmAttr partAsmAttr = (AstPartAsmAttr)pasmaObj;
				if ( pa.getPartAttrId().equals(partAsmAttr.getAstPartAttr().getPartAttrId()) ) {
					attrFound = true;					
					//Fill in CONFIG values with latest part CRUD values
					if ( partAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAstDataSource().getSourceNm().equalsIgnoreCase("CONFIG") ) {
						partAsmAttr.setValueTx(pa.getValueTx());
					}
					//break;
				}
			}
			if ( !attrFound ) {
				if ( "Y".equalsIgnoreCase(pa.getAstCompTypeAttr().getActiveFl()) ) {
					newAttrList.add(new AstPartAsmAttr(null, partAsm, pa, pa.getValueTx(), user.getAvnetGlobalUserId(), now, null, null));
					/*
					log.info(
							"*** ADDING " + pa.getAstPart().getAstCompType().getTypeNm() + ": " + pa.getAstCompTypeAttr().getAttrNm() + ""
							);
							*/
				}
			}
		}
		for ( AstPartAsmAttr newAttr : newAttrList ) {
			partAsm.getAstPartAsmAttrs().add(newAttr);
		}
		for ( Object pasmObj : partAsm.getAstPartAsms() ) {
			AstPartAsm childPartAsm = (AstPartAsm)pasmObj;
			this.addMissingPartAsmAttrs(childPartAsm, user);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public AstPartAsm addPartAsm(AstPartAsm pasm, SecurityUser user) throws Exception {
		Transaction trans = null;
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction();
	
			SQLQuery query = sess.createSQLQuery(nextPartAsmIdSequence);
			BigDecimal partAsmId = (BigDecimal)query.uniqueResult();
			pasm.setPartAsmId(partAsmId);
			
			
			if ( pasm.getAstPart() != null ) {
				AstPart part = astPartHome.findById(pasm.getAstPart().getPartId());
				pasm.setAstPart(part);
			}
			
			pasm.setCreateDt(new Date());
			pasm.setCreateUsrId(user.getAvnetGlobalUserId());
			
			astPartAsmHome.persist(pasm); //Inheritance wont work with hibernate (this way)

			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return pasm;
	}
	
	
	/* TODO: Experimental */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public AstPartAsm getSlimPartAsmByTestResultList(AstPartAsm partAsm, List<BigDecimal> filterDisplayTestResultIds) throws Exception {
		Session sess = this.getALAPPSSession();
		return getSlimNestedPartAsms(sess, true, partAsm, filterDisplayTestResultIds);
	}
	
	@SuppressWarnings("unchecked")
	private AstPartAsm getSlimNestedPartAsms(Session sess, boolean isChassis, AstPartAsm partAsm, List<BigDecimal> filterDisplayTestResultIds) {
		DateTime chassisStart = null;
		if ( isChassis ) {
			chassisStart = new DateTime();
		}

		AstPartAsm newAsm = new AstPartAsm(
				partAsm.getPartAsmId(), 
				partAsm.getAstPartAsm(), //AstPartAsm astPartAsm,
				partAsm.getAstPart(), 
				partAsm.getCreateDt(), 
				partAsm.getCreateUsrId(),
				partAsm.getTouchLevel(), 
				null, //BigDecimal depopFl, 
				partAsm.getUpdateDt(), //Date updateDt,
				partAsm.getUpdateUsrId(), //String updateUsrId, 
				new HashSet(), //Set astPartAsmExcludeds, 
				new HashSet(), //Set astPartAsms,
				new HashSet(), //Set astTestResults, 
				partAsm.getAstPartAsmAttrs(), //Set astPartAsmAttrs, 
				new HashSet() //Set astTestResultItems
				);
	
		
		if ( partAsm != null ) {
			Set<AstPartAsm> childAsmSet = partAsm.getAstPartAsms();
			List<AstPartAsm> newChildAsmList = new ArrayList<AstPartAsm>();
			for (Object ca : childAsmSet) {
				AstPartAsm childAsm = (AstPartAsm)ca;
				AstPartAsm newchildAsm = getSlimNestedPartAsms(sess, false, childAsm, filterDisplayTestResultIds);
				newChildAsmList.add(newchildAsm);
			}
			newAsm.setAstPartAsms(new HashSet(newChildAsmList));
			
			if ( isChassis ) {
				Criteria trCrit = sess.createCriteria(AstTestResult.class)
					.add(Restrictions.in("testResultId", filterDisplayTestResultIds))
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
				List<AstTestResult> trList = trCrit.list();
				if ( trList != null ) {
					newAsm.setAstTestResults(new HashSet(trList));
				}
				else {
					newAsm.setAstTestResults(new HashSet());
				}
			}
			else {
				newAsm.setAstTestResults(new HashSet());
			}
			
			Criteria triCrit = sess.createCriteria(AstTestResultItem.class)
				.add(Restrictions.eq("astPartAsm.partAsmId", partAsm.getPartAsmId()))
				.add(Restrictions.in("astTestResult.testResultId", filterDisplayTestResultIds))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			List<AstTestResultItem> triList = triCrit.list();
			if ( triList != null ) {
				newAsm.setAstTestResultItems(new HashSet(triList));
			}
			else {
				newAsm.setAstTestResultItems(new HashSet());
			}
		}
		
		if ( isChassis ) {
			DateTime chassisEnd  = new DateTime();
			Duration chassisDur = new Duration(chassisStart, chassisEnd);
			System.out.println("Slim Query: " + String.valueOf(chassisDur.getStandardSeconds() / 60) + " (" + String.valueOf(chassisDur.getStandardSeconds()) + "s)");
		}
		
		return newAsm;
	}
	
	
	
	/* TODO: Experimental */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public MinAstPartAsm getPartAsmByTestResultIdList(BigDecimal partAsmId, List<BigDecimal> filterDisplayTestResultIds) throws Exception {

		
		// http://stackoverflow.com/questions/6941388/hibernate-criteria-how-to-filter-on-nested-child?rq=1
		// https://forum.hibernate.org/viewtopic.php?f=1&t=973446
		// https://docs.jboss.org/hibernate/orm/3.3/reference/en/html/filters.html
		// https://forum.hibernate.org/viewtopic.php?f=1&t=967820&sid=e43f77dbc53d7cdfa6049d327f81d685&start=15
		// https://docs.jboss.org/hibernate/orm/3.5/javadocs/org/hibernate/Criteria.html#createCriteria(java.lang.String, java.lang.String, int, org.hibernate.criterion.Criterion)
		//!!!  http://java-persistence-performance.blogspot.com/2012/04/objects-vs-data-and-filtering-join.html
		//https://examples.javacodegeeks.com/enterprise-java/hibernate/hibernate-data-filter-example-xml-and-annotation/
		//http://stackoverflow.com/questions/5680750/how-to-enable-hibernate-filter-for-sessionfactory-getcurrentsession

		Session sess = this.getALAPPSSession();
		if ( filterDisplayTestResultIds != null && !filterDisplayTestResultIds.isEmpty() ) {

			//String idListString = StringUtils.join(filterDisplayTestResultIds.iterator(), ",");
			//System.out.println("*** idListString = " + idListString);
			
			Filter filterTR = sess.enableFilter("MinAstPartAsm_MinAstTestResult_testResultId_IN_Filter")
				.setParameterList("testResultIdList", filterDisplayTestResultIds);
			filterTR.validate();
			
			Filter filterTRI = sess.enableFilter("MinAstPartAsm_MinAstTestResultItem_testResultId_IN_Filter")
				.setParameterList("testResultIdList", filterDisplayTestResultIds);
			filterTRI.validate();
		}
		Query q = sess.createQuery("from MinAstPartAsm pa where pa.partAsmId = " + String.valueOf(partAsmId));
		return (MinAstPartAsm)q.uniqueResult();
	}
	
	
	/*
	 * ********************************************
	 * Part Asm Attr
	 * ********************************************
	 */
	public void updateAstPartAsmAttr(AstPartAsmAttr changedAttr) {
		this.astPartAsmAttrHome.merge(changedAttr);
	}
	
	
	/*
	 * ********************************************
	 * Mock BOM. 
	 * No longer needed
	 * ********************************************
	 */
	@Transactional(readOnly = true)
	public DisplayBom getTempBom(String serialNum, String icn) {
		DisplayBom returnBom = null;
		Criteria q = this.getALAPPSSession().createCriteria(AstTempBom.class);
		q.add(Restrictions.eq("serialNum", serialNum));
		q.add(Restrictions.eq("icn", icn));
		AstTempBom b = (AstTempBom) q.uniqueResult();
		if ( b != null ) {
			returnBom = new DisplayBom(b);
			returnBom.setICN(icn);
			if ( returnBom.getBomPartList() != null ) {
				for ( DisplayBomPart bp : returnBom.getBomPartList() ) {
					bp.setPart(this.getPartByActivePartKeyAttributeValue(bp.getPartNum()));
				}
			}
			returnBom.setPart(this.getPartByActivePartKeyAttributeValue(b.getPartNum()));
			for ( JtablePartAttr attr : returnBom.getPart().getPartAttrList() ) {
				if ( "SHOPFLOOR".equalsIgnoreCase(attr.getCompTypeAttr().getDataSourceNm()) &&
						"SERIALNUMBER".equalsIgnoreCase(attr.getCompTypeAttr().getAttrNm())	) {
					attr.setValueTx(returnBom.getSerialNum());
				}
			}
		}
		return returnBom;
	}
	
	/*
	 * ********************************************
	 * BOM. 
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public DisplayBom getBom(String serialNum, String icn, BigDecimal touchLevel, AstPartAsm firstTouchChassisAsm) throws Exception {
		Session astSession = this.getALAPPSSession();
		Session itsSession = this.getDBCONNECTSSession();			
		List<String> astChassisPartNumberList = new ArrayList<String>();
		
		SQLQuery astChassisPartNumberQuery = astSession.createSQLQuery(getAllAstChassisPartNumbersQuery);
		for ( Object obj : astChassisPartNumberQuery.list() ) {
			astChassisPartNumberList.add((String)obj);
		}

		DisplayBom returnBom = null;

		/*
		//GENESIS
		Criteria ipCrit = itsSession.createCriteria(ItsPart.class);
		ipCrit.add(Restrictions.eq("itsPartSn", serialNum.toUpperCase()));
		ipCrit.add(Restrictions.eq("itsInvoiceNo", icn.toUpperCase()).ignoreCase());
		ItsPart nutanixTopLevelChassisPart = (ItsPart) ipCrit.uniqueResult();
		if ( nutanixTopLevelChassisPart == null ) {
			throw new Exception("Could not find ITS record for serial number and ICN. Please contact support.");
		}
		*/
		
		//EVOLVE
		Criteria ipCrit = itsSession.createCriteria(ItsUnitDetail.class);
		ipCrit.add(Restrictions.eq("unitSerialNumber", serialNum.toUpperCase()));
		ipCrit.add(Restrictions.eq("itsInvoiceNo", icn.toUpperCase()).ignoreCase());
		ItsUnitDetail nutanixTopLevelChassis = (ItsUnitDetail) ipCrit.uniqueResult();
		if ( nutanixTopLevelChassis == null ) {
			throw new Exception("Could not find ITS record for chassis S/N and production order. Please contact support.");
		}
		
		//BigDecimal itsUnitDetailId = nutanixTopLevelChassisPart.getItsUnitDetailId();
		BigDecimal itsUnitDetailId = new BigDecimal(nutanixTopLevelChassis.getItsUnitDetailId());
		
		if ( itsUnitDetailId == null ) {
			throw new Exception(
					"Cannot determine ITS unit detail id for serialnumber=" + serialNum + ", icn=" + icn + ". Please contact support."
					);
		}

		
		
		SQLQuery itsPartQuery = itsSession.createSQLQuery(
				getItsPartsByUnitDetailIdQuery
				.replaceFirst(":itsUnitDetailId:", itsUnitDetailId.toPlainString())
			);
		
		List itsPartList = itsPartQuery.list();

		
		if ( itsPartList == null || itsPartList.size() == 0 ) {
			throw new Exception("No parts found in ITS. Please contact support.");
		}
		
		//Loop over parts to check data capture on 1st touch, and get correct mfg part number always
		int partIdx = 0;
		for ( Object obj : itsPartList ) {
			
			Object[] data = (Object[])obj;
			//final String itsPartSerialNumber = (String)data[1];
			//final String itsPartDesc = (String)data[2];
			final String itsReqDesc = (String)data[3];
			final String itsReqVal = (String)data[4];
			//log.info("**********  assembly " + itsReqDesc + " = " + itsReqVal);

			//Check that data capture was performed in ITS on 1st-touch only
			if ( touchLevel.intValue() == 1 ) {
				if ( ( "Node IPMI".equalsIgnoreCase(itsReqDesc) || 
						"Node MAC 1".equalsIgnoreCase(itsReqDesc) ||
						"Node MAC 2".equalsIgnoreCase(itsReqDesc)  ) &&
						( itsReqVal == null || itsReqVal.trim().length() == 0  )
						)  {
					throw new Exception(
							"Assembly prevented. Missing value for " + itsReqDesc + 
							". Correct ITS data capture value and try again. If problem persists, please open a Nomad ticket for support."
							);
				}
				//TODO: These will be checked in the future - "SFT-OOB-LIC KEY", "PSID", "MSID", "Hardware Revision"
			}
			
			//Check NIC MAC 1 for 1st and 2nd touch
			if ( "MAC Address 1".equalsIgnoreCase(itsReqDesc)  )  {
				if ( itsReqVal == null || itsReqVal.trim().length() == 0 )  {
					throw new Exception(
							"Assembly prevented. Missing value for " + itsReqDesc + 
							". Correct ITS data capture value and try again. If problem persists, please open a Nomad ticket for support."
							);
				}
				else {
					//Validate MAC1 on NIC for later MAC generation on assembly submit. Prevent load if malformed
					String mac1String = itsReqVal.trim().toUpperCase().replaceAll("[^A-Z0-9]", "");
					if ( mac1String.length() != 12 ) {
						throw new Exception(
								"Assembly prevented. Bad value for " + itsReqDesc + " = " + itsReqVal +
								". Correct ITS data capture value and try again."
								);
					}
				}
			}
			
			//In ITS, mfg part number is actually just a TLA record id, for some parts returned. Sigh...
			String itsPartMfgPartNumber = (String)data[0];
			if ( itsPartMfgPartNumber != null && StringUtils.isNumeric(itsPartMfgPartNumber) ) {
				TopLevelAssembly tla = this.topLevelAssemblyHome.findById(new BigDecimal(itsPartMfgPartNumber));
				if ( tla != null ) {
					data[0] = tla.getCustomerPartNo();
					itsPartList.set(partIdx, data);
				}
			}
			
			partIdx++;
		}
		//
		
		
		List<DisplayBomPart> bomPartList = new ArrayList<DisplayBomPart>();
		//List<DisplayExcludedPart> excludedPartList = new ArrayList<DisplayExcludedPart>();
		Map<String, DisplayExcludedPart> excludedPartMap = new HashMap<String, DisplayExcludedPart>();

		if ( touchLevel.intValue() == 2 && firstTouchChassisAsm == null ) {
			throw new Exception("2nd-Touch assembly does not have 1st-Touch assembly. Please contact support.");
		}
		else if ( touchLevel.intValue() == 2 && firstTouchChassisAsm != null ) {
			firstTouchChassisAsm.getAstPart().getKeyValue();
			String paaSerialNumber = null;
			String paaPartNumber = null;
			for ( Object o : firstTouchChassisAsm.getAstPartAsmAttrs() ) {
				AstPartAsmAttr paa = (AstPartAsmAttr) o;
				String paaNm = paa.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
				if ( "NUTANIXSERIALNUMBER".equalsIgnoreCase(paaNm) ) {
					paaSerialNumber = paa.getValueTx();
				}
				else if ( "PARTNUMBER".equalsIgnoreCase(paaNm) ) {
					paaPartNumber = paa.getValueTx();
				}
			}
			
			returnBom = new DisplayBom(
					itsUnitDetailId, 
					paaPartNumber, 
					paaSerialNumber, 
					icn, 
					null
					);
			returnBom.setPart(new JtablePart(firstTouchChassisAsm.getAstPart(), firstTouchChassisAsm.getAstPart().getKeyValue()));
		}
		
		Map<String, String> serialNumberPartMap = new HashMap<String, String>();
		for ( Object obj : itsPartList ) {
			Object[] data = (Object[])obj;
			String itsPartMfgPartNumber = (String)data[0];
			String itsPartSerialNumber = (String)data[1];
			String itsPartDesc = (String)data[2];
			//String itsReqDesc = (String)data[3];
			//String itsReqVal = (String)data[4];
			
			//Dont duplicate parts records with same serial
			if ( itsPartSerialNumber != null ) {
				
				String previousPartNumber = serialNumberPartMap.get(itsPartSerialNumber);
				if ( previousPartNumber != null ) {
					if ( previousPartNumber.equalsIgnoreCase(itsPartMfgPartNumber) ) {
						continue;
					}
					else {
						throw new Exception(
								"Duplicate serial numbers found in ITS: Serial Number = " + itsPartSerialNumber + 
								", Parts = " + previousPartNumber + ", " + itsPartMfgPartNumber
								);
					}
				}
				else {
					serialNumberPartMap.put(itsPartSerialNumber, itsPartMfgPartNumber);
				}
				
				/*
				if ( serialNumberList.contains(itsPartSerialNumber) ) {
					continue;
				}
				else {
					serialNumberList.add(itsPartSerialNumber);
				}
				*/
			}
			
			if ( touchLevel.intValue() == 1 && astChassisPartNumberList.contains(itsPartMfgPartNumber) ) {
				//returnBom = new DisplayBom(itsUnitDetailId, itsPartMfgPartNumber, itsPartSerialNumber, icn, null);
				/*
				returnBom = new DisplayBom(
						itsUnitDetailId, 
						nutanixTopLevelChassisPart.getItsPartMfgpartno(), 
						nutanixTopLevelChassisPart.getItsPartSn(), 
						icn, 
						null
						);
				*/
				returnBom = new DisplayBom(
						itsUnitDetailId, 
						nutanixTopLevelChassis.getItsPartMfgpartno(), 
						nutanixTopLevelChassis.getUnitSerialNumber(), 
						icn, 
						null
						);
				
				returnBom.setPart(this.getPartByActivePartKeyAttributeValue(itsPartMfgPartNumber));
			}
			else if ( !serialNum.equalsIgnoreCase(itsPartSerialNumber) ) {
				JtablePart part = this.getPartByActivePartKeyAttributeValue(itsPartMfgPartNumber);
				DisplayBomPart bomPart = new DisplayBomPart(null, itsUnitDetailId, itsPartMfgPartNumber,
						itsPartSerialNumber, itsPartDesc);
				if ( part != null ) {
					bomPart.setPartId(part.getPartId());
					bomPart.setPart(part);
					bomPartList.add(bomPart);
				}
				else {
					DisplayExcludedPart ep = null;
					if ( excludedPartMap.containsKey(itsPartMfgPartNumber) ) {
						ep = excludedPartMap.get(itsPartMfgPartNumber);
						ep.setPartCount(new BigDecimal(ep.getPartCount().longValue() + 1));
					}
					else {
						ep = new DisplayExcludedPart(
								itsPartMfgPartNumber, 
								null,
								itsPartDesc,
								"Part not configured.", 
								new BigDecimal(1)
								);
					}
					excludedPartMap.put(itsPartMfgPartNumber, ep);
				}
				
			}
		}
		
		if ( touchLevel.intValue() == 1 && returnBom == null ) {
			throw new Exception(
					"Could not match any ITS manufacturer part numbers to chassis parts in the AST Part CRUD."
					);
		}
		
		
		returnBom.setBomPartList(bomPartList);
		
		for ( String epKey : excludedPartMap.keySet() ) {
			returnBom.addExcludedPart(excludedPartMap.get(epKey));
		}
	
		return returnBom;
	}
	
	
	@SuppressWarnings("unchecked")
	public AstPartAsm saveAssemblyFromJsonString(
										String submitData, 
										String submitChassisSerialNum, 
										String submitICN,
										String submitChassisPartNum, 
										String submitChassisPartId, 
										SecurityUser user,
										BigDecimal touchLevel,
										String submitFirstTouchAssemblyId,
										boolean persist,
										EvolveSalesOrderDetailService evolveSalesOrderDetailService
										) throws Exception {

		//Setup container for processing json
		ContainerFactory containerFactory = new ContainerFactory(){
			public List creatArrayContainer() {
			  return new LinkedList();
			}
			public Map createObjectContainer() {
			  return new LinkedHashMap();
			}                       
		};
		
		//Get generic node part from AST
		AstPart dummyAstNodePart = this.getAstPartByActivePartKeyAttributeValue("NODE");
		if ( dummyAstNodePart == null ) {
			throw new Exception("Dummy node part with part key value of 'NODE' not found. Please check part configuration.");
		}
		
		AstPartAsm chassisPartAsm = null;
		Transaction trans = null;
		try {
			Session astSession = this.getALAPPSSession();
			Session itsSession = this.getDBCONNECTSSession();		
			
			
			///////////////////////////////////////////////////////////////////////////////
			
			//Get list of chassis part numbers registered in AST so we can locate part record from ITS/SABRE
			List<String> astChassisPartNumberList = new ArrayList<String>();
			SQLQuery astChassisPartNumberQuery = astSession.createSQLQuery(getAllAstChassisPartNumbersQuery);
			for ( Object obj : astChassisPartNumberQuery.list() ) {
				astChassisPartNumberList.add((String)obj);
			}

			//Get ITS part for Nutanix top level chassis with unit detail ID so we can locate components
			/*
			//GENESIS itsUnitDetailId
			Criteria ipCrit = itsSession.createCriteria(ItsPart.class);
			ipCrit.add(Restrictions.eq("itsPartSn", submitChassisSerialNum.toUpperCase()));
			ipCrit.add(Restrictions.eq("itsInvoiceNo", submitICN.toUpperCase()).ignoreCase());
			ItsPart nutanixTopLevelChassisPart = (ItsPart) ipCrit.uniqueResult();
			BigDecimal itsUnitDetailId = nutanixTopLevelChassisPart.getItsUnitDetailId();
			String nutanixSerialNumber =  nutanixTopLevelChassisPart.getItsPartSn();
			*/
			
			//EVOLVE itsUnitDetailId
			Criteria ipCrit = itsSession.createCriteria(ItsUnitDetail.class);
			ipCrit.add(Restrictions.eq("unitSerialNumber", submitChassisSerialNum.toUpperCase()));
			ipCrit.add(Restrictions.eq("itsInvoiceNo", submitICN.toUpperCase()).ignoreCase());
			ItsUnitDetail nutanixTopLevelChassisUnitDetail  = (ItsUnitDetail) ipCrit.uniqueResult();
			
			BigDecimal itsUnitDetailId = new BigDecimal(nutanixTopLevelChassisUnitDetail.getItsUnitDetailId());
			String nutanixSerialNumber =  nutanixTopLevelChassisUnitDetail.getUnitSerialNumber();
			String nutanixTopLevelPartNumber = null;
			String customerOrderLineNumber = null;
			String customerSalesOrderReference = null;
			String productionCell = null;
			
			Criteria itgCrit = itsSession.createCriteria(Integration.class);
			itgCrit.add(Restrictions.eq("id.itsInvoiceNo", submitICN.toUpperCase()).ignoreCase());
			Integration integration = (Integration) itgCrit.uniqueResult();

			if ( touchLevel.intValue() == 2 ) {
				
				//Old EVOLVE nutanixTopLevelPartNumber
				nutanixTopLevelPartNumber = nutanixTopLevelChassisUnitDetail.getItsPartMfgpartno(); //Initialized to old logic
				if ( StringUtils.isNumeric(nutanixTopLevelPartNumber) ) {
					Criteria tlaCrit = itsSession.createCriteria(TopLevelAssembly.class)
					.add(Restrictions.eq("topLevelAssemblyId", new BigDecimal(nutanixTopLevelChassisUnitDetail.getItsPartMfgpartno())));
					TopLevelAssembly chassisTla = (TopLevelAssembly)tlaCrit.uniqueResult();
					if ( chassisTla != null ) {
						nutanixTopLevelPartNumber = chassisTla.getCustomerPartNo();
					}
				}
		
		
				String itsSalesOrderNumber = null;
				if ( integration != null ) {
					customerSalesOrderReference = integration.getItsCustpono();
					ItsTeam team = this.getTeamById(integration.getItsTeamId());
					if ( team != null ) {
						productionCell = team.getItsTeamDesc();
					}
					itsSalesOrderNumber = integration.getId().getItsScn();
				}
				
				//GENESIS
				//if ( nutanixTopLevelChassisPart.getScnCustomerLineNo() != null ) {
				//	customerOrderLineNumber = nutanixTopLevelChassisPart.getScnCustomerLineNo().toPlainString();
				//}
				//EVOLVE
				
	
				if ( itsSalesOrderNumber != null ) {					
					//Criteria pCrit = itsSession.createCriteria(ItsPart.class);
					//pCrit.add(Restrictions.eq("itsPartSn", submitChassisSerialNum.toUpperCase()));
					//pCrit.add(Restrictions.eq("itsInvoiceNo", submitICN.toUpperCase()).ignoreCase());
					//ItsPart nutanixTopLevelChassisPart = (ItsPart) pCrit.uniqueResult();
					ItsPart nutanixTopLevelChassisPart = this.getItsTopLevelPart(submitChassisSerialNum, submitICN);
					if ( nutanixTopLevelChassisPart == null ) {
						throw new Exception("Could not find ITS record for serial number and production order. Please contact support.");
					}
					
					//String itsLineItemNumber = nutanixTopLevelChassisUnitDetail.getItsLineItemNo();
					//customerOrderLineNumber = itsLineItemNumber; //Dont do this anymore
					customerOrderLineNumber = 
						evolveSalesOrderDetailService.getZzCustRefBySalesOrderAndLineNumber(itsSalesOrderNumber, nutanixTopLevelChassisPart.getSapMaterialNo());
				}
			}
			else if ( touchLevel.intValue() == 1 ) { //Get project customer part number for 1st touch from ICN
				try {
					
					//Get new way Evolve Nutanix top level part number. Same way we did in Genesis again
					if ( integration != null ) {
						Criteria projCrit = itsSession.createCriteria(Project.class)
						.add(Restrictions.eq("projectId", integration.getProjectId()));
						Project project = (Project)projCrit.uniqueResult();
						if ( project != null ) {
							nutanixTopLevelPartNumber = project.getCustpartno();
						}
					}
					
					
					if ( integration != null ) {
						
						/*
						//GENESIS nutanixTopLevelPartNumber
						Criteria projCrit = itsSession.createCriteria(Project.class)
							.add(Restrictions.eq("projectId", intg.getProjectId()));
						Project project = (Project) projCrit.uniqueResult();
						if ( project != null ) {
							nutanixTopLevelPartNumber = project.getCustpartno();
						}
						*/
						
						
						ItsTeam team = this.getTeamById(integration.getItsTeamId());
						if ( team != null ) {
							productionCell = team.getItsTeamDesc();
						}
					}
				}
				catch ( Exception e ) {
					throw new Exception("Could not retrieve 1st-Touch Nutanix top-level part number: " + e.getMessage());
				}
			}
			//else if ( touchLevel.intValue() == 2 ) { //TODO: SAME LOGIC FOR GENESIS VS. EVOLVE??
			//	nutanixTopLevelPartNumber = nutanixTopLevelChassisPart.getItsPartMfgpartno();
			//}
			
			if ( nutanixTopLevelPartNumber != null ) {
				nutanixTopLevelPartNumber = nutanixTopLevelPartNumber.replace("-TEST", "");
			}

			//Get Nutanix top-level chassis data
			Map<String, String> shopFloorValuesChassisMap = 
				new HashMap<String, String>();
			shopFloorValuesChassisMap.put("productioncell", productionCell);
			shopFloorValuesChassisMap.put("customersalesorderref", customerSalesOrderReference);
			shopFloorValuesChassisMap.put("customerorderlinenumber", customerOrderLineNumber);			
			shopFloorValuesChassisMap.put("nutanixtoplevelpartnumber", nutanixTopLevelPartNumber);
			shopFloorValuesChassisMap.put("nutanixserialnumber", nutanixSerialNumber);
			shopFloorValuesChassisMap.put("ICN", submitICN);
			AstPart chassisPart = null;
			Map<String, Map<String, String>> shopFloorValuesByPartSerialMap = 
				new HashMap<String, Map<String, String>>();
			
			//Get chassis part and serial number for 1st-touch assembly if this is a 2nd-touch assembly
			if ( touchLevel.intValue() == 2  ) {
				//This is mfr chassis data if 2nd-touch assembly
				AstPartAsm firstTouchAsm = this.getAstPartAsmById(new BigDecimal(submitFirstTouchAssemblyId), false, null);
				if ( firstTouchAsm != null ) {
					chassisPart = firstTouchAsm.getAstPart();
					for ( Object o : firstTouchAsm.getAstPartAsmAttrs() ) {
						AstPartAsmAttr a = (AstPartAsmAttr)o;
						if ( "SERIALNUMBER".equalsIgnoreCase(a.getAstPartAttr().getAstCompTypeAttr().getAttrNm()) ) {
							shopFloorValuesChassisMap.put("serialnumber", a.getValueTx());
							break;
						}
					}
					
					//Need to pull motherboard SHOPFLOOR attrs here for 2nd touch
					for ( Object cp1 : firstTouchAsm.getAstPartAsms() ) {
						AstPartAsm cPart1 = (AstPartAsm)cp1;
						if ( "NODE".equalsIgnoreCase(cPart1.getAstPart().getAstCompType().getTypeNm()) ) {
							for ( Object cp2 : cPart1.getAstPartAsms() ) {
								AstPartAsm cPart2 = (AstPartAsm)cp2;
								if ( "MB".equalsIgnoreCase(cPart2.getAstPart().getAstCompType().getTypeNm()) ) {
									String serialMB = null;
									String ipmimacMB = "";
									String mac1MB = "";
									String mac2MB = "";
									
									for ( Object pA : cPart2.getAstPartAsmAttrs() ) {
										AstPartAsmAttr pAttr = (AstPartAsmAttr)pA;
										String pAttrNm = pAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm();
										if ( "serialnumber".equalsIgnoreCase(pAttrNm) ) {
											serialMB = pAttr.getValueTx();
										}
										else if ( "ipmimac".equalsIgnoreCase(pAttrNm) ) {
											ipmimacMB = pAttr.getValueTx();
										}
										else if ( "mac1".equalsIgnoreCase(pAttrNm) ) {
											mac1MB = pAttr.getValueTx();
										}
										else if ( "mac2".equalsIgnoreCase(pAttrNm) ) {
											mac2MB = pAttr.getValueTx();
										}
									}
									
									if ( serialMB != null ) {
										shopFloorValuesByPartSerialMap.put(serialMB, new HashMap<String, String>());
										shopFloorValuesByPartSerialMap.get(serialMB).put("ipmimac", ipmimacMB);
										shopFloorValuesByPartSerialMap.get(serialMB).put("mac1", mac1MB);
										shopFloorValuesByPartSerialMap.get(serialMB).put("mac2", mac2MB);
									}
								}
							}
						}
					}
					
				}
			}

			//Loop over ITS part records to retrieve SHOPFLOOR attribute values
			//Construct ITS query to get all parts for this bom
			SQLQuery itsPartQuery =  itsSession.createSQLQuery(
					getItsPartsByUnitDetailIdQuery
						.replaceFirst(":itsUnitDetailId:", itsUnitDetailId.toPlainString())
					);
			List itsPartList = itsPartQuery.list();
			if ( itsPartList == null || itsPartList.size() == 0 ) {
				throw new Exception("No parts found in ITS. Please contact support.");
			}
			
			int partIdx = 0;
			for ( Object obj : itsPartQuery.list()) {
				
				Object[] data = (Object[])obj;
				String itsPartMfgPartNumber = (String)data[0];
				
				//In ITS, mfg part number is actually just a TLA record id, for some parts returned. Sigh...
				if ( itsPartMfgPartNumber != null && StringUtils.isNumeric(itsPartMfgPartNumber) ) {
					TopLevelAssembly tla = this.topLevelAssemblyHome.findById(new BigDecimal(itsPartMfgPartNumber));
					if ( tla != null ) {
						itsPartMfgPartNumber = tla.getCustomerPartNo();
						data[0] = itsPartMfgPartNumber;
						itsPartList.set(partIdx, data);
					}
				}
				
				final String itsPartSerialNumber = (String)data[1];
				//final String itsPartDesc = (String)data[2];
				final String itsReqDesc = (String)data[3];
				final String itsReqVal = (String)data[4];				
				
				AstPart part = this.getAstPartByActivePartKeyAttributeValue(itsPartMfgPartNumber);
				
				if ( part != null ) { //Part found in CRUD?
					final String compType = part.getAstCompType().getTypeNm().toUpperCase();
				
					if ( touchLevel.intValue() == 1 && astChassisPartNumberList.contains(itsPartMfgPartNumber) ) {
						//This is mfr chassis data if 1st-touch assembly
						chassisPart = this.getAstPartByActivePartKeyAttributeValue(itsPartMfgPartNumber);
						shopFloorValuesChassisMap.put("serialnumber", itsPartSerialNumber);
					}
					else if ( touchLevel.intValue() == 2 && ("NIC".equals(compType) || "HDD".equals(compType) || "SSD".equals(compType) || "DIMM".equals(compType))  ) {
						//This is component part data for 2nd-touch of component type nic, hdd, ssd, or dimm
						BigDecimal lastFirstTouchAstPartAsmId = this.getLatestFirstTouchPartAsmIdBySerialNumber(itsPartSerialNumber);
						if ( lastFirstTouchAstPartAsmId == null ) {
							throw new Exception("Component was never part of a 1st-touch assembly: Type=" + compType + 
									", Part=" + itsPartMfgPartNumber + ", Serial=" + itsPartSerialNumber);
						}
						AstPartAsm firstTouchPartAsm = this.astPartAsmHome.findById(lastFirstTouchAstPartAsmId);
						if ( firstTouchPartAsm != null ) { //Check for null, since it is possible someone inserted a part that was never tested. Yes they should not do that!
							for( Object paaObj : firstTouchPartAsm.getAstPartAsmAttrs() ) {
								AstPartAsmAttr paa = (AstPartAsmAttr)paaObj;
								if ( "SHOPFLOOR".equals(paa.getAstPartAttr().getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
									if ( !shopFloorValuesByPartSerialMap.containsKey(itsPartSerialNumber) ) {
										shopFloorValuesByPartSerialMap.put(itsPartSerialNumber, new HashMap<String, String>());
									}
									shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put(
											paa.getAstPartAttr().getAstCompTypeAttr().getAttrNm(), 
											paa.getValueTx()
											);
								}
							}
						}
						else {
							throw new Exception("Component was never part of a 1st-touch assembly: Type=" + compType + 
									", Part=" + itsPartMfgPartNumber + ", Serial=" + itsPartSerialNumber);
						}
					}
					else if ( !submitChassisSerialNum.equalsIgnoreCase(itsPartSerialNumber) ) {
						//This is component part data for 1st-touch, or 2nd-touch not of component type nic, hdd, ssd, or dimm
						//If part exists in AST, get values according to component type
						if ( "MB".equals(compType) ) {
							if ( !shopFloorValuesByPartSerialMap.containsKey(itsPartSerialNumber) ) {
								shopFloorValuesByPartSerialMap.put(itsPartSerialNumber, new HashMap<String, String>());
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("ipmimac", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac1", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac2", "");
							}
							
							if ( "Node IPMI".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("ipmimac", itsReqVal);
							}
							else if ( "Node MAC 1".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac1", itsReqVal);
							}
							else if ( "Node MAC 2".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac2", itsReqVal);
							}
						}
						else if ( "NIC".equals(compType) ) {
							if ( !shopFloorValuesByPartSerialMap.containsKey(itsPartSerialNumber) ) {
								shopFloorValuesByPartSerialMap.put(itsPartSerialNumber, new HashMap<String, String>());
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac1", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac2", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac3", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac4", "");
							}
	
							if ( "MAC Address 1".equalsIgnoreCase(itsReqDesc) ) {
								//shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac1", itsReqVal);
								int portCount = 0;
								for ( Object pa : part.getAstPartAttrs() ) {
									AstPartAttr partAttr = (AstPartAttr)pa;
									if ( "portcount".equals(partAttr.getAstCompTypeAttr().getAttrNm()) ) {
										portCount = Integer.parseInt(partAttr.getValueTx());
										break;
									}
								}
								List<String> macList = Util.generateMacs(itsReqVal, portCount);
								int portIdx = 0;
								for ( String macString : macList ) {
									portIdx++;
									shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac" + String.valueOf(portIdx), macString);
								}
							}
							
							/* 
							 * Engineering team is no longer willing to capture all MAC addresses besides mac1,
							 * so now they are expecting us to find out how many ports the NIC has and increment
							 * and populate the remaining MAC addresses for them. Yes really they want that. Sure
							 * hope some engineer didnt make a bad assumption here.
							 
							else if ( "MAC Address 2".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac2", itsReqVal);
							}
							else if ( "MAC Address 3".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac3", itsReqVal);
							}
							else if ( "MAC Address 4".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("mac4", itsReqVal);
							}
							*/		
						}
						else if ( "HDD".equals(compType) || "SSD".equals(compType)  ) {
							if ( !shopFloorValuesByPartSerialMap.containsKey(itsPartSerialNumber) ) {
								shopFloorValuesByPartSerialMap.put(itsPartSerialNumber, new HashMap<String, String>());
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("psid", "");
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("msid", "");
							}
							
							if ( "PSID".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("psid", itsReqVal);
							}
							else if ( "MSID".equalsIgnoreCase(itsReqDesc) ) {
								shopFloorValuesByPartSerialMap.get(itsPartSerialNumber).put("msid", itsReqVal);
							}
						}
						//Already have and only need serial numbers for CPU, DIMM, HDD, PSU, SATA_DOM, SSD, and GPU
	
					}
				}
				partIdx++;
			}
			///////////////////////////////////////////////////////////////////////////////
			
			
			if ( persist ) trans = astSession.beginTransaction();
			Date now = new Date();
			String userId = user.getAvnetGlobalUserId();
		
			//AstPart chassisPart = this.getPartById(new BigDecimal(submitChassisPartId));
			//AstTempBom astBom = this.getTempAstBom(submitChassisSerialNum);
			
			chassisPartAsm = new AstPartAsm();
			chassisPartAsm.setPartAsmId(this.getNextPartAsmIdSequence());
			chassisPartAsm.setAstPart(chassisPart);
			chassisPartAsm.setCreateDt(now);
			chassisPartAsm.setCreateUsrId(userId);
			chassisPartAsm.setTouchLevel(touchLevel);
			
			if ( persist ) this.astPartAsmHome.persist(chassisPartAsm);
			
			JSONParser jsonParser = new JSONParser();
			Map jsonMap = (Map)jsonParser.parse(submitData, containerFactory);
			
			//Get the excluded parts list
			LinkedList jsonExcludedParts = (LinkedList)jsonMap.get("miscParts");
			for ( Object epObj : jsonExcludedParts ) {
				LinkedHashMap jsonExcludedPartMap = (LinkedHashMap)epObj;
				
				if ( jsonExcludedPartMap != null && jsonExcludedPartMap.size() > 0 ) {				
					String partNum = (String)jsonExcludedPartMap.get("partNum");
					//String serialNum = (String)jsonExcludedPartMap.get("serialNum");
					String comment = (String)jsonExcludedPartMap.get("comment");
					String partCount = (String)jsonExcludedPartMap.get("partCount");
					
					//Persist excluded parts here in AST_PART_ASM_EXCLUDED table
					AstPartAsmExcluded exPart = new AstPartAsmExcluded();
					exPart.setPartAsmExcludedId(getNextPartAsmExcludedIdSequence());
					exPart.setAstPartAsm(chassisPartAsm);
					exPart.setPartNumber(partNum);
					//exPart.setSerialNumber(serialNum);
					exPart.setReasonTx(comment);
					if ( partCount != null && partCount.length() > 0 ) {
						exPart.setPartCount(new BigDecimal(partCount));
					}
					if ( persist ) astPartAsmExcludedHome.persist(exPart);
				}
			}
			
		
			LinkedList jsonChassisAttrs = (LinkedList)jsonMap.get("chassisAttrs");
			for ( Object apaObj : chassisPart.getAstPartAttrs() ) {
				
				AstPartAttr astPartAttr = (AstPartAttr)apaObj;
				final String currentAstAttrName = astPartAttr.getAstCompTypeAttr().getAttrNm();
				
				if ( "Y".equals(astPartAttr.getAstCompTypeAttr().getActiveFl()) ) {
					if ( "USER".equals(astPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
						for ( Object caObj : jsonChassisAttrs ) {
							//System.out.println("********* chassisAttrs = " + caObj.toString());
							LinkedHashMap jsonChassisAttrMap = (LinkedHashMap)caObj;
							String attrNm = (String)jsonChassisAttrMap.get("attrNm");
							String valueTx = (String)jsonChassisAttrMap.get("valueTx");
		
							//System.out.println("*** " + key + " = " + jsonChassisAttrMap.get(key));
							if ( currentAstAttrName.equals(attrNm) ) {
								AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();								
								chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								chassisPartAsmAttr.setAstPartAsm(chassisPartAsm);
								chassisPartAsmAttr.setAstPartAttr(astPartAttr);
								chassisPartAsmAttr.setValueTx(valueTx);
								chassisPartAsmAttr.setCreateDt(now);
								chassisPartAsmAttr.setCreateUsrId(userId);
								chassisPartAsm.getAstPartAsmAttrs().add(chassisPartAsmAttr);
								if ( persist ) this.astPartAsmAttrHome.persist(chassisPartAsmAttr);
							}
						}
	
					}
					else if ( "CONFIG".equals(astPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
						AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();
						chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
						chassisPartAsmAttr.setAstPartAsm(chassisPartAsm);
						chassisPartAsmAttr.setAstPartAttr(astPartAttr);
						//if ( "PARTNUMBER".equalsIgnoreCase(currentAstAttrName) ) {
						//	chassisPartAsmAttr.setValueTx(submitChassisPartNum);
						//}
						//else {
						chassisPartAsmAttr.setValueTx(astPartAttr.getValueTx());
						chassisPartAsmAttr.setCreateDt(now);
						chassisPartAsmAttr.setCreateUsrId(userId);
						//}
						chassisPartAsm.getAstPartAsmAttrs().add(chassisPartAsmAttr);
						if ( persist ) this.astPartAsmAttrHome.persist(chassisPartAsmAttr);
					}
					else if ( "SHOPFLOOR".equals(astPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
						final String attrVal = shopFloorValuesChassisMap.get(currentAstAttrName);
						if ( attrVal != null ) {
							AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();
							chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
							chassisPartAsmAttr.setAstPartAsm(chassisPartAsm);
							chassisPartAsmAttr.setAstPartAttr(astPartAttr);
							chassisPartAsmAttr.setValueTx(attrVal);
							chassisPartAsmAttr.setCreateDt(now);
							chassisPartAsmAttr.setCreateUsrId(userId);
							chassisPartAsm.getAstPartAsmAttrs().add(chassisPartAsmAttr);
							if ( persist ) this.astPartAsmAttrHome.persist(chassisPartAsmAttr);
						}
					}
					else { //Create IST,FST,IMG attrs
						AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();
						chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
						chassisPartAsmAttr.setAstPartAsm(chassisPartAsm);
						chassisPartAsmAttr.setAstPartAttr(astPartAttr);
						chassisPartAsmAttr.setValueTx(astPartAttr.getValueTx());
						chassisPartAsmAttr.setCreateDt(now);
						chassisPartAsmAttr.setCreateUsrId(userId);
						chassisPartAsm.getAstPartAsmAttrs().add(chassisPartAsmAttr);
						if ( persist ) this.astPartAsmAttrHome.persist(chassisPartAsmAttr);
					}
				}
			}
	
			//Get the chassis parts and their attributes
			LinkedList jsonChassisParts = (LinkedList)jsonMap.get("chassisParts");
			for ( Object cpObj : jsonChassisParts ) {
				//System.out.println("********* jsonChassisParts = " + cpObj.toString());
				LinkedHashMap jsonChassisPartMap = (LinkedHashMap)cpObj;
				
				if ( jsonChassisPartMap != null && jsonChassisPartMap.size() > 0 ) {
					
					BigDecimal chassisPartId = new BigDecimal((String)jsonChassisPartMap.get("partId"));
					String chassisPartPartNum = (String)jsonChassisPartMap.get("partNum");
					String chassisPartSerialNum = (String)jsonChassisPartMap.get("serialNum");
					
					AstPart chassisChildPart = this.getPartById(chassisPartId);
					AstPartAsm chassisChildPartAsm = new AstPartAsm();
					chassisChildPartAsm.setCreateDt(now);
					chassisChildPartAsm.setCreateUsrId(userId);
					chassisChildPartAsm.setAstPartAsm(chassisPartAsm); ////chassisPartAsm as parent
					chassisChildPartAsm.setPartAsmId(this.getNextPartAsmIdSequence());
					chassisChildPartAsm.setAstPart(chassisChildPart);
					chassisChildPartAsm.setTouchLevel(touchLevel);
					if ( persist ) this.astPartAsmHome.persist(chassisChildPartAsm);
					
					for ( Object ccpaObj : chassisChildPart.getAstPartAttrs() ) {
						
						AstPartAttr chassisChildPartAttr = (AstPartAttr)ccpaObj;
						final String currentAstAttrName = chassisChildPartAttr.getAstCompTypeAttr().getAttrNm();
						
						if ( "Y".equals(chassisChildPartAttr.getAstCompTypeAttr().getActiveFl()) ) {
							if ( "USER".equals(chassisChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
								for ( String ccpkey : (String[])jsonChassisPartMap.keySet().toArray(new String[jsonChassisPartMap.keySet().size()]) ) {
									//System.out.println("*** " + key + " = " + chassisPartMap.get(key));
									if ( NumberUtils.isNumber(ccpkey) ) {
										LinkedHashMap jsonChassisPartAttrsMap = (LinkedHashMap)jsonChassisPartMap.get(ccpkey);
										String attrNm = (String)jsonChassisPartAttrsMap.get("attrNm");
										String valueTx = (String)jsonChassisPartAttrsMap.get("valueTx");
			
										if ( currentAstAttrName.equals(attrNm) ) {
											AstPartAsmAttr chassisChildPartAsmAttr = new AstPartAsmAttr();
											chassisChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
											chassisChildPartAsmAttr.setAstPartAsm(chassisChildPartAsm);
											chassisChildPartAsmAttr.setAstPartAttr(chassisChildPartAttr);
											chassisChildPartAsmAttr.setValueTx(valueTx);
											chassisChildPartAsmAttr.setCreateDt(now);
											chassisChildPartAsmAttr.setCreateUsrId(userId);
											chassisChildPartAsm.getAstPartAsmAttrs().add(chassisChildPartAsmAttr);
											if ( persist ) this.astPartAsmAttrHome.persist(chassisChildPartAsmAttr);
										}
	
									}
									//else {
									//	System.out.println("*** PART DATA: " + key + " = " + chassisPartMap.get(key));
									//}
								}
							}
							else if ( "CONFIG".equals(chassisChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
								AstPartAsmAttr chassisChildPartAsmAttr = new AstPartAsmAttr();
								chassisChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								chassisChildPartAsmAttr.setAstPartAsm(chassisChildPartAsm);
								chassisChildPartAsmAttr.setAstPartAttr(chassisChildPartAttr);
								if ( "PARTNUMBER".equalsIgnoreCase(currentAstAttrName) ) {
									chassisChildPartAsmAttr.setValueTx(chassisPartPartNum);
								}
								else {
									chassisChildPartAsmAttr.setValueTx(chassisChildPartAttr.getValueTx());
								}
								chassisChildPartAsmAttr.setCreateDt(now);
								chassisChildPartAsmAttr.setCreateUsrId(userId);
								chassisChildPartAsm.getAstPartAsmAttrs().add(chassisChildPartAsmAttr);
								if ( persist ) this.astPartAsmAttrHome.persist(chassisChildPartAsmAttr);
							}
							else if ( "SHOPFLOOR".equals(chassisChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {							
								AstPartAsmAttr chassisChildPartAsmAttr = new AstPartAsmAttr();
								chassisChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								chassisChildPartAsmAttr.setAstPartAsm(chassisChildPartAsm);
								chassisChildPartAsmAttr.setAstPartAttr(chassisChildPartAttr);
								String val = null;
								if ( "SERIALNUMBER".equalsIgnoreCase(currentAstAttrName) ) {
									val = chassisPartSerialNum;
								}
								else {
									val = shopFloorValuesByPartSerialMap.get(chassisPartSerialNum).get(currentAstAttrName);
								}
								chassisChildPartAsmAttr.setValueTx(val);
								chassisChildPartAsmAttr.setCreateDt(now);
								chassisChildPartAsmAttr.setCreateUsrId(userId);
								chassisChildPartAsm.getAstPartAsmAttrs().add(chassisChildPartAsmAttr);
								if ( persist ) this.astPartAsmAttrHome.persist(chassisChildPartAsmAttr);
							}
							else { //Create IST,FST,IMG attrs
								AstPartAsmAttr chassisChildPartAsmAttr = new AstPartAsmAttr();
								chassisChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								chassisChildPartAsmAttr.setAstPartAsm(chassisChildPartAsm);
								chassisChildPartAsmAttr.setAstPartAttr(chassisChildPartAttr);
								chassisChildPartAsmAttr.setValueTx(chassisChildPartAttr.getValueTx());
								chassisChildPartAsmAttr.setCreateDt(now);
								chassisChildPartAsmAttr.setCreateUsrId(userId);
								chassisChildPartAsm.getAstPartAsmAttrs().add(chassisChildPartAsmAttr);
								if ( persist ) this.astPartAsmAttrHome.persist(chassisChildPartAsmAttr);
							}
						}
					}
					chassisPartAsm.getAstPartAsms().add(chassisChildPartAsm);
				}
			}
		
			
			LinkedList jsonNodeData = (LinkedList)jsonMap.get("nodeData");
			if ( jsonNodeData != null && jsonNodeData.size() > 0 ) { //Has nodes?
				
				for ( Object jnObj : jsonNodeData ) { //loop over nodes
					LinkedHashMap jsonNodeDataMap = (LinkedHashMap)jnObj; 
					
					AstPartAsm nodePartAsm = new AstPartAsm();
					nodePartAsm.setCreateDt(now);
					nodePartAsm.setCreateUsrId(userId);
					nodePartAsm.setPartAsmId(this.getNextPartAsmIdSequence());
					nodePartAsm.setAstPartAsm(chassisPartAsm); ////chassisPartAsm as parent
					nodePartAsm.setAstPart(dummyAstNodePart);
					nodePartAsm.setTouchLevel(touchLevel);
					if ( persist ) this.astPartAsmHome.persist(nodePartAsm);
					
					for ( Object dnpaObj : dummyAstNodePart.getAstPartAttrs() ) { //Loop over configured node attrs
						AstPartAttr nodeAstPartAttr = (AstPartAttr)dnpaObj;
						final String currentAstAttrName = nodeAstPartAttr.getAstCompTypeAttr().getAttrNm();
						
						if ( "Y".equals(nodeAstPartAttr.getAstCompTypeAttr().getActiveFl()) ) {
							if ( "USER".equals(nodeAstPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
								
								LinkedList jsonNodeAttrs = (LinkedList)jsonNodeDataMap.get("nodeAttrs");
								for ( Object naObj : jsonNodeAttrs ) {
									//System.out.println("********* nodeAttrs = " + naObj.toString());
									LinkedHashMap jsonNodeAttrMap = (LinkedHashMap)naObj;
									String attrNm = (String)jsonNodeAttrMap.get("attrNm");
									String valueTx = (String)jsonNodeAttrMap.get("valueTx");
									
									if ( currentAstAttrName.equals(attrNm) ) {
										//System.out.println("*** " + key + " = " + jsonNodeAttrMap.get(key));
										AstPartAsmAttr nodePartAsmAttr = new AstPartAsmAttr();
										nodePartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
										nodePartAsmAttr.setAstPartAsm(nodePartAsm);
										nodePartAsmAttr.setAstPartAttr(nodeAstPartAttr);
										nodePartAsmAttr.setValueTx(valueTx);
										nodePartAsmAttr.setCreateDt(now);
										nodePartAsmAttr.setCreateUsrId(userId);
										nodePartAsm.getAstPartAsmAttrs().add(nodePartAsmAttr);	
										if ( persist ) this.astPartAsmAttrHome.persist(nodePartAsmAttr);
									}
								}
							}
							else { //Create IST,FST,IMG attrs as well as CONFIG
								//if ( "CONFIG".equals(nodeAstPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
								AstPartAsmAttr nodePartAsmAttr = new AstPartAsmAttr();
								nodePartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								nodePartAsmAttr.setAstPartAsm(nodePartAsm);
								nodePartAsmAttr.setAstPartAttr(nodeAstPartAttr);
								nodePartAsmAttr.setValueTx(nodeAstPartAttr.getValueTx());	
								nodePartAsmAttr.setCreateDt(now);
								nodePartAsmAttr.setCreateUsrId(userId);
								nodePartAsm.getAstPartAsmAttrs().add(nodePartAsmAttr);
								if ( persist ) this.astPartAsmAttrHome.persist(nodePartAsmAttr);
							}
							//No serial number for node
						}
					} //End loop over configured node attrs
		
					LinkedList jsonNodeParts = (LinkedList)jsonNodeDataMap.get("nodeParts");
					for ( Object npObj : jsonNodeParts ) { 
						//System.out.println("********* nodeParts = " + npObj.toString());
						LinkedHashMap jsonNodeChildPartDataMap = (LinkedHashMap)npObj;
						
						BigDecimal nodePartId = new BigDecimal((String)jsonNodeChildPartDataMap.get("partId"));
						String nodePartPartNum = (String)jsonNodeChildPartDataMap.get("partNum");
						String nodePartSerialNum = (String)jsonNodeChildPartDataMap.get("serialNum");
						
						AstPart nodeChildPart = this.getPartById(nodePartId);
						AstPartAsm nodeChildPartAsm = new AstPartAsm();
						nodeChildPartAsm.setCreateDt(now);
						nodeChildPartAsm.setCreateUsrId(userId);
						nodeChildPartAsm.setPartAsmId(this.getNextPartAsmIdSequence());
						nodeChildPartAsm.setAstPartAsm(nodePartAsm); //nodePartAsm as parent
						nodeChildPartAsm.setAstPart(nodeChildPart);
						nodeChildPartAsm.setTouchLevel(touchLevel);
						if ( persist ) this.astPartAsmHome.persist(nodeChildPartAsm);
		
						for ( Object cnpaObj : nodeChildPart.getAstPartAttrs() ) {
							AstPartAttr nodeChildPartAttr = (AstPartAttr)cnpaObj;
							final String currentAstAttrName = nodeChildPartAttr.getAstCompTypeAttr().getAttrNm();
							
							if ( "Y".equals(nodeChildPartAttr.getAstCompTypeAttr().getActiveFl()) ) {
								if ( "USER".equals(nodeChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
									for ( String key : (String[])jsonNodeChildPartDataMap.keySet().toArray(new String[jsonNodeChildPartDataMap.keySet().size()]) ) {
										//System.out.println("*** NODE PART: " + key + " = " + jsonNodeChildPartDataMap.get(key));
										if ( NumberUtils.isNumber(key) ) {
	
											LinkedHashMap jsonNodePartAttrsMap = (LinkedHashMap)jsonNodeChildPartDataMap.get(key);
											String attrNm = (String)jsonNodePartAttrsMap.get("attrNm");
											String valueTx = (String)jsonNodePartAttrsMap.get("valueTx");
												
											if ( currentAstAttrName.equals(attrNm) ) {
												//System.out.println("***** NODE PART USR ATTRS: " + attrNm + " = " + valueTx);
												AstPartAsmAttr nodeChildPartAsmAttr = new AstPartAsmAttr();
												nodeChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
												nodeChildPartAsmAttr.setAstPartAsm(nodeChildPartAsm);
												nodeChildPartAsmAttr.setAstPartAttr(nodeChildPartAttr);
												nodeChildPartAsmAttr.setValueTx(valueTx);
												nodeChildPartAsmAttr.setCreateDt(now);
												nodeChildPartAsmAttr.setCreateUsrId(userId);
												nodeChildPartAsm.getAstPartAsmAttrs().add(nodeChildPartAsmAttr);
												if ( persist ) this.astPartAsmAttrHome.persist(nodeChildPartAsmAttr);
											}
										}
										//else {
										//	System.out.println("***** NODE PART DATA: " + key + " = " + jsonNodeChildPartDataMap.get(key));
										//}
									}
								}
								else if ( "CONFIG".equals(nodeChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
									AstPartAsmAttr nodeChildPartAsmAttr = new AstPartAsmAttr();
									nodeChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
									nodeChildPartAsmAttr.setAstPartAsm(nodeChildPartAsm);
									nodeChildPartAsmAttr.setAstPartAttr(nodeChildPartAttr);
									if ( "PARTNUMBER".equalsIgnoreCase(currentAstAttrName) ) {
										nodeChildPartAsmAttr.setValueTx(nodePartPartNum);
									}
									else {
										nodeChildPartAsmAttr.setValueTx(nodeChildPartAttr.getValueTx());
									}
									nodeChildPartAsmAttr.setCreateDt(now);
									nodeChildPartAsmAttr.setCreateUsrId(userId);
									nodeChildPartAsm.getAstPartAsmAttrs().add(nodeChildPartAsmAttr);
									if ( persist ) this.astPartAsmAttrHome.persist(nodeChildPartAsmAttr);
								}
								else if ( "SHOPFLOOR".equals(nodeChildPartAttr.getAstCompTypeAttr().getAstDataSource().getSourceNm()) ) {
									AstPartAsmAttr nodeChildPartAsmAttr = new AstPartAsmAttr();
									nodeChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
									nodeChildPartAsmAttr.setAstPartAsm(nodeChildPartAsm);
									nodeChildPartAsmAttr.setAstPartAttr(nodeChildPartAttr);
									String val = null;
									if ( "SERIALNUMBER".equalsIgnoreCase(currentAstAttrName) ) {
										val = nodePartSerialNum;
									}
									else {
										val = shopFloorValuesByPartSerialMap.get(nodePartSerialNum).get(currentAstAttrName);
										
										//if ( !persist && currentAstAttrName.startsWith("mac") ) {
										//	log.info("************** " + currentAstAttrName + " = " + val);
										//}
									}
									nodeChildPartAsmAttr.setValueTx(val);
									nodeChildPartAsmAttr.setCreateDt(now);
									nodeChildPartAsmAttr.setCreateUsrId(userId);
									nodeChildPartAsm.getAstPartAsmAttrs().add(nodeChildPartAsmAttr);
									if ( persist ) this.astPartAsmAttrHome.persist(nodeChildPartAsmAttr);
								}
								else { //Create IST,FST,IMG attrs
									AstPartAsmAttr nodeChildPartAsmAttr = new AstPartAsmAttr();
									nodeChildPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
									nodeChildPartAsmAttr.setAstPartAsm(nodeChildPartAsm);
									nodeChildPartAsmAttr.setAstPartAttr(nodeChildPartAttr);
									nodeChildPartAsmAttr.setValueTx(nodeChildPartAttr.getValueTx());
									nodeChildPartAsmAttr.setCreateDt(now);
									nodeChildPartAsmAttr.setCreateUsrId(userId);
									nodeChildPartAsm.getAstPartAsmAttrs().add(nodeChildPartAsmAttr);
									if ( persist ) this.astPartAsmAttrHome.persist(nodeChildPartAsmAttr);
								}
							}
						}
						nodePartAsm.getAstPartAsms().add(nodeChildPartAsm);
					} //End loop over nodes
					
					chassisPartAsm.getAstPartAsms().add(nodePartAsm);
				}			
			} //End has nodes	
			
			if ( persist ) trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback();
			throw e;
		}
		return chassisPartAsm;
		
		/*
		System.out.println("\n\n");
		System.out.println("------------Chassis: ");
		System.out.println("partid=" + chassisPartAsm.getAstPart().getPartId());
		System.out.println("type=" + chassisPartAsm.getAstPart().getAstCompType().getTypeNm());
		System.out.println("----------Chassis Attrs: ");
		for ( Object o : chassisPartAsm.getAstPartAsmAttrs() ) {
			AstPartAsmAttr cpa = (AstPartAsmAttr)o;
			System.out.println("name=" + cpa.getAstPartAttr().getAstCompTypeAttr().getAttrNm() + ", value=" + cpa.getValueTx());
		}
		System.out.println("\n\n");
		System.out.println("--------Chassis Parts: ");
		for ( Object o : chassisPartAsm.getAstPartAsms() ) {
			System.out.println("\n\n");
			AstPartAsm cp = (AstPartAsm)o;
			System.out.println("------Chassis Part: ");
			System.out.println("partid=" + cp.getAstPart().getPartId());
			System.out.println("type=" + cp.getAstPart().getAstCompType().getTypeNm());
			System.out.println("\n\n");

			System.out.println("------Chassis Part Attrs: ");
			for ( Object ao : cp.getAstPartAsmAttrs() ) {
				AstPartAsmAttr cpa = (AstPartAsmAttr)ao;
				System.out.println("name=" + cpa.getAstPartAttr().getAstCompTypeAttr().getAttrNm() + ", value=" + cpa.getValueTx());
			}
			
			
			System.out.println("--------Part Parts: ");
			for ( Object o2 : cp.getAstPartAsms() ) {
				System.out.println("\n\n");
				AstPartAsm cp2 = (AstPartAsm)o2;
				System.out.println("------Part Part: ");
				System.out.println("partid=" + cp2.getAstPart().getPartId());
				System.out.println("type=" + cp2.getAstPart().getAstCompType().getTypeNm());
				System.out.println("\n\n");

				System.out.println("------Part Part Attrs: ");
				for ( Object ao2 : cp2.getAstPartAsmAttrs() ) {
					AstPartAsmAttr cpa2 = (AstPartAsmAttr)ao2;
					System.out.println("name=" + cpa2.getAstPartAttr().getAstCompTypeAttr().getAttrNm() + ", value=" + cpa2.getValueTx());
				}
			}
		}
		System.out.println("\n\n");
		*/
	}
	

	@SuppressWarnings("unchecked")
	public AstPartAsm saveAssemblyEditFromJsonString(
										String submitData, 
										String submitChassisSerialNum,
										AstPartAsm chassisPartAsm,
										SecurityUser user) throws Exception {
		ContainerFactory containerFactory = new ContainerFactory(){
			public List creatArrayContainer() {
			  return new LinkedList();
			}
			public Map createObjectContainer() {
			  return new LinkedHashMap();
			}                       
		};
		
		AstPart dummyAstNodePart = this.getAstPartByActivePartKeyAttributeValue("NODE");
		if ( dummyAstNodePart == null ) {
			throw new Exception("Dummy node part with part key value of 'NODE' not found. Please check part configuration.");
		}
	
		Transaction trans = null; 
		try {
			Session sess = this.getALAPPSSession();
			trans = sess.beginTransaction(); 
			Date now = new Date();
			String userId = user.getAvnetGlobalUserId();
	
			JSONParser jsonParser = new JSONParser();
			Map jsonMap = (Map)jsonParser.parse(submitData, containerFactory);

			LinkedList jsonChassisAttrs = (LinkedList)jsonMap.get("chassisAttrs");
			for ( Object caObj : jsonChassisAttrs ) {
				LinkedHashMap jsonChassisAttrMap = (LinkedHashMap)caObj;
				//String attrNm = (String)jsonChassisAttrMap.get("attrNm");
				String valueTx = (String)jsonChassisAttrMap.get("valueTx");
				//String compTypeAttrId = (String)jsonChassisAttrMap.get("compTypeAttrId");
				String partAsmAttrId = (String)jsonChassisAttrMap.get("partAsmAttrId");
				String partAttrId = (String)jsonChassisAttrMap.get("partAttrId");
	
				boolean chassisAttrFound = false;
				for ( Object paaObj : chassisPartAsm.getAstPartAsmAttrs() ) {
					AstPartAsmAttr astPartAsmAttr = (AstPartAsmAttr)paaObj;
					if ( partAsmAttrId.equals(astPartAsmAttr.getPartAsmAttrId().toPlainString()) ) {
						chassisAttrFound = true;
						astPartAsmAttr.setValueTx(valueTx);
						astPartAsmAttr.setUpdateDt(now);
						astPartAsmAttr.setUpdateUsrId(userId);
						/*
						log.info("*** OLD CHASSIS ATTR: " +
								astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
								"=" + valueTx
								);
						*/
						this.astPartAsmAttrHome.merge(astPartAsmAttr); 
					}
				}
				if  ( !chassisAttrFound ) {
					AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();								
					chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
					chassisPartAsmAttr.setAstPartAsm(chassisPartAsm);
					AstPartAttr astPartAttr = this.astPartAttrHome.findById(new BigDecimal(partAttrId));
					chassisPartAsmAttr.setAstPartAttr(astPartAttr);	
					chassisPartAsmAttr.setValueTx(valueTx);
					chassisPartAsmAttr.setCreateDt(now);
					chassisPartAsmAttr.setCreateUsrId(userId);
					/*
					log.info("*** NEW CHASSIS ATTR: " +
							chassisPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
							"=" + valueTx
							);
					*/
					this.astPartAsmAttrHome.persist(chassisPartAsmAttr); 
				}
			}
	
			//Get the chassis parts and their attributes
			LinkedList jsonChassisParts = (LinkedList)jsonMap.get("chassisParts");
			for ( Object cpObj : jsonChassisParts ) {
				//System.out.println("********* jsonChassisParts = " + cpObj.toString());
				LinkedHashMap jsonChassisPartMap = (LinkedHashMap)cpObj;	
				if ( jsonChassisPartMap != null && jsonChassisPartMap.size() > 0 ) {
					BigDecimal partAsmId = new BigDecimal((String)jsonChassisPartMap.get("partAsmId"));
					AstPartAsm chassisChildPartAsm = this.astPartAsmHome.findById(partAsmId);
					for ( String ccpkey : (String[])jsonChassisPartMap.keySet().toArray(new String[jsonChassisPartMap.keySet().size()]) ) {
						//System.out.println("*** " + key + " = " + chassisPartMap.get(key));
						if ( NumberUtils.isNumber(ccpkey) ) {
							LinkedHashMap jsonChassisPartAttrsMap = (LinkedHashMap)jsonChassisPartMap.get(ccpkey);
							String valueTx = (String)jsonChassisPartAttrsMap.get("valueTx");
							String partAsmAttrId = (String)jsonChassisPartAttrsMap.get("partAsmAttrId");
							String partAttrId = (String)jsonChassisPartAttrsMap.get("partAttrId");

							boolean chassisPartAttrFound = false;
							for ( Object paObj : chassisChildPartAsm.getAstPartAsmAttrs() ) {
								AstPartAsmAttr astPartAsmAttr = (AstPartAsmAttr)paObj;
								if ( partAsmAttrId.equals(astPartAsmAttr.getPartAsmAttrId().toPlainString()) ) {
									chassisPartAttrFound = true;
									astPartAsmAttr.setValueTx(valueTx);
									astPartAsmAttr.setUpdateDt(now);
									astPartAsmAttr.setUpdateUsrId(userId);
									/*
									log.info("*** OLD CHASSIS CHILD PART ATTR: " +
											astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
											"=" + valueTx
											);
									*/
									this.astPartAsmAttrHome.merge(astPartAsmAttr); 
								}
							}
							if  ( !chassisPartAttrFound ) {
								AstPartAsmAttr chassisPartAsmAttr = new AstPartAsmAttr();								
								chassisPartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
								chassisPartAsmAttr.setAstPartAsm(chassisChildPartAsm);
								AstPartAttr astPartAttr = this.astPartAttrHome.findById(new BigDecimal(partAttrId));
								chassisPartAsmAttr.setAstPartAttr(astPartAttr);	
								chassisPartAsmAttr.setValueTx(valueTx);
								chassisPartAsmAttr.setCreateDt(now);
								chassisPartAsmAttr.setCreateUsrId(userId);
								/*
								log.info("*** NEW CHASSIS CHILD PART ATTR: " +
										chassisPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
										"=" + valueTx
										);
								*/
								this.astPartAsmAttrHome.persist(chassisPartAsmAttr); 
							}						
						}
						//else {
						//	System.out.println("*** EDIT CHASSIS CHILD PART DATA: " + ccpkey + " = " + jsonChassisPartMap.get(ccpkey));
						//}
					}	
				}
			}

			LinkedList jsonNodeData = (LinkedList)jsonMap.get("nodeData");
			if ( jsonNodeData != null && jsonNodeData.size() > 0 ) { //Has nodes?
				
				for ( Object jnObj : jsonNodeData ) { //loop over nodes
					LinkedHashMap jsonNodeDataMap = (LinkedHashMap)jnObj; 
					LinkedList jsonNodeAttrs = (LinkedList)jsonNodeDataMap.get("nodeAttrs");
					for ( Object naObj : jsonNodeAttrs ) {
						//System.out.println("********* nodeAttrs = " + naObj.toString());
						LinkedHashMap jsonNodeAttrMap = (LinkedHashMap)naObj;
						String valueTx = (String)jsonNodeAttrMap.get("valueTx");
						String partAsmAttrId = (String)jsonNodeAttrMap.get("partAsmAttrId");
						String partAttrId = (String)jsonNodeAttrMap.get("partAttrId");
						BigDecimal partAsmId = new BigDecimal((String)jsonNodeAttrMap.get("partAsmId"));
		
						AstPartAsm nodePartAsm = this.astPartAsmHome.findById(partAsmId);
						
						boolean nodeAttrFound = false;
						for ( Object paObj : nodePartAsm.getAstPartAsmAttrs() ) {
							AstPartAsmAttr astPartAsmAttr = (AstPartAsmAttr)paObj;
							if ( partAsmAttrId.equals(astPartAsmAttr.getPartAsmAttrId().toPlainString()) ) {
								nodeAttrFound = true;
								astPartAsmAttr.setValueTx(valueTx);
								astPartAsmAttr.setUpdateUsrId(userId);
								astPartAsmAttr.setUpdateDt(now);
								/*
								log.info("*** OLD NODE ATTR: " +
										astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
										"=" + valueTx
										);
								*/
								this.astPartAsmAttrHome.merge(astPartAsmAttr); 
							}
						}
						if  ( !nodeAttrFound ) {
							AstPartAsmAttr nodePartAsmAttr = new AstPartAsmAttr();								
							nodePartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
							nodePartAsmAttr.setAstPartAsm(nodePartAsm);
							AstPartAttr astPartAttr = this.astPartAttrHome.findById(new BigDecimal(partAttrId));
							nodePartAsmAttr.setAstPartAttr(astPartAttr);	
							nodePartAsmAttr.setValueTx(valueTx);
							nodePartAsmAttr.setCreateDt(now);
							nodePartAsmAttr.setCreateUsrId(userId);
							/*
							log.info("*** NEW NODE ATTR: " +
									nodePartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
									"=" + valueTx
									);
							*/
							this.astPartAsmAttrHome.persist(nodePartAsmAttr); 
						}	
					}
		
					LinkedList jsonNodeParts = (LinkedList)jsonNodeDataMap.get("nodeParts");
					for ( Object npObj : jsonNodeParts ) { 
						//System.out.println("********* nodeParts = " + npObj.toString());
						LinkedHashMap jsonNodeChildPartDataMap = (LinkedHashMap)npObj;
						for ( String key : (String[])jsonNodeChildPartDataMap.keySet().toArray(new String[jsonNodeChildPartDataMap.keySet().size()]) ) {
							//System.out.println("*** NODE PART: " + key + " = " + jsonNodeChildPartDataMap.get(key));
							if ( NumberUtils.isNumber(key) ) {

								LinkedHashMap jsonNodePartAttrsMap = (LinkedHashMap)jsonNodeChildPartDataMap.get(key);
								String valueTx = (String)jsonNodePartAttrsMap.get("valueTx");
								String partAsmAttrId = (String)jsonNodePartAttrsMap.get("partAsmAttrId");
								String partAttrId = (String)jsonNodePartAttrsMap.get("partAttrId");
								BigDecimal nodeChildPartAsmId = new BigDecimal((String)jsonNodePartAttrsMap.get("partAsmId"));
								AstPartAsm nodeChildPartAsm = this.astPartAsmHome.findById(nodeChildPartAsmId);
								
								
								boolean nodePartAttrFound = false;
								for ( Object paObj : nodeChildPartAsm.getAstPartAsmAttrs() ) {
									AstPartAsmAttr astPartAsmAttr = (AstPartAsmAttr)paObj;
									if ( partAsmAttrId.equals(astPartAsmAttr.getPartAsmAttrId().toPlainString()) ) {
										nodePartAttrFound = true;
										astPartAsmAttr.setValueTx(valueTx);
										astPartAsmAttr.setCreateDt(now);
										astPartAsmAttr.setCreateUsrId(userId);
										/*
										log.info("*** OLD NODE CHILD PART ATTR: " +
												astPartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
												"=" + valueTx
												);
										*/
										this.astPartAsmAttrHome.merge(astPartAsmAttr); 
									}
								}
								if  ( !nodePartAttrFound ) {
									AstPartAsmAttr nodePartAsmAttr = new AstPartAsmAttr();								
									nodePartAsmAttr.setPartAsmAttrId(this.getNextPartAsmAttrIdSequence());
									nodePartAsmAttr.setAstPartAsm(nodeChildPartAsm);
									AstPartAttr astPartAttr = this.astPartAttrHome.findById(new BigDecimal(partAttrId));
									nodePartAsmAttr.setAstPartAttr(astPartAttr);	
									nodePartAsmAttr.setValueTx(valueTx);
									nodePartAsmAttr.setCreateDt(now);
									nodePartAsmAttr.setCreateUsrId(userId);
									/*
									log.info("*** NEW NODE CHILD PART ATTR: " +
											nodePartAsmAttr.getAstPartAttr().getAstCompTypeAttr().getAttrNm() +
											"=" + valueTx
											);
											*/
									this.astPartAsmAttrHome.persist(nodePartAsmAttr); 
								}
							}
							//else {
							//	System.out.println("***** NODE CHILD PART DATA: " + key + " = " + jsonNodeChildPartDataMap.get(key));
							//}
						}
					} //End loop over nodes
				}			
			} //End has nodes	
			trans.commit();
		}
		catch ( Exception e ) {
			if ( trans != null ) trans.rollback(); 
			throw e;
		}
		return chassisPartAsm;
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<BigDecimal, Date> getChassisPartAsmIdsWithCreateDateBySerialNumber(String chassisSerialNumber) {
		Session sess = this.getALAPPSSession();
		Map<BigDecimal, Date> returnMap = null;
		SQLQuery query = sess.createSQLQuery(
				getChassisPartAsmIdsBySerialNumberQuery.replaceFirst(":chassisSerialNumber:", chassisSerialNumber)
				);
		List results = query.list();
		if ( results != null ) {
			returnMap = new LinkedHashMap<BigDecimal, Date>();
			for ( Object result : results ) {
				Object[] row = (Object[])result;
				returnMap.put((BigDecimal)row[0], (Date)row[1]);
			}
		}
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public BigDecimal getLatestFirstTouchPartAsmIdBySerialNumber(String serialNumber) {
		Session sess = this.getALAPPSSession();
		SQLQuery query = sess.createSQLQuery(
				this.getLatestFirstTouchPartAsmIdBySerialNum.replaceFirst(":serialNum:", serialNumber)
				);
		List results = query.list();
		return results == null ? null : (BigDecimal)results.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<BigDecimal, BigDecimal> getChassisPartAsmIdsWithTouchLevelBySerialNumber(String chassisSerialNumber) {
		Session sess = this.getALAPPSSession();
		Map<BigDecimal, BigDecimal> returnMap = null;
		SQLQuery query = sess.createSQLQuery(
				getChassisPartAsmIdsBySerialNumberQuery.replaceFirst(":chassisSerialNumber:", chassisSerialNumber)
				);
		List results = query.list();
		if ( results != null ) {
			returnMap = new LinkedHashMap<BigDecimal, BigDecimal>();
			for ( Object result : results ) {
				Object[] row = (Object[])result;
				returnMap.put((BigDecimal)row[0], (BigDecimal)row[2]);
			}
		}
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<BigDecimal, Date> getChassisPartAsmIdsByICN(String icn) {
		Session sess = this.getALAPPSSession();
		Map<BigDecimal, Date> returnMap = null;
		SQLQuery query = sess.createSQLQuery(
				getChassisPartAsmIdsByICNQuery.replaceFirst(":icn:", icn)
				);
		List results = query.list();
		if ( results != null ) {
			returnMap = new LinkedHashMap<BigDecimal, Date>();
			for ( Object result : results ) {
				Object[] row = (Object[])result;
				returnMap.put((BigDecimal)row[0], (Date)row[1]);
			}
		}
		return returnMap;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Map<BigDecimal, Date> getChassisPartAsmIdsByICNList(List<Integration> integrationList) throws Exception {
		
		if ( integrationList == null || integrationList.size() == 0 ) {
			return null;
		}
		
		Session sess = this.getALAPPSSession();
		Map<BigDecimal, Date> returnMap = null;
		
		for ( Integration integration : integrationList ) {
			SQLQuery query = sess.createSQLQuery(
					getChassisPartAsmIdsByICNQuery.replaceFirst(":icn:", integration.getId().getItsInvoiceNo())
					);
			List results = query.list();
			if ( results == null ) {
				throw new Exception("No assemblies found for SCN: " + integration.getId().getItsScn() + ", ICN: " + integration.getId().getItsInvoiceNo());
			}
			else {
				if ( returnMap == null ) {
					returnMap = new LinkedHashMap<BigDecimal, Date>();
				}
				
				for ( Object result : results ) {
					Object[] row = (Object[])result;
					returnMap.put((BigDecimal)row[0], (Date)row[1]);
				}
			}
		}
		return returnMap;
	}
	
	@Transactional(readOnly = true)
	public AstTempBom getTempAstBom(String serialNum) {
		Criteria q = this.getALAPPSSession().createCriteria(AstTempBom.class);
		q.add(Restrictions.eq("serialNum", serialNum));
		return (AstTempBom) q.uniqueResult();
	}
	
	/*
	 * ********************************************
	 * ITS Part
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public List<ItsPart> get2ndTouchItsPartsByICN(String icn) {
		List<ItsPart> returnList = new ArrayList<ItsPart>();
		Criteria q = this.getDBCONNECTSSession().createCriteria(ItsPart.class);
		q.add(Restrictions.eq("itsInvoiceNo", icn))
		//.add(Restrictions.ne("itsPartMfgcode", "AAA")) //No longer with EVOLVE
		//.add(Restrictions.ne("itsPartMfgcode", "FGF"))
		.add(Restrictions.eq("componentUom", "EA"))
		.addOrder(Order.asc("itsPartMfgpartno"));
		List<ItsPart> list = (List<ItsPart>)q.list();
		if ( list != null ) {
			for ( ItsPart p : list ) {
				String mfg = p.getItsPartMfgpartno();
				if ( mfg != null && mfg.length() < 7 && StringUtils.isNumeric(mfg) ) {
					Criteria tq = this.getDBCONNECTSSession().createCriteria(TopLevelAssembly.class);
					tq.add(Restrictions.eq("topLevelAssemblyId", new BigDecimal(p.getItsPartMfgpartno())));
					TopLevelAssembly tla = (TopLevelAssembly)tq.uniqueResult();
					if ( tla != null ) {
						p.setItsPartMfgpartno(tla.getCustomerPartNo());
					}
				}
				returnList.add(p);
			}
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsPart> get2ndTouchItsPartsByICNList(List<String> icnList) {
		List<ItsPart> returnList = new ArrayList<ItsPart>();
		Criteria q = this.getDBCONNECTSSession().createCriteria(ItsPart.class);
		q.add(Restrictions.in("itsInvoiceNo", icnList))
		.add(Restrictions.eq("componentUom", "EA"))
		.addOrder(Order.asc("itsPartMfgpartno"));
		List<ItsPart> list = (List<ItsPart>)q.list();
		if ( list != null ) {
			for ( ItsPart p : list ) {
				String mfg = p.getItsPartMfgpartno();
				if ( mfg != null && mfg.length() < 7 && StringUtils.isNumeric(mfg) ) {
					Criteria tq = this.getDBCONNECTSSession().createCriteria(TopLevelAssembly.class);
					tq.add(Restrictions.eq("topLevelAssemblyId", new BigDecimal(p.getItsPartMfgpartno())));
					TopLevelAssembly tla = (TopLevelAssembly)tq.uniqueResult();
					if ( tla != null ) {
						p.setItsPartMfgpartno(tla.getCustomerPartNo());
					}
				}
				returnList.add(p);
			}
		}
		return returnList;
	}
	
	@SuppressWarnings("unchecked")
	public List<ItsPart> get2ndTouchItsPartsBySCN(String scn) {
		List<ItsPart> returnList = new ArrayList<ItsPart>();
		Criteria q = this.getDBCONNECTSSession().createCriteria(ItsPart.class);
		q.add(Restrictions.eq("itsScn", scn))
		//.add(Restrictions.ne("itsPartMfgcode", "AAA")) //No longer with EVOLVE
		//.add(Restrictions.ne("itsPartMfgcode", "FGF"))
		.add(Restrictions.eq("componentUom", "EA"))
		.addOrder(Order.asc("itsPartMfgpartno"));
		List<ItsPart> list = (List<ItsPart>)q.list();
		if ( list != null ) {
			for ( ItsPart p : list ) {
				String mfg = p.getItsPartMfgpartno();
				if ( mfg != null && mfg.length() < 7 && StringUtils.isNumeric(mfg) ) {
					Criteria tq = this.getDBCONNECTSSession().createCriteria(TopLevelAssembly.class);
					tq.add(Restrictions.eq("topLevelAssemblyId", new BigDecimal(p.getItsPartMfgpartno())));
					TopLevelAssembly tla = (TopLevelAssembly)tq.uniqueResult();
					if ( tla != null ) {
						p.setItsPartMfgpartno(tla.getCustomerPartNo());
					}
				}
				returnList.add(p);
			}
		}
		return returnList;
	}
	
	
	@SuppressWarnings("unchecked")
	public ItsPart getItsTopLevelPart(String topLevelSerialNumber, String productionOrder) {
		Criteria pCrit = this.getDBCONNECTSSession().createCriteria(ItsPart.class);
		pCrit.add(Restrictions.eq("itsPartSn", topLevelSerialNumber.toUpperCase()));
		pCrit.add(Restrictions.eq("itsInvoiceNo", productionOrder.toUpperCase()).ignoreCase());
		List<ItsPart> partList = (List<ItsPart>)pCrit.list();
		ItsPart part = null;
		if ( partList != null && partList.size() > 0 ) {
			part = partList.get(0);
		}
		return part;
		//return  (ItsPart) pCrit.uniqueResult();
	}
	
	/*
	 * ********************************************
	 * ITS Integration
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public Integration getIntegrationByICN(String icn) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(Integration.class);
		q.add(Restrictions.eq("id.itsInvoiceNo", icn));
		return (Integration)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integration> getIntegrationBySCN(String scn) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(Integration.class);
		q.add(Restrictions.eq("id.itsScn", scn));
		return (List<Integration>)q.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Integration> getCollectiveIntegrationBySCN(String scn) {
		/*
		Query q = this.getDBCONNECTSSession().createQuery(
				"from Integration as I " +
				"INNER JOIN ProductionOrderHeader as P " +
				"  ON I.id.itsInvoiceNo = cast(P.id.productionOrderNo as string) " +
				"  AND I.id.sourceErpId = P.id.sourceErpId " +
				"  AND P.collectiveOrderFl = 'X' " +
				"  AND P.id.productionOrderNo = P.leadProdOrderNo " +
				"WHERE I.id.itsScn = '" + scn + "'" 
				);
				*/
		Query q = this.getDBCONNECTSSession().createQuery(
				"SELECT x FROM Integration x " +
				", ProductionOrderHeader y " +
				" WHERE x.id.itsInvoiceNo = cast(y.id.productionOrderNo as string) " +
				"  AND x.id.sourceErpId = y.id.sourceErpId " +
				"  AND y.collectiveOrderFl = 'X' " +
				"  AND y.id.productionOrderNo = y.leadProdOrderNo " +
				" AND x.id.itsScn = '" + scn + "' " 
				);
		return (List<Integration>)q.list();
	}
	
	/*
	 * ********************************************
	 * ITS Carrier
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public CarrierCode getCarrierByCode(String code) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(CarrierCode.class);
		q.add(Restrictions.eq("carrierCode", code));
		return (CarrierCode)q.uniqueResult();
	}
	
	/*
	 * ********************************************
	 * ITS Team
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public ItsTeam getTeamById(int itsTeamId) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(ItsTeam.class);
		q.add(Restrictions.eq("itsTeamId", itsTeamId));
		return (ItsTeam)q.uniqueResult();
	}
	
	
	/*
	 * ********************************************
	 * Genesis Order Header
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	public Orderheader getGenesisOrderheaderBySCN(String scn) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(Orderheader.class);
		q.add(Restrictions.eq("scn", scn));
		return (Orderheader)q.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public Orderaddress getGenesisOrderaddressBySCN(String scn) {
		Criteria q = this.getDBCONNECTSSession().createCriteria(Orderaddress.class);
		q.add(Restrictions.eq("scn", scn));
		return (Orderaddress)q.uniqueResult();
	}
	
	/*
	 * ********************************************
	 * Reports
	 * ********************************************
	 */
	public List<TestResultReportItem> getDailyImagingFSTTestResults(String formatedDateString) {
		Session sess = this.getALAPPSSession();
		SQLQuery query = sess.createSQLQuery(
				getAllImagingFSTTestResultsByDateQuery.replaceFirst(":testDate:", formatedDateString));
		List<TestResultReportItem> recordList = new ArrayList<TestResultReportItem>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
	
			TestResultReportItem rec = new TestResultReportItem(
					(Date)data[0], 
					(BigDecimal)data[1],
					(String)data[2], 
					(String)data[3],
					(String)data[4], 
					(BigDecimal)data[5], 
					(String)data[6],
					(String)data[7], 
					(String)data[8],
					(String)data[9],
					(String)data[10], 
					(String)data[11],
					(String)data[12]
					);
			recordList.add(rec);
		}
		return recordList;
	}
	
	/*
	 * ********************************************
	 * Utility queries
	 * ********************************************
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false)
	public List<String> getDistinctICNList() {
		Session sess = this.getALAPPSSession();
		SQLQuery query = sess.createSQLQuery(getDistinctICNListQuery);
		return (List<String>)query.list();
	}
	
	@Transactional(readOnly = false, timeout = 3600)
	public void updateAstOrderInfoFromSlip(NutanixPackingSlip slip) {
		//Criteria q = this.getALAPPSSession().createCriteria(AstOrderInfo.class);
		//q.add(Restrictions.eq("id.scn", slip.getSalesOrderNumber()));
		//q.add(Restrictions.eq("id.icn", slip.getProductionOrderNumber()));
		//AstOrderInfo info = (AstOrderInfo)q.uniqueResult();
		AstOrderInfoId id = new AstOrderInfoId(slip.getProductionOrderNumber(), slip.getSalesOrderNumber());
		AstOrderInfo info = astOrderInfoHome.findById(id);
		if ( info == null ) {
			info = new AstOrderInfo();
			//info.setIcn(slip.getProductionOrderNumber());
			//info.setScn(slip.getSalesOrderNumber());
			//info.setSapDelivery(slip.getDeliveryNote());
			info.setCustomerOrder(slip.getNutanixOrderNumber());
			info.setCustomerPo(slip.getCustomerOrderNumber());
			info.setShiptoContactName(slip.getShipToContactName());
			info.setShiptoContactPhone(slip.getShipToContactPhone());
			info.setShiptoName(slip.getShipToName());
			info.setShiptoName2(slip.getShipToAttention());
			info.setShiptoAddr1(slip.getShipToAddress1());
			info.setShiptoAddr2(slip.getShipToAddress2());
			info.setShiptoAddr3(slip.getShipToAddress3());
			info.setShiptoCity(slip.getShipToCity());
			info.setShiptoState(slip.getShipToState());
			info.setShiptoPostalcode(slip.getShipToZip());
			String countryName = this.getCountryNameByCountryCode(slip.getShipToCountry());
			if ( countryName == null ) {
				info.setShiptoCountry(slip.getShipToCountry());
			}
			else {
				info.setShiptoCountry(countryName);
			}
			info.setUpdateCount(new BigDecimal(0));
			info.setId(id);
			astOrderInfoHome.persist(info);

			if ( slip.getNutanixPackingSlipWaybills() != null ) {
				for ( NutanixPackingSlipWaybill pswb : slip.getNutanixPackingSlipWaybills() ) {
					AstOrderInfoWaybill wb = new AstOrderInfoWaybill();
					wb.setId(new AstOrderInfoWaybillId(slip.getSalesOrderNumber(), pswb.getDeliveryNote()));
					wb.setCarrierName(pswb.getCarrierName());
					wb.setWaybill(pswb.getWaybill());
					wb.setShipDt(pswb.getShipDate());
					astOrderInfoWaybillHome.persist(wb);
				}
			}
		}
		else {
			//AstOrderInfoId id = info.getId();
			//id.setIcn(slip.getProductionOrderNumber());
			//id.setScn(slip.getSalesOrderNumber());
			//info.setSapDelivery(slip.getDeliveryNote());
			
			if ( info.getUpdateCount() == null ) {
				info.setUpdateCount(new BigDecimal(0));
			}
			
			//Only allow 14 updates (one per day for a couple of weeks)
			if ( info.getUpdateCount().intValue() >= 14 ) {
				return;
			}
			else {
				int updateCount = info.getUpdateCount().intValue();
				info.setUpdateCount(new BigDecimal(updateCount + 1));
			}
			
			info.setCustomerOrder(slip.getNutanixOrderNumber());
			info.setCustomerPo(slip.getCustomerOrderNumber());
			info.setShiptoContactName(slip.getShipToContactName());
			info.setShiptoContactPhone(slip.getShipToContactPhone());
			info.setShiptoName(slip.getShipToName());
			info.setShiptoName2(slip.getShipToAttention());
			info.setShiptoAddr1(slip.getShipToAddress1());
			info.setShiptoAddr2(slip.getShipToAddress2());
			info.setShiptoAddr3(slip.getShipToAddress3());
			info.setShiptoCity(slip.getShipToCity());
			info.setShiptoState(slip.getShipToState());
			info.setShiptoPostalcode(slip.getShipToZip());
			String countryName = this.getCountryNameByCountryCode(slip.getShipToCountry());
			if ( countryName == null ) {
				info.setShiptoCountry(slip.getShipToCountry());
			}
			else {
				info.setShiptoCountry(countryName);
			}
			info.setId(id);
			astOrderInfoHome.merge(info);
			
			if ( slip.getNutanixPackingSlipWaybills() != null ) {
				for ( NutanixPackingSlipWaybill pswb : slip.getNutanixPackingSlipWaybills() ) {
					
					AstOrderInfoWaybillId wbId = 
						new AstOrderInfoWaybillId(slip.getSalesOrderNumber(), pswb.getDeliveryNote());
					
					AstOrderInfoWaybill wb = astOrderInfoWaybillHome.findById(wbId);
					if ( wb == null ) {
						wb = new AstOrderInfoWaybill();
						wb.setId(wbId);
						wb.setCarrierName(pswb.getCarrierName());
						wb.setWaybill(pswb.getWaybill());
						wb.setShipDt(pswb.getShipDate());
						astOrderInfoWaybillHome.persist(wb);
					}
					else {
						wb.setCarrierName(pswb.getCarrierName());
						wb.setWaybill(pswb.getWaybill());
						wb.setShipDt(pswb.getShipDate());
						astOrderInfoWaybillHome.merge(wb);
					}
				}
			}
		}
	}
	
	public String getCountryNameByCountryCode(String countryCode) {
		if ( countryCode == null ) {
			return null;
		}
		Criteria q = this.getGSFCSession().createCriteria(Country.class);
		q.add(Restrictions.eq("countryIsoAlpha2Cd", countryCode.trim()));
		Country c = (Country)q.uniqueResult();
		if ( c == null ) {
			return null;
		}
		return c.getCountryNm();
	}
	
	
	public void updateAstOrderInfoForMiddleware(EvolveSalesOrderDetailService esodService, String icn) throws Exception {
		if ( icn != null && !icn.startsWith("N") && !icn.startsWith("P")  ) { //Not Genesis or fake BOM in ITS
			NutanixPackingSlip slip = new NutanixPackingSlip();
			slip.setProductionOrderNumber(icn);
			Integration integration = this.getIntegrationByICN(icn);
			if ( esodService != null && integration != null ) {
				slip.setNutanixOrderNumber(integration.getItsCustpono());
				slip.setSalesOrderNumber(integration.getId().getItsScn());
				esodService.getSalesOrderForNutanixPackingSlip(slip);
				this.updateAstOrderInfoFromSlip(slip);
			}
			else {
				throw new Exception("INTEGRATION record not found in ITS: icn = " + icn);
			}
		}	
	}
	
	public String getItsFullProductPartDescription(String sapMaterialNumber) {
		String description = null;
		SQLQuery itsFullProductQuery = this.getDBCONNECTSSession().createSQLQuery(
				getItsFullProductPartDescriptionQuery
				.replaceFirst(":sapMaterialNumber:", sapMaterialNumber)
			);
		description = (String)itsFullProductQuery.uniqueResult();
		if ( description != null ) {
			description = description.toUpperCase();
		}
		return description;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	public List<NutanixPackingSlip> getSlipsWithMissingWaybill(Session sess) {
		if ( sess == null ) {
			sess = this.getALAPPSSession();
		}
		SQLQuery query = sess.createSQLQuery(getSAPOrdersWithMissingWaybillQuery);
		List<NutanixPackingSlip> recordList = new ArrayList<NutanixPackingSlip>();
		for ( Object obj : query.list()) {
			Object[] data = (Object[])obj;
			NutanixPackingSlip rec = new NutanixPackingSlip();
			rec.setSalesOrderNumber((String)data[0]);
			rec.setProductionOrderNumber((String)data[1]);
			recordList.add(rec);
		}
		return recordList;
	}
	
	
	@Transactional(readOnly = false, timeout = 600)
	public List<BigDecimal> getAllSAPAssemblyIds(Session sess) {
		if ( sess == null ) {
			sess = this.getALAPPSSession();
		}
		SQLQuery query = sess.createSQLQuery(getAllSAPAssemblyIdsQuery);
		List<BigDecimal> recordList = new ArrayList<BigDecimal>();
		for ( Object obj : query.list()) {
			recordList.add((BigDecimal)obj);
		}
		return recordList;
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
	
	protected Session getDBCONNECTSSession() {
		try {
			SessionFactory sf =
				(SessionFactory) new InitialContext().lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_DBCONNECT);
			return sf.getCurrentSession();
		} catch (Exception e) {
			log.error("Could not locate DBCONNECTSessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate DBCONNECTSessionFactory in JNDI");
		}
	}
	
	protected Session getGSFCSession() {
		try {
			SessionFactory sf =
				(SessionFactory) new InitialContext().lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_GSFC);
			return sf.getCurrentSession();
		} catch (Exception e) {
			log.error("Could not locate GSFCSessionFactory in JNDI", e);
			throw new IllegalStateException(
					"Could not locate GSFCSessionFactory in JNDI");
		}
	}
	
	/*
	public EvolveSalesOrderDetailService getEvolveSalesOrderDetailService() {
		return evolveSalesOrderDetailService;
	}

	@Autowired
	public void setEvolveSalesOrderDetailService(
			EvolveSalesOrderDetailService evolveSalesOrderDetailService) {
		this.evolveSalesOrderDetailService = evolveSalesOrderDetailService;
	}
	*/

}
