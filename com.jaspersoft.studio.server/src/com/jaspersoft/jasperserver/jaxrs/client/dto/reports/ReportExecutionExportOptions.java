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
/*
 * Copyright (C) 2005 - 2014 Jaspersoft Corporation. All rights  reserved.
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

package com.jaspersoft.jasperserver.jaxrs.client.dto.reports;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "options")
public class ReportExecutionExportOptions {

	private String outputFormat;
	private String attachmentsPrefix;
	private Boolean allowInlineScripts;

	public Boolean getAllowInlineScripts() {
		return allowInlineScripts;
	}

	public void setAllowInlineScripts(Boolean allowInlineScripts) {
		this.allowInlineScripts = allowInlineScripts;
	}

	public String getOutputFormat() {
		return outputFormat;
	}

	public void setOutputFormat(String contentType) {
		this.outputFormat = contentType;
	}

	public String getAttachmentsPrefix() {
		return attachmentsPrefix;
	}

	public void setAttachmentsPrefix(String fileName) {
		this.attachmentsPrefix = fileName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ReportExecutionExportOptions that = (ReportExecutionExportOptions) o;

		if (outputFormat != null ? !outputFormat.equals(that.outputFormat) : that.outputFormat != null)
			return false;
		if (attachmentsPrefix != null ? !attachmentsPrefix.equals(that.attachmentsPrefix) : that.attachmentsPrefix != null)
			return false;
		if (allowInlineScripts != null ? !allowInlineScripts.equals(that.allowInlineScripts) : that.allowInlineScripts != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = outputFormat != null ? outputFormat.hashCode() : 0;
		result = 31 * result + (attachmentsPrefix != null ? attachmentsPrefix.hashCode() : 0);
		result = 31 * result + (allowInlineScripts != null ? allowInlineScripts.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "ReportExecutionExportOptions{" + "outputFormat='" + outputFormat + '\'' + ", attachmentsPrefix='" + attachmentsPrefix + '\'' + ", allowInlineScripts='" + allowInlineScripts + '\'' + '}';
	}
}
