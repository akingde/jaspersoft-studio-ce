/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.widgets.map.core.Marker;

/**
 * This class implements the support for the Google Map component. The panel
 * that is shown contains:
 * <ul>
 * <li>a browser with the Google Maps component loaded</li>
 * <li>an additional panel with controls that allows the interaction with the
 * Google Maps in the browser</li>
 * </ul>
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class GMapsPathsPanel extends GMapsMarkersPanel {

	protected Combo cPaths;

	/**
	 * Creates a new panel containing the controls to work with a Google Maps
	 * component presented inside a browser instance.
	 * 
	 * @param parent
	 *            a composite control which will be the parent of the new
	 *            instance (cannot be null)
	 * @param style
	 *            the style of widget to construct
	 */
	public GMapsPathsPanel(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void createMarkersLabel(Composite containerCmp) {
	}

	protected void createRightPanel(Composite containerCmp) {
		Label lbl = new Label(containerCmp, SWT.NONE);
		lbl.setText("Paths");

		cPaths = new Combo(containerCmp, SWT.NONE);
		cPaths.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		cPaths.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (initMarkers)
					return;
				handlePathChanged();
			}
		});
		cPaths.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (initMarkers)
					return;
				handlePathChanged();
			}
		});

		super.createRightPanel(containerCmp);
	}

	protected void drawPolyline() {
		String snippet = "var pathCoordinates = [\n";

		int cnt = markersList.getItemCount();
		for (int i = 0; i < cnt; i++) {
			snippet += "{lat:" + markersList.getItem(i).replaceAll(" : ", ", lng:") + "}";
			if (i < cnt - 1)
				snippet += ",";
			snippet += "\n";
		}
		snippet += "];\n";
		snippet += "mypath.setMap(null);\n";
		snippet += "mypath = new google.maps.Polyline({\n";
		snippet += "path: pathCoordinates,\n";
		snippet += "geodesic: true,\n";
		snippet += "strokeColor: '#FF0000',\n";
		snippet += "strokeOpacity: 1.0,\n";
		snippet += "strokeWeight: 2\n";
		snippet += " });\n";
		snippet += "mypath.setMap(myMap.map);\n";

		// System.out.println(snippet);
		map.getJavascriptMapSupport().evaluateJavascript(snippet);
	}

	protected void handlePathChanged() {

	}

	protected void fillPaths() {

	}

	@Override
	protected void handleUpdateMarkerPosition(int markerIdx, Marker m) {
		super.handleUpdateMarkerPosition(markerIdx, m);
		drawPolyline();
	}

	@Override
	protected void handleRemoveMarker(int[] markerIndex) {
		super.handleRemoveMarker(markerIndex);
		drawPolyline();
		fillPaths();
	}

	@Override
	protected void handleNewMarker(Marker newMarker) {
		super.handleNewMarker(newMarker);
		drawPolyline();
		fillPaths();
	}

	public void initMarkers() {

	}
}
