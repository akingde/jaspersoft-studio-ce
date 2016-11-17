/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.actions;

import java.util.Arrays;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerEditPart;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerFigure;
import com.jaspersoft.studio.messages.Messages;

public class CreateGuideAction extends Action {
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.rulers.CreateGuideAction";
	
	public static final int MIN_DISTANCE_BW_GUIDES = 5;

	/**
	 * The viewer of the ruler
	 */
	private EditPartViewer viewer;

	/**
	 * Constructor
	 * 
	 * @param ruler
	 *          the viewer for the ruler on which the guide is to be created
	 */
	public CreateGuideAction(EditPartViewer ruler) {
		super(Messages.CreateGuideAction_Label);
		viewer = ruler;
		setToolTipText(Messages.CreateGuideAction_Tooltip);
		setId(ID);
	}

	/**
	 * @see org.eclipse.jface.action.IAction#run()
	 */
	@Override
	public void run() {
		JDRulerEditPart rulerEditPart = (JDRulerEditPart) viewer.getRootEditPart().getChildren().get(0);
		RulerProvider provider = rulerEditPart.getRulerProvider();
		ReportRuler ruler = (ReportRuler)provider.getRuler();
		// Determine where the guide should be created
		int[] positions = provider.getGuidePositions();
		Arrays.sort(positions);
		int index = 0;
		int newPosition = MIN_DISTANCE_BW_GUIDES + 1;
		//get the position from the pointer on the ruler
		if (rulerEditPart.getMousePosition() != -1){
			newPosition = rulerEditPart.getMousePosition();
		} else {
			int desiredDifference = (MIN_DISTANCE_BW_GUIDES * 2) + 1;
			boolean found = positions.length > 0 && positions[0] > desiredDifference;
			while (index < positions.length - 1 && !found) {
				if (positions[index + 1] - positions[index] > desiredDifference) {
					newPosition += positions[index];
					found = true;
				}
				index++;
			}
			if (!found && positions.length > 0){
				newPosition += positions[positions.length - 1];
			}
		}
		PositionDialog dlg = new PositionDialog(UIUtils.getShell(), newPosition, provider.getUnit(), ruler.isHorizontal());
		if (dlg.open() == Window.OK){
			newPosition = dlg.getPixelPosition();
			JDRulerFigure rf = rulerEditPart.getRulerFigure();
			newPosition += rf.isHorizontal() ? rf.getHoffset() : rf.getVoffset();

			// Create the guide and reveal it
			viewer.getEditDomain().getCommandStack().execute(provider.getCreateGuideCommand(newPosition));
			viewer.reveal((EditPart) viewer.getEditPartRegistry().get(provider.getGuideAt(newPosition)));
		}
	}
}
