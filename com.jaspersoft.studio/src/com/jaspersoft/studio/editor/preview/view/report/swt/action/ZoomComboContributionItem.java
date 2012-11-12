/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report.swt.action;

import java.text.DecimalFormat;
import java.util.EventObject;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewer;
import com.jaspersoft.studio.editor.preview.view.report.swt.IReportViewerListener;

/**
 * Zoom contribution item
 * 
 * @author Peter Severin (peter_p_s@users.sourceforge.net)
 */
public class ZoomComboContributionItem extends ContributionItem implements IReportViewerListener, Listener {

	private static DecimalFormat ZOOM_FORMAT = new DecimalFormat("####%"); //$NON-NLS-1$

	private IReportViewer viewer;

	private Combo combo;

	private ToolItem toolitem;

	private double[] zoomLevels;

	/**
	 * Constructs the action by specifying the report viewer to associate with the item.
	 * 
	 * @param viewer
	 *          the report viewer
	 */
	public ZoomComboContributionItem(IReportViewer viewer) {
		Assert.isNotNull(viewer);
		this.viewer = viewer;
		this.viewer.addReportViewerListener(this);
		refresh();
	}

	private void refresh() {
		if (combo == null || combo.isDisposed())
			return;
		try {
			if (!viewer.canChangeZoom()) {
				combo.setEnabled(false);
				combo.setText(""); //$NON-NLS-1$
			} else {
				combo.removeListener(SWT.Selection, this);
				combo.removeListener(SWT.DefaultSelection, this);
				if (zoomLevels != viewer.getZoomLevels()) {
					this.zoomLevels = viewer.getZoomLevels();
					combo.setItems(getZoomLevelsAsText());
				}

				String zoom = getZoomAsText();
				int index = combo.indexOf(zoom);
				if (index != -1)
					combo.select(index);
				else
					combo.setText(zoom);
				combo.setEnabled(true);
				combo.addListener(SWT.Selection, this);
				combo.addListener(SWT.DefaultSelection, this);
			}
		} catch (SWTException exception) {
			if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
				throw exception;
		}
	}

	private String[] getZoomLevelsAsText() {
		if (zoomLevels == null) {
			return new String[] { "100%" }; //$NON-NLS-1$
		} else {
			String[] textZoomLevels = new String[zoomLevels.length];
			for (int i = 0; i < textZoomLevels.length; i++) {
				textZoomLevels[i] = ZOOM_FORMAT.format(zoomLevels[i]);
			}
			return textZoomLevels;
		}
	}

	// private static int getTextWidth(String string, Control control) {
	// GC gc = new GC(control);
	// try {
	// return gc.textExtent(string).x;
	// } finally {
	// gc.dispose();
	// }
	// }

	private Control createControl(Composite parent) {
		combo = new Combo(parent, SWT.DROP_DOWN);
		combo.addListener(SWT.Selection, this);
		combo.addListener(SWT.DefaultSelection, this);
		combo.addListener(SWT.FocusIn, this);

		// Initialize width of combo
		combo.setItems(new String[] { "8888%" }); //$NON-NLS-1$
		combo.pack();

		refresh();
		return combo;
	}

	/**
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	public void dispose() {
		viewer.removeReportViewerListener(this);
		combo = null;
		viewer = null;
	}

	/**
	 * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Composite)
	 */
	public final void fill(Composite parent) {
		createControl(parent);
	}

	/**
	 * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.Menu, int)
	 */
	public final void fill(Menu parent, int index) {
		Assert.isTrue(false, "Can not add to menu"); //$NON-NLS-1$
	}

	/**
	 * @see org.eclipse.jface.action.IContributionItem#fill(org.eclipse.swt.widgets.ToolBar, int)
	 */
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		Control control = createControl(parent);
		toolitem.setWidth(combo.getSize().x);
		toolitem.setControl(control);
	}

	private void onSelection() {
		if (viewer.hasDocument()) {
			if (combo.getSelectionIndex() >= 0)
				setZoomAsText(combo.getItem(combo.getSelectionIndex()));
			else
				setZoomAsText(combo.getText());
		}
	}

	private void setZoomAsText(String zoomText) {
		int percentIndex = zoomText.indexOf('%');
		if (percentIndex != -1)
			zoomText = zoomText.substring(0, percentIndex);
		try {
			final float zoom = Float.parseFloat(zoomText) / 100;
			BusyIndicator.showWhile(null, new Runnable() {
				public void run() {
					viewer.setZoom(zoom);
				}
			});
		} catch (NumberFormatException e) {
			Display.getCurrent().beep();
		}

		combo.removeListener(SWT.Selection, this);
		combo.removeListener(SWT.DefaultSelection, this);
		String zoom = ZOOM_FORMAT.format(viewer.getZoom());
		int index = combo.indexOf(zoom);
		if (index != -1)
			combo.select(index);
		else
			combo.setText(zoom);
		combo.addListener(SWT.Selection, this);
		combo.addListener(SWT.DefaultSelection, this);
	}

	private String getZoomAsText() {
		return ZOOM_FORMAT.format(viewer.getZoom());
	}

	public void viewerStateChanged(EventObject evt) {
		refresh();
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		switch (event.type) {
		case SWT.FocusIn:
			refresh();
			break;
		case SWT.Selection:
		case SWT.DefaultSelection:
			onSelection();
			break;
		}
	}

}
