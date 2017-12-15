/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.section;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.chartAxis.MChartAxes;
import com.jaspersoft.studio.components.chart.model.plot.MAreaPlot;
import com.jaspersoft.studio.components.chart.model.plot.MBar3DPlot;
import com.jaspersoft.studio.components.chart.model.plot.MBarPlot;
import com.jaspersoft.studio.components.chart.model.plot.MBubblePlot;
import com.jaspersoft.studio.components.chart.model.plot.MCandlestickPlot;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.model.plot.MHighLowPlot;
import com.jaspersoft.studio.components.chart.model.plot.MLinePlot;
import com.jaspersoft.studio.components.chart.model.plot.MMeterPlot;
import com.jaspersoft.studio.components.chart.model.plot.MMultiAxisPlot;
import com.jaspersoft.studio.components.chart.model.plot.MPie3DPlot;
import com.jaspersoft.studio.components.chart.model.plot.MPiePlot;
import com.jaspersoft.studio.components.chart.model.plot.MScatterPlot;
import com.jaspersoft.studio.components.chart.model.plot.MThermometerPlot;
import com.jaspersoft.studio.components.chart.model.plot.MTimeSeriesPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.AreaPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.Bar3dPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.BarPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.BubblePlot;
import com.jaspersoft.studio.components.chart.property.section.plot.CandlestickPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.HighLowPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.LinePlot;
import com.jaspersoft.studio.components.chart.property.section.plot.MeterPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.MultiAxisPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.Pie3dPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.PiePlot;
import com.jaspersoft.studio.components.chart.property.section.plot.ScatterPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.ThermometerPlot;
import com.jaspersoft.studio.components.chart.property.section.plot.TimeSeriesPlot;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;
import com.jaspersoft.studio.swt.widgets.RealSizeStackLayout;

import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;

/**
 * Every chart is an MChart so very chart has this section. But this content change
 * Dynamically because of the type of chart. This section cache the configuration panel
 * of each type of chart when it is requested and then show it.
 * 
 * @author Orlandin Marco
 *
 */
public class ChartPlotSection extends AbstractRealValueSection {
	
