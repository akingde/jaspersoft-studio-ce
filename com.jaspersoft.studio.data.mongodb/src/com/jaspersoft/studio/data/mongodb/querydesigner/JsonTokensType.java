/*******************************************************************************
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package com.jaspersoft.studio.data.mongodb.querydesigner;


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
