package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MRDatasource;
import com.jaspersoft.studio.server.model.MRDatasourceCustom;

public class RDDatasourcePage extends AResourcePage {

	public RDDatasourcePage(ANode parent, MRDatasource resource) {
		super("rddatasource", parent, resource);
		setTitle("Datasource");
		setDescription("Datasource");
	}

	public RDDatasourcePage(ANode parent, MRDatasourceCustom resource) {
		super("rddatasource", parent, resource);
		setTitle("Datasource");
		setDescription("Datasource");
	}

}
