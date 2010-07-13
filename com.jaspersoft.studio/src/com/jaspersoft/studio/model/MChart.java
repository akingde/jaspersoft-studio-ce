/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MChart.
 */
public class MChart extends MGraphicElementLineBox {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("chart");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m chart.
	 */
	public MChart() {
		super();
	}

	/**
	 * Instantiates a new m chart.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrChart
	 *          the jr chart
	 * @param newIndex
	 *          the new index
	 */
	public MChart(ANode parent, JRDesignChart jrChart, int newIndex) {
		super(parent, newIndex);
		setValue(jrChart);
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor titlePositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_POSITION,
				"Title Position", EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		titlePositionD.setDescription("Title position.");
		desc.add(titlePositionD);

		ComboBoxPropertyDescriptor legendPositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_POSITION,
				"Legend Position", EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		legendPositionD.setDescription("Legend position.");
		desc.add(legendPositionD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(JRDesignChart.PROPERTY_EVALUATION_TIME,
				"Evaluation Time", EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NULL));
		evaluationTimeD
				.setDescription("The chart to be printed is supplied by the associated expression. This expression can be evaluated at a specified moment. This could be useful, for example, when we want to have on the first page a chart that will be generated only after fetching all the data source rows.");
		desc.add(evaluationTimeD);

		ColorPropertyDescriptor titleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_COLOR, "Title Color",
				NullEnum.INHERITED);
		titleColorD.setDescription("Color of the title text");
		desc.add(titleColorD);

		ColorPropertyDescriptor subtitleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_SUBTITLE_COLOR,
				"Subtitle Color", NullEnum.INHERITED);
		subtitleColorD.setDescription("Color of the subtitle text");
		desc.add(subtitleColorD);

		ColorPropertyDescriptor legendColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_COLOR,
				"Legend Color", NullEnum.INHERITED);
		legendColorD.setDescription("The color of the text in the legend.");
		desc.add(legendColorD);

		ColorPropertyDescriptor legendBackColorD = new ColorPropertyDescriptor(
				JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, "Legend Background Color", NullEnum.INHERITED);
		legendBackColorD.setDescription("The color of the background of the legend.");
		desc.add(legendBackColorD);

		legendBackColorD.setCategory("Common Chart Properties");
		legendColorD.setCategory("Common Chart Properties");
		subtitleColorD.setCategory("Common Chart Properties");
		titleColorD.setCategory("Common Chart Properties");
		titlePositionD.setCategory("Common Chart Properties");
		legendPositionD.setCategory("Common Chart Properties");
		evaluationTimeD.setCategory("Common Chart Properties");

		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_SUBTITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, null);

		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_POSITION, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_POSITION, null);
		defaultsMap.put(JRDesignChart.PROPERTY_EVALUATION_TIME, EnumHelper.getValue(EvaluationTimeEnum.NOW, 1, true));
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignChart jrElement = (JRDesignChart) getValue();

		if (id.equals(JRBaseChart.PROPERTY_TITLE_POSITION))
			return EnumHelper.getValue(jrElement.getTitlePositionValue());
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_POSITION))
			return EnumHelper.getValue(jrElement.getLegendPositionValue());
		if (id.equals(JRDesignChart.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue());

		if (id.equals(JRBaseChart.PROPERTY_TITLE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTitleColor());
		if (id.equals(JRBaseChart.PROPERTY_SUBTITLE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnSubtitleColor());
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnLegendColor());
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnLegendBackgroundColor());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignChart jrElement = (JRDesignChart) getValue();
		if (id.equals(JRBaseChart.PROPERTY_TITLE_POSITION))
			jrElement.setTitlePosition(EdgeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRBaseChart.PROPERTY_LEGEND_POSITION))
			jrElement.setLegendPosition(EdgeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));
		else if (id.equals(JRDesignChart.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime(EvaluationTimeEnum.getByValue(EnumHelper.getSetValue((Integer) value)));

		else if (id.equals(JRBaseChart.PROPERTY_TITLE_COLOR)) {
			if (value instanceof RGB)
				jrElement.setTitleColor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRBaseChart.PROPERTY_SUBTITLE_COLOR)) {
			if (value instanceof RGB)
				jrElement.setSubtitleColor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRBaseChart.PROPERTY_LEGEND_COLOR)) {
			if (value instanceof RGB)
				jrElement.setLegendColor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR)) {
			if (value instanceof RGB)
				jrElement.setLegendBackgroundColor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else
			super.setPropertyValue(id, value);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultHeight()
	 */
	@Override
	public int getDefaultHeight() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDefaultWidth()
	 */
	@Override
	public int getDefaultWidth() {
		return 200;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign) {
		JRDesignElement jrDesignElement = new JRDesignChart(jasperDesign, JRDesignChart.CHART_TYPE_PIE);
		return jrDesignElement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

}
