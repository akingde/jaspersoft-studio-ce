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
package com.jaspersoft.studio.property.descriptor.expression;

import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.expression.dialog.JRExpressionEditor;

public class JRExpressionCellEditor extends DialogCellEditor {
	
	private ExpressionContext expContext;

	public JRExpressionCellEditor(Composite parent) {
		super(parent);
	}

	public JRExpressionCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		JRExpressionEditor wizard = new JRExpressionEditor();
		wizard.setValue((JRDesignExpression) getValue());
		wizard.setExpressionContext(expContext);
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			JRDesignExpression value = wizard.getValue();
			// updateContents(value);
			return value;
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
	
	public void setExpressionContext(ExpressionContext expContext){
		this.expContext=expContext;
	}
}
