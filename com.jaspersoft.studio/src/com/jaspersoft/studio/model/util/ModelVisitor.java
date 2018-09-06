/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import net.sf.jasperreports.engine.JRConstants;

import org.apache.commons.lang.StringUtils;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;

public abstract class ModelVisitor<T> {

	public ModelVisitor(INode model) {
		try {
			iterate(model);
		} catch (StopException e) {
			// nothing to do here, is just a way to stop the visitor
		}
	}

	protected int level = 0;

	public void iterate(INode node) {
		if (node != null && node.getChildren() != null) {
			level++;
			for (INode n : node.getChildren()) {
				if (visit(n)) {
					iterate(n);
					postChildIteration(n);
				}
			}
			level--;
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

	public static void printModel(ANode node) {
		StringBuilder sb = new StringBuilder();
		INode r = node.getRoot();
		if (r == null) {
			sb.append("Root: null\n");
			do {
				r = node;
				node = node.getParent();
			} while (node != null);
		}
		sb.append(r.getDisplayText()).append(" [" + r + "]\n");
		new ModelVisitor<String>(r) {

			@Override
			public boolean visit(INode n) {
				sb.append(StringUtils.repeat(" ", level)).append(n.getDisplayText()).append(" [" + n + "]\n");
				return true;
			}
		};
		System.out.println("\n" + sb.toString());
	}
}
