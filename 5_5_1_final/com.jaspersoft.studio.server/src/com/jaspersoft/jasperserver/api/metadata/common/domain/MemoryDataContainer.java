/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

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
