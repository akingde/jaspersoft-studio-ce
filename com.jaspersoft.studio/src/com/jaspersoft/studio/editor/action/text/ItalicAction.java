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
package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.model.text.MTextElement;

public class ItalicAction extends ABooleanPropertyAction {
	public static String ID = "com.jaspersoft.studio.editor.action.text.italic";

	public ItalicAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
	}

	protected boolean checkSelection(Object obj) {
		return obj instanceof MTextElement;
	}

	@Override
	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_ITALIC;
	}
}
