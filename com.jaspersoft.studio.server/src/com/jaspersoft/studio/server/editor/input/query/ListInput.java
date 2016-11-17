/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.input.query;

import java.util.Map;

import org.eclipse.nebula.widgets.tablecombo.TableCombo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.preview.input.IParameter;

public class ListInput extends TableInput {

	private TableCombo combo;

	public ListInput(QueryInput dataInput, IParameter param, Map<String, Object> params) {
		super(dataInput, param, params);
	}

	@Override
	protected void createTable(Composite parent, int style) {
		combo = new TableCombo(parent, SWT.BORDER | SWT.SINGLE | SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 8;
		gd.minimumHeight = 100;
		combo.setLayoutData(gd);

		table = combo.getTable();
	}

	@Override
	public void fillControl() {
		combo.select(-1);
		combo.clearSelection();
		super.fillControl();
		combo.defineColumns(table.getColumnCount());
	}

	public Control getControl() {
		return combo;
	}

	@Override
	public void updateInput() {
		super.updateInput();
		int ind = table.getSelectionIndex();
		table.setSelection(-1);
		combo.select(ind);

	}
}
