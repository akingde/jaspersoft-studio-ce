/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.engine.JRHyperlink;
import net.sf.jasperreports.engine.design.JRDesignHyperlink;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.MHyperLink;
import com.jaspersoft.studio.property.descriptor.JRPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
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
				JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION, Messages.common_series_expression);
		seriesExprD.setDescription(Messages.MChartHighLowDataset_series_expression_description);
		desc.add(seriesExprD);

		JRExpressionPropertyDescriptor volumeExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION, Messages.MChartHighLowDataset_volume_expression);
		volumeExprD.setDescription(Messages.MChartHighLowDataset_volume_expression_description);
		desc.add(volumeExprD);

		JRPropertyDescriptor hyperLinkD = new JRPropertyDescriptor(JRDesignHighLowDataset.PROPERTY_ITEM_HYPERLINK,
				Messages.common_item_hyperlink);
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
			cExpression = ExprUtil.getExpression(this, cExpression, jrElement.getCloseExpression());
			return cExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION)) {
			dExpression = ExprUtil.getExpression(this, dExpression, jrElement.getDateExpression());
			return dExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION)) {
			hExpression = ExprUtil.getExpression(this, hExpression, jrElement.getHighExpression());
			return hExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION)) {
			lExpression = ExprUtil.getExpression(this, lExpression, jrElement.getLowExpression());
			return lExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION)) {
			oExpression = ExprUtil.getExpression(this, oExpression, jrElement.getOpenExpression());
			return oExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION)) {
			sExpression = ExprUtil.getExpression(this, sExpression, jrElement.getSeriesExpression());
			return sExpression;
		}
		if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION)) {
			vExpression = ExprUtil.getExpression(this, vExpression, jrElement.getVolumeExpression());
			return vExpression;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignHighLowDataset jrElement = (JRDesignHighLowDataset) getValue();

		if (id.equals(JRDesignHighLowDataset.PROPERTY_CLOSE_EXPRESSION))
			jrElement.setCloseExpression(ExprUtil.setValues(jrElement.getCloseExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_DATE_EXPRESSION))
			jrElement.setDateExpression(ExprUtil.setValues(jrElement.getDateExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_HIGH_EXPRESSION))
			jrElement.setHighExpression(ExprUtil.setValues(jrElement.getHighExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_LOW_EXPRESSION))
			jrElement.setLowExpression(ExprUtil.setValues(jrElement.getLowExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_OPEN_EXPRESSION))
			jrElement.setOpenExpression(ExprUtil.setValues(jrElement.getOpenExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_SERIES_EXPRESSION))
			jrElement.setSeriesExpression(ExprUtil.setValues(jrElement.getSeriesExpression(), value));
		else if (id.equals(JRDesignHighLowDataset.PROPERTY_VOLUME_EXPRESSION))
			jrElement.setVolumeExpression(ExprUtil.setValues(jrElement.getVolumeExpression(), value));
		else
			super.setPropertyValue(id, value);
	}

}
