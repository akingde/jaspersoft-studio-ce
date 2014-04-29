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
package com.jaspersoft.jasperserver.api.engine.scheduling.domain.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Yaroslav.Kovalchyk
 * @version $Id: TimestampToDateXmlAdapter.java 22583 2012-03-16 11:09:31Z ykovalchyk $
 */
public class TimestampToDateXmlAdapter extends XmlAdapter<Date, Timestamp>{
    @Override
    public Timestamp unmarshal(Date v) throws Exception {
        return new Timestamp(v.getTime());
    }

    @Override
    public Date marshal(Timestamp v) throws Exception {
        return new Date(v.getTime());
    }
}
