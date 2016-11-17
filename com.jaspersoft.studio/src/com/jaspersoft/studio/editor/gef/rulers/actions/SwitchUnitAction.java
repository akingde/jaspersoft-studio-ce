/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.actions;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerEditPart;
import com.jaspersoft.studio.messages.Messages;

/**
 * Contextual action of the ruler to switch on the fly the masure unit
 * 
 * @author Orlandin Marco
 *
 */
public class SwitchUnitAction extends Action implements IMenuCreator{
	
	/**
	 * The ruler viewer
	 */
	private EditPartViewer viewer;
	
	/**
	 * The submenu where the available measure are shown
	 */
	private Menu menu;
	
	/**
	 * The ID of the action
	 */
	public static final String ID = "com.jaspersoft.studio.rulers.SwitchUnitAction"; //$NON-NLS-1$

	/**
	 * Listener called when a measure is selected, change the measure unit
	 * on the ruler provider
	 */
	private SelectionAdapter checkSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			//each menu item has in the data field the associated measure
			int value = (Integer)e.widget.getData();
			JDRulerEditPart rulerEditPart = (JDRulerEditPart) viewer.getRootEditPart().getChildren().get(0);
			RulerProvider provider = rulerEditPart.getRulerProvider();
			provider.setUnit(value);
		};
	};
	
	/**
	 * Listener called when the menu is opened, check the action
	 * of the actually used measure
	 */
	private MenuAdapter menuOpened = new MenuAdapter() {
		
		public void menuShown(MenuEvent e) {
			if (menu != null && !menu.isDisposed()){
				JDRulerEditPart rulerEditPart = (JDRulerEditPart) viewer.getRootEditPart().getChildren().get(0);
				RulerProvider provider = rulerEditPart.getRulerProvider();
				int unit = provider.getUnit();
				for(MenuItem item : menu.getItems()){
					item.setSelection(item.getData().equals(unit));
				}
			}
		};
		
	};
	
	/**
	 * Constructor
	 * 
	 * @param ruler the viewer for the ruler on which the guide is to be created
	 */
	public SwitchUnitAction(EditPartViewer ruler) {
		super(Messages.SwitchUnitAction_menuTitle, IAction.AS_DROP_DOWN_MENU);
		viewer = ruler;
		setId(ID);
	}

	@Override
	public Menu getMenu(Menu parent) {
		if (menu != null){
			menu.dispose();
		}
		//Create the menu entry for each measure
		menu = new Menu(parent);
		menu.addMenuListener(menuOpened);
		MenuItem item = new MenuItem(menu, SWT.CHECK);
		item.setText(Messages.DesignerPreferencePage_pixelMeasure);
		item.setData(RulerProvider.UNIT_PIXELS);
		item.addSelectionListener(checkSelected);
		item = new MenuItem(menu, SWT.CHECK);
		item.setText(Messages.DesignerPreferencePage_centimeterMeasure);
		item.setData( RulerProvider.UNIT_CENTIMETERS);
		item.addSelectionListener(checkSelected);
		item = new MenuItem(menu, SWT.CHECK);
		item.setText(Messages.DesignerPreferencePage_inchMeasure);
		item.setData(RulerProvider.UNIT_INCHES);
		item.addSelectionListener(checkSelected);
		return menu;
	}
	
	@Override
	public void dispose() {
		if (menu != null){
			menu.dispose();
			menu = null;
		}
	}

	@Override
	public Menu getMenu(Control parent) {
		return null;
	}
	
	@Override
	public IMenuCreator getMenuCreator() {
		return this;
	}
}
