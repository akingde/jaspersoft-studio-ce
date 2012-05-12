package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.PDF417Component;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class PDF417Section extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "PDF417",
				false, 2);

		createWidget4Property(group, PDF417Component.PROPERTY_MIN_COLUMNS);

		createWidget4Property(group, PDF417Component.PROPERTY_MAX_COLUMNS);
		createWidget4Property(group, PDF417Component.PROPERTY_MIN_ROWS);
		createWidget4Property(group, PDF417Component.PROPERTY_MAX_ROWS);

		GridData gd = new GridData();
		gd.horizontalSpan = 3;
		createWidget4Property(group,
				PDF417Component.PROPERTY_ERROR_CORRECTION_LEVEL).getControl()
				.setLayoutData(gd);
	}
}
