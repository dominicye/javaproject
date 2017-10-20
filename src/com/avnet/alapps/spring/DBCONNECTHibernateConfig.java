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
@ComponentScan({ "com.avnet.alapps.spring",  "com.avnet.alapps.security", "com.avnet.alapps.services", "com.avnet.alapps.controllers", "com.avnet.alapps.model.dbconnect"  }) //
public class DBCONNECTHibernateConfig {
	
	private static Logger log = Logger.getLogger(DBCONNECTHibernateConfig.class);
	@Value("${dbconnect.hibernate.dialect}") private String hibernateDialect;
	@Value("${dbconnect.datasource.jndi}") private String datasourceJndi;
	@Value("${dbconnect.hibernate.show_sql}") private String hibernateShowSql;
	@Value("${dbconnect.hibernate.format_sql}") private String hibernateFormatSql;
	@Value("${dbconnect.hibernate.default_schema}") private String hibernateDefaultSchema;
	
    //@ Autowired 
    private  SessionFactory dbconnectSessionFactory;
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer dbconnectHibernateConfigProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
    
    @Bean public static WebSphereUowTransactionManager wasUOWTxnManagerObj() {
    	return new WebSphereUowTransactionManager();
    }
    
    @Bean
    public  static TransactionManager dbconnectTransactionManager() throws NamingException {
    	return wasUOWTxnManagerObj().getTransactionManager();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public  SessionFactory dbconnectSessionFactory() throws NamingException { 
        try {
        	if ( dbconnectSessionFactory != null ) {
        		return dbconnectSessionFactory;
        	}
        	LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
        	lsf.setDataSource(dbconnectDataSource());
        	lsf.setPackagesToScan(new String[] { 
	        		"com.avnet.alapps", 
	        		"com.avnet.alapps.spring", 
	        		"com.avnet.alapps.security",
	        		"com.avnet.alapps.model.dbconnect",
	        		"com.avnet.alapps.services",
	        		"com.avnet.alapps.controllers"
	        		});
        	lsf.setHibernateProperties(dbconnectHibernateProperties());
        	lsf.setMappingResources(dbconnectMappingResources());
        	lsf.setJtaTransactionManager(dbconnectTransactionManager());
        	lsf.afterPropertiesSet();
        	dbconnectSessionFactory = lsf.getObject();
	        return dbconnectSessionFactory;
        } 
        catch (Exception e) {
            log.error("Couldn't configure the sessionFactory bean", e);
        }
        throw new RuntimeException("Couldn't configure the dbconnectSessionFactory bean");
     }

    @Bean
    public DataSource dbconnectDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = 
        	dsLookup.getDataSource(datasourceJndi); 
        return dataSource;
    }

    @Bean
    public Properties dbconnectHibernateProperties() {   
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.session_factory_name", AlAppsConstants.HIBERNATE_SESSION_NAME_DBCONNECT);
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
    public static String[] dbconnectMappingResources() {
    	return new String[] {
    	        "com/avnet/alapps/model/dbconnect/DataCaptureCharacteristic.hbm.xml",    	        
    	        "com/avnet/alapps/model/dbconnect/DataCaptureDetail.hbm.xml", 
    	        "com/avnet/alapps/model/dbconnect/DataCaptureRequirement.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/Integration.hbm.xml", 	        
    	        "com/avnet/alapps/model/dbconnect/ItsPart.hbm.xml", 
    	        "com/avnet/alapps/model/dbconnect/ItsUnitDetail.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/Project.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/ItsTeam.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/CarrierCode.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/TopLevelAssembly.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/Orderheader.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/Orderaddress.hbm.xml",
    	        "com/avnet/alapps/model/dbconnect/ProductionOrderHeader.hbm.xml"
    	};
    }
	
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   } 
   
   public SessionFactory getDbConnectSessionFactory() throws NamingException {  
       return dbconnectSessionFactory();  
   }  
   
  //@ Autowired
  public void setDbConnectSessionFactory(SessionFactory sf) {
	  dbconnectSessionFactory = sf;
  }
}

