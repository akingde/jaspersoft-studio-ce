/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.ui.actions;

import org.eclipse.gef.editparts.ZoomListener;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

/**
 * A ControlContribution that uses a {@link org.eclipse.swt.widgets.Combo} as
 * its control to set the level of zoom on the ZoomManager, it is a simplified 
 * version of the GEF {@link ZoomComboContributionItem}, where some listeners
 * were removed to better fit JSS
 * 
 * @author Orlandin Marco
 */
public class RZoomComboContributionItem  extends ContributionItem implements ZoomListener {
	
	/** 
	 * The combo where the zoom level is set
	 */
	private Combo combo;

	/**
	 * Flag used to simply set the text inside the combo and not select
	 * an items instead
	 */
	private boolean forceSetText;
	
	/**
	 * The entry on the combo widget
	 */
	private String[] initStrings;
	
	/**
	 * Toolitem where the combo is placed
	 */
	private ToolItem toolitem;
	
	/**
	 * The zoom manager affected by the selection of the combo
	 */
	private ZoomManager zoomManager;

	/**
	 * Instantiates a new zoom combo contribution item with the default
	 * zoom levels. The item is instantiated without a zoom manger so its selection
	 * will do nothing until a not null zoom manager is set trough setZoomManager method
	 */
	public RZoomComboContributionItem() {
		this(new String[] { "8888%"}, null);//$NON-NLS-1$
	}
	
	/**
	 * Instantiates a new zoom combo contribution item with the default
	 * zoom levels
	 * 
	 * @param manager the zoom manger affected by the selection
	 */
	public RZoomComboContributionItem(ZoomManager manager) {
		this(new String[] { "8888%"}, manager);//$NON-NLS-1$
	}	

	/**
	 * Instantiates a new zoom combo contribution item with the default
	 * zoom levels
	 * 
	 * @param the number of characters used to force the size of the combo
	 * @param manager the zoom manger affected by the selection
	 */
	public RZoomComboContributionItem(String[] initStrings, ZoomManager manager) {
		super(GEFActionConstants.ZOOM_TOOLBAR_WIDGET);
		this.initStrings = initStrings;
		setZoomManager(manager);
	}

	/**
	 * Refresh the combo, can populate it as defined from the zoom manager and
	 * enable or disable it if the zoom manager is not null or null
	 * 
	 * @param repopulateCombo true if the entry of the combo should be refreshed, false
	 * otherwise
	 */
	private void refresh(boolean repopulateCombo) {
		if (combo == null || combo.isDisposed())
			return;
		try {
			if (zoomManager == null) {
				combo.setEnabled(false);
				combo.setText(""); //$NON-NLS-1$
			} else {
				if (repopulateCombo){
					combo.setItems(zoomManager.getZoomLevelsAsText());
				}
				String zoom = zoomManager.getZoomAsText();
				int index = combo.indexOf(zoom);
				if (index == -1 || forceSetText)
					combo.setText(zoom);
				else
					combo.select(index);
				combo.setEnabled(true);
			}
		} catch (SWTException exception) {
			if (!SWT.getPlatform().equals("gtk")) //$NON-NLS-1$
				throw exception;
		}
	}
	
	 
	/**
	 * Force to enable or disable the combo, if the combo was
	 * not created or it is disposed this does nothing
	 * 
	 * @param enabled true if the combo should be enabled,
	 * false otherwise
	 */
	public void setEnabled(boolean enabled) {
		if (combo != null && !combo.isDisposed())
			combo.setEnabled(enabled);
	}


	/**
	 * Computes the width required by control
	 * 
	 * @param control The control to compute width
	 * @return int The width required
	 */
	protected int computeWidth(Control control) {
		return control.computeSize(SWT.DEFAULT, SWT.DEFAULT, true).x;
	}

