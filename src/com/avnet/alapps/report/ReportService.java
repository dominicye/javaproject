package com.avnet.alapps.report;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporter;
import net.sf.jasperreports.engine.export.JRHtmlExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
//import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
//import net.sf.jasperreports.engine.export.JRXhtmlExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import com.avnet.alapps.report.model.ProgramRevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocation;
import com.avnet.alapps.report.model.RevenueVolumeLocationCustomerDivision;
import com.avnet.alapps.systest.model.NutanixCustomerPackingSlipLine;
import com.avnet.alapps.systest.model.NutanixPackingSlip;
import com.avnet.alapps.systest.model.NutanixPackingSlipLine;
import com.avnet.alapps.systest.model.TestResultReportItem;



public class ReportService {
	private static Logger log = Logger.getLogger(ReportService.class);
	private static String SUBREPORT_DIR = null;	
	private static final DefaultResourceLoader resourceLoader = new DefaultResourceLoader(); 
	private static Resource reportImagePath = resourceLoader.getResource("classpath:WEB-INF/resources/images/");
	private static Resource generationPath = resourceLoader.getResource("classpath:WEB-INF/tempreport/");
	private static Resource virtualizerPath = resourceLoader.getResource("classpath:WEB-INF/tempvirtualizer/");
	private static final int VIRT_MAX = 4;
	
	
	private static final String JRXML_ROOT = "com/avnet/alapps/report/jrxml";
	private static final String PROGRAM_REVENUE_VOLUME_LOCATION_REPORT_PATH = JRXML_ROOT + "/ProgramRevenueVolumeLocation.jasper";
	private static final String REVENUE_VOLUME_LOCATION_REPORT_PATH = JRXML_ROOT + "/RevenueVolumeLocation.jasper";
	private static final String PROGRAM_REVENUE_VOLUME_LOCATION_CUSTOMER_DIVISION_REPORT_PATH = JRXML_ROOT + "/RevenueVolumeLocationCustomerDivision.jasper";
	private static final String NUTANIX_PACKING_SLIP_REPORT_PATH = JRXML_ROOT + "/NutanixPackingSlip.jasper";
	private static final String NUTANIX_CUSTOMER_PACKING_SLIP_REPORT_PATH = JRXML_ROOT + "/NutanixCustomerPackingSlip.jasper";
	private static final String AST_DAILY_TEST_RESULTS_REPORT_PATH = JRXML_ROOT + "/AstDailyTestResults.jasper";
	
	
	
	static {
		try {
			File subreportDirFD = new ClassPathResource(JRXML_ROOT).getFile();
			SUBREPORT_DIR = subreportDirFD.getAbsolutePath() + "/";
		}
		catch (Exception e) {
			log.error("Could not initialize SUBREPORT_DIR!!! Somre reports containing subreports will fail in DEV, TEST, and PROD!", e);
		}

		System.setProperty("net.sf.jasperreports.subreport.runner.factory", 
					"net.sf.jasperreports.engine.fill.JRContinuationSubreportRunnerFactory");

	}
	
