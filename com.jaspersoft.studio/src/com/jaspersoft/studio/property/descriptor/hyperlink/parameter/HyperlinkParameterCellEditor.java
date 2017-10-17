/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.hyperlink.parameter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.expression.IExpressionContextSetter;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.parameter.GenericParameterLabelProvider;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterEditor;

import net.sf.jasperreports.engine.JRHyperlinkParameter;

public class HyperlinkParameterCellEditor extends EditableDialogCellEditor implements IExpressionContextSetter{
	private ExpressionContext expContext;

	public HyperlinkParameterCellEditor(Composite parent) {
		super(parent);
	}

	public HyperlinkParameterCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		ParameterEditor wizard = new ParameterEditor();
		JRHyperlinkParameter[] value = (JRHyperlinkParameter[]) getValue();
		wizard.setValue(GenericJSSParameter.convertFrom(value));
		wizard.setExpressionContext(expContext);
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return GenericJSSParameter.convertToHyperlink(wizard.getValue());
		}
		return null;
	}

	private LabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new GenericParameterLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext=expContext;
	}
}
