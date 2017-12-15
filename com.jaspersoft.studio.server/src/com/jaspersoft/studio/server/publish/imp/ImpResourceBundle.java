/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Set;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpResourceBundle extends AImpObject {
	public ImpResourceBundle(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	public File publish(JasperReportsConfiguration jConfig, JasperDesign jd,
			String dpath, MReportUnit mrunit, IProgressMonitor monitor,
			Set<String> fileset, IFile file) throws Exception {
		File f = null;
		URL url = jConfig.getClassLoader().getResource(dpath + ".properties");
		if (url != null)
			try {
				f = new File(url.toURI());
			} catch (URISyntaxException e) {
				f = findFile(file, dpath + ".properties");
			}
		else
			f = findFile(file, dpath + ".properties");
		if (f != null && f.exists()) {
			fileset.add(f.getAbsolutePath());
			addResource(monitor, mrunit, fileset, f,
					createOptions(jrConfig, f.getAbsolutePath()));
		}
		return f;
	}

	@Override
	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MResourceBundle.createDescriptor(mrunit);
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

}
