package com.jaspersoft.studio.components.list.property;

import net.sf.jasperreports.components.list.DesignListContents;
import net.sf.jasperreports.components.list.StandardListComponent;

import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.ColumnLayout;

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

		ColumnLayout layout = new ColumnLayout();
		parent.setLayout(layout);

		Group group = getWidgetFactory().createGroup(parent, "List");
		group.setLayout(new GridLayout(2, false));
		// group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group,
				StandardListComponent.PROPERTY_IGNORE_WIDTH);
		createWidget4Property(group, MList.PREFIX
				+ DesignListContents.PROPERTY_HEIGHT);
		createWidget4Property(group, MList.PREFIX
				+ DesignListContents.PROPERTY_WIDTH);
		createWidget4Property(group, StandardListComponent.PROPERTY_PRINT_ORDER);

		group = getWidgetFactory().createGroup(parent, "Dataset Run");
		group.setLayout(new GridLayout(2, false));
		// group.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(group, MList.PREFIX
				+ StandardListComponent.PROPERTY_DATASET_RUN);
	}
}
