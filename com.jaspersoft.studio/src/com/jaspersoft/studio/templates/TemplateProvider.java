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
package com.jaspersoft.studio.templates;

import java.util.List;

import com.jaspersoft.templates.TemplateBundle;


public interface TemplateProvider {

		/**
		 * Return a list of TemplateBundle that could be handled by this engine
		 */
		public List<TemplateBundle> getTemplateBundles();

		/**
		 * Return the key that identify this engine
		 */
		public String getProviderKey();
		
		/**
		 * return a human readable name for the engine
		 */
		public String getProviderName();
}
