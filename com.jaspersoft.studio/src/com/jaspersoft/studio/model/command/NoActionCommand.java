/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
/*
 * The Class OrphanElementCommand.
 * 
 * @author Chicu Veaceslav
 */
public class NoActionCommand extends Command {

	/**
	 * Instantiates a new orphan element command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public NoActionCommand() {
		super(Messages.common_orphan_child);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {

	}

}
