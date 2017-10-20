package com.avnet.alapps.security;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.model.gsfc.Site;
import com.avnet.alapps.model.gsfc.SiteHome;
import com.avnet.alapps.model.gsfc.UserOperation;
import com.avnet.alapps.model.gsfc.Operation;
import com.avnet.alapps.model.gsfc.UserOperationHome;
import com.avnet.alapps.model.gsfc.UserRoleOperation;
import com.avnet.alapps.model.gsfc.UserRoleOperationHome;
import com.avnet.alapps.model.gsfc.Users;
import com.avnet.alapps.model.gsfc.UsersHome;


public class AdministrationDAO  {
	
	private static Logger log = Logger.getLogger(AdministrationDAO.class);

	public Site retrieveSiteBySiteId(BigDecimal id) {
		SiteHome siteDao = new SiteHome();
		return siteDao.findById(id);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Operation> getUserOperations(String avnetGlobalUserId) throws Exception{
		// A HashSet will be used to remove duplicate operations between the user's operations and the role's operations
		Set<Operation> uniqueOpertions = new HashSet<Operation>();
		Users users = new Users();
		users.setAvnetGlobalUserId(avnetGlobalUserId);
		
		List<Users> usersList = new UsersHome().findByExample(users);
		if ( usersList != null && usersList.size() > 0 ) {
			users = (Users)new UsersHome().findByExample(users).get(0);
		}
		
		UserOperation userOperation = new UserOperation();
		userOperation.setUsers(users);

		Session session = ((SessionFactory) new InitialContext().lookup(AlAppsConstants.HIBERNATE_SESSION_NAME_GSFC)).getCurrentSession();
		List<UserOperation> userOperationList = session.createCriteria(UserOperation.class)
						.add(Restrictions.eq("users", users)).list();
		//List<UserOperation> userOperationList = new UserOperationHome().findByExample(userOperation);
		for ( UserOperation uo : userOperationList ) {
			uniqueOpertions.add(uo.getOperation());
		}
		List<UserRoleOperation> userRoleOperationList = 
									session.createCriteria(UserRoleOperation.class)
										.add(Restrictions.eq("userRole", users.getUserRole())).list();
		//UserRoleOperation userRoleOperation = new UserRoleOperation();
		//userRoleOperation.setUserRole(users.getUserRole());								
		//List<UserRoleOperation> userRoleOperationList = new UserRoleOperationHome().findByExample(userRoleOperation);
		
		for ( UserRoleOperation uro : userRoleOperationList ) {
			uniqueOpertions.add(uro.getOperation());
		}
		
		return new ArrayList<Operation>(uniqueOpertions);
	}

}