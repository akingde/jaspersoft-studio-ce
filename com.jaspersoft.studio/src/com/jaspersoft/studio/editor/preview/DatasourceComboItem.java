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
package com.jaspersoft.studio.editor.preview;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.ui.IPartListener;

import com.jasperassistant.designer.viewer.IReportViewerListener;
import com.jasperassistant.designer.viewer.ReportViewerEvent;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.repository.RepositoryManager;

public class DatasourceComboItem extends ContributionItem implements PropertyChangeListener, IReportViewerListener,
		Listener {
	private List<AMDatasource> items;
	private int selecteditem;
	// private boolean forceSetText;
	private CCombo combo;
	private ToolItem toolitem;
	private IPartListener partListener;
	private PreviewEditor editor;

	/**
	 * Constructor for ComboToolItem.
	 * 
	 * @param partService
	 *          used to add a PartListener
	 * @param initStrings
	 *          the initial string displayed in the combo
	 */
	public DatasourceComboItem(PreviewEditor editor) {
		super(GEFActionConstants.ZOOM_TOOLBAR_WIDGET);
		this.editor = editor;
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNorun();// .canChangeZoom();
	}

	public void refresh(boolean repopulateCombo) {
		if (combo == null || combo.isDisposed())
			return;
		// $TODO GTK workaround
		try {
			if (!isEnabled()) {
				combo.setEnabled(false);
				combo.setText(""); //$NON-NLS-1$
			} else {
				combo.removeListener(SWT.Selection, this);
				combo.removeListener(SWT.DefaultSelection, this);
				if (repopulateCombo) {
					AMDatasource d = null;
					if (combo.getSelectionIndex() > 0)
						d = items.get(combo.getSelectionIndex() - 1);
					else if (selecteditem > 0 && items.size() > selecteditem)
						d = items.get(selecteditem - 1);

					items = RepositoryManager.getDatasources();

					combo.setItems(getStringItems());
					selectCombo(0);
					if (d != null)
						for (int i = 0; i < items.size(); i++)
							if (items.get(i) == d) {
								selectCombo(i + 1);
								break;
							}

				}
				combo.setEnabled(true);
				combo.addListener(SWT.Selection, this);
				combo.addListener(SWT.DefaultSelection, this);
			}
		} catch (SWTException exception) {
			if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
				throw exception;
		}
	}

	private void selectCombo(int i) {
		combo.select(i);
	}

	private String[] getStringItems() {
		String[] s = new String[items.size() + 1];
		s[0] = "-- Select a DataSource --";
		for (int i = 0; i < items.size(); i++) {
			String displayText = items.get(i).getDisplayText();
			if (displayText == null || displayText.trim().equals(""))
				displayText = "empty";
			s[i + 1] = displayText;
		}
		return s;
	}

	/**
	 * Computes the width required by control
	 * 
	 * @param control
	 *          The control to compute width
	 * @return int The width required
	 */
	protected int computeWidth(Control control) {
		return control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
	}

	/**
	 * Creates and returns the control for this contribution item under the given parent composite.
	 * 
	 * @param parent
	 *          the parent composite
	 * @return the new control
	 */
	protected Control createControl(Composite parent) {
		combo = new CCombo(parent, SWT.DROP_DOWN | SWT.BORDER);
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
				refresh(false);
			}
		});

		// Initialize width of combo
		items = RepositoryManager.getDatasources();
		combo.setItems(getStringItems());
		selectCombo(0);
		toolitem.setWidth(computeWidth(combo));
		refresh(true);

		RepositoryManager.getPropertyChangeSupport().addPropertyChangeListener(this);
		return combo;
	}

	/**
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	public void dispose() {
		if (partListener == null)
			return;
		RepositoryManager.getPropertyChangeSupport().removePropertyChangeListener(this);
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
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		Control control = createControl(parent);
		toolitem.setControl(control);
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(SelectionEvent)
	 */
	private void handleWidgetDefaultSelected(SelectionEvent event) {
		if (combo.getSelectionIndex() > 0) {
			final AMDatasource m = items.get(combo.getSelectionIndex() - 1);
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					editor.runReport(m);
				}
			});
		}

		refresh(false);
	}

	/**
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(SelectionEvent)
	 */
	private void handleWidgetSelected(SelectionEvent event) {
		// forceSetText = true;
		handleWidgetDefaultSelected(event);
		// forceSetText = false;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refresh(true);
	}

	/**
	 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
	 */
	public void handleEvent(Event event) {
		switch (event.type) {
		case SWT.FocusIn:
			refresh(false);
			break;
		case SWT.Selection:
		case SWT.DefaultSelection:
			onSelection();
			break;
		}
	}

	private void onSelection() {
		selecteditem = combo.getSelectionIndex();
	}

	@Override
	public void viewerStateChanged(ReportViewerEvent evt) {
		refresh(false);
	}
}
