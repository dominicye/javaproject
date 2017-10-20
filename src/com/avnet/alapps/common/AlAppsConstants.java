package com.avnet.alapps.common;

import java.util.Date;

public class AlAppsConstants {
	
	// This is added to the end of the javascript URL to make sure Chrome downloads the latest version
	public static final long JS_TIMESTAMP = (new Date()).getTime();
	
	//Hibernate session factory names for data sources
	public static final String HIBERNATE_SESSION_NAME_GSFC = "GSFCSessionFactory";
	public static final String HIBERNATE_SESSION_NAME_GSFCREPORT = "GSFCReportSessionFactory";
	public static final String HIBERNATE_SESSION_NAME_ALAPPS = "ALAPPSSessionFactory";
	public static final String HIBERNATE_SESSION_NAME_DBCONNECT = "DBCONNECTSessionFactory";
	
	public static final String AST_ADMIN_CODE = "ASTAD";

}
