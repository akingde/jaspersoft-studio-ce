/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.runit;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.publish.wizard.DatasourceSelectionComposite;
import com.jaspersoft.studio.server.publish.wizard.DatasourceSelectionListener;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;

public class ReportUnitDatasourceContent extends APageContent implements DatasourceSelectionListener {
	private boolean mandatory = false;
	private DatasourceSelectionComposite datasourceSelectionCmp;

	public ReportUnitDatasourceContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ReportUnitDatasourceContent(ANode parent, MResource resource, boolean mandatory) {
		this(parent, resource);
		this.mandatory = mandatory;
	}

	public ReportUnitDatasourceContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getName() {
		return Messages.SelectorDatasource_TabTitle;
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.runit.datasource";
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.editReportUnitDSContent";
	}

	@Override
	public Control createContent(Composite parent) {
		datasourceSelectionCmp = new SelectorDatasource().createDatasource(parent, pnode, res, mandatory);
		datasourceSelectionCmp.addDatasourceSelectionListener(this);
		return datasourceSelectionCmp;
	}

	@Override
	public boolean isPageComplete() {
		return datasourceSelectionCmp != null && datasourceSelectionCmp.isDatasourceSelectionValid();
	}

	@Override
	public void datasourceSelectionChanged() {
		setPageComplete(isPageComplete());
	}
}
