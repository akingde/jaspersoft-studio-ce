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
package com.jaspersoft.mongodb.adapter;

import net.sf.jasperreports.data.DataAdapter;

/**
 * @author Eric Diaz
 */
public interface MongoDbDataAdapter extends DataAdapter {
    public void setMongoURI(String mongoURI);

    public String getMongoURI();

    public void setUsername(String username);

    public String getUsername();

    public void setPassword(String password);

    public String getPassword();
}
