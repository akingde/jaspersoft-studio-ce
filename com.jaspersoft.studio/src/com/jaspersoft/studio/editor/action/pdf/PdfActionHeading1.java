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
package com.jaspersoft.studio.editor.action.pdf;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.commands.ResizeCommand;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.SetValueCommand;

public class PdfActionHeading1 extends PdfActionAbstact {
	
	/** The Constant ID. */
	public static final String ID_Heading1_Full = "PdfAction_Heading1_Full"; //$NON-NLS-1$
	public static final String ID_Heading1_Start = "PdfAction_Heading1_Start"; //$NON-NLS-1$
	public static final String ID_Heading1_End = "PdfAction_Heading1_End"; //$NON-NLS-1$
	public static final String ID_Heading1_None = "PdfAction_Heading1_None"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionHeading1(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Heading1_Full, ID_Heading1_Start, ID_Heading1_End, ID_Heading1_None);
	}

	
	public Command createCommand(MGraphicElement model){
		SetValueCommand cmd = new SetValueCommand();
		cmd.setTarget(model);
		//Here miss some stuff, because first i want to solve the GeteSelectedObjects()'s problem
		return cmd;
	}
	
}
