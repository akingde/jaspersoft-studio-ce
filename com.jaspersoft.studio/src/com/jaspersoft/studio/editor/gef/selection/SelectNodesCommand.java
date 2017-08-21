/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.util.Pair;

/**
 * Command used to modify the selection of the current editor
 * 
 * @author Orlandin Marco
 *
 */
public class SelectNodesCommand extends Command {

	/**
	 * The element to select
	 */
	private List<ANode> elementsToSelect;
	
	/**
	 * The old selection, stored during the execute to allow the undo
	 */
	private Pair<ISelection, EditPartViewer> oldSelection = null;
	
	/**
	 * Create the command
	 * 
	 * @param elementsToSelect List of the JRDesignElement to select on the command execution
	 */
	public SelectNodesCommand(List<ANode> elementsToSelect){
		this.elementsToSelect = elementsToSelect;
	}
	
	/**
	 * Create the command
	 * 
	 * @param elementsToSelect List of the JRDesignElement to select on the command execution
	 */
	public SelectNodesCommand(ANode elementToSelect){
		this.elementsToSelect = new ArrayList<ANode>();
		elementsToSelect.add(elementToSelect);
	}
	
	
	public void execute() {	
		if (!elementsToSelect.isEmpty()){
			oldSelection = SelectionHelper.setNodeSelection(elementsToSelect, false);
		}
	};
	
	@Override
	public boolean canExecute() {
		return elementsToSelect != null;
	}
	
	@Override
	public void undo() {	
		if (oldSelection != null){
			oldSelection.getValue().setSelection(oldSelection.getKey());
		}
	}
	
}
