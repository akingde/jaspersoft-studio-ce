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
package com.jaspersoft.studio.server.publish.wizard;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page for the selection of a datasource for a resource being
 * created/modified into a remote JasperServer repository.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class DatasourceSelectionPage extends JSSHelpWizardPage {

	public static final String PAGE_NAME = "ruDatasourceSelectionPage"; //$NON-NLS-1$
	private JasperReportsConfiguration jConfig;
	private DatasourceSelectionComposite datasourceCmp;

	protected DatasourceSelectionPage(JasperReportsConfiguration jConfig) {
		super(PAGE_NAME);
		setTitle(Messages.DatasourceSelectionPage_Title);
		setDescription(Messages.DatasourceSelectionPage_Description);
		this.jConfig = jConfig;
	}

	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SELECT_DATASOURCES;
	}

	@Override
	public void createControl(Composite parent) {
		datasourceCmp = new DatasourceSelectionComposite(parent, SWT.NONE, false);
		setControl(datasourceCmp);
	}

	public void configurePage(ANode parent, MResource resource) {
		if (resource instanceof MReportUnit) {
			try {
				ResourceDescriptor oldru = ((MReportUnit) resource).getValue();
				if (SelectorDatasource.getDatasource(oldru) == null) {
					ResourceDescriptor ru = WSClientHelper.getResource(new NullProgressMonitor(), resource, oldru);
					oldru.getChildren().add(SelectorDatasource.getDatasource(ru));
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}

		datasourceCmp.configurePage(parent, resource);
	}
}
