/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import org.eclipse.gef.EditPart;

import com.jaspersoft.studio.book.editparts.BookPagesEditPart;
import com.jaspersoft.studio.book.editparts.BookReportEditPart;
import com.jaspersoft.studio.book.editparts.BookSectionEditPart;
import com.jaspersoft.studio.book.model.MBookReport;
import com.jaspersoft.studio.book.model.MReportPart;
import com.jaspersoft.studio.book.model.MReportPartContainer;
import com.jaspersoft.studio.editor.AEditPartFactory;

public class BookEditPartFactory extends AEditPartFactory {

	@Override
	protected EditPart createEditPart(Object model) {
		if (model instanceof MReportPart){
			return new BookPagesEditPart();
		} else if (model instanceof MReportPartContainer){
			return new BookSectionEditPart();
		} else if (model instanceof MBookReport){
			return new BookReportEditPart();
		}
		return null;
	}

}
