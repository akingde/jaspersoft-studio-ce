/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.property;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;

public class BarcodeBarbecueSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = getWidgetFactory().createSection(parent, "Barbecue", false, 2);

		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_BAR_WIDTH);

		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_BAR_HEIGTH);
		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION);
		Combo cmb = (Combo) createWidget4Property(group, StandardBarbecueComponent.PROPERTY_TYPE).getControl();

		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_ROTATION);

		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		createWidget4Property(group, StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED, false).getControl()
				.setLayoutData(gd);

		final GridData gdt = new GridData();
		gdt.horizontalSpan = 2;
		final Control wDrawText = createWidget4Property(group, StandardBarbecueComponent.PROPERTY_DRAW_TEXT, false)
				.getControl();
		wDrawText.setLayoutData(gdt);

		hidePDF417(gd, wDrawText);
		cmb.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hidePDF417(gdt, wDrawText);
			}
		});
	}

	protected void hidePDF417(GridData gd, Control wDrawText) {
		String type = (String) getElement().getPropertyValue(StandardBarbecueComponent.PROPERTY_TYPE);
		if (type != null && type.equals("PDF417")) {
			gd.exclude = true;
			wDrawText.setVisible(false);
		} else {
			gd.exclude = false;
			wDrawText.setVisible(true);
		}
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_BAR_WIDTH, Messages.MBarcodeBarbecue_bar_width);
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_BAR_HEIGTH, Messages.MBarcodeBarbecue_bar_height);
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_APPLICATION_IDENTIFIER_EXPRESSION,
				Messages.MBarcodeBarbecue_application_identifier_expression);
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_CHECKSUM_REQUIRED,
				Messages.MBarcodeBarbecue_checksum_required);
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_DRAW_TEXT, Messages.MBarcodeBarbecue_draw_text);
		addProvidedProperties(StandardBarbecueComponent.PROPERTY_ROTATION, Messages.MBarcodeBarbecue_rotation);
	}
}
