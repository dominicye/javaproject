package com.avnet.alapps.security;

import java.math.BigDecimal;
import java.util.List;
import com.avnet.alapps.model.gsfc.Users;
import com.avnet.alapps.model.gsfc.UsersHome;


public class SecurityDAO {
	

	//private static Logger log = Logger.getLogger(SecurityDAO.class);

	@SuppressWarnings("unchecked")
	//Transactional(readOnly = true)
	public SecurityUser getUserByAvnetId(String avnetGlobalUserId) {
		
	
		Users users = new Users();
		users.setAvnetGlobalUserId(avnetGlobalUserId);
		List<Users> usersList = (List<Users>)new UsersHome().findByExample(users);
		if ( usersList != null && usersList.size() > 0 ) {
			users = usersList.get(0);
		}
		SecurityUser user = new SecurityUser(users);
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public SecurityUser getUserByUserId(BigDecimal userId) {

		Users users = new Users();
		users.setUserId(userId);
		List<Users> usersList = (List<Users>)new UsersHome().findByExample(users);
		users = usersList.get(0);
		
		SecurityUser user = new SecurityUser(users);
		return user;
	}
}
