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

package com.jaspersoft.jasperserver.remote.services;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportExecutionOptions.java 28947 2013-02-26 15:02:08Z vsabadosh $
 */
public class ReportExecutionOptions {

    private Boolean freshData = false;
    private Boolean saveDataSnapshot = false;
    private Boolean interactive = false;
    private Boolean ignorePagination = false;
    private Boolean async = false;
    private String transformerKey;
    private String contextPath;
    private String defaultAttachmentsPrefixTemplate;

    public String getDefaultAttachmentsPrefixTemplate() {
        return defaultAttachmentsPrefixTemplate;
    }

    public ReportExecutionOptions setDefaultAttachmentsPrefixTemplate(String defaultAttachmentsPrefixTemplate) {
        this.defaultAttachmentsPrefixTemplate = defaultAttachmentsPrefixTemplate;
        return this;
    }

    public Boolean isAsync() {
        return async;
    }

    public ReportExecutionOptions setAsync(Boolean async) {
        this.async = async;
        return this;
    }

    public String getContextPath() {
        return contextPath;
    }

    public ReportExecutionOptions setContextPath(String contextPath) {
        this.contextPath = contextPath;
        return this;
    }

    public Boolean isFreshData() {
        return freshData;
    }

    public ReportExecutionOptions setFreshData(Boolean freshData) {
        this.freshData = freshData;
        return this;
    }

    public Boolean isSaveDataSnapshot() {
        return saveDataSnapshot;
    }

    public ReportExecutionOptions setSaveDataSnapshot(Boolean saveDataSnapshot) {
        this.saveDataSnapshot = saveDataSnapshot;
        return this;
    }

    public Boolean isIgnorePagination() {
        return ignorePagination;
    }

    public ReportExecutionOptions setIgnorePagination(Boolean ignorePagination) {
        this.ignorePagination = ignorePagination;
        return this;
    }

    public String getTransformerKey() {
        return transformerKey;
    }

    public ReportExecutionOptions setTransformerKey(String transformerKey) {
        this.transformerKey = transformerKey;
        return this;
    }

    public Boolean isInteractive() {
        return interactive;
    }

    public ReportExecutionOptions setInteractive(Boolean interactive) {
        this.interactive = interactive;
        return this;
    }
}
