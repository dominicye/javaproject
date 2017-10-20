package com.avnet.alapps.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import com.avnet.alapps.security.LdapUser;


public class EnterpriseLDAPReader {

	private static String[] groupAttribute = { "cn" };

	public static final String ATTR_USER_ID = "uid";
	public static final String ATTR_FIRST_NAME = "givenName";
	public static final String ATTR_LAST_NAME = "sn";
	public static final String ATTR_MAIL = "mail";
	public static final String ATTR_GENESIS_ID = "avnetGenesisID";

	public static final String ATTR_CORP_NUM = "avnetCorpNum";
	public static final String ATTR_AVET_JOB_NUM = "avnetJobNum";
	public static final String ATTR_STATUS_CODE = "avnetStatusCode";
	public static final String ATTR_OBJECT_CLASS = "objectclass";
	public static final String ATTR_DEPT_NUM = "avnetDeptNum";
	public static final String ATTR_FULL_NAME = "cn";
	public static final String ATTR_BRANCH_NUM = "avnetBranchNum";

	public static final String WEBSHIP_SHIPPER = "WebShip_Shipper";
	public static final String WEBSHIP_SALES = "WebShip_Sales";
	public static final String WEBSHIP_ADMIN = "WebShip_Admin";

	
	@Value("${ldap.initial.context}") private String ldapInitialContext;
	@Value("${ldap.url}") private String ldapUrl;
	@Value("${ldap.user}") private String ldapUser;
	@Value("${ldap.password}") private String ldapPassword;
	@Value("${ldap.authentication}") private String ldapAuth;
	
	public Properties getLdapProperties() {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, ldapInitialContext);
		props.put(Context.PROVIDER_URL, ldapUrl);
		props.put(Context.SECURITY_PRINCIPAL, ldapUser);
		props.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		props.put(Context.SECURITY_AUTHENTICATION, ldapAuth);
		return props;
	}

	public LdapUser getLdapUser(String uid) {
		DirContext context = null;
		Attributes attribs = null;
		List<String> groupNames = new ArrayList<String>();

		try {
			context = new InitialDirContext(this.getLdapProperties());

			String query = "(&(objectclass=person)(uid=" + uid + "))";

			SearchControls ctrl = new SearchControls();
			ctrl.setSearchScope(SearchControls.SUBTREE_SCOPE);

			NamingEnumeration<SearchResult> personAttribsEnumeration = context.search("", query, ctrl);

			if (personAttribsEnumeration.hasMore()) {
				attribs = ((SearchResult) personAttribsEnumeration.next()).getAttributes();
				query = "(&(objectclass=groupOfNames)(member=uid=" + uid + ",*))";
				ctrl.setReturningAttributes(groupAttribute);
				NamingEnumeration<SearchResult> groupAttribsEnumeration = context.search("", query, ctrl);
				while (groupAttribsEnumeration.hasMore()) {
					groupNames.add(((BasicAttribute) ((SearchResult) groupAttribsEnumeration.next()).getAttributes().get("cn")).getAll().next().toString());
				}
				groupAttribsEnumeration.close();
			}
			personAttribsEnumeration.close();
		} catch (NamingException ne) {
			ne.printStackTrace();
		} finally {
			if (context != null) {
				try {
					context.close();
				} catch (NamingException ne) {
				}
			}
		}
		LdapUser ldapUser = null;
		if (attribs != null) {
			ldapUser = new LdapUser();
			ldapUser.setAvnetGlobalUserId(uid);
			ldapUser.setFirstName(getAttributeValue(attribs, ATTR_FIRST_NAME));
			ldapUser.setLastName(getAttributeValue(attribs, ATTR_LAST_NAME));
			ldapUser.setGenesisId(getAttributeValue(attribs, ATTR_GENESIS_ID));
			ldapUser.setEmailAddress(getAttributeValue(attribs, ATTR_MAIL));
			if (ldapUser.getEmailAddress() == null) {
				ldapUser.setEmailAddress(uid + "@avnet.com");
			}
			ldapUser.setGroupNames(groupNames);
		}
		return ldapUser;
	}

	private String getAttributeValue(Attributes attribs, String key) {
		String returnValue = null;

		if (attribs != null) {
			if (attribs.get(key) != null) {
				try {
					returnValue = attribs.get(key).get().toString();
				} catch (NamingException ne) {
					ne.printStackTrace();
				}
			}
		}

		return returnValue;
	}

	
}
