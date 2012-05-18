package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class ParameterSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		createWidget4Property(parent, JRDesignParameter.PROPERTY_DESCRIPTION).getControl().setLayoutData(gd);
		createWidget4Property(parent, JRDesignParameter.PROPERTY_FOR_PROMPTING);
		createWidget4Property(parent, JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION);

	}
}
