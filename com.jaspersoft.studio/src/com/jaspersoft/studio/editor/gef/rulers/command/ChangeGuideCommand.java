/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.rulers.command;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.editor.gef.rulers.ReportRulerGuide;
import com.jaspersoft.studio.model.IGuidebleElement;
/*
 * @author Veaceslav Chicu
 */
public class ChangeGuideCommand extends Command {

	private IGuidebleElement part;
	private ReportRulerGuide oldGuide, newGuide;
	private int oldAlign, newAlign;
	private boolean horizontal;

	public ChangeGuideCommand(IGuidebleElement part, boolean horizontalGuide) {
		super();
		this.part = part;
		horizontal = horizontalGuide;
	}

	protected void changeGuide(ReportRulerGuide oldGuide, ReportRulerGuide newGuide, int newAlignment) {
		if (oldGuide != null && oldGuide != newGuide) {
			oldGuide.detachPart(part);
		}
		// You need to re-attach the part even if the oldGuide and the newGuide are the same
		// because the alignment could have changed
		if (newGuide != null) {
			newGuide.attachPart(part, newAlignment);
		}
	}

	public void execute() {
		// Cache the old values
		oldGuide = horizontal ? part.getHorizontalGuide() : part.getVerticalGuide();
		if (oldGuide != null)
			oldAlign = oldGuide.getAlignment(part);

		redo();
	}

	public void redo() {
		changeGuide(oldGuide, newGuide, newAlign);
	}

	public void setNewGuide(ReportRulerGuide guide, int alignment) {
		newGuide = guide;
		newAlign = alignment;
	}

	public void undo() {
		changeGuide(newGuide, oldGuide, oldAlign);
	}

}
