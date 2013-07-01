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
package com.jaspersoft.studio.data.sql.action;

import com.jaspersoft.studio.data.sql.SQLQueryDesigner;
import com.jaspersoft.studio.model.ANode;

public abstract class AMultiSelectionAction extends AAction {

	public AMultiSelectionAction(String text, SQLQueryDesigner designer) {
		super(text, designer);
	}

	@Override
	public boolean calculateEnabled(Object[] selection) {
		super.calculateEnabled(selection);
		if (selection == null)
			return false;
		else {
			for (Object s : selection) {
				s = convertObject(s);
				if (s == null)
					return false;
				if (!isGoodNode((ANode) s))
					return false;
			}
		}
		return true;
	}

	protected ANode convertObject(Object obj) {
		if (obj instanceof ANode)
			return (ANode) obj;
		return null;
	}

	protected abstract boolean isGoodNode(ANode element);
}
