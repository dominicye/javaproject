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
@ComponentScan({ "com.avnet.alapps.spring",  "com.avnet.alapps.security", "com.avnet.alapps.services", "com.avnet.alapps.controllers", "com.avnet.alapps.model.gsfc"  }) //
public class GSFCHibernateConfig {
	
	private static Logger log = Logger.getLogger(GSFCHibernateConfig.class);
	@Value("${gsfc.hibernate.dialect}") private String hibernateDialect;
	@Value("${gsfc.datasource.jndi}") private String datasourceJndi;
	@Value("${gsfc.hibernate.show_sql}") private String hibernateShowSql;
	@Value("${gsfc.hibernate.format_sql}") private String hibernateFormatSql;
	@Value("${gsfc.hibernate.default_schema}") private String hibernateDefaultSchema;
	
    //@ Autowired 
    private  SessionFactory gsfcSessionFactory;
    
    @Bean
	public static PropertySourcesPlaceholderConfigurer gsfcHibernateConfigProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
    
    @Bean public static WebSphereUowTransactionManager wasUOWTxnManagerObj() {
    	return new WebSphereUowTransactionManager();
    }
    
    @Bean
    public  static TransactionManager gsfcTransactionManager() throws NamingException {
    	return wasUOWTxnManagerObj().getTransactionManager();
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    @Bean
    public  SessionFactory gsfcSessionFactory() throws NamingException { 
        try {
        	if ( gsfcSessionFactory != null ) {
        		return gsfcSessionFactory;
        	}
        	LocalSessionFactoryBean lsf = new LocalSessionFactoryBean();
        	lsf.setDataSource(gsfcDataSource());
        	lsf.setPackagesToScan(new String[] { 
	        		"com.avnet.alapps", 
	        		"com.avnet.alapps.spring", 
	        		"com.avnet.alapps.security",
	        		"com.avnet.alapps.model.gsfc",
	        		"com.avnet.alapps.services",
	        		"com.avnet.alapps.controllers"
	        		});
        	lsf.setHibernateProperties(gsfcHibernateProperties());
        	lsf.setMappingResources(gsfcMappingResources());
        	lsf.setJtaTransactionManager(gsfcTransactionManager());
        	lsf.afterPropertiesSet();
        	gsfcSessionFactory = lsf.getObject();
	        return gsfcSessionFactory;
        } 
        catch (Exception e) {
            log.error("Couldn't configure the sessionFactory bean", e);
        }
        throw new RuntimeException("Couldn't configure the sessionFactory bean");
     }

    @Bean
    public DataSource gsfcDataSource() {
        final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
        dsLookup.setResourceRef(true);
        DataSource dataSource = 
        	dsLookup.getDataSource(datasourceJndi); 
        return dataSource;
    }

    @Bean
    public Properties gsfcHibernateProperties() {   
        Properties properties = new Properties();
        properties.put("hibernate.dialect", hibernateDialect);
        properties.put("hibernate.show_sql", hibernateShowSql);
        properties.put("hibernate.format_sql", hibernateFormatSql);
        properties.put("hibernate.session_factory_name", AlAppsConstants.HIBERNATE_SESSION_NAME_GSFC);
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
    public static String[] gsfcMappingResources() {
    	return new String[] {
    	        "com/avnet/alapps/model/gsfc/Contact.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/ContactGroup.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/ContactGroupMember.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/ContactType.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/Operation.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/Site.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/UpromHost.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/UserContact.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/UserOperation.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/UserRole.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/UserRoleOperation.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/Users.hbm.xml",
    	        "com/avnet/alapps/model/gsfc/Country.hbm.xml"
    	};
    }
	
   @Bean
   public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
      return new PersistenceExceptionTranslationPostProcessor();
   } 
   
   public SessionFactory getGsfcSessionFactory() throws NamingException {  
       return gsfcSessionFactory();  
   }  
   
  //@ Autowired
  public void setGsfcSessionFactory(SessionFactory sf) {
	  gsfcSessionFactory = sf;
  }
}

