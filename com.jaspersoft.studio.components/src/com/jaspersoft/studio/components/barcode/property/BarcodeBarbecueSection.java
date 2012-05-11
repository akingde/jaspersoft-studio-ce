package com.jaspersoft.studio.components.barcode.property;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class BarcodeBarbecueSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Group group = getWidgetFactory().createGroup(parent, "Barbecue");
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_BAR_WIDTH);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_BAR_HEIGTH);
		createWidget4Property(
				group,
				StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION);
		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_TYPE);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED, false)
				.getControl().setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_DRAW_TEXT, false)
				.getControl().setLayoutData(gd);

		createWidget4Property(group,
				StandardBarbecueComponent.PROPERTY_ROTATION);

	}
}