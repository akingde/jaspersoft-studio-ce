/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;
import java.util.Map;

import org.eclipse.gef.EditPart;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;
import com.jaspersoft.studio.model.INode;

/*
 * The Class CreateGroupAction.
 */
public class CreateColumnAfterAction extends CreateColumnAction {

	/** The Constant ID. */
	public static final String ID = "create_table_column_after"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateColumnAfterAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnAfterAction_title);
		setToolTipText(Messages.CreateColumnAfterAction_desc);
		setId(CreateColumnAfterAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor(
				"icons/table-insert-column-after.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(Activator.getDefault().getImageDescriptor(
				"icons/table-insert-column-after.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	protected boolean setExtendedData(Map<Object, Object> map, List<?> objects) {
		super.setExtendedData(map, objects);
		Object obj = objects.get(0);
		if (obj instanceof EditPart) {
			Object model = ((EditPart) obj).getModel();
			if (model instanceof MColumn) {
				MColumn columnModel = (MColumn)model;
				if (columnModel.getParent() != null) {
					List<INode> children = columnModel.getParent().getChildren();
					int index = children.indexOf(columnModel);
					map.put(CreateRequestUtil.NEWINDEX, index + 1);
				}
			} else
				return false;
		}
		return true;
	}
}
