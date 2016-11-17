/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.querydesigner.json;


/**
 * Enumeration for different types of Json Query tokens.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum JsonTokensType {
	TEXT(true),KEYWORD(true),QUOTED_LITERAL(true),NUMBER(true),
	SYMBOL(true),EOF(false),EOL(false),SPACE(false),OTHER(true),
	JRPARAMETER(true),JRVARIABLE(true),JRFIELD(true);

	private boolean hasColor;
	
	private JsonTokensType(boolean hasColor) {
		this.hasColor=hasColor;
	}
	
	public static int getColoredTokensNum(){
		int num=0;
		for (JsonTokensType t : values()){
			if(t.hasColor){
				num++;
			}
		}
		return num;
	}
}
