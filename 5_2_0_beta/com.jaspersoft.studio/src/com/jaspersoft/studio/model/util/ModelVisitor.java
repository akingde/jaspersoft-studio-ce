/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.model.INode;

public abstract class ModelVisitor<T> {

	public ModelVisitor(INode model) {
		try {
			iterate(model);
		} catch (StopException e) {
		}
	}

	public void iterate(INode node) {
		for (INode n : node.getChildren()) {
			if (visit(n))
				iterate(n);
		}
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
