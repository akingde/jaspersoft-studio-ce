/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.celleditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;
import com.jaspersoft.studio.messages.Messages;

/**
 * Cell Editor for the <code>ItemDataList</code>
 * property
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class ItemListCellEditor extends DialogCellEditor {

	private ItemLabelProvider labelProvider;
	private ADescriptor descriptor;

	public ItemListCellEditor(Composite parent, ADescriptor descriptor) {
		super(parent);
		this.descriptor = descriptor;
	}

	public ItemListCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		MessageDialog.openInformation(UIUtils.getShell(),
				Messages.ItemListCellEditor_0, Messages.ItemListCellEditor_1);
		return getValue();
	}

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		if (labelProvider == null)
			labelProvider = new ItemLabelProvider(descriptor);
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
