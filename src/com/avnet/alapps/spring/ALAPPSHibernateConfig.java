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
@ComponentScan({ "com.avnet.alapps.spring",  "com.avnet.alapps.security", "com.avnet.alapps.services", "com.avnet.alapps.controllers", "com.avnet.alapps.model.alapps"  }) //
public class ALAPPSHibernateConfig {
	
	private static Logger log = Logger.getLogger(ALAPPSHibernateConfig.class);
	@Value("${alapps.hibernate.dialect}") private String hibernateDialect;
	@Value("${alapps.datasource.jndi}") private String datasourceJndi;
	@Value("${alapps.hibernate.show_sql}") private String hibernateShowSql;
	@Value("${alapps.hibernate.format_sql}") private String hibernateFormatSql;
	@Value("${alapps.hibernate.default_schema}") private String hibernateDefaultSchema;
	
    //@ Autowired 
    private  SessionFactory alappsSessionFactory;
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer alappsHibernateConfigProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
    
    @Bean public static WebSphereUowTransactionManager wasUOWTxnManagerObj() {
    	return new WebSphereUowTransactionManager();
    }
    
    @Bean
    public  static TransactionManager alappsTransactionManager() throws NamingException {
    	//TODO: This sets default global transaction timeout currently 600s. Need to find a reasonable value
    	try {
    		wasUOWTxnManagerObj().getTransactionManager().setTransactionTimeout(720);
    	}
    	catch (Exception e ) {}
    	return wasUOWTxnManagerObj().getTransactionManager();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public  SessionFactory alappsSessionFactory() throws NamingException { 
        try {
        	if ( alappsSessionFactory != null ) {
        		return alappsSessionFactory;
        	}
        	LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
        	lsf.setDataSource(alappsDataSource());
        	lsf.setPackagesToScan(new String[] { 
	        		"com.avnet.alapps", 
	        		"com.avnet.alapps.spring", 
	        		"com.avnet.alapps.security",
	        		"com.avnet.alapps.model.alapps",
	        		"com.avnet.alapps.services",
	        		"com.avnet.alapps.controllers"
	        		});
        	lsf.setHibernateProperties(alappsHibernateProperties());
        	lsf.setMappingResources(alappsMappingResources());
        	lsf.setJtaTransactionManager(alappsTransactionManager());
        	lsf.afterPropertiesSet();
        	alappsSessionFactory = lsf.getObject();
	        return alappsSessionFactory;
        } 
        catch (Exception e) {
            log.error("Couldn't configure the sessionFactory bean", e);
        }
        throw new RuntimeException("Couldn't configure the alappsSessionFactory bean");
     }

    @Bean
    public DataSource alappsDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = 
        	dsLookup.getDataSource(datasourceJndi); 
        return dataSource;
    }

    @Bean
    public Properties alappsHibernateProperties() {   
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.session_factory_name", AlAppsConstants.HIBERNATE_SESSION_NAME_ALAPPS);
        //properties.put("hibernate.current_session_context_class", "org.hibernate.context.internal.JTASessionContext"); 
        properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate4.SpringJtaSessionContext"); 
        properties.put("hibernate.transaction.jta.platform", "org.hibernate.engine.transaction.jta.platform.internal.WebSphereExtendedJtaPlatform");
        properties.put("hibernate.transaction.factory_class", "org.hibernate.engine.transaction.internal.jta.JtaTransactionFactory");
        //properties.put("javax.persistence.transactionType", "JTA");
        properties.put("hibernate.default_schema", hibernateDefaultSchema);  
        properties.put("hibernate.connection.release_mode", "after_statement");
        properties.put("hibernate.transaction.auto_close_session", "true");
        return properties;        
    }
    
    @Bean
    public static String[] alappsMappingResources() {
    	return new String[] {
    	        "com/avnet/alapps/model/alapps/AstCompType.hbm.xml",    	        
    	        "com/avnet/alapps/model/alapps/AstCompTypeAttr.hbm.xml",  	        
    	        "com/avnet/alapps/model/alapps/AstDataSource.hbm.xml", 	        
    	        "com/avnet/alapps/model/alapps/AstDataType.hbm.xml", 	        
    	        "com/avnet/alapps/model/alapps/AstPart.hbm.xml",   	        
    	        "com/avnet/alapps/model/alapps/AstPartAsm.hbm.xml",   	        
    	        "com/avnet/alapps/model/alapps/AstPartAsmAttr.hbm.xml",   	        
    	        "com/avnet/alapps/model/alapps/AstPartAttr.hbm.xml",   	        
    	        "com/avnet/alapps/model/alapps/AstSubPartCtrl.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstTempBom.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstTempBomPart.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstTestResult.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstTestResultItem.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstTestResultCode.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstPartAsmExcluded.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstOrderInfo.hbm.xml",
    	        "com/avnet/alapps/model/alapps/AstOrderInfoWaybill.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstCompType.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstCompTypeAttr.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstPart.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstPartAsm.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstPartAsmAttr.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstPartAttr.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstTestResult.hbm.xml",
    	        "com/avnet/alapps/model/alapps/MinAstTestResultItem.hbm.xml"
    	};
    }
	
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   } 
   
   public SessionFactory getAlappsSessionFactory() throws NamingException {  
       return alappsSessionFactory();  
   }  
   
  //@ Autowired
  public void setAlappsSessionFactory(SessionFactory sf) {
	  alappsSessionFactory = sf;
  }
}

