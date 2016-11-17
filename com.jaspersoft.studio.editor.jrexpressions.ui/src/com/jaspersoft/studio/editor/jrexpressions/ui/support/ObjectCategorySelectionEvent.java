/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.jrexpressions.ui.support;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Widget;

import com.jaspersoft.studio.editor.jrexpressions.ui.support.ObjectCategoryItem.Category;
import com.jaspersoft.studio.editor.jrexpressions.ui.support.java.EditingAreaHelper;

/**
 * Instances of this class are sent as a result of
 * an {@link ObjectCategoryItem} being selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see ObjectCategorySelectionListener
 * @see EditingAreaHelper Example of events notication
 *
 */
public class ObjectCategorySelectionEvent extends TypedEvent {

	private static final long serialVersionUID = -4147730325947077155L;
	
	/** The selected category */
	public Category selectedCategory;

	public ObjectCategorySelectionEvent(Widget widget){
		super(widget);
	}

}
