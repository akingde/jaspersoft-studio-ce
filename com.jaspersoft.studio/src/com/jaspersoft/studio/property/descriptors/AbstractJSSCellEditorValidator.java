/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.jface.viewers.ICellEditorValidator;

import com.jaspersoft.studio.model.APropertyNode;

/**
 * This class want to be a validator for property node but with the possibility to set the target
 * of the validation in any moment
 * 
 * @author Orlandin Marco
 *
 */
public abstract class AbstractJSSCellEditorValidator implements ICellEditorValidator {

	/**
	 * Target node of the validation
	 */
	protected APropertyNode targetNode;
	
	/**
	 * Set the target
	 * @param target the new target
	 */
	public void setTargetNode(APropertyNode target){
		this.targetNode = target;
	}
	
	/**
	 * return the target
	 * @return the actual target
	 */
	public APropertyNode getTarget(){
		return targetNode;
	}
	
	
}
