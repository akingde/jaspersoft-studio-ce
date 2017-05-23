/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/


package com.jaspersoft.jasperserver.api.metadata.common.domain;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Lucian Chirita
 *
 */
public class MemoryDataContainer implements DataContainer {

	private byte[] data;
	
	public MemoryDataContainer() {
		this(null);
	}
	
	public MemoryDataContainer(byte[] data) {
		this.data = data;
	}

	public OutputStream getOutputStream() {
		return new DataOutputStream();
	}

	public int dataSize() {
		return data == null ? 0 : data.length;
	}
	
	public InputStream getInputStream() {
		return data == null ? null : new ByteArrayInputStream(data);
	}

	public byte[] getData() {
		return data;
	}

	public boolean hasData() {
		return data != null;
	}
	
	protected class DataOutputStream extends ByteArrayOutputStream {
		public void close() throws IOException {
			super.close();
			data = toByteArray();
		}
	}

	public void dispose() {
		// help GC
		data = null;
	}
}
