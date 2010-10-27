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
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRGroup;
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
import net.sf.jasperreports.engine.type.HyperlinkTypeEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.chart.model.plot.PlotFactory;
import com.jaspersoft.studio.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainer;
import com.jaspersoft.studio.model.IContainerEditPart;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.IPastable;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MGraphicElementLineBox;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.combo.RWComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * The Class MChart.
 */
public class MChart extends MGraphicElementLineBox implements IPastable, IContainer, IContainerEditPart {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("chart");
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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor titlePositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_POSITION,
				"Title Position", EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		titlePositionD.setDescription("Title position.");
		desc.add(titlePositionD);

		ComboBoxPropertyDescriptor legendPositionD = new ComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_POSITION,
				"Legend Position", EnumHelper.getEnumNames(EdgeEnum.values(), NullEnum.NULL));
		legendPositionD.setDescription("Legend position.");
		desc.add(legendPositionD);

		ColorPropertyDescriptor titleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_TITLE_COLOR, "Title Color",
				NullEnum.INHERITED);
		titleColorD.setDescription("Color of the title text");
		desc.add(titleColorD);

		JRExpressionPropertyDescriptor titleExprD = new JRExpressionPropertyDescriptor(
				JRDesignChart.PROPERTY_TITLE_EXPRESSION, "Title Expression");
		titleExprD.setDescription("The title expression.");
		desc.add(titleExprD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(JRDesignChart.PROPERTY_EVALUATION_TIME,
				"Evaluation Time", EnumHelper.getEnumNames(EvaluationTimeEnum.values(), NullEnum.NULL));
		evaluationTimeD
				.setDescription("The chart to be printed is supplied by the associated expression. This expression can be evaluated at a specified moment. This could be useful, for example, when we want to have on the first page a chart that will be generated only after fetching all the data source rows.");
		desc.add(evaluationTimeD);

		ColorPropertyDescriptor subtitleColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_SUBTITLE_COLOR,
				"Subtitle Color", NullEnum.INHERITED);
		subtitleColorD.setDescription("Color of the subtitle text");
		desc.add(subtitleColorD);

		JRExpressionPropertyDescriptor subtitleExprD = new JRExpressionPropertyDescriptor(
				JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION, "Subtitle Expression");
		subtitleExprD.setDescription("The subtitle expression.");
		desc.add(subtitleExprD);

		ColorPropertyDescriptor legendColorD = new ColorPropertyDescriptor(JRBaseChart.PROPERTY_LEGEND_COLOR,
				"Legend Color", NullEnum.INHERITED);
		legendColorD.setDescription("The color of the text in the legend.");
		desc.add(legendColorD);

		ColorPropertyDescriptor legendBackColorD = new ColorPropertyDescriptor(
				JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, "Legend Background Color", NullEnum.INHERITED);
		legendBackColorD.setDescription("The color of the background of the legend.");
		desc.add(legendBackColorD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS,
				"Customizer Class");
		classD.setDescription("Customizer class.");
		desc.add(classD);

		CheckBoxPropertyDescriptor showLegendD = new CheckBoxPropertyDescriptor(JRBaseChart.PROPERTY_SHOW_LEGEND,
				"Show Legend", NullEnum.NULL);
		showLegendD.setDescription("Show the legend.");
		desc.add(showLegendD);

		RWComboBoxPropertyDescriptor rendererTypeD = new RWComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_RENDER_TYPE,
				"Renderer Type", new String[] { "", "draw", "image", "svg" }, NullEnum.NULL);
		rendererTypeD.setDescription("Renderer type.");
		desc.add(rendererTypeD);

		RWComboBoxPropertyDescriptor themeD = new RWComboBoxPropertyDescriptor(JRBaseChart.PROPERTY_THEME, "Theme",
				new String[] { "", "aegian", "default", "default.spring", "generic", "eye.candy.sixties" }, NullEnum.NULL);
		themeD.setDescription("Chart theme.");
		desc.add(themeD);

		evaluationGroupD = new RComboBoxPropertyDescriptor(JRDesignChart.PROPERTY_EVALUATION_GROUP, "Evaluation Group",
				new String[] { "" });
		evaluationGroupD.setDescription("Evaluation Group.");
		desc.add(evaluationGroupD);

		FontPropertyDescriptor titleFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_TITLE_FONT, "Title Font");
		titleFontD.setDescription("Title Font.");
		desc.add(titleFontD);

		FontPropertyDescriptor subtitleFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_SUBTITLE_FONT,
				"Subtitle Font");
		subtitleFontD.setDescription("Subtitle Font.");
		desc.add(subtitleFontD);

		FontPropertyDescriptor legendFontD = new FontPropertyDescriptor(JRDesignChart.PROPERTY_LEGEND_FONT, "Legend Font");
		legendFontD.setDescription("Legend Font.");
		desc.add(legendFontD);

		PlotPropertyDescriptor plotD = new PlotPropertyDescriptor("PLOTPROPERTY", "Plot");
		plotD.setDescription("Plot.");
		desc.add(plotD);

		if (mHyperLink == null)
			mHyperLink = new MHyperLink(null);
		mHyperLink.createPropertyDescriptors(desc, defaultsMap);

		titleFontD.setCategory("Common Chart Properties");
		subtitleFontD.setCategory("Common Chart Properties");
		legendFontD.setCategory("Common Chart Properties");

		plotD.setCategory("Common Chart Properties");
		evaluationGroupD.setCategory("Common Chart Properties");
		themeD.setCategory("Common Chart Properties");
		titleExprD.setCategory("Common Chart Properties");
		subtitleExprD.setCategory("Common Chart Properties");
		classD.setCategory("Common Chart Properties");
		legendBackColorD.setCategory("Common Chart Properties");
		legendColorD.setCategory("Common Chart Properties");
		subtitleColorD.setCategory("Common Chart Properties");
		titleColorD.setCategory("Common Chart Properties");
		titlePositionD.setCategory("Common Chart Properties");
		legendPositionD.setCategory("Common Chart Properties");
		evaluationTimeD.setCategory("Common Chart Properties");
		showLegendD.setCategory("Common Chart Properties");
		rendererTypeD.setCategory("Common Chart Properties");

		defaultsMap.put(JRBaseChart.PROPERTY_THEME, null);
		defaultsMap.put(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS, null);
		defaultsMap.put(JRBaseChart.PROPERTY_SHOW_LEGEND, new Boolean(true));
		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_SUBTITLE_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_COLOR, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_BACKGROUND_COLOR, null);

		defaultsMap.put(JRBaseChart.PROPERTY_TITLE_POSITION, null);
		defaultsMap.put(JRBaseChart.PROPERTY_LEGEND_POSITION, null);
		defaultsMap.put(JRDesignChart.PROPERTY_EVALUATION_TIME, EnumHelper.getValue(EvaluationTimeEnum.NOW, 1, true));
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

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignChart jrElement = (JRDesignChart) getValue();

		if (id.equals(JRBaseChart.PROPERTY_TITLE_POSITION))
			return EnumHelper.getValue(jrElement.getTitlePositionValue(), 0, true);
		if (id.equals(JRBaseChart.PROPERTY_LEGEND_POSITION))
			return EnumHelper.getValue(jrElement.getLegendPositionValue(), 0, true);
		if (id.equals(JRDesignChart.PROPERTY_EVALUATION_TIME))
			return EnumHelper.getValue(jrElement.getEvaluationTimeValue(), 1, true);
		if (id.equals(JRBaseChart.PROPERTY_RENDER_TYPE))
			return jrElement.getRenderType();
		if (id.equals(JRBaseChart.PROPERTY_THEME))
			return jrElement.getTheme();
		if (id.equals(JRDesignChart.PROPERTY_EVALUATION_GROUP)) {
			if (jrElement.getEvaluationGroup() != null)
				return jrElement.getEvaluationGroup().getName();
			return "";
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
			return EnumHelper.getValue(jrElement.getHyperlinkTypeValue(), 0, false);
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (mAnchorExpression == null)
				mAnchorExpression = new MExpression(jrElement.getHyperlinkAnchorExpression());
			return mAnchorExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (mPageExpression == null)
				mPageExpression = new MExpression(jrElement.getHyperlinkPageExpression());
			return mPageExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (mReferenceExpression == null)
				mReferenceExpression = new MExpression(jrElement.getHyperlinkReferenceExpression());
			return mReferenceExpression;
		}
		if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (mToolTipExpression == null)
				mToolTipExpression = new MExpression(jrElement.getHyperlinkTooltipExpression());
			return mToolTipExpression;
		}

		if (id.equals("PLOTPROPERTY")) {
			if (mChartPlot == null)
				mChartPlot = PlotFactory.getChartPlot(jrElement.getPlot());
			return mChartPlot;
		}
		if (id.equals(JRDesignChart.PROPERTY_TITLE_FONT)) {
			if (tFont == null)
				tFont = new MFont(jrElement.getTitleFont());
			return tFont;
		}
		if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_FONT)) {
			if (stFont == null)
				stFont = new MFont(jrElement.getSubtitleFont());
			return stFont;
		}
		if (id.equals(JRDesignChart.PROPERTY_LEGEND_FONT)) {
			if (lFont == null)
				lFont = new MFont(jrElement.getLegendFont());
			return lFont;
		}

		if (id.equals(JRDesignChart.PROPERTY_TITLE_EXPRESSION)) {
			if (titleExpression == null)
				titleExpression = new MExpression(jrElement.getTitleExpression());
			return titleExpression;
		}
		if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION)) {
			if (subtitleExpression == null)
				subtitleExpression = new MExpression(jrElement.getSubtitleExpression());
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
					true));
		else if (id.equals(JRBaseChart.PROPERTY_SHOW_LEGEND))
			jrElement.setShowLegend((Boolean) value);
		else if (id.equals(JRBaseChart.PROPERTY_RENDER_TYPE))
			jrElement.setRenderType((String) value);
		else if (id.equals(JRBaseChart.PROPERTY_THEME))
			jrElement.setTheme((String) value);
		else if (id.equals(JRDesignChart.PROPERTY_EVALUATION_GROUP)) {
			if (!value.equals("")) {
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
			if (value instanceof MExpression) {
				titleExpression = (MExpression) value;
				JRExpression expression = (JRExpression) titleExpression.getValue();
				jrElement.setTitleExpression(expression);
			}
		} else if (id.equals(JRDesignChart.PROPERTY_SUBTITLE_EXPRESSION)) {
			if (value instanceof MExpression) {
				subtitleExpression = (MExpression) value;
				JRExpression expression = (JRExpression) subtitleExpression.getValue();
				jrElement.setSubtitleExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TARGET))
			jrElement.setLinkTarget((String) value);
		else if (id.equals(JRDesignHyperlink.PROPERTY_LINK_TYPE))
			jrElement
					.setHyperlinkType((HyperlinkTypeEnum) EnumHelper.getSetValue(HyperlinkTypeEnum.values(), value, 0, false));
		else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_ANCHOR_EXPRESSION)) {
			if (value instanceof MExpression) {
				mAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mAnchorExpression.getValue();
				jrElement.setHyperlinkAnchorExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_PAGE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mPageExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mPageExpression.getValue();
				jrElement.setHyperlinkPageExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_REFERENCE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mReferenceExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mReferenceExpression.getValue();
				jrElement.setHyperlinkReferenceExpression(expression);
			}
		} else if (id.equals(JRDesignHyperlink.PROPERTY_HYPERLINK_TOOLTIP_EXPRESSION)) {
			if (value instanceof MExpression) {
				mToolTipExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mToolTipExpression.getValue();
				jrElement.setHyperlinkTooltipExpression(expression);
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

	public JRDesignElement createJRElement(JasperDesign jasperDesign, byte chartType) {
		JRDesignElement jrDesignElement = new JRDesignChart(jasperDesign, chartType);
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
		} else if (evt.getPropertyName().equals("axes")) {
			if (evt.getOldValue() == null && evt.getNewValue() != null) {
				int newIndex = -2;
				if (evt instanceof CollectionElementAddedEvent) {
					newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
				}
				// add the node to this parent
				ANode n = ReportFactory.createNode(this, evt.getNewValue(), newIndex);
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