	/**
	 * Listener used to refresh the dynamic controls when something in the chart changes
	 */
	private PropertyChangeListener refreshListener = new PropertyChangeListener() {
		
		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			setRefreshing(true);
			//Refresh the chart specific properties
			APropertyNode plot = getElement();
			if (plot != null){
				Pair<AbstractRealValueSection, Composite> configPanel = getSubplotContainer(plot);
				if (configPanel != null){
					AbstractRealValueSection section = configPanel.getKey();
					section.refresh();
				}
			}
			setRefreshing(false);
		}
	};
	
	/**
	 * Stack layout used to show the correct panel
	 */
	private RealSizeStackLayout dyinamicCompositeLayout;
	
	/**
	 * Composite where the chart type specific controls are shown
	 */
	private Composite mainComposite;
	
	/**
	 * Cache map, the key is the type of chart. The value is a pair of both the section
	 * that has the definition of the controls for that type of chart and the composite
	 * container that contains the controls
	 */
	private HashMap<Class<?>, Pair<AbstractRealValueSection, Composite>> subsections;
	
	@Override
	public void createControls(Composite parent,TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));
		createCommon(parent);
		
		mainComposite = new Composite(parent, SWT.NONE);
		GridData mainCompositeData = new GridData(SWT.FILL, SWT.FILL, true, true);
		mainCompositeData.horizontalSpan = 2;
		dyinamicCompositeLayout = new RealSizeStackLayout();
		mainComposite.setLayoutData(mainCompositeData);
		mainComposite.setLayout(dyinamicCompositeLayout);
		subsections = new HashMap<Class<?>, Pair<AbstractRealValueSection, Composite>>();
	}
	

	public void createCommon(Composite parent) {
		Composite group = getWidgetFactory().createComposite(parent);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		group.setLayoutData(gd);
		group.setLayout(new GridLayout(3, false));

		getWidgetFactory().createCLabel(group, Messages.MChartPlot_backcolor);

		createWidget4Property(group, JRBaseChartPlot.PROPERTY_BACKCOLOR, false).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA).getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_SERIES_COLORS).getControl().setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, JRBaseChartPlot.PROPERTY_ORIENTATION).getControl().setLayoutData(gd);
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseChartPlot.PROPERTY_BACKCOLOR, Messages.MChartPlot_backcolor);
		addProvidedProperties(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA, Messages.MChartPlot_foreground_alpha_percent);
		addProvidedProperties(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA, Messages.MChartPlot_background_alpha_percent);
		addProvidedProperties(JRBaseChartPlot.PROPERTY_SERIES_COLORS, Messages.MChartPlot_series_colors);
		addProvidedProperties(JRBaseChartPlot.PROPERTY_ORIENTATION, Messages.MChartPlot_orientation);
	}
	
	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MChartAxes) {
			md = (APropertyNode) md
					.getPropertyValue(JRDesignChartAxis.PROPERTY_CHART);
		}
		if (md instanceof MChart) {
			MChartPlot chartplot = (MChartPlot) md
					.getPropertyValue(MChart.PLOTPROPERTY);
			return chartplot;
		}
		return md;
	}
	
	/**
	 * Get the section on composite container for a specific type of chart. If it was never requested before
	 * it is created and cached, otherwise it is return from the cache
	 * 
	 * @param plot the type of chart
	 * @return a pair of both the section that has the definition of the controls for that type 
	 * of chart and the composite container that contains the controls
	 */
	protected Pair<AbstractRealValueSection, Composite> getSubplotContainer(APropertyNode plot){
		Pair<AbstractRealValueSection, Composite> configPanel = subsections.get(plot.getClass());
		if (configPanel == null){
			Composite subPanelComposite = new Composite(mainComposite, SWT.NONE);
			subPanelComposite.setData(plot.getClass());
			subPanelComposite.setLayout(new GridLayout(2, false));
			AbstractRealValueSection aplot = getSubPlotSection(plot);
			aplot.setEditDomain(getEditDomain());
			aplot.setElements(getElements());
			aplot.setElement(plot);
			aplot.createControls(subPanelComposite, getTabbedPropertySheetPage());
			configPanel = new Pair<AbstractRealValueSection, Composite>(aplot, subPanelComposite);
			subsections.put(plot.getClass(), configPanel);
		}
		return configPanel;
	}
	
	@Override
	public void aboutToBeShown() {
		super.aboutToBeShown();
		APropertyNode plot = getElement();
		if (plot != null){
			Pair<AbstractRealValueSection, Composite> configPanel = getSubplotContainer(plot);
			dyinamicCompositeLayout.topControl = configPanel.getValue();
			mainComposite.layout();
			plot.getPropertyChangeSupport().removePropertyChangeListener(refreshListener);
			plot.getPropertyChangeSupport().addPropertyChangeListener(refreshListener);
		}
	}
	
	@Override
	public void aboutToBeHidden() {
		super.aboutToBeShown();
		APropertyNode plot = getElement();
		if (plot != null){
			plot.getPropertyChangeSupport().removePropertyChangeListener(refreshListener);
		}
	}
	
	/**
	 * Return the section that contains the control definition for a specific type
	 * of chart
	 * 
	 * @param plot the chart plot of the selected chart
	 * @return the section that contains the control definitions for the requested chart
	 */
	protected AbstractRealValueSection getSubPlotSection(APropertyNode plot){
		AbstractRealValueSection aplot = null;
		if (plot instanceof MAreaPlot)
			aplot = new AreaPlot();
		else if (plot instanceof MBar3DPlot)
			aplot = new Bar3dPlot();
		else if (plot instanceof MBarPlot)
			aplot = new BarPlot();
		else if (plot instanceof MBubblePlot)
			aplot = new BubblePlot();
		else if (plot instanceof MCandlestickPlot)
			aplot = new CandlestickPlot();
		else if (plot instanceof MHighLowPlot)
			aplot = new HighLowPlot();
		else if (plot instanceof MLinePlot)
			aplot = new LinePlot();
		else if (plot instanceof MMeterPlot)
			aplot = new MeterPlot();
		else if (plot instanceof MMultiAxisPlot)
			aplot = new MultiAxisPlot();
		else if (plot instanceof MPie3DPlot)
			aplot = new Pie3dPlot();
		else if (plot instanceof MPiePlot)
			aplot = new PiePlot();
		else if (plot instanceof MScatterPlot)
			aplot = new ScatterPlot();
		else if (plot instanceof MThermometerPlot)
			aplot = new ThermometerPlot();
		else if (plot instanceof MTimeSeriesPlot)
			aplot = new TimeSeriesPlot();
		return aplot;
	}
	
	/**
	 * When refreshing refresh both the common elements and the widget specific of
	 * the currently selected chart
	 */
	public void refresh() {
		setRefreshing(true);
		APropertyNode plot = getElement();
		if (plot != null){
			//Refresh the common properties
			plot.getPropertyDescriptors();
			for (Object key : widgets.keySet()) {
				//Use actual and current value to check if a value is inherited or not
				Object currentValue = plot.getPropertyActualValue(key);
				Object ownValue = plot.getPropertyValue(key);
				widgets.get(key).setData(plot, currentValue, ownValue);
			}
			
			//Refresh the chart specific properties
			Pair<AbstractRealValueSection, Composite> configPanel = getSubplotContainer(plot);
			if (configPanel != null){
				AbstractRealValueSection section = configPanel.getKey();
				section.setEditDomain(getEditDomain());
				section.setElements(getElements());
				section.setElement(plot);
				section.refresh();
			}
		}
		setRefreshing(false);
	}
	
	/**
	 * Since the content can change depending on the selected chart the content of this
	 * section is marked dynamic
	 */
	@Override
	public boolean hasDynamicContent() {
		return true;
	}
}
