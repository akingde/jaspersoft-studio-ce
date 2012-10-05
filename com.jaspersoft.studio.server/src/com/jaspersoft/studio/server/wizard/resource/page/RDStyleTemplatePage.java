package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AFileResource;

/**
 * Page for creation details of a new style template resource
 * on a JasperServer instance.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class RDStyleTemplatePage extends AFileResourcePage {

	public RDStyleTemplatePage(ANode parent, AFileResource resource) {
		super("rdstyletemplate", parent, resource);
		setTitle("Style Template file");
		setDescription("Style Template resource");
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.jrtx"};
	}

}
