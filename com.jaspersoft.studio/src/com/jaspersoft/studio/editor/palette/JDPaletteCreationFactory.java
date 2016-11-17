/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.palette;

import org.eclipse.gef.requests.CreationFactory;

import com.jaspersoft.studio.editor.tools.MCompositeElement;

/*
 * A factory for creating JDPaletteCreation objects.
 * 
 * @author Chicu Veaceslav
 */
public class JDPaletteCreationFactory implements CreationFactory {

	/** The template. */
	private Object template;

	/**
	 * Instantiates a new jD palette creation factory.
	 * 
	 * @param t
	 *          the t
	 */
	public JDPaletteCreationFactory(Object t) {
		this.template = t;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getNewObject()
	 */
	public Object getNewObject() {
		if (template == null)
			return null;
		try {
			if (template instanceof Class) {
				return ((Class<?>) template).newInstance();
			} else if (template instanceof MCompositeElement){
				return template;
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
		return template;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.requests.CreationFactory#getObjectType()
	 */
	public Object getObjectType() {
		return template;
	}

}
