package com.jaspersoft.studio.components.table.properties;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class TableCellSection extends AbstractSection {
	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRDesignCrosstabCell.PROPERTY_WIDTH);
		createWidget4Property(parent, JRDesignCrosstabCell.PROPERTY_HEIGHT);

		createWidget4Property(parent, DesignCell.PROPERTY_ROW_SPAN);
		createWidget4Property(parent, DesignCell.PROPERTY_STYLE);

		createWidget4Property(parent,
				StandardBaseColumn.PROPERTY_PRINT_WHEN_EXPRESSION);

	}
}
