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
 * Copyright (C) 2005 - 2013 Jaspersoft Corporation. All rights reserved.
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
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api.metadata.common.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

import java.io.InputStream;


/**
 * FileResource is the interface which represents the JasperServer resource containing some files
 * of any type: images, fonts, jrxml, jar, resource bundles, style templates, xml.
 * It extends {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource}
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: FileResource.java 32262 2013-05-31 16:05:18Z inesterenko $
 */
@JasperServerAPI
public interface FileResource extends Resource
{
	String TYPE_IMAGE = "img";
	String TYPE_FONT = "font";
	String TYPE_JRXML = "jrxml";
	String TYPE_JAR = "jar";
	String TYPE_RESOURCE_BUNDLE = "prop";
	String TYPE_STYLE_TEMPLATE = "jrtx";
	String TYPE_XML = "xml";
    String TYPE_CSS = "css";
    String TYPE_JSON = "json";
    String TYPE_ACCESS_GRANT_SCHEMA = "accessGrantSchema";

    /**
     * Returns <code>true</code> if the resource has some file.
     *
     * @return <code>true</code> if the resource has some file.
     */
	boolean hasData();

    /**
     * Returns the data stream of the file
     *
     * @return data stream of the file
     */
	InputStream getDataStream();

    /**
     * Sets the data of the file input stream
     *
     * @param is the input stream of file to be set
     */
	void readData(InputStream is);

    /**
     * Returns the data from the file as a byte array
     *
     * @return data
     */
	byte[] getData();

    /**
     * Sets the data of the file
     *
     * @param data
     */
	void setData(byte[] data);

    /**
     * Returns the file type
     *
     * @return file type
     */
	String getFileType();

    /**
     * Sets the file type
     *
     * @param fileType
     */
	void setFileType(String fileType);

    /**
     * Shows if the file resource is only a reference
     *
     * @return <code>true</code> if file resource is a reference
     */
	boolean isReference();

    /**
     * Returns the URI that the resource references to
     *
     * @return reference URI
     */
	String getReferenceURI();

    /**
     * Sets the URI that resource should reference to
     *
     * @param referenceURI
     */
	void setReferenceURI(String referenceURI);
}
