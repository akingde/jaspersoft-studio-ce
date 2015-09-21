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

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.AddNewMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.ClearMarkersList;
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * This dialog allows to place a list of markers in the map.
 * Markers can be moved/removed and their position is update accordingly.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MarkersPickupDialog extends Dialog {
	
	private java.util.List<LatLng> markers;
	private List markersWidget;
	private MapTile map;
	private LatLng initialPosition;
	private int zoom;
	private MapType type;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MarkersPickupDialog(Shell parentShell) {
		super(parentShell);
		this.markers=new ArrayList<LatLng>();
	}
	
	/**
	 * Sets the initial position of the map that will be used to pick-up a list
	 * of markers.
	 * 
	 * @param initialPosition
	 *            initial map center
	 */
	public void setInitialPosition(LatLng initialPosition) {
		this.initialPosition = initialPosition;
	}

	/**
	 * Sets the initial zoom level of the map that will be used to pick-up a
	 * list of markers.
	 * 
	 * @param zoom
	 *            initial zoom level
	 */
	public void setZoom(int zoom) {
		this.zoom = zoom;
	}

	/**
	 * Sets the initial type of the map that will be used to pick-up a list of
	 * markers.
	 * 
	 * @param type
	 *            the initial map type
	 */
	public void setType(MapType type) {
		this.type = type;
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		
		SashForm sash = new SashForm(container, SWT.HORIZONTAL);
		sash.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		sash.setSashWidth(10);
		
		map = new MapTile(sash, SWT.BORDER);
		map.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		map.configureJavaSupport(new PanelJavaMapSupport(map.getMapControl()));
		map.getFunctions().add(
				new AddNewMarker(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER, map
								.getJavaMapSupport()));
		map.getFunctions().add(
				new ClearMarkersList(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map
								.getJavaMapSupport()));
		map.getFunctions().add(
				new RemoveMarker(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER, map
								.getJavaMapSupport()));
		map.getFunctions()
				.add(new UpdateMarkerPosition(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUCTION_UPDATE_MARKER_POSITION,
						map.getJavaMapSupport()));
		map.getFunctions()
				.add(new InitialConfigurationFunction(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION,
						map.getJavaMapSupport()));

		Composite panelCmp = new Composite(sash, SWT.NONE);
		GridLayout panelCmpGL = new GridLayout(1,true);
		panelCmpGL.marginWidth=0;
		panelCmpGL.marginHeight=0;
		panelCmp.setLayout(panelCmpGL);
		panelCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    
		Label markersLbl = new Label(panelCmp,SWT.NONE);
	    markersLbl.setText(Messages.MarkersPickupDialog_MarkersLbl);
	    markersLbl.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, false));
		
	    markersWidget = new List(panelCmp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
	    markersWidget.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
	    Button delMarkersBtn = new Button(panelCmp, SWT.PUSH);
	    delMarkersBtn.setLayoutData(new GridData(SWT.RIGHT,SWT.BOTTOM,false,false));
	    delMarkersBtn.setText(Messages.MarkersPickupDialog_DeleteMarkersBtn);
	    delMarkersBtn.addSelectionListener(new SelectionAdapter() {
	        public void widgetSelected(SelectionEvent e) {
	        	map.getJavascriptMapSupport().clearMarkers();
	        }
	    });
	    markersWidget.addSelectionListener(new SelectionAdapter() {
	    	@Override
	    	public void widgetSelected(SelectionEvent e) {
	    		int markerIdx = markersWidget.getSelectionIndex();
	    		map.getJavascriptMapSupport().highlightMarker(markerIdx);
	    	}
		});
	    markersWidget.addKeyListener(new KeyAdapter() {
	    	@Override
	    	public void keyPressed(KeyEvent e) {
	    		if(e.keyCode == SWT.DEL) {
	    			int markerIdx = markersWidget.getSelectionIndex();
					RemoveMarker.removeMarker(markerIdx, map.getJavaMapSupport());
					map.getJavascriptMapSupport().evaluateJavascript("JAVA_TO_JAVASCRIPT_CALLED=true");
					map.getJavascriptMapSupport().removeMarker(markerIdx);
	    		}
	    	}
		});

	    map.activateMapTile();
 	    
		sash.setWeights(new int[] {75,25});

		return container;
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
	};

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(Messages.MarkersPickupDialog_Title);
	}
	
	/**
	 * Returns the list of markers added on the map.
	 * It's a list of coordinates.
	 * 
	 * @return list of markers
	 */
	public java.util.List<LatLng> getMarkersList() {
		return markers;
	}
	
	/**
	 * Browser function for correctly configuring the initial settings of the
	 * map.
	 * 
	 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
	 * 
	 */
	class InitialConfigurationFunction extends GMapEnabledFunction {

		public InitialConfigurationFunction(Browser browser, String name,
				JavaMapSupport mapSupport) {
			super(browser, name, mapSupport);
		}
		
		@Override
		public Object function(Object[] arguments) {
			map.getJavascriptMapSupport().evaluateJavascript("MENU_KIND=_MENU_COMPLETE");
			if(initialPosition!=null){
				map.getJavascriptMapSupport().setMapCenter(initialPosition);
			}
			if(type!=null){
				map.getJavascriptMapSupport().setMapType(type);
			}
			if(zoom!=0) {
				map.getJavascriptMapSupport().setZoomLevel(zoom);
			}
			
			return null;
		}
		
	}
	
	/**
	 * Custom Java map support. We need to update properly the dialog UI 
	 * and internal components.
	 * 
	 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
	 *
	 */
	class PanelJavaMapSupport extends BaseJavaMapSupport{

		PanelJavaMapSupport(Browser browser) {
			super(browser);
		}
		
		@Override
		public void removeMarker(int markerIndex) {
			super.removeMarker(markerIndex);
			markersWidget.remove(markerIndex);
			markers.remove(markerIndex);
		}
		
		@Override
		public void highlightMarker(int markerIdx) {
			super.highlightMarker(markerIdx);
			markersWidget.setSelection(markerIdx);
		}
		
		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			super.updateMarkerPosition(markerIdx, newPosition);
			markersWidget.setItem(markerIdx, 
					String.format("%.6f",newPosition.getLat()) + " : " + String.format("%.6f",newPosition.getLng()));
		}
		
		@Override
		public void clearMarkers() {
			super.clearMarkers();
			markersWidget.removeAll();
			markers.clear();
		}
		
		@Override
		public void removeMarker(Marker oldMarker) {
			int mIdx = getMarkers().indexOf(oldMarker);
			if(mIdx>0){
				getMarkers().remove(mIdx);
				markersWidget.remove(mIdx);
				markers.remove(mIdx);
			}
			else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}
		
		@Override
		public void addNewMarker(Marker newMarker) {
			super.addNewMarker(newMarker);
			LatLng position = newMarker.getPosition();
			markersWidget.add(
					String.format("%.6f",position.getLat()) + " : " + String.format("%.6f",position.getLng()));
			markers.add(position);
		}
		
	}
}
