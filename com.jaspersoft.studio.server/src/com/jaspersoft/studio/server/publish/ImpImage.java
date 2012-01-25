package com.jaspersoft.studio.server.publish;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignImage;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MReportUnit;

public class ImpImage extends AImpObject {
	protected JRDesignExpression getExpression(JRDesignElement img) {
		return (JRDesignExpression) ((JRDesignImage) img).getExpression();
	}

	protected ResourceDescriptor createResource(MReportUnit mrunit) {
		return MRImage.createDescriptor(mrunit);
	}

}
