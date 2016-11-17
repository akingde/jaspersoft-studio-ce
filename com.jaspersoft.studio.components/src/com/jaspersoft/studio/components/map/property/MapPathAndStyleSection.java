/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.components.map.StandardMapComponent;

/**
 * This section should be used to customize the list of map paths and related
 * styles.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MapPathAndStyleSection extends AbstractSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));

		createWidget4Property(parent, StandardMapComponent.PROPERTY_PATH_DATA_LIST);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_PATH_STYLE_LIST);
	}

}
