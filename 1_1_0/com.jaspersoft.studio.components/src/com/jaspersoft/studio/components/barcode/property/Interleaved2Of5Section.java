package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class Interleaved2Of5Section extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent,
				"Interleaved2Of5", false, 2);

		createWidget4Property(group,
				Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE);
		createWidget4Property(group,
				Interleaved2Of5Component.PROPERTY_WIDE_FACTOR);
		createWidget4Property(group,
				Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM);
	}
}
