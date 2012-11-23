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
package com.jaspersoft.studio.editor.preview.actions;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.MultiPageContainer;

public class ViewExporterAction extends ASwitchAction {
	public ViewExporterAction(MultiPageContainer container) {
		super(container, "Exporter Parameters");
		setImageDescriptor(JaspersoftStudioPlugin.getImageDescriptor("icons/resources/equalizer--arrow.png")); //$NON-NLS-1$
		setToolTipText("Set exporter parameters");
	}
}
