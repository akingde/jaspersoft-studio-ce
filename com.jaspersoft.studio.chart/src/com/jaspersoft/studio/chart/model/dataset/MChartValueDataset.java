package com.jaspersoft.studio.chart.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MChartValueDataset extends MChartDataset {

	public MChartValueDataset(ANode parent, JRDesignValueDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
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

		JRExpressionPropertyDescriptor valExpD = new JRExpressionPropertyDescriptor(
				JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, Messages.common_value_expression);
		valExpD.setDescription(Messages.MChartValueDataset_value_expression_description);
		desc.add(valExpD);

		valExpD.setCategory(Messages.MChartValueDataset_chart_value_dataset_category);

		defaultsMap.put(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, null);

	}

	private MExpression oExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION)) {
			if (oExpression == null){
				oExpression = new MExpression(jrElement.getValueExpression());
				setChildListener(oExpression);
			}
			return oExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				setChildListener(oExpression);
				JRExpression expression = (JRExpression) oExpression.getValue();
				jrElement.setValueExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

}
