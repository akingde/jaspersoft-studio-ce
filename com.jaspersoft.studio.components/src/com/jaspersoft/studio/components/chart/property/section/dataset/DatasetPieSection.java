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
package com.jaspersoft.studio.components.chart.property.section.dataset;

import net.sf.jasperreports.charts.design.JRDesignPieDataset;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.dataset.descriptor.DatasetSection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class DatasetPieSection extends DatasetSection {

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Pie Dataset", false,
				2, 2);

		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_OTHER_KEY_EXPRESSION);
		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_OTHER_LABEL_EXPRESSION);

		createWidget4Property(parent,
				JRDesignPieDataset.PROPERTY_MIN_PERCENTAGE);
		createWidget4Property(parent, JRDesignPieDataset.PROPERTY_MAX_COUNT);

	}

}
