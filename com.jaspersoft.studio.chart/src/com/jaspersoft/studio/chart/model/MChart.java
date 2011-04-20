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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRHyperlinkParameter;
import net.sf.jasperreports.engine.base.JRBaseChart;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;
import net.sf.jasperreports.engine.base.JRBaseFont;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.chart.model.plot.PlotFactory;
import com.jaspersoft.studio.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.ReportFactory;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.hyperlink.parameter.dialog.ParameterDTO;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;
/*
 * The Class MChart.
 */
public class MChart extends MGraphicElementLineBox implements IContainer, IContainerEditPart {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("chart"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m chart.
	 */
	public MChart() {
		super();
	}

	public MChart(ANode parent, int newIndex) {
		super(parent, newIndex);
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
	public MChart(ANode parent, JRChart jrChart, int newIndex) {
		super(parent, newIndex);
		setValue(jrChart);
	}

	private IPropertyDescriptor[] descriptors;
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor titlePositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_POSITION,
				Messages.MChart_title_position, EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		titlePositionD.setDescription(Messages.MChart_title_position_description);
		desc.add(titlePositionD);

		ComboBoxPropertyDescriptor legendPositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_POSITION,
				Messages.MChart_legend_position, EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		legendPositionD.setDescription(Messages.MChart_legend_position_description);
		desc.add(legendPositionD);

		ColorPropertyDescriptor titleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_COLOR,
				Messages.MChart_title_color, NullEnum.INHERITED);
		titleColorD.setDescription(Messages.MChart_title_color_description);
		desc.add(titleColorD);

		JRExpressionPropertyDescriptor titleExprD = new JRExpressionPropertyDescriptor(
				JRDesignChart.PROPERTY_TITLE_EXPRESSION, Messages.MChart_title_expression);
		titleExprD.setDescription(Messages.MChart_title_expression_description);
		desc.add(titleExprD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(JRDesignChart.PROPERTY_EVALUATION_TIME,
				Messages.MChart_evaluation_time, EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD.setDescription(Messages.MChart_evaluation_time_description);
		desc.add(evaluationTimeD);

		ColorPropertyDescriptor subtitleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_SUBTITLE_COLOR,
				Messages.MChart_subtitle_color, NullEnum.INHERITED);
		subtitleColorD.setDescription(Messages.MChart_subtitle_color_description);
		desc.add(subtitleColorD);

		JRExpressionPropertyDescriptor subtitleExprD = new JRExpressionPropertyDescriptor(
				JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION, Messages.MChart_subtitle_expression);
		subtitleExprD.setDescription(Messages.MChart_subtitle_expression_description);
		desc.add(subtitleExprD);

		ColorPropertyDescriptor legendColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_COLOR,
				Messages.MChart_legend_color, NullEnum.INHERITED);
		legendColorD.setDescription(Messages.MChart_legend_color_description);
		desc.add(legendColorD);

		ColorPropertyDescriptor legendBackColorD = new ColorPropertyDescriptor(
				JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, Messages.MChart_legend_background_color, NullEnum.INHERITED);
		legendBackColorD.setDescription(Messages.MChart_legend_background_color_description);
		desc.add(legendBackColorD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS,
				Messages.MChart_customizer_class);
		classD.setDescription(Messages.MChart_customizer_class_description);
		desc.add(classD);

		CheckBoxPropertyDescriptor showLegendD = new CheckBoxPropertyDescriptor(JRBaseChart.PROPERTY_SHOW_LEGEND,
				Messages.MChart_show_legend, NullEnum.NULL);
		showLegendD.setDescription(Messages.MChart_show_legend_description);
		desc.add(showLegendD);

		RWComboBoxPropertyDescriptor rendererTypeD = new RWComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_RENDER_TYPE,
				Messages.MChart_renderer_type, new String[] { "", "draw", "image", "svg" }, NullEnum.NULL); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
		rendererTypeD.setDescription(Messages.MChart_renderer_type_description);
		desc.add(rendererTypeD);

