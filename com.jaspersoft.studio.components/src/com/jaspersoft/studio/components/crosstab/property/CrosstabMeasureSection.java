package com.jaspersoft.studio.components.crosstab.property;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class CrosstabMeasureSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(3, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignCrosstabMeasure.PROPERTY_NAME)
				.getControl().setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabMeasure.PROPERTY_CALCULATION).getControl()
				.setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE)
				.getControl().setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent,
				JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION).getControl()
				.setLayoutData(gd);

		createWidget4Property(parent,
				JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS);

		createWidget4Property(parent,
				JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME);

		createWidget4Property(
				parent,
				JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME);
	}
}
