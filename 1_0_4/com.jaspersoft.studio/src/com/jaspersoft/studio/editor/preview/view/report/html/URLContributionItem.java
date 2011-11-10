/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.view.report.html;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;

public class URLContributionItem extends ContributionItem implements PropertyChangeListener, Listener {

	private Text combo;
	private ToolItem toolitem;
	private IPartListener partListener;
	private String url;

	public URLContributionItem(String url) {
		super("urlitem");
		this.url = url;
	}

	/**
	 * Computes the width required by control
	 * 
	 * @param control
	 *          The control to compute width
	 * @return int The width required
	 */
	protected int computeWidth(Control control) {
		return control.computeSize(500, SWT.DEFAULT, true).x;
	}

	/**
	 * Creates and returns the control for this contribution item under the given parent composite.
	 * 
	 * @param parent
	 *          the parent composite
	 * @return the new control
	 */
	protected Control createControl(Composite parent) {

		combo = new Text(parent, SWT.BORDER | SWT.READ_ONLY);

		combo.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				handleWidgetSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				handleWidgetDefaultSelected(e);
			}
		});
		combo.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				// do nothing
			}

			public void focusLost(FocusEvent e) {
				refresh();
			}
		});
		refresh();
		toolitem.setWidth(computeWidth(combo));

		return combo;
	}

	/**
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	@Override
	public void dispose() {
		if (partListener == null)
			return;

		combo = null;
		partListener = null;
	}

	/**
	 * The control item implementation of this <code>IContributionItem</code> method calls the <code>createControl</code>
	 * framework method. Subclasses must implement <code>createControl</code> rather than overriding this method.
	 * 
	 * @param parent
	 *          The parent of the control to fill
	 */
	@Override
	public final void fill(Composite parent) {
		createControl(parent);
	}

	/**
	 * The control item implementation of this <code>IContributionItem</code> method throws an exception since controls
	 * cannot be added to menus.
	 * 
	 * @param parent
	 *          The menu
	 * @param index
	 *          Menu index
	 */
	@Override
	public final void fill(Menu parent, int index) {
		Assert.isTrue(false, "Can't add a control to a menu");//$NON-NLS-1$
	}

	/**
	 * The control item implementation of this <code>IContributionItem</code> method calls the <code>createControl</code>
	 * framework method to create a control under the given parent, and then creates a new tool item to hold it.
	 * Subclasses must implement <code>createControl</code> rather than overriding this method.
	 * 
	 * @param parent
	 *          The ToolBar to add the new control to
	 * @param index
	 *          Index
	 */
	@Override
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		Control control = createControl(parent);
		toolitem.setControl(control);
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
	 */
	private void handleWidgetDefaultSelected(SelectionEvent event) {
		refresh();
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
	 */
	private void handleWidgetSelected(SelectionEvent event) {
		// forceSetText = true;
		handleWidgetDefaultSelected(event);
		// forceSetText = false;
	}

	public void propertyChange(PropertyChangeEvent evt) {
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
			// onSelection();
			break;
		}
	}

	public void refresh() {
		if (combo == null || combo.isDisposed())
			return;
		// $TODO GTK workaround
		try {
			String strurl = url != null ? url : "";
			combo.setText(strurl);
			combo.setToolTipText(strurl);
		} catch (SWTException exception) {
			if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
				throw exception;
		}
	}

	public void setEnabled(boolean enabled) {
		combo.setEnabled(enabled);
	}

}
