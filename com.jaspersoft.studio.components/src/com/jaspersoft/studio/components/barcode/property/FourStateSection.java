package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.FourStateBarcodeComponent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class FourStateSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Group group = getWidgetFactory().createGroup(parent, "FourState");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group,
				FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE);
		createWidget4Property(group,
				FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH);
		createWidget4Property(group,
				FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT);
		createWidget4Property(group,
				FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT);
	}
}
