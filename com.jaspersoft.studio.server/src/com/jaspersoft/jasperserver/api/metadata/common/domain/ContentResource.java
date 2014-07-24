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

package com.jaspersoft.jasperserver.api.metadata.common.domain;

import java.io.InputStream;
import java.util.List;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.metadata.common.domain.Resource;

/**
 * ContentResource is the interface which represents the JasperServer resource containing some data of any type:
 * pdf,html,xls,rtf,csv,odt,txt,docx,ods,xlsx,img. It extends {@link com.jaspersoft.jasperserver.api.metadata.common.domain.Resource}
 *
 * @version
 * @see com.jaspersoft.jasperserver.api.metadata.common.domain.client.ContentResourceImpl
 */

@JasperServerAPI
public interface ContentResource extends Resource
{
	String TYPE_PDF = "pdf";
	String TYPE_HTML = "html";
	String TYPE_XLS = "xls";
	String TYPE_RTF = "rtf";
	String TYPE_CSV = "csv";
	String TYPE_ODT = "odt";
	String TYPE_TXT = "txt";
	String TYPE_DOCX = "docx";
	String TYPE_ODS = "ods";
	String TYPE_XLSX = "xlsx";
	String TYPE_IMAGE = "img";//unspecified image type
	String TYPE_UNSPECIFIED = "contentResource";


    /**
     * Returns <code>true</code> if the resource has some data.
     *
     * @return <code>true</code> if the resource has some data.
     */
	boolean hasData();

    /**
     * Returns the data stream from the data container of resource
     *
     * @return data stream of the resource
     */
	InputStream getDataStream();

    /**
     * Sets the data to the data container of resource
     *
     * @param is the input stream to be set
     */
	void readData(InputStream is);

    /**
     * Returns the data from the data container of resource as a byte array
     *
     * @return data
     */
	byte[] getData();

    /**
     * Sets the data to the data container of resource
     *
     * @param data the data
     */
	void setData(byte[] data);

    /**
     * Sets the data container to the resource
     *
     * @param dataContainer
     */
	void setDataContainer(DataContainer dataContainer);

    /**
     * Returns the size of the data container
     *
     * @return size
     */
    public int getSize();

    /**
     * Returns the file type contained in the resource
     *
     * @return file type
     */
	String getFileType();

    /**
     * Sets the file type contained in the resource
     *
     * @param fileType
     */
	void setFileType(String fileType);

    /**
     * Shows if the content resource is only a reference
     *
     * @return <code>true</code> if content resource is a reference
     */
	boolean isReference();

    /**
     * Returns the URI that the recource references to
     *
     * @return reference URI
     */
	String getReferenceURI();

    /**
     * Sets the URI that recource should reference to
     *
     * @param referenceURI
     */
	void setReferenceURI(String referenceURI);

    /**
     * Returns the list of subresources contained in this ContentResource
     *
     * @return list of resources
     */
	public List getResources();

    /**
     * Sets the list of subresources to this ContentResource
     *
     * @param resources
     */
	public void setResources(List resources);

    /**
     * Adds one more resource to the list of subresources of this ContentResource
     *
     * @param child resource
     */
	public void addChildResource(ContentResource child);

}
