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

import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jaspersoft.mongodb.connection.MongoDbConnection;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id$
 * @author Eric Diaz
 */
public class MongoDbDataAdapterService extends AbstractDataAdapterService {
    private static final Log log = LogFactory.getLog(MongoDbDataAdapterService.class);

    private MongoDbConnection connection;

    private MongoDbDataAdapter dataAdapter;

    public MongoDbDataAdapterService(MongoDbDataAdapter dataAdapter) {
        this.dataAdapter = dataAdapter;
    }

    @Override
    public void contributeParameters(Map<String, Object> parameters) throws JRException {
        if (connection != null) {
            dispose();
        }
        if (dataAdapter != null) {
            try {
                createConnection();
                parameters.put(JRParameter.REPORT_CONNECTION, connection);
            } catch (Exception e) {
                throw new JRException(e);
            }
        }
    }

    private void createConnection() throws JRException {
        connection = new MongoDbConnection(dataAdapter.getMongoURI(), dataAdapter.getUsername(),
                dataAdapter.getPassword());
    }

    @Override
    public void dispose() {
        try {
            if (connection != null)
                connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (log.isErrorEnabled())
                log.error("Error while closing the connection.", e);
        }
    }

    @Override
    public void test() throws JRException {
        try {
            if (connection != null) {
            } else {
                createConnection();
            }
            connection.test();
        } finally {
            dispose();
        }
    }
}
