package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.POSTNETComponent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class POSTNETSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Group group = getWidgetFactory().createGroup(parent, "POSTNET");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group,
				POSTNETComponent.PROPERTY_SHORT_BAR_HEIGHT);

		createWidget4Property(group,
				POSTNETComponent.PROPERTY_INTERCHAR_GAP_WIDTH);
		createWidget4Property(group,
				POSTNETComponent.PROPERTY_DISPLAY_CHECKSUM);
		createWidget4Property(group, POSTNETComponent.PROPERTY_CHECKSUM_MODE);

		createWidget4Property(group,
				POSTNETComponent.PROPERTY_BASELINE_POSITION);
	}
}
