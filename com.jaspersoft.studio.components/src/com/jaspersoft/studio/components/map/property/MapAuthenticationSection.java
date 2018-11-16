/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

import net.sf.jasperreports.components.map.MapComponent;

/**
 * Section with authentication information for Google Maps component.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MapAuthenticationSection extends AbstractSection {

	private ASPropertyWidget<?> keyWidget;
	private ASPropertyWidget<?> signatureWidget;
	private ASPropertyWidget<?> idWidget;
	private Button useBusinessAPICheck;
	private Composite cmpContainer;

	public void createControls(final Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		cmpContainer = getWidgetFactory().createComposite(parent);
		cmpContainer.setLayout(new GridLayout(2, false));
		GridData containerGD = new GridData(SWT.FILL, SWT.FILL, true, true);
		containerGD.minimumHeight = 150;
		cmpContainer.setLayoutData(containerGD);
		useBusinessAPICheck = getWidgetFactory().createButton(cmpContainer,
				Messages.MapAuthenticationSection_UseBusinessAPICheckbox, SWT.CHECK);
		useBusinessAPICheck.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1));

		keyWidget = createWidget4Property(cmpContainer, MapComponent.PROPERTY_KEY);
		idWidget = createWidget4Property(cmpContainer, MapComponent.PROPERTY_CLIENT_ID);
		signatureWidget = createWidget4Property(cmpContainer, MapComponent.PROPERTY_SIGNATURE);
		createWidget4Property(cmpContainer, MapComponent.PROPERTY_GOOGLE_VERSION);
		useBusinessAPICheck.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean useBusiness = useBusinessAPICheck.getSelection();
				useBusinessSelected(cmpContainer, useBusiness);
			}
		});
		parent.layout();
		boolean usingBusinessAPI = isUsingBusinessAPI();
		useBusinessAPICheck.setSelection(usingBusinessAPI);
		useBusinessSelected(cmpContainer, usingBusinessAPI);
	}

	private void useBusinessSelected(final Composite parent, boolean useBusiness) {
		keyWidget.toggleVisibility(!useBusiness);
		idWidget.toggleVisibility(useBusiness);
		signatureWidget.toggleVisibility(useBusiness);
		if (useBusiness) {
			((Text) keyWidget.getControl()).setText(""); //$NON-NLS-1$
			getElement().setPropertyValue(MapComponent.PROPERTY_KEY, null);
		} else {
			((Text) idWidget.getControl()).setText(""); //$NON-NLS-1$
			getElement().setPropertyValue(MapComponent.PROPERTY_CLIENT_ID, null);
			((Text) signatureWidget.getControl()).setText(""); //$NON-NLS-1$
			getElement().setPropertyValue(MapComponent.PROPERTY_SIGNATURE, null);
		}
		parent.update();
		parent.layout();
	}

	private boolean isUsingBusinessAPI() {
		Object clientID = getElement().getPropertyValue(MapComponent.PROPERTY_CLIENT_ID);
		return (clientID instanceof String && !((String) clientID).isEmpty());
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(MapComponent.PROPERTY_KEY, Messages.MapAuthenticationSection_ApiKeyText);
		addProvidedProperties(MapComponent.PROPERTY_CLIENT_ID, Messages.MapAuthenticationSection_ClientIdText);
		addProvidedProperties(MapComponent.PROPERTY_SIGNATURE, Messages.MapAuthenticationSection_SignatureText);
		addProvidedProperties(MapComponent.PROPERTY_GOOGLE_VERSION, Messages.MapAuthenticationSection_VersionText);
	}

}
