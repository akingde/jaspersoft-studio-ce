package com.jaspersoft.studio.chart.model.series.pie;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignPieSeries;
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

public class MPieSeries extends APropertyNode {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new ChartNodeIconDescriptor("pieseries"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MPieSeries() {
		super();
	}

	public MPieSeries(ANode parent, JRDesignPieSeries value, int newIndex) {
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

		JRExpressionPropertyDescriptor keyExpD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_KEY_EXPRESSION, Messages.common_key_expression);
		keyExpD.setDescription(Messages.MPieSeries_key_expression_description);
		desc.add(keyExpD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION, Messages.common_label_expression);
		lblExprD.setDescription(Messages.MPieSeries_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor valExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION, Messages.common_value_expression);
		valExprD.setDescription(Messages.MPieSeries_value_expression_description);
		desc.add(valExprD);

		JRPropertyDescriptor sectionHyperLinkD = new JRPropertyDescriptor(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK,
				Messages.common_section_hyperlink);
		sectionHyperLinkD.setDescription(Messages.MPieSeries_section_hyperlink_description);
		desc.add(sectionHyperLinkD);

		defaultsMap.put(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION, null);
		defaultsMap.put(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION, null);
		defaultsMap.put(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK, null);

	}

	private MHyperLink mHyperLink;

	private MExpression dExpression;
	private MExpression hExpression;
	private MExpression lExpression;

	public Object getPropertyValue(Object id) {
		JRDesignPieSeries jrElement = (JRDesignPieSeries) getValue();

		if (id.equals(JRDesignPieSeries.PROPERTY_SECTION_HYPERLINK)) {
			JRHyperlink itemHyperLink = jrElement.getSectionHyperlink();
			if (itemHyperLink == null)
				itemHyperLink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(itemHyperLink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION)) {
			if (dExpression == null) {
				dExpression = new MExpression(jrElement.getKeyExpression());
				setChildListener(dExpression);
			}
			return dExpression;
		}
		if (id.equals(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (hExpression == null) {
				hExpression = new MExpression(jrElement.getLabelExpression());
				setChildListener(hExpression);
			}
			return hExpression;
		}
		if (id.equals(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION)) {
			if (lExpression == null) {
				lExpression = new MExpression(jrElement.getValueExpression());
				setChildListener(lExpression);
			}
			return lExpression;
		}

		return null;
	}

	public void setPropertyValue(Object id, Object value) {
		JRDesignPieSeries jrElement = (JRDesignPieSeries) getValue();

		if (id.equals(JRDesignPieSeries.PROPERTY_KEY_EXPRESSION)) {
			if (value instanceof MExpression) {
				dExpression = (MExpression) value;
				setChildListener(dExpression);
				JRExpression expression = (JRExpression) dExpression.getValue();
				jrElement.setKeyExpression(expression);
			}
		} else if (id.equals(JRDesignPieSeries.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				setChildListener(hExpression);
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignPieSeries.PROPERTY_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				setChildListener(lExpression);
				JRExpression expression = (JRExpression) lExpression.getValue();
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