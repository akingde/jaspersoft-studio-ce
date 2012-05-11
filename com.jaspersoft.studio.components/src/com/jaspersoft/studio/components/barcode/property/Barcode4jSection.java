package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.BarcodeComponent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Barcode4jSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Group group = getWidgetFactory().createGroup(parent, "Barcode 4J");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group,
				BarcodeComponent.PROPERTY_PATTERN_EXPRESSION);

		createWidget4Property(group, BarcodeComponent.PROPERTY_QUIET_ZONE);
		createWidget4Property(group, BarcodeComponent.PROPERTY_MODULE_WIDTH);
		createWidget4Property(group,
				BarcodeComponent.PROPERTY_VERTICAL_QUIET_ZONE);

		createWidget4Property(group, BarcodeComponent.PROPERTY_ORIENTATION);
		createWidget4Property(group, BarcodeComponent.PROPERTY_TEXT_POSITION);
	}
}
