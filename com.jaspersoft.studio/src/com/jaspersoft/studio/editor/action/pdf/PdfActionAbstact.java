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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.geometry.PrecisionRectangle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.AlignmentRequest;
import org.eclipse.gef.requests.GroupRequest;
import org.eclipse.gef.tools.ToolUtilities;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.gef.commands.AlignCommand;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.MGraphicElement;


/*
 * The Class PdfActionAbstract implements common action (most UI related) of the 
 * pdf action
 */
public abstract class PdfActionAbstact extends SelectionAction {

	/** The Constant ID. */
	public final String ID_Full; //$NON-NLS-1$
	public final String ID_Start; //$NON-NLS-1$
	public final String ID_End;
	public final String ID_None; //$NON-NLS-1$
	
	protected Position action_position;

	public 	PdfActionAbstact(IWorkbenchPart part,Position action_position, String ID_Full, String ID_Start, String ID_End, String ID_None){
		super(part);
		this.action_position = action_position;
		this.ID_Full = ID_Full;
		this.ID_Start = ID_Start;
		this.ID_End = ID_End;
		this.ID_None = ID_None;
		initUI();
	}
	
	protected void initUI() {
		switch (action_position) {
		case Full:
			setId(ID_Full);
			setText(Messages.PdfAction_Full);
			setToolTipText(null);
			setImageDescriptor(null); //$NON-NLS-1$
			setDisabledImageDescriptor(null); //$NON-NLS-1$
			break;

		case Start:
			setId(ID_Start);
			setText(Messages.PdfAction_Start);
			setToolTipText(null);
			setImageDescriptor(null); //$NON-NLS-1$
			setDisabledImageDescriptor(null); //$NON-NLS-1$
			break;

		case End:
			setId(ID_End);
			setText(Messages.PdfAction_End);
			setToolTipText(null);
			setImageDescriptor(null); //$NON-NLS-1$
			setDisabledImageDescriptor(null); //$NON-NLS-1$
			break;
		case None:
			setId(ID_None);
			setText(Messages.PdfAction_None);
			setToolTipText(null);
			setImageDescriptor(null); //$NON-NLS-1$
			setDisabledImageDescriptor(null); //$NON-NLS-1$
			break;
		}
	}
	
	public abstract Command createCommand(MGraphicElement model);  
	
	
		/**
	 * Returns the list of editparts which will participate in PDF Editing.
	 * 
	 * @return the list of parts which will be aligned
	 */
	private Command createAlignmentCommand() {
		List<?> editparts = getSelectedObjects();
		if (editparts.isEmpty() || !(editparts.get(0) instanceof GraphicalEditPart)){
			System.out.println("void list");
			return null;
		}
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		for (int i = 0; i < editparts.size(); i++) {
			EditPart editpart = (EditPart) editparts.get(i);
			if (editpart.getModel() instanceof MGraphicElement)
				command.add(createCommand((MGraphicElement)editpart.getModel()));
		}
		return command;
	}
	

	
	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		execute(createAlignmentCommand());
	}
	
	@Override
	protected boolean calculateEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
