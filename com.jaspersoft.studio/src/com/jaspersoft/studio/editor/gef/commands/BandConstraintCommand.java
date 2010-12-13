/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.commands;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class BandConstraintCommand.
 */
public class BandConstraintCommand extends Command {
	
	/** The edit part. */
	private GraphicalEditPart editPart;
	
	/** The old bounds. */
	private Rectangle newBounds, oldBounds;

	/**
	 * Instantiates a new band constraint command.
	 * 
	 * @param editPart
	 *          the edit part
	 * @param constraint
	 *          the constraint
	 */
	public BandConstraintCommand(GraphicalEditPart editPart,
			Rectangle constraint) {
		this.editPart = editPart;
		this.newBounds = constraint;
		this.oldBounds = new Rectangle(editPart.getFigure().getBounds());
		this.newBounds.setSize( oldBounds.getSize());
		setLabel(Messages.BandConstraintCommand_band_resized);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		redo();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#redo()
	 */
	public void redo() {
		((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(
				editPart, editPart.getFigure(), newBounds);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		((GraphicalEditPart) editPart.getParent()).setLayoutConstraint(
				editPart, editPart.getFigure(), oldBounds);
	}
}