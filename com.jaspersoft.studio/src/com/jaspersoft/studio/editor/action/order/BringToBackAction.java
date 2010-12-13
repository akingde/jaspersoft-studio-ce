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
package com.jaspersoft.studio.editor.action.order;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

// TODO: Auto-generated Javadoc
/**
 * The Class BringToBackAction.
 */
public class BringToBackAction extends SelectionAction {
	
	/** The Constant ID. */
	public static final String ID = "bring_back"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public BringToBackAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	/**
	 * Returns <code>true</code> if the selected objects can be created. Returns <code>false</code> if there are no
	 * objects selected or the selected objects are not {@link EditPart}s.
	 * 
	 * @return if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		Command cmd = createReorderCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}

	/**
	 * Create a command to create the selected objects.
	 * 
	 * @param objects
	 *          The objects to be deleted.
	 * @return The command to remove the selected objects.
	 */
	public Command createReorderCommand(List<?> objects) {
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;

		CompoundCommand compoundCmd = new CompoundCommand("Bring To Back"); //$NON-NLS-1$
		int j = 0;
		for (int i = 0; i < objects.size(); i++) {
			EditPart part = (EditPart) objects.get(i);
			Command cmd = null;
			Object model = part.getModel();
			if (model instanceof MGraphicElement) {
				ANode parent = (ANode) ((MGraphicElement) model).getParent();
				if (parent.getChildren().indexOf(model) > 0) {
					cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, j);
					j++;
				} else
					return null;
				if (cmd != null)
					compoundCmd.add(cmd);
			}
		}
		return compoundCmd;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		execute(createReorderCommand(getSelectedObjects()));
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(Messages.BringToBackAction_send_to_back);
		setToolTipText(Messages.BringToBackAction_send_to_back_tool_tip);
		setId(BringToBackAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/elcl16/send_to_back.gif")); //$NON-NLS-1$
		setEnabled(false);
	}
}
