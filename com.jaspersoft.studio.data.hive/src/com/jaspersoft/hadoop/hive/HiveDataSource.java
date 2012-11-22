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
package com.jaspersoft.hadoop.hive;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

/**
 * An implementation of a data source that runs a HiveQL query and uses the
 * result set
 * 
 * @author Eric Diaz
 * 
 */
public class HiveDataSource implements JRDataSource {
    private ResultSet resultSet;

    private Map<String, Integer> indexesMap;

    public static final String DRIVER_NAME = "org.apache.hadoop.hive.jdbc.HiveDriver";

    public static final String QUERY_LANGUAGE = "HiveQL";

    private final static Logger logger = Logger.getLogger(HiveDataSource.class);

    public HiveDataSource(ResultSet resultSet) throws ClassNotFoundException {
        this.resultSet = resultSet;
    }

    /**
     * Gets the field value for the current position.
     */
    public Object getFieldValue(JRField field) throws JRException {
        if (resultSet == null) {
            return null;
        }
        try {
            Integer index = indexesMap.get(field.getName());
            if (index == null) {
                throw new JRException("Unknown field \"" + field.getName() + "\"");
            }
            return resultSet.getObject(index);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new JRException(e.getMessage());
        }
    }

    /**
     * Tries to position the cursor on the next element in the data source.
     */
    public boolean next() throws JRException {
        if (resultSet == null) {
            return false;
        }
        boolean next = false;
        try {
            next = resultSet.next();
            if (!next) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (next && indexesMap == null) {
            getIndexesMap();
        }
        return next;
    }

    private void getIndexesMap() {
        indexesMap = new HashMap<String, Integer>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int limit = metaData.getColumnCount();
            for (int index = 1; index <= limit; index++) {
                indexesMap.put(metaData.getColumnName(index), index);
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Indexes Map: " + indexesMap);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getResultSet() {
        return resultSet;
    }
}
