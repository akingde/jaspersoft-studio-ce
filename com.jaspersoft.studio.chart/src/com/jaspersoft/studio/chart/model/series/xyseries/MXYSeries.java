package com.jaspersoft.studio.chart.model.series.xyseries;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignXySeries;
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
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
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
			xExpression = ExprUtil.getExpression(this, xExpression, jrElement.getXValueExpression());
			return xExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION)) {
			yExpression = ExprUtil.getExpression(this, yExpression, jrElement.getYValueExpression());
			return xExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION)) {
			lExpression = ExprUtil.getExpression(this, lExpression, jrElement.getLabelExpression());
			return lExpression;
		}
		if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION)) {
			sExpression = ExprUtil.getExpression(this, sExpression, jrElement.getSeriesExpression());
			return sExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignXySeries jrElement = (JRDesignXySeries) getValue();

		if (id.equals(JRDesignXySeries.PROPERTY_X_VALUE_EXPRESSION))
			jrElement.setXValueExpression(ExprUtil.setValues(jrElement.getXValueExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_Y_VALUE_EXPRESSION))
			jrElement.setYValueExpression(ExprUtil.setValues(jrElement.getYValueExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_LABEL_EXPRESSION))
			jrElement.setLabelExpression(ExprUtil.setValues(jrElement.getLabelExpression(), value));
		else if (id.equals(JRDesignXySeries.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(jrElement.getSeriesExpression(), value));
	}

	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	public String getDisplayText() {
		return getIconDescriptor().getTitle();
	}

}