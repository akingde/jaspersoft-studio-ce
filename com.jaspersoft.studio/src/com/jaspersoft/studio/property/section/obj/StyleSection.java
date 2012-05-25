package com.jaspersoft.studio.property.section.obj;

import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

public class StyleSection extends AbstractSection {
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent.setLayout(new GridLayout(2, false));

		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_NAME);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PARENT_STYLE);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_BLANK_WHEN_NULL);

		if (!(getElement() instanceof MConditionalStyle))
			createWidget4Property(parent, JRDesignStyle.PROPERTY_DEFAULT);

		createWidget4Property(parent, JRDesignStyle.PROPERTY_PATTERN);

		createWidget4Property(parent, JRBaseStyle.PROPERTY_FILL);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_RADIUS);
		createWidget4Property(parent, JRBaseStyle.PROPERTY_SCALE_IMAGE);
	}
}
