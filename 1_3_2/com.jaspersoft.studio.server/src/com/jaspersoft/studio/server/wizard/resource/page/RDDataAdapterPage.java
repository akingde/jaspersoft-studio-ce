package com.jaspersoft.studio.server.wizard.resource.page;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MDataAdapter;

/**
 * Page for creation details of a data adapter resource
 * on a JasperServer instance.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class RDDataAdapterPage extends AFileResourcePage {

	public RDDataAdapterPage(ANode parent, MDataAdapter resource) {
		super("rddataadapterfile", parent, resource); //$NON-NLS-1$
		setTitle(Messages.RDDataAdapterPage_Title);
		setDescription(Messages.RDDataAdapterPage_Description);
	}

	@Override
	protected String[] getFilter() {
		return new String[] { "*.xml" }; //$NON-NLS-1$
	}

}
