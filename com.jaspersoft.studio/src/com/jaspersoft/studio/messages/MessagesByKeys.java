/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.messages;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MessagesByKeys {
	
	private static final String MESSAGES_BY_KEYS = "com.jaspersoft.studio.messages.messagesbykeys";
	private static final ResourceBundle RB_MESSAGES_BY_KEYS = ResourceBundle.getBundle(MESSAGES_BY_KEYS);
	
	private MessagesByKeys(){
	}

	public static String getString(String key) {
		try {
			return RB_MESSAGES_BY_KEYS.getString(key.toLowerCase());
		} catch (MissingResourceException e) {
			return key;
		}
	}
	
	public static boolean hasTranslation(String key) {
		return RB_MESSAGES_BY_KEYS.containsKey(key);
	}
	
}
