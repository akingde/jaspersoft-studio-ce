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
package com.jaspersoft.studio.editor.gef.rulers;

import org.eclipse.gef.commands.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class MoveGuideCommand.
 * 
 * @author Chicu Veaceslav
 */
public class MoveGuideCommand 
	extends Command 
{

/** The p delta. */
private int pDelta;

/** The guide. */
private ReportGuide guide;
	
/**
 * Instantiates a new move guide command.
 * 
 * @param guide
 *          the guide
 * @param positionDelta
 *          the position delta
 */
public MoveGuideCommand(ReportGuide guide, int positionDelta) {
	super("Move guide");
	this.guide = guide;
	pDelta = positionDelta;
}

/* (non-Javadoc)
 * @see org.eclipse.gef.commands.Command#execute()
 */
public void execute() {
//	guide.setPosition(guide.getPosition() + pDelta);
//	Iterator iter = guide.getParts().iterator();
//	while (iter.hasNext()) {
//		LogicSubpart part = (LogicSubpart)iter.next();
//		Point location = part.getLocation().getCopy();
//		if (guide.isHorizontal()) {
//			location.y += pDelta;
//		} else {
//			location.x += pDelta;
//		}
//		part.setLocation(location);
//	}
}

/* (non-Javadoc)
 * @see org.eclipse.gef.commands.Command#undo()
 */
public void undo() {
		// guide.setPosition(guide.getPosition() - pDelta);
		// Iterator iter = guide.getParts().iterator();
		// while (iter.hasNext()) {
		// LogicSubpart part = (LogicSubpart)iter.next();
		// Point location = part.getLocation().getCopy();
		// if (guide.isHorizontal()) {
		// location.y -= pDelta;
		// } else {
		// location.x -= pDelta;
		// }
		// part.setLocation(location);
		// }
}

}
