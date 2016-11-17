/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.copy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;

import com.jaspersoft.studio.model.CopyElementExpressionProperty;
import com.jaspersoft.studio.model.ICopyable;

/**
 * Implementation of the paste definition for the editor graphical
 * objects. This can be used to copy\paste properties of an element
 * 
 * @author Orlandin Marco
 *
 */
public class PastableProperties extends AbstractPastableObject{

	/**
	 * Create an instance of the class
	 * 
	 * @param list not null list of the properties that are object of the copy. The content 
	 * must be of type CopyElementExpressionProperty
	 */
	public PastableProperties(List<ICopyable> list) {
		super(list);
	}

	@Override
	public Command getPasteCommand(Collection<?> targets) {
		CompoundCommand cc = new CompoundCommand();
		for(Object obj : list){
			CopyElementExpressionProperty property = (CopyElementExpressionProperty)obj;
			cc.add(property.getPasteCommand(targets));
		}
		return cc;
	}
	
	/**
	 * Return a not null list of the copied properties, it can contains both expression
	 * properties or normal properties
	 */
	public List<CopyElementExpressionProperty> getCopiedProperties(){
		List<CopyElementExpressionProperty> result = new ArrayList<CopyElementExpressionProperty>();
		for(Object obj : list){
			CopyElementExpressionProperty property = (CopyElementExpressionProperty)obj;
			result.add(property);
		}
		return result;
	}

}
