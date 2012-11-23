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

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;

public abstract class ASwitchAction extends Action {
	private MultiPageContainer container;
	protected String view;

	public ASwitchAction(MultiPageContainer container, String view) {
		super();
		this.container = container;
		this.view = view;
	}

	@Override
	public void run() {
		if (view != null) {
			container.switchView(view);
		}
	}
}
