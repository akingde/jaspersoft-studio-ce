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

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import org.eclipse.core.resources.IFile;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ImpSubreport extends AImpObject {

	public ImpSubreport(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	@Override
	protected File findFile(IFile file, String str) {
		File f = super.findFile(file, str);
		if (f == null && str.endsWith(".jasper")) {
			str = str.replaceAll(".jasper", ".jrxml");
			f = super.findFile(file, str);
		}
		return f;
	}

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJrxml.createDescriptor(mrunit);
	}

	protected JRDesignExpression getExpression(JRDesignElement img) {
		return (JRDesignExpression) ((JRDesignSubreport) img).getExpression();
	}
}
