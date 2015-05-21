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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.model.ICopyable;

/**
 * Command used to copy the selected elements inside the editor into the clipboard
 */
public class CopyCommand extends Command {
	
	/**
	 * List of the copied elements
	 */
	protected List<ICopyable> list = new ArrayList<ICopyable>();

	/**
	 * Add an element to the list of the copied elements if it was not already inside
	 * 
	 * @return true if the element was added to the list, false otherwise
	 */
	public boolean addElement(ICopyable node) {
		if (!list.contains(node))
			return list.add(node);
		return false;
	}

	@Override
	public boolean canUndo() {
		return false;
	}

	/**
	 * Create the container for the paste of editor elements and
	 * put it inside the clipboard
	 */
	@Override
	public void execute() {
		if (canExecute()){
			PastableElements container = new PastableElements(list);
			Clipboard.getDefault().setContents(container);
		}
	}

	@Override
	public boolean canExecute() {
		return list != null && !list.isEmpty();
	}

}
