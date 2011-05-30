package com.jaspersoft.studio.eclipse.editorinput;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.eclipse.core.resources.IStorage;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IStorageEditorInput;

public class StringEditorInput implements IStorageEditorInput, IEditorInput {

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
