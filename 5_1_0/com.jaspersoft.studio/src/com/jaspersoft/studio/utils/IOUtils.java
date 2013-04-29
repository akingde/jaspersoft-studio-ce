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
package com.jaspersoft.studio.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Common methods for a serialization from and to a byte array
 * 
 * @author Orlandin Marco
 *
 */
public class IOUtils {
	
	/**
	 * Serialize an object to a byte array
	 * 
	 * @param item the object to serialize, must implements the interface Serializable
	 * @return the item serialized into a byte array
	 */
	public static byte[] writeToByteArray(Object item){
		byte[] bytes = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(baos);
			out.writeObject(item);
			bytes = baos.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytes;
	}
	
	/**
	 * Deserialize a byte array into an object
	 * 
	 * @param data the byte array of a serialized object
	 * @return the object deserialized or null if the desirialization was not possible
	 */
	public static Object readFromByteArray(byte[] data) {
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(data);
			ObjectInputStream in = new ObjectInputStream(bais);
			return (Object) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
