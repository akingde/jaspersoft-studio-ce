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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.apache.log4j.Logger;

import com.jaspersoft.mongodb.connection.MongoDbConnection;
import com.jaspersoft.mongodb.query.MongoDbParameter;
import com.jaspersoft.mongodb.query.MongoDbQueryExecuter;
import com.jaspersoft.mongodb.query.MongoDbQueryWrapper;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbFieldsProvider {
    private static MongoDbFieldsProvider instance;

    private static final Lock lock = new ReentrantLock();

    private static final Logger logger = Logger.getLogger(MongoDbFieldsProvider.class);

    public static final String FIELD_NAME_SEPARATOR = ".";

    private MongoDbFieldsProvider() {

    }

    public static MongoDbFieldsProvider getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new MongoDbFieldsProvider();
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public List<JRDesignField> getFields(JasperReportsContext context, JRDataset dataset, Map<String, Object> parameters, MongoDbConnection connection)
            throws JRException {
        MongoDbQueryExecuter queryExecuter = null;
        MongoDbQueryWrapper wrapper = null;
        try {
            Map<String, JRValueParameter> newValueParameters = new HashMap<String, JRValueParameter>();
            for (String parameterName : parameters.keySet()) {
                Object parameterValue = parameters.get(parameterName);
                MongoDbParameter newParameter = new MongoDbParameter(parameterName, parameterValue);
                newValueParameters.put(parameterName, newParameter);
            }
            parameters.clear();
            newValueParameters.put(JRParameter.REPORT_PARAMETERS_MAP, new MongoDbParameter(
                    JRParameter.REPORT_PARAMETERS_MAP, parameters));
            queryExecuter = new MongoDbQueryExecuter(context, dataset, newValueParameters, true);
            wrapper = new MongoDbQueryWrapper(queryExecuter.getProcessedQueryString(), connection,
                    queryExecuter.getParameters());
            logger.info("FieldsProvider will find fields from the first " + wrapper.rowsToProcess + " records.");
            Map<String, Class<?>> fieldNames = new TreeMap<String, Class<?>>();
            if (wrapper.iterator != null) {
                processCursorFields(wrapper, fieldNames);
            } else if (wrapper.commandResults != null) {
                processCommandResultFields(wrapper, fieldNames);
            }
            List<JRDesignField> fields = new ArrayList<JRDesignField>();
            JRDesignField field = null;
            logger.info("Found " + fieldNames.keySet().size() + " fields");
            for (String fieldName : fieldNames.keySet()) {
                field = new JRDesignField();
                field.setName(fieldName);
                field.setValueClass(fieldNames.get(fieldName));
                field.setDescription(null);
                fields.add(field);
            }
            return fields;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    private void processCommandResultFields(MongoDbQueryWrapper wrapper, Map<String, Class<?>> fieldNames) {
        Iterator<?> resultsIterator = wrapper.commandResults.iterator();
        while (resultsIterator.hasNext() && wrapper.rowsToProcess >= 0) {
            Map<?, ?> currentResult = (Map<?, ?>) resultsIterator.next();
            processMapResult(null, currentResult, fieldNames);
            wrapper.rowsToProcess--;
        }
    }

    private void processCursorFields(MongoDbQueryWrapper wrapper, Map<String, Class<?>> fieldNames) {
        try {
            DBObject currentDbObject;
            while (wrapper.iterator.hasNext() && wrapper.rowsToProcess >= 0) {
                currentDbObject = wrapper.iterator.next();
                processDBObject(null, currentDbObject, fieldNames);
                wrapper.rowsToProcess--;
            }
        } finally {
            if (wrapper.iterator != null) {
                wrapper.iterator.close();
            }
        }
    }

    private void processMapResult(String parentFieldName, Map<?, ?> currentResult, Map<String, Class<?>> fieldNames) {
        Object value;
        if (logger.isDebugEnabled()) {
            logger.debug("processDBObject parentFieldName: " + parentFieldName);
            logger.debug("processDBObject currentDbObject: " + currentResult.toString());
        }
        for (Object fieldName : currentResult.keySet()) {
            value = currentResult.get(fieldName);
            if (value == null) {
                continue;
            }
            if (value instanceof Map) {
                processMapResult((parentFieldName == null ? "" : parentFieldName + FIELD_NAME_SEPARATOR) + fieldName,
                        (Map<?, ?>) value, fieldNames);
            } else {
                fieldNames.put((parentFieldName == null ? "" : parentFieldName + FIELD_NAME_SEPARATOR) + fieldName,
                        value.getClass());
            }
        }
    }

    private void processDBObject(String parentFieldName, DBObject currentDbObject, Map<String, Class<?>> fieldNames) {
        Object value;
        if (logger.isDebugEnabled()) {
            logger.debug("processDBObject parentFieldName: " + parentFieldName);
            logger.debug("processDBObject currentDbObject: " + currentDbObject.toString());
        }
        for (String fieldName : currentDbObject.keySet()) {
            value = currentDbObject.get(fieldName);
            if (value == null) {
                continue;
            }
            if (value instanceof BasicDBList) {
                fieldNames.put((parentFieldName == null ? "" : parentFieldName + ".") + fieldName, List.class);
            } else if (value instanceof BasicDBObject) {
                processDBObject((parentFieldName == null ? "" : parentFieldName + ".") + fieldName, (DBObject) value,
                        fieldNames);
            } else {
                fieldNames.put((parentFieldName == null ? "" : parentFieldName + ".") + fieldName, value.getClass());
            }
        }
    }
}
