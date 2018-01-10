/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
