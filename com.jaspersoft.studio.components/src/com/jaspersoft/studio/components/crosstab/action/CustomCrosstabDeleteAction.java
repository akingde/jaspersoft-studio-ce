/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.action;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.crosstab.model.MDatasetGroupNode;
import com.jaspersoft.studio.components.crosstab.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroup;
import com.jaspersoft.studio.editor.action.CustomDeleteAction;
import com.jaspersoft.studio.model.ANode;

/**
 * Custom crosstab delete action, it is disabled when every elements in a crosstab group is
 * selected, this because a row group/column group/measure can not be empty
 */
public class CustomCrosstabDeleteAction extends CustomDeleteAction {

	public CustomCrosstabDeleteAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	protected boolean calculateEnabled() {
		List<?> selectedObjects = getSelectedObjects();
		if (!checkValidSelection(selectedObjects)) return false;
		Command cmd = createDeleteCommand(getSelectedObjects());
		if (cmd == null)
			return false;
		return cmd.canExecute();
	}
	
	protected boolean checkValidSelection(List<?> selectedObjects){
		List<?> selectedObject = super.getSelectedObjects();
		List<ANode> selectedMesures = new ArrayList<ANode>();
		List<ANode> selectedRowGroups = new ArrayList<ANode>();
		List<ANode> selectedColumnGrops = new ArrayList<ANode>();
		ANode measuresNode = null;
		ANode rowGroupsNode = null;
		ANode columnGroupsNode = null;
		for(Object obj : selectedObject){
			if (!(obj instanceof EditPart)){
				return false;
			}
		}
		for(Object obj : selectedObject){
			EditPart selectedPart = (EditPart)obj;
			Object model = selectedPart.getModel();
			if (model instanceof MDatasetGroupNode){
				if (model instanceof MRowGroup){
					selectedRowGroups.add((MRowGroup)model);
					if (rowGroupsNode == null){
						rowGroupsNode = ((MRowGroup)model).getParent();
					}
				} else if (model instanceof MColumnGroup){
					selectedColumnGrops.add((MColumnGroup)model);
					if (columnGroupsNode == null){
						columnGroupsNode = ((MColumnGroup)model).getParent();
					}
				} else {
					selectedMesures.add((ANode)model);
					if (measuresNode == null){
						measuresNode = ((ANode)model).getParent();
					}
				}
			}
			if (rowGroupsNode != null && rowGroupsNode.getChildren().size() == selectedRowGroups.size()) return false;
			else if (columnGroupsNode != null && columnGroupsNode.getChildren().size()== selectedColumnGrops.size()) return false;
			else if (measuresNode != null && measuresNode.getChildren().size()== selectedMesures.size()) return false;
		}
		return true;
	}
}
