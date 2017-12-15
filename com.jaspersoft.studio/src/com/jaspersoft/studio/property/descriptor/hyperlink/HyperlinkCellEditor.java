/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.hyperlink;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

public class HyperlinkCellEditor extends EditableDialogCellEditor {

	public HyperlinkCellEditor(Composite parent) {
		super(parent);
	}

	public HyperlinkCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {

		return null;
	}

	private HyperlinkLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new HyperlinkLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
