package com.jaspersoft.studio.components.list.property;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ListSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent,
				StandardListComponent.PROPERTY_IGNORE_WIDTH);
		createWidget4Property(parent, MList.PREFIX
				+ DesignListContents.PROPERTY_HEIGHT);
		createWidget4Property(parent, MList.PREFIX
				+ DesignListContents.PROPERTY_WIDTH);
		createWidget4Property(parent,
				StandardListComponent.PROPERTY_PRINT_ORDER);
	}
}
