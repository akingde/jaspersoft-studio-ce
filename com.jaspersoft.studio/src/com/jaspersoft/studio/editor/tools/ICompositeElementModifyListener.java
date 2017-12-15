/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

/**
 * Interface to define a listener called when the custom composite elements set is modified
 * 
 * @author Orlandin Marco
 *
 */
public interface ICompositeElementModifyListener {

	/**
	 * The type of operation that has modified the composite elements set
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public enum OPERATION_TYPE{DELETE, ADD, EDIT};
	
	/**
	 * Method called to notify that something in the composite elements set is changed
	 * 
	 * @param oldElement the old element, on a delete this is the deleted element, on 
	 * and add it is null and on an edit is the element before the edit
	 * @param newElement the new element, on a delete this is null, on 
	 * and add it is the created element and on an edit is the element after the edit
	 * @param operation define if the composite element was added, removed or edited
	 */
	public void elementChanged(MCompositeElement oldElement, MCompositeElement newElement, OPERATION_TYPE operation);
	
	
}
