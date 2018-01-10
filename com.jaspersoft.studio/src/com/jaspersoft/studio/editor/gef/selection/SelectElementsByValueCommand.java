/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.selection;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import com.jaspersoft.studio.utils.SelectionHelper;

/**
 * Command used to modify the selection of the current editor
 * 
 * @author Orlandin Marco
 *
 */
public class SelectElementsByValueCommand extends Command {

	/**
	 * The element to select
	 */
	private List<Object> elementsToSelect;
	
	private EditPartViewer viewer;
	
	/**
	 * The old selection, stored during the execute to allow the undo
	 */
	private ISelection oldSelection = null;
	
	/**
	 * Create the command
	 * 
	 * @param elementsToSelect List of the JRDesignElement to select on the command execution
	 */
	public SelectElementsByValueCommand(List<Object> elementsToSelect, EditPartViewer viewer){
		this.elementsToSelect = elementsToSelect;
		this.viewer = viewer;
	}
	
	/**
	 * Create the command
	 * 
	 * @param elementsToSelect List of the JRDesignElement to select on the command execution
	 */
	public SelectElementsByValueCommand(Object elementToSelect, EditPartViewer viewer){
		this.elementsToSelect = new ArrayList<Object>();
		elementsToSelect.add(elementToSelect);
		this.viewer = viewer;
	}
	
	public void execute() {	
		if (!elementsToSelect.isEmpty()){
			oldSelection = viewer.getSelection();
			List<EditPart> editParstToSelect = new ArrayList<EditPart>();
			for(Object element : elementsToSelect){
				EditPart elementPart = SelectionHelper.getEditPartByValue(element, viewer);
				if (elementPart != null){
					editParstToSelect.add(elementPart);
				}
			}
			if (!editParstToSelect.isEmpty()){
				viewer.setSelection(new StructuredSelection(editParstToSelect));
				viewer.reveal(editParstToSelect.get(0));
			}
		}
	};
	
	@Override
	public boolean canExecute() {
		return elementsToSelect != null && viewer != null;
	}
	
	@Override
	public void undo() {	
		if (oldSelection != null){
			viewer.setSelection(oldSelection);
		}
	}
	
}
