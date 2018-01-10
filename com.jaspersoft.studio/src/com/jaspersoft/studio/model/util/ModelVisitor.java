/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.INode;

public abstract class ModelVisitor<T> {

	public ModelVisitor(INode model) {
		try {
			iterate(model);
		} catch (StopException e) {
			// nothing to do here, is just a way to stop the visitor
		}
	}

	public void iterate(INode node) {
		if (node != null && node.getChildren() != null)
			for (INode n : node.getChildren()) {
				if (visit(n)) {
					iterate(n);
					postChildIteration(n);
				}
			}
	}

	protected void postChildIteration(INode n) {

	}

	public abstract boolean visit(INode n);

	private T object;

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public void stop() {
		throw new StopException();
	}

	private static class StopException extends RuntimeException {
		public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

		public StopException() {
			super();
		}
	}
}
