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
package com.jaspersoft.mongodb;

import java.util.Iterator;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.log4j.Logger;

import com.jaspersoft.mongodb.query.MongoDbQueryWrapper;
import com.mongodb.DBObject;

/**
 * An implementation of a data source that uses an empty query and parameters
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbDataSource implements JRDataSource {
    private MongoDbQueryWrapper wrapper;

    private DBObject currentDbObject;

    public static final String QUERY_LANGUAGE = "MongoDbQuery";

    private static final Logger logger = Logger.getLogger(MongoDbDataSource.class);

    private boolean hasIterator = false;

    private boolean hasCommandResult = false;

    private Iterator<?> resultsIterator;

    private Map<?, ?> currentResult;

    public MongoDbDataSource(MongoDbQueryWrapper wrapper) {
        logger.info("New MongoDB Data Source");
        this.wrapper = wrapper;
        hasIterator = wrapper.iterator != null;
        if (!hasIterator) {
            hasCommandResult = wrapper.commandResults != null;
            resultsIterator = wrapper.commandResults.iterator();
        }
    }

    /**
     * Gets the field value for the current position.
     */
    @Override
    public Object getFieldValue(JRField field) throws JRException {
        try {
            if (field.getName() == null) {
                return null;
            }
            String[] ids = field.getName().split("\\" + MongoDbFieldsProvider.FIELD_NAME_SEPARATOR);
            if (hasIterator) {
                return getCursorValue(ids);
            } else if (hasCommandResult) {
                return getCommandResult(ids);
            }
            return null;
        } catch (Exception e) {
            logger.error(e);
            throw new JRException(e.getMessage());
        }
    }

    private Object getCommandResult(String[] ids) {
        Map<?, ?> currentMap = currentResult;
        for (int index = 0; index < ids.length; index++) {
            boolean isLast = index == (ids.length - 1);
            String id = ids[index];
            Object currentFieldObject = currentMap.get(id);
            if (currentFieldObject == null) {
                return null;
            }
            if (currentFieldObject instanceof Map) {
                if (isLast) {
                    return currentFieldObject;
                }
                currentMap = (Map<?, ?>) currentFieldObject;
            } else {
                if (isLast) {
                    return currentFieldObject;
                }
                return null;
            }
        }
        return null;
    }

    private Object getCursorValue(String[] ids) {
        DBObject fieldObject = currentDbObject;
        for (int index = 0; index < ids.length; index++) {
            boolean isLast = index == (ids.length - 1);
            String id = ids[index];
            Object currentFieldObject = fieldObject.get(id);
            if (currentFieldObject == null) {
                return null;
            }
            if (currentFieldObject instanceof DBObject) {
                if (isLast) {
                    return currentFieldObject;
                }
                fieldObject = (DBObject) currentFieldObject;
            } else {
                if (isLast) {
                    return currentFieldObject;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Tries to position the cursor on the next element in the data source.
     */
    @Override
    public boolean next() throws JRException {
        boolean next = false;
        if (hasIterator && (next = wrapper.iterator.hasNext())) {
            currentDbObject = wrapper.iterator.next();
        } else if (hasCommandResult) {
            next = resultsIterator.hasNext();
            currentResult = null;
            if (next) {
                currentResult = (Map<?, ?>) resultsIterator.next();
            }
        }
        return next;
    }
}
