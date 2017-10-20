package com.avnet.alapps.report;

public enum ReportExportTypeEnum {
	XLS(	"xls", 		"application/vnd.ms-excel"),
	PDF(	"pdf", 		"application/pdf"),
	HTML(	"html", 	"text/html")
	;
	
	private final String fileExtension;
	private final String contentType;
	
	ReportExportTypeEnum(String extension, String contentType) {
		this.fileExtension = extension;
		this.contentType = contentType;
	}

	public String getFileExtension() {
		return fileExtension;
	}

	public String getContentType() {
		return contentType;
	}
	
	public static ReportExportTypeEnum getByFileExtension(String extension) {
		//I could make a lookup map if this gets larger
		for ( ReportExportTypeEnum val : ReportExportTypeEnum.values() ) {
			if ( val.getFileExtension().equalsIgnoreCase(extension) ) {
				return val;
			}
		}
		return null;
	}
}
