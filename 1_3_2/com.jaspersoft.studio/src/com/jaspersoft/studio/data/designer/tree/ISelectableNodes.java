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
package com.jaspersoft.studio.data.designer.tree;

import java.util.List;

import com.jaspersoft.studio.model.ANode;

/**
 * This interface is supposed to be implemented by those clients that want
 * to give the ability to support the nodes selection.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 * @param <T> node class type
 */
public interface ISelectableNodes<T extends ANode> {

	/**
	 * Returns a list of selected nodes based on the
	 * specified input query string.
	 * 
	 * @param query the query string
	 * @return the list of selected nodes
	 */
	List<T> getSelectableNodes(String query);
}
