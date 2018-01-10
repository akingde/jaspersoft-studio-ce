/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.dialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.editor.table.TableLabelProvider;
import com.jaspersoft.studio.swt.widgets.table.DeleteButton;
import com.jaspersoft.studio.swt.widgets.table.INewElement;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;
import com.jaspersoft.studio.swt.widgets.table.NewButton;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.eclipse.ui.util.PersistentLocationDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Dialog used to handle the content of a array
 */
public class ArrayDialog extends PersistentLocationDialog {
	
	/**
	 * Table where the content of the array is shown
	 */
	private TableViewer tableViewer;
	
	/**
	 * Deserialized json array that is the input of the list
	 */
	private List<String> deserializedValues;
	
	/**
	 * The type of the expected elements into the list
	 */
	private ItemPropertyDescription<?> innerType;
	
	/**
	 * Build the dialog
	 * 
	 * @param parentShell the shell for the dialog
	 * @param values a json array as string that is the first input of the table
	 * @param innterType the type of the elements into the array
	 */
	public ArrayDialog(Shell parentShell, String values, ItemPropertyDescription<?> innerType) {
		super(parentShell);
		this.innerType = innerType;
		ObjectMapper mapper = new ObjectMapper();
		try {
			String[] stringValues = new String[0];
			if (values != null && !values.isEmpty()) {
				stringValues = mapper.readValue(values, String[].class);
			}
			deserializedValues = new ArrayList<String>(Arrays.asList(stringValues));
		} catch (Exception ex){
			ex.printStackTrace();
			deserializedValues = new ArrayList<String>();
		}
	}

	/**
	 * Configure Shell attributes like setText
	 */
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText(Messages.ImportDialog_2);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		// Control control = super.createDialogArea(parent);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		buildTable(composite);

		Composite bGroup = new Composite(composite, SWT.NONE);
		bGroup.setLayout(new GridLayout(1, false));
		bGroup.setLayoutData(new GridData(GridData.FILL_VERTICAL));
		bGroup.setBackground(parent.getBackground());

		//The dialog to input a simple value, the item property dialog is forced in simple mode since an array can not accept expression as inner element
		NewButton bnew = new NewButton();
		bnew.createNewButtons(bGroup, tableViewer, new INewElement() {

			public Object newElement(List<?> input, int pos) {
				ItemPropertyElementDialog dialog = new ItemPropertyElementDialog(UIUtils.getShell(), innerType, "", null, ExpressionContext.getGlobalContext()) {
		
					@Override
					protected WItemProperty createProperty(Composite parent, ItemPropertyDescription<?> idDesc, IPropertyEditor editor){
						
						return new WItemProperty(parent, WItemProperty.FORCE_SIMPLE_MODE, ipDesc, editor){
							@Override
							public boolean isExpressionMode() {
								return false;
							}
						};
					}
					
					@Override
					protected void createExpressionCheckbox(Composite dialogArea) {
					}
				};
				if (dialog.open() == IDialogConstants.OK_ID) {
					return dialog.getStaticValue();
				}
				return null;
			}
		});
		bnew.setButtonText(Messages.ImportDialog_4);

		
		DeleteButton bdel = new DeleteButton();
		bdel.createDeleteButton(bGroup, tableViewer);

		tableViewer.setInput(deserializedValues);

		return composite;
	}
	
	/**
	 * Return the values in the table serialized as a json array
	 */
	public String getInnerValues() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(deserializedValues);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void buildTable(Composite composite) {
		Table table = new Table(composite, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.minimumHeight = 400;
		gd.minimumWidth = 400;
		table.setLayoutData(gd);
		table.setHeaderVisible(false);
		table.setLinesVisible(true);

		tableViewer = new TableViewer(table);
		attachContentProvider(tableViewer);
		attachLabelProvider(tableViewer);

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100));
		table.setLayout(tlayout);

		TableColumn[] column = new TableColumn[1];
		column[0] = new TableColumn(table, SWT.NONE);

		for (int i = 0, n = column.length; i < n; i++)
			column[i].pack();

	}

	private void attachLabelProvider(TableViewer viewer) {
		viewer.setLabelProvider(new TableLabelProvider());
	}

	private void attachContentProvider(TableViewer viewer) {
		viewer.setContentProvider(new ListContentProvider());
	}
}
