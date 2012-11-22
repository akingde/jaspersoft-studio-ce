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
package com.jaspersoft.studio.components.table.model.column;

import java.util.Comparator;

import com.jaspersoft.studio.model.INode;

public final class MColumnComparator implements Comparator<INode> {
	private static MColumnComparator inst = new MColumnComparator();

	public static MColumnComparator inst() {
		if (inst == null)
			inst = new MColumnComparator();
		return inst;
	}

	@Override
	public int compare(INode o1, INode o2) {
		if (o1 instanceof MColumn && o2 instanceof MColumn && o1 != null
				&& o2 != null)
			return ((MColumn) o1).getName().compareTo(((MColumn) o2).getName());
		return 0;
	}
}
