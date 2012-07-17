package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.table.DesignCell;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TableCellSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, "Cell Properties",
				false, 2);

		createWidget4Property(parent, DesignCell.PROPERTY_HEIGHT);

		createWidget4Property(parent, DesignCell.PROPERTY_ROW_SPAN);
		createWidget4Property(parent, DesignCell.PROPERTY_STYLE);
	}
}