	/**
	 * Creates and returns the control for this contribution item under the
	 * given parent composite.
	 * 
	 * @param parent the parent composite
	 * @return the new control
	 */
	protected Control createControl(Composite parent) {
		combo = new Combo(parent, SWT.DROP_DOWN);
		combo.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				//Called when the value is selected by clicking on an item
				handleWidgetSelected(e);
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				//Called when the value is typed and then enter is pressed
				handleWidgetDefaultSelected(e);
			}
		});
		
		combo.addFocusListener(new FocusAdapter() {

			public void focusLost(FocusEvent e) {
				refresh(false);
				combo.setSelection(new Point(0, 0));
			}
		});

		// Initialize width of combo
		combo.setItems(initStrings);
		toolitem.setWidth(computeWidth(combo));
		refresh(true);
		return combo;
	}

	/**
	 * Dispose the current control and eventually remove the listeners from the zoom manager
	 */
	@Override
	public void dispose() {
		if (zoomManager != null) {
			zoomManager.removeZoomListener(this);
			zoomManager = null;
		}
		if (combo != null){
			combo.dispose();
		}
		combo = null;
	}

	/**
	 * The control item implementation of this <code>IContributionItem</code>
	 * method calls the <code>createControl</code> framework method. Subclasses
	 * must implement <code>createControl</code> rather than overriding this
	 * method.
	 * 
	 * @param parent The parent of the control to fill
	 */
	@Override
	public final void fill(Composite parent) {
		createControl(parent);
	}

	/**
	 * The control item implementation of this <code>IContributionItem</code>
	 * method calls the <code>createControl</code> framework method to create a
	 * control under the given parent, and then creates a new tool item to hold
	 * it. Subclasses must implement <code>createControl</code> rather than
	 * overriding this method.
	 * 
	 * @param parent
	 *            The ToolBar to add the new control to
	 * @param index
	 *            Index
	 */
	@Override
	public void fill(ToolBar parent, int index) {
		toolitem = new ToolItem(parent, SWT.SEPARATOR, index);
		Control control = createControl(parent);
		toolitem.setControl(control);
	}

	/**
	 * Sets the ZoomManager, if there were a zoom manager set before then its 
	 * listener are removed before
	 * 
	 * @param zm The ZoomManager, if it is null the combo will
	 * be disabled, otherwise it will be initialized with the entry specified
	 * from the zoom manager
	 */
	public void setZoomManager(ZoomManager zm) {
		if (zoomManager == zm)
			return;
		if (zoomManager != null)
			zoomManager.removeZoomListener(this);

		zoomManager = zm;
		refresh(true);

		if (zoomManager != null)
			zoomManager.addZoomListener(this);
	}

	/**
	 * Action executed when a level of zoom is selected in the combo, it
	 * will notify to the zoom manager to change the level of zoom
	 * 
	 * @param event the combo selection event
	 */
	private void handleWidgetDefaultSelected(SelectionEvent event) {
		if (zoomManager != null) {
			if (combo.getSelectionIndex() >= 0){
				zoomManager.setZoomAsText(combo.getItem(combo.getSelectionIndex()));
			} else {
				zoomManager.setZoomAsText(combo.getText());
			}
		}
		/*
		 * There are several cases where invoking setZoomAsText (above) will not
		 * result in zoomChanged being fired (the method below), such as when
		 * the user types "asdf" as the zoom level and hits enter, or when they
		 * type in 1%, which is below the minimum limit, and the current zoom is
		 * already at the minimum level. Hence, there is no guarantee that
		 * refresh() will always be invoked. But we need to invoke it to clear
		 * out the invalid text and show the current zoom level. Hence, an
		 * (often redundant) invocation to refresh() is made below.
		 */
		refresh(false);
	}

	/**
	 * Action executed when a level of zoom is typed in the combo, it
	 * will notify to the zoom manager to change the level of zoom
	 * 
	 * @param event the combo selection event
	 */
	private void handleWidgetSelected(SelectionEvent event) {
		forceSetText = true;
		handleWidgetDefaultSelected(event);
		forceSetText = false;
	}

	/**
	 * Event called when the zoom level change in the zoom manager, maybe because of the 
	 * hotkey pressed to change it. This will refresh the selection of the combo but not its
	 * content
	 */
	@Override
	public void zoomChanged(double zoom) {
		refresh(false);
	}

}