	public byte[] generateProgramRevenueVolumeLocationReport(List<ProgramRevenueVolumeLocation> beanList, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(PROGRAM_REVENUE_VOLUME_LOCATION_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		
		return returnByteArray;
	}
	
	public byte[] generateRevenueVolumeLocationReport(List<RevenueVolumeLocation> beanList, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(REVENUE_VOLUME_LOCATION_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnByteArray;
	}
	
	public byte[] generateRevenueVolumeLocationCustomerDivisionReport(List<RevenueVolumeLocationCustomerDivision> beanList, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(PROGRAM_REVENUE_VOLUME_LOCATION_CUSTOMER_DIVISION_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnByteArray;
	}
	

	public byte[] generateAllFinancialReports(List<ProgramRevenueVolumeLocation> beanList1, 
										List<RevenueVolumeLocation> beanList2,
										List<RevenueVolumeLocationCustomerDivision> beanList3, 
										List<ProgramRevenueVolumeLocation> beanList4, 
										List<RevenueVolumeLocation> beanList5,
										List<RevenueVolumeLocationCustomerDivision> beanList6, 
										ReportExportTypeEnum reportType, 
										HttpServletRequest request) {
		JRSwapFileVirtualizer virtualizer = null;
		byte[] returnArray = null;
		try {	
			Map<String, Object> parameters = new HashMap<String, Object>();
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			
			List<JRDataSource> dsList = new ArrayList<JRDataSource>();
			dsList.add(new JRBeanCollectionDataSource(beanList1, false));
			dsList.add(new JRBeanCollectionDataSource(beanList2, false));
			dsList.add(new JRBeanCollectionDataSource(beanList3, false));
			dsList.add(new JRBeanCollectionDataSource(beanList4, false));
			dsList.add(new JRBeanCollectionDataSource(beanList5, false));
			dsList.add(new JRBeanCollectionDataSource(beanList6, false));
			
			List<String> jasperReportPathList = new ArrayList<String>();
			jasperReportPathList.add(PROGRAM_REVENUE_VOLUME_LOCATION_REPORT_PATH);
			jasperReportPathList.add(REVENUE_VOLUME_LOCATION_REPORT_PATH);
			jasperReportPathList.add(PROGRAM_REVENUE_VOLUME_LOCATION_CUSTOMER_DIVISION_REPORT_PATH);
			jasperReportPathList.add(PROGRAM_REVENUE_VOLUME_LOCATION_REPORT_PATH);
			jasperReportPathList.add(REVENUE_VOLUME_LOCATION_REPORT_PATH);
			jasperReportPathList.add(PROGRAM_REVENUE_VOLUME_LOCATION_CUSTOMER_DIVISION_REPORT_PATH);
			
			String[] sheetNames = new String[6];
			sheetNames[0] = "Prog Revenue Vol";
			sheetNames[1] = "Revenue Vol Loc";
			sheetNames[2] = "Revenue Vol Div";
			sheetNames[3] = "YTD Prog Revenue Vol";
			sheetNames[4] = "YTD Revenue Vol Loc";
			sheetNames[5] = "YTD Revenue Vol Div";
			
			returnArray = getJasperReportAsByteArrayMulti(jasperReportPathList, sheetNames, request, parameters, dsList, reportType);
		} 
		catch (Exception e) {
			log.error("Could not generate report All Financial ", e);
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnArray;
	}
	
	
	public byte[] generateNutanixPackingSlip(NutanixPackingSlip slip, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		List<NutanixPackingSlip> beanList = new ArrayList<NutanixPackingSlip>();
		beanList.add(slip);
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			parameters.put("REPORT_IMAGE_PATH", reportImagePath.getFile().getAbsolutePath() + "/");
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(NUTANIX_PACKING_SLIP_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnByteArray;
	}
	
	public byte[] generateNutanixCustomerPackingSlip(NutanixPackingSlip slip, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		List<NutanixPackingSlip> beanList = new ArrayList<NutanixPackingSlip>();
		beanList.add(slip);
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			parameters.put("REPORT_IMAGE_PATH", reportImagePath.getFile().getAbsolutePath() + "/");
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 2048, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(NUTANIX_CUSTOMER_PACKING_SLIP_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnByteArray;
	}
	
	public byte[] generateAstDailyTestResults(List<TestResultReportItem> beanList, ReportExportTypeEnum reportType, HttpServletRequest request) throws Exception {
		byte[] returnByteArray;
		JRSwapFileVirtualizer virtualizer = null;
		try {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("SUBREPORT_DIR", SUBREPORT_DIR);	
			parameters.put("REPORT_IMAGE_PATH", reportImagePath.getFile().getAbsolutePath() + "/");
			virtualizer = new JRSwapFileVirtualizer(VIRT_MAX, new JRSwapFile(virtualizerPath.getFile().getPath(), 4096, 1024), true); 
			parameters.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
			returnByteArray = 
				this.generateReportFromBeanList(AST_DAILY_TEST_RESULTS_REPORT_PATH, request, parameters, beanList, reportType);
		}
		catch ( Exception e) {
			throw e;
		}
		finally {
			if (virtualizer != null) try { virtualizer.cleanup(); } catch (Exception e) {}
		}
		return returnByteArray;
	}
	
	@SuppressWarnings("unchecked")
	private byte[] generateReportFromBeanList(String reportPath, HttpServletRequest request, Map<String, Object> parameters, Collection beanCollection, ReportExportTypeEnum reportType) {
		byte[] returnArray = null;
		try {			
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(beanCollection, false);
			returnArray = this.getJasperReportAsByteArray(reportPath, request, parameters, ds, reportType);
		} 
		catch (Exception e) {
			log.error("Could not generate report: " + reportPath, e);
		}
		return returnArray;
	}
	
	private byte[] getJasperReportAsByteArray(String jasperReportPath, HttpServletRequest request, Map<String, Object> parameters, JRDataSource ds, ReportExportTypeEnum reportType) throws Exception {
		byte[] returnArray = null;
		InputStream iStream = null;
		try {
			ClassPathResource cpr = new ClassPathResource(jasperReportPath);
			iStream = cpr.getInputStream();
			JasperPrint reportPrint = JasperFillManager.fillReport(iStream, parameters, ds);

			if ( reportType == ReportExportTypeEnum.XLS ) {
				returnArray = this.exportXLS(reportPrint);
			}
			else if ( reportType == ReportExportTypeEnum.HTML ) {
				returnArray = this.exportHTML(reportPrint, request);
			}
			else if ( reportType == ReportExportTypeEnum.PDF ) {
				returnArray = this.exportPDF(reportPrint);
			}
		}
		finally {
			if ( iStream != null ) {
				try {
					iStream.close();
				}
				catch (Exception e) {
					log.error("Could not get Jasper byte array for report.", e);
				}
			}
		}
		return returnArray;
	}
	
	private byte[] getJasperReportAsByteArrayMulti(List<String> jasperReportPathList, String[] sheetNames, HttpServletRequest request, Map<String, Object> parameters, List<JRDataSource> dsList, ReportExportTypeEnum reportType) throws Exception {
		byte[] returnArray = null;

		if (  jasperReportPathList.size() != dsList.size() ) {
			throw new Exception("Report path list size does not match jasper datasource size.");
		}
		if ( reportType != ReportExportTypeEnum.XLS ) {
			throw new Exception("Multi report generation not implemented for non-Excel report types.");
		}
		
		List<JasperPrint> jasperPrintList = new ArrayList<JasperPrint>();
		for ( int x = 0; x < jasperReportPathList.size(); x++ ) {
			ClassPathResource cpr = new ClassPathResource(jasperReportPathList.get(x));
			
			InputStream iStream = null;
			try {
				iStream = cpr.getInputStream();
				JasperPrint reportPrint = JasperFillManager.fillReport(iStream, parameters, dsList.get(x));
				jasperPrintList.add(reportPrint);
			}
			catch ( Exception e ) {
				log.error("Could not get Jasper byte array for report: " + jasperReportPathList.get(x), e);
				throw e;
			}
			finally {
				if ( iStream != null ) {
					try {
						iStream.close();
					}
					catch (Exception e) {
						log.error("Could not get Jasper byte array for report: " + jasperReportPathList.get(x), e);
						throw e;
					}
				}
			}
		}
		returnArray = this.exportXLSMulti(jasperPrintList, sheetNames);
		return returnArray;
	}

	private byte[] exportXLS(JasperPrint reportPrint) throws Exception {
		ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
		JExcelApiExporter exporter = new JExcelApiExporter(); //JRXlsExporter is older for compatibility
		//Set Excel export parameter options
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint); //single sheet
		//exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList); //mult sheet
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputByteArray);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporter.exportReport();
		return outputByteArray.toByteArray();
	}
	
	private byte[] exportXLSMulti(List<JasperPrint> reportPrintList, String[] sheetNames) throws Exception {
		ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
		JExcelApiExporter exporter = new JExcelApiExporter(); //JRXlsExporter is older for compatibility
		//Set Excel export parameter options
	
		//exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint); //single sheet
		exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, reportPrintList); //mult sheet
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputByteArray);
		exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.FALSE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporter.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetNames);
		exporter.exportReport();
		return outputByteArray.toByteArray();
	}
	
	private byte[] exportHTML(JasperPrint reportPrint, HttpServletRequest request) throws Exception {
		ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
		JRHtmlExporter exporter = new JRHtmlExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputByteArray);
		exporter.setParameter(JRHtmlExporterParameter.HTML_HEADER, "");
		exporter.setParameter(JRHtmlExporterParameter.HTML_FOOTER, "");
		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
		
		exporter.setParameter(JRHtmlExporterParameter.IMAGES_URI, request.getContextPath() + "/reportImage?image=");
		exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.FALSE);
		request.getSession().setAttribute(ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE, reportPrint);
		
