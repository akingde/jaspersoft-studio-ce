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
package com.jaspersoft.studio.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

import com.jaspersoft.ireport.jasperserver.ws.WSClient;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.export.JrxmlExporter;
import com.jaspersoft.studio.server.utils.ReferenceResolver;
import com.jaspersoft.studio.utils.CacheMap;

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

	public JSFileResolver(JasperDesign jasperDesign, IProgressMonitor monitor) {
		this(new ArrayList<File>(), jasperDesign, monitor);
	}

	public JSFileResolver(List<File> parentFolders, JasperDesign jDesign,
			IProgressMonitor monitor) {
		super(parentFolders);
		this.jDesign = jDesign;
		init(monitor);

		setResolveAbsolutePath(true);
	}

	private void init(IProgressMonitor monitor) {
		serverUri = jDesign.getProperty(JrxmlExporter.PROP_SERVERURL);
		if (serverUri != null)
			try {
				c = ServerManager.getServer(serverUri, monitor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		runitUri = jDesign.getProperty(JrxmlExporter.PROP_REPORTUNIT);
	}

	private Map<String, File> map = new CacheMap<String, File>(30000);

	@Override
	public File resolveFile(String fileName) {
		if (c == null) {
			Job job = new Job("Initialize connection") {
				protected IStatus run(IProgressMonitor monitor) {
					init(monitor);
					return Status.OK_STATUS;
				}
			};
			job.setPriority(Job.SHORT);
			job.setSystem(true);
			job.schedule();
		}
		if (c != null && fileName.startsWith("repo:")) {
			File f = map.get(fileName);
			if (f != null)
				return f;
			String objectUri = fileName.substring(5);
			try {
				if (objectUri.contains("/")) {
					// Locate the resource inside the repository...
					ResourceDescriptor r = new ResourceDescriptor();
					r.setUriString(objectUri);
					r = c.get(r, null);
					if (r.getIsReference())
						r = ReferenceResolver.resolveReference(c, r, null);

					f = File.createTempFile("jrsfr", r.getName());
					c.get(r, f);
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
						if (r.getName() == null
								|| !r.getName().equals(objectUri))
							continue;
						if (r.getIsReference())
							r = ReferenceResolver.resolveReference(c, r, null);
						if (isFileResource(r)) {
							f = File.createTempFile("jrsfr", r.getName());
							c.get(r, f);
							break;
						}
					}
				}
			} catch (Exception ex) {
			}
			if (f != null) {
				map.put(fileName, f);
				return f;
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
