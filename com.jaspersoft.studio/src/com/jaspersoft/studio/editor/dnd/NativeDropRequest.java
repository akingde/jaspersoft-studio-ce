/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.dnd;

import org.eclipse.gef.Request;
/*/*
 * The Class NativeDropRequest.
 */
public class NativeDropRequest extends Request {

	/** The data. */
	private Object data;

	/** The Constant ID. */
	public static final String ID = "$Native Drop Request";//$NON-NLS-1$

	/**
	 * Instantiates a new native drop request.
	 */
	public NativeDropRequest() {
		super(ID);
	}

	/**
	 * Instantiates a new native drop request.
	 * 
	 * @param type
	 *          the type
	 */
	public NativeDropRequest(Object type) {
		super(type);
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data
	 *          the new data
	 */
	public void setData(Object data) {
		this.data = data;
	}

}
