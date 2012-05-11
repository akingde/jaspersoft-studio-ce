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
package com.jaspersoft.studio.editor.expression;

/**
 * Classes that implement this interface are supposed to monitor 
 * directly or indirectly the expression modification in order to
 * notify a status change of the expression itself.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface IExpressionStatusNotifier {

	/**
	 * Add a new listener to the list of the listeners that will
	 * be notified when the expression status changes. 
	 * 
	 * @param listener the new listener
	 */
	void addExpressionStatusChangeListener(IExpressionStatusChangeListener listener);
	
	/**
	 * Remove an existing listener to the list of the listeners that will
	 * be notified when the expression status changes. 
	 * 
	 * @param listener the listener to be removed
	 */
	void removeExpressionStatusChangeListener(IExpressionStatusChangeListener listener);
	
	/**
	 * Notifies the expression status change to the 
	 * collections of listeners.
	 * 
	 * @param status the expression status information
	 */
	void notifyExpressionStatusChanged(ExpressionStatus status);
	
}
