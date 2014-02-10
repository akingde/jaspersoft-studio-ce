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
package com.jaspersoft.translation.resources;

import java.io.File;

/**
 * Interface implemented to provide an action to publish a translation
 * 
 * @author Orlandin Marco
 *
 */
public interface ISendTranslation {

	/**
	 * Method that must be implemented to provide a publish translation action, trough an
	 * extension point. Who implement this method receive a java.io.File that point to a 
	 * zip file with inside all the files of the project of the translation to publish
	 * and must return the result of the publish
	 * 
	 * @param translationArchive java.io.File that point to a zip file with inside all the files of the project 
	 * of the translation to publish. The file is created into a temp. directory
	 * 
	 * @return true if the publish was done, false otherwise
	 */
	public boolean sendTranslation(File translationArchive);
	
}
