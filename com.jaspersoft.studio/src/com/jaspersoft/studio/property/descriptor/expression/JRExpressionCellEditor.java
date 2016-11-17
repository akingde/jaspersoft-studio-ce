/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.ExpressionEditorSupportUtil;
import com.jaspersoft.studio.property.descriptor.JSSDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class JRExpressionCellEditor extends JSSDialogCellEditor {

	private ExpressionContext expContext;

	public JRExpressionCellEditor(Composite parent, ExpressionContext expContext) {
		super(parent, true);
		this.expContext = expContext;
	}

	public JRExpressionCellEditor(Composite parent, int style, ExpressionContext expContext) {
		super(parent, style, true);
		this.expContext = expContext;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		if(!ExpressionEditorSupportUtil.isExpressionEditorDialogOpen()) {
			JRExpressionEditor wizard = new JRExpressionEditor();
			JRDesignExpression originalValue = (JRDesignExpression) getValue();
			wizard.setValue(originalValue);
			wizard.setExpressionContext(expContext);
			WizardDialog dialog = ExpressionEditorSupportUtil.getExpressionEditorWizardDialog(cellEditorWindow.getShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				JRDesignExpression value = wizard.getValue();
				// updateContents(value);
				return value;
			}
			else {
				return originalValue;
			}
		}
		return null;
	}

	private JRExpressionLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new JRExpressionLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}
