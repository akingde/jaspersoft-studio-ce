package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SortFieldSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRDesignSortField.PROPERTY_NAME);

		createWidget4Property(parent, JRDesignSortField.PROPERTY_TYPE);
		createWidget4Property(parent, JRDesignSortField.PROPERTY_ORDER);
	}
}
