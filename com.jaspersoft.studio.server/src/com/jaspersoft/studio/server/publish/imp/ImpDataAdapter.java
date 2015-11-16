/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import net.sf.jasperreports.data.FileDataAdapter;
import net.sf.jasperreports.data.RepositoryDataLocation;
import net.sf.jasperreports.data.StandardRepositoryDataLocation;
import net.sf.jasperreports.data.json.JsonDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
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
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.FileDataAdapterStorage;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MRDataAdapter;
import com.jaspersoft.studio.server.model.MRDataAdapterFile;
import com.jaspersoft.studio.server.model.MRJson;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.server.protocol.Version;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpDataAdapter extends AImpObject {
	public ImpDataAdapter(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	public File publish(JRDesignDataset jd, String dpath, MReportUnit mrunit,
			IProgressMonitor monitor, Set<String> fileset, IFile file)
			throws Exception {
		dpath = preparePath(fileset, dpath);
		if (dpath == null)
			return null;
		File f = findFile(file, dpath);
		if (f != null && f.exists()) {
			fileset.add(f.getAbsolutePath());
			PublishOptions popt = createOptions(jrConfig, dpath);
			// popt.setDataset(jd);
			AFileResource fr = addResource(monitor, mrunit, fileset, f, popt);
			popt.setValueSetter(popt.new ValueSetter<JRDesignDataset>(
					(JRDesignDataset) jd) {

				@Override
				public void setup() {
					object.setProperty(
							DataAdapterParameterContributorFactory.PROPERTY_DATA_ADAPTER_LOCATION,
							getValue());
				}
			});
			popt.getValueSetter().setValue(
					"repo:" + fr.getValue().getUriString());
		}
		return f;
	}

	@Override
	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MRDataAdapter.createDescriptor(mrunit);
	}

	@Override
	public AFileResource publish(JasperDesign jd, JRDesignElement img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		return null;
	}

	@Override
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return null;
	}

	@Override
	protected AFileResource addResource(IProgressMonitor monitor,
			MReportUnit mrunit, Set<String> fileset, File f, PublishOptions popt) {
		ResourceDescriptor runit = mrunit.getValue();
		String rname = f.getName();
		ResourceDescriptor rd = createResource(mrunit);
		rd.setName(IDStringValidator.safeChar(rname));
		rd.setLabel(rname);

		rd.setParentFolder(runit.getParentFolder());
		rd.setUriString(runit.getParentFolder() + "/" + rd.getName());

		final AFileResource mres = new MRDataAdapter(mrunit, rd, -1);
		mres.setFile(f);
		mres.setPublishOptions(popt);

		PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
		if (true) {
			IProject prj = ((IFile) jrConfig.get(FileUtils.KEY_FILE))
					.getProject();
			FileInputStream is = null;
			try {
				is = new FileInputStream(f);
				final DataAdapterDescriptor dad = FileDataAdapterStorage
						.readDataADapter(is, prj);
				if (dad != null) {
					final DataAdapter da = dad.getDataAdapter();
					String fname = getFileName(da);
					if (fname != null) {
						InputStream fis = null;
						OutputStream fos = null;
						try {
							fis = RepositoryUtil.getInstance(jrConfig)
									.getInputStreamFromLocation(fname);
							File file = FileUtils.createTempFile("tmp", "");
							fos = new FileOutputStream(file);
							if (fis != null) {
								IOUtils.copy(fis, fos);

								fname = fname.replace("\\", "/");

								int indx = fname.lastIndexOf("/");
								if (indx >= 0 && indx + 1 < fname.length())
									fname = fname.substring(indx + 1);

								rd = getResource(da, mrunit);
								rd.setName(IDStringValidator.safeChar(fname));
								rd.setLabel(fname);

								rd.setParentFolder(runit.getParentFolder());
								rd.setUriString(runit.getParentFolder() + "/"
										+ rd.getName());

								MRDataAdapterFile mdaf = new MRDataAdapterFile(
										mrunit, rd, -1);
								mdaf.setFile(file);
								PublishOptions fpopt = createOptions(jrConfig,
										fname);
								mdaf.setPublishOptions(fpopt);
								fpopt.setValueSetter(popt.new ValueSetter<DataAdapter>(
										da) {

									@Override
									public void setup() {
										setFileName(da, value);
										try {
											File f = FileUtils.createTempFile(
													"tmp", "");
											org.apache.commons.io.FileUtils
													.writeStringToFile(
															f,
															DataAdapterManager
																	.toDataAdapterFile(
																			dad,
																			jrConfig));
											mres.setFile(f);
										} catch (IOException e) {
											UIUtils.showError(e);
										}
									}
								});
								fpopt.getValueSetter().setValue(
										"repo:" + rd.getUriString());

								PublishUtil.getResources(mrunit, monitor,
										jrConfig).add(mdaf);

								// setFileName(da, "repo:" + rd.getUriString());
								f = FileUtils.createTempFile("tmp", "");
								org.apache.commons.io.FileUtils
										.writeStringToFile(f,
												DataAdapterManager
														.toDataAdapterFile(dad,
																jrConfig));
								mres.setFile(f);
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

	protected ResourceDescriptor getResource(DataAdapter da, MReportUnit parent) {
		if (Version.isGreaterThan(parent.getWsClient().getServerInfo(), "6")
				&& da instanceof JsonDataAdapter)
			return MRJson.createDescriptor(parent);
		return MXmlFile.createDescriptor(parent);
	}

	protected void setFileName(DataAdapter da, String fname) {
		if (da instanceof FileDataAdapter
				&& ((FileDataAdapter) da).getDataFile() instanceof RepositoryDataLocation)
			((StandardRepositoryDataLocation) ((FileDataAdapter) da)
					.getDataFile()).setLocation(fname);
	}

	protected String getFileName(DataAdapter da) {
		if (da instanceof FileDataAdapter
				&& ((FileDataAdapter) da).getDataFile() instanceof RepositoryDataLocation)
			return ((RepositoryDataLocation) ((FileDataAdapter) da)
					.getDataFile()).getLocation();
		return null;
	}
}
