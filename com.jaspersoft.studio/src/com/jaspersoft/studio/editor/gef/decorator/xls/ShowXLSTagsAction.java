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
package com.jaspersoft.studio.editor.gef.decorator.xls;

import org.eclipse.gef.GraphicalViewer;

import com.jaspersoft.studio.editor.action.snap.ACheckResourcePrefAction;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Tag in the viewer to display or not the XLS decorator
 * 
 * @author Orlandin Marco
 * 
 */
public class ShowXLSTagsAction extends ACheckResourcePrefAction {
	public static final String ID = "com.jaspersoft.studio.editor.gef.decorator.xls.ShowXLSTagsAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowXLSTagsAction(GraphicalViewer diagramViewer, JasperReportsConfiguration jrConfig) {
		super("Show XSL tags", jrConfig);
		setToolTipText("Show the XSL decorator tag");
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return ID;
	}
}
