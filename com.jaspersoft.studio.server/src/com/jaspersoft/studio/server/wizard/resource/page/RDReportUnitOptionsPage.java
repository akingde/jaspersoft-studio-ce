package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MReportUnitOptions;

public class RDReportUnitOptionsPage extends AResourcePage {

	public RDReportUnitOptionsPage(ANode parent, MReportUnitOptions resource) {
		super("rdrepunitoptions", parent, resource);
		setTitle("Report Unit Options");
		setDescription("JasperServer report units options resource");
	}

}
