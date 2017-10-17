/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.actions;

import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.window.Window;

import com.jaspersoft.studio.editor.gef.rulers.ReportRuler;
import com.jaspersoft.studio.editor.gef.rulers.component.JDGuideEditPart;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerEditPart;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerFigure;
import com.jaspersoft.studio.messages.Messages;

/**
 * Action used to edit the position of a guide
 * 
 * @author Orlandin Marco
 *
 */
public class EditGuideAction extends Action {
	
	/**
	 * The viewer of the ruler
	 */
	private EditPartViewer viewer;
	
	/**
	 * Id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.rulers.EditGuideAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param ruler the viewer for the ruler on which the guide is to be created.
	 */
	public EditGuideAction(EditPartViewer ruler) {
		super(Messages.EditGuideAction_editText);
		viewer = ruler;
		setToolTipText(Messages.EditGuideAction_editTooltip);
		setId(ID);
	}

	/**
	 * Get the selected edit part and open the dialog to modify its position
	 */
	@Override
	public void run() {
		JDRulerEditPart rulerEditPart = (JDRulerEditPart) viewer.getRootEditPart().getChildren().get(0);
		RulerProvider provider = rulerEditPart.getRulerProvider();
		ReportRuler ruler = (ReportRuler)provider.getRuler();
		
		List<?> parts = viewer.getSelectedEditParts();
		if (!parts.isEmpty() && parts.get(0) instanceof JDGuideEditPart){
			JDGuideEditPart editedPart = (JDGuideEditPart)parts.get(0);
			int oldPosition = provider.getGuidePosition(editedPart.getModel());
			JDRulerFigure rf = rulerEditPart.getRulerFigure();
			int offest = rf.isHorizontal() ? rf.getHoffset() : rf.getVoffset();
			PositionDialog dlg = new PositionDialog(UIUtils.getShell(), oldPosition - offest, provider.getUnit(), ruler.isHorizontal());
			if (dlg.open() == Window.OK){
				int newPosition = dlg.getPixelPosition() + offest;
				// Create the guide and reveal it
				viewer.getEditDomain().getCommandStack().execute(provider.getMoveGuideCommand(editedPart.getModel(), newPosition - oldPosition));
				viewer.reveal(editedPart);
			}	
		}
	}
}
