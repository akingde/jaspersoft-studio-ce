/*******************************************************************************
 * Copyright (c) 2014 Massimo Rabbi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
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
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;

/**
 * This dialog allows to configure the basic details of a map: its center, the
 * zoom level and the type.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BasicInfoMapDialog extends Dialog {
	
	// basic map details
	private LatLng mapCenter;
	private int zoomLevel;
	private MapType mapType;
	private MapTile mapTile;
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public BasicInfoMapDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		Composite mainCmp = new Composite(container, SWT.NONE);
		GridLayout cmpGL = new GridLayout(3,false);
		cmpGL.verticalSpacing = 10;
		mainCmp.setLayout(cmpGL);
		mainCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		Label addressLbl = new Label(mainCmp,SWT.NONE);
		addressLbl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false));
		addressLbl.setText(Messages.BasicInfoMapDialog_AddressLookup);
		
		final Text addressBar = new Text(mainCmp, SWT.BORDER);
		addressBar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
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
		
		CLabel findBtn = new CLabel(mainCmp, SWT.NONE);
		findBtn.setImage(MapActivator.getDefault().getImageRegistry().get(MapActivator.FIND_BTN_IMG));
		findBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				locateAddress(addressBar.getText());
			}
		});
		findBtn.setCursor(UIUtils.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
		
		mapTile = new MapTile(mainCmp, SWT.BORDER);
		mapTile.configureJavaSupport(new BaseJavaMapSupport(mapTile.getMapControl()){
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
		mapTile.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,3,1));
		mapTile.activateMapTile();
	    
		return container;
	}
	
	private void locateAddress(String addressText) {
		LatLng coords = GMapUtils.getAddressCoordinates(addressText);
		if(coords!=null) {
			mapTile.getJavascriptMapSupport().evaluateJavascript("myMap.panTo(new google.maps.LatLng("+coords.getLat()+","+coords.getLng()+"));");
			mapTile.getJavascriptMapSupport().clearMarkers();
			mapTile.getJavascriptMapSupport().addNewMarker(new Marker(coords));
			mapTile.getJavascriptMapSupport().evaluateJavascript("myMap.mapMarkers[0].setDraggable(false);");
		}
		else {
			MessageDialog.openError(UIUtils.getShell(), 
					Messages.BasicInfoMapDialog_LocationErrorTitle, Messages.BasicInfoMapDialog_LocationErrorMsg);
		}
	}

	/**
	 * Create contents of the button bar.	
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

	private void updateTitle(){
		if(mapCenter!=null && zoomLevel!=0 && mapType!=null) {
			getShell().setText(
					NLS.bind(Messages.BasicInfoMapDialog_Title,
							new Object[]{
							String.format("%.6f", mapCenter.getLat()), //$NON-NLS-1$
							String.format("%.6f", mapCenter.getLng()), //$NON-NLS-1$
							String.valueOf(zoomLevel),
							this.mapType}));
		}
	}
}
