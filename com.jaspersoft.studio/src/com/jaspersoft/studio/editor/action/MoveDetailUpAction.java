/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.band.MBandGroup;
import com.jaspersoft.studio.model.band.command.ReorderBandCommandByRelativeIndex;

import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.type.BandTypeEnum;

/**
 * Action to move a detail before the detail band above it it
 * 
 * @author Orlandin Marco
 * 
 */
public class MoveDetailUpAction extends ACachedSelectionAction implements IGlobalAction {

	/** The Constant ID. */
	public static final String ID = "move_detail_up"; //$NON-NLS-1$
	
	/**
	 * Comparator used to sort the selected bands to move in increasing order
	 * by their position as children
	 */
	private Comparator<Pair<Integer,MBand>> increasingComparator = new Comparator<Pair<Integer,MBand>>() {

		@Override
		public int compare(Pair<Integer,MBand> o1, Pair<Integer,MBand> o2) {
			return o1.getKey() - o2.getKey();
		}
	};

	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 */
	public MoveDetailUpAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	protected Command createCommand() {
		List<Object> bands = editor.getSelectionCache().getSelectionModelForType(MBand.class);
		if (bands.isEmpty()) return null;
		MBand firstBand = (MBand)bands.get(0);
		
		//check if all the bands have the same type
		if (!checkSameType(firstBand, bands)) return null;
	
		JSSCompoundCommand cmd = new JSSCompoundCommand(firstBand);
		cmd.enableSelectionRestore(true);
	
		//Check that all the bands can be moved
		List<Pair<Integer, MBand>> bandsToMove = new ArrayList<Pair<Integer, MBand>>();
		for(Object obj : bands){
			MBand bandNode = (MBand)obj;
			ANode parent = bandNode.getParent();
			if (parent != null){
				int index = parent.getChildren().indexOf(bandNode);
				//check that it is not the first children
				if (index == 0) return null;
				INode previousItem = parent.getChildren().get(index-1);
				if (previousItem instanceof MBand && bandNode.isSameBandType((MBand)previousItem)){
					bandsToMove.add(new Pair<Integer, MBand>(index, bandNode));
				} else {
					return null;
				}
			}
		}
		if (bandsToMove.size() > 0){
			final EditPartViewer currentViewer = firstBand.getFigureEditPart().getViewer();
			//sort the band to move to move them from the downer to the upper
			Collections.sort(bandsToMove, increasingComparator);
			for(Pair<Integer,MBand> band : bandsToMove){
				MBand bandNode = band.getValue();
				if ( bandNode.getBandType() == BandTypeEnum.GROUP_FOOTER || bandNode.getBandType() == BandTypeEnum.GROUP_HEADER){
					cmd.add(new ReorderBandCommandByRelativeIndex((MBandGroup) bandNode, -1));
				} else if (bandNode.getBandType() == BandTypeEnum.DETAIL) {
					cmd.add(new ReorderBandCommandByRelativeIndex(bandNode, (MReport) bandNode.getParent(), -1));
				} 
			}
			//Command to clear the selection
			if (!cmd.isEmpty()){
				cmd.add(new Command() {
					@Override
					public void execute() {
						ISelection currentSelection = currentViewer.getSelection();
						currentViewer.deselectAll();
						currentViewer.setSelection(currentSelection);
					}
				});
			}
		}
	
		return cmd.isEmpty() ? null : cmd;
	}
	
	
	/**
	 * Check if all the bands on the list have the same type
	 * 
	 * @param firstBand first band, used as reference for the check
	 * @param bands list to check
	 * @return true if all the bands on the list have the same type of the first band,
	 * false otherwise
	 */
	protected boolean checkSameType(MBand firstBand, List<?> bands){
		for(Object band : bands){
			MBand node = (MBand)band;
			if (!firstBand.isSameBandType(node)){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public boolean calculateEnabled() {
		return super.calculateEnabled();
	}
	
	/**
	 * Initializes this action's text and images.
	 */
	@Override
	protected void init() {
		super.init();
		setText(Messages.MoveDetailUpAction_actionName);
		setToolTipText(Messages.MoveDetailUpAction_actionDescription);
		setId(MoveDetailUpAction.ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/arrow-band-up.png")); //$NON-NLS-1$
		setEnabled(false);
	}

}
