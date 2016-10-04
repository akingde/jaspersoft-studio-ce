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