		RWComboBoxPropertyDescriptor themeD = new RWComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_THEME,
				Messages.MChart_theme,
				new String[] { "", "aegian", "default", "default.spring", "generic", "eye.candy.sixties" }, NullEnum.NULL); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
		themeD.setDescription(Messages.MChart_theme_description);
		desc.add(themeD);

		evaluationGroupD = new RComboBoxPropertyDescriptor(JRDesignChart.PROPERTY_EVALUATION_GROUP,
				Messages.MChart_evaluation_group, new String[] { "" }); //$NON-NLS-1$
		evaluationGroupD.setDescription(Messages.MChart_evaluation_group_description);
		desc.add(evaluationGroupD);

		FontPropertyDescriptor titleFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_TITLE_FONT,
				Messages.MChart_title_font);
		titleFontD.setDescription(Messages.MChart_title_font_description);
		desc.add(titleFontD);

		FontPropertyDescriptor subtitleFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_SUBTITLE_FONT,
				Messages.MChart_subtitle_font);
		subtitleFontD.setDescription(Messages.MChart_subtitle_font_description);
		desc.add(subtitleFontD);

		FontPropertyDescriptor legendFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_LEGEND_FONT,
				Messages.MChart_legend_font);
		legendFontD.setDescription(Messages.MChart_legend_font_description);
		desc.add(legendFontD);

		PlotPropertyDescriptor plotD = new PlotPropertyDescriptor("PLOTPROPERTY", Messages.MChart_plot); //$NON-NLS-1$
		plotD.setDescription(Messages.MChart_plot_description);
		desc.add(plotD);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		titleFontD.setCategory(Messages.MChart_chart_title_category);
		titleColorD.setCategory(Messages.MChart_chart_title_category);
		titlePositionD.setCategory(Messages.MChart_chart_title_category);
		titleExprD.setCategory(Messages.MChart_chart_title_category);

		subtitleFontD.setCategory(Messages.MChart_chart_subtitle_category);
		subtitleExprD.setCategory(Messages.MChart_chart_subtitle_category);
		subtitleColorD.setCategory(Messages.MChart_chart_subtitle_category);

		plotD.setCategory(Messages.MChart_common_chart_properties_category);

		evaluationGroupD.setCategory(Messages.MChart_common_chart_properties_category);
		themeD.setCategory(Messages.MChart_common_chart_properties_category);

		classD.setCategory(Messages.MChart_common_chart_properties_category);

		legendFontD.setCategory(Messages.MChart_chart_legend_category);
		legendBackColorD.setCategory(Messages.MChart_chart_legend_category);
		legendColorD.setCategory(Messages.MChart_chart_legend_category);
		legendPositionD.setCategory(Messages.MChart_chart_legend_category);
		showLegendD.setCategory(Messages.MChart_chart_legend_category);

		evaluationTimeD.setCategory(Messages.MChart_common_chart_properties_category);

		rendererTypeD.setCategory(Messages.MChart_common_chart_properties_category);

		defaultsMap.put(JRBaseChart.PROPERTY_THEME, null);
		defaultsMap.put(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS, null);
		defaultsMap.put(JRBaseChart.PROPERTY_SHOW_LEGEND, new Boolean(true));
		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_SUBTITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, null);

		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_POSITION, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_POSITION, null);
		defaultsMap.put(JRDesignChart.PROPERTY_EVALUATION_TIME, EnumHelper.getValue(EvaluationTimeEnum.NOW, 1, false));
	}

	@Override
	public void setGroupItems(String[] items) {
		if (evaluationGroupD != null)
			evaluationGroupD.setItems(items);
	}

	private RComboBoxPropertyDescriptor evaluationGroupD;
	private MExpression titleExpression;
	private MExpression subtitleExpression;
	private MChartPlot mChartPlot;
	private MFont tFont;
	private MFont stFont;
	private MFont lFont;

	private MHyperLink mHyperLink;

	private MExpression mAnchorExpression;
	private MExpression mPageExpression;
	private MExpression mReferenceExpression;
	private MExpression mToolTipExpression;
	private ParameterDTO propertyDTO;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignChart jrElement = (JRDesignChart) getValue();

		if (id.equals(JRBaseChart.PROPERTY_TITLE_POSITION))
			return EnumHelper.getValue(jrElement.getTitlePositionValue(), 0, true);
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_POSITION))
			return EnumHelper.getValue(jrElement.getLegendPositionValue(), 0, true);
		if (id.equals(JRDesignChart.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue(), 1, false);
		if (id.equals(JRBaseChart.PROPERTY_RENDER_TYPE))
			return jrElement.getRenderType();
		if (id.equals(JRBaseChart.PROPERTY_THEME))
			return jrElement.getTheme();
		if (id.equals(JRDesignChart.PROPERTY_EVALUATION_GROUP)) {
			if (jrElement.getEvaluationGroup() != null)
				return jrElement.getEvaluationGroup().getName();
			return ""; //$NON-NLS-1$
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (propertyDTO == null) {
				propertyDTO = new ParameterDTO();
				propertyDTO.setJasperDesign(getJasperDesign());
				propertyDTO.setValue(jrElement.getHyperlinkParameters());
			}
			return propertyDTO;
		}
		if (id.equals(JRBaseChart.PROPERTY_TITLE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTitleColor());
		if (id.equals(JRBaseChart.PROPERTY_SUBTITLE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnSubtitleColor());
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnLegendColor());
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnLegendBackgroundColor());
		if (id.equals(JRBaseChart.PROPERTY_SHOW_LEGEND))
			return jrElement.getShowLegend();

		if (id.equals(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS))
			return jrElement.getCustomizerClass();

		// hyperlink --------------------------------------
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			return jrElement.getLinkTarget();
		if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			return jrElement.getLinkType();
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			mAnchorExpression = ExprUtil.getExpression(this, mAnchorExpression, jrElement.getHyperlinkAnchorExpression());
			return mAnchorExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			mPageExpression = ExprUtil.getExpression(this, mPageExpression, jrElement.getHyperlinkPageExpression());
			return mPageExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			mReferenceExpression = ExprUtil.getExpression(this, mReferenceExpression,
					jrElement.getHyperlinkReferenceExpression());
			return mReferenceExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			mToolTipExpression = ExprUtil.getExpression(this, mToolTipExpression, jrElement.getHyperlinkTooltipExpression());
			return mToolTipExpression;
		}

		if (id.equals("PLOTPROPERTY")) { //$NON-NLS-1$
			if (mChartPlot == null) {
				mChartPlot = PlotFactory.getChartPlot(jrElement.getPlot());
				setChildListener(mChartPlot);
			}
			return mChartPlot;
		}
		if (id.equals(JRDesignChart.PROPERTY_TITLE_FONT)) {
			if (tFont == null) {
				tFont = new MFont(jrElement.getTitleFont());
				setChildListener(tFont);
			}
			return tFont;
		}
		if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_FONT)) {
			if (stFont == null) {
				stFont = new MFont(jrElement.getSubtitleFont());
				setChildListener(stFont);
			}
			return stFont;
		}
		if (id.equals(JRDesignChart.PROPERTY_LEGEND_FONT)) {
			if (lFont == null) {
				lFont = new MFont(jrElement.getLegendFont());
				setChildListener(lFont);
			}
			return lFont;
		}

		if (id.equals(JRDesignChart.PROPERTY_TITLE_EXPRESSION)) {
			titleExpression = ExprUtil.getExpression(this, titleExpression, jrElement.getTitleExpression());
			return titleExpression;
		}
		if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION)) {
			subtitleExpression = ExprUtil.getExpression(this, subtitleExpression, jrElement.getSubtitleExpression());
			return subtitleExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignChart jrElement = (JRDesignChart) getValue();
		if (id.equals(JRBaseChart.PROPERTY_TITLE_POSITION))
			jrElement.setTitlePosition((EdgeEnum) EnumHelper.getSetValue(EdgeEnum.values(), value, 0, true));
		else if (id.equals(JRBaseChart.PROPERTY_LEGEND_POSITION))
			jrElement.setLegendPosition((EdgeEnum) EnumHelper.getSetValue(EdgeEnum.values(), value, 0, true));
		else if (id.equals(JRDesignChart.PROPERTY_EVALUATION_TIME))
			jrElement.setEvaluationTime((EvaluationTimeEnum) EnumHelper.getSetValue(EvaluationTimeEnum.values(), value, 1,
					false));
		else if (id.equals(JRBaseChart.PROPERTY_SHOW_LEGEND))
			jrElement.setShowLegend((Boolean) value);
		else if (id.equals(JRBaseChart.PROPERTY_RENDER_TYPE))
			jrElement.setRenderType((String) value);
		else if (id.equals(JRBaseChart.PROPERTY_THEME))
			jrElement.setTheme((String) value);
		else if (id.equals(JRDesignChart.PROPERTY_EVALUATION_GROUP)) {
			if (!value.equals("")) { //$NON-NLS-1$
				JRGroup group = (JRGroup) getJasperDesign().getGroupsMap().get(value);
				jrElement.setEvaluationGroup(group);
			}
		}

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
		} else if (id.equals(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS)) {
			jrElement.setCustomizerClass((String) value);
		} else if (id.equals(JRDesignChart.PROPERTY_TITLE_EXPRESSION)) {
			jrElement.setTitleExpression(ExprUtil.setValues(jrElement.getTitleExpression(), value));
		} else if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION)) {
			jrElement.setSubtitleExpression(ExprUtil.setValues(jrElement.getSubtitleExpression(), value));
		} else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement.setLinkType((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION))
			jrElement.setHyperlinkAnchorExpression(ExprUtil.setValues(jrElement.getHyperlinkAnchorExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION))
			jrElement.setHyperlinkPageExpression(ExprUtil.setValues(jrElement.getHyperlinkPageExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION))
			jrElement.setHyperlinkReferenceExpression(ExprUtil.setValues(jrElement.getHyperlinkReferenceExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION))
			jrElement.setHyperlinkTooltipExpression(ExprUtil.setValues(jrElement.getHyperlinkTooltipExpression(), value));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PARAMETERS)) {
			if (value instanceof ParameterDTO) {
				ParameterDTO v = (ParameterDTO) value;

				for (JRHyperlinkParameter prm : propertyDTO.getValue())
					jrElement.removeHyperlinkParameter(prm);

				for (JRHyperlinkParameter param : v.getValue())
					jrElement.addHyperlinkParameter(param);

				propertyDTO = v;
			}
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

	public static JRDesignElement createJRElement(JasperDesign jasperDesign, byte chartType) {
		JRDesignChart jrDesignElement = new JRDesignChart(jasperDesign, chartType);
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

	@Override
	public void setValue(Object value) {
		JRChart oldObject = (JRChart) getValue();
		JRChart newObject = (JRChart) value;

		if (oldObject != null) {
			((JRBaseChartPlot) oldObject.getPlot()).getEventSupport().removePropertyChangeListener(this);
			((JRBaseFont) oldObject.getLegendFont()).getEventSupport().removePropertyChangeListener(this);
			((JRBaseFont) oldObject.getSubtitleFont()).getEventSupport().removePropertyChangeListener(this);
			((JRBaseFont) oldObject.getTitleFont()).getEventSupport().removePropertyChangeListener(this);
		}
		if (newObject != null) {
			((JRBaseChartPlot) newObject.getPlot()).getEventSupport().addPropertyChangeListener(this);
			((JRBaseFont) newObject.getLegendFont()).getEventSupport().addPropertyChangeListener(this);
			((JRBaseFont) newObject.getSubtitleFont()).getEventSupport().addPropertyChangeListener(this);
			((JRBaseFont) newObject.getTitleFont()).getEventSupport().addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(JRDesignElementGroup.PROPERTY_CHILDREN)) {
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
					}
					// add the node to this parent
					ANode n = ReportFactory.createNode(this, evt.getNewValue(), newIndex);
					if (evt.getNewValue() instanceof JRElementGroup) {
						JRElementGroup jrFrame = (JRElementGroup) evt.getNewValue();
						ReportFactory.createElementsForBand(n, jrFrame.getChildren());
					}
				} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
					// delete
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue()) {
							removeChild((ANode) n);
							break;
						}
					}
				} else {
					// changed
					for (INode n : getChildren()) {
						if (n.getValue() == evt.getOldValue())
							n.setValue(evt.getNewValue());
					}
				}
			}
		} else if (evt.getPropertyName().equals("axes")) { //$NON-NLS-1$
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -2;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				// add the node to this parent
				ReportFactory.createNode(this, evt.getNewValue(), newIndex);
				// if (evt.getNewValue() instanceof JRElementGroup) {
				// JRElementGroup jrFrame = (JRElementGroup) evt.getNewValue();
				// ReportFactory.createElementsForBand(n, jrFrame.getChildren());
				// }
			} else if (evt.getOldValue() != null && evt.getNewValue() == null) {
				// delete
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue()) {
						removeChild((ANode) n);
						break;
					}
				}
			} else {
				// changed
				for (INode n : getChildren()) {
					if (n.getValue() == evt.getOldValue())
						n.setValue(evt.getNewValue());
				}
			}

		}
		PropertyChangeEvent newEvent = evt;
		if (!(evt.getSource() instanceof ANode))
			newEvent = new PropertyChangeEvent(this, evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
		getPropertyChangeSupport().firePropertyChange(newEvent);
	}
}
