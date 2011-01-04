package com.jaspersoft.studio.model.util;

import com.jaspersoft.studio.model.INode;

public class ModelUtil {
	public static INode getNode(final Object obj, INode parent) {
		ModelVisitor mv = new ModelVisitor(parent) {

			@Override
			public void visit(INode n) {
				if (n.getValue() == obj)
					setObject(n);
			}
		};
		return (INode) mv.getObject();
	}
}
