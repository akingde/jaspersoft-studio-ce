/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.preferences;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.help.TableHelpListener;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.table.TableFieldEditor;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class ChartCustomizerListFieldEditor extends TableFieldEditor {

	private List<String> items = new ArrayList<String>();
	
	public ChartCustomizerListFieldEditor(String name, Composite parent) {
		super(name, "", new String[] { "Chart Customizer Class" }, new int[] { 100 }, parent);
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
		ChartCustomizerSelectionDialog fd = new ChartCustomizerSelectionDialog(UIUtils.getShell(), "Select the definition file", "Select the Chart Customizer definition file");
		if (fd.open() == Dialog.OK){
			String selection = fd.getSelectedResource();
			items.add(selection);
			return new String[]{selection};
		}
		return null;
	}

	protected void doStore() {
		String v = "";
		for (String srv : items)
			v += Base64.encodeBase64String(srv.getBytes()) + "\n";

		getPreferenceStore().setValue(ChartCustomizerPreferencePage.CHARTCUSTOMIZER, v);
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoad() {
		if (getTable() != null) {
			items.clear();
			String v = null;
			v = getPreferenceStore().getString(ChartCustomizerPreferencePage.CHARTCUSTOMIZER);
			if (v != null){
				for (String line : v.split("\n")) {
					if (line.isEmpty())
						continue;
					try {
						String srv = new String(Base64.decodeBase64(line));
						items.add(srv);
						TableItem tableItem = new TableItem(getTable(), SWT.NONE);
						tableItem.setText(new String[] { srv });
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			// Add an help listener to the table
			TableHelpListener.setTableHelp(getTable());
		}
	}

	/*
	 * (non-Javadoc) Method declared on FieldEditor.
	 */
	protected void doLoadDefault() {

	}

	@Override
	protected void adjustForNumColumns(int numColumns) {
		super.adjustForNumColumns(numColumns);
		((GridData) getLabelControl().getLayoutData()).exclude = true;
		getLabelControl().setVisible(false);
	}

	@Override
	protected boolean isFieldEditable(int col, int row) {
		return false;
	}

	@Override
	protected void createControl(Composite parent) {
		super.createControl(parent);
		PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, ContextHelpIDs.PREFERENCES_PROPERTIES);
	}

	@Override
	public void createSelectionListener() {
		selectionListener = new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				Widget widget = event.widget;
				if (widget == addButton){
					addPressed();
				} else if (widget == removeButton){
					removePressed();
				} else if (widget == table) {
					selectionChanged();
				}
			}
		};
	}


	@Override
	protected void removePressed() {
		int selIdx = table.getSelectionIndex();
		if (selIdx >= 0)
			items.remove(selIdx);
		super.removePressed();
	}

	protected boolean isEditable(int row) {
		return false;
	}

	@Override
	protected void createButtons(Composite box) {
		addButton = createPushButton(box, Messages.common_add);
		
		removeButton = createPushButton(box, Messages.common_delete);
	}
}
