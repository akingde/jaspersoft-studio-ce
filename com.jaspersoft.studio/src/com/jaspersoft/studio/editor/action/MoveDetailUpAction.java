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

import net.sf.jasperreports.engine.design.JRDesignSection;
import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.jface.viewers.StructuredSelection;
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
public class MoveDetailUpAction extends SelectionAction implements IGlobalAction  {

	/** The Constant ID. */
	public static final String ID = "move_detail_up"; //$NON-NLS-1$

	/**
	 * Index of the edit part actually selected
	 */
	private int selectionIndex=0;
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public MoveDetailUpAction(IWorkbenchPart part) {
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
				int index = ((JRDesignSection)selectedNode.getJasperDesign().getDetailSection()).getBandsList().indexOf(selectedNode.getValue());
				if (index>0)return true;
			}
				
		}
		return false;
	}
	
	private void setSelection(EditPart parent, int selectionIndex){
		Object child = parent.getChildren().get(selectionIndex);
		EditPart part = (EditPart)child;
		if (part != null){
			StructuredSelection newselection = new StructuredSelection(part);
			setSelection(newselection);
			getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
		}
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
				if (part.getModel() instanceof MBand && ((MBand)part.getModel()).getBandType().equals(BandTypeEnum.DETAIL)){
					result.add((APropertyNode) part.getModel());
					selectionIndex = part.getParent().getChildren().indexOf(part);
					break;
				}
			}
		}
		return result;
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		@SuppressWarnings("unchecked")
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		EditPart selectionParent = ((EditPart)editparts.get(0)).getParent();
		APropertyNode node = getOperationSet().get(0);
    // Remove the band
    CompoundCommand cmd = new CompoundCommand();
    MBand bandNode = (MBand)node;
    DeleteBandDetailCommand deleteBand = new DeleteBandDetailCommand(bandNode.getParent(), bandNode);
    cmd.add(deleteBand);
		int index = ((JRDesignSection)bandNode.getJasperDesign().getDetailSection()).getBandsList().indexOf(bandNode.getValue());
		CreateBandDetailCommand createBand = new CreateBandDetailCommand((MBand)bandNode, (MBand)bandNode,index-1);
		cmd.add(createBand); 
		execute(cmd);
		setSelection(selectionParent,selectionIndex-1);
	}

	/**
	 * Initializes this action's text and images.
	 */
	protected void init() {
		super.init();
		setText(Messages.MoveDetailUpAction_actionName);
		setToolTipText(Messages.MoveDetailUpAction_actionDescription);
		setId(MoveDetailUpAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/arrow-curve-up.png")); //$NON-NLS-1$
		setEnabled(false);
	}

}
