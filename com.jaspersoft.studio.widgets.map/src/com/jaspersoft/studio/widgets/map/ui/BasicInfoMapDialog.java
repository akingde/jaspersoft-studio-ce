/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.widgets.map.MapActivator;
import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * This dialog allows to configure the basic details of a map: its center, the
 * zoom level and the type.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BasicInfoMapDialog extends PersistentLocationDialog {

	// basic map details
	protected LatLng mapCenter;
	protected int zoomLevel;
	protected MapType mapType;
	protected MapTile map;
	protected String address;
	protected Text addressBar;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public BasicInfoMapDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title == null ? "" : title);
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		createContent(container);

		return container;
	}

	protected void createContent(Composite parent) {
		createMap(parent);
	}

	protected void createMap(Composite container) {
		Composite mainCmp = new Composite(container, SWT.NONE);
		GridLayout cmpGL = new GridLayout(3, false);
		cmpGL.verticalSpacing = 10;
		mainCmp.setLayout(cmpGL);
		mainCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label addressLbl = new Label(mainCmp, SWT.NONE);
		addressLbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false));
		addressLbl.setText(Messages.BasicInfoMapDialog_AddressLookup);

		addressBar = new Text(mainCmp, SWT.BORDER);
		addressBar
				.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		addressBar.addTraverseListener(new TraverseListener() {
			@Override
			public void keyTraversed(TraverseEvent e) {
				if (e.detail == SWT.TRAVERSE_RETURN) {
					e.doit = false;
					e.detail = SWT.TRAVERSE_NONE;
					locateAddress(addressBar.getText());
				}
			}
		});
		addressBar.setText(address == null ? "" : address);

		CLabel findBtn = new CLabel(mainCmp, SWT.NONE);
		findBtn.setImage(MapActivator.getDefault().getImageRegistry()
				.get(MapActivator.FIND_BTN_IMG));
		findBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				locateAddress(addressBar.getText());
			}
		});
		findBtn.setCursor(UIUtils.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
		
		if(Util.isLinux()){
			Composite warningCmp = MapUIUtils.createLinuxWarningText(mainCmp);
			warningCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false,3,1));
		}

		map = new MapTile(mainCmp, SWT.BORDER);
		map.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		configureMapTile();

		map.activateMapTile();
	}

	protected void configureMapTile() {
		map.configureJavaSupport(new BaseJavaMapSupport(map.getMapControl()) {
			@Override
			public void setZoomLevel(int newZoomLevel) {
				BasicInfoMapDialog.this.setZoomLevel(newZoomLevel);
			}

			@Override
			public void setMapCenter(LatLng position) {
				BasicInfoMapDialog.this.setMapCenter(position);
			}

			@Override
			public void setMapType(MapType mapType) {
				BasicInfoMapDialog.this.setMapType(mapType);
			}
		});
		map.getFunctions()
				.add(new InitialConfigurationFunction(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION,
						map.getJavaMapSupport()));
	}

	class InitialConfigurationFunction extends GMapEnabledFunction {

		public InitialConfigurationFunction(Browser browser, String name,
				JavaMapSupport mapSupport) {
			super(browser, name, mapSupport);
		}

		@Override
		public Object function(Object[] arguments) {
			initConfigurationFunction(arguments);
			map.getJavascriptMapSupport().setZoomLevel(getZoomLevel());
			map.getJavascriptMapSupport().setMapType(
					mapType != null ? mapType : MapType.ROADMAP);
			if ((mapCenter == null || mapCenter.getLat() == null || mapCenter
					.getLat() == null)) {
				if (address != null && !address.isEmpty()) {
					LatLng coords = GMapUtils.getAddressCoordinates(address);
					if (coords != null)
						centerMap(coords);
				}
			} else
				centerMap(mapCenter);
			return null;
		}

	}

	protected Object initConfigurationFunction(Object[] arguments) {
		return null;
	}

	private void locateAddress(String addressText) {
		LatLng coords = GMapUtils.getAddressCoordinates(addressText);
		if (coords != null) {
			centerMap(coords);
			address = addressText;
		} else {
			MessageDialog.openError(UIUtils.getShell(),
					Messages.BasicInfoMapDialog_LocationErrorTitle,
					Messages.BasicInfoMapDialog_LocationErrorMsg);
		}
	}

	private void centerMap(LatLng coords) {
		setMapCenter(coords);
		map.getJavascriptMapSupport().evaluateJavascript(
				"myMap.panTo(new google.maps.LatLng(" + coords.getLat() + ","
						+ coords.getLng() + "));");
		addCenterMarker(coords);
	}

	protected void addCenterMarker(LatLng coords) {
		map.getJavascriptMapSupport().clearMarkers();
		map.getJavascriptMapSupport().addNewMarker(new Marker(coords));
		map.getJavascriptMapSupport().evaluateJavascript(
				"myMap.mapMarkers[0].setDraggable(false);");
	}

	/**
	 * Create contents of the button bar.
	 * 
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(800, 600);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		if (addressBar != null)
			addressBar.setText(address);
	}

	public LatLng getMapCenter() {
		return mapCenter;
	}

	public void setMapCenter(LatLng mapCenter) {
		this.mapCenter = mapCenter;
		updateTitle();
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
		updateTitle();
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
		updateTitle();
	}

	private String title;

	private void updateTitle() {
		if (mapCenter != null && zoomLevel != 0 && mapType != null) {
			title = NLS.bind(Messages.BasicInfoMapDialog_Title, new Object[] {
					String.format("%.6f", mapCenter.getLat()), //$NON-NLS-1$
					String.format("%.6f", mapCenter.getLng()), //$NON-NLS-1$
					String.valueOf(zoomLevel), this.mapType });
			Shell shell = getShell();
			if (shell != null)
				shell.setText(title);
		}
	}
}
