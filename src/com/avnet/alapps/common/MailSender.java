package com.avnet.alapps.common;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import com.avnet.alapps.spring.WebConfig;

public class MailSender {
	private static Logger log = Logger.getLogger(MailSender.class);
	private static final String SENDER_EMAIL = "global_shop_floor@avnet.com";

	@Autowired private JavaMailSender javaMailSender;
	private static String tier;
	private String mailSubjectPrefix;
	private static String disclaimer;
	private static String disclaimerHtml;
	@Value("${alapps.developer.email}") private String developerEmailAddress; 
	
	
	static {
		StringBuilder sb = new StringBuilder();
		sb.append("\n***********************************************************************************************\n");
		sb.append("DISCLAIMER: The from email address is only used to send emails.  Please do not reply to this email.\n");
		sb.append("External Users - If you have a question about this email please contact your Avnet salesperson.\n");
		sb.append("Internal Users - If you have a question about this email please contact the Global Shop Floor support team.\n");
		sb.append("***********************************************************************************************\n");
		disclaimer = sb.toString();
		
		StringBuilder sbHtml = new StringBuilder();
		sbHtml.append("<br/>***********************************************************************************************<br/>");
		sbHtml.append("DISCLAIMER: The from email address is only used to send emails.  Please do not reply to this email.<br/>");
		sbHtml.append("External Users - If you have a question about this email please contact your Avnet salesperson.<br/>");
		sbHtml.append("Internal Users - If you have a question about this email please contact the Global Shop Floor support team.<br/>");
		sbHtml.append("***********************************************************************************************<br/>");
		disclaimerHtml = sbHtml.toString();
	}
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer mailSenderProperties(){
		return WebConfig.propertySourcesPlaceholderConfigurer();
	}

	public void sendMessage(
			final String emailAddress, 
			final String mailSubject, 
			final String body,
			final boolean htmlBody) {
		sendMessage(emailAddress,mailSubject,body,htmlBody,1);
	}
	
	//Priority 1 highest to priority 5 lowest
	public void sendMessage(
			String email, 
			final String mailSubject, 
			final String body,
			final boolean htmlBody,
			final int priority) {
		if (email == null) return;
				
		final String emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? developerEmailAddress : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		log.info("--------- 1 send email to " + emailAddress);
		try {
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//log.info("--------- 1 prepare email to " + emailAddress);
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(priority);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}
	
	public void sendMessageWithAttachment(
			final String email, 
			final String mailSubject, 
			final String body,
			final boolean htmlBody,
			final File attachment) {
		if (email == null) return;
		final String emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? developerEmailAddress : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		try {
			log.info("--------- 2 send email to " + emailAddress);
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//log.info("--------- 2 prepare email to " + emailAddress);
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(1);
					message.addAttachment(attachment.getName(), attachment);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}	

	public void sendMessage(
			final String email[], 
			final String mailSubject, 
			final String body,
			final boolean htmlBody) {
		final String[] emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? new String[] { developerEmailAddress } : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		try {
			logEmailList(emailAddress, "3 send email to ");
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//logEmailList(emailAddress, "3 prepare email to ");
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(1);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}
	
	
	public void sendMessage(
			final String email[], 
			final String ccEmailAddress[], 
			final String mailSubject, 
			final String body,
			final boolean htmlBody) {
		final String[] emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? new String[] {developerEmailAddress} : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		try {
			logEmailList(emailAddress, "4 send email to ");
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//logEmailList(emailAddress, "3 prepare email to ");
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					message.setCc(ccEmailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(1);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress.toString() + "]", e);
		}
	}

	public void sendMessage(
			final String emailAddress, 
			final String mailSubject, 
			final String body, 
			final String attachmentName,
			final File attachment,
			final boolean htmlBody) {
		this.sendMessage(emailAddress, mailSubject, body, attachmentName, attachment, htmlBody, 1);
	}

	public void sendMessage(
			final String email, 
			final String mailSubject, 
			final String body, 
			final String attachmentName,
			final File attachment,
			final boolean htmlBody,
			final int priority) {
		if (email == null) return;
		final String emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? developerEmailAddress : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		try {
			log.info("--------- 5 send email to " + emailAddress);
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//log.info("--------- 5 prepare email to " + emailAddress);
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(priority);
					if (attachment != null)
						message.addAttachment(attachmentName, attachment);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}

	public void sendMessage(
			final String[] emailAddress, 
			final String mailSubject, 
			final String body, 
			final String attachmentName,
			final File attachment,
			final boolean htmlBody) {
		this.sendMessage(emailAddress, mailSubject, body, attachmentName, attachment, htmlBody, 1);
	}

	public void sendMessage(
			final String[] email, 
			final String mailSubject, 
			final String body, 
			final String attachmentName,
			final File attachment,
			final boolean htmlBody,
			final int priority) {
		final String[] emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? new String[] {developerEmailAddress} : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		try {
			logEmailList(emailAddress, "6 send email to ");
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//logEmailList(emailAddress, "6 prepare email to ");
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(priority);
					if (attachment != null)
						message.addAttachment(attachmentName, attachment);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}

	public void sendInfoMessage(
			final String email, 
			final String mailSubject, 
			final String body,
			final boolean htmlBody) {
		if (email == null) return;
		final String emailAddress = ( !"PROD".equalsIgnoreCase(tier) ) ? developerEmailAddress : email;
		final String newBody = ( !"PROD".equalsIgnoreCase(tier) ) ? 
				body + " \n TIER is " + tier + ": Would have been sent to: " + email + "\n"
				: body;
		log.info("--------- 1 send email to " + emailAddress);
		try {
			javaMailSender.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					//log.info("--------- 1 prepare email to " + emailAddress);
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					message.setFrom(SENDER_EMAIL);
					message.setTo(emailAddress);
					if (mailSubjectPrefix == null)
						message.setSubject(mailSubject);
					else
						message.setSubject(mailSubjectPrefix + mailSubject);
					if (htmlBody)
						message.setText(newBody+disclaimerHtml, htmlBody);
					else
						message.setText(newBody+disclaimer, htmlBody);
					message.setPriority(0);
				}
			});
		} catch (Exception e) {
			log.error("Problem Sending Email to [" + emailAddress + "]", e);
		}
	}

	
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	@Autowired
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	public void setTier(String t) {
		tier = t;
		if (tier != null && !"prod".equalsIgnoreCase(tier)) {
			mailSubjectPrefix = tier.toUpperCase() + " ";
		}
	}

	public String getTier() {
		return tier;
	}
	
	private void logEmailList(String[] emails, String message) {
		for (int i=0;i<emails.length;i++) {
			log.info("--------- " + message + " " + emails[i]);
		}
	}
}
