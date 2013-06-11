/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.JRGroup;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.group.MGroup;
import com.jaspersoft.studio.model.group.command.CreateGroupCommand;
import com.jaspersoft.studio.model.group.command.DeleteGroupCommand;

/**
 * Action to move a group down the group after it
 * 
 * @author Orlandin Marco
 *
 */
public class MoveGroupUpAction extends SelectionAction {

	/** The Constant ID. */
	public static final String ID = "move_group_up"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public MoveGroupUpAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Returns <code>true</code> if the selected objects is a group that can be moved down. false otherwise
	 * 
	 * @return if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		List<APropertyNode> selection = getOperationSet();
		if (selection.size() == 1) {
			APropertyNode groupNode = getOperationSet().get(0);
			MGroup groupElement = null;
			if (groupNode instanceof MBandGroupHeader) {
				groupElement = ((MBandGroupHeader) groupNode).getMGroup();
			} else if (groupNode instanceof MBandGroupFooter) {
				groupElement = ((MBandGroupFooter) groupNode).getMGroup();
			}
			List<JRGroup> groupList = groupNode.getJasperDesign().getGroupsList();
			int index = groupList.indexOf(groupElement.getValue());
			if (index <= 0) return false;
			return true;
		}
		return false;
	}

	/**
	 * Return a list of every MBandGroupFooter or MBandGroupHeader selected
	 * anyway the operation will be performed only on the first element of the list
	 * 
	 * @return a not null list of MBandGroupHeader or MBandGroupFotter
	 */
	protected List<APropertyNode> getOperationSet() {
		@SuppressWarnings("unchecked")
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		if (editparts.isEmpty())
			return new ArrayList<APropertyNode>();
		List<APropertyNode> result = new ArrayList<APropertyNode>();
		for (Object element : editparts) {
			if (element instanceof EditPart){
				EditPart part = (EditPart) element;
				if (part.getModel() instanceof MBandGroupHeader || part.getModel() instanceof MBandGroupFooter)
					result.add((APropertyNode) ((EditPart) element).getModel());
			}
		}
		return result;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		
		 APropertyNode groupNode = getOperationSet().get(0);
     // Remove the group...

     CompoundCommand cmd = new CompoundCommand();
     MGroup groupElement = null;
		 if (groupNode instanceof MBandGroupHeader) {
				cmd.add(new DeleteGroupCommand((MReport) groupNode.getParent(), (MBandGroupHeader) groupNode));
				groupElement = ((MBandGroupHeader)groupNode).getMGroup();
			} else if (groupNode instanceof MBandGroupFooter) {
				cmd.add(new DeleteGroupCommand((MReport) groupNode.getParent(), (MBandGroupFooter) groupNode));
				groupElement = ((MBandGroupFooter)groupNode).getMGroup();
			}
			
			int index = groupNode.getJasperDesign().getGroupsList().indexOf(groupElement.getValue());
			cmd.add(new CreateGroupCommand((MReport) groupNode.getParent(), groupElement, index-1)); 
			execute(cmd);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(Messages.MoveGroupUpAction_actionName);
		setToolTipText(Messages.MoveGroupUpAction_actionDescription);
		setId(MoveGroupUpAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/arrow-curve-up.png")); //$NON-NLS-1$
		setEnabled(false);
	}

}
