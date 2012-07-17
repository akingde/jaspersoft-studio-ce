/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import com.jaspersoft.studio.server.model.MDataType;
import com.jaspersoft.studio.server.model.MFolder;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MJar;
import com.jaspersoft.studio.server.model.MJrxml;
import com.jaspersoft.studio.server.model.MListOfValues;
import com.jaspersoft.studio.server.model.MRDatasourceBean;
import com.jaspersoft.studio.server.model.MRDatasourceCustom;
import com.jaspersoft.studio.server.model.MRDatasourceJDBC;
import com.jaspersoft.studio.server.model.MRDatasourceJNDI;
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
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class AddResourcePage extends WizardPage {
	private MResource resource;
	private ANode parent;

	public AddResourcePage(ANode parent) {
		super("addresource");
		setTitle("Add JasperServer Resource");
		setDescription("Add JasperServer resource");
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
				if (obj != null && obj instanceof MResource)
					resource = (MResource) obj;
			}
		});
		setControl(treeViewer.getControl());
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
				new MReportUnitOptions(root,
						MReportUnitOptions.createDescriptor(parent), -1);
				new MDataType(root, MDataType.createDescriptor(parent), -1);
			}

			new MJrxml(root, MJrxml.createDescriptor(parent), -1);
			new MInputControl(root, MInputControl.createDescriptor(parent), -1);
			new MListOfValues(root, MListOfValues.createDescriptor(parent), -1);
			new MJar(root, MJar.createDescriptor(parent), -1);
			new MResource(root, MResource.createDescriptor(parent), -1);
			new MResourceBundle(root, MResourceBundle.createDescriptor(parent),
					-1);
			new MReference(root, MReference.createDescriptor(parent), -1);
			new MRFont(root, MRFont.createDescriptor(parent), -1);
			new MRImage(root, MRImage.createDescriptor(parent), -1);
			new MRStyleTemplate(root, MRStyleTemplate.createDescriptor(parent),
					-1);
			new MXmlFile(root, MXmlFile.createDescriptor(parent), -1);

			Activator.getExtManager().createNewResource(root, parent);

			// new MUnknown(root, MUnknown.createDescriptor(parent), -1);
		}
		for (INode n : root.getChildren()) {
			((MResource) n).setEditMode(true);
		}

		return root;
	}

	protected void createReportUnit(MRoot root) {
		resource = new MReportUnit(root, MReportUnit.createDescriptor(parent),
				-1);
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
	}
}
