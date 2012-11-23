/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.messages.Messages;
/*
 * The Class CreateGuideCommand.
 * 
 * @author Chicu Veaceslav
 */
public class CreateGuideCommand extends Command {

	/** The guide. */
	private ReportRulerGuide guide;

	/** The parent. */
	private ReportRuler parent;

	/** The position. */
	private int position;

	/**
	 * Instantiates a new creates the guide command.
	 * 
	 * @param parent
	 *          the parent
	 * @param position
	 *          the position
	 */
	public CreateGuideCommand(ReportRuler parent, int position) {
		super(Messages.CreateGuideCommand_create_guide);
		this.parent = parent;
		this.position = position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (guide == null)
			guide = new ReportRulerGuide(!parent.isHorizontal());
		guide.setPosition(position);
		parent.addGuide(guide);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.removeGuide(guide);
	}

}
