/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.dialog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.dataset.DatasetUtil;
import com.jaspersoft.studio.property.infoList.ElementDescription;

import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.properties.PropertiesMetadataUtil;
import net.sf.jasperreports.properties.PropertyMetadata;

/**
 * Class that define static methods to get the hint properties specific to some type of elements
 */
public class HintsPropertiesList {

	public static List<ElementDescription> getElementProperties(Object holder, ExpressionContext eContext) {
		List<ElementDescription> result = new ArrayList<ElementDescription>();
		if (holder instanceof JasperDesign) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());

				List<PropertyMetadata> pms = pmu.getReportProperties((JasperDesign) holder);
				for (PropertyMetadata pm : pms)
					result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRDesignDataset) {
			JRDesignDataset ds = (JRDesignDataset) holder;
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
				try {
					List<PropertyMetadata> pms = pmu.getDatasetProperties((JRDesignDataset) holder,
							DatasetUtil.getDataAdapter(eContext.getJasperReportsConfiguration(), ds));
					for (PropertyMetadata pm : pms)
						result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
				} catch (JRException e) {
					e.printStackTrace();
				}
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRElement) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());
				List<PropertyMetadata> pms = pmu.getElementProperties((JRElement) holder);
				for (PropertyMetadata pm : pms)
					result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRField) {
			PropertiesMetadataUtil pmu = PropertiesMetadataUtil.getInstance(eContext.getJasperReportsConfiguration());
			ClassLoader oldCl = Thread.currentThread().getContextClassLoader();
			try {
				Thread.currentThread().setContextClassLoader(JRLoader.class.getClassLoader());

				try {
					List<PropertyMetadata> pms = pmu.getQueryExecuterFieldProperties(
							(String) eContext.getJasperReportsConfiguration().get(COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE));
					for (PropertyMetadata pm : pms)
						result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
				} catch (JRException e) {
					e.printStackTrace();
				}
			} finally {
				Thread.currentThread().setContextClassLoader(oldCl);
			}
		} else if (holder instanceof JRParameter) {
			Map<String, PropertyMetadata> map = DatasetUtil.getPmap(eContext.getJasperReportsConfiguration());
			for (PropertyMetadata pm : map.values())
				result.add(new ElementDescription(pm.getName(), pm.getDescription(), true));
		}

		return result;
	}

	public static final String COM_JASPERSOFT_STUDIO_DATASET_LANGUAGE = "com.jaspersoft.studio.dataset.language";

}
