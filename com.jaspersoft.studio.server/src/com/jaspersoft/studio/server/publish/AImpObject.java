/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.publish;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.action.JrxmlPublishAction;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AImpObject {
	private JasperReportsConfiguration jrConfig;

	public AImpObject(JasperReportsConfiguration jrConfig) {
		this.jrConfig = jrConfig;
	}

	protected AFileResource findFile(MReportUnit mrunit,
			IProgressMonitor monitor, JasperDesign jd, Set<String> fileset,
			JRDesignExpression exp, IFile file) {
		String str = ExpressionUtil.eval(exp, jrConfig, jd);
		if (str == null || fileset.contains(str))
			return null;

		File f = findFile(file, str);
		if (f != null && f.exists()) {
			PublishOptions popt = new PublishOptions();
			popt.setjExpression(exp);
			popt.setExpression("repo:" + f.getName());
			fileset.add(str);

			return addResource(mrunit, fileset, f, popt);
		}
		return null;
	}

	protected AFileResource addResource(MReportUnit mrunit,
			Set<String> fileset, File f, PublishOptions popt) {
		ResourceDescriptor runit = mrunit.getValue();
		String rname = f.getName();
		ResourceDescriptor rd = null;
		List<ResourceDescriptor> list = runit.getChildren();
		for (ResourceDescriptor r : list) {
			if (r.getName().equals(rname)) {
				rd = r;
				break;
			}
		}
		if (rd == null) {
			rd = createResource(mrunit);
			rd.setName(rname);
			rd.setLabel(rname);

			rd.setParentFolder(runit.getUriString() + "_files");
			rd.setUriString(runit.getUriString() + "_files/" + rd.getName());
		}

		AFileResource mres = (AFileResource) ResourceFactory.getResource(
				mrunit, rd, -1);
		mres.setFile(f);
		mres.setPublishOptions(popt);

		JrxmlPublishAction.getResources(jrConfig).add(mres);
		return mres;
	}

	protected File findFile(IFile file, String str) {
		SimpleFileResolver fr = new SimpleFileResolver(
				Arrays.asList(new File[] {
						new File(file.getParent().getLocationURI()),
						new File("."),
						new File(file.getProject().getLocationURI()) }));
		fr.setResolveAbsolutePath(true);
		return fr.resolveFile(str);
	}

	public AFileResource publish(JasperDesign jd, JRDesignElement img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		return findFile(mrunit, monitor, jd, fileset, getExpression(img), file);
	}

	protected abstract ResourceDescriptor createResource(MReportUnit mrunit);

	protected abstract JRDesignExpression getExpression(JRDesignElement img);

}
