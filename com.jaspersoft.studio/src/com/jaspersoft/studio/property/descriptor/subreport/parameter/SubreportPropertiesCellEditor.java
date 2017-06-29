/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.parameter;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog.SubreportParameterEditor;

import net.sf.jasperreports.engine.JRSubreportParameter;

public class SubreportPropertiesCellEditor extends EditableDialogCellEditor {

	private MSubreport msubreport;
	
	public SubreportPropertiesCellEditor(Composite parent) {
		super(parent);
	}

	public SubreportPropertiesCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	public void init(MSubreport msubreport) {
		this.msubreport = msubreport;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		SubreportParameterEditor wizard = new SubreportParameterEditor(msubreport);
		JRSubreportParameter[] values = (JRSubreportParameter[]) getValue();
		wizard.setValue(GenericJSSParameter.convertFrom(values));
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return GenericJSSParameter.convertToSubreport(wizard.getValue());
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
			labelProvider = new SubreportPropertiesLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
