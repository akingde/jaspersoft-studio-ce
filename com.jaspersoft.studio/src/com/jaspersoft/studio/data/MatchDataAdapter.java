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

import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;

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

			return FileDataAdapterStorage.readDataADapter(new FileInputStream(path.toOSString())) != null;
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
