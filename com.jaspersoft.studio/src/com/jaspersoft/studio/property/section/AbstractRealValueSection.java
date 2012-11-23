/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.property.section;

import com.jaspersoft.studio.model.APropertyNode;

	/**
	 * Override the original refresh method to retrieve from the items it's attributes
	 * actual values, independently if it's inherited of real. Every section that want paint into 
	 * the widget the actual value and avoid the inherited values should extend this section instead
	 * of AbstractSection 
	 * @author Orlandin Marco
	 *
	 */
	abstract public class AbstractRealValueSection extends AbstractSection {
		public void refresh() {
			isRefreshing = true;
			APropertyNode element = getElement();
			if (element != null) {
				element.getPropertyDescriptors();
				for (Object key : widgets.keySet()) {
					widgets.get(key).setData(element, element.getPropertyActualValue(key));
				}
			}
			isRefreshing = false;
		}
}
