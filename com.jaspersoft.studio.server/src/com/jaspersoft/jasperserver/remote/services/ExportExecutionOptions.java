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

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * <p></p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ExportExecutionOptions.java 26599 2012-12-10 13:04:23Z ykovalchyk $
 */
@XmlRootElement(name = "export")
public class ExportExecutionOptions {

    public static final String PARAM_NAME_PAGES = "pages";
    public static final String PARAM_NAME_ATTACHMENTS_PREFIX = "attachmentsPrefix";

    private String outputFormat;
    private String attachmentsPrefix;
    private ReportOutputPages pages;

    public ExportExecutionOptions setPages(ReportOutputPages pages) {
        this.pages = pages;
        return this;
    }

    public String getAttachmentsPrefix() {
        return attachmentsPrefix;
    }

    public ExportExecutionOptions setAttachmentsPrefix(String attachmentsPrefix) {
        this.attachmentsPrefix = attachmentsPrefix;
        return this;
    }

    @XmlTransient
    public ReportOutputPages getPages(){
        return pages;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public ExportExecutionOptions setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExportExecutionOptions that = (ExportExecutionOptions) o;

        if (pages != null ? !pages.equals(that.pages) : that.pages != null) return false;
        if (!outputFormat.equals(that.outputFormat)) return false;
        if (attachmentsPrefix != null ? !attachmentsPrefix.equals(that.attachmentsPrefix) : that.attachmentsPrefix != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = outputFormat.hashCode();
        result = 31 * result + (pages != null ? pages.hashCode() : 0);
        result = 31 * result + (attachmentsPrefix != null ? attachmentsPrefix.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return outputFormat +
                (pages != null ? ";" + PARAM_NAME_PAGES + "=" + pages.toString() : "") +
                (attachmentsPrefix != null ? ";" + PARAM_NAME_ATTACHMENTS_PREFIX + "=" + attachmentsPrefix : "");
    }
}
