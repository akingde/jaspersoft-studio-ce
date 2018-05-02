/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.imp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.FileExtension;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlWriter;

public class ImpSubreport extends AImpObject {

	public ImpSubreport(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	@Override
	protected File findFile(IFile file, String str) {
		File f = super.findFile(file, doPath(str));
		if (f == null) {
			f = super.findFile(file, str);
			if (f != null) {
				try {
					Object obj = JRLoader.loadObject(jrConfig, f);
					if (obj instanceof JasperReport) {
						f = FileUtils.getTmpFile(str);
						try (FileOutputStream fos = new FileOutputStream(f);) {
							JRXmlWriter.writeReport((JasperReport) obj, fos, "UTF-8");
							return f;
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (JRException | IOException e) {
					e.printStackTrace();
				}
			}
		}
		return f;
	}

	@Override
	protected String doPath(String path) {
		return FileExtension.getJRXMLFileName(path);
	}

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJrxml.createDescriptor(mrunit);
	}

	protected JRDesignExpression getExpression(JRDesignElement img) {
		JRDesignExpression exp = (JRDesignExpression) ((JRDesignSubreport) img).getExpression();
		if (exp != null && !Misc.isNullOrEmpty(exp.getText())) {
			exp.setText(FileExtension.getJRXMLFileName(exp.getText()));
			return exp;
		}
		return (JRDesignExpression) ((JRDesignSubreport) img).getExpression();
	}
}
