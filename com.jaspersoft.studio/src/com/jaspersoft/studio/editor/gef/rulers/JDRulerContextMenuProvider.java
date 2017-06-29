/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.rulers;

import java.util.List;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.jface.action.IMenuManager;

import com.jaspersoft.studio.editor.gef.rulers.actions.CreateGuideAction;
import com.jaspersoft.studio.editor.gef.rulers.actions.DeleteGuideAction;
import com.jaspersoft.studio.editor.gef.rulers.actions.EditGuideAction;
import com.jaspersoft.studio.editor.gef.rulers.actions.SwitchUnitAction;
import com.jaspersoft.studio.editor.gef.rulers.component.JDGuideEditPart;
import com.jaspersoft.studio.editor.gef.rulers.component.JDRulerEditPart;

/**
 * Provide the contextual menu to manage the ruler
 * 
 * @author Orlandin Marco
 *
 */
public class JDRulerContextMenuProvider extends ContextMenuProvider {
	public JDRulerContextMenuProvider(EditPartViewer viewer) {
		super(viewer);
	}

	/**
	 * Create the contextual menu. The action to create
	 * a guide is always present. If the right click is done on a guide
	 * then there will be also the action to edit or delete the guide. Instead
	 * if the click is done on the ruler show the option to change the measure unit
	 */
	@Override
	public void buildContextMenu(IMenuManager menu) {
		GEFActionConstants.addStandardActionGroups(menu);
		//Change the menu depending on the selected part
		List<?> parts = getViewer().getSelectedEditParts();
		if (!parts.isEmpty() && parts.get(0) instanceof JDGuideEditPart){
			//A guide is selected, give the edit and delete option
			menu.add(new EditGuideAction(getViewer()));
			menu.add(new DeleteGuideAction(getViewer()));
		} 
		menu.add(new CreateGuideAction(getViewer()));
		if (!parts.isEmpty() && parts.get(0) instanceof JDRulerEditPart){
			//the ruler is selected, return the switch unit option
			menu.add(new SwitchUnitAction(getViewer()));
		}
	}

}
