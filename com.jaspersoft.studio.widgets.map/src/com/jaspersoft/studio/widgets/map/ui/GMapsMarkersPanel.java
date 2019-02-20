/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.Util;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
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
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

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

	/**
	 * The markers string is formatted with at max 6 decimal digits and using the
	 * English locale, that will force the . separator. This is required because
	 * when passing the location to the javascript it expects double with the
	 * standard dot separator
	 */
	protected static DecimalFormat coordinatesFormatter = new DecimalFormat("#.######",
			new DecimalFormatSymbols(Locale.ENGLISH));

	protected List markersList;

	/**
	 * Creates a new panel containing the controls to work with a Google Maps
	 * component presented inside a browser instance.
	 * 
	 * @param parent
	 *            a composite control which will be the parent of the new instance
	 *            (cannot be null)
	 * @param style
	 *            the style of widget to construct
	 */
	public GMapsMarkersPanel(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void createContent(Composite parent, int style) {
		createTop(parent);
		if (Util.isLinux()) {
			Composite warningCmp = MapUIUtils.createLinuxWarningText(parent);
			warningCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		}
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
		UIUtils.getDisplay().asyncExec(() -> map.activateMapTile());

		sash.setWeights(new int[] { 4, 1 });
	}

	@Override
	protected void createMap(Composite parent) {
		map = new MapTile(parent, SWT.NONE, MapActivator.getFileLocation("mapfiles/gmaps_library/map2.html")); //$NON-NLS-1$
		map.configureJavaSupport(new DetailsPanelMapSupportMarker(map.getMapControl()));
		map.getFunctions().add(new AddNewMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new ClearMarkersList(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map.getJavaMapSupport()));
		map.getFunctions().add(new RemoveMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new UpdateMarkerPosition(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_UPDATE_MARKER_POSITION, map.getJavaMapSupport()));
		map.getFunctions().add(new GMapEnabledFunction(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_MARKER_DOUBLE_CLICK, map.getJavaMapSupport()) {
			@Override
			public Object function(Object[] arguments) {
				handleMarkerDoubleClick(((Double) arguments[0]).intValue());
				return null;
			}

		});
		map.getFunctions().add(new InitialConfigurationFunction(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION, map.getJavaMapSupport()));

		map.getMapControl().addListener(SWT.Deactivate,
				event -> map.getJavascriptMapSupport().evaluateJavascript("myMap.hideMenus();"));//$NON-NLS-1$
		map.getMapControl().addControlListener(new ControlListener() {

			private boolean first = true;

			@Override
			public void controlResized(ControlEvent e) {
				if (first)
					UIUtils.getDisplay().asyncExec(() -> centerMap(getMapCenter()));
				first = false;
			}

			@Override
			public void controlMoved(ControlEvent e) {
				// nothing to do here
			}

		});
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
			@Override
			public void menuShown(MenuEvent e) {
				int selected = markersList.getSelectionIndex();
				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				if (selected < 0 || selected >= markersList.getItemCount() || markersList.getItemCount() == 0)
					return;

				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText(Messages.GMapsMarkersPanel_2);
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
			MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.GMapsMarkersPanel_2, null,
					Messages.GMapsMarkersPanel_7, MessageDialog.QUESTION,
					new String[] { Messages.GMapsMarkersPanel_8, Messages.GMapsMarkersPanel_9 }, 1);
			if (dialog.open() == Dialog.OK) {
				super.removeMarker(markerIndex);
				if (initMarkers)
					return;
				handleRemoveMarker(markerIndex);
			} else
				refresh();
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
			super.updateMarkerPosition(markerIdx, newPosition);
			if (initMarkers)
				return;
			handleUpdateMarkerPosition(markerIdx, getMarkers().get(markerIdx));
		}

		@Override
		public void clearMarkers() {
			super.clearMarkers();
			if (initMarkers)
				return;
			handleClearMarkers();
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			super.addNewMarker(newMarker);
			if (initMarkers)
				return;
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
		handleRemoveMarker(new int[] { markerIndex });
	}

	protected void handleRemoveMarker(int[] mIndxs) {
		markersList.remove(mIndxs);
	}

	protected void handleClearMarkers() {
		markersList.removeAll();
	}

	protected void handleMarkerDoubleClick(int ind) {
		// nothing to do
	}

	public void addNewMarker(Marker m) {
		LatLng p = m.getPosition();
		if (p != null) {
			markersList.add(formatMarker(m));
			map.getJavascriptMapSupport().addNewMarker(m);
			map.getJavaMapSupport().addNewMarker(m);
		}
	}

	protected void handleNewMarker(Marker newMarker) {
		if (initMarkers)
			return;
		markersList.add(formatMarker(newMarker));
	}

	/**
	 * Format the marker in a format like lat : long where each value has at max 6
	 * decimal digits and the decimal separator is a dot. This assure the
	 * compatibility with the javascript, since for it the decimal separator must be
	 * a standard .
	 */
	protected String formatMarker(Marker m) {
		LatLng p = m.getPosition();
		StringBuilder builder = new StringBuilder();
		builder.append(coordinatesFormatter.format(p.getLat()));
		builder.append(" : ");
		builder.append(coordinatesFormatter.format(p.getLng()));
		return builder.toString();
	}

	public void clearMarkers() {
		map.getJavascriptMapSupport().clearMarkers();
		if (markersList != null)
			markersList.removeAll();
	}

	@Override
	protected void postInitMap() {
		map.getJavascriptMapSupport().evaluateJavascript("MENU_KIND=_MENU_COMPLETE"); //$NON-NLS-1$
	}

	protected void deleteMarker() {
		if (markersList.getSelectionCount() <= 0)
			return;
		MessageDialog dialog = new MessageDialog(UIUtils.getShell(), Messages.GMapsMarkersPanel_2, null,
				Messages.GMapsMarkersPanel_7, MessageDialog.QUESTION,
				new String[] { Messages.GMapsMarkersPanel_8, Messages.GMapsMarkersPanel_9 }, 1);
		if (dialog.open() == Dialog.OK)
			handleRemoveMarker(markersList.getSelectionIndices());
	}
}
