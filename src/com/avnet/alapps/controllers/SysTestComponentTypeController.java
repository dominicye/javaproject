package com.avnet.alapps.controllers;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.avnet.alapps.common.AjaxResponse;
import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.ComponentTypeJtableListResponse;
import com.avnet.alapps.systest.model.JtableCompType;
import com.avnet.alapps.systest.model.JtableGenericResponse;
import com.avnet.alapps.systest.model.JtableOptionBean;
import com.avnet.alapps.systest.model.JtableOptionsResponse;
import com.avnet.alapps.systest.model.JtableResult;


@Controller
public class SysTestComponentTypeController {
	private static Logger log = Logger.getLogger(SysTestComponentTypeController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	
	@RequestMapping(value="/systest/componentTypeCRUD.htm", method=RequestMethod.GET)
	public ModelAndView showForm() throws Exception {
		
		SecurityUser user = securityService.getAuthenticatedUser();
		if (!this.securityService.isAuthorized(user.getAvnetGlobalUserId(), AlAppsConstants.AST_ADMIN_CODE)) {
			return new ModelAndView("auth_error");
		}
		
		return new ModelAndView("systest/component_type_crud");
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeList.json", method=RequestMethod.POST)
	public @ResponseBody ComponentTypeJtableListResponse getComponentTypeList(
										@RequestParam String jtSorting,
										@RequestParam Integer jtStartIndex,
										@RequestParam Integer jtPageSize) throws Exception {
			ComponentTypeJtableListResponse resp = null;
			try{	
				String[] sort = jtSorting.split(" ");
				SysTestService serv = new SysTestService();
				List<JtableCompType> list = serv.getComponentTypesPaginated(false, sort[0], sort[1], jtStartIndex, jtPageSize);
				Long totalCount = serv.getComponentTypesCount(false);
				resp = new ComponentTypeJtableListResponse();
				resp.setResult(JtableResult.OK.toString());
				resp.setRecords(list);
				resp.setTotalRecordCount(totalCount.intValue());
			} 
			catch (Exception ex) {
				log.error("Could not get component type list paginated: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeOptions.json", method=RequestMethod.POST)
	public @ResponseBody JtableOptionsResponse getComponentTypeOptions() throws Exception {
			JtableOptionsResponse resp = new JtableOptionsResponse();
			try{	
				List<JtableOptionBean> opts = new ArrayList<JtableOptionBean>();
				List<AstCompType> list =  (new SysTestService()).getComponentTypes(false, "typeDs", "ASC");
				if ( list != null ) {
					opts.add(new JtableOptionBean("", null));
					for (AstCompType ct : list) {
						opts.add(
								new JtableOptionBean(ct.getTypeDs(), ct.getCompTypeId().toPlainString())
								);
					}
				}
				resp.setResult(JtableResult.OK.toString());
				resp.setOptions(opts);
			} 
			catch (Exception ex) {
				log.error("Could not get component type options: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeCreate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse createComponentType(
			@ModelAttribute JtableCompType ct, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{	
				SecurityUser user = securityService.getAuthenticatedUser();
				if ( ct.getActiveFl() == null ) {
					ct.setActiveFl("N");
				}
				
				ct = (new SysTestService()).addComponentType(ct, user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(ct);
			} 
			catch (Exception ex) {
				log.error("Could not CREATE component type: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeUpdate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse updateComponentType(
			@ModelAttribute JtableCompType ct, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{	
				if ( ct.getActiveFl() == null ) {
					ct.setActiveFl("N");
				}
				SecurityUser user = securityService.getAuthenticatedUser();
				ct = (new SysTestService()).updateComponentType(ct, user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(ct);
			} 
			catch (Exception ex) {
				log.error("Could not UPDATE component type: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeDelete.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse deleteComponentType(
			@ModelAttribute JtableCompType ct, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				if ( ct.getActiveFl() == null ) {
					ct.setActiveFl("N");
				}
				SecurityUser user = securityService.getAuthenticatedUser();
				ct = (new SysTestService()).deleteComponentType(ct,user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(ct);
			} 
			catch (Exception ex) {
				log.error("Could not DELETE component type: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	public MailSender getMailSender() {
		return mailSender;
	}

	@Autowired
	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public String getTier() {
		return tier;
	}

	@Autowired
	public void setTier(String tier) {
		this.tier = tier;
	}

	@Autowired
	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}
}
