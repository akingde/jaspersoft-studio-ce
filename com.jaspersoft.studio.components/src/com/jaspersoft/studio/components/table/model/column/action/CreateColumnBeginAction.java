/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.editor.gef.util.CreateRequestUtil;

/*
 * The Class CreateGroupAction.
 */
public class CreateColumnBeginAction extends CreateColumnAction {

	/** The Constant ID. */
	public static final String ID = "create_table_column_begin"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateColumnBeginAction(IWorkbenchPart part) {
		super(part);
	}
	
	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnBeginAction_title);
		setToolTipText(Messages.CreateColumnBeginAction_desc);
		setId(CreateColumnBeginAction.ID);
		setImageDescriptor(
				Activator.getDefault().getImageDescriptor("icons/table-insert-column-begin.png")); //$NON-NLS-1$
		setEnabled(false);
	}

	@Override
	protected boolean setExtendedData(Map<Object, Object> map, List<?> objects) {
		super.setExtendedData(map, objects);
		map.put(CreateRequestUtil.NEWINDEX, 0);
		return true;
	}
}
