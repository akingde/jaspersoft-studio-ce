/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
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

package com.jaspersoft.jasperserver.api.metadata.user.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;

/**
 * An interface which makes an object be aware of the owning tenant
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: TenantQualified.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 */
@JasperServerAPI
public interface TenantQualified {

    /**
     * Returns tenant id
     * @return
     */
    String getTenantId();

    /**
     * Sets tenant id
     * @param tenantId
     */
    void setTenantId(String tenantId);

}
