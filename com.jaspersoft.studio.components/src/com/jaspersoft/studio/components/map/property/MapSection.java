/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPEvaluationTime;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.ui.GMapCenterDialog;

import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.BasicMapInfoData;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class MapSection extends AbstractSection {

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);
		parent.setLayout(new GridLayout(2, false));

		createWidget4Property(parent, StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_ZOOM_EXPRESSION);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_MAP_TYPE);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_MAP_SCALE);
		IPropertyDescriptor pd = getPropertyDesriptor(StandardMapComponent.PROPERTY_EVALUATION_TIME);
		IPropertyDescriptor gpd = getPropertyDesriptor(StandardMapComponent.PROPERTY_EVALUATION_GROUP);
		getWidgetFactory().createCLabel(parent, pd.getDisplayName());
		widgets.put(pd.getId(), new SPEvaluationTime(parent, this, pd, gpd));
		createWidget4Property(parent, StandardMapComponent.PROPERTY_IMAGE_TYPE);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_ON_ERROR_TYPE);
		
		createEditButton(parent);
	}
	
	/**
	 * Create the button to open the map preview area
	 * 
	 * @param parent the parent where the button is placed, must have a grid layout
	 * with two columns
	 */
	protected void createEditButton(Composite parent){
		Button mapPickSuggestion = new Button(parent, SWT.PUSH | SWT.FLAT);
		mapPickSuggestion.setText(Messages.MapSection_buttonText);
		mapPickSuggestion.setToolTipText(Messages.MapSection_buttonTooltip);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.horizontalAlignment = SWT.CENTER;
		mapPickSuggestion.setLayoutData(gd);
		mapPickSuggestion.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				MMap mmap = (MMap) getElement();
				GMapCenterDialog d = new GMapCenterDialog(UIUtils.getShell()) {
					@Override
					protected void configureShell(Shell newShell) {
						super.configureShell(newShell);
						UIUtils.resizeAndCenterShell(newShell, 800, 600);
					}
				};
				BasicMapInfoData mapInfo = mmap.getBasicMapInformation();
				if (mapInfo.getLatitude() != null && mapInfo.getLongitude() != null)
					d.setMapCenter(new LatLng(mapInfo.getLatitude(), mapInfo.getLongitude(), true));
				if (mapInfo.getAddress() != null)
					d.setAddress(mapInfo.getAddress());
				if (mapInfo.getMapType() != null)
					d.setMapType(MapType.fromStringID(mapInfo.getMapType().getName()));
				if (mapInfo.getZoom() != 0)
					d.setZoomLevel(mapInfo.getZoom());
				if (d.open() == Dialog.OK) {
					LatLng center = d.getMapPanel().getMapCenter();
					changeProperty(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
							new JRDesignExpression(center.getLat() + "f"));
					changeProperty(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
							new JRDesignExpression(center.getLng() + "f"));
					changeProperty(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
							new JRDesignExpression(String.valueOf(d.getMapPanel().getZoomLevel())));
					String adr = d.getMapPanel().getAddress();
					if (Misc.isNullOrEmpty(adr))
						changeProperty(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, null);
					else
						changeProperty(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION,
								new JRDesignExpression("\"" + adr + "\""));
					changeProperty(StandardMapComponent.PROPERTY_MAP_TYPE, d.getMapPanel().getMapType().ordinal());
				}
			}
		});
	}

	@Override
	protected void initializeProvidedProperties() {
		super.initializeProvidedProperties();
		addProvidedProperties(StandardMapComponent.PROPERTY_EVALUATION_TIME, Messages.MMap_evaluation_time);
		addProvidedProperties(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, Messages.MMap_latitude);
		addProvidedProperties(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, Messages.MMap_longitude);
		addProvidedProperties(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, Messages.MMap_0);
		addProvidedProperties(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, Messages.MMap_zoom);
		addProvidedProperties(StandardMapComponent.PROPERTY_LANGUAGE_EXPRESSION, Messages.MMap_languageExpressionTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_MAP_TYPE, Messages.MMap_mapTypeTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_MAP_SCALE, Messages.MMap_mapScaleTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_IMAGE_TYPE, Messages.MMap_imageTypeTitle);
		addProvidedProperties(StandardMapComponent.PROPERTY_ON_ERROR_TYPE, Messages.MMap_OnErrorType);
	}

}
