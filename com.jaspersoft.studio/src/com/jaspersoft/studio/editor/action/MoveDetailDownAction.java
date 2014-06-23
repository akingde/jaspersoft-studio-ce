/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.type.BandTypeEnum;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;
import com.jaspersoft.studio.model.band.command.ReorderBandCommand;

/**
 * Action to move a detail before the detail band above it it
 * 
 * @author Orlandin Marco
 * 
 */
public class MoveDetailDownAction extends SetWorkbenchAction implements IGlobalAction {

	/** The Constant ID. */
	public static final String ID = "move_detail_down"; //$NON-NLS-1$

	/**
	 * Index of the edit part actually selected
	 */
	private int selectionIndex = 0;

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
	protected boolean calculateEnabled(List<?> editparts) {
		List<APropertyNode> selection = getOperationSet(editparts);
		if (selection.size() == 1) {
			APropertyNode selectedNode = getOperationSet(editparts).get(0);
			if (selectedNode instanceof MBand) {
				MBand mband = (MBand) selectedNode;
				if (mband.getBandType() == BandTypeEnum.DETAIL || mband.getBandType() == BandTypeEnum.GROUP_HEADER
						|| mband.getBandType() == BandTypeEnum.GROUP_FOOTER) {
					List<INode> pchildren = selectedNode.getParent().getChildren();
					int prevInd = pchildren.indexOf(selectedNode) + 1;
					if (prevInd < pchildren.size()) {
						INode prev = pchildren.get(prevInd);
						return prev instanceof MBand && mband.isSameBandType((MBand) prev);
					}
				}
			}
		}
		return false;
	}
	
	public boolean calculateEnabled(ISelection selection){
		if (selection instanceof IStructuredSelection)
			return calculateEnabled(((IStructuredSelection) selection).toList());
		return false;
	}
	
	@Override
	protected boolean calculateEnabled() {
		return calculateEnabled(getSelectedObjects());
	}

	/**
	 * Return a list of every MBand with type Detail selected anyway the operation will be performed only on the first
	 * element of the list
	 * 
	 * @return a not null list of MBand with type Detail selected
	 */
	protected List<APropertyNode> getOperationSet(List<?> editparts) {
		if (editparts.isEmpty())
			return new ArrayList<APropertyNode>();
		List<APropertyNode> result = new ArrayList<APropertyNode>();
		for (Object element : editparts) {
			if (element instanceof EditPart) {
				EditPart part = (EditPart) element;
				if (part.getModel() instanceof MBand && MBand.isMultiBand((MBand) part.getModel())) {
					result.add((APropertyNode) part.getModel());
					if (part.getParent() != null && part.getParent().getChildren() != null)
						selectionIndex = part.getParent().getChildren().indexOf(part);
					break;
				}
			}
		}
		return result;
	}

	private void setSelection(EditPart parent, int selectionIndex) {
		Object child = parent.getChildren().get(selectionIndex);
		EditPart part = (EditPart) child;
		if (part != null) {
			StructuredSelection newselection = new StructuredSelection(part);
			setSelection(newselection);
			getWorkbenchPart().getSite().getSelectionProvider().setSelection(newselection);
		}
	}
	
	public void execute(ISelection selection){
		if (selection instanceof IStructuredSelection)
			execute(((IStructuredSelection) selection).toList());
	}
	
	public void execute(List<?> editparts){
		EditPart selectionParent = ((EditPart) editparts.get(0)).getParent();

		APropertyNode node = getOperationSet(editparts).get(0);
		// Remove the band
		MBand bandNode = (MBand) node;
		JSSCompoundCommand cmd = new JSSCompoundCommand(bandNode);

		List<INode> pchildren = bandNode.getParent().getChildren();
		int offset = 0;
		for (INode n : pchildren) {
			if (n instanceof MBand && ((MBand) n).isSameBandType(bandNode))
				break;
			offset++;
		}
		int location = pchildren.indexOf(bandNode) + 1 - offset;

		if (bandNode instanceof MBandGroupFooter)
			cmd.add(new ReorderBandCommand((MBandGroupFooter) bandNode, location));
		else if (bandNode instanceof MBandGroupHeader)
			cmd.add(new ReorderBandCommand((MBandGroupHeader) bandNode, location));
		else if (bandNode instanceof MBand && bandNode.getBandType() == BandTypeEnum.DETAIL)
			cmd.add(new ReorderBandCommand(bandNode, (MReport) bandNode.getParent(), location));

		// DeleteBandDetailCommand deleteBand = new DeleteBandDetailCommand(bandNode.getParent(), (MBand) bandNode);
		// cmd.add(deleteBand);
		// int index = ((JRDesignSection) bandNode.getJasperDesign().getDetailSection()).getBandsList().indexOf(
		// bandNode.getValue());
		// CreateBandDetailCommand createBand = new CreateBandDetailCommand((MBand) bandNode, (MBand) bandNode, index + 1);
		// cmd.add(createBand);
		execute(cmd);
		setSelection(selectionParent, selectionIndex + 1);
	}

	/**
	 * Performs the create action on the selected objects.
	 */
	public void run() {
		@SuppressWarnings("unchecked")
		List<?> editparts = new ArrayList<Object>(getSelectedObjects());
		execute(editparts);
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
