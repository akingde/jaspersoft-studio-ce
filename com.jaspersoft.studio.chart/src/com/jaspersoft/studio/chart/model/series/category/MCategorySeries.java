package com.jaspersoft.studio.chart.model.series.category;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
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

public class MCategorySeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("categoryseries");
		return iconDescriptor;
	}

	public MCategorySeries() {
		super();
	}

	public MCategorySeries(ANode parent, JRDesignCategorySeries value, int newIndex) {
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

		JRExpressionPropertyDescriptor catExpD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION, "Category Expression");
		catExpD.setDescription("Category Expression.");
		desc.add(catExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION, "Label Expression");
		lblExprD.setDescription("Label expression.");
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION, "Series Expression");
		seriesExprD.setDescription("Series expression.");
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION, "Value Expression");
		valExprD.setDescription("Valule expression.");
		desc.add(valExprD);

		JRPropertyDescriptor itemHyperLinkD = new JRPropertyDescriptor(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK,
				"Item Hyperlink");
		itemHyperLinkD.setDescription("Item Hyperlink");
		desc.add(itemHyperLinkD);

		defaultsMap.put(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK, null);

	}

	private MHyperLink mHyperLink;

	private MExpression dExpression;
	private MExpression hExpression;
	private MExpression lExpression;
	private MExpression oExpression;

	public Object getPropertyValue(Object id) {
		JRDesignCategorySeries jrElement = (JRDesignCategorySeries) getValue();

		if (id.equals(JRDesignCategorySeries.PROPERTY_ITEM_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getItemHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION)) {
			if (dExpression == null)
				dExpression = new MExpression(jrElement.getCategoryExpression());
			return dExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (hExpression == null)
				hExpression = new MExpression(jrElement.getLabelExpression());
			return hExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (lExpression == null)
				lExpression = new MExpression(jrElement.getSeriesExpression());
			return lExpression;
		}
		if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION)) {
			if (oExpression == null)
				oExpression = new MExpression(jrElement.getValueExpression());
			return oExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignCategorySeries jrElement = (JRDesignCategorySeries) getValue();

		if (id.equals(JRDesignCategorySeries.PROPERTY_CATEGORY_EXPRESSION)) {
			if (value instanceof MExpression) {
				dExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dExpression.getValue();
				jrElement.setCategoryExpression(expression);
			}
		} else if (id.equals(JRDesignCategorySeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignCategorySeries.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setSeriesExpression(expression);
			}
		} else if (id.equals(JRDesignCategorySeries.PROPERTY_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				JRExpression expression = (JRExpression) oExpression.getValue();
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