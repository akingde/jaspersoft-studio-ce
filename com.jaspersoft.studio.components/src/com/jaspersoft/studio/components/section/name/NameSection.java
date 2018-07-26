/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.section.name;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

public class NameSection extends AbstractSection {
	
	public NameSection() {
		super();
	}
	
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent = getWidgetFactory().createComposite(parent);
		parent.setLayout(new GridLayout(1,false));
		parent.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		IPropertyDescriptor columnDescriptor = getElement().getPropertyDescriptor(MTable.PROPERTY_COLUMNS_AUTORESIZE_NEXT);
		if (columnDescriptor != null){
			ASPropertyWidget<?> widget = createWidget4Property(getElement(), parent, MTable.PROPERTY_COLUMNS_AUTORESIZE_NEXT, false);
			GridData controlData = new GridData();
			widget.getControl().setLayoutData(controlData);
		}

		columnDescriptor = getElement().getPropertyDescriptor(MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL);
		if (columnDescriptor != null){
			ASPropertyWidget<?> widget = createWidget4Property(getElement(), parent, MTable.PROPERTY_COLUMNS_AUTORESIZE_PROPORTIONAL, false);
			GridData controlData = new GridData();
			widget.getControl().setLayoutData(controlData);
		}
	}
}
