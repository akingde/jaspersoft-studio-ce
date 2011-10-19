/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;

public class SwitchViewsAction extends Action implements IMenuCreator {
	public static final String SEPARATOR = "SEPARATOR";
	private MultiPageContainer container;
	protected String view;
	private boolean changeName = true;

	public SwitchViewsAction(MultiPageContainer container, String view, boolean changeName) {
		super(view, AS_DROP_DOWN_MENU);
		setToolTipText("View the report in different format");
		setMenuCreator(this);
		this.container = container;
		this.view = view;
		this.changeName = changeName;
	}

	private Menu listMenu;

	public void dispose() {
		if (listMenu != null)
			listMenu.dispose();
	}

	public Menu getMenu(Control parent) {
		if (listMenu != null)
			listMenu.dispose();
		listMenu = new Menu(parent);

		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MenuItem menuItem = (MenuItem) e.getSource();

				view = menuItem.getText();
				run();
			}
		};

		for (String key : container.getKeys()) {
			if (key.startsWith(SEPARATOR)) {
				new MenuItem(listMenu, SWT.SEPARATOR);
			} else {
				MenuItem m1 = new MenuItem(listMenu, SWT.CHECK);
				m1.setText(key);
				m1.addSelectionListener(listener);
			}
		}
		return listMenu;
	}

	public Menu getMenu(Menu parent) {
		return null;
	}

	@Override
	public void run() {
		if (view != null) {
			if (changeName)
				setText(view);
			container.switchView(view);
		}
	}
}