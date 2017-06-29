/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.action;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.table.messages.Messages;

/*
 * The Class CreateGroupAction.
 */
public class CreateColumnEndAction extends CreateColumnAction {

	/** The Constant ID. */
	public static final String ID = "create_table_column"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *            The part for this action
	 */
	public CreateColumnEndAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.CreateColumnAction_create_column);
		setToolTipText(Messages.CreateColumnAction_create_column_tool_tip);
		setId(CreateColumnEndAction.ID);
		setImageDescriptor(Activator.getDefault().getImageDescriptor("icons/table-insert-column-end.png"));
		setEnabled(false);
	}

}
