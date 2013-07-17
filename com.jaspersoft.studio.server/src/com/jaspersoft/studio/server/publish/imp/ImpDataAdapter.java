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
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterParameterContributorFactory;
import net.sf.jasperreports.data.csv.CsvDataAdapter;
import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.data.xls.XlsDataAdapter;
import net.sf.jasperreports.data.xlsx.XlsxDataAdapter;
import net.sf.jasperreports.data.xml.XmlDataAdapter;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.repo.RepositoryUtil;

import org.apache.commons.io.IOUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MRDataAdapter;
import com.jaspersoft.studio.server.model.MRDataAdapterFile;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpDataAdapter extends AImpObject {
	public ImpDataAdapter(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	public File publish(JasperDesign jd, String dpath, MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset, IFile file) throws Exception {
		File f = findFile(file, dpath);
		if (f != null && f.exists()) {
			fileset.add(f.getAbsolutePath());
			AFileResource fr = addResource(mrunit, fileset, f, new PublishOptions());
			jd.setProperty(DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION, "repo:" + fr.getValue().getUriString());
		}
		return f;
	}

	@Override
	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MRDataAdapter.createDescriptor(mrunit);
	}

	@Override
	public AFileResource publish(JasperDesign jd, JRDesignElement img, MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset, IFile file) throws Exception {
		return null;
	}

	@Override
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return null;
	}

	@Override
	protected AFileResource addResource(MReportUnit mrunit, Set<String> fileset, File f, PublishOptions popt) {
		ResourceDescriptor runit = mrunit.getValue();
		String rname = f.getName();
		ResourceDescriptor rd = createResource(mrunit);
		rd.setName(rname);
		rd.setLabel(rname);

		rd.setParentFolder(runit.getParentFolder());
		rd.setUriString(runit.getParentFolder() + "/" + rd.getName());

		AFileResource mres = new MRDataAdapter(mrunit, rd, -1);
		mres.setFile(f);
		mres.setPublishOptions(popt);

		PublishUtil.getResources(jrConfig).add(mres);
		if (true) {
			IProject prj = ((IFile) jrConfig.get(FileUtils.KEY_FILE)).getProject();
			InputStream is = null;
			try {
				is = new FileInputStream(f);
				DataAdapterDescriptor dad = FileDataAdapterStorage.readDataADapter(is, prj);
				if (dad != null) {
					DataAdapter da = dad.getDataAdapter();
					String fname = null;
					if (da instanceof JsonDataAdapter)
						fname = ((JsonDataAdapter) da).getFileName();
					else if (da instanceof CsvDataAdapter)
						fname = ((CsvDataAdapter) da).getFileName();
					else if (da instanceof XmlDataAdapter)
						fname = ((XmlDataAdapter) da).getFileName();
					else if (da instanceof XlsDataAdapter)
						fname = ((XlsDataAdapter) da).getFileName();
					else if (da instanceof XlsxDataAdapter)
						fname = ((XlsxDataAdapter) da).getFileName();
					if (fname != null) {
						InputStream fis = null;
						OutputStream fos = null;
						try {
							fis = RepositoryUtil.getInstance(jrConfig).getInputStreamFromLocation(fname);
							File file = FileUtils.createTempFile("tmp", "");
							fos = new FileOutputStream(file);
							if (fis != null) {
								IOUtils.copy(fis, fos);

								int indx = fname.lastIndexOf(File.separator);
								if (indx >= 0 && indx + 1 < fname.length())
									fname = fname.substring(indx + 1);

								rd = MXmlFile.createDescriptor(mrunit);
								rd.setName(fname);
								rd.setLabel(fname);

								rd.setParentFolder(runit.getParentFolder());
								rd.setUriString(runit.getParentFolder() + "/" + rd.getName());

								mres = new MRDataAdapterFile(mrunit, rd, -1);
								mres.setFile(file);
								mres.setPublishOptions(popt);

								PublishUtil.getResources(jrConfig).add(mres);
							}
						} catch (JRException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						} finally {
							FileUtils.closeStream(fis);
							FileUtils.closeStream(fos);
						}

					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				FileUtils.closeStream(is);
			}
		}
		return mres;
	}
}