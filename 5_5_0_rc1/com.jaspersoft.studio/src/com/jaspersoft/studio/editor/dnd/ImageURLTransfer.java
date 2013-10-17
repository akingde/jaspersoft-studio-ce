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
package com.jaspersoft.studio.editor.dnd;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import org.eclipse.swt.dnd.ByteArrayTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;

/**
 * Specific class of type {@link Transfer} for a Java string that is supposed to represent an image URL.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ImageURLTransfer extends ByteArrayTransfer {
	
	private final static ImageURLTransfer instance = new ImageURLTransfer();
	private final static String ID_NAME = "image-url-transfer" ; //$NON-NLS-1$
	private final static int ID = registerType(ID_NAME);
	
	private ImageURLTransfer(){
	}

	/**
	 * @return the singleton instance for the {@link ImageURLTransfer} type
	 */
	public static ImageURLTransfer getInstance(){
		return instance;
	}
	
	@Override
	protected int[] getTypeIds() {
		return new int[] {ID};
	}

	@Override
	protected String[] getTypeNames() {
		return new String[] {ID_NAME};
	}

	@Override
	protected void javaToNative(Object object, TransferData transferData) {
		byte[] byteArray = toByteArray(((String)object).toCharArray());
		if(byteArray!=null){
			super.javaToNative(byteArray, transferData);
		}
	}
	
	@Override
	protected Object nativeToJava(TransferData transferData) {
		byte[] bytes = (byte[]) super.nativeToJava(transferData);
		if(isSupportedType(transferData) && bytes !=null){
			return new String(bytes);
		}
		else {
			return null;
		}
	}
	
	private static byte[] toByteArray(char[] array) {
	    return toByteArray(array, Charset.defaultCharset());
	}

	private static byte[] toByteArray(char[] array, Charset charset) {
	    CharBuffer cbuf = CharBuffer.wrap(array);
	    ByteBuffer bbuf = charset.encode(cbuf);
	    return bbuf.array();
	}
}
