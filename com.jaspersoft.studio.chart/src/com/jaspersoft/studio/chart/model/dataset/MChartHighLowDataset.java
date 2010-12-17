package com.jaspersoft.studio.chart.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MChartHighLowDataset extends MChartDataset {

	public MChartHighLowDataset(ANode parent, JRDesignHighLowDataset value, JasperDesign jasperDesign) {
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

		JRExpressionPropertyDescriptor closeExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION, Messages.MChartHighLowDataset_close_expression);
		closeExprD.setDescription(Messages.MChartHighLowDataset_close_expression_description);
		desc.add(closeExprD);

		JRExpressionPropertyDescriptor dateExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION, Messages.MChartHighLowDataset_data_expression);
		dateExprD.setDescription(Messages.MChartHighLowDataset_data_expression_description);
		desc.add(dateExprD);

		JRExpressionPropertyDescriptor highExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION, Messages.MChartHighLowDataset_high_expression);
		highExprD.setDescription(Messages.MChartHighLowDataset_high_expression_description);
		desc.add(highExprD);

		JRExpressionPropertyDescriptor lowExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION, Messages.MChartHighLowDataset_low_expression);
		lowExprD.setDescription(Messages.MChartHighLowDataset_low_expression_description);
		desc.add(lowExprD);

		JRExpressionPropertyDescriptor openExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION, Messages.MChartHighLowDataset_open_expression);
		openExprD.setDescription(Messages.MChartHighLowDataset_open_expression_description);
		desc.add(openExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, Messages.MChartHighLowDataset_series_expression);
		seriesExprD.setDescription(Messages.MChartHighLowDataset_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor volumeExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, Messages.MChartHighLowDataset_volume_expression);
		volumeExprD.setDescription(Messages.MChartHighLowDataset_volume_expression_description);
		desc.add(volumeExprD);

		JRPropertyDescriptor hyperLinkD = new JRPropertyDescriptor(JRDesignHighLowDataset.PROPERTY_ITEM_HYPERLINK,
				Messages.MChartHighLowDataset_item_hyperlink);
		hyperLinkD.setDescription(Messages.MChartHighLowDataset_item_hyperlink_description);
		desc.add(hyperLinkD);

		closeExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		dateExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		highExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		lowExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		openExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		seriesExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		volumeExprD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);
		hyperLinkD.setCategory(Messages.MChartHighLowDataset_chart_highlow_dataset_category);

		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, null);
		defaultsMap.put(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, null);
	}

	private MHyperLink mHyperLink;

	private MExpression cExpression;
	private MExpression dExpression;
	private MExpression hExpression;
	private MExpression lExpression;
	private MExpression oExpression;
	private MExpression sExpression;
	private MExpression vExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignHighLowDataset jrElement = (JRDesignHighLowDataset) getValue();

		if (id.equals(JRDesignHighLowDataset.PROPERTY_ITEM_HYPERLINK)) {
			if (mHyperLink == null) {
				JRHyperlink itemHyperlink = jrElement.getItemHyperlink();
				if (itemHyperlink == null)
					itemHyperlink = new JRDesignHyperlink();
				mHyperLink = new MHyperLink(itemHyperlink);
				setChildListener(mHyperLink);
			}
			return mHyperLink;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION)) {
			if (cExpression == null) {
				cExpression = new MExpression(jrElement.getCloseExpression());
				setChildListener(cExpression);
			}
			return cExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION)) {
			if (dExpression == null) {
				dExpression = new MExpression(jrElement.getDateExpression());
				setChildListener(dExpression);
			}
			return dExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION)) {
			if (hExpression == null) {
				hExpression = new MExpression(jrElement.getHighExpression());
				setChildListener(hExpression);
			}
			return hExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION)) {
			if (lExpression == null) {
				lExpression = new MExpression(jrElement.getLowExpression());
				setChildListener(lExpression);
			}
			return lExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION)) {
			if (oExpression == null) {
				oExpression = new MExpression(jrElement.getOpenExpression());
				setChildListener(oExpression);
			}
			return oExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION)) {
			if (sExpression == null) {
				sExpression = new MExpression(jrElement.getSeriesExpression());
				setChildListener(sExpression);
			}
			return sExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION)) {
			if (vExpression == null) {
				vExpression = new MExpression(jrElement.getVolumeExpression());
				setChildListener(vExpression);
			}
			return vExpression;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignHighLowDataset jrElement = (JRDesignHighLowDataset) getValue();

		if (id.equals(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION)) {
			if (value instanceof MExpression) {
				cExpression = (MExpression) value;
				setChildListener(cExpression);
				JRExpression expression = (JRExpression) cExpression.getValue();
				jrElement.setCloseExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dExpression = (MExpression) value;
				setChildListener(dExpression);
				JRExpression expression = (JRExpression) dExpression.getValue();
				jrElement.setDateExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				setChildListener(hExpression);
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setHighExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				setChildListener(lExpression);
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setLowExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				setChildListener(oExpression);
				JRExpression expression = (JRExpression) oExpression.getValue();
				jrElement.setOpenExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				sExpression = (MExpression) value;
				setChildListener(sExpression);
				JRExpression expression = (JRExpression) sExpression.getValue();
				jrElement.setSeriesExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION)) {
			if (value instanceof MExpression) {
				vExpression = (MExpression) value;
				setChildListener(vExpression);
				JRExpression expression = (JRExpression) vExpression.getValue();
				jrElement.setVolumeExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

}
