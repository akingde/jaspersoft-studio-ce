/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Property change listener used by the intepreter cache utilities to 
 * discard the cache of all the interpreters created for a report when
 * the language or import properties changes. In general there is one
 * of this listener for every report that has requested an evaluation, and
 * should be placed on the jasper design
 * 
 * @author Orlandin Marco
 *
 */
public class DesignChanges implements PropertyChangeListener{
	
	/**
	 * Configuration of the report
	 */
	private JasperReportsConfiguration reportConfiguration;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param reportConfiguration configuration of the report
	 */
	public DesignChanges(JasperReportsConfiguration reportConfiguration){
		this.reportConfiguration = reportConfiguration;
	}
	
	/**
	 * When the import or the report language of the report changes then
	 * al the cache for the report is discarded
	 */
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JasperDesign.PROPERTY_IMPORTS) || evt.getPropertyName().equals(JasperDesign.PROPERTY_LANGUAGE)){
			ExpressionUtil.removeAllReportInterpreters(reportConfiguration);
		}
	}
	
}
