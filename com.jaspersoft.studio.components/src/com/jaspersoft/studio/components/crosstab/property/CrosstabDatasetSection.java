/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.property;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabDataset;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.MCrosstabDataset;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

public class CrosstabDatasetSection extends DatasetSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabDataset.PROPERTY_DATA_PRE_SORTED, false)
				.getControl().setLayoutData(gd);
	}

	@Override
	protected APropertyNode getModelFromEditPart(Object item) {
		APropertyNode md = super.getModelFromEditPart(item);
		if (md instanceof MCrosstab) {
			MCrosstabDataset dataset = (MCrosstabDataset) md
					.getPropertyValue(JRDesignCrosstab.PROPERTY_DATASET);
			return dataset;
		}
		return md;
	}
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignCrosstabDataset.PROPERTY_DATA_PRE_SORTED, Messages.MCrosstabDataset_data_presorted);
	}
}
