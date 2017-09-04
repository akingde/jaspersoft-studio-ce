/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import com.jaspersoft.studio.widgets.map.MapActivator;
import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.browserfunctions.TestJavaCallSupport;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMapCenter;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMapType;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateZoomLevel;
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJSMapSupport;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.JSMapSupport;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * A tile containing the map widget. The browser can be further customized via
 * protected methods.
 * <p>
 * 
 * This implementation provides basic support for Java/Javascript communication.
 * The browser functions used in here are related to the basic operations like:
 * map type, zoom level and center update.
 * <p>
 * 
 * This is the correct order of invocations:
 * <ol>
 * <li>constructor (object creation);</li>
 * <li>{@link #configureJavaSupport(BaseJavaMapSupport)} (not mandatory);</li>
 * <li>{@link #configureJavascriptSupport(BaseJSMapSupport)} (not mandatory);
 * </li>
 * <li>{@link #configureFunctions(List)} (not mandatory);</li>
 * <li>{@link #activateMapTile()}.</li>
 * </ol>
 * For every non-mandatory method there is a default fallback that is checked in
 * the {@link #activateMapTile()}.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MapTile {

	private List<GMapEnabledFunction> functions;
	private JSMapSupport jsMapSupp;
	private JavaMapSupport javaMapSupp;
	protected Browser mapControl;
	private String mapURL;

	public MapTile(Composite parent, int style) {
		this(parent, style, MapActivator.getFileLocation("mapfiles/gmaps_library/map.html")); //$NON-NLS-1$
	}

	public MapTile(Composite parent, int style, String mapURL) {
		createBrowser(parent, style);
		addListeners();
		this.mapURL = mapURL;
	}

	protected void createBrowser(Composite parent, int style) {
		mapControl = new Browser(parent, style | SWT.BORDER);
		mapControl.addListener(SWT.MenuDetect, new Listener() {
			public void handleEvent(Event event) {
				event.doit = false;
			}
		});
	}

	/**
	 * Add custom listeners to the browser widget containing the map.
	 */
	protected void addListeners() {
	}

	public void configureJavaSupport(BaseJavaMapSupport javaSupport) {
		if (javaMapSupp == null) {
			javaMapSupp = javaSupport;
		} else {
			throw new RuntimeException(Messages.MapTile_JavaSupportAlreadyDefinedError);
		}

	}

	public void configureJavascriptSupport(BaseJSMapSupport jsSupport) {
		if (jsMapSupp == null) {
			jsMapSupp = jsSupport;
		} else {
			throw new RuntimeException(Messages.MapTile_JavascriptSupportAlreadyDefined);
		}
	}

	public void configureFunctions(List<GMapEnabledFunction> functs) {
		functions = new ArrayList<GMapEnabledFunction>(functs);
	}

	public void setLayoutData(Object layoutData) {
		mapControl.setLayoutData(layoutData);
	}

	public JSMapSupport getJavascriptMapSupport() {
		if (this.jsMapSupp == null) {
			this.jsMapSupp = new BaseJSMapSupport(mapControl);
		}
		return this.jsMapSupp;
	}

	public boolean hasJavaMapSupport() {
		return javaMapSupp != null;
	}

	public JavaMapSupport getJavaMapSupport() {
		if (this.javaMapSupp == null) {
			this.javaMapSupp = new BaseJavaMapSupport(mapControl);
		}
		return this.javaMapSupp;
	}

	public List<GMapEnabledFunction> getFunctions() {
		if (this.functions == null) {
			functions = new ArrayList<GMapEnabledFunction>(4);
			functions.add(new TestJavaCallSupport(mapControl, MapWidgetConstants.BROWSER_FUNCTION_TEST_JAVACALL_SUPPORT,
					getJavaMapSupport()));
			functions.add(new UpdateZoomLevel(mapControl, MapWidgetConstants.BROWSER_FUNCTION_UPDATE_ZOOM_LEVEL,
					getJavaMapSupport()));
			functions.add(new UpdateMapCenter(mapControl, MapWidgetConstants.BROWSER_FUNCTION_UPDATE_MAP_CENTER,
					getJavaMapSupport()));
			functions.add(new UpdateMapType(mapControl, MapWidgetConstants.BROWSER_FUNCTION_UPDATE_MAP_TYPE,
					getJavaMapSupport()));
		}
		return this.functions;
	}

	public Browser getMapControl() {
		return this.mapControl;
	}

	public void activateMapTile() {
		// Safe check for default initialization
		getJavaMapSupport();
		getJavascriptMapSupport();
		getFunctions();
		// Sets the URL on the browser
		mapControl.setUrl(mapURL);
	}
}
