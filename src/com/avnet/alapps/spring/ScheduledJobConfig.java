package com.avnet.alapps.spring;

import java.net.InetAddress;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.transaction.annotation.Transactional;
import com.avnet.alapps.model.dbconnect.Integration;
import com.avnet.alapps.services.EvolveSalesOrderDetailService;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.NutanixPackingSlip;


public class ScheduledJobConfig implements ApplicationContextAware {
	
	//TODO: Once we go pure annotation java config vs. xml configs, duplicate runs will be a problem until Websphere updates to j2ee servlet v. 3.0

	private static boolean running = false;
	private ApplicationContext context;
	private static Logger log = Logger.getLogger(ScheduledJobConfig.class);
	
	//@//Scheduled(cron="0/30 * * * * ?")
	@Transactional(readOnly = false, timeout = 3600)
	public void orderInfoUpdateJob() { 
		try {
			
			boolean persist = true; 
			
			InetAddress local = InetAddress.getLocalHost();
			String localHost = local.getHostName().toLowerCase();
			String tier = WebConfig.tier();
			Properties prop = new Properties();
			prop.load(ScheduledJobConfig.class.getClassLoader().getResourceAsStream("com/avnet/alapps/properties/app-" + tier + ".properties"));
			String scheduledJobHost = prop.getProperty("alapps.scheduled.job.process.host");
			
			if ( tier == null || localHost == null || scheduledJobHost == null ) {
				log.error("Configuration data for orderInfoUpdateJob not loaded. Scheduled job aborted: localHost=" + localHost + ", scheduledJobHost=" + scheduledJobHost);
				return;
			}
			else if ( "local".equalsIgnoreCase(tier) ) {
				if ( persist ) {
					log.info("orderInfoUpdateJob aborted since running on local tier. ");
					return; 
				}
			}
			else if ( !localHost.equalsIgnoreCase(scheduledJobHost) ) {
				log.info("orderInfoUpdateJob aborted since not running on configured host: localHost=" + localHost + ", scheduledJobHost=" + scheduledJobHost);
				return;
			}

			synchronized(this) {
				if ( running ) {
					System.out.println("SKIPPING already running orderInfoUpdateJob");
					return;
				}
				else {
					running = true;
				}
			}
			log.info("Started orderInfoUpdateJob: tier=" + tier + ", localHost=" + localHost + ", scheduledJobHost=" + scheduledJobHost);
			//log.info("RUNNING orderInfoUpdateJob: context = " + context.toString());
			SessionFactory sf = (SessionFactory)context.getBean("alappsSessionFactory");
			Session sess =  sf.getCurrentSession();
			SysTestService testServ = new SysTestService();
			List<NutanixPackingSlip> slipList = testServ.getSlipsWithMissingWaybill(sess);
			long updateCheckCount = 0;
			if ( slipList != null && slipList.size() > 0 ) {
				EvolveSalesOrderDetailService soServ = (EvolveSalesOrderDetailService)context.getBean("evolveSalesOrderDetailService");
				for ( NutanixPackingSlip slip : slipList ) {
					try {
						
						//if ( slip.getSalesOrderNumber().startsWith("2") ) {
						//	continue;
						//}
						
						Integration integration = testServ.getIntegrationByICN(slip.getProductionOrderNumber());
						if ( integration != null ) {
							slip.setNutanixOrderNumber(integration.getItsCustpono());
						}	
						soServ.getSalesOrderForNutanixPackingSlip(slip);
						if ( persist ) {
							testServ.updateAstOrderInfoFromSlip(slip);
						}
						updateCheckCount++;
						log.info("orderInfoUpdateJob updated: SCN=" + slip.getSalesOrderNumber() + ", ICN=" + slip.getProductionOrderNumber());
					}
					catch ( Exception e ) {
						log.error("orderInfoUpdateJob error: SCN=" + slip.getSalesOrderNumber() + ", ICN=" + slip.getProductionOrderNumber(), e);
					}
				}
			}
			log.info("Completed orderInfoUpdateJob: updateCheckCount = " + String.valueOf(updateCheckCount));
		}
		catch ( Exception e ) {
			log.error("Error running orderInfoUpdateJob: ", e);
		}
		finally {
			synchronized(this) {
				running = false;
			}
		}
	}
	
	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		this.context = ctx;
	}
	
}
