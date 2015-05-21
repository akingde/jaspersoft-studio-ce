/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.util;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.gef.EditPart;

public abstract class EditPartVisitor<T> {

	public EditPartVisitor(EditPart model) {
		try {
			iterate(model);
		} catch (StopException e) {
		}
	}

	public void iterate(EditPart node) {
		if (node != null && node.getChildren() != null)
			for (Object obj : node.getChildren()) {
				if (obj instanceof EditPart) {
					EditPart n = (EditPart) obj;
					if (visit(n)) {
						iterate(n);
						postChildIteration(n);
					}
				}
			}
	}

	protected void postChildIteration(EditPart n) {

	}

	public abstract boolean visit(EditPart n);

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
