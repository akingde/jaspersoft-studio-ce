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
package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SubreportSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM);

		createWidget4Property(parent, JRBaseSubreport.PROPERTY_USING_CACHE);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION);

		Composite cmp = getWidgetFactory().createComposite(parent);
		cmp.setLayout(new GridLayout(2, false));
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		cmp.setLayoutData(gd);

		createWidget4Property(cmp, JRDesignSubreport.PROPERTY_RETURN_VALUES, false);

		createWidget4Property(cmp, JRDesignSubreport.PROPERTY_PARAMETERS, false);
	}
}
