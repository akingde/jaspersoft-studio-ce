/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.utils.jasper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.util.FileResolver;

/**
 * 
 * @author gtoffoli
 */
public class ProxyFileResolver implements FileResolver {

	private List<FileResolver> resolvers = null;

	/**
	 * Add a resolver on top of the list....
	 * 
	 * @param resolver
	 */
	public void addResolver(FileResolver resolver) {
		if (!resolvers.contains(resolver))
			resolvers.add(resolver);
	}

	public void removeResolver(FileResolver resolver) {
		resolvers.remove(resolver);
	}

	public ProxyFileResolver() {
		resolvers = new ArrayList<FileResolver>();
	}

	public ProxyFileResolver(List<FileResolver> resolverList) {
		this();
		resolvers.addAll(resolverList);
	}

	public File resolveFile(String arg0) {
		for (FileResolver res : resolvers) {
			try {
				File f = res.resolveFile(arg0);
				if (f != null)
					return f;
			} catch (Exception ex) {

			}
		}
		return null;
	}

}
