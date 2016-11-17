/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.UIUtil;
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
	public void setupMenu(final IWItemProperty wiProp, final ItemPropertyDescription<?> item, final Control c){
		if (item.getDefaultValue() != null && !item.isMandatory()) {
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
					UIUtil.updateFocus(c);
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
			
			//When there is a fallback value show a different action name, since in that case means that there is a value to inherit
			String actionName = item.getFallbackValue() != null ? Messages.ASPropertyWidget_2 : Messages.ASPropertyWidget_1;
			for (MenuItem mi : controlMenu.getItems())
				if (mi.getText().equals(actionName))
					return;

			MenuItem refreshItem = new MenuItem(controlMenu, SWT.NONE);
			refreshItem.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					wiProp.getPropertyEditor().removeProperty(item.getName());
					wiProp.updateWidget();
					UIUtil.updateFocus(c);
				}
			});
			refreshItem.setText(actionName);
		}
	}
}
