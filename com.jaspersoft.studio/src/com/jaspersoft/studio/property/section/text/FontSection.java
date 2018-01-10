/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.text;

import net.sf.jasperreports.engine.base.JRBaseStyle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.forms.widgets.ExpandableComposite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractRealValueSection;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class FontSection extends AbstractRealValueSection {

	private ExpandableComposite section;
	
	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		Composite group = createFontSection(parent);
		
		//A composite with a static width is necessary because the toolbar used to implement the font_increment dosen't
		//return the real size when the size is computed, so it causes problem with the bottom scrollbar
		Composite firstLineContainer = new Composite(group, SWT.NONE);
		GridLayout firstLineContainerLayout = new GridLayout(3, false);
		firstLineContainerLayout.marginHeight = 0;
		firstLineContainerLayout.marginWidth = 0;
		firstLineContainer.setLayout(firstLineContainerLayout);
		GridData firtstLineContainerData = new GridData(GridData.FILL_HORIZONTAL);
		firtstLineContainerData.minimumWidth = 300;
		firstLineContainer.setLayoutData(firtstLineContainerData);
		
		GridData fontNameData = new GridData(GridData.FILL_HORIZONTAL);
		fontNameData.minimumWidth = 150;
		createWidget4Property(firstLineContainer, JRBaseStyle.PROPERTY_FONT_NAME, false).getControl().setLayoutData(fontNameData);
		
		GridData fontSizeData = new GridData();
		fontSizeData.minimumWidth = 60;
		fontSizeData.widthHint = 60;
		createWidget4Property(firstLineContainer, JRBaseStyle.PROPERTY_FONT_SIZE, false).getControl().setLayoutData(fontSizeData);

		createWidget4Property(firstLineContainer, MFont.FONT_INCREMENT, false);

		//END OF THE FIRS LINE
		Composite buttonContainer = new Composite(group, SWT.NONE);
		GridLayout buttonContainerLayout = new GridLayout(4, false);
		buttonContainerLayout.horizontalSpacing = 0;
		buttonContainerLayout.marginHeight = 0;
		buttonContainerLayout.marginWidth = 0;
		buttonContainerLayout.verticalSpacing = 0;
		buttonContainer.setLayout(buttonContainerLayout);
		buttonContainer.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createButton(buttonContainer, JRBaseStyle.PROPERTY_BOLD);
		createButton(buttonContainer, JRBaseStyle.PROPERTY_ITALIC);
		createButton(buttonContainer, JRBaseStyle.PROPERTY_UNDERLINE);
		createButton(buttonContainer, JRBaseStyle.PROPERTY_STRIKE_THROUGH);
	}
	
	/**
	 * Create every button in its own container, this help to handle the toolbar contextual
	 * menu, since the menu is on the toolbar, not on the item
	 */
	protected void createButton(Composite parent, String propertyId){
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT | SWT.WRAP | SWT.LEFT);
		GridData gd = new GridData();
		gd.horizontalAlignment = SWT.LEFT;
		toolBar.setLayoutData(gd);
		createWidget4Property(toolBar, propertyId, false);
	}

	protected Composite createFontSection(Composite parent) {
		Composite cmp = getWidgetFactory().createSection(parent, Messages.common_font, true, 1);
		section = (ExpandableComposite)cmp.getParent();
		return cmp;
	}
	
	@Override
	public void expandForProperty(Object propertyId) {
		if (section != null && !section.isExpanded()) section.setExpanded(true);
	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(JRBaseStyle.PROPERTY_FONT_NAME, Messages.common_font_name);
		addProvidedProperties(JRBaseStyle.PROPERTY_FONT_SIZE, Messages.common_font_size);
		addProvidedProperties(JRBaseStyle.PROPERTY_BOLD, Messages.common_bold);
		addProvidedProperties(JRBaseStyle.PROPERTY_UNDERLINE, Messages.common_underline);
		addProvidedProperties(JRBaseStyle.PROPERTY_STRIKE_THROUGH, Messages.common_strike_trough);
		addProvidedProperties(JRBaseStyle.PROPERTY_ITALIC, Messages.common_italic);
	}

}
