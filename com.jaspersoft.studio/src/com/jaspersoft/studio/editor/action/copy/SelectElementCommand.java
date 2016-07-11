/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.List;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.viewers.ISelection;

import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.util.Pair;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * Command used to modify the selection of the current editor
 * 
 * @author Orlandin Marco
 *
 */
public class SelectElementCommand extends Command {

	/**
	 * The element to select
	 */
	private List<JRDesignElement> elementsToSelect;
	
	/**
	 * The old selection, stored during the execute to allow the undo
	 */
	private Pair<ISelection, EditPartViewer> oldSelection = null;
	
	/**
	 * Create the command
	 * 
	 * @param elementsToSelect List of the JRDesignElement to select on the command execution
	 */
	public SelectElementCommand(List<JRDesignElement> elementsToSelect){
		this.elementsToSelect = elementsToSelect;
	}
	
	public void execute() {	
		if (!elementsToSelect.isEmpty()){
			oldSelection = SelectionHelper.setSelection(elementsToSelect, false);
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
