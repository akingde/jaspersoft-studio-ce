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
package com.jaspersoft.studio.components.customvisualization.server;

import java.io.File;
import java.util.Set;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperreports.customvisualization.CVItemProperty;
import com.jaspersoft.jasperreports.customvisualization.design.CVDesignItemProperty;
import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.imp.AImpObject;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public abstract class AImpResource extends AImpObject {
	public AImpResource(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	public AFileResource publish(JasperDesign jd, CVItemProperty img,
			MReportUnit mrunit, IProgressMonitor monitor, Set<String> fileset,
			IFile file) throws Exception {
		String str = img.getValue();
		JRDesignExpression exp = (JRDesignExpression) img.getValueExpression();
		if (exp != null)
			str = getPath(fileset, exp);
		else
			str = preparePath(fileset, str);
		if (str == null)
			return null;
		File f = findFile(file, str);
		if (f != null && f.exists()) {
			final PublishOptions popt = createOptions(jrConfig, str);

			if (exp != null) {
				popt.setjExpression(exp);
				if (!f.getName().contains(":"))
					popt.setExpression("repo:" + f.getName());
			} else if (Misc.isNullOrEmpty(img.getValue())) {
				popt.setValueSetter(popt.new ValueSetter<CVDesignItemProperty>(
						(CVDesignItemProperty) img) {

					@Override
					public void setup() {
						object.setValue(getValue());
					}
				});
				if (!f.getName().contains(":"))
					popt.getValueSetter().setValue(
							"\"repo:" + f.getName() + "\"");
			}
			fileset.add(str);

			return addResource(monitor, mrunit, fileset, f, popt);
		}
		return null;
	}

	@Override
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return null;
	}

}
