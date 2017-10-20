package com.avnet.alapps.spring;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.avnet.alapps.common.MailSender;

@Configuration
@ComponentScan(basePackages = {"com.avnet.alapps", "com.avnet.alapps.spring", "com.avnet.alapps.controllers", "com.avnet.alapps.common"})
public class MailConfig {

	@Value("${alapps.email.host}") private String host;
    @Value("${alapps.mail.username}") private String username;
    @Value("${alapps.mail.password}") private String password;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer mailConfigProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}
  
    @Bean
    public JavaMailSender javaMailService() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();      
        javaMailSender.setHost(host);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        javaMailSender.setJavaMailProperties(getMailProperties());
        return javaMailSender;
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        //properties.setProperty("mail.debug", "false");
        return properties;
    }
    
    @Bean
    public MailSender mailSender() {
    	return new MailSender();
    }

}
