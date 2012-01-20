/*
 * iReport - Visual Designer for JasperReports.
 * Copyright (C) 2002 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */

package com.jaspersoft.studio.server;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.export.JrxmlExporter;

/**
 * 
 * @version $Id: JSFileResolver.java 0 2010-05-27 21:50:17 CET gtoffoli $
 * @author Giulio Toffoli (giulio@jaspersoft.com)
 * 
 */
public class JSFileResolver extends SimpleFileResolver {
	private WSClient c;
	private String serverUri;
	private JasperDesign jDesign;
	private String runitUri;

	private List<ResourceDescriptor> reportUnitResources = null;

	public JSFileResolver(File parentFolder, JasperDesign jasperDesign) {
		this(Arrays.asList(parentFolder), jasperDesign);
	}

	public JSFileResolver(List<File> parentFolders, JasperDesign jDesign) {
		super(parentFolders);
		this.jDesign = jDesign;
		init();

		setResolveAbsolutePath(true);
	}

	private void init() {
		serverUri = jDesign.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (serverUri != null)
			c = ServerManager.getServer(serverUri);
		runitUri = jDesign.getProperty(JrxmlExporter.PROP_REPORTUNIT);
	}

	@Override
	public File resolveFile(String fileName) {
		if (c != null && fileName.startsWith("repo:")) {
			String objectUri = fileName.substring(5);
			try {
				if (objectUri.contains("/")) {
					// Locate the resource inside the repository...
					ResourceDescriptor r = new ResourceDescriptor();
					r.setUriString(objectUri);
					r = c.get(r, null);

					File f = File.createTempFile("jrsfr", r.getName());
					c.get(r, f);
					return f;
				} else if (runitUri != null) {
					// Locate the resource inside the report unit, if any...
					if (reportUnitResources == null) {
						ResourceDescriptor rd = new ResourceDescriptor();
						rd.setWsType(ResourceDescriptor.TYPE_REPORTUNIT);
						rd.setUriString(runitUri);
						rd = c.get(rd, null);
						reportUnitResources = c.list(rd);
						if (reportUnitResources == null) {
							reportUnitResources = new ArrayList<ResourceDescriptor>();
						}
					}

					// find the resource...
					for (ResourceDescriptor r : reportUnitResources) {
						if (r.getName().equals(objectUri) && isFileResource(r)) {
							File f = File.createTempFile("jrsfr", r.getName());
							c.get(r, f);
							return f;
						}
					}
					// System.out.println("Resource " + objectUri
					// + " not found in the JasperServer Report at "
					// + runitUri);
				}
			} catch (Exception ex) {
				// System.out.println("Unable to resolve " + objectUri + " on "
				// + server.getName() + "server ( " + ex.getMessage()
				// + ")");
			}
		}
		return super.resolveFile(fileName);
	}

	protected boolean isFileResource(ResourceDescriptor r) {
		String t = r.getWsType();
		return t.equals(ResourceDescriptor.TYPE_IMAGE)
				||
				// resource.getWsType().equals(ResourceDescriptor.TYPE_JRXML)
				// ||
				t.equals(ResourceDescriptor.TYPE_RESOURCE_BUNDLE)
				|| t.equals(ResourceDescriptor.TYPE_STYLE_TEMPLATE);
	}
}
