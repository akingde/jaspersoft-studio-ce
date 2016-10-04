/*******************************************************************************
 * Copyright (C) 2005 - 2016 TIBCO Software Inc. All rights reserved.
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
package com.jaspersoft.studio.data.jdbc;

import java.util.List;

/**
 * Clients that want to provide a list of JDBC driver definitions should
 * implement this interface.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface JDBCDriverDefinitionsContainer {

	/**
	 * @return the list of JDBC driver definitions
	 */
	List<JDBCDriverDefinition> getJDBCDriverDefinitions();
	
}
