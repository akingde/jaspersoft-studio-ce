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

package com.jaspersoft.jasperserver.api.metadata.xml.domain.impl;

/**
 * @author tkavanagh
 * @version $Id: Argument.java 4307 2006-08-24 08:13:55Z giulio $
 */

public class Argument {

    public static final String MODIFY_REPORTUNIT = "MODIFY_REPORTUNIT_URI";
    public static final String CREATE_REPORTUNIT = "CREATE_REPORTUNIT_BOOLEAN";
    public static final String LIST_DATASOURCES  = "LIST_DATASOURCES";
    public static final String IC_GET_QUERY_DATA  = "IC_GET_QUERY_DATA";
    
    public static final String VALUE_TRUE = "true";
    public static final String VALUE_FALSE = "false";
    
    public static final String RUN_OUTPUT_FORMAT = "RUN_OUTPUT_FORMAT";
    public static final String RUN_OUTPUT_FORMAT_PDF = "PDF";
    public static final String RUN_OUTPUT_FORMAT_JRPRINT = "JRPRINT";
    public static final String RUN_OUTPUT_FORMAT_HTML = "HTML";
    public static final String RUN_OUTPUT_FORMAT_XLS = "XLS";
    public static final String RUN_OUTPUT_FORMAT_XML = "XML";
    public static final String RUN_OUTPUT_FORMAT_CSV = "CSV";
    public static final String RUN_OUTPUT_FORMAT_ODS = "ODS";
    public static final String RUN_OUTPUT_FORMAT_DOCX = "DOCX";
    public static final String RUN_OUTPUT_FORMAT_XLS_NOPAG = "XLS_NOPAG";
    public static final String RUN_OUTPUT_FORMAT_XLSX = "XLSX";
    public static final String RUN_OUTPUT_FORMAT_XLSX_NOPAG = "XLSX_NOPAG";
	public static final String RUN_OUTPUT_FORMAT_RTF = "RTF";

    
    public static final String RUN_OUTPUT_IMAGES_URI = "IMAGES_URI";
            
    public static final String RUN_OUTPUT_PAGE = "PAGE";
    public static final String RUN_OUTPUT_PAGES = "OUTPUT_PAGES";

    /**
     * Argument used to pass a transformer key to be used when running a report
     * with {@link #RUN_OUTPUT_FORMAT_JRPRINT} as output format.
     * 
     * The transformer key will be used to transform generic elements in the
     * generated report as per 
     * <code>net.sf.jasperreports.engine.export.GenericElementReportTransformer</code>.
     */
    public static final String RUN_TRANSFORMER_KEY = "TRANSFORMER_KEY";

    public static final String RU_REF_URI = "RU_REF_URI";
    public static final String PARAMS_ARG = "PARAMS_ARG";

    /**
     * Argument used for the <code>list</code> method to specify that a resource
     * lookup is to be performed.
     * 
     * <p>
     * The {@link #RESOURCE_TYPE} argument is mandatory and needs to be set 
     * to a resource type.
     * 
     * Optionally, {@link #PARENT_DIRECTORY} or {@link #START_FROM_DIRECTORY} can
     * be used to specify a folder to use as parent/ancestor when listing
     * resources.
     */
    public static final String LIST_RESOURCES = "LIST_RESOURCES";
    
    /**
     * An argument used along with {@link #LIST_RESOURCES} which specifies
     * the type of resources to be listed.
     * 
     * <p>
     * Valid valus for this argument are resource types such as
     * {@link ResourceDescriptor#TYPE_IMAGE TYPE_IMAGE} and the special value
     * {@link #REPORT_TYPE}.
     */
    public static final String RESOURCE_TYPE = "RESOURCE_TYPE";
    
    /**
     * A special {@link #RESOURCE_TYPE} value which is used for listing
     * reports.
     * 
     * <p>
     * Using this differs from using {@link ResourceDescriptor#TYPE_REPORTUNIT}
     * in that listed reports are filtered not to include reports that are not
     * meant to be executed by users.
     */
    public static final String REPORT_TYPE = "REPORT_TYPE";
    
    /**
     * Argument used in conjunction with {@link #LIST_RESOURCES} to specify
     * a folder starting from which to list resources.
     * 
     * <p>
     * When this argument is used, resources located under the specified folder
     * at any level are returned by the <code>list</code> operation.
     */
    public static final String START_FROM_DIRECTORY = "START_FROM_DIRECTORY";
    
    /**
     * Argument used for the <code>list</code> method in conjunction with
     * {@link #LIST_RESOURCES} to specify a parent
     * folder for which to list resources.
     */
    public static final String PARENT_DIRECTORY = "PARENT_DIRECTORY";
    
    public static final String NO_RESOURCE_DATA_ATTACHMENT = "NO_ATTACHMENT"; // marks to skip uploading blob from DB
    public static final String NO_SUBRESOURCE_DATA_ATTACHMENTS = "NO_SUBRESOURCE_ATTACHMENTS";
    
    /**
     * Argument used to pass the destination URI for the resource/folder copy/move operations.
     */
    public static final String DESTINATION_URI = "DESTINATION_URI";

    public static final String FRESH_DATA = "FRESH_DATA";
    public static final String SAVE_DATA_SNAPSHOT = "SAVE_DATA_SNAPSHOT";
    public static final String PARAM_INTERACTIVE = "interactive";
    
    private String name;
    private String value;
    
    /** Creates a new instance of Argument */
    public Argument(String name, String value) {
        this.name = name;
        this.value = value;
    }
    
    public Argument() {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
	
}
