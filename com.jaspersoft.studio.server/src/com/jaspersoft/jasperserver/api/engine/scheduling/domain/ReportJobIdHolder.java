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
package com.jaspersoft.jasperserver.api.engine.scheduling.domain;

import com.jaspersoft.jasperserver.api.JasperServerAPI;
import com.jaspersoft.jasperserver.api.engine.scheduling.service.ReportJobsPersistenceService;

import java.io.Serializable;

/**
 * Wrapper around a report job ID used by some report scheduling API methods.
 * 
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: ReportJobIdHolder.java 19921 2010-12-11 14:52:49Z tmatyashovsky $
 * @see ReportJob#getId()
 * @see ReportJobsPersistenceService
 * @since 1.0
 */
@JasperServerAPI
public class ReportJobIdHolder implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final long id;
	
	/**
	 * Wraps a report job ID into an object.
	 * 
	 * @param id the ID to wrap
	 */
	public ReportJobIdHolder(long id) {
		this.id = id;
	}

	/**
	 * Returns the wrapped report job ID.
	 * 
	 * @return the job ID
	 */
	public long getId() {
		return id;
	}
	
	/**
	 * Uses the job ID as has code.
	 */
	public int hashCode() {
		return (int) id;
	}
	
	/**
	 * Compares wrappers by job IDs.
	 */
	public boolean equals(Object obj) {
		if (!(obj instanceof ReportJobIdHolder)) {
			return false;
		}
		
		return id == ((ReportJobIdHolder) obj).id;
	}

}
