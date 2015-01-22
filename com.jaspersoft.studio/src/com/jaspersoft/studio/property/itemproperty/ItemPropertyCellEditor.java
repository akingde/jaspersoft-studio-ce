/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty;

import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.JSSDialogCellEditor;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemPropertyElementDialog;

public class ItemPropertyCellEditor extends JSSDialogCellEditor {
	private String id;
	private ExpressionContext expContext;

	public ItemPropertyCellEditor(Composite parent, ExpressionContext expContext, String id) {
		super(parent, true);
		this.expContext = expContext;
		this.id = id;
	}

	public ItemPropertyCellEditor(Composite parent, int style, ExpressionContext expContext) {
		super(parent, style, true);
		this.expContext = expContext;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		StandardItemProperty sip = (StandardItemProperty) getValue();
		if (sip == null)
			sip = new StandardItemProperty(id, null, null);
		ItemPropertyElementDialog dialog = new ItemPropertyElementDialog(UIUtils.getShell(), sip);
		dialog.setExpressionContext(expContext);
		if (dialog.open() == Dialog.OK)
			return dialog.getElementName();
		return null;
	}

	private ItemPropertyLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		if (labelProvider == null)
			labelProvider = new ItemPropertyLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}
