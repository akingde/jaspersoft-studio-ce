/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.Collection;
import java.util.List;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ICopyable;

/**
 * In jaspersoft studio there are different type of element that are copyable. Someone
 * maybe can be design element, but also the properties of an element can be copied.
 * This class is the one that it is implemented for each one of this types. Since the 
 * copy of a property is different from the copy of a graphic element, so it will need
 * a specific command to execute the operation. Because of this the implementation must
 * define the method that provide the command to paste the copied elements on the specific
 * targets
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractPastableObject {

	/**
	 * List of copied elements
	 */
	protected List<ICopyable> list;
	
	/**
	 * Create an instance of the class
	 * 
	 * @param list not null list of the elements that are object of the copy
	 */
	public AbstractPastableObject(List<ICopyable> list){
		this.list = list;
	}
	
	/**
	 * Return the list of the elements that were copied
	 * 
	 * @return the copied elements
	 */
	public Collection<ICopyable> getCopiedElements(){
		return list;
	}
	
	/**
	 * Return the command that will be executed to have the copied elements
	 * pasted on the targets
	 * 
	 * @param targets the target for the paste operation
	 * @return a command, can be null if the paste operation can't be executed
	 */
	public abstract Command getPasteCommand(Collection<?> targets);
}
