package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.base.JRBaseSubreport;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class SubreportSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, JRBaseSubreport.PROPERTY_RUN_TO_BOTTOM);

		createWidget4Property(parent, JRBaseSubreport.PROPERTY_USING_CACHE);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_PARAMETERS_MAP_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_CONNECTION_EXPRESSION);
		createWidget4Property(parent, JRDesignSubreport.PROPERTY_DATASOURCE_EXPRESSION);
	}
}
