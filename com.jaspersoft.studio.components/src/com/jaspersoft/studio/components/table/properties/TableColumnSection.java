package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.table.StandardBaseColumn;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TableColumnSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardBaseColumn.PROPERTY_WIDTH);

		createWidget4Property(parent,
				StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION);

	}
}
