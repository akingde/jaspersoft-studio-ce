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
package com.jaspersoft.studio.runtime.adapters;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;

import com.jaspersoft.studio.editor.outline.part.TreeEditPart;

/**
 * Adapter factory for the {@link TreeEditPart} subclasses.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class TreeEditPartAdapterFactory implements IAdapterFactory {

	/** The list of provided adapters. */
	private static final Class<?>[] ADAPTER_LIST= new Class[] { IResource.class, IFile.class };
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (IResource.class.equals(adapterType)
				&& adaptableObject instanceof TreeEditPart) {
			return ((TreeEditPart) adaptableObject).getAdapter(IResource.class);
		}
		if (IFile.class.equals(adapterType)
				&& adaptableObject instanceof TreeEditPart) {
			return ((TreeEditPart) adaptableObject).getAdapter(IFile.class);
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTER_LIST;
	}

}
