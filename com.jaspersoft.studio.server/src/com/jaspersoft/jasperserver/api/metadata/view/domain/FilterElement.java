/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.jasperserver.api.metadata.view.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;


/**
 * Filter criterion interface.
 * 
 * @author Sherman Wood
 * @author Lucian Chirita
 * @version $Id: FilterElement.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @since 1.0
 * @see FilterElementCollection
 */
@JasperServerAPI
public interface FilterElement {

	/**
	 * Applies this criterion in a filter implementation.
	 * 
	 * @param filter the filter implementation
	 * @see Filter
	 */
	void apply(Filter filter);
	
	/**
	 * Creates a clone of the filter criterion.
	 * 
	 * @return a clone of this filter
	 * @since 3.5.0
	 */
	FilterElement cloneFilterElement();
	
}
