/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.server;

import java.io.File;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.AFileResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.publish.PublishOptions;
import com.jaspersoft.studio.server.publish.imp.AImpObject;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.ui.validator.IDStringValidator;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

public abstract class AImpResource extends AImpObject {
	public AImpResource(JasperReportsConfiguration jrConfig) {
		super(jrConfig);
	}

	public AFileResource publish(JasperDesign jd, ItemProperty img, MReportUnit mrunit, IProgressMonitor monitor,
			Set<String> fileset, IFile file) throws Exception {
		String str = img.getValue();
		JRDesignExpression exp = (JRDesignExpression) img.getValueExpression();
		if (exp != null)
			str = getPath(fileset, exp);
		else
			str = preparePath(fileset, str);
		if (fileset.contains(str)) {
			setupSameExpression(mrunit, exp, str);
			return null;
		}
		if (str == null)
			return null;
		File f = findFile(file, str);
		if (f != null && f.exists()) {
			final PublishOptions popt = createOptions(jrConfig, str);

			if (exp != null) {
				popt.setjExpression(exp);
				if (!f.getName().contains(":"))
					popt.setExpression("\"repo:" + IDStringValidator.safeChar(f.getName()) + "\"");
			} else if (Misc.isNullOrEmpty(img.getValue())) {
				popt.setValueSetter(popt.new ValueSetter<StandardItemProperty>((StandardItemProperty) img) {

					@Override
					public void setup() {
						object.setValue(getValue());
					}
				});
				if (!f.getName().contains(":"))
					popt.getValueSetter().setValue("\"repo:" + IDStringValidator.safeChar(f.getName()) + "\"");
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
