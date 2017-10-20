package com.avnet.alapps.controllers;

import java.math.BigDecimal;
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

import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;
import com.avnet.alapps.model.alapps.AstDataSource;
import com.avnet.alapps.model.alapps.AstDataType;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.ComponentTypeAttrJtableListResponse;
import com.avnet.alapps.systest.model.JtableCompTypeAttr;
import com.avnet.alapps.systest.model.JtableGenericResponse;
import com.avnet.alapps.systest.model.JtableOptionBean;
import com.avnet.alapps.systest.model.JtableOptionsResponse;
import com.avnet.alapps.systest.model.JtableResult;


@Controller
public class SysTestComponentTypeAttrController {
	private static Logger log = Logger.getLogger(SysTestComponentTypeAttrController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrCRUD.htm", method=RequestMethod.GET)
	public ModelAndView showForm() throws Exception {
		
		SecurityUser user = securityService.getAuthenticatedUser();
		if (!this.securityService.isAuthorized(user.getAvnetGlobalUserId(), AlAppsConstants.AST_ADMIN_CODE)) {
			return new ModelAndView("auth_error");
		}
		
		ModelAndView view = new ModelAndView("systest/component_type_attr_crud");
		List<AstCompType> list =  this.getSysTestService().getComponentTypes(false, "typeDs", "ASC");
		view.getModel().put("componentTypeList", list);	
		return view;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrList.json", method=RequestMethod.POST)
	public @ResponseBody ComponentTypeAttrJtableListResponse getComponentTypeAttrList(
										@RequestParam BigDecimal compTypeId,
										@RequestParam String jtSorting,
										@RequestParam Integer jtStartIndex,
										@RequestParam Integer jtPageSize) throws Exception {
			ComponentTypeAttrJtableListResponse resp = new ComponentTypeAttrJtableListResponse();
			try{	
				String[] sort = jtSorting.split(" ");
				SysTestService serv = this.getSysTestService();
				List<JtableCompTypeAttr> list = serv.getComponentTypeAttributesPaginated(compTypeId, false, sort[0], sort[1], jtStartIndex, jtPageSize);
				Long totalCount = serv.getComponentTypeAttributesCount(compTypeId, false);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecords(list);
				resp.setTotalRecordCount(totalCount.intValue());
			} 
			catch (Exception ex) {
				log.error("Could not get component type attr list paginated: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/dataSourceOptions.json", method=RequestMethod.POST)
	public @ResponseBody JtableOptionsResponse getDataSourceOptions() throws Exception {
			JtableOptionsResponse resp = new JtableOptionsResponse();
			try{	
				List<JtableOptionBean> opts = new ArrayList<JtableOptionBean>();
				List<AstDataSource> list =  this.getSysTestService().getDataSources();
				if ( list != null ) {
					opts.add(new JtableOptionBean("", null));
					for (AstDataSource ds : list) {
						opts.add(
								new JtableOptionBean(ds.getSourceNm(), ds.getDataSourceId().toPlainString())
								);
					}
				}
				resp.setResult(JtableResult.OK.toString());
				resp.setOptions(opts);
			} 
			catch (Exception ex) {
				log.error("Could not get data source options: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/dataTypeOptions.json", method=RequestMethod.POST)
	public @ResponseBody JtableOptionsResponse getDataTypeOptions() throws Exception {
			JtableOptionsResponse resp = new JtableOptionsResponse();
			try{	
				List<JtableOptionBean> opts = new ArrayList<JtableOptionBean>();
				List<AstDataType> list =  this.getSysTestService().getDataTypes();
				if ( list != null ) {
					opts.add(new JtableOptionBean("", null));
					for (AstDataType dt : list) {
						opts.add(
								new JtableOptionBean(dt.getTypeNm(), dt.getDataTypeId().toPlainString())
								);
					}
				}
				resp.setResult(JtableResult.OK.toString());
				resp.setOptions(opts);
			} 
			catch (Exception ex) {
				log.error("Could not get data type options: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrCreate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse createComponentTypeAttr(
			@ModelAttribute JtableCompTypeAttr cta, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				if ( cta.getKeyFl()== null ) {
					cta.setKeyFl("N");
				}
				if ( cta.getActiveFl() == null ) {
					cta.setActiveFl("N");
				}
				if ( cta.getEditableFl() == null ) {
					cta.setEditableFl("N");
				}
				cta = this.getSysTestService().addComponentTypeAttribute(cta, user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(cta);
			} 
			catch (Exception ex) {
				log.error("Could not CREATE component type attr: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrUpdate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse updateComponentTypeAttr(
			@ModelAttribute JtableCompTypeAttr cta, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				if ( cta.getKeyFl()== null ) {
					cta.setKeyFl("N");
				}
				if ( cta.getActiveFl() == null ) {
					cta.setActiveFl("N");
				}
				if ( cta.getEditableFl() == null ) {
					cta.setEditableFl("N");
				}
				SysTestService serv = this.getSysTestService();
				
				//UI was designed to prohibit change of comp type, so grid wont send it.
				//Get current value of comp type.
				AstCompTypeAttr attr = serv.getComponentTypeAttribute(cta.getCompTypeAttrId());
				cta.setCompTypeId(attr.getAstCompType().getCompTypeId());
				
				cta = serv.updateComponentTypeAttribute(cta,user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(cta);
			} 
			catch (Exception ex) {
				log.error("Could not UPDATE component type attr: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrDelete.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse deleteComponentTypeAttr(
			@ModelAttribute JtableCompTypeAttr cta, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				if ( cta.getKeyFl()== null ) {
					cta.setKeyFl("N");
				}
				if ( cta.getActiveFl() == null ) {
					cta.setActiveFl("N");
				}
				if ( cta.getEditableFl() == null ) {
					cta.setEditableFl("N");
				}
				cta = this.getSysTestService().deleteComponentTypeAttribute(cta, user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(cta);
			} 
			catch (Exception ex) {
				log.error("Could not DELETE component type attr: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrOptions.json", method=RequestMethod.POST)
	public @ResponseBody JtableOptionsResponse getComponentTypeAttrOptions(
			@RequestParam BigDecimal compTypeId) throws Exception {
			JtableOptionsResponse resp = new JtableOptionsResponse();
			try{	
				List<JtableOptionBean> opts = new ArrayList<JtableOptionBean>();
				List<AstCompTypeAttr> list =  (new SysTestService()).getComponentTypeAttributes(compTypeId, false, "attrNm", "ASC");
				if ( list != null ) {
					opts.add(new JtableOptionBean("", null));
					for (AstCompTypeAttr cta : list) {
						opts.add(
								new JtableOptionBean(cta.getAttrNm(), cta.getCompTypeAttrId().toPlainString())
								);
					}
				}
				resp.setResult(JtableResult.OK.toString());
				resp.setOptions(opts);
			} 
			catch (Exception ex) {
				log.error("Could not get component type attr options: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/componentTypeAttrOptionsBySource.json", method=RequestMethod.POST)
	public @ResponseBody JtableOptionsResponse getComponentTypeAttrOptionsBySource(
			@RequestParam BigDecimal compTypeId,
			@RequestParam String dataSourceName) throws Exception {
			JtableOptionsResponse resp = new JtableOptionsResponse();
			try{	
				List<JtableOptionBean> opts = new ArrayList<JtableOptionBean>();
				
				List<AstCompTypeAttr> list =  
					(new SysTestService()).getComponentTypeAttributesBySourceName(
							dataSourceName, 
							compTypeId, 
							false, 
							"attrNm", 
							"ASC"
							);
				
				if ( list != null ) {
					//opts.add(new JtableOptionBean("", null));
					for (AstCompTypeAttr cta : list) {
						opts.add(
								new JtableOptionBean(cta.getAttrDs(), cta.getCompTypeAttrId().toPlainString())
								);
					}
				}
				resp.setResult(JtableResult.OK.toString());
				resp.setOptions(opts);
			} 
			catch (Exception ex) {
				log.error("Could not get component type attr options: ", ex);
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

	//TODO: Need to set this up for autowire
	public SysTestService getSysTestService() {
		return new SysTestService();
	}
	
	
}
