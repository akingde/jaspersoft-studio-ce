/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.swt.events;

import java.util.List;

/**
 * Classes which implement this interface usually deal with the palette
 * modification events manipulating the new updated colors available.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public interface PaletteListener {

	/**
	 * Notification method invoked when a new list of colors is available.
	 * 
	 * @param newHexColors
	 *            the new colors that belong to the palette.
	 */
	void paletteModified(List<String> newHexColors);
	
}
