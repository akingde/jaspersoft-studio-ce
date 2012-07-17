package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TableDatasetSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardTable.PROPERTY_WHEN_NO_DATA_TYPE);

		Composite group = getWidgetFactory().createSection(parent,
				"Dataset Run", true, 2, 2);
		createWidget4Property(group, StandardTable.PROPERTY_DATASET_RUN);
	}
}
