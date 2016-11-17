/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.Clipboard;

import com.jaspersoft.studio.editor.action.copy.PastableElements.ACTION_TYPE;
import com.jaspersoft.studio.model.ANode;
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
	 * Create a map of the parents of each copied node. This is done
	 * because with the cut action the nodes are removed and could be impossible
	 * to recover the current parent, so the parent is saved when the node 
	 * is copied
	 * 
	 * @return a not null map with the parents of every copied node 
	 */
	protected HashMap<ICopyable, ANode> getParentsMap(){
		HashMap<ICopyable, ANode> parentsMap = new HashMap<ICopyable, ANode>();
		for(ICopyable node : list){
			if (node instanceof ANode){
				parentsMap.put(node, ((ANode)node).getParent());
			}
		}
		return parentsMap;
	}
	
	/**
	 * Create the container for the paste of editor elements and
	 * put it inside the clipboard
	 */
	@Override
	public void execute() {
		if (canExecute()){
			PastableElements container = new PastableElements(list, getParentsMap(), ACTION_TYPE.COPY);
			Clipboard.getDefault().setContents(container);
		}
	}

	@Override
	public boolean canExecute() {
		return list != null && !list.isEmpty();
	}
}
