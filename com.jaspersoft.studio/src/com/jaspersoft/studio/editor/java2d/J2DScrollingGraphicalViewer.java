/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.editor.java2d;

import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.gef.ui.parts.ScrollingGraphicalViewer;

import com.jaspersoft.studio.editor.gef.selection.JSelectionManager;
/*
 * The Class J2DScrollingGraphicalViewer.
 */
public class J2DScrollingGraphicalViewer extends ScrollingGraphicalViewer {

	public J2DScrollingGraphicalViewer(){
		super();
		setSelectionManager(new JSelectionManager());
	}
	
	/**
	 * Internally creates a J2DLightweightSystem.
	 * 
	 * @return the lightweight system
	 * @see org.eclipse.gef.ui.parts.GraphicalViewerImpl#createLightweightSystem()
	 */
	protected LightweightSystem createLightweightSystem() {
		return new J2DLightweightSystem();
	}

}
