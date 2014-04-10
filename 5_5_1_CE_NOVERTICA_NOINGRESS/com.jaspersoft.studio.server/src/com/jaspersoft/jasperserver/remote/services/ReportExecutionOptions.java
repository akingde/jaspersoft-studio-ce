/*
* Copyright (C) 2005 - 2009 Jaspersoft Corporation. All rights  reserved.
* http://www.jaspersoft.com.
*
* Unless you have purchased  a commercial license agreement from Jaspersoft,
* the following license terms  apply:
*
* This program is free software: you can redistribute it and/or  modify
* it under the terms of the GNU Affero General Public License  as
* published by the Free Software Foundation, either version 3 of  the
* License, or (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero  General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public  License
* along with this program.&nbsp; If not, see <http://www.gnu.org/licenses/>.
*/
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