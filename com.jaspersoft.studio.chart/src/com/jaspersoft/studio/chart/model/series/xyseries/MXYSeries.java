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
import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.util.IIconDescriptor;
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
			iconDescriptor = new ChartNodeIconDescriptor("xyseries"); //$NON-NLS-1$
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
				JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION, Messages.common_x_value_expression);
		xValueExpD.setDescription(Messages.MXYSeries_x_value_expression_description);
		desc.add(xValueExpD);

		JRExpressionPropertyDescriptor yValueExpD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION, Messages.common_y_value_expression);
		yValueExpD.setDescription(Messages.MXYSeries_y_value_expression_description);
		desc.add(yValueExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_LABEL_EXPRESSION, Messages.common_label_expression);
		lblExprD.setDescription(Messages.MXYSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignXySeries.PROPERTY_SERIES_EXPRESSION, Messages.common_series_expression);
		seriesExprD.setDescription(Messages.MXYSeries_series_expression_description);
		desc.add(seriesExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignXySeries.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
		itemHyperLinkD.setDescription(Messages.MXYSeries_item_hyperlink_description);
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
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION)) {
			if (xExpression == null) {
				xExpression = new MExpression(jrElement.getXValueExpression());
				setChildListener(xExpression);
			}
			return xExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION)) {
			if (yExpression == null) {
				yExpression = new MExpression(jrElement.getYValueExpression());
				setChildListener(yExpression);
			}
			return yExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (lExpression == null) {
				lExpression = new MExpression(jrElement.getLabelExpression());
				setChildListener(lExpression);
			}
			return lExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (sExpression == null) {
				sExpression = new MExpression(jrElement.getSeriesExpression());
				setChildListener(sExpression);
			}
			return sExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				xExpression = (MExpression) value;
				setChildListener(xExpression);
				JRExpression expression = (JRExpression) xExpression.getValue();
				jrElement.setXValueExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				yExpression = (MExpression) value;
				setChildListener(yExpression);
				JRExpression expression = (JRExpression) yExpression.getValue();
				jrElement.setYValueExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				setChildListener(lExpression);
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				sExpression = (MExpression) value;
				setChildListener(sExpression);
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