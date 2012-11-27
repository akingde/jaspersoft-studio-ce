/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.order;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.action.IGlobalAction;
import com.jaspersoft.studio.editor.outline.OutlineTreeEditPartFactory;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/*
 * /* The Class BringForwardAction.
 */
public class BringForwardAction extends SelectionAction implements IGlobalAction {

	/** The Constant ID. */
	public static final String ID = "bring_forward"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public BringForwardAction(IWorkbenchPart part) {
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

		CompoundCommand compoundCmd = new CompoundCommand("Bring Forward"); //$NON-NLS-1$
		for (int i = objects.size() - 1; i >= 0; i--) {
			EditPart part = (EditPart) objects.get(i);
			Command cmd = null;
			Object model = part.getModel();
			if (model instanceof MGraphicElement) {
				ANode parent = (ANode) ((MGraphicElement) model).getParent();
				if (parent != null && parent.getChildren() != null) {
					int newIndex = parent.getChildren().indexOf(model) + 1;
					if (newIndex < parent.getChildren().size()) {
						cmd = OutlineTreeEditPartFactory.getReorderCommand((ANode) model, parent, newIndex);
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
		setText(Messages.BringForwardAction_bring_forward);
		setToolTipText(Messages.BringForwardAction_bring_forward_tool_tip);
		setId(BringForwardAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/elcl16/bring_forward.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/eclipseapps/dlcl16/bring_forward.gif")); //$NON-NLS-1$
		setEnabled(false);
	}
}
