/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.OverwriteEnum;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

public abstract class AImpObject {
	protected JasperReportsConfiguration jrConfig;

	public AImpObject(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	protected AFileResource findFile(MReportUnit mrunit, IProgressMonitor monitor, JasperDesign jd, Set<String> fileset,
			JRDesignExpression exp, IFile file) {
		String str = getPath(fileset, exp);
		if (fileset.contains(str)) {
			File f = findFile(file, str);
			if (f != null && f.exists())
				setupSameExpression(mrunit, exp, doPath(getFileName(f)));
			else
				setupSameExpression(mrunit, exp, doPath(str));
			return null;
		}
		if (str == null)
			return null;
		File f = findFile(file, str);
		if (f != null && f.exists()) {
			PublishOptions popt = createOptions(jrConfig, str);
			popt.setjExpression(exp);
			if (!f.getName().contains(":"))
				popt.setExpression("\"repo:" + IDStringValidator.safeChar(getFileName(f)) + "\"");

			fileset.add(str);

			return addResource(monitor, mrunit, fileset, f, popt);
		}
		return null;
	}

	protected String getFileName(File f) {
		String fname = f.getName();
		if (fname.contains("___")) {
			int ind = fname.indexOf("___");
			fname = fname.substring(0, ind );
		} 
		return fname;
	}

	protected String doPath(String path) {
		return path;
	}

	public static void setupSameExpression(MReportUnit mrunit, JRDesignExpression exp, String str) {
		str = "\"repo:" + IDStringValidator.safeChar(str) + "\"";
		PublishOptions popt = mrunit.getPublishOptions();
		if (popt != null && popt.getExpression() != null && popt.getExpression().equals(str)) {
			popt.setjExpression(exp);
			return;
		}
		for (INode n : mrunit.getChildren()) {
			if (n instanceof AFileResource) {
				popt = ((AFileResource) n).getPublishOptions();
				if (popt != null && popt.getExpression() != null && popt.getExpression().equals(str)) {
					popt.setjExpression(exp);
					break;
				}
			}
		}
	}

	public static PublishOptions createOptions(JasperReportsConfiguration jrConfig, String path) {
		PublishOptions popt = new PublishOptions();
		String b = jrConfig.getProperty(JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true");
		if (b.equals("ignore"))
			popt.setOverwrite(OverwriteEnum.IGNORE);
		else if (b.equals("overwrite"))
			popt.setOverwrite(OverwriteEnum.OVERWRITE);
		else if (!b.equals("true") || (path != null && isRemoteResource(path)))
			popt.setOverwrite(OverwriteEnum.IGNORE);
		else
			popt.setOverwrite(OverwriteEnum.OVERWRITE);
		return popt;
	}

	public static boolean isRemoteResource(String path) {
		return path != null && path.startsWith("\\w+?://") && !path.startsWith("file://");
	}

	protected String getPath(Set<String> fileset, JRDesignExpression exp) {
		String str = ExpressionUtil.cachedExpressionEvaluationString(exp, jrConfig);
		return preparePath(fileset, str);
	}

	protected String preparePath(Set<String> fileset, String str) {
		if (str != null && str.startsWith("repo:"))
			str = str.replaceFirst("repo:", "");
		return str;
	}

	protected AFileResource addResource(IProgressMonitor monitor, MReportUnit mrunit, Set<String> fileset, File f,
			PublishOptions popt) {
		ResourceDescriptor runit = mrunit.getValue();
		String rname = getFileName(f);
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

		AMResource res = ResourceFactory.getResource(mrunit, rd, -1);
		String b = jrConfig.getProperty(JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, "true");
		if (b.equals("true") && rd.getIsNew())
			popt.setOverwrite(OverwriteEnum.OVERWRITE);
		res.setPublishOptions(popt);
		if (res instanceof AFileResource) {
			AFileResource mres = (AFileResource) res;
			mres.setFile(f);

			PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
			return mres;
		}
		return null;
	}

	protected File findFile(IFile file, String str) {
		return FileUtils.findFile(file, str, jrConfig);
	}

	public AFileResource publish(JasperDesign jd, JRDesignElement img, MReportUnit mrunit, IProgressMonitor monitor,
			Set<String> fileset, IFile file) throws Exception {
		return findFile(mrunit, monitor, jd, fileset, getExpression(img), file);
	}

	protected abstract ResourceDescriptor createResource(MReportUnit mrunit);

	protected abstract JRDesignExpression getExpression(JRDesignElement img);

}
