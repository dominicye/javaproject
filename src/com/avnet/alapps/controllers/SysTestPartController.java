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

import com.avnet.alapps.common.AjaxResponse;
import com.avnet.alapps.common.AlAppsConstants;
import com.avnet.alapps.common.MailSender;
import com.avnet.alapps.model.alapps.AstCompType;
import com.avnet.alapps.model.alapps.AstCompTypeAttr;
import com.avnet.alapps.model.alapps.AstPart;
import com.avnet.alapps.model.alapps.AstPartAttr;
import com.avnet.alapps.security.SecurityService;
import com.avnet.alapps.security.SecurityUser;
import com.avnet.alapps.services.SysTestService;
import com.avnet.alapps.systest.model.JtableGenericResponse;
import com.avnet.alapps.systest.model.JtablePart;
import com.avnet.alapps.systest.model.JtablePartAttr;
import com.avnet.alapps.systest.model.JtableResult;
import com.avnet.alapps.systest.model.PartAttrJtableListResponse;
import com.avnet.alapps.systest.model.PartJtableListResponse;


@Controller
public class SysTestPartController {
	private static Logger log = Logger.getLogger(SysTestPartController.class);
	@Autowired private SecurityService securityService;
	@Autowired private MailSender mailSender;
	@Autowired private String tier;
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/partCRUD.htm", method=RequestMethod.GET)
	public ModelAndView showForm() throws Exception {
		
		SecurityUser user = securityService.getAuthenticatedUser();
		if (!this.securityService.isAuthorized(user.getAvnetGlobalUserId(), AlAppsConstants.AST_ADMIN_CODE)) {
			return new ModelAndView("auth_error");
		}
		
		ModelAndView view = new ModelAndView("systest/part_crud");
		List<AstCompType> list =  this.getSysTestService().getComponentTypes(false, "typeDs", "ASC");
		view.getModel().put("componentTypeList", list);	
		return view;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/partList.json", method=RequestMethod.POST)
	public @ResponseBody PartJtableListResponse getPartList(
										@RequestParam BigDecimal compTypeId,
										@RequestParam String jtSorting,
										@RequestParam Integer jtStartIndex,
										@RequestParam Integer jtPageSize) throws Exception {
		PartJtableListResponse resp = new PartJtableListResponse();
			try{	
				String[] sort = jtSorting.split(" ");
				SysTestService serv = this.getSysTestService();
				List<JtablePart> list = serv.getJtablePartsPaginated(compTypeId, false, sort[0], sort[1], jtStartIndex, jtPageSize);
				Long totalCount = serv.getPartsCount(compTypeId, false);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecords(list);
				resp.setTotalRecordCount(totalCount.intValue());
			} 
			catch (Exception ex) {
				log.error("Could not get part list paginated: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/partCreate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse createPart(
			@ModelAttribute JtablePart p, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				SysTestService serv = this.getSysTestService();
				JtablePart existingPart = 
					serv.getPartByActivePartKeyAttributeValue(p.getKeyValue());
				if ( existingPart != null ) {
					resp.setResult(JtableResult.ERROR.toString());
					resp.setMessage("Part with active part key value aleady exists: " + p.getKeyValue());
					return resp;
				}
				
				if ( p.getActiveFl() == null ) {
					p.setActiveFl("N");
				}
				p = serv.addPartWithBlankAttributes(p, user);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(p);
			} 
			catch (Exception ex) {
				log.error("Could not CREATE part: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/partUpdate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse updatePart(
			@ModelAttribute JtablePart p, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				if ( p.getActiveFl() == null ) {
					p.setActiveFl("N");
				}
				SysTestService serv = this.getSysTestService();
				p = serv.updatePart(p, user);
				resp.setRecord(p);
			
				resp.setResult(JtableResult.OK.toString());
			} 
			catch (Exception ex) {
				log.error("Could not UPDATE part: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/partDelete.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse deletePart(
			@ModelAttribute JtablePart p, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				if ( p.getActiveFl() == null ) {
					p.setActiveFl("N");
				}
				p = (new SysTestService()).deletePart(p);
				resp.setResult(JtableResult.OK.toString());
				resp.setRecord(p);
			} 
			catch (Exception ex) {
				log.error("Could not DELETE part: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/partAttrList.json", method=RequestMethod.POST)
	public @ResponseBody PartAttrJtableListResponse getPartAttrList(
										@RequestParam BigDecimal partId) throws Exception {
		PartAttrJtableListResponse resp = null;
			try{	
				SysTestService serv = this.getSysTestService();
				List<JtablePartAttr> list = serv.getPartAttrs(partId);
				resp = new PartAttrJtableListResponse();
				resp.setResult(JtableResult.OK.toString());
				resp.setRecords(list);
				resp.setTotalRecordCount(list.size());
			} 
			catch (Exception ex) {
				log.error("Could not get part attr list: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = true, timeout = 600)
	@RequestMapping(value="/systest/partAttrBySourceList.json", method=RequestMethod.POST)
	public @ResponseBody PartAttrJtableListResponse getPartAttrList(
										@RequestParam BigDecimal partId,
										@RequestParam String dataSourceName) throws Exception {
		PartAttrJtableListResponse resp = null;
			try{	
				SysTestService serv = this.getSysTestService();
				List<JtablePartAttr> list = serv.getPartAttrsBySource(partId, dataSourceName);
				//List<JtablePartAttr> list = serv.getPartAttrs(partId);
				resp = new PartAttrJtableListResponse();
				resp.setResult(JtableResult.OK.toString());
				resp.setRecords(list);
				resp.setTotalRecordCount(list.size());
			} 
			catch (Exception ex) {
				log.error("Could not get part attr by source list: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/partAttrUpdate.json", method=RequestMethod.POST)
	public @ResponseBody JtableGenericResponse updatePartAttr(
			@ModelAttribute JtablePartAttr pa, BindingResult result) throws Exception {
			JtableGenericResponse resp = new JtableGenericResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				SysTestService serv = this.getSysTestService();
				AstPartAttr currAttr = serv.getPartAttrById(pa.getPartAttrId());
				AstCompTypeAttr cta = serv.getComponentTypeAttribute(currAttr.getAstCompTypeAttr().getCompTypeAttrId());
				if ( "Y".equalsIgnoreCase(cta.getActiveFl()) && "Y".equalsIgnoreCase(cta.getKeyFl()) ) {
					JtablePart part = serv.getPartByActivePartKeyAttributeValue(pa.getValueTx());
					if ( part != null && !part.getPartId().equals(currAttr.getAstPart().getPartId()) ) {
						resp.setResult(JtableResult.ERROR.toString());
						resp.setMessage("Active part key value already exists for another part: " + pa.getValueTx());
						return resp;
					}
				}
				pa = serv.updatePartAttrValue(pa, user);
				resp.setRecord(pa);
				resp.setResult(JtableResult.OK.toString());
			} 
			catch (Exception ex) {
				log.error("Could not UPDATE part attr: ", ex);
				resp.setResult(JtableResult.ERROR.toString());
				resp.setMessage(ex.getMessage());
			}
			return resp;
	}
	
	
	@Transactional(readOnly = false, timeout = 600)
	@RequestMapping(value="/systest/addMissingPartAttrs.json", method=RequestMethod.POST)
	public @ResponseBody AjaxResponse addMissingPartAttrs(
			@RequestParam String[] partIds) throws Exception {
			AjaxResponse ajaxResponse = new AjaxResponse();
			try{
				SecurityUser user = securityService.getAuthenticatedUser();
				SysTestService serv = this.getSysTestService();
				StringBuilder message = new StringBuilder();
				if ( partIds != null && partIds.length > 0 ) {
					for ( String partId : partIds ) {
						message.append(serv.addPartMissingAttributes(new BigDecimal(partId), user)).append("<br>");
					}
				}
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_SUCCESS);
			    ajaxResponse.setResult(message.toString()); 
			} 
			catch (Exception ex) {
				log.error("Could not add missing attributes to parts: ", ex);
				ajaxResponse.setStatus(AjaxResponse.RESPONSE_TYPE_FAIL);
				ajaxResponse.setResult(ex);
			}
			return ajaxResponse;
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
