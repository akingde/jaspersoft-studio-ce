/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JRDesignParameter;
import net.sf.jasperreports.engine.design.JRDesignQuery;

import com.jaspersoft.studio.property.dataset.wizard.WizardDatasetNewPage;
import com.jaspersoft.studio.wizards.datasource.StaticWizardDataSourcePage;

/**
 * Utilities to create objects from wizard settings
 * 
 * @author gtoffoli
 * 
 */
public class WizardUtils {

	/**
	 * Create a new JRDesignDataset by looking into the settings for the following information (keys), which are all
	 * optional:<br>
	 * <ul>
	 * <li>WizardDatasetNewPage.DATASET_NAME Name of the dataset.</li>
	 * <li>WizardDatasetNewPage.DATASET_EMPTY If true returns an empty dataset.</li>
	 * <li>WizardDatasetNewPage.DATASET_FIELDS The fields of the dataset.</li>
	 * <li>WizardDatasetNewPage.GROUP_FIELDS A List of JRDesignFields to be used as grouping fields.</li>
	 * </ul>
	 * 
	 * @param isMain
	 *          - True if this is a main dataset, false otherwise.
	 * @param settings
	 *          - a Map<String, Object> populated the objects useful to create the dataset
	 * @return JRDesignDataset
	 */
	@SuppressWarnings("unchecked")
	public static JRDesignDataset createDataset(JasperReportsContext jContext, boolean isMain,
			Map<String, Object> settings) {

		// Create a new dataset
		JRDesignDataset dataset = new JRDesignDataset(jContext, isMain);
		JRDesignQuery query = new JRDesignQuery();
		dataset.setQuery(query);

		// Get values from the settings...
		if (settings != null) {

			if (settings.containsKey(WizardDatasetNewPage.DATASET_NAME)) {
				dataset.setName((String) settings.get(WizardDatasetNewPage.DATASET_NAME));
			}

			// If the user specified to use an empty dataset, return the dataset as it is...
			if (settings.containsKey(WizardDatasetNewPage.DATASET_EMPTY)) {
				Boolean b = (Boolean) settings.get(WizardDatasetNewPage.DATASET_EMPTY);
				if (b.booleanValue() == true) {
					return dataset;
				}
			}

			if (settings.containsKey(StaticWizardDataSourcePage.DATASET_QUERY_LANGUAGE)) {
				query.setLanguage((String) settings.get(StaticWizardDataSourcePage.DATASET_QUERY_LANGUAGE));
			}

			if (settings.containsKey(StaticWizardDataSourcePage.DATASET_QUERY_TEXT)) {
				query.setText((String) settings.get(StaticWizardDataSourcePage.DATASET_QUERY_TEXT));
			}

			if (settings.containsKey(StaticWizardDataSourcePage.DATASET_PROPERTIES)) {
				JRPropertiesMap map = (JRPropertiesMap) settings.get(StaticWizardDataSourcePage.DATASET_PROPERTIES);
				for (String prop : map.getPropertyNames())
					dataset.setProperty(prop, map.getProperty(prop));
			}
			if (settings.containsKey(StaticWizardDataSourcePage.DISCOVERED_PARAMETERS)) {
				List<JRDesignParameter> prms = (List<JRDesignParameter>) settings
						.get(StaticWizardDataSourcePage.DISCOVERED_PARAMETERS);
				for (JRDesignParameter p : prms)
					try {
						dataset.addParameter(p);
					} catch (JRException e) {
						// Let's ignore exceptions here, the worst case would be a duplicated parameters name not getting
						// in the dataset, situation that should be checked upfront, not now.
						e.printStackTrace();
					}
			}

			// Check for fields...
			if (settings.containsKey(StaticWizardDataSourcePage.DATASET_FIELDS)) {
				List<JRDesignField> fields = (List<JRDesignField>) (settings.get(StaticWizardDataSourcePage.DATASET_FIELDS));
				for (JRDesignField f : fields) {
					try {
						dataset.addField(f);
					} catch (JRException ex) {
						// Let's ignore exceptions here, the worst case would be a duplicated fields name not getting
						// in the dataset, situation that should be checked upfront, not now.
						ex.printStackTrace();
					}
				}

				// If there are fields, there may be groups also...
				if (settings.containsKey(StaticWizardDataSourcePage.GROUP_FIELDS)) {
					List<JRDesignField> groupFields = (List<JRDesignField>) (settings.get(StaticWizardDataSourcePage.GROUP_FIELDS));
					for (JRDesignField f : groupFields) {
						try {
							String name = ((JRField) f).getName();

							JRDesignGroup group = new JRDesignGroup();
							group.setName(name);

							JRDesignExpression jre = new JRDesignExpression();
							jre.setText("$F{" + name + "}"); //$NON-NLS-1$ //$NON-NLS-2$
							group.setExpression(jre);

							dataset.addGroup(group);

						} catch (JRException ex) {
							// Let's ignore exceptions here, the worst case would be a duplicated group name not getting
							// in the dataset, situation that should be checked upfront, not now.
							ex.printStackTrace();
						}
					}
				}

			}
		}

		return dataset;
	}

}
