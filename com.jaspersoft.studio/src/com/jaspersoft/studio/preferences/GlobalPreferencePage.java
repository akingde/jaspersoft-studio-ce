/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class GlobalPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {
	public static final String JSS_JETTY_PORT = "com.jaspersoft.studio.jetty.port"; //$NON-NLS-1$

	public GlobalPreferencePage() {
		super(GRID);
		setPreferenceStore(JaspersoftStudioPlugin.getInstance().getPreferenceStore());
	}

	@Override
	protected void createFieldEditors() {
		Label lbl = new Label(getFieldEditorParent(), SWT.NONE);
		lbl.setText(Messages.GlobalPreferencePage_jettyServerTitle);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		IntegerFieldEditor port = new IntegerFieldEditor(JSS_JETTY_PORT, Messages.GlobalPreferencePage_port, getFieldEditorParent());
		port.setValidRange(0, 49151);
		addField(port);
	}

	public static void getDefaults(IPreferenceStore store) {
		store.setDefault(JSS_JETTY_PORT, 0);
	}

	@Override
	public void init(IWorkbench workbench) {

	}

}
