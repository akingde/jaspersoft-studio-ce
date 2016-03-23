/*******************************************************************************
 * Copyright (c) 2014 Massimo Rabbi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
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
