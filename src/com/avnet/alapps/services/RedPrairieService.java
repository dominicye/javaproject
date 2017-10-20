package com.avnet.alapps.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.common.Util;
import com.avnet.alapps.spring.WebConfig;
import com.avnet.alapps.systest.model.NutanixPackingSlip;
import com.avnet.alapps.systest.model.NutanixPackingSlipLine;
import com.redprairie.moca.MocaArgument;
import com.redprairie.moca.MocaResults;
import com.redprairie.moca.NotFoundException;
import com.redprairie.moca.client.ConnectionUtils;
import com.redprairie.moca.client.MocaConnection;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class RedPrairieService {


	private static Logger log = Logger.getLogger(RedPrairieService.class);
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	@Value("${alapps.redprairie.2009.host}") private String rp2009Host; 
	@Value("${alapps.redprairie.2009.user}") private String rp2009User; 
	@Value("${alapps.redprairie.2009.password}") private String rp2009Password; 
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer rpProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}


	public NutanixPackingSlip getNutanixPackingSlipLines(String scn) throws Exception {
		NutanixPackingSlip slip = new NutanixPackingSlip();
		MocaConnection conn = null;
		try {
			conn = this.getNewRedPrairie2009Connection();
			
			MocaResults hResults = conn.executeCommand(MOCA_GET_PACKING_SLIP_HEADER.replaceFirst("@ordnum", scn));
			if ( hResults != null && hResults.getRowCount() > 0  ) {
				hResults.next();
				slip.setSalesOrderNumber(hResults.getString("sales_ordnum"));
				String nutanixPO = hResults.getString("cponum");
				//if ( nutanixPO != null ) {
				//	nutanixPO = nutanixPO.trim().replaceFirst(" ", "-");
				//}
				slip.setNutanixOrderNumber(nutanixPO);
				slip.setShippingCarrier(hResults.getString("carnam"));
				slip.setShipToName(hResults.getString("adrnam"));
				slip.setShipToAddress1(hResults.getString("adrln1"));
				slip.setShipToAddress2(hResults.getString("adrln2"));
				slip.setShipToCity(hResults.getString("adrcty"));
				slip.setShipToState(hResults.getString("adrstc"));
				slip.setShipToZip(hResults.getString("adrpsz"));
				slip.setShipToCountry(hResults.getString("ctry_name"));
			}
			
			
			MocaResults dResults = conn.executeCommand(MOCA_GET_PACKING_SLIP_LINES.replaceFirst("@ordnum", scn));
			if ( dResults != null && dResults.getRowCount() > 0  ) {
				List<NutanixPackingSlipLine> chassisLines = new ArrayList<NutanixPackingSlipLine>();
				List<NutanixPackingSlipLine> partLines = new ArrayList<NutanixPackingSlipLine>();
				while ( dResults.next() ) {
					
					String customerPartNumber = dResults.getString("cstprt");
					if ( customerPartNumber == null || customerPartNumber.trim().length() == 0 ) {
						customerPartNumber = dResults.getString("prtnum");
					}
					
					NutanixPackingSlipLine line = 
						new NutanixPackingSlipLine(
								customerPartNumber, 
								dResults.getString("prtdesc"),
								"", //serial 
								new Long(dResults.getInt("ordqty"))
								);
					
					if ( customerPartNumber.startsWith("NX-") ) { //This is a chassis
						String ordnum = dResults.getString("ordnum");
						String ordlin = dResults.getString("ordlin");
						
						try {
							MocaResults snResults = conn.executeCommand(
									MOCA_GET_PACKING_SLIP_PART_SERIAL.replaceFirst("@ordnum", ordnum).replaceFirst("@ordlin", ordlin)
									);
							if ( snResults != null && snResults.getRowCount() > 0  ) {
								snResults.next();
								line.setSerialNumber(snResults.getString("ser_num"));
							}
							chassisLines.add(line);
						} catch (com.redprairie.moca.NotFoundException nfe) {
							throw new Exception("Nutanix chassis serial number not found for part " + customerPartNumber, nfe); //TODO: BRING THIS BACK
						}
						
					}
					else {
						partLines.add(line);
					}
				}
				
				//Chassis first, then sorted parts
				Collections.sort(chassisLines, new Util.NutanixPackingSlipLineComparator());
				Collections.sort(partLines, new Util.NutanixPackingSlipLineComparator());
				chassisLines.addAll(partLines);
				slip.setNutanixPackingSlipLines(chassisLines);
			}
			
		} catch (com.redprairie.moca.NotFoundException nfe) {
			throw new Exception("Nutanix packing slip data not found for SCN: " + scn, nfe);
		} 
		finally {
			this.closeRedPrairieConnection(conn);
		}	
		return slip;
	}
	

	
	private MocaConnection getNewRedPrairie2009Connection() {
		MocaConnection conn = null;
		try {
			conn = ConnectionUtils.createConnection(this.rp2009Host, null);
			ConnectionUtils.login(conn, this.rp2009User, this.rp2009Password);
		} catch (Exception e) {
			log.error("Error opening RP 2009 connection", e);
		}
		return conn;
	}
	
	private void closeRedPrairieConnection(MocaConnection conn) {
		if (conn != null) {
			try {
				ConnectionUtils.logout(conn);
				conn.close();
			} catch (Exception e) {
				log.error("Error closing RP connection", e);
			}
		}
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getTier() {
		return tier;
	}

	@Autowired
	public void setTier(String tier) {
		this.tier = tier;
	}
	
	private static final String MOCA_GET_PACKING_SLIP_LINES = 
		"[select ol.prt_client_id, " +
        "ol.ordnum, " +
        "ol.ordlin, " +
        "ol.prtnum, " +
        "ol.cstprt, " +
        "ol.uc_reffld_1, " +
        "(select p.lngdsc " +
        "   from prtdsc p " +
        "  where p.colnam = 'prtnum|prt_client_id|wh_id_tmpl' " +
        "    and p.colval = ol.prtnum || '|' || ol.prt_client_id || '|' || ol.wh_id " +
        "    and p.locale_id = 'US_ENGLISH') as prtdesc, " +
        "ol.ordqty " +
		"from ord_line ol " +
		"where ol.wh_id = 'P140' " +
		//"and ol.ordnum = '@ordnum' " + GENESIS
		"and ol.sales_ordnum = '@ordnum' " +
		"and ol.prt_client_id not in ('AAA', 'FGF') " +
		"];";
	
	private static final String MOCA_GET_PACKING_SLIP_PART_SERIAL =
		"[select isn.ser_num " +
		"from shipment_line spl " +
		"inner join invdtl ivd on spl.ship_line_id = ivd.ship_line_id " +
		"inner join inv_ser_num isn on ivd.dtlnum = isn.invtid " +
		"where spl.ordnum = '@ordnum' " +
		"and spl.ordlin = '@ordlin' " +
		"and spl.wh_id = 'P140'];";
	
	private static final String MOCA_GET_PACKING_SLIP_HEADER = 
		"[select o.cponum,ol.prjnum,ol.sales_ordnum,c.carnam,am.adrnam,am.adrln1,am.adrln2,am.adrln3,am.adrcty,am.adrstc,am.adrpsz,am.ctry_name " +
		  "from ord o " +
		  "inner join adrmst am on o.st_adr_id = am.adr_id and am.adrln1 is not null " +
		  //"inner join ord_line ol on o.ordnum = ol.ordnum and o.wh_id = ol.wh_id " + GENSIS
		  "inner join ord_line ol on o.ordnum = ol.ordnum and ol.wh_id = 'P140' and ol.sales_ordnum = '@ordnum' " +
		  "inner join carhdr c on ol.carcod = c.carcod " +
		  //"where o.wh_id = 'P140' and o.ordnum = '@ordnum' " + GENESIS
		  "group by o.cponum,ol.prjnum,ol.sales_ordnum,c.carnam,am.adrnam,am.adrln1,am.adrln2,am.adrln3,am.adrcty,am.adrstc,am.adrpsz,am.ctry_name " +
		  "order by o.cponum,ol.prjnum,ol.sales_ordnum,c.carnam,am.adrnam,am.adrln1,am.adrln2,am.adrln3,am.adrcty,am.adrstc,am.adrpsz,am.ctry_name];";
	
	
}