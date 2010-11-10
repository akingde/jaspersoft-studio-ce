package com.jaspersoft.studio.chart.model.series.xyseries;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignXySeries;
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

public class MXYSeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("xyseries");
		return iconDescriptor;
	}

	public MXYSeries() {
		super();
	}

	public MXYSeries(ANode parent, JRDesignXySeries value, int newIndex) {
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

		JRExpressionPropertyDescriptor xValueExpD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION, "X Value Expression");
		xValueExpD.setDescription("X Value Expression.");
		desc.add(xValueExpD);

		JRExpressionPropertyDescriptor yValueExpD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION, "Y Value Expression");
		yValueExpD.setDescription("Y Value Expression.");
		desc.add(yValueExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_LABEL_EXPRESSION, "Label Expression");
		lblExprD.setDescription("Label expression.");
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_SERIES_EXPRESSION, "Series Expression");
		seriesExprD.setDescription("Series expression.");
		desc.add(seriesExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK,
				"Item Hyperlink");
		itemHyperLinkD.setDescription("Item Hyperlink");
		desc.add(itemHyperLinkD);

		defaultsMap.put(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK, null);
	}

	private MHyperLink mHyperLink;

	private MExpression xExpression;
	private MExpression yExpression;
	private MExpression lExpression;
	private MExpression sExpression;

	public Object getPropertyValue(Object id) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION)) {
			if (xExpression == null)
				xExpression = new MExpression(jrElement.getXValueExpression());
			return xExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION)) {
			if (yExpression == null)
				yExpression = new MExpression(jrElement.getYValueExpression());
			return yExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (lExpression == null)
				lExpression = new MExpression(jrElement.getLabelExpression());
			return lExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (sExpression == null)
				sExpression = new MExpression(jrElement.getSeriesExpression());
			return sExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				xExpression = (MExpression) value;
				JRExpression expression = (JRExpression) xExpression.getValue();
				jrElement.setXValueExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				yExpression = (MExpression) value;
				JRExpression expression = (JRExpression) yExpression.getValue();
				jrElement.setYValueExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				sExpression = (MExpression) value;
				JRExpression expression = (JRExpression) sExpression.getValue();
				jrElement.setSeriesExpression(expression);
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