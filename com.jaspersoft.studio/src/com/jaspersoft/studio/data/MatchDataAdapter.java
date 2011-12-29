package com.jaspersoft.studio.data;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.ide.FileStoreEditorInput;

public class MatchDataAdapter implements IEditorMatchingStrategy {

	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		InputStream in = null;
		IPath path = null;
		try {

			if (input instanceof FileStoreEditorInput) {
				FileStoreEditorInput fsei = (FileStoreEditorInput) input;
				path = new Path(fsei.getURI().getPath());
			} else if (input instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) input).getFile();			
				path = file.getRawLocation();
			}
			if (!path.getFileExtension().equals("xml"))
				return false;

			IPath refpath = null;
			IEditorInput editorRefInput = editorRef.getEditorInput();
			if (editorRefInput instanceof FileStoreEditorInput) {
				FileStoreEditorInput fsei = (FileStoreEditorInput) editorRefInput;
				refpath = new Path(fsei.getURI().getPath());
			} else if (input instanceof IFileEditorInput) {
				IFile file = ((IFileEditorInput) input).getFile();
				refpath = file.getRawLocation();
			}
			if (!path.toOSString().equals(refpath.toOSString()))
				return false;

			return DataAdapterManager.readDataADapter(new FileInputStream(path.toOSString())) != null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return false;
	}

}
