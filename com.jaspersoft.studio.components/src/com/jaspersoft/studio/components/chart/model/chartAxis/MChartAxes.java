/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.chartAxis;

import java.util.HashSet;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.plot.PlotFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastableGraphic;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.charts.JRChartAxis;
import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.type.AxisPositionEnum;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

public class MChartAxes extends APropertyNode implements IDragable, ICopyable {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("chartaxis"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public String getDisplayText() {
		JRChartAxis ca = (JRChartAxis) getValue();
		if (ca != null) {
			JRChart c = ca.getChart();
			String dt = PlotFactory.getChartPlot(c.getPlot()).getDisplayText();
			if (c.getKey() != null && !c.getKey().trim().isEmpty())
				dt += " (" + c.getKey() + ")";
			return dt;
		}
		return getIconDescriptor().getTitle();
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public MChartAxes() {
		super();
	}

	public MChartAxes(ANode parent, JRChartAxis jrChart, int newIndex) {
		super(parent, newIndex);
		setValue(jrChart);
	}

	@Override
	public JRChartAxis getValue() {
		return (JRChartAxis) super.getValue();
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		// super.createPropertyDescriptors(desc, defaultsMap);

		NamedEnumPropertyDescriptor<AxisPositionEnum> positionD = new NamedEnumPropertyDescriptor<AxisPositionEnum>(
				JRDesignChartAxis.PROPERTY_POSITION,
				Messages.MChartAxes_position, AxisPositionEnum.LEFT_OR_TOP,
				NullEnum.NOTNULL);
		positionD.setDescription(Messages.MChartAxes_position_description);
		desc.add(positionD);

		JRPropertyDescriptor chartD = new JRPropertyDescriptor(
				JRDesignChartAxis.PROPERTY_CHART, Messages.MChartAxes_chart);
		chartD.setDescription(Messages.MChartAxes_chart_description);
		desc.add(chartD);
		//
		// if (mChart == null) {
		// mChart = new MChart();
		// mChart.setValue(((JRChartAxis) getValue()).getChart());
		// }
		// mChart.createPropertyDescriptors(desc, defaultsMap);

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#axis");
	}

	@Override
	protected void postDescriptors(IPropertyDescriptor[] descriptors) {
		super.postDescriptors(descriptors);
		// initialize style
		JasperDesign jasperDesign = getJasperDesign();
		if (jasperDesign != null) {
			// initialize groups
			JRGroup[] groups = jasperDesign.getGroups();
			String[] items = new String[groups.length + 1];
			items[0] = ""; //$NON-NLS-1$
			for (int j = 0; j < groups.length; j++) {
				items[j + 1] = groups[j].getName();
			}
			setGroupItems(items);
		}
	}

	protected void setGroupItems(String[] items) {
		if (mChart != null)
			mChart.setGroupItems(items);
	}

	private MChart mChart;
	
	public Object getPropertyValue(Object id) {
		JRDesignChartAxis jrElement = (JRDesignChartAxis) getValue();

		if (id.equals(JRDesignChartAxis.PROPERTY_POSITION)) {
			return NamedEnumPropertyDescriptor.getIntValue(NullEnum.NOTNULL, jrElement.getPositionValue());
		}

		if (id.equals(JRDesignChartAxis.PROPERTY_CHART)) {
			if (mChart == null)
				mChart = new MChart();
			mChart.setJasperConfiguration(getJasperConfiguration());
			setChildListener(mChart);
			mChart.setValue(jrElement.getChart());
			return mChart;
		}
		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignChartAxis jrElement = (JRDesignChartAxis) getValue();
		if (id.equals(JRDesignChartAxis.PROPERTY_POSITION)) {
			AxisPositionEnum axisPosition = NamedEnumPropertyDescriptor.getEnumValue(AxisPositionEnum.LEFT_OR_TOP, NullEnum.NOTNULL, value);
			jrElement.setPosition(axisPosition);
		} else if (mChart != null) {
			mChart.setPropertyValue(id, value);
		}
	}

	@Override
	public void setValue(Object value) {
		JRDesignChartAxis oldObject = (JRDesignChartAxis) getValue();
		JRDesignChartAxis newObject = (JRDesignChartAxis) value;

		if (oldObject != null)
			((JRDesignChart) oldObject.getChart()).getEventSupport()
					.removePropertyChangeListener(this);
		if (newObject != null)
			((JRDesignChart) newObject.getChart()).getEventSupport()
					.addPropertyChangeListener(this);
		super.setValue(value);
	}

	@Override
	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MChart || parent instanceof MElementGroup
				|| parent instanceof IPastableGraphic)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}
	
	/**
	 * An axis can be cut when there are more than one axis and when 
	 * not every axis of the chart are selected
	 */
	@Override
	public boolean isCuttable(ISelection currentSelection) {
		ANode parent = getParent();
		if (parent != null){
			if (parent.getChildren().size() <= 1) return false;
			//Use the selection to understand if all the subcharts are in the 
			//current selection
			StructuredSelection sSel = (StructuredSelection)currentSelection;
			HashSet<Object> selectedModels = new HashSet<Object>();
			for(Object obj : sSel.toArray()){
				if (obj instanceof EditPart){
					EditPart part = (EditPart)obj;
					selectedModels.add(part.getModel());
				}
			}
			boolean allIncluded = true;
			for(INode child : parent.getChildren()){
				if (!selectedModels.contains(child)){
					allIncluded = false;
					break;
				}
			}
			return !allIncluded;			
		}
		return false;
	}

}
