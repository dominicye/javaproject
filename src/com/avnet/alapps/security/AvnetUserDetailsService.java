package com.avnet.alapps.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import com.avnet.alapps.model.gsfc.Contact;


public class AvnetUserDetailsService implements UserDetailsService {

	private static Logger log = Logger.getLogger(AvnetUserDetailsService.class);
	//private static final Log log = LogFactory.getLog(AvnetUserDetailsService.class);
	
	@Autowired private SecurityService securityService;
	@Autowired private EnterpriseLDAPReader enterpriseLDAPReader;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
	
		
		SecurityUser userDetails = securityService.getUserByAvnetId(username);
		LdapUser ldapUser = null;
		if (userDetails == null) {
			log.info(username + " was not found.  Creating default system user.");	
			userDetails = securityService.getUserByAvnetId(PreAuthenticatedProcessingFilter.DEAFULT_SYSTEM_USER_ID);
			// Load user data from LDAP since they don't have an account in GSF
			ldapUser = enterpriseLDAPReader.getLdapUser(username);
			if (ldapUser != null) {
				userDetails.setContact(new Contact());
				userDetails.getContact().setFirstNm(ldapUser.getFirstName());
				userDetails.getContact().setLastNm(ldapUser.getLastName());
				userDetails.getContact().setDefaultEmailAddressTx(ldapUser.getEmailAddress());
			}
		}
		
		try {
			// Load Genesis ID.  It is needed for WebShip.
			if (userDetails != null && 
				username != null && 
				"Y".equalsIgnoreCase(userDetails.getInternalUserFl()) &&
				!PreAuthenticatedProcessingFilter.DEAFULT_SYSTEM_USER_ID.equals(userDetails.getAvnetGlobalUserId())) {
				if (ldapUser == null) {
					ldapUser = enterpriseLDAPReader.getLdapUser(username);
				}
				if (ldapUser != null) {
					userDetails.setGenesisId(ldapUser.getGenesisId());
				}
			}
		} catch (Exception e) {
			log.error("Problem loading Genesis ID from LDAP", e);
		}
		return userDetails;
	}

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
	
	@Autowired
	public void setEnterpriseLDAPReader(EnterpriseLDAPReader enterpriseLDAPReader) {
    	this.enterpriseLDAPReader = enterpriseLDAPReader;
    }


}
