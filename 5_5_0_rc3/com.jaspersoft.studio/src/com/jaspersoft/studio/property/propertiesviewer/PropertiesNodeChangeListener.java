/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.property.propertiesviewer;

/**
 * Classes which implement this interface provide method that deals
 * with the events that are generated when the node selection inside 
 * a {@link TreePropertiesViewerPanel} widget is changed.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public interface PropertiesNodeChangeListener {

	/**
	 * Sent when a new {@link PropertiesViewerNode} element
	 * is selected in the tree.
	 * 
	 * @param node the new node that is currently selected
	 */
	void nodeChanged(IPropertiesViewerNode node);

}
