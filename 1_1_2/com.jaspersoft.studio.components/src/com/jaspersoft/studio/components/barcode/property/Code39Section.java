package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.Code39Component;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Code39Section extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Code39",
				false, 2);

		createWidget4Property(group, Code39Component.PROPERTY_WIDE_FACTOR);

		createWidget4Property(group,
				Code39Component.PROPERTY_INTERCHAR_GAP_WIDTH);
		createWidget4Property(group, Code39Component.PROPERTY_DISPLAY_CHECKSUM);
		createWidget4Property(group,
				Code39Component.PROPERTY_DISPLAY_START_STOP);

		createWidget4Property(group,
				Code39Component.PROPERTY_EXTENDED_CHARSET_ENABLED);
		createWidget4Property(group, Code39Component.PROPERTY_CHECKSUM_MODE);
	}
}
