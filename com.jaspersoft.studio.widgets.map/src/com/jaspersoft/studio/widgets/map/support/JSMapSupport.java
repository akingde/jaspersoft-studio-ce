/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.support;

import org.eclipse.swt.browser.Browser;

/**
 * This interface should be implemented by clients who wants to provide the
 * facilities to interact with the map component on the Javascript side.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public interface JSMapSupport extends MapSupport {

	/**
	 * Performs a generic evaluation of the input javascript snippet.
	 * <p>
	 * 
	 * It wraps the {@link Browser#evaluate(String)} method.
	 * 
	 * @param snippet javascript code to be evaluated
	 * @return the result of the evaluation
	 */
	Object evaluateJavascript(String snippet);

}
