/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.designer;


public abstract class AQueryStatus {
	protected String msg;
	protected Throwable t;

	public abstract void showError(final Throwable t);

	public abstract void showError(final String message, final Throwable t);

	public abstract void showWarning(final String msg);

	public abstract void showInfo(final String msg);

	protected void setMessage(final Throwable t, String message, boolean enabled) {
		this.t = t;
		msg = message;
	}

}
