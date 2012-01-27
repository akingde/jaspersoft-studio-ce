package com.jaspersoft.studio.server.publish;

import java.io.File;

import org.eclipse.core.resources.IFile;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;

public class ImpSubreport extends AImpObject {

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
