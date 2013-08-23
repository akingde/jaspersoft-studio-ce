/*
 * Copyright (C) 2007  Uwe Voigt
 * 
 * This file is part of Essiembre ResourceBundle Editor.
 * 
 * Essiembre ResourceBundle Editor is free software; you can redistribute it 
 * and/or modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * Essiembre ResourceBundle Editor is distributed in the hope that it will be 
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with Essiembre ResourceBundle Editor; if not, write to the 
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330, 
 * Boston, MA  02111-1307  USA
 */
package com.essiembre.eclipse.rbe.ui.editor.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * The extension point descriptor for the resource factory extension point.
 *  
 * @author Uwe Voigt (http://sourceforge.net/users/uwe_ewald/)
 */
public class ResourceFactoryDescriptor {

	private static final String EXTENSION_POINT_ID = "com.essiembre.eclipse.i18n.resourcebundle.resourceFactory"; //$NON-NLS-1$
	private static final String TAG_FACTORY = "factory"; //$NON-NLS-1$
	private IConfigurationElement fElement;

	private ResourceFactoryDescriptor(IConfigurationElement element) {
		fElement = element;
	}

	/**
	 * Returns new instances of the contributed resource factories order by their
	 * contributed order value.
	 * 
	 * @return
	 * @throws CoreException
	 */
	public static IResourceFactory[] getContributedResourceFactories() throws CoreException {
		ResourceFactoryDescriptor[] descriptors = getContributedResourceFactoryDescriptors();
		SortedMap factories = new TreeMap();
		for (int i = 0, lastOrder = 0; i < descriptors.length; i++) {
			Object factory = descriptors[i].fElement.createExecutableExtension("class"); //$NON-NLS-1$
			String attribute = descriptors[i].fElement.getAttribute("order"); //$NON-NLS-1$
			Integer order = null;
			try {
				order = new Integer(attribute);
			} catch (Exception e) {
				order = new Integer(++lastOrder);
			}
			while (factories.containsKey(order))
				order = new Integer(lastOrder = order.intValue());
			factories.put(order, factory);
		}
		return (IResourceFactory[]) factories.values().toArray(new IResourceFactory[factories.values().size()]);
	}

	private static ResourceFactoryDescriptor[] getContributedResourceFactoryDescriptors() {
		IConfigurationElement[] elements = Platform.getExtensionRegistry()
					.getConfigurationElementsFor(EXTENSION_POINT_ID);
		return createDescriptors(elements);
	}

	private static ResourceFactoryDescriptor[] createDescriptors(IConfigurationElement[] elements) {
		List list = new ArrayList(elements.length);
		for (int i = 0; i < elements.length; i++) {
			IConfigurationElement element = elements[i];
			if (TAG_FACTORY.equals(element.getName())) {
				ResourceFactoryDescriptor descriptor = new ResourceFactoryDescriptor(element);
				list.add(descriptor);
			}
		}
		return (ResourceFactoryDescriptor[]) list.toArray(new ResourceFactoryDescriptor[list.size()]);
	}

}
