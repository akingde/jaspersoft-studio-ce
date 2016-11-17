/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers.actions;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.rulers.RulerProvider;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.editor.gef.rulers.component.JDGuideEditPart;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerEditPart;

/**
 * Action to delete a guide edit part 
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteGuideAction extends Action {
	
	/**
	 * The viewer of the ruler
	 */
	private EditPartViewer viewer;
	
	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.rulers.DeleteGuideAction";

	/**
	 * Constructor
	 * 
	 * @param ruler the viewer for the ruler on which the guide is to be created
	 */
	public DeleteGuideAction(EditPartViewer ruler) {
		super("Delete Guide");
		viewer = ruler;
		setToolTipText("Delete Guide");
		setId(ID);
	}

	/**
	 * Get the selected guide edit part and delete it
	 */
	@Override
	public void run() {
		JDRulerEditPart rulerEditPart = (JDRulerEditPart) viewer.getRootEditPart().getChildren().get(0);
		RulerProvider provider = rulerEditPart.getRulerProvider();

		List<?> parts = viewer.getSelectedEditParts();
		if (!parts.isEmpty() && parts.get(0) instanceof JDGuideEditPart){
			JDGuideEditPart editedPart = (JDGuideEditPart)parts.get(0);
			viewer.getEditDomain().getCommandStack().execute(provider.getDeleteGuideCommand(editedPart.getModel()));
		}
	}
}
