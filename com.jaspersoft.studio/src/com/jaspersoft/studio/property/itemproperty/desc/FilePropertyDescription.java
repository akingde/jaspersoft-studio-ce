/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.FileSelectionDialog;

import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class FilePropertyDescription extends ItemPropertyDescription<String> {

	public FilePropertyDescription() {
		super();
	}

	public FilePropertyDescription(String name, String description, boolean mandatory) {
		super(name, name, description, mandatory, null);
	}

	public FilePropertyDescription(String name, String label, String description, boolean mandatory) {
		super(name, label, description, mandatory, null);
	}

	public FilePropertyDescription(String name, String description, boolean mandatory, String defaultValue) {
		super(name, name, description, mandatory, defaultValue);
	}

	public FilePropertyDescription(String name, String label, String description, boolean mandatory,
			String defaultValue) {
		super(name, label, description, mandatory, defaultValue);
	}

	private boolean refreshing = false;

	@Override
	public void handleEdit(Control txt, StandardItemProperty value) {
		if (refreshing)
			return;
		super.handleEdit(txt, value);
	}

	public Control createControl(final IWItemProperty wiProp, Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Control c = super.createControl(wiProp, cmp);

		if (!isReadOnly()) {
			ToolBar toolBar = new ToolBar(cmp, SWT.NONE);
			ToolItem b = new ToolItem(toolBar, SWT.FLAT);
			b.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/obj16/fldr_obj.gif"));
			b.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {
					refreshing = true;
					wiProp.setRefresh(true);
					try {
						FileSelectionDialog fsd = new FileSelectionDialog(UIUtils.getShell());
						fsd.configureDialog(jConfig);
						if (fsd.open() == Dialog.OK) {
							JRDesignExpression exp = fsd.getFileExpression();
							textExpression.setText(exp.getText());

							StandardItemProperty v = wiProp.getValue();
							if (v == null)
								v = new StandardItemProperty(getName(), null, exp);
							else
								v.setValueExpression(exp);
							wiProp.setValue(v);
						}
					} finally {
						wiProp.setRefresh(false);
						refreshing = false;
					}
				}

			});
		}
		return c;
	}

}
