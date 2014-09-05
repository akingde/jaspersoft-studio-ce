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

package com.jaspersoft.jasperserver.api.metadata.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * Folder is the interface which represents the folder in the JasperServer. It extends
 * {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource}
 *
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: Folder.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @see com.jaspersoft.jasperserver.api.metadata.common.domain.client.FolderImpl
 */
@JasperServerAPI
public interface Folder extends Resource {
	static final String SEPARATOR = "/";
	static final int SEPARATOR_LENGTH = SEPARATOR.length();
}
