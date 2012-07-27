/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.reader;

/**
 * Clients that want to be notified when a new record is read 
 * from a dataset should implement this interface.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface DatasetReaderListener {
	
	/**
	 * Notifies the reading of a new record.
	 * <p>
	 * New record information are presented as a data array.
	 * Each element represents the column data for the 
	 * record just read.
	 * 
	 * @param values the record elements read
	 */
	void newRecord(Object[] values);
	
	/**
	 * Notifies the end of the dataset reading task.
	 */
	void finished();
	
	/**
	 * Checks if the listener is an valid status that allows
	 * it to receive the notification of events.
	 * 
	 * @return <code>true</code> if listener status is ok, 
	 * 				<code>false</code> otherwise
	 */
	boolean isValidStatus();
	
	/**
	 * Change the current status of the listener 
	 * in order to invalidate it.
	 */
	void invalidate();
}
