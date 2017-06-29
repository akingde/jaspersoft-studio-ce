/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.publish.wizard.page;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.utils.IPageCompleteListener;
import com.jaspersoft.studio.server.wizard.resource.page.runit.ReportUnitDatasourceContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorDatasource;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorQueryWithNon;
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
public class DatasourceSelectionPage extends JSSHelpWizardPage implements DatasourceSelectionListener {

	public static final String PAGE_NAME = "ruDatasourceSelectionPage"; //$NON-NLS-1$
	protected JasperReportsConfiguration jConfig;
	private DatasourceSelectionComposite datasourceCmp;

	public DatasourceSelectionPage(JasperReportsConfiguration jConfig) {
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
		tabfolder = new TabFolder(parent, SWT.NONE);

		TabItem tb = new TabItem(tabfolder, SWT.NONE);
		tb.setText(Messages.DatasourceSelectionPage_0);

		Composite cmp = new Composite(tabfolder, SWT.NONE);
		cmp.setLayout(new GridLayout());

		datasourceCmp = new DatasourceSelectionComposite(cmp, SWT.NONE, false,
				new String[] { ResourceDescriptor.TYPE_OLAP_XMLA_CONNECTION });
		datasourceCmp.setLayoutData(new GridData(GridData.FILL_BOTH));

		datasourceCmp.addDatasourceSelectionListener(this);
		tb.setControl(cmp);
		tabfolder.setSelection(tb);

		tb = new TabItem(tabfolder, SWT.NONE);
		tb.setText(Messages.DatasourceSelectionPage_1);

		cmp = new Composite(tabfolder, SWT.NONE);
		cmp.setLayout(new GridLayout(2, false));

		sQuery = new SelectorQueryWithNon();
		sQuery.createControls(cmp, null, null);
		sQuery.addPageCompleteListener(new IPageCompleteListener() {

			@Override
			public void pageCompleted(boolean completed) {
				setPageComplete(isPageComplete());
				if (sQuery.isPageComplete())
					setErrorMessage(null);
				else
					setErrorMessage(Messages.DatasourceSelectionPage_2);
			}
		});
		tb.setControl(cmp);

		setControl(tabfolder);
	}

	public void configurePage(ANode parent, AMResource resource) {
		if (tabfolder.getItemCount() > 1) {
			int oldIndx = tabfolder.getSelectionIndex();
			tabfolder.setSelection(1);
			tabfolder.setSelection(oldIndx);
		}
		if (refresh)
			return;
		if (resource instanceof MReportUnit) {
			sQuery.setResource(resource.getParent(), resource);
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
		datasourceCmp.setExcludeTypes(ReportUnitDatasourceContent.getExcludedTypes(resource));
		datasourceCmp.configurePage(parent, resource);
	}

	@Override
	public boolean isPageComplete() {
		return datasourceCmp != null && datasourceCmp.isDatasourceSelectionValid() && sQuery.isPageComplete();
	}

	private boolean refresh = false;
	private SelectorQueryWithNon sQuery;
	private TabFolder tabfolder;

	@Override
	public void datasourceSelectionChanged() {
		if (refresh)
			return;
		refresh = true;
		setPageComplete(isPageComplete());
		if (datasourceCmp.isDatasourceSelectionValid())
			setErrorMessage(null);
		else
			setErrorMessage(Messages.DatasourceSelectionPage_3);
		refresh = false;
	}
}
