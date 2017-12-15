/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.IGuidebleElement;
/*
 * The Class DeleteGuideCommand.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteGuideCommand extends Command {

	/** The parent. */
	private ReportRuler parent;

	/** The guide. */
	private ReportRulerGuide guide;

	/** The old parts. */
	private Map<IGuidebleElement, Integer> oldParts;

	/**
	 * Instantiates a new delete guide command.
	 * 
	 * @param guide
	 *          the guide
	 * @param parent
	 *          the parent
	 */
	public DeleteGuideCommand(ReportRulerGuide guide, ReportRuler parent) {
		super(Messages.DeleteGuideCommand_delete_guide);
		this.guide = guide;
		this.parent = parent;
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
		oldParts = new HashMap<IGuidebleElement, Integer>(guide.getMap());
		for (IGuidebleElement part : guide.getParts()) {
			guide.detachPart(part);
		}
		parent.removeGuide(guide);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		parent.addGuide(guide);
		for (IGuidebleElement part : guide.getParts()) {
			guide.attachPart(part, (oldParts.get(part)).intValue());
		}
	}
}
