/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr.cell;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionDialog;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;

public class JRPropertyExpressionCellEditor extends EditableDialogCellEditor {
	private boolean showPropertyName = false;
	private boolean showExpression = true;

	public JRPropertyExpressionCellEditor(Composite parent) {
		super(parent);
	}

	public JRPropertyExpressionCellEditor(Composite parent, boolean showPropertyName, boolean showExpression,
			JasperReportsConfiguration jConfig) {
		super(parent);
		this.showPropertyName = showPropertyName;
		this.showExpression = showExpression;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		PropertyExpressionDTO v = ((PropertyExpressionDTO) getValue()).clone();
		JRPropertyExpressionDialog dialog = new JRPropertyExpressionDialog(cellEditorWindow.getShell());
		dialog.setShowPropertyName(showPropertyName);
		dialog.setShowExpression(showExpression);
		String title = getDialogTitle();
		if (title != null)
			dialog.setTitle(title);
		dialog.setValue(v);
		if (dialog.open() == Window.OK)
			return v;
		return null;
	}

	protected String getDialogTitle() {
		return null;
	}

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		getDefaultLabel().setText(Misc.nvl(value, ""));
	}
}