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
package com.jaspersoft.studio.editor.action.order;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
/*/*
 * The Class BringToFrontAction.
 */
public class BringToFrontAction extends SelectionAction {

	/** The Constant ID. */
	public static final String ID = "bring_front"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public BringToFrontAction(IWorkbenchPart part) {
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

		CompoundCommand compoundCmd = new CompoundCommand("Bring To Front"); //$NON-NLS-1$
		int j = 0;
		for (int i = objects.size() - 1; i >= 0; i--) {
			EditPart part = (EditPart) objects.get(i);
			Command cmd = null;
			Object model = part.getModel();
			if (model instanceof MGraphicElement) {
				ANode parent = (ANode) ((MGraphicElement) model).getParent();
				if (parent != null) {
					int newIndex = parent.getChildren().size() - 1;
					if (parent.getChildren().indexOf(model) < parent.getChildren().size() - 1) {
						cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, newIndex - j);
						j++;
					} else
						return null;
					if (cmd != null)
						compoundCmd.add(cmd);
				}
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
		setText(Messages.BringToFrontAction_bring_to_front);
		setToolTipText(Messages.BringToFrontAction_bring_to_front_tool_tip);
		setId(BringToFrontAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/elcl16/bring_to_front.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin
				.getImageDescriptor("icons/resources/formatting/bring_to_front.gif")); //$NON-NLS-1$
		setEnabled(false);
	}
}
