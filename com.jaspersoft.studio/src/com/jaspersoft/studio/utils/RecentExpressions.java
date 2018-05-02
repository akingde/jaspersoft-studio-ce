/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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

	private static List<String> recentExpressionsLst=new ArrayList<>();
	
	private RecentExpressions() {
	}
	
	/**
	 * Returns the expression found at the specified index.
	 * <p>
	 * Example: expression with index 1 is the oldest in the list.
	 * 
	 * @param index 
	 * @return the expression string at the specified position, <code>null</code> otherwise
	 */
	public static String getExpression(int index){
		if(recentExpressionsLst.size()>=index && index>0){
			return recentExpressionsLst.get(index-1);
		}
		else {
			return null;
		}
	}
	
	/**
	 * @return the whole list of cached expressions
	 */
	public static List<String> getRecentExpressionsList(){
		return recentExpressionsLst;
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
		if(recentExpressionsLst.contains(expression)){
			recentExpressionsLst.remove(expression);			
		}
		recentExpressionsLst.add(0, expression);
	}
	
	/**
	 * Clears the list of recent expressions.
	 */
	public static void clear(){
		recentExpressionsLst.clear();
	}
	
}
