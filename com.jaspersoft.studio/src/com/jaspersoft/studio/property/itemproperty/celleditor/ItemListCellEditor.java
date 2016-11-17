/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.celleditor;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataListDialog;
import com.jaspersoft.studio.property.itemproperty.label.ItemLabelProvider;

import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.util.JRCloneUtils;

/**
 * Cell Editor for the <code>ItemDataList</code> property
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class ItemListCellEditor extends EditableDialogCellEditor {

	private ItemLabelProvider labelProvider;
	private ADescriptor descriptor;
	private ExpressionContext expContext;
	private AItemDataListPropertyDescriptor pd;
	private APropertyNode pNode;

	public ItemListCellEditor(Composite parent, ExpressionContext expContext, ADescriptor descriptor,
			AItemDataListPropertyDescriptor pd, APropertyNode pNode) {
		super(parent);
		this.descriptor = descriptor;
		this.expContext = expContext;
		this.pd = pd;
		this.pNode = pNode;
	}

	public ItemListCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		List<ItemData> v = JRCloneUtils.cloneList((List<ItemData>) getValue());
		if (v == null)
			v = new ArrayList<ItemData>();
		ItemDataListDialog dialog = new ItemDataListDialog(UIUtils.getShell(), descriptor, v, pd, pNode);
		dialog.setExpressionContext(expContext);
		if (dialog.open() == Dialog.OK)
			return dialog.getValue();
		return null;
	}

	public void setLabelProvider(ItemLabelProvider labelProvider) {
		this.labelProvider = labelProvider;
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

	public void setPNode(APropertyNode pNode) {
		this.pNode = pNode;
	}

	public void setExpressionContext(ExpressionContext expContext) {
		this.expContext = expContext;
	}
}
