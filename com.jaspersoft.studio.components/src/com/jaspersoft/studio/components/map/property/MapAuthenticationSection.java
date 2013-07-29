/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import net.sf.jasperreports.components.map.MapComponent;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Section with authentication information for Google Maps component.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapAuthenticationSection extends AbstractSection {

	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(2, false));
		createWidget4Property(parent, MapComponent.PROPERTY_KEY);
		createWidget4Property(parent, MapComponent.PROPERTY_CLIENT_ID);
		createWidget4Property(parent, MapComponent.PROPERTY_SIGNATURE);
		createWidget4Property(parent, MapComponent.PROPERTY_VERSION);
	}
	
	
	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MapComponent.PROPERTY_KEY, Messages.MapAuthenticationSection_ApiKeyText);
		addProvidedProperties(MapComponent.PROPERTY_CLIENT_ID, Messages.MapAuthenticationSection_ClientIdText);
		addProvidedProperties(MapComponent.PROPERTY_SIGNATURE, Messages.MapAuthenticationSection_SignatureText);
		addProvidedProperties(MapComponent.PROPERTY_VERSION, Messages.MapAuthenticationSection_VersionText);
	}
	
}
