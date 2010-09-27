/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.rulers.command;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.model.MGraphicElement;

/**
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
	private Map<MGraphicElement, Integer> oldParts;

	/**
	 * Instantiates a new delete guide command.
	 * 
	 * @param guide
	 *          the guide
	 * @param parent
	 *          the parent
	 */
	public DeleteGuideCommand(ReportRulerGuide guide, ReportRuler parent) {
		super("delete guide");
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
		oldParts = new HashMap<MGraphicElement, Integer>(guide.getMap());
		for (MGraphicElement part : guide.getParts()) {
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
		for (MGraphicElement part : guide.getParts()) {
			guide.attachPart(part, ( oldParts.get(part)).intValue());
		}
	}
}
