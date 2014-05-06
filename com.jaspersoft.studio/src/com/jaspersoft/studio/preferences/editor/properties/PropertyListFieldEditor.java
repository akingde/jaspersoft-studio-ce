/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.preferences.editor.properties;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRPropertiesUtil.PropertySuffix;
import net.sf.jasperreports.engine.util.JRProperties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.preferences.util.PropertiesHelper;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

public class PropertyListFieldEditor extends TableFieldEditor {

	public static final String NET_SF_JASPERREPORTS_JRPROPERTIES = "net.sf.jasperreports.JRPROPERTIES"; //$NON-NLS-1$

	public PropertyListFieldEditor() {
		super();
	}

	public PropertyListFieldEditor(String name, String labelText, Composite parent) {
		super(name, labelText, new String[] { Messages.PropertyListFieldEditor_propertyLabel,
				Messages.PropertyListFieldEditor_valueLabel }, new int[] { 200, 30 }, parent);
	}

	@Override
	protected String createList(String[][] items) {
		return ""; //$NON-NLS-1$
	}

	@Override
	protected String[][] parseString(String string) {
		return new String[0][0];
	}

	@Override
	protected String[] getNewInputObject() {
		final String[] prop = new String[2];
		Dialog dialog = new Dialog(UIUtils.getShell()) {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
			 */
			protected Control createDialogArea(Composite parent) {
				Composite composite = (Composite) super.createDialogArea(parent);
				composite.setLayout(new GridLayout(2, false));
				Label label = new Label(composite, SWT.NONE);
				label.setText(Messages.PropertyListFieldEditor_newPropertyName);

				final Text text = new Text(composite, SWT.BORDER);
				text.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
				text.setText("net.sf.jaspersoft."); //$NON-NLS-1$
				text.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						prop[0] = text.getText();
					}
				});

				label = new Label(composite, SWT.NONE);
				label.setText(Messages.PropertyListFieldEditor_newPropertyValue);

				final Text tname = new Text(composite, SWT.BORDER);
				tname.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL | GridData.HORIZONTAL_ALIGN_FILL));
				tname.setText(Messages.PropertyListFieldEditor_exampleValue);
				tname.addModifyListener(new ModifyListener() {

					@Override
					public void modifyText(ModifyEvent e) {
						prop[1] = tname.getText();
					}
				});
				applyDialogFont(composite);
				return composite;
			}
			
			@Override
			protected boolean isResizable() {
				return true;
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
			 */
			protected void configureShell(Shell newShell) {
				super.configureShell(newShell);
				newShell.setSize(500, 200);
				newShell.setText(Messages.PropertyListFieldEditor_newPropertyTitle);
			}

		};
		if (dialog.open() == Window.OK)
			return prop;
		return null;
	}

	protected void doStore() {
		TableItem[] items = getTable().getItems();
		Properties props = new Properties();
		for (int i = 0; i < items.length; i++) {
			TableItem item = items[i];
			// getPreferenceStore().setValue(item.getText(0), item.getText(1));
			String key = item.getText(0);
			String value = item.getText(1);
			props.setProperty(key, value);
			if (key.equals("net.sf.jasperreports.default.font.name"))
				JRProperties.setProperty(key, value);
			else if (key.equals("net.sf.jasperreports.default.font.size"))
				JRProperties.setProperty(key, value);
		}
		getPreferenceStore().setValue(NET_SF_JASPERREPORTS_JRPROPERTIES, FileUtils.getPropertyAsString(props));
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		if (getTable() != null) {
			//			List<PropertySuffix> lst = PropertiesHelper.DPROP.getProperties(""); //$NON-NLS-1$
			// Collections.sort(lst, new PropertyComparator());
			Properties props = null;
			try {
				props = FileUtils.load(getPreferenceStore().getString(NET_SF_JASPERREPORTS_JRPROPERTIES));
				List<String> keys = new ArrayList<String>();
				for (Object key : props.keySet())
					keys.add((String) key);
				Collections.sort(keys);

				for (String key : keys) {
					String value = props.getProperty(key);
					TableItem tableItem = new TableItem(getTable(), SWT.NONE);
					tableItem.setText(new String[] { (String) key, value });
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			// if (props != null)
			// for (PropertySuffix ps : lst) {
			// if (props.getProperty(ps.getKey()) == null) {
			// TableItem tableItem = new TableItem(getTable(), SWT.NONE);
			// tableItem.setText(new String[] { ps.getKey(), ps.getValue() });
			// }
			// }

			// TableItem[] items = table.getItems();
			// Collator collator = Collator.getInstance(Locale.getDefault());
			// for (int i = 1; i < items.length; i++) {
			// String value1 = items[i].getText(0);
			// for (int j = 0; j < i; j++) {
			// String value2 = items[j].getText(0);
			// if (collator.compare(value1, value2) < 0) {
			// String[] values = { items[i].getText(0), items[i].getText(1) };
			// items[i].dispose();
			// TableItem item = new TableItem(table, SWT.NONE, j);
			// item.setText(values);
			// items = table.getItems();
			// break;
			// }
			// }
			// }
			// Add an help listener to the table
			TableHelpListener.setTableHelp(getTable());
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoadDefault() {
		if (getTable() != null) {
			getTable().removeAll();

			List<PropertySuffix> lst = PropertiesHelper.DPROP.getProperties(""); //$NON-NLS-1$
			Collections.sort(lst, new PropertyComparator());
			for (PropertySuffix ps : lst) {

				TableItem tableItem = new TableItem(getTable(), SWT.NONE);
				tableItem.setText(new String[] { ps.getKey(), ps.getValue() });
			}
		}
	}

	@Override
	protected boolean isFieldEditable(int col, int row) {
		if (col == 0) {
			TableItem ti = table.getItem(row);
			return PropertiesHelper.DPROP.getProperty(ti.getText(0)) == null;
		}
		return super.isFieldEditable(col, row);
	}

	@Override
	protected void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, ContextHelpIDs.PREFERENCES_PROPERTIES);
	}

	@Override
	protected void createButtons(Composite box) {
		addButton = createPushButton(box, Messages.common_add);
		duplicateButton = createPushButton(box, Messages.PropertyListFieldEditor_duplicateButton);
		removeButton = createPushButton(box, Messages.common_delete);
	}
}
