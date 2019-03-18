/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.graphic;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.properties.layout.ResizableControlLayout;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.engine.design.JRDesignElement;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class GraphicSection extends AbstractSection {

	private ExpandableComposite section1;

	private ExpandableComposite section2;
	
	private static int defCharWidth = -1;

	public static int getCharWidth(Control c) {
		if (defCharWidth < 0)
			defCharWidth = UIUtil.getCharWidth(c);
		return defCharWidth;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = getWidgetFactory().createSection(parent, Messages.GraphicSection_Detail_Section_Title, true, 2);
		section1 = (ExpandableComposite) parent.getParent();

		createWidget4Property(parent, MGraphicElement.PROPERTY_ELEMENT_NAME).getControl()
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createWidget4Property(parent, JRDesignElement.PROPERTY_KEY).getControl()
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		Composite styleContainer = new Composite(parent, SWT.NONE);
		//custom layout that make the style control start (small) and allow it to grow until it reach the width of the 
		//panel, but not above it
		Layout styleLayout = new ResizableControlLayout(100);
		styleContainer.setLayoutData(gd);
		createWidget4Property(styleContainer, JRDesignElement.PROPERTY_PARENT_STYLE).getControl().setLayoutData(gd);
		styleContainer.setLayout(styleLayout);
		
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES, false).getControl()
				.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK, false).getControl()
				.setLayoutData(gd);

		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND, false).getControl()
				.setLayoutData(gd);

		parent = getWidgetFactory().createSection(parent, Messages.MGraphicElement_print_when, true, 3, 2);
		section2 = (ExpandableComposite) parent.getParent();

		gd = new GridData();
		gd.horizontalSpan = 3;
		createWidget4Property(parent, JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS, false).getControl()
				.setLayoutData(gd);

		gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES).getControl().setLayoutData(gd);

		createWidget4Property(parent, JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION);

		gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.horizontalSpan = 2;
		createWidget4Property(parent, JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS, false).getControl()
				.setLayoutData(gd);
	}

	private void expandSection(ExpandableComposite section) {
		if (section != null && !section.isExpanded())
			section.setExpanded(true);
	}

	@Override
	public void expandForProperty(Object propertyId) {
		expandSection(section1);
		if (propertyId.equals(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS)
				|| propertyId.equals(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES)
				|| propertyId.equals(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION))
			expandSection(section2);
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRDesignElement.PROPERTY_KEY, Messages.common_key);
		addProvidedProperties(JRDesignElement.PROPERTY_PARENT_STYLE, Messages.common_parent_style);
		addProvidedProperties(JRDesignElement.PROPERTY_PRINT_REPEATED_VALUES,
				Messages.MGraphicElement_print_repeated_values);
		addProvidedProperties(JRDesignElement.PROPERTY_REMOVE_LINE_WHEN_BLANK,
				Messages.MGraphicElement_remove_line_when_blank);
		addProvidedProperties(JRDesignElement.PROPERTY_PRINT_IN_FIRST_WHOLE_BAND,
				Messages.MGraphicElement_print_in_first_whole_band);
		addProvidedProperties(JRDesignElement.PROPERTY_PRINT_WHEN_DETAIL_OVERFLOWS,
				Messages.MGraphicElement_print_when_detail_overflows);
		addProvidedProperties(JRDesignElement.PROPERTY_PRINT_WHEN_GROUP_CHANGES,
				Messages.MGraphicElement_print_when_group_changes);
		addProvidedProperties(JRDesignElement.PROPERTY_PRINT_WHEN_EXPRESSION, Messages.common_print_when_expression);
	}

}
