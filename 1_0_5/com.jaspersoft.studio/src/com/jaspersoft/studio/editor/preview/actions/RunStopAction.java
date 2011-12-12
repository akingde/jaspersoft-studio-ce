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
package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuCreator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.PreviewContainer;
import com.jaspersoft.studio.messages.Messages;

public class RunStopAction extends Action implements IMenuCreator {
	public static final String MODERUN_LOCAL = "RUNLOCAL";
	public static final String MODERUN_JIVE = "RUNJIVE";

	public static final String ID = "PREVIEWRELOADACTION"; //$NON-NLS-1$
	private PreviewContainer editor;

	public RunStopAction(PreviewContainer editor) {
		super();
		this.editor = editor;
		setId(ID);
		setMenuCreator(this);
		setDescription(Messages.RunStopAction_runreport_desc);
		setToolTipText(Messages.RunStopAction_runreport_desc);
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	@Override
	public void run() {
		editor.runReport(null);
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
				menuItem.setSelection(true);
				editor.setMode((String) menuItem.getData("run.key"));
				run();
			}
		};

		MenuItem m1 = new MenuItem(listMenu, SWT.RADIO);
		m1.setText("Run Report");
		m1.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/start_task.gif"));
		m1.addSelectionListener(listener);
		m1.setData("run.key", MODERUN_LOCAL);

//		m1 = new MenuItem(listMenu, SWT.RADIO);
//		m1.setText("Run Interactive Report (Jive)");
//		m1.setImage(JaspersoftStudioPlugin.getImage("icons/resources/eclipse/start_task.gif"));
//		m1.addSelectionListener(listener);
//		m1.setData("run.key", MODERUN_JIVE);

		return listMenu;
	}

	public Menu getMenu(Menu parent) {
		return null;
	}
}
