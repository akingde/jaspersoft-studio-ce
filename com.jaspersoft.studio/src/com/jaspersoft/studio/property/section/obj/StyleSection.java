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

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

public class StyleSection extends AbstractRealValueSection {

	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_NAME);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PARENT_STYLE);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_DEFAULT, false).getControl().setLayoutData(gd);
		
		createWidget4Property(parent, JRDesignStyle.PROPERTY_BLANK_WHEN_NULL);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PATTERN);

		createWidget4Property(parent, JRBaseStyle.PROPERTY_FILL);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_RADIUS);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_SCALE_IMAGE);
	}
}
