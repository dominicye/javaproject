package com.avnet.alapps.security;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;


import com.avnet.alapps.model.gsfc.Users;
import com.avnet.alapps.model.gsfc.Operation;




public class SecurityService {
	private static Logger log = Logger.getLogger(SecurityService.class);
	//private static final Log log = LogFactory.getLog(SecurityService.class);

	@Autowired private SecurityDAO securityDao;
	@Autowired private AdministrationDAO administrationDAO;
	@Autowired private EnterpriseLDAPReader enterpriseLDAPReader;

	
	public Users getUserWithLDAPDataByAvnetId(String avnetId) {
		SecurityUser user = getUserByAvnetId(avnetId);
		try {
			if (user != null) {
				LdapUser ldapUser = enterpriseLDAPReader.getLdapUser(avnetId);
				if (ldapUser != null) {
					user.setGenesisId(ldapUser.getGenesisId());
					user.setAvnetGlobalUserId(avnetId);
					if ( user.getContact() == null ) { //User does not have profile in GSFC
						user.setFullName(ldapUser.getFirstName() + " " + ldapUser.getLastName());
					}
				}
			}
		} catch (Exception e) {
			log.error("Problem loading LDAP user data", e);
		}
		return user;
	}

	public SecurityUser getAuthenticatedUser() {
		SecurityUser user = null;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getPrincipal() instanceof SecurityUser) {
			user = (SecurityUser) auth.getPrincipal();
		} else {
			// This will happen when a JMS reponse is received. We need to return a valid user to record the update.
			log.info("---------- returning default system user");
			user = getUserByAvnetId(PreAuthenticatedProcessingFilter.DEAFULT_SYSTEM_USER_ID);
		}
		return user;
	}

	//Transactional(readOnly = true)
	public SecurityUser getUserByAvnetId(String avnetId) {
		SecurityUser user = securityDao.getUserByAvnetId(avnetId);
		if (user == null) return user;
		populateUserSiteAndAuthorityInfo(user);
		return user;

	}

	public void populateUserSiteAndAuthorityInfo(SecurityUser user) {
		try {
			ArrayList<Operation> operations = administrationDAO.getUserOperations(user.getAvnetGlobalUserId());
			user.setCustomerizedOperations(operations);
			ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			Operation operation = null;
			for (int i = 0; i < operations.size(); i++) {
				operation = (Operation) operations.get(i);
				
			
				authorities.add(new SimpleGrantedAuthority(operation.getOperationCd()));
			}

			user.setAuthorities(authorities);
		} catch (Exception e) {
			log.error(e);
		}
	}

	public boolean isAuthorized(String avnetGlobalId, String operationCode) throws Exception {
		if (operationCode == null || avnetGlobalId == null) return false;	
		List<String> userAuthCodes = new ArrayList<String>();
		SecurityUser authUser = getAuthenticatedUser();
		List<Operation> operations = null;
		if (authUser.getAvnetGlobalUserId().equals(avnetGlobalId)) {
			operations = authUser.getCustomerizedOperations();
		} 
		else {
			operations = administrationDAO.getUserOperations(avnetGlobalId);
		}
		boolean access = false;
		if (operations != null) {
			for ( Operation op : operations ) {
				userAuthCodes.add(op.getOperationCd());
			}
			access = userAuthCodes.contains(operationCode);
			if (!access) {
				// Check if the user has the ADMIN persmission.
				// ADMIN permission has access to everything.
				access = userAuthCodes.contains(SecurityOperationConstants.IT_ADMINISTRATION);
			}
		}
		return access;
	}

	
	@Autowired 
	public void setAdministrationDAO(AdministrationDAO adminDao) {
		this.administrationDAO = adminDao;
	}
	
	@Autowired 
	public void setSecurityDao(SecurityDAO securityDao) {
		this.securityDao = securityDao;
	}
	
	@Autowired 
	public void setEnterpriseLDAPReader(EnterpriseLDAPReader enterpriseLDAPReader) {
		this.enterpriseLDAPReader = enterpriseLDAPReader;
	}
}
