/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyDescriptor;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Cell editor for the chart customizers in the advanced properties view. It doesn't allow
 * to edit them directly but the button must be pressed to open the edit dialog
 * 
 * @author Orlandin Marco
 *
 */
public class ChartCustomizerCellEditor extends EditableDialogCellEditor {

	public ChartCustomizerCellEditor(Composite parent) {
		super(parent);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		CustomizerPropertyExpressionsDTO value = (CustomizerPropertyExpressionsDTO)getValue();
		PropertyExpressionsDTO dto = null;
		if (value != null){
			ChartCustomizerDialog dialog = new ChartCustomizerDialog(UIUtils.getShell(), value.getPnode(), value);
			if (dialog.open() == Dialog.OK){
				dto = dialog.getDTO();
			}
		}
		return dto;
	}

	@Override
	protected void updateContents(Object value) {
		String text = CustomizerPropertyDescriptor.labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
