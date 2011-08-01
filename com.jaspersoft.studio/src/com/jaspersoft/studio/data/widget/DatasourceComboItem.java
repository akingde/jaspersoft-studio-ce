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
package com.jaspersoft.studio.data.widget;

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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
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
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.messages.Messages;

public class DatasourceComboItem extends ContributionItem implements PropertyChangeListener, IReportViewerListener,
		Listener {

	/**
	 * List of dataAdapter backing end the combo box. The combo shows the data coming from this list.
	 */
	private List<DataAdapterDescriptor> dataAdapters;

	// private int selecteditem;
	// private boolean forceSetText;
	private CCombo combo;
	private ToolItem toolitem;
	private IPartListener partListener;
	private IDataAdapterRunnable editor;

	/**
	 * Constructor for ComboToolItem.
	 * 
	 * @param partService
	 *          used to add a PartListener
	 * @param initStrings
	 *          the initial string displayed in the combo
	 */
	public DatasourceComboItem(IDataAdapterRunnable editor) {
		super(GEFActionConstants.ZOOM_TOOLBAR_WIDGET);
		this.editor = editor;
	}

	/**
	 * This method refresh the list of data adapters in the combo box. It does preserve the selection (if any).
	 * 
	 * If combo is disposed or not yet available, it does nothing.
	 * 
	 */
	public void updateDataAdapters() {
		if (combo == null || combo.isDisposed())
			return;

		// Remove the current listener
		combo.removeListener(SWT.Selection, this);
		combo.removeListener(SWT.DefaultSelection, this);

		// Store the previous selection in selectedAdapter
		int selectedIndex = combo.getSelectionIndex();

		DataAdapterDescriptor selectedAdapter = null;

		if (selectedIndex > 0 && getDataAdapters().size() >= selectedIndex) {
			selectedAdapter = getDataAdapters().get(selectedIndex - 1);
		}

		// Clear the list, and get a fresh list from the DataAdapter manager
		setDataAdapters(getDataAdaptersList());

		// Clear the combo box...
		combo.removeAll();

		// Populate the combo with an empty string as first item
		String[] dataAdapterNames = new String[getDataAdapters().size() + 1];

		dataAdapterNames[0] = "-- " + Messages.DatasourceComboItem_select_a_datasource + " --";
		int index = 1;
		for (DataAdapterDescriptor da : getDataAdapters()) {
			dataAdapterNames[index] = da.getName();
			index++;
		}
		combo.setItems(dataAdapterNames);

		// restore the selection...if any
		setSelected(selectedAdapter);

		combo.pack();
		Point size = combo.getSize();
		Rectangle bounds = combo.getBounds();
		bounds.width = Math.max(300, size.x);
		combo.setBounds(bounds);
		combo.setSize(size.x, bounds.height);

		// Restore listener
		combo.addListener(SWT.Selection, this);
		combo.addListener(SWT.DefaultSelection, this);
	}

	public void setSelected(DataAdapterDescriptor selectedAdapter) {
		if (selectedAdapter != null) {
			int newSelectionIndex = getDataAdapters().indexOf(selectedAdapter);
			if (newSelectionIndex >= 0) {
				combo.select(newSelectionIndex + 1);
			}
		}

		// Set a default selection
		if (combo.getSelectionIndex() < 0 && combo.getItemCount() > 0) {
			combo.select(0);
		}
		handleWidgetDefaultSelected(null);
	}

	private List<DataAdapterDescriptor> getDataAdaptersList() {
		return DataAdapterManager.getDataAdapters();
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();// .canChangeZoom();
	}

	public void refresh(boolean repopulateCombo) {
		if (combo == null || combo.isDisposed())
			return;
		// $TODO GTK workaround
		try {
			if (!isEnabled()) {
				combo.setEnabled(false);
				//combo.setText(""); //$NON-NLS-1$
			} else {

				/*
				 * combo.removeListener(SWT.Selection, this); combo.removeListener(SWT.DefaultSelection, this);
				 * 
				 * if (repopulateCombo) {
				 * 
				 * 
				 * AMDatasource d = null; if (combo.getSelectionIndex() > 0) d = items.get(combo.getSelectionIndex() - 1); else
				 * if (selecteditem > 0 && items.size() > selecteditem) d = items.get(selecteditem - 1);
				 * 
				 * items = RepositoryManager.getDatasources();
				 * 
				 * combo.setItems(getStringItems()); selectCombo(0); if (d != null) for (int i = 0; i < items.size(); i++) if
				 * (items.get(i) == d) { selectCombo(i + 1); break; } } combo.setEnabled(true); combo.addListener(SWT.Selection,
				 * this); combo.addListener(SWT.DefaultSelection, this);
				 */
				if (repopulateCombo) {
					updateDataAdapters();
				}
				combo.setEnabled(true);
			}
		} catch (SWTException exception) {
			if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
				throw exception;
		}
	}

	private void selectCombo(int i) {
		combo.select(i);
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
		setDataAdapters(getDataAdaptersList());

		combo = new CCombo(parent, SWT.DROP_DOWN | SWT.BORDER | SWT.READ_ONLY);

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
		/*
		 * items = RepositoryManager.getDatasources(); combo.setItems(getStringItems()); selectCombo(0);
		 */

		// We add a listener to the DataAdapterManager, so we can update the
		// list of available data adapters when they are changed.
		DataAdapterManager.getPropertyChangeSupport().addPropertyChangeListener(this);

		refresh(true);
		toolitem.setWidth(computeWidth(combo));

		// RepositoryManager.getPropertyChangeSupport().addPropertyChangeListener(this);
		return combo;
	}

	/**
	 * @see org.eclipse.jface.action.ContributionItem#dispose()
	 */
	@Override
	public void dispose() {
		if (partListener == null)
			return;

		DataAdapterManager.getPropertyChangeSupport().removePropertyChangeListener(this);
		// RepositoryManager.getPropertyChangeSupport().removePropertyChangeListener(this);
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
		if (combo.getSelectionIndex() > 0) {

			final DataAdapterDescriptor da = getDataAdapters().get(combo.getSelectionIndex() - 1);
			Display.getDefault().syncExec(new Runnable() {
				public void run() {
					selectedDA = da;
					editor.runReport(da);

				}
			});
		}

		refresh(false);
	}

	private DataAdapterDescriptor selectedDA;

	public DataAdapterDescriptor getSelected() {
		if (selectedDA == null) {
			try {
				int index = combo.getSelectionIndex() - 1;
				if (index < 0 || index >= dataAdapters.size())
					index = 0;
				if (dataAdapters.size() != 0) {
					selectedDA = dataAdapters.get(index);
					selectCombo(index + 1);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (dataAdapters.size() > 0)
					return dataAdapters.get(0);
				return null;
			}
		}
		return selectedDA;
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
			// onSelection();
			break;
		}
	}

	public void viewerStateChanged(ReportViewerEvent evt) {
		refresh(false);
	}

	/**
	 * @param dataAdapters
	 *          the dataAdapters to set
	 */
	private void setDataAdapters(List<DataAdapterDescriptor> dataAdapters) {
		this.dataAdapters = dataAdapters;
	}

	/**
	 * @return the dataAdapters
	 */
	private List<DataAdapterDescriptor> getDataAdapters() {
		return dataAdapters;
	}

}