		//exporter.setParameter(JRHtmlExporterParameter.IS_OUTPUT_IMAGES_TO_DIR, Boolean.TRUE);
		//exporter.setParameter(JRHtmlExporterParameter.IMAGES_DIR_NAME, contextPath + "/reportImage/");


		
		
		exporter.exportReport();
		return outputByteArray.toByteArray();
	}
	
	private byte[] exportPDF(JasperPrint reportPrint) throws Exception {
		ByteArrayOutputStream outputByteArray = new ByteArrayOutputStream();
		JRPdfExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, reportPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputByteArray);
		exporter.exportReport();
		return outputByteArray.toByteArray();
	}
	
	
	public void prepareResponseHeaderForJasperReport(HttpServletResponse response, String reportName, ReportExportTypeEnum exportFormat) throws IOException {
		response.setContentType(exportFormat.getContentType());
		response.setHeader("Cache-Control", "no-cache"); 
		response.setHeader("Content-Disposition", "attachment; filename=" + reportName + "." + exportFormat.getFileExtension()); 
		response.setHeader("Expires", "0"); 
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0"); 
		response.setHeader("Pragma", "public"); 
	}

	public Resource getReportImagePath() {
		return reportImagePath;
	}


	public void setReportImagePath(Resource reportImagePath) {
		ReportService.reportImagePath = reportImagePath;
	}

	public static Resource getGenerationPath() {
		return generationPath;
	}

	public static void setGenerationPath(Resource generationPath) {
		ReportService.generationPath = generationPath;
	}

	public static Resource getVirtualizerPath() {
		return virtualizerPath;
	}

	public static void setVirtualizerPath(Resource virtualizerPath) {
		ReportService.virtualizerPath = virtualizerPath;
	}


	
	

}
