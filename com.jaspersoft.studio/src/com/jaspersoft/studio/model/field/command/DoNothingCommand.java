/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.field.command;

import org.eclipse.gef.commands.Command;

/*/*
 * The Class OrphanConditionalStyleCommand.
 * 
 * @author Chicu Veaceslav
 */
public class DoNothingCommand extends Command {

	/**
	 * Instantiates a new orphan conditional style command.
	 * 
	 * @param parent
	 *            the parent
	 * @param child
	 *            the child
	 */
	public DoNothingCommand() {
		super("");
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
