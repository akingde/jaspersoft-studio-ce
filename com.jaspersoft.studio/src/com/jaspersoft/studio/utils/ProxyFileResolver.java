/*
 * iReport - Visual Designer for JasperReports. Copyright (C) 2002 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.utils;

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
			resolvers.add(0, resolver);
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
