package com.jaspersoft.studio.data.sql;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Point;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class Util {
	public static int getFirstOffsetOfKeyword(EObject object) {
		int offset = -1;
		if (object != null) {
			ICompositeNode node = NodeModelUtils.getNode(object);
			if (node != null)
				return node.getLastChild().getTotalEndOffset();
		}
		return offset;
	}

	public static Point getPosition(EObject object) {
		Point offset = null;
		if (object != null) {
			ICompositeNode node = NodeModelUtils.getNode(object);
			if (node != null) {
				INode lastChild = node.getLastChild();
				return new Point(node.getLastChild().getOffset(), lastChild.getTotalEndOffset());
			}
		}
		return offset;
	}
}
