/*
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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