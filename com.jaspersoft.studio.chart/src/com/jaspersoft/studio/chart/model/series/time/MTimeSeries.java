package com.jaspersoft.studio.chart.model.series.time;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.ChartNodeIconDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MTimeSeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("timeseries");
		return iconDescriptor;
	}

	public MTimeSeries() {
		super();
	}

	public MTimeSeries(ANode parent, JRDesignTimeSeries value, int newIndex) {
		super(parent, -1);
		setValue(value);
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

		JRExpressionPropertyDescriptor timePeriodD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION, "Time Period Expression");
		timePeriodD.setDescription("Time Period Expression.");
		desc.add(timePeriodD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION, "Label Expression");
		lblExprD.setDescription("Label expression.");
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION, "Series Expression");
		seriesExprD.setDescription("Series expression.");
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION, "Value Expression");
		valExprD.setDescription("Valule expression.");
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK,
				"Item Hyperlink");
		itemHyperLinkD.setDescription("Item Hyperlink");
		desc.add(itemHyperLinkD);

		defaultsMap.put(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION, null);
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK, null);

	}

	private MHyperLink mHyperLink;

	private MExpression tpExpression;
	private MExpression vExpression;
	private MExpression lExpression;
	private MExpression sExpression;

	public Object getPropertyValue(Object id) {
		JRDesignTimeSeries jrElement = (JRDesignTimeSeries) getValue();

		if (id.equals(JRDesignTimeSeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION)) {
			if (tpExpression == null)
				tpExpression = new MExpression(jrElement.getTimePeriodExpression());
			return tpExpression;
		}
		if (id.equals(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (lExpression == null)
				lExpression = new MExpression(jrElement.getLabelExpression());
			return lExpression;
		}
		if (id.equals(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION)) {
			if (sExpression == null)
				sExpression = new MExpression(jrElement.getSeriesExpression());
			return sExpression;
		}
		if (id.equals(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION)) {
			if (vExpression == null)
				vExpression = new MExpression(jrElement.getValueExpression());
			return vExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeries jrElement = (JRDesignTimeSeries) getValue();

		if (id.equals(JRDesignTimeSeries.PROPERTY_TIME_PERIOD_EXPRESSION)) {
			if (value instanceof MExpression) {
				tpExpression = (MExpression) value;
				JRExpression expression = (JRExpression) tpExpression.getValue();
				jrElement.setTimePeriodExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeries.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				sExpression = (MExpression) value;
				JRExpression expression = (JRExpression) sExpression.getValue();
				jrElement.setSeriesExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeries.PROPERTY_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				vExpression = (MExpression) value;
				JRExpression expression = (JRExpression) vExpression.getValue();
				jrElement.setValueExpression(expression);
			}
		}
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

}