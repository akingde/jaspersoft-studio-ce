package com.jaspersoft.studio.server.wizard.resource.page.olap;

import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.utils.UIUtil;

public class SecureMondrianContent extends OLAPMondrianSchemaContent {

	public SecureMondrianContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public SecureMondrianContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public Control createContent(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		UIUtil.createLabel(cmp, "Catalog");

		Text tCatalog = new Text(cmp, SWT.BORDER);
		tCatalog.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		List<ResourceProperty> props = res.getValue().getProperties();
		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(ResourceDescriptor.PROP_XMLA_CATALOG, props);

		bindingContext.bindValue(SWTObservables.observeText(tCatalog, SWT.Modify), PojoObservables.observeValue(resprop, "value"));

		Control c = super.createContent(cmp);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);
		return cmp;
	}

}
