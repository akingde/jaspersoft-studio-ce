/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.property.section.report.util.PHolderUtil;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;
import net.sf.jasperreports.parts.subreport.StandardSubreportPartComponent;

public class ImpJRXML {
	private JasperReportsConfiguration jrConfig;

	public ImpJRXML(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	// public MJrxml publish(MReportUnit mrunit, IProgressMonitor monitor,
	// JasperDesign jasper, JasperReportsConfiguration jrConfig) throws
	// Exception
	// {
	// ResourceDescriptor runit = mrunit.getValue();
	//
	// ResourceDescriptor rd = MJrxml.createDescriptor(mrunit);
	// rd.setName(IDStringValidator.safeChar(Misc.nvl(jasper.getName())));
	// rd.setLabel(jasper.getName());
	// rd.setParentFolder(runit.getUriString() + "_files");
	// rd.setUriString(runit.getUriString() + "_files/" + rd.getName());
	//
	// MJrxml mres = (MJrxml) ResourceFactory.getResource(mrunit, rd, -1);
	//
	// mres.setPublishOptions(createOption(jrConf, str));
	//
	// PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
	// return mres;
	// }

	protected File findFile(IFile file, String str) {
		File f = FileUtils.findFile(file,
				StringUtils.replaceAllIns(str, FileExtension.PointJASPER + "$", FileExtension.PointJRXML));
		if (f == null) {
			f = FileUtils.findFile(file, str);
			if (f != null) {
				try {
					Object obj = JRLoader.loadObject(jrConfig, f);
					if (obj != null && obj instanceof JasperReport) {
						JasperReport jrReport = (JasperReport) obj;

						FileOutputStream fos = null;
						try {
							f = FileUtils.getTmpFile(str);
							fos = new FileOutputStream(f);
							JRXmlWriter.writeReport(jrReport, fos, "UTF-8");
							return f;
						} catch (IOException e) {
							FileUtils.closeStream(fos);
							e.printStackTrace();
						}

					}
				} catch (JRException e) {
					e.printStackTrace();
				}
			}
		}
		return f;
	}

	public AFileResource publish(JasperDesign jd, StandardSubreportPartComponent img, MReportUnit mrunit,
			IProgressMonitor monitor, Set<String> fileset, IFile file) throws Exception {
		return findFile(mrunit, monitor, jd, fileset, getExpression(img), file);
	}

	protected AFileResource findFile(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jd, Set<String> fileset,
			JRDesignExpression exp, IFile file) {
		String str = ExpressionUtil.cachedExpressionEvaluationString(exp, jrConfig);
		if (str.startsWith("repo:"))
			str = str.replaceFirst("repo:", "");
		if (str == null || fileset.contains(str)) {
			AImpObject.setupSameExpression(mrunit, exp, str);
			return null;
		}

		File f = findFile(file, str);
		if (f != null && f.exists()) {
			PublishOptions popt = AImpObject.createOptions(jrConfig, str);
			popt.setjExpression(exp);
			if (!f.getName().contains(":"))
				popt.setExpression("\"repo:" + IDStringValidator.safeChar(f.getName()) + "\"");
			fileset.add(str);

			AFileResource res = addResource(monitor, mrunit, fileset, f, popt);
			String desc = jd.getProperty(PHolderUtil.COM_JASPERSOFT_STUDIO_REPORT_DESCRIPTION);
			if (!Misc.isNullOrEmpty(desc))
				res.getValue().setDescription(desc);
			return res;
		}
		return null;
	}

	protected AFileResource addResource(IProgressMonitor monitor, MReportUnit mrunit, Set<String> fileset, File f,
			PublishOptions popt) {
		ResourceDescriptor runit = mrunit.getValue();
		String rname = f.getName();
		if (rname.startsWith("repo:"))
			rname = rname.replaceFirst("repo:", "");
		ResourceDescriptor rd = null;
		List<ResourceDescriptor> list = runit.getChildren();
		String idname = IDStringValidator.safeChar(rname);
		for (ResourceDescriptor r : list) {
			if (r.getName() != null && r.getName().equals(idname)) {
				rd = r;
				break;
			}
		}
		if (rd == null) {
			rd = createResource(mrunit);
			rd.setName(idname);
			rd.setLabel(rname);

			rd.setParentFolder(runit.getUriString() + "_files");
			rd.setUriString(rd.getParentFolder() + "/" + rd.getName());
		}

		AFileResource mres = (AFileResource) ResourceFactory.getResource(mrunit, rd, -1);
		mres.setFile(f);
		mres.setPublishOptions(popt);

		PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
		return mres;
	}

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJrxml.createDescriptor(mrunit);
	}

	protected JRDesignExpression getExpression(StandardSubreportPartComponent img) {
		return (JRDesignExpression) img.getExpression();
	}
}
