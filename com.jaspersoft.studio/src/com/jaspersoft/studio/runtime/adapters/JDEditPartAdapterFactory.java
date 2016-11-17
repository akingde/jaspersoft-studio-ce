/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.runtime.adapters;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;

import com.jaspersoft.studio.editor.gef.parts.AJDEditPart;

/**
 * Adapter factory for the AJDEditPart subclasses.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class JDEditPartAdapterFactory implements IAdapterFactory {

	/** The list of provided adapters. */
	private static final Class<?>[] ADAPTER_LIST= new Class[] { IResource.class, IFile.class };
	
	@Override
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (IResource.class.equals(adapterType)
				&& adaptableObject instanceof AJDEditPart) {
			return ((AJDEditPart) adaptableObject).getAdapter(IResource.class);
		}
		if (IFile.class.equals(adapterType)
				&& adaptableObject instanceof AJDEditPart) {
			return ((AJDEditPart) adaptableObject).getAdapter(IFile.class);
		}
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return ADAPTER_LIST;
	}

}
