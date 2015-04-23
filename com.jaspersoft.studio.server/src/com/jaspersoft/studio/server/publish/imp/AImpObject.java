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
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.preferences.JRSPreferencesPage;
import com.jaspersoft.studio.server.publish.OverwriteEnum;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.PublishUtil;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AImpObject {
	protected JasperReportsConfiguration jrConfig;

	public AImpObject(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	protected AFileResource findFile(MReportUnit mrunit,
			IProgressMonitor monitor, JasperDesign jd, Set<String> fileset,
			JRDesignExpression exp, IFile file) {
		String str = getPath(fileset, exp);
		if (fileset.contains(str))
			return null;
		if (str == null)
			return null;
		File f = findFile(file, str);
		if (f != null && f.exists()) {
			PublishOptions popt = createOptions(jrConfig, str);
			popt.setjExpression(exp);
			if (!f.getName().contains(":"))
				popt.setExpression("\"repo:"
						+ IDStringValidator.safeChar(f.getName()) + "\"");

			fileset.add(str);

			return addResource(monitor, mrunit, fileset, f, popt);
		}
		return null;
	}

	public static PublishOptions createOptions(
			JasperReportsConfiguration jrConfig, String path) {
		PublishOptions popt = new PublishOptions();
		Boolean b = jrConfig.getPropertyBoolean(
				JRSPreferencesPage.PUBLISH_REPORT_OVERRIDEBYDEFAULT, true);
		if (!b || (path != null && isRemoteResource(path)))
			popt.setOverwrite(OverwriteEnum.IGNORE);
		else
			popt.setOverwrite(OverwriteEnum.OVERWRITE);
		return popt;
	}

	public static boolean isRemoteResource(String path) {
		if (path == null)
			return false;
		if (path.startsWith("\\w+?://") && !path.startsWith("file://"))
			return true;
		return false;
	}

	protected String getPath(Set<String> fileset, JRDesignExpression exp) {
		String str = ExpressionUtil.cachedExpressionEvaluationString(exp,
				jrConfig);
		return preparePath(fileset, str);
	}

	protected String preparePath(Set<String> fileset, String str) {
		if (str != null && str.startsWith("repo:"))
			str = str.replaceFirst("repo:", "");
		if (str == null || fileset.contains(str))
			return null;
		return str;
	}

	protected AFileResource addResource(IProgressMonitor monitor,
			MReportUnit mrunit, Set<String> fileset, File f, PublishOptions popt) {
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

		MResource res = ResourceFactory.getResource(mrunit, rd, -1);
		if (res instanceof AFileResource) {
			AFileResource mres = (AFileResource) res;
			mres.setFile(f);
			mres.setPublishOptions(popt);

			PublishUtil.getResources(mrunit, monitor, jrConfig).add(mres);
			return mres;
		}
		return null;
	}

	protected File findFile(IFile file, String str) {
		return FileUtils.findFile(file, str, jrConfig);
	}

	public AFileResource publish(JasperDesign jd, JRDesignElement img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		return findFile(mrunit, monitor, jd, fileset, getExpression(img), file);
	}

	protected abstract ResourceDescriptor createResource(MReportUnit mrunit);

	protected abstract JRDesignExpression getExpression(JRDesignElement img);

}
