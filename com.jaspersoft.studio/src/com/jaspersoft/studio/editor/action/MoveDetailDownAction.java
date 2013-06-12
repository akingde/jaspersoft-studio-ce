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

import net.sf.jasperreports.engine.JRBand;
import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.command.CreateBandDetailCommand;
import com.jaspersoft.studio.model.band.command.DeleteBandDetailCommand;

/**
 * Action to move a detail before the detail band above it it
 * 
 * @author Orlandin Marco
 *
 */
public class MoveDetailDownAction extends SelectionAction {

	/** The Constant ID. */
	public static final String ID = "move_detail_down"; //$NON-NLS-1$

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public MoveDetailDownAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Returns <code>true</code> if the selected objects is detail that can be moved down. false otherwise
	 * 
	 * @return if the command should be enabled
	 */
	protected boolean calculateEnabled() {
		List<APropertyNode> selection = getOperationSet();
		if (selection.size() == 1) {
			APropertyNode selectedNode = getOperationSet().get(0);
			if (selectedNode instanceof MBand && ((MBand)selectedNode).getBandType().equals(BandTypeEnum.DETAIL)){
				List<JRBand> detailsList = ((JRDesignSection)selectedNode.getJasperDesign().getDetailSection()).getBandsList(); 
				int index = detailsList.indexOf(selectedNode.getValue());
				if (index<detailsList.size()-1)return true;
			}
				
		}
		return false;
	}

	/**
	 * Return a list of every MBand with type Detail selected
	 * anyway the operation will be performed only on the first element of the list
	 * 
	 * @return a not null list of MBand with type Detail selected
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
				if (part.getModel() instanceof MBand && ((MBand)part.getModel()).getBandType().equals(BandTypeEnum.DETAIL))
					result.add((APropertyNode) part.getModel());
			}
		}
		return result;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		 APropertyNode bandNode = getOperationSet().get(0);
     // Remove the band
     CompoundCommand cmd = new CompoundCommand();
     DeleteBandDetailCommand deleteBand = new DeleteBandDetailCommand(bandNode.getParent(), (MBand)bandNode);
     cmd.add(deleteBand);
		 int index = ((JRDesignSection)bandNode.getJasperDesign().getDetailSection()).getBandsList().indexOf(bandNode.getValue());
		 CreateBandDetailCommand createBand = new CreateBandDetailCommand((MBand)bandNode, (MBand)bandNode,index+1);
		 cmd.add(createBand); 
		 execute(cmd);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(Messages.MoveDetailDownAction_actionName);
		setToolTipText(Messages.MoveDetailDownAction_actionDescription);
		setId(MoveDetailDownAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/arrow-curve-down.png")); //$NON-NLS-1$
		setEnabled(false);
	}

}
