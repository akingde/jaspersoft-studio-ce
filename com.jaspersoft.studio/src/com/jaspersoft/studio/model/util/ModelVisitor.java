package com.jaspersoft.studio.model.util;

import com.jaspersoft.studio.model.INode;

public abstract class ModelVisitor {

	public ModelVisitor(INode model) {
		iterate(model);
	}

	public void iterate(INode node) {
		for (INode n : node.getChildren()) {
			visit(n);
			iterate(n);
		}
	}

	public abstract void visit(INode n);

	private Object object;

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
