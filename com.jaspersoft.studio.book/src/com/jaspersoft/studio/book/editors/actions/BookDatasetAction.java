/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors.actions;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.book.editors.JRBookDesignEditor;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.dialog.DatasetAction;

/**
 * Action to open the dataset and query dialog for a book report
 * 
 * @author Orlandin Marco
 *
 */
public class BookDatasetAction extends DatasetAction {

	public BookDatasetAction(IWorkbenchPart part) {
		super(part);
	}

	/**
	 * Return the dataset used by book report, that is always
	 * the main dataset.
	 */
	@Override
	protected MDataset getMDatasetToShow() {
		final JRBookDesignEditor part = (JRBookDesignEditor) getWorkbenchPart();
		if (part.getModel() != null && !part.getModel().getChildren().isEmpty()) {
			MReport mreport = (MReport) part.getModel().getChildren().get(0);
			// get report main dataset
			return (MDataset) mreport.getPropertyValue(JasperDesign.PROPERTY_MAIN_DATASET);
		}
		return null;
	}
	
}
