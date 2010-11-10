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
				JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION, "Close Expression");
		closeExprD.setDescription("Close expression.");
		desc.add(closeExprD);

		JRExpressionPropertyDescriptor dateExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION, "Date Expression");
		dateExprD.setDescription("Date expression.");
		desc.add(dateExprD);

		JRExpressionPropertyDescriptor highExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION, "High Expression");
		highExprD.setDescription("High expression.");
		desc.add(highExprD);

		JRExpressionPropertyDescriptor lowExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION, "Low Expression");
		lowExprD.setDescription("Low expression.");
		desc.add(lowExprD);

		JRExpressionPropertyDescriptor openExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION, "Open Expression");
		openExprD.setDescription("Open expression.");
		desc.add(openExprD);

		JRExpressionPropertyDescriptor seriesExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, "Series Expression");
		seriesExprD.setDescription("Series expression.");
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor volumeExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, "Volume Expression");
		volumeExprD.setDescription("Volume expression.");
		desc.add(volumeExprD);

		JRPropertyDescriptor hyperLinkD = new JRPropertyDescriptor(JRDesignHighLowDataset.PROPERTY_ITEM_HYPERLINK,
				"Item Hyperlink");
		hyperLinkD.setDescription("Item Hyperlink");
		desc.add(hyperLinkD);

		closeExprD.setCategory("Chart HighLow Dataset");
		dateExprD.setCategory("Chart HighLow Dataset");
		highExprD.setCategory("Chart HighLow Dataset");
		lowExprD.setCategory("Chart HighLow Dataset");
		openExprD.setCategory("Chart HighLow Dataset");
		seriesExprD.setCategory("Chart HighLow Dataset");
		volumeExprD.setCategory("Chart HighLow Dataset");
		hyperLinkD.setCategory("Chart HighLow Dataset");

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
			}
			return mHyperLink;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION)) {
			if (cExpression == null)
				cExpression = new MExpression(jrElement.getCloseExpression());
			return cExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION)) {
			if (dExpression == null)
				dExpression = new MExpression(jrElement.getDateExpression());
			return dExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION)) {
			if (hExpression == null)
				hExpression = new MExpression(jrElement.getHighExpression());
			return hExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION)) {
			if (lExpression == null)
				lExpression = new MExpression(jrElement.getLowExpression());
			return lExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION)) {
			if (oExpression == null)
				oExpression = new MExpression(jrElement.getOpenExpression());
			return oExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION)) {
			if (sExpression == null)
				sExpression = new MExpression(jrElement.getSeriesExpression());
			return sExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION)) {
			if (vExpression == null)
				vExpression = new MExpression(jrElement.getVolumeExpression());
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
				JRExpression expression = (JRExpression) cExpression.getValue();
				jrElement.setCloseExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dExpression.getValue();
				jrElement.setDateExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION)) {
			if (value instanceof MExpression) {
				hExpression = (MExpression) value;
				JRExpression expression = (JRExpression) hExpression.getValue();
				jrElement.setHighExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION)) {
			if (value instanceof MExpression) {
				lExpression = (MExpression) value;
				JRExpression expression = (JRExpression) lExpression.getValue();
				jrElement.setLowExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION)) {
			if (value instanceof MExpression) {
				oExpression = (MExpression) value;
				JRExpression expression = (JRExpression) oExpression.getValue();
				jrElement.setOpenExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION)) {
			if (value instanceof MExpression) {
				sExpression = (MExpression) value;
				JRExpression expression = (JRExpression) sExpression.getValue();
				jrElement.setSeriesExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION)) {
			if (value instanceof MExpression) {
				vExpression = (MExpression) value;
				JRExpression expression = (JRExpression) vExpression.getValue();
				jrElement.setVolumeExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}

}
