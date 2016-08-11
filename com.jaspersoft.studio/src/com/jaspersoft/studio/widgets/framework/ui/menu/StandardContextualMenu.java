/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * A standard contextual menu provider to have the action "set to default" and "reset to null"
 * action on the widget that support them
 * 
 * @author Orlandin Marco
 *
 */
public class StandardContextualMenu implements IMenuProvider {
	
	public static StandardContextualMenu INSTANCE = new StandardContextualMenu();

	@Override
	public void setupMenu(final IWItemProperty wiProp, final ItemPropertyDescription<?> item, Control c){
		if (item.getDefaultValue() != null) {
			Menu controlMenu = c.getMenu();
			if (controlMenu == null) {
				controlMenu = new Menu(c);
				c.setMenu(controlMenu);
			}
			for (MenuItem mi : controlMenu.getItems())
				if (mi.getText().equals(Messages.ASPropertyWidget_0))
					return;

			MenuItem refreshItem = new MenuItem(controlMenu, SWT.NONE);
			refreshItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					wiProp.setValue(item.getDefaultValueString(), null);
				}
			});
			refreshItem.setText(Messages.ASPropertyWidget_0);
		}
		if (!item.isMandatory()) {
			Menu controlMenu = c.getMenu();
			if (controlMenu == null) {
				controlMenu = new Menu(c);
				c.setMenu(controlMenu);
			}
			for (MenuItem mi : controlMenu.getItems())
				if (mi.getText().equals(Messages.ASPropertyWidget_1))
					return;

			MenuItem refreshItem = new MenuItem(controlMenu, SWT.NONE);
			refreshItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					wiProp.getPropertyEditor().removeProperty(item.getName());
					wiProp.updateWidget();
				}
			});
			refreshItem.setText(Messages.ASPropertyWidget_1);
		}
	}
}
