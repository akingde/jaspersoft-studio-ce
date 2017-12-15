/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.jobs;

/**
 * Clients that implements this interface are supposed to get notified 
 * when a generic abort/cancel operation occurs.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface AbortOperationListener {

	/**
	 * Notifies that an abort operation is occurred.
	 */
	void abortOperationOccured();
	
}
