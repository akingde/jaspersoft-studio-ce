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
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.ExpressionUtil;

public abstract class AImpObject {

	protected File findFile(JasperDesign jd, Set<String> fileset,
			JRDesignExpression exp, IFile file) {
		String str = ExpressionUtil.eval(exp, jd);
		if (str == null || fileset.contains(str))
			return null;

		File f = findFile(file, str);
		if (f != null && f.exists()) {
			fileset.add(str);
			exp.setText("repo:" + f.getName());
			return f;
		} else
			return null;
	}

	protected File findFile(IFile file, String str) {
		SimpleFileResolver fr = new SimpleFileResolver(
				Arrays.asList(new File[] {
						new File(file.getParent().getLocationURI()),
						new File("."),
						new File(file.getProject().getLocationURI()) }));
		fr.setResolveAbsolutePath(true);
		File f = fr.resolveFile(str);
		return f;
	}

	public AFileResource publish(JasperDesign jd, JRDesignElement img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		File f = findFile(jd, fileset, getExpression(img), file);
		return uploadFile(mrunit, monitor, f);
	}

	protected AFileResource uploadFile(MReportUnit mrunit,
			IProgressMonitor monitor, File f) throws Exception {
		if (f != null) {
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
			try {
				WSClientHelper.saveResource(mres, monitor, false);
			} catch (Exception e) {
				mres.getValue().setIsNew(false);
				WSClientHelper.saveResource(mres, monitor, false);
			}
			return mres;
		}
		return null;
	}

	protected abstract ResourceDescriptor createResource(MReportUnit mrunit);

	protected abstract JRDesignExpression getExpression(JRDesignElement img);

}
