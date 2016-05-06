package com.jaspersoft.studio.book.model;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignSection;

@Deprecated
public interface IReportPartContainer {

	List<MReportPart> getReportParts();
	
	JRDesignSection getSection();
	
}
