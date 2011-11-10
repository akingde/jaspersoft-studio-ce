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
package com.jaspersoft.studio.eclipse.editorinput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class StringEditorInput implements IStorageEditorInput {

	private final String inputString;

	public StringEditorInput(String inputString) {
		this.inputString = inputString;
	}

	public boolean exists() {
		return false;
	}

	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public Object getAdapter(Class adapter) {
		return null;
	}

	public String getName() {
		return "input name";
	}

	public String getToolTipText() {
		return "tool tip";
	}

	public IStorage getStorage() {

		return new StringStorage();
	}

	private final class StringStorage implements IStorage {
		public InputStream getContents() throws CoreException {
			return new ByteArrayInputStream(inputString.getBytes());// new StringBufferInputStream(inputString);
		}

		public IPath getFullPath() {
			return null;
		}

		public String getName() {
			return StringEditorInput.this.getName();
		}

		public boolean isReadOnly() {
			return false;
		}

		public Object getAdapter(Class adapter) {
			return null;
		}
	}
}
