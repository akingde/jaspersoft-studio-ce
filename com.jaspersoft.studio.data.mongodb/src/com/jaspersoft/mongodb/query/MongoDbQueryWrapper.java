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
package com.jaspersoft.mongodb.query;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import net.sf.jasperreports.engine.JRException;

import org.apache.log4j.Logger;

import com.jaspersoft.mongodb.connection.MongoDbConnection;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MapReduceCommand;
import com.mongodb.MapReduceCommand.OutputType;
import com.mongodb.MapReduceOutput;
import com.mongodb.util.JSON;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class MongoDbQueryWrapper {
    private final static Logger logger = Logger.getLogger(MongoDbQueryWrapper.class);

    public static final String FIND_QUERY_KEY = "findQuery";

    public static final String FIND_QUERY_REGEXP_KEY = "findQueryRegEx";

    public static final String FIND_FIELDS_KEY = "findFields";

    public static final String SORT_KEY = "sort";

    public static final String LIMIT_KEY = "limit";

    public static final String COLLECTION_NAME_KEY = "collectionName";

    public static final String ROWS_TO_PROCESS_KEY = "rowsToProcess";

    public static final String MAP_REDUCE_KEY = "mapReduce";

    public static final String MAP_KEY = "map";

    public static final String REDUCE_KEY = "reduce";

    public static final String OUT_KEY = "out";

    private static final String OUT_DB_KEY = "db";

    private static final String FINALIZE_KEY = "finalize";

    private static final String RUN_COMMAND_KEY = "runCommand";

    private static final String RESULT_KEY = "result";

    public DBCursor iterator;

    public DBObject queryObject;

    public int rowsToProcess = 5;

    private MongoDbConnection connection;

    private Map<String, Object> parameters;

    public List<?> commandResults;

    public MongoDbQueryWrapper(String queryString, MongoDbConnection connection, Map<String, Object> parameters)
            throws JRException {
        this.connection = connection;
        this.parameters = parameters;
        processQuery(queryString);
    }

    public void processQuery(String queryString) throws JRException {
        logger.info("Processing mongoDB query");
        if (queryString.startsWith("\"")) {
            queryString = queryString.substring(1, queryString.length());
        }
        if (queryString.endsWith("\"")) {
            queryString = queryString.substring(0, queryString.length() - 1);
        }
        Object parseResult = JSON.parse(queryString);
        if (logger.isDebugEnabled()) {
            logger.debug("Query: " + queryString);
        }
        if (!(parseResult instanceof DBObject)) {
            throw new JRException("Unsupported type: " + parseResult.getClass().getName());
        }
        queryObject = (DBObject) parseResult;
        fixQueryObject(queryObject, parameters);

        if (queryObject.containsField(RUN_COMMAND_KEY)) {
            runCommand(queryObject.removeField(RUN_COMMAND_KEY));
        } else {
            createIterator();
        }

        if (queryObject.containsField(ROWS_TO_PROCESS_KEY)) {
            Integer value = processInteger(queryObject.get(ROWS_TO_PROCESS_KEY));
            if (value != null) {
                rowsToProcess = value.intValue();
            }
        }
        if (rowsToProcess == 0) {
            rowsToProcess = Integer.MAX_VALUE;
        }
    }

    private Object fixQueryObject(DBObject queryObjectToFix, Map<String, Object> reportParameters) {
        Set<String> keySet = queryObjectToFix.keySet();
        if (keySet.size() == 1) {
            String key = keySet.iterator().next();
            if (reportParameters.containsKey(key) && queryObjectToFix.get(key) == null) {
                return reportParameters.get(key);
            }
        }
        for (String key : queryObjectToFix.keySet()) {
            Object value = queryObjectToFix.get(key);
            if (value instanceof DBObject) {
                queryObjectToFix.put(key, fixQueryObject((DBObject) value, reportParameters));
            }
        }
        return queryObjectToFix;
    }

    private void runCommand(Object commandValue) throws JRException {
        if (!(commandValue instanceof DBObject)) {
            throw new JRException("Command must be a valid BSON object");
        }
        DBObject commandObject = (DBObject) commandValue;
        if (logger.isDebugEnabled()) {
            logger.debug("Command object: " + commandObject);
        }
        CommandResult commandResult = connection.getMongoDatabase().command(commandObject);
        if (!commandResult.ok()) {
            throw new JRException(commandResult.getErrorMessage());
        }
        Object resultObject = commandResult.get(RESULT_KEY);
        if (resultObject == null) {
            throw new JRException("No results");
        }
        commandResults = (List<?>) resultObject;
        if (logger.isDebugEnabled()) {
            logger.debug("Result List: " + resultObject);
        }
    }

    private void createIterator() throws JRException {
        if (!queryObject.containsField(COLLECTION_NAME_KEY)) {
            throw new JRException("\"" + COLLECTION_NAME_KEY + "\" must be part of the query object");
        }
        DBObject findQueryObject = (DBObject) queryObject.get(FIND_QUERY_KEY);
        if (findQueryObject == null) {
            findQueryObject = new BasicDBObject();
        }
        if (queryObject.containsField(FIND_QUERY_REGEXP_KEY)) {
            DBObject regExpObject = (DBObject) queryObject.get(FIND_QUERY_REGEXP_KEY);
            String value, flags;
            int index;
            for (String key : regExpObject.keySet()) {
                value = (String) regExpObject.get(key);
                if (value.startsWith("/")) {
                    value = value.substring(1, value.length());
                } else {
                    throw new JRException("Regular expressions must start with: /");
                }
                if (!value.contains("/")) {
                    throw new JRException("No ending symbol found: /");
                }
                index = value.lastIndexOf("/");
                flags = null;
                if (index == value.length() - 1) {
                    value = value.substring(0, index);
                } else {
                    flags = value.substring(index + 1, value.length());
                    value = value.substring(0, index);
                }
                findQueryObject.put(key, Pattern.compile((flags != null ? "(?" + flags + ")" : "") + value));
            }
        }

        DBCollection collection = connection.getMongoDatabase().getCollectionFromString(
                (String) queryObject.removeField(COLLECTION_NAME_KEY));
        if (queryObject.containsField(MAP_REDUCE_KEY)) {
            Object value = queryObject.removeField(MAP_REDUCE_KEY);
            if (!(value instanceof DBObject)) {
                logger.error("MapReduce value must be a valid JSON object");
            } else {
                DBObject mapReduceObject = (DBObject) value;
                String map = validateProperty(mapReduceObject, MAP_KEY);
                String reduce = validateProperty(mapReduceObject, REDUCE_KEY);
                Object outObject = mapReduceObject.get(OUT_KEY);
                if (outObject == null) {
                    throw new JRException("\"out\" cannot be null");
                }
                String collectionName = null;
                Object outDb = null;
                OutputType outputType = null;
                boolean hasOutputType = false;
                if (logger.isDebugEnabled()) {
                    logger.debug("Out object: " + outObject + ". Type: " + outObject.getClass().getName());
                }
                if (outObject instanceof String) {
                    collectionName = String.valueOf(outObject);
                } else if (outObject instanceof DBObject) {
                    DBObject outDbObject = (DBObject) outObject;
                    outDb = outDbObject.removeField(OUT_DB_KEY);
                    Iterator<String> keysIterator = outDbObject.keySet().iterator();
                    String type = null;
                    if (keysIterator.hasNext()) {
                        type = keysIterator.next();
                        collectionName = String.valueOf(outDbObject.get(type));
                    } else {
                        throw new JRException("\"out\" object cannot be empty");
                    }
                    type = type.toUpperCase();
                    outputType = OutputType.valueOf(type);
                    if (outputType == null) {
                        throw new JRException("Unknow output type: " + type);
                    }
                    hasOutputType = true;
                    if (logger.isDebugEnabled()) {
                        logger.debug("outobject: " + outDbObject);
                        logger.debug("collectionName: " + collectionName);
                        logger.debug("outputType: " + outputType);
                    }
                } else {
                    throw new JRException("Unsupported type for \"out\": " + outObject.getClass().getName());
                }
                MapReduceCommand mapReduceCommand = new MapReduceCommand(collection, map, reduce, collectionName,
                        hasOutputType ? outputType : OutputType.REPLACE, null);
                if (outDb != null) {
                    mapReduceCommand.setOutputDB(String.valueOf(outDb));
                }
                Object finalizeObject = mapReduceObject.removeField(FINALIZE_KEY);
                if (finalizeObject != null) {
                    mapReduceCommand.setFinalize(String.valueOf(finalizeObject));
                }
                MapReduceOutput mapReduceOutput = collection.mapReduce(mapReduceCommand);
                DBCollection mapReduceCollection = mapReduceOutput.getOutputCollection();
                if (mapReduceCollection != null) {
                    collection = mapReduceCollection;
                }
            }
        }

        iterator = collection.find(findQueryObject, (DBObject) queryObject.get(FIND_FIELDS_KEY));
        if (queryObject.containsField(SORT_KEY)) {
            iterator = iterator.sort((DBObject) queryObject.get(SORT_KEY));
        }
        if (queryObject.containsField(LIMIT_KEY)) {
            Integer value = processInteger(queryObject.get(LIMIT_KEY));
            if (value != null) {
                iterator = iterator.limit(value.intValue());
            }
        }
    }

    private Integer processInteger(Object value) {
        try {
            if (value instanceof Integer) {
                return (Integer) value;
            } else {
                return Integer.parseInt((String) value);
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return null;
    }

    public String validateProperty(DBObject object, String name) throws JRException {
        Object value = object.get(name);
        if (value == null) {
            throw new JRException(name + " can't be null");
        }
        return String.valueOf(value);
    }
}
