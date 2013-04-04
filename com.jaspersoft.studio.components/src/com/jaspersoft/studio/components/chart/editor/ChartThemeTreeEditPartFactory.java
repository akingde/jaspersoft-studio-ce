/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.editor;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import com.jaspersoft.studio.components.chart.editor.tree.ChartThemeContainerTreeEditPart;
import com.jaspersoft.studio.components.chart.editor.tree.ChartThemeTreeEditPart;
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
