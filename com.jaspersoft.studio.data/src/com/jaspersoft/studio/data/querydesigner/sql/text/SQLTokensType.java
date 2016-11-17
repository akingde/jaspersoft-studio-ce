/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.sql.text;

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
