/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartPlot;

/**
 * Class used to deserialize a chart customizer definition file
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomizerWidgetsDescriptor extends WidgetsDescriptor{
	
	/**
	 * The class of the chart customizer
	 */
	private String customizerClass;
	
	private String[] supportedPlot;
	
	/**
	 * Cache used to store the supported plots
	 */
	@JsonIgnore
	private HashSet<Integer> supportedPlots = null;
	
	public String getCustomizerClass(){
		return customizerClass;
	}
	
	public void setCustomizerClass(String customizerClass){
		this.customizerClass = customizerClass;
	}

	@Override
	public String toString() {
		return getLabel();
	}
	
	public String[] getSupportedPlot(){
		return supportedPlot;
	}
	
	public void setSupportedPlot(String[] supportedPlot){
		this.supportedPlot = supportedPlot;
	}
	
	/**
	 * Generate an hashset of all the supported plots 
	 * from this customizer. The id of the plot are
	 * defined in the class {@link JRChart}
	 * 
	 * @return a not null hashset of integer where every integer 
	 * represent a chart type
	 */
	protected HashSet<Integer> getSupportedPlots(){
		if (supportedPlots == null){
			supportedPlots = new HashSet<Integer>();
			if (supportedPlot != null){
				for(String plot : supportedPlot){
					supportedPlots.add(Integer.valueOf(plot));
				}
			}
		}
		return supportedPlots;
	}
	
	/**
	 * Check if a plot is supported by this customizer. A customizer
	 * without restriction of plot support all plots
	 * 
	 * @param plot the plot to check
	 * @return true if this customizer support the passed plot, false otherwise
	 */
	public boolean isPlotSupported(JRChartPlot plot){	
		HashSet<Integer> supportedPlotsID = getSupportedPlots();
		if (supportedPlotsID.isEmpty()) return true;
		else return supportedPlotsID.contains(Integer.valueOf(plot.getChart().getChartType()));
	}
}
