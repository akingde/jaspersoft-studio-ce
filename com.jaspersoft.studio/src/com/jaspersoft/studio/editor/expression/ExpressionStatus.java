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

import java.util.ArrayList;
import java.util.List;

/**
 * Describes the actual status of an expression, usually being edited.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public enum ExpressionStatus {
	ERROR, WARNING, INFO;

	// A list of messages describing the actual status
	private List<String> messages;
	// A compact message that can be shown for example
	// in a title or into a small message area
	private String shortDescription;
	
	private ExpressionStatus(){
		messages=new ArrayList<String>();
	}

	/**
	 * @return the list of messages associated to the actual status
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * Sets the list of messages for the actual status.
	 * 
	 * @param messages the messages list
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}

	/**
	 * @return a short text description of the actual status
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Sets the short text description of the actual status.
	 * 
	 * @param shortDescription the short description 
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

}
