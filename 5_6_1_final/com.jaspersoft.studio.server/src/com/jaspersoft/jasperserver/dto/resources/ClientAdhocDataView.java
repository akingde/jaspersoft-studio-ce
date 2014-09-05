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

package com.jaspersoft.jasperserver.dto.resources;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * <p>AdhocDataView belongs to PRO codebase, but ClientAdhocDataView should be placed to CE because of usage in AbstractClientDataSourceHolder</p>
 *
 * @author Yaroslav.Kovalchyk
 * @version $Id: ClientAdhocDataView.java 27624 2013-03-01 09:55:15Z ykovalchyk $
 */
@XmlRootElement(name = ResourceMediaType.ADHOC_DATA_VIEW_CLIENT_TYPE)
public class ClientAdhocDataView extends AbstractClientDataSourceHolder<ClientAdhocDataView> implements ClientReferenceableDataSource {

    @Override
    public String toString() {
        return "ClientAdhocDataView{" +
                "version=" + getVersion() +
                ", permissionMask=" + getPermissionMask() +
                ", uri='" + getUri() + '\'' +
                ", label='" + getLabel() + '\'' +
                '}';
    }
}
