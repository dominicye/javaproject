package com.avnet.alapps.spring;

import java.io.IOException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import com.avnet.alapps.security.AdministrationDAO;
import com.avnet.alapps.security.EnterpriseLDAPReader;
import com.avnet.alapps.security.SecurityDAO;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.AvnetUserDetailsService;
import com.avnet.alapps.services.EvolveSalesOrderDetailService;
import com.avnet.alapps.services.RedPrairieService;


@Configuration
@EnableWebMvc
//@//EnableScheduling 
@ComponentScan(basePackages = {
				"com.avnet.alapps", 
				"com.avnet.alapps.spring", 
				"com.avnet.alapps.security", 
				"com.avnet.alapps.controllers", 
				"com.avnet.alapps.common"
				}) //"com.avnet.alapps.security", 
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final Integer CACHE_PERIOD = new Integer(31556926); 
	
	private static String tier;
	private static PropertySourcesPlaceholderConfigurer propertyPlaceHolder = null;
	private static Properties log4jProperties = null; 

	@Bean 
	public static String tier() {
		try {
			if ( tier == null ) {
				Context ctx;
				ctx = new InitialContext();
				tier = (String) ctx.lookup("cell/persistent/tier");
				//System.out.println("****** cell/persistent/tier = " + tier);
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return tier;
	}

	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		if ( propertyPlaceHolder == null ) {
			propertyPlaceHolder = new PropertySourcesPlaceholderConfigurer();
			Resource[] resourceLocations = new Resource[] {
					new ClassPathResource("com/avnet/alapps/properties/log4j-" + tier() + ".properties"),
	                new ClassPathResource("com/avnet/alapps/properties/app-" + tier() + ".properties"),
	                new ClassPathResource("com/avnet/alapps/properties/gsf_hibernate.properties"),
	                new ClassPathResource("com/avnet/alapps/properties/gsfreport_hibernate.properties"),
	                new ClassPathResource("com/avnet/alapps/properties/alapps_hibernate.properties"),
	                new ClassPathResource("com/avnet/alapps/properties/dbconnect_hibernate.properties"),
	                new ClassPathResource("com/avnet/alapps/properties/mail_sender.properties")   
	        };
			propertyPlaceHolder.setLocations(resourceLocations);
			propertyPlaceHolder.setIgnoreResourceNotFound(true);
			propertyPlaceHolder.setIgnoreUnresolvablePlaceholders(true);
		}
 
		if ( log4jProperties == null ) {
	        try {
	        	log4jProperties = new Properties();
	        	log4jProperties.load(
					WebConfig.class.getClassLoader().getResourceAsStream("com/avnet/alapps/properties/log4j-" + tier() + ".properties")
				);
				PropertyConfigurator.configure(log4jProperties);
			} 
	        catch (IOException e) {
				e.printStackTrace();
			}
		}
 
        return propertyPlaceHolder;
    }
	
  @Bean
  public InternalResourceViewResolver getInternalResourceViewResolver() { 
    InternalResourceViewResolver resolver = new InternalResourceViewResolver();
    resolver.setPrefix("/WEB-INF/jsp/");
    resolver.setSuffix(".jsp");
    resolver.setCache(false);
    resolver.setViewClass(org.springframework.web.servlet.view.JstlView.class);
    return resolver;
  }
 
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
      super.addResourceHandlers(registry);
      if (!registry.hasMappingForPattern("/resources/**")) {
          registry.addResourceHandler("/resources/**").addResourceLocations(
                  "/WEB-INF/resources/*").setCachePeriod(CACHE_PERIOD);;

      }
  }

  @Bean
	public MessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      //messageSource.setBasenames("classpath:messages/messages", "classpath:messages/validation");
      messageSource.setBasenames("classpath:com/avnet/alapps/properties/messages");
      // if true, the key of the message will be displayed if the key is not
      // found, instead of throwing a NoSuchMessageException
      messageSource.setUseCodeAsDefaultMessage(true);
      messageSource.setDefaultEncoding("UTF-8");
      // # -1 : never reload, 0 always reload
      messageSource.setCacheSeconds(0);
	    return messageSource;
	}
  
  @Bean 
  public CommonsMultipartResolver multipartResolver() {
	  //multipartResolver.setMaxUploadSize(50000000); //can set upload limit size in bytes
	  return new CommonsMultipartResolver();
  }

  
	  @Override
	  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
	      configurer.enable();
	  }
	  
	  
	  ////////////////////////////////////////////////////////////////////////////
	  //Will need to wait until servlet 3.0 is supported here
	  /*
	  @Bean
	    public LocaleChangeInterceptor localeChangeInterceptor() {
	        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
	        localeChangeInterceptor.setParamName("locale");
	        return localeChangeInterceptor;
	    }

	    @Bean
	    public LocaleResolver localeResolver() {
	        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
	        localeResolver.setDefaultLocale(new Locale("en_US"));
	        return localeResolver;
	    }
	    
	 // implementing WebMvcConfigurer
	    @Override
	    public void addInterceptors(InterceptorRegistry registry) {
	        registry.addInterceptor(localeChangeInterceptor());
	    }
	    
	    @Override
	    public Validator getValidator() {
	        LocalValidatorFactoryBean factory = new LocalValidatorFactoryBean();
	        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	        messageSource.setBasename("classpath:com/avnet/alapps/properties/messages");
	        messageSource.setDefaultEncoding("UTF-8");
	        messageSource.setCacheSeconds(0);
	        factory.setValidationMessageSource(messageSource);
	        return factory;
	    }
	    */
	  
	  //TODO: Move these beans into its own config class
	  @Bean
	  public SecurityService securityService() {
		  return new SecurityService();
	  }
	  
	  @Bean
	  public SecurityDAO securityDao() {
		return new SecurityDAO(); 
	  }
	  
	  @Bean
	  public AdministrationDAO administrationDAO() {
		  return new AdministrationDAO();
	  }
	  
	  @Bean 
	  public EnterpriseLDAPReader enterpriseLDAPReader() {
		  return new EnterpriseLDAPReader();
	  }
	  
	  @Bean 
	  public AvnetUserDetailsService avnetUserDetailsService() {
		  return new AvnetUserDetailsService();
	  }
	  
	  @Bean 
	  public RedPrairieService redprairieService() {
		  return new RedPrairieService();
	  }
	  
	  @Bean 
	  public EvolveSalesOrderDetailService evolveSalesOrderDetailService() {
		  return new EvolveSalesOrderDetailService();
	  }
	  

	  
	  //@Bean PreAuthenticatedProcessingFilter preAuthenticatedProcessingFilter() {
		//  return new PreAuthenticatedProcessingFilter();
	  //}
	
}
