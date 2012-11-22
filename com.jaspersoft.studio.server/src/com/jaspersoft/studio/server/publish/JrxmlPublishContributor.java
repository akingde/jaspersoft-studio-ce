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
package com.jaspersoft.studio.server.publish;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignImage;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlDigesterFactory;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.xml.sax.InputSource;

import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.plugin.IPublishContributor;
import com.jaspersoft.studio.utils.FileUtils;
import com.jaspersoft.studio.utils.JRXMLUtils;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class JrxmlPublishContributor implements IPublishContributor {
	private JasperReportsConfiguration jrConfig;
	private String version;

	public void publishJrxml(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file,
			String version, JasperReportsConfiguration jrConfig)
			throws Exception {
		init(jrConfig, version);

		publishJrxml(mrunit, monitor, jasper, fileset, file);

		publishParameters(mrunit, monitor, jasper, jrConfig);
	}

	public void publishParameters(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, JasperReportsConfiguration jrConfig)
			throws Exception {
		impIC.publish(mrunit, monitor, jasper, jrConfig);
		Activator.getExtManager().publishParameters(mrunit, monitor, jasper,
				jrConfig);
	}

	private void publishJrxml(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file)
			throws Exception {
		List<JRDesignElement> elements = ModelUtils.getAllElements(jasper);
		for (JRDesignElement ele : elements) {
			if (ele instanceof JRDesignImage)
				publishImage(mrunit, monitor, jasper, fileset, file, ele,
						version);
			else if (ele instanceof JRDesignSubreport) {
				publishSubreport(mrunit, monitor, jasper, fileset, file, ele,
						version);
			} else {
				publishElement(mrunit, monitor, jasper, fileset, file, ele,
						version);
			}
		}
		publishTemplates(mrunit, monitor, jasper, fileset, file, version);
		// here extend and give possibility to contribute to plugins
		Activator.getExtManager().publishJrxml(mrunit, monitor, jasper,
				fileset, file, version, jrConfig);
	}

	protected void publishSubreport(MReportUnit mrunit,
			IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, JRDesignElement ele, String version) throws Exception {
		AFileResource fres = impSRP.publish(jasper, ele, mrunit, monitor,
				fileset, file);
		if (fres == null)
			return;

		IFile[] fs = root.findFilesForLocationURI(fres.getFile().toURI());
		if (fs != null && fs.length > 0) {
			InputStream jrxmlInputStream = JRXMLUtils.getJRXMLInputStream(
					jrConfig, fs[0].getContents(), fs[0].getFileExtension(),
					fs[0].getCharset(true), version);
			InputSource is = new InputSource(new InputStreamReader(
					jrxmlInputStream, "UTF-8"));
			JasperDesign jrd = new JRXmlLoader(
					JRXmlDigesterFactory.createDigester()).loadXML(is);
			if (jrd != null) {
				publishJrxml(mrunit, monitor, jrd, fileset, fs[0]);
				File f = FileUtils.createTempFile("jrsres", ".jrxml");
				FileUtils.writeFile(f,
						JRXmlWriterHelper.writeReport(jrConfig, jrd, version));
				fres.setFile(f);
			}
		}
	}

	protected void publishElement(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file,
			JRDesignElement ele, String version) throws Exception {
	}

	protected void publishImage(MReportUnit mrunit, IProgressMonitor monitor,
			JasperDesign jasper, Set<String> fileset, IFile file,
			JRDesignElement ele, String version) throws Exception {
		impImg.publish(jasper, ele, mrunit, monitor, fileset, file);
	}

	protected void publishTemplates(MReportUnit mrunit,
			IProgressMonitor monitor, JasperDesign jasper, Set<String> fileset,
			IFile file, String version) throws Exception {
		for (JRReportTemplate rt : jasper.getTemplatesList()) {
			impStyle.publish(jasper, rt, mrunit, monitor, fileset, file);
		}
	}

	private void init(JasperReportsConfiguration jrConfig, String version) {
		this.jrConfig = jrConfig;
		this.version = version;
		impStyle = new ImpStyleTemplate(jrConfig);
		impImg = new ImpImage(jrConfig);
		impSRP = new ImpSubreport(jrConfig);
		impIC = new ImpInputControls(jrConfig);
	}

	private IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	private ImpStyleTemplate impStyle;
	private ImpImage impImg;
	private ImpSubreport impSRP;
	private ImpInputControls impIC;

}
