/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.editor.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.jaspersoft.studio.model.IContainerEditPart;

/*
 * A factory for creating OutlineTreeEditPart objects.
 */
public class ChartThemeTreeEditPartFactory implements EditPartFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.gef.EditPartFactory#createEditPart(org.eclipse.gef.EditPart,
	 * java.lang.Object)
	 */
	public EditPart createEditPart(EditPart context, Object model) {
		EditPart editPart = null;
		if (model instanceof IContainerEditPart)
			editPart = new ChartThemeContainerTreeEditPart();
		else
			editPart = new ChartThemeTreeEditPart();
		if (editPart != null)
			editPart.setModel(model);
		return editPart;
	}

}
