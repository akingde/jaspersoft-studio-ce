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
package com.jaspersoft.studio.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the recent expressions.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class RecentExpressions {

	private static List<String> recentExpressions=new ArrayList<String>();
	
	/**
	 * Returns the expression found at the specified index.
	 * <p>
	 * Example: expression with index 1 is the oldest in the list.
	 * 
	 * @param index 
	 * @return the expression string at the specified position, <code>null</code> otherwise
	 */
	public static String getExpression(int index){
		if(recentExpressions.size()>=index && index>0){
			return recentExpressions.get(index-1);
		}
		else {
			return null;
		}
	}
	
	/**
	 * @return the whole list of cached expressions
	 */
	public static List<String> getRecentExpressionsList(){
		return recentExpressions;
	}
	
	/**
	 * Add new expression string to the "recent" list.
	 * <p>
	 * If the string is already present the one in the old position
	 * is removed and then it is re-inserted.
	 * 
	 * @param expression the expression string to add
	 */
	public static void addNewExpression(String expression){
		if(!recentExpressions.contains(expression)){
			recentExpressions.remove(expression);
			recentExpressions.add(expression);
		}
	}
	
	/**
	 * Clears the list of recent expressions.
	 */
	public static void clear(){
		recentExpressions.clear();
	}
	
}
