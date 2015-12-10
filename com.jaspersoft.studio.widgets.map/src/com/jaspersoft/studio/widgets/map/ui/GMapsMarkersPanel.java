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
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.widgets.map.MapActivator;
import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.AddNewMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.ClearMarkersList;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;

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
public class GMapsMarkersPanel extends GMapsCenterPanel {

	protected List markersList;

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
	public GMapsMarkersPanel(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void createContent(Composite parent, int style) {
		createTop(parent);

		SashForm sash = new SashForm(parent, style | SWT.HORIZONTAL) {
			@Override
			public void setBounds(int x, int y, int width, int height) {
				initMarkers = true;
				super.setBounds(x, y, width, height);
				initMarkers = false;
			}
		};
		if (parent.getLayout() instanceof GridLayout)
			sash.setLayoutData(new GridData(GridData.FILL_BOTH));

		createMap(sash);

		Composite containerCmp = new Composite(sash, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		containerCmp.setLayout(layout);

		createRightPanel(containerCmp);
		UIUtils.getDisplay().asyncExec(new Runnable() {
			public void run() {
				map.activateMapTile();
			}
		});

		sash.setWeights(new int[] { 4, 1 });
	}

	@Override
	protected void createMap(Composite parent) {
		map = new MapTile(parent, SWT.NONE, MapActivator.getFileLocation("mapfiles/gmaps_library/map2.html"));
		map.configureJavaSupport(new DetailsPanelMapSupportMarker(map.getMapControl()));
		map.getFunctions().add(new AddNewMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new ClearMarkersList(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map.getJavaMapSupport()));
		map.getFunctions().add(new RemoveMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new UpdateMarkerPosition(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUCTION_UPDATE_MARKER_POSITION, map.getJavaMapSupport()));
		map.getFunctions().add(new InitialConfigurationFunction(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION, map.getJavaMapSupport()));
	}

	protected void createRightPanel(Composite containerCmp) {
		createMarkersLabel(containerCmp);

		markersList = new List(containerCmp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
		markersList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		markersList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int markerIdx = markersList.getSelectionIndex();
				map.getJavascriptMapSupport().highlightMarker(markerIdx);
			}
		});
		markersList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS)
					deleteMarker();
			}
		});
		markersList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (markersList.getSelectionCount() <= 0)
					return;
				handleMarkerDoubleClick(markersList.getSelectionIndex());
			}
		});
		final Menu menu = new Menu(markersList);
		markersList.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				int selected = markersList.getSelectionIndex();

				if (selected < 0 || selected >= markersList.getItemCount())
					return;

				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Delete");
				newItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						deleteMarker();
					}
				});
			}
		});
	}

	protected void createMarkersLabel(Composite containerCmp) {
		Label markersLbl = new Label(containerCmp, SWT.NONE);
		markersLbl.setText(Messages.GMapsDetailsPanel_MarkersLbl);
		markersLbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
	}

	class DetailsPanelMapSupportMarker extends DetailsPanelMapSupport {

		DetailsPanelMapSupportMarker(Browser browser) {
			super(browser);
		}

		@Override
		public void removeMarker(int markerIndex) {
			if (initMarkers)
				return;
			super.removeMarker(markerIndex);
			handleRemoveMarker(markerIndex);
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			if (initMarkers)
				return;
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				removeMarker(mIdx);
			} else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}

		@Override
		public void highlightMarker(int markerIdx) {
			if (initMarkers)
				return;
			super.highlightMarker(markerIdx);
			handleHighlightMarker(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			if (initMarkers)
				return;
			super.updateMarkerPosition(markerIdx, newPosition);
			handleUpdateMarkerPosition(markerIdx, getMarkers().get(markerIdx));
		}

		@Override
		public void clearMarkers() {
			if (initMarkers)
				return;
			super.clearMarkers();
			handleClearMarkers();
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			if (initMarkers)
				return;
			super.addNewMarker(newMarker);
			handleNewMarker(newMarker);
		}

	}

	protected void handleHighlightMarker(int markerIdx) {
		markersList.setSelection(markerIdx);
	}

	protected void handleUpdateMarkerPosition(int markerIdx, Marker m) {
		markersList.setItem(markerIdx, formatMarker(m));
	}

	protected void handleRemoveMarker(int markerIndex) {
		markersList.remove(markerIndex);
	}

	protected void handleRemoveMarker(int[] mIndxs) {

	}

	protected void handleClearMarkers() {
		markersList.removeAll();
	}

	protected void handleNewMarker(Marker newMarker) {
		if (initMarkers)
			return;
		markersList.add(formatMarker(newMarker));
	}

	protected void handleMarkerDoubleClick(int ind) {

	}

	public void addNewMarker(Marker m) {
		LatLng p = m.getPosition();
		if (p != null) {
			markersList.add(p.getLat() + " : " + p.getLng());
			map.getJavascriptMapSupport().addNewMarker(m);
		}
	}

	public void clearMarkers() {
		map.getJavascriptMapSupport().clearMarkers();
		markersList.removeAll();
	}

	protected String formatMarker(Marker m) {
		LatLng p = m.getPosition();
		return String.format("%.7f : %.7f", p.getLat(), p.getLng());
	}

	protected void postInitMap() {
		map.getJavascriptMapSupport().evaluateJavascript("MENU_KIND=_MENU_COMPLETE");
	}

	protected void deleteMarker() {
		if (markersList.getSelectionCount() <= 0)
			return;
		MessageDialog dialog = new MessageDialog(UIUtils.getShell(), "Delete", null,
				"Are you sure you want to delete selected items?", MessageDialog.QUESTION, new String[] { "Yes", "No" },
				1);
		if (dialog.open() == Dialog.OK)
			handleRemoveMarker(markersList.getSelectionIndices());
	}
}
