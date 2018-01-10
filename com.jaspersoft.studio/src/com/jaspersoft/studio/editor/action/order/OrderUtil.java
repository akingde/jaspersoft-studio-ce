/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.order;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

public class OrderUtil {
	public static void reorderElements(List<Object> elements) {
		Collections.sort(elements, new Comparator<Object>() {

			@Override
			public int compare(Object o1, Object o2) {
				MGraphicElement ge1 = (MGraphicElement) o1;
				MGraphicElement ge2 = (MGraphicElement) o2;
				ANode p1 = ge1.getParent();
				ANode p2 = ge2.getParent();
				//null checks, avoid npe when deleting more elements at once
				if (p1 == null && p2 == null) return 0;
				else if (p1 == null) return 1;
				else if (p2 == null) return -1;
				if (p1.hashCode() < p2.hashCode())
					return -1;
				if (p1.hashCode() > p2.hashCode())
					return 1;
				if (p1.getChildren().indexOf(ge1) > p2.getChildren().indexOf(ge2))
					return 1;
				if (p1.getChildren().indexOf(ge1) < p2.getChildren().indexOf(ge2))
					return -1;
				return 0;
			}
		});
	}

	public static void reorderReverseElements(List<Object> elements) {
		reorderElements(elements);
		Collections.reverse(elements);
	}
}
