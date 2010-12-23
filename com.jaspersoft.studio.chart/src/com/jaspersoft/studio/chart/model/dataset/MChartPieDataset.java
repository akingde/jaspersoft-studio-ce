package com.jaspersoft.studio.chart.model.dataset;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.design.events.CollectionElementAddedEvent;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.model.ReportFactory;
import com.jaspersoft.studio.property.descriptor.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MChartPieDataset extends MChartDataset {

	public MChartPieDataset(ANode parent, JRDesignPieDataset value, JasperDesign jasperDesign) {
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

		JRExpressionPropertyDescriptor keyExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieDataset.PROPERTY_KEY_EXPRESSION, Messages.MChartPieDataset_key_expression);
		keyExprD.setDescription(Messages.MChartPieDataset_key_expression_description);
		desc.add(keyExprD);

		JRExpressionPropertyDescriptor lblExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieDataset.PROPERTY_LABEL_EXPRESSION, Messages.MChartPieDataset_label_expression);
		lblExprD.setDescription(Messages.MChartPieDataset_label_expression_description);
		desc.add(lblExprD);

		JRExpressionPropertyDescriptor oKeyExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION, Messages.MChartPieDataset_other_key_expression);
		oKeyExprD.setDescription(Messages.MChartPieDataset_other_key_expression_description);
		desc.add(oKeyExprD);

		JRExpressionPropertyDescriptor oLblExprD = new JRExpressionPropertyDescriptor(
				JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION, Messages.MChartPieDataset_other_label_expression);
		oLblExprD.setDescription(Messages.MChartPieDataset_other_label_expression_description);
		desc.add(oLblExprD);

		JRExpressionPropertyDescriptor valExpD = new JRExpressionPropertyDescriptor(
				JRDesignPieDataset.PROPERTY_VALUE_EXPRESSION, Messages.common_value_expression);
		valExpD.setDescription(Messages.MChartPieDataset_value_expression_description);
		desc.add(valExpD);

		JRPropertyDescriptor oSectHyperLD = new JRPropertyDescriptor(JRDesignPieDataset.PROPERTY_OTHER_SECTION_HYPERLINK,
				Messages.MChartPieDataset_other_section_hyperlink);
		oSectHyperLD.setDescription(Messages.MChartPieDataset_other_section_hyperlink_description);
		desc.add(oSectHyperLD);

		JRPropertyDescriptor sectHyperLD = new JRPropertyDescriptor(JRDesignPieDataset.PROPERTY_SECTION_HYPERLINK,
				Messages.MChartPieDataset_section_hyperlink);
		sectHyperLD.setDescription(Messages.MChartPieDataset_section_hyperlink_description);
		desc.add(sectHyperLD);

		IntegerPropertyDescriptor maxCountD = new IntegerPropertyDescriptor(JRDesignPieDataset.PROPERTY_MAX_COUNT,
				Messages.MChartPieDataset_max_count);
		maxCountD.setDescription(Messages.MChartPieDataset_max_count_description);
		desc.add(maxCountD);

		FloatPropertyDescriptor minPercD = new FloatPropertyDescriptor(JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE,
				Messages.MChartPieDataset_min_percentage);
		minPercD.setDescription(Messages.MChartPieDataset_min_percentage_description);
		desc.add(minPercD);

		keyExprD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		lblExprD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		oKeyExprD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		oLblExprD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		valExpD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		oSectHyperLD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		sectHyperLD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);

		maxCountD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);
		minPercD.setCategory(Messages.MChartPieDataset_chart_pie_dataset_category);

		defaultsMap.put(JRDesignPieDataset.PROPERTY_KEY_EXPRESSION, null);
		defaultsMap.put(JRDesignPieDataset.PROPERTY_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION, null);
		defaultsMap.put(JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION, null);
		defaultsMap.put(JRDesignPieDataset.PROPERTY_VALUE_EXPRESSION, null);

	}

	private MHyperLink mHyperLink;
	private MHyperLink omHyperLink;

	private MExpression cExpression;
	private MExpression dExpression;
	private MExpression hExpression;
	private MExpression lExpression;
	private MExpression oExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignPieDataset jrElement = (JRDesignPieDataset) getValue();

		if (id.equals(JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE))
			return jrElement.getMinPercentage();
		if (id.equals(JRDesignPieDataset.PROPERTY_MAX_COUNT))
			return jrElement.getMaxCount();
		if (id.equals(JRDesignPieDataset.PROPERTY_SECTION_HYPERLINK)) {
			JRHyperlink sectionHyperlink = jrElement.getSectionHyperlink();
			if (sectionHyperlink == null)
				sectionHyperlink = new JRDesignHyperlink();
			mHyperLink = new MHyperLink(sectionHyperlink);
			setChildListener(mHyperLink);
			return mHyperLink;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_OTHER_SECTION_HYPERLINK)) {
			JRHyperlink otherSectionHyperlink = jrElement.getOtherSectionHyperlink();
			if (otherSectionHyperlink == null)
				otherSectionHyperlink = new JRDesignHyperlink();
			omHyperLink = new MHyperLink(otherSectionHyperlink);
			setChildListener(omHyperLink);
			return omHyperLink;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_KEY_EXPRESSION)) {
			if (cExpression == null) {
				cExpression = new MExpression(jrElement.getKeyExpression());
				setChildListener(cExpression);
			}
			return cExpression;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_LABEL_EXPRESSION)) {
			if (dExpression == null) {
				dExpression = new MExpression(jrElement.getLabelExpression());
				setChildListener(dExpression);
			}
			return dExpression;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION)) {
			if (hExpression == null) {
				hExpression = new MExpression(jrElement.getOtherKeyExpression());
				setChildListener(hExpression);
			}
			return hExpression;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION)) {
			if (lExpression == null) {
				lExpression = new MExpression(jrElement.getOtherLabelExpression());
				setChildListener(lExpression);
			}
			return lExpression;
		}
		if (id.equals(JRDesignPieDataset.PROPERTY_VALUE_EXPRESSION)) {
			if (oExpression == null) {
				oExpression = new MExpression(jrElement.getValueExpression());
				setChildListener(oExpression);
			}
			return oExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignPieDataset jrElement = (JRDesignPieDataset) getValue();

		if (id.equals(JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE))
			jrElement.setMinPercentage((Float) value);
		else if (id.equals(JRDesignPieDataset.PROPERTY_MAX_COUNT))
			jrElement.setMaxCount((Integer) value);
		else if (id.equals(JRDesignPieDataset.PROPERTY_KEY_EXPRESSION)) {
			if (value instanceof MExpression) {
				cExpression = (MExpression) value;
				setChildListener(cExpression);
				JRExpression expression = (JRExpression) cExpression.getValue();
				jrElement.setKeyExpression(expression);
			}
		} else if (id.equals(JRDesignPieDataset.PROPERTY_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				dExpression = (MExpression) value;
				setChildListener(dExpression);
				JRExpression expression = (JRExpression) dExpression.getValue();
				jrElement.setLabelExpression(expression);
			}
		} else if (id.equals(JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				setChildListener(hExpression);
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setOtherKeyExpression(expression);
			}
		} else if (id.equals(JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				setChildListener(lExpression);
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setOtherLabelExpression(expression);
			}
		} else if (id.equals(JRDesignPieDataset.PROPERTY_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				setChildListener(oExpression);
				JRExpression expression = (JRExpression) oExpression.getValue();
				jrElement.setValueExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals("pieSeries")) { //$NON-NLS-1$
			if (evt.getSource() == getValue()) {
				if (evt.getOldValue() == null && evt.getNewValue() != null) {
					int newIndex = -1;
					if (evt instanceof CollectionElementAddedEvent) {
						newIndex = ((CollectionElementAddedEvent) evt).getAddedIndex();
					}
					ReportFactory.createNode(this, evt.getNewValue(), newIndex);
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
		}
		super.propertyChange(evt);
	}

}