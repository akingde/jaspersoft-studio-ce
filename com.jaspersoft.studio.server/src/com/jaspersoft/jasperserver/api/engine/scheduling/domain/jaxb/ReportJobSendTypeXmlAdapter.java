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
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb;

import com.jaspersoft.jasperserver.api.common.domain.jaxb.AbstractEnumXmlAdapter;
import com.jaspersoft.jasperserver.api.common.domain.jaxb.NamedPropertyHolder;
import com.jaspersoft.jasperserver.api.engine.scheduling.domain.ReportJobMailNotification;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: ReportJobSendTypeXmlAdapter.java 38348 2013-09-30 04:57:18Z carbiv $
 */
public class ReportJobSendTypeXmlAdapter extends AbstractEnumXmlAdapter<Byte> {
    @Override
    protected NamedPropertyHolder<Byte>[] getEnumConstantsArray() {
        return SendType.values();
    }

    public enum SendType implements NamedPropertyHolder<Byte> {
        SEND(ReportJobMailNotification.RESULT_SEND),
        SEND_ATTACHMENT(ReportJobMailNotification.RESULT_SEND_ATTACHMENT),
        SEND_ATTACHMENT_NOZIP(ReportJobMailNotification.RESULT_SEND_ATTACHMENT_NOZIP),
        SEND_EMBED(ReportJobMailNotification.RESULT_SEND_EMBED),
        SEND_ATTACHMENT_ZIP_ALL(ReportJobMailNotification.RESULT_SEND_ATTACHMENT_ZIP_ALL),
        SEND_EMBED_ZIP_ALL_OTHERS(ReportJobMailNotification.RESULT_SEND_EMBED_ZIP_ALL_OTHERS);

        private final Byte byteValue;

        private SendType(Byte byteValue) {
            this.byteValue = byteValue;
        }
         public Byte getProperty(){
             return this.byteValue;
         }
    }




}
