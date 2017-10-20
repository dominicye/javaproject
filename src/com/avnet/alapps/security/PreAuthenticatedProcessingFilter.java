package com.avnet.alapps.security;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

public class PreAuthenticatedProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

	private static Logger log = Logger.getLogger(PreAuthenticatedProcessingFilter.class);
	//private static final Log log = LogFactory.getLog(PreAuthenticatedProcessingFilter.class);
	
	// This user will be used for users that request a first article but are not logged in.
	public static final String DEAFULT_SYSTEM_USER_ID = "000000";
	
	
	@Autowired private String tier;
	
	protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
		return returnUsername(request);
	}

	protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
		return returnUsername(request);
	}

	private Object returnUsername(HttpServletRequest request) {
		if (request.getUserPrincipal() == null) {
			// This will happen in a developers local environment or
			// an anonymous user is creating a first article request
			if (tier == null || "local".equals(tier)) {
				log.info("------- user is null returning user 030524 tier is " + tier);
				return "914186";
			} else {
				log.info("------- user is null returning user 000000 tier is " + tier);
				return DEAFULT_SYSTEM_USER_ID; // This is a default sales users
			}
		} else {
			String username = request.getUserPrincipal().getName();
			log.info("------- WebSeal USER " + username);
			log.info("------- WebSeal session id " + request.getSession().getId());
			if (username != null) {
				return username.toUpperCase();
			}
		} 
		return request.getUserPrincipal();
	}

	public String getTier() {
    	return tier;
    }

	@Autowired public void setTier(String tier) {
    	this.tier = tier;
    }
}
