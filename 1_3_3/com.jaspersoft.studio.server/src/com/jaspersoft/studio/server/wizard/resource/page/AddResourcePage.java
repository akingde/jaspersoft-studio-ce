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
package com.jaspersoft.studio.server.wizard.resource.page;

import java.text.MessageFormat;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.model.MRoot;
import com.jaspersoft.studio.outline.ReportTreeContetProvider;
import com.jaspersoft.studio.outline.ReportTreeLabelProvider;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MDataAdapter;
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRAccessGrantSchema;
import com.jaspersoft.studio.server.model.MRDashboard;
import com.jaspersoft.studio.server.model.MRFont;
import com.jaspersoft.studio.server.model.MRImage;
import com.jaspersoft.studio.server.model.MRQuery;
import com.jaspersoft.studio.server.model.MRStyleTemplate;
import com.jaspersoft.studio.server.model.MReference;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.MReportUnitOptions;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.MResourceBundle;
import com.jaspersoft.studio.server.model.MXmlFile;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceBean;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceDiagnostic;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJDBC;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJNDI;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceVDS;
import com.jaspersoft.studio.server.model.datasource.MRMondrianSchema;
import com.jaspersoft.studio.server.model.datasource.MROlapMondrianConnection;
import com.jaspersoft.studio.server.model.datasource.MROlapUnit;
import com.jaspersoft.studio.server.model.datasource.MROlapXmlaConnection;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class AddResourcePage extends WizardPage {
	private MResource resource;
	private ANode parent;

	public AddResourcePage(ANode parent) {
		super("addresource"); //$NON-NLS-1$
		setTitle(Messages.AddResourcePage_Title);
		String title = ANode.getIconDescriptor().getTitle();
		if (parent instanceof MServerProfile)
			title = MServerProfile.getIconDescriptor().getTitle();
		else if (parent instanceof MResource)
			title = ((MResource) parent).getThisIconDescriptor().getTitle();
		setDescription(MessageFormat.format(
				Messages.AddResourcePage_Description, title,
				parent.getDisplayText()));
		this.parent = parent;
	}

	public MResource getResource() {
		return resource;
	}

	public void createControl(Composite parent) {
		TreeViewer treeViewer = new TreeViewer(parent, SWT.SINGLE | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		gd.minimumHeight = 300;
		gd.minimumWidth = 400;
		treeViewer.getTree().setLayoutData(gd);
		treeViewer.setContentProvider(new ReportTreeContetProvider());
		treeViewer.setLabelProvider(new ReportTreeLabelProvider());
		treeViewer.setInput(getInput());
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				TreeSelection ts = (TreeSelection) event.getSelection();
				Object obj = ts.getFirstElement();
				if (obj != null && obj instanceof MResource) {
					resource = (MResource) obj;
				}
			}
		});
		setControl(treeViewer.getControl());
		treeViewer.setSelection(new TreeSelection(new TreePath(
				new Object[] { resource })), true);
	}

	private boolean dsonly = false;

	public void setOnlyDatasource(boolean dsonly) {
		this.dsonly = dsonly;
	}

	private boolean ruOnly = false;

	public void setOnlyReportUnit(boolean ruOnly) {
		this.ruOnly = ruOnly;
	}

	private MRoot getInput() {
		MRoot root = new MRoot(null, null);
		if (dsonly) {
			createDatasources(root);
		} else if (ruOnly) {
			createReportUnit(root);
		} else {
			if (parent instanceof MFolder || parent instanceof MServerProfile) {
				new MFolder(root, MFolder.createDescriptor(parent), -1);
				createReportUnit(root);

				createDatasources(root);

				new MDataType(root, MDataType.createDescriptor(parent), -1);
				new MRQuery(root, MRQuery.createDescriptor(parent), -1);

				new MDataType(root, MDataType.createDescriptor(parent), -1);
			}

			new MJrxml(root, MJrxml.createDescriptor(parent), -1);
			new MInputControl(root, MInputControl.createDescriptor(parent), -1);
			new MListOfValues(root, MListOfValues.createDescriptor(parent), -1);
			new MJar(root, MJar.createDescriptor(parent), -1);
			// new MResource(root, MResource.createDescriptor(parent), -1);
			new MResourceBundle(root, MResourceBundle.createDescriptor(parent),
					-1);
			new MReference(root, MReference.createDescriptor(parent), -1);
			new MRFont(root, MRFont.createDescriptor(parent), -1);
			new MRImage(root, MRImage.createDescriptor(parent), -1);
			new MRStyleTemplate(root, MRStyleTemplate.createDescriptor(parent),
					-1);
			new MXmlFile(root, MXmlFile.createDescriptor(parent), -1);
			new MDataAdapter(root, MDataAdapter.createDescriptor(parent), -1);

			new MRDashboard(root, MRDashboard.createDescriptor(parent), -1);
			new MRMondrianSchema(root,
					MRMondrianSchema.createDescriptor(parent), -1);
			new MROlapMondrianConnection(root,
					MROlapMondrianConnection.createDescriptor(parent), -1);
			new MROlapXmlaConnection(root,
					MROlapXmlaConnection.createDescriptor(parent), -1);
			new MROlapUnit(root, MROlapUnit.createDescriptor(parent), -1);
			new MRAccessGrantSchema(root,
					MRAccessGrantSchema.createDescriptor(parent), -1);

			if (parent instanceof MReportUnit) {
				new MReportUnitOptions(root,
						MReportUnitOptions
								.createDescriptor((MReportUnit) parent), -1);
			}

			Activator.getExtManager().createNewResource(root, parent);

			// new MUnknown(root, MUnknown.createDescriptor(parent), -1);
		}
		for (INode n : root.getChildren()) {
			((MResource) n).setEditMode(true);
		}
		if (root.getChildren() != null && !root.getChildren().isEmpty())
			resource = (MResource) root.getChildren().get(0);
		return root;
	}

	protected void createReportUnit(MRoot root) {
		new MReportUnit(root, MReportUnit.createDescriptor(parent), -1);
	}

	protected void createDatasources(MRoot root) {
		new MRDatasourceBean(root, MRDatasourceBean.createDescriptor(parent),
				-1);
		new MRDatasourceJDBC(root, MRDatasourceJDBC.createDescriptor(parent),
				-1);
		new MRDatasourceJNDI(root, MRDatasourceJNDI.createDescriptor(parent),
				-1);
		new MRDatasourceCustom(root,
				MRDatasourceCustom.createDescriptor(parent), -1);
		new MRDatasourceVDS(root, MRDatasourceVDS.createDescriptor(parent), -1);
		new MRDatasourceDiagnostic(root,
				MRDatasourceDiagnostic.createDescriptor(parent), -1);

		Activator.getExtManager().createNewDatasource(root, parent);
	}

	@Override
	public boolean canFlipToNextPage() {
		return resource != null;
	}
}
