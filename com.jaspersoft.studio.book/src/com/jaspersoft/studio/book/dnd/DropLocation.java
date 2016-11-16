/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
