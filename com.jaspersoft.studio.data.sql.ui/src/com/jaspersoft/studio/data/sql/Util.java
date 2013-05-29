package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Point;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class Util {
	public static int getFirstOffsetOfKeyword(EObject object) {
		if (object != null) {
			ICompositeNode node = NodeModelUtils.getNode(object);
			if (node != null)
				return node.getLastChild().getTotalEndOffset();
		}
		return 0;
	}

	public static Point getPosition(EObject object) {
		if (object != null) {
			ICompositeNode node = NodeModelUtils.getNode(object);
			if (node != null) {
				INode lc = node.getLastChild();
				return new Point(lc.getOffset(), lc.getTotalEndOffset());
			}
		}
		return new Point(0, 0);
	}
}
