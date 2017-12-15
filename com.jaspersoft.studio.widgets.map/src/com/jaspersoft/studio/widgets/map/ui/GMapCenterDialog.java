/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class GMapCenterDialog extends PersistentLocationDialog {

	private GMapsCenterPanel mapPanel;

	protected GMapCenterDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Map");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		try {
			mapPanel = new GMapsCenterPanel(container, SWT.NONE) {

				@Override
				public void setMapCenter(LatLng mapCenter) {
					super.setMapCenter(mapCenter);
					GMapCenterDialog.this.mapCenter = mapCenter;
				}

				@Override
				public void setZoomLevel(int zoomLevel) {
					super.setZoomLevel(zoomLevel);
					GMapCenterDialog.this.zoomLevel = zoomLevel;
				}

				@Override
				public void setMapType(MapType mapType) {
					super.setMapType(mapType);
					GMapCenterDialog.this.mapType = mapType;
				}

				@Override
				public void setAddress(String address) {
					super.setAddress(address);
					GMapCenterDialog.this.address = address;
				}

				@Override
				protected void handleMapCenterChanged(LatLng position) {
					super.handleMapCenterChanged(position);
					setMapCenter(position);
				}

				@Override
				protected void handleMapZoomChanged(int newZoomLevel) {
					setZoomLevel(newZoomLevel);
				}

				@Override
				protected void handleAddressChanged(String address) {
					setAddress(address);
				}

				@Override
				protected void handleMapTypeChanged(MapType mapType) {
					setMapType(mapType);
				}
			};
			mapPanel.setAddress(address);
			mapPanel.setMapCenter(mapCenter);
			mapPanel.setMapType(mapType);
			mapPanel.setZoomLevel(zoomLevel);
		} catch (Throwable e) {
			UIUtils.showError(e);
		}
		return container;
	}

	public GMapsCenterPanel getMapPanel() {
		return mapPanel;
	}

	protected LatLng mapCenter = new LatLng(45.439722, 12.331944);
	protected int zoomLevel = 12;
	protected MapType mapType = MapType.ROADMAP;
	protected String address;

	public void setMapCenter(LatLng mapCenter) {
		this.mapCenter = mapCenter;
	}

	public LatLng getMapCenter() {
		return mapCenter;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

}
