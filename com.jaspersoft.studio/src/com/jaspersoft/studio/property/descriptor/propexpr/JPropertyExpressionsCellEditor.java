/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.propexpr;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionEditor;

public class JPropertyExpressionsCellEditor extends EditableDialogCellEditor {
	private boolean showExpression = true;

	public JPropertyExpressionsCellEditor(Composite parent) {
		this(parent, true);
	}

	public JPropertyExpressionsCellEditor(Composite parent, boolean showExpression) {
		super(parent);
		this.showExpression = showExpression;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		JRPropertyExpressionEditor wizard = new JRPropertyExpressionEditor();
		wizard.setShowExpression(showExpression);
		// clone the object to avoid side effect
		wizard.setValue(((PropertyExpressionsDTO) getValue()).clone());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	private JPropertyExpressionsLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		if (labelProvider == null)
			labelProvider = new JPropertyExpressionsLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
