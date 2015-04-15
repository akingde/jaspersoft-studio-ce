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
package com.jaspersoft.studio.book.dnd;

import com.jaspersoft.studio.book.editparts.BookPagesEditPart;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;

/**
 * Describe a generic drop location inside a container
 * 
 * @author Orlandin Marco
 *
 */
public class DropLocation {

	/**
	 * Container where the element is dropped
	 */
	private BookSectionEditPart container;
	
	/**
	 * After which element the dropped element should be placed, null if it should
	 * be placed as first element
	 */
	private BookPagesEditPart afterPart;
	
	/**
	 * Create the drop location
	 * 
	 * @param container Container where the element is dropped
	 * @param afterPart After which element the dropped element should be placed, null if it should
	 * be placed as first element
	 */
	public DropLocation(BookSectionEditPart container, BookPagesEditPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
	}

	/**
	 * Return the container where the element should be placed
	 * 
	 * @return Container where the element is dropped or null if 
	 * there is not a valid container at the moment
	 */
	public BookSectionEditPart getContainer() {
		return container;
	}

	/**
	 * Return  After which element the dropped element should be placed
	 * 
	 * @return After which element the dropped element should be placed, null if it should
	 * be placed as first element
	 */
	public BookPagesEditPart getAfterPart() {
		return afterPart;
	}
	
	/**
	 * Invalid the current drop location
	 */
	public void reset(){
		container = null;
		afterPart = null;
	}
	
	/**
	 * Set the current drop location
	 * 
	 * @param container Container where the element is dropped
	 * @param afterPart After which element the dropped element should be placed, null if it should
	 * be placed as first element
	 */
	public void set(BookSectionEditPart container, BookPagesEditPart afterPart){
		this.container = container;
		this.afterPart = afterPart;
	}
	
}
