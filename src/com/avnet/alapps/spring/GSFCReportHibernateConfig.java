package com.avnet.alapps.spring;

import java.util.Properties;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.TransactionManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.WebSphereUowTransactionManager;

import com.avnet.alapps.common.AlAppsConstants;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.avnet.alapps.spring",  "com.avnet.alapps.security", "com.avnet.alapps.services", "com.avnet.alapps.controllers", "com.avnet.alapps.model.gsfcreport"  }) //
public class GSFCReportHibernateConfig {
	
	private static Logger log = Logger.getLogger(GSFCReportHibernateConfig.class);
	@Value("${gsfcreport.hibernate.dialect}") private String hibernateDialect;
	@Value("${gsfcreport.datasource.jndi}") private String datasourceJndi;
	@Value("${gsfcreport.hibernate.show_sql}") private String hibernateShowSql;
	@Value("${gsfcreport.hibernate.format_sql}") private String hibernateFormatSql;
	@Value("${gsfcreport.hibernate.default_schema}") private String hibernateDefaultSchema;
	
    //@ Autowired 
    private  SessionFactory gsfcReportSessionFactory;
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer gsfcReportHibernateConfigProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
    
    @Bean public static WebSphereUowTransactionManager wasUOWTxnManagerObj() {
    	return new WebSphereUowTransactionManager();
    }
    
    @Bean
    public  static TransactionManager gsfcReportTransactionManager() throws NamingException {
    	return wasUOWTxnManagerObj().getTransactionManager();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public  SessionFactory gsfcReportSessionFactory() throws NamingException { 
        try {
        	if ( gsfcReportSessionFactory != null ) {
        		return gsfcReportSessionFactory;
        	}
        	LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
        	lsf.setDataSource(gsfcReportDataSource());
        	lsf.setPackagesToScan(new String[] { 
	        		"com.avnet.alapps", 
	        		"com.avnet.alapps.spring", 
	        		"com.avnet.alapps.security",
	        		"com.avnet.alapps.model.gsfcreport",
	        		"com.avnet.alapps.services",
	        		"com.avnet.alapps.controllers"
	        		});
        	lsf.setHibernateProperties(gsfcReportHibernateProperties());
        	lsf.setMappingResources(gsfcReportMappingResources());
        	lsf.setJtaTransactionManager(gsfcReportTransactionManager());
        	lsf.afterPropertiesSet();
        	gsfcReportSessionFactory = lsf.getObject();
	        return gsfcReportSessionFactory;
        } 
        catch (Exception e) {
            log.error("Couldn't configure the sessionFactory bean", e);
        }
        throw new RuntimeException("Couldn't configure the sessionFactory bean");
     }

    @Bean
    public DataSource gsfcReportDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = 
        	dsLookup.getDataSource(datasourceJndi); 
        return dataSource;
    }

    @Bean
    public Properties gsfcReportHibernateProperties() {   
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.session_factory_name", AlAppsConstants.HIBERNATE_SESSION_NAME_GSFCREPORT);
        //properties.put("hibernate.current_session_context_class", "org.hibernate.context.internal.JTASessionContext"); 
        properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringJtaSessionContext"); 
        properties.put("hibernate.transaction.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jta.JtaTransactionFactory");
        properties.put("hibernate.default_schema", hibernateDefaultSchema);  
        properties.put("hibernate.connection.release_mode", "after_statement");
        properties.put("hibernate.transaction.auto_close_session", "true");
        
        return properties;        
    }
    
    @Bean
    public static String[] gsfcReportMappingResources() {
    	return new String[] {
    	        "com/avnet/alapps/model/gsfcreport/Customer.hbm.xml",
    	        "com/avnet/alapps/model/gsfcreport/Site.hbm.xml",
    	        "com/avnet/alapps/model/gsfcreport/FirstArticle.hbm.xml",
    	        "com/avnet/alapps/model/gsfcreport/SourceWorkOrder.hbm.xml",
    	        "com/avnet/alapps/model/gsfcreport/WorkOrder.hbm.xml",
    	        "com/avnet/alapps/model/gsfcreport/WorkOrderStatusHst.hbm.xml"
    	};
    }
	
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   } 
   
   public SessionFactory getGsfcReportSessionFactory() throws NamingException {  
       return gsfcReportSessionFactory();  
   }  
   
  //@ Autowired
  public void setGsfcReportSessionFactory(SessionFactory sf) {
	  gsfcReportSessionFactory = sf;
  }
}

