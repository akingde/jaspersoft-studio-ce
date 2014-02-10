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

	@SuppressWarnings("rawtypes")
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

		@SuppressWarnings("rawtypes")
		public Object getAdapter(Class adapter) {
			return null;
		}
	}
}
