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
package com.jaspersoft.studio.preferences.editor.table;

import org.eclipse.jface.viewers.ArrayContentProvider;

import com.jaspersoft.studio.preferences.editor.sorttable.model.ITableContentProvider;

public class TableContentProvider extends ArrayContentProvider implements ITableContentProvider {

	public Object getColumnValue(Object element, int columnIndex) {

		switch (columnIndex) {
		case 0: {
			return "BCDa";
		}
		case 1: {
			return "ABCD";
		}

		default: {
			return (element != null ? element.toString() : "");
		}
		}
	}
}
