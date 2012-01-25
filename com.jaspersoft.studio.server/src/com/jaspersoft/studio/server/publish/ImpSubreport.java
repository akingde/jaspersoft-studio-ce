package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignSubreport;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MReportUnit;

public class ImpSubreport extends AImpObject {

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MJrxml.createDescriptor(mrunit);
	}

	protected JRDesignExpression getExpression(JRDesignElement img) {
		return (JRDesignExpression) ((JRDesignSubreport) img).getExpression();
	}
}
