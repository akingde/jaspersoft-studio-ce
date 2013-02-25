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
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseTextField;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JRDesignTextField;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class TextFieldSection extends AbstractRealValueSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRDesignTextField.PROPERTY_EXPRESSION);

		IPropertyDescriptor pd = getPropertyDesriptor(JRDesignTextField.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(JRDesignTextField.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(parent, this, pd, gpd));

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		
		createWidget4Property(parent, JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,false).getControl().setLayoutData(gd);

		createWidget4Property(parent, JRBaseTextField.PROPERTY_STRETCH_WITH_OVERFLOW, false).getControl().setLayoutData(gd);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PATTERN);

		createWidget4Property(parent, JRDesignTextField.PROPERTY_PATTERN_EXPRESSION);
	}

}
