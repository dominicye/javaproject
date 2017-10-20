package com.avnet.alapps.controllers;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;

@Controller
public class DefaultController {
	
	@Autowired private SecurityService securityService;
	
	
   @RequestMapping(value = "/", method = RequestMethod.GET)
   @Transactional(readOnly = true)
   public String index(ModelMap map) throws NamingException, UnknownHostException {
	   
	   SecurityUser user = this.securityService.getAuthenticatedUser();
	 
	   map.put("securityUser", user);
	   map.put("hostname", InetAddress.getLocalHost().getHostName());
	   
       return "index";
   } 
   
   @Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
}
