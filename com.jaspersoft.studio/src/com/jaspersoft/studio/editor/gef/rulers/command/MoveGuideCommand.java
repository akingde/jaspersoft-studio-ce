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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MoveGuideCommand.
 * 
 * @author Chicu Veaceslav
 */
public class MoveGuideCommand extends Command {
	private List<SetConstraintCommand> constraintCommands;
	/** The p delta. */
	private int pDelta;

	/** The guide. */
	private ReportRulerGuide guide;

	/**
	 * Instantiates a new move guide command.
	 * 
	 * @param guide
	 *          the guide
	 * @param positionDelta
	 *          the position delta
	 */
	public MoveGuideCommand(ReportRulerGuide guide, int positionDelta) {
		super(Messages.MoveGuideCommand_move_guide);
		this.guide = guide;
		pDelta = positionDelta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		if (constraintCommands == null) {
			constraintCommands = new ArrayList<SetConstraintCommand>();
			for (MGraphicElement part : guide.getParts()) {
				if (part.getParent() == null)
					continue;
				JRDesignElement jrE = (JRDesignElement) part.getValue();
				Point location = ModelUtils.getY4Element(part);
				if (guide.isHorizontal()) {
					location.y += pDelta;
				} else {
					location.x += pDelta;
				}
				SetConstraintCommand cc = new SetConstraintCommand();
				cc.setContext(null, part, new Rectangle(location.x, location.y, jrE.getWidth(), jrE.getHeight()));
				constraintCommands.add(cc);
			}
		}
		guide.setPosition(guide.getPosition() + pDelta);
		for (SetConstraintCommand c : constraintCommands)
			c.execute();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		guide.setPosition(guide.getPosition() - pDelta);
		for (SetConstraintCommand c : constraintCommands)
			c.undo();
	}

}
