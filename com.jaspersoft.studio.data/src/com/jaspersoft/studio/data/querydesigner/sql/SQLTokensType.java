/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql;

/**
 * Enumeration for different types of SQL tokens.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum SQLTokensType{
	STANDARD_TEXT(true),KEYWORD(true),COMMENT(true),QUOTED_LITERAL(true),NUMBER(true),
	BRACKETED_LITERAL(true),SYMBOL(true),EOF(false),EOL(false),SPACE(false),OTHER(true),
	JRPARAMETER(true),JRVARIABLE(true),JRFIELD(true);

	private boolean hasColor;
	
	private SQLTokensType(boolean hasColor) {
		this.hasColor=hasColor;
	}
	
	public static int getColoredTokensNum(){
		int num=0;
		for (SQLTokensType t : values()){
			if(t.hasColor){
				num++;
			}
		}
		return num;
	}
}
