/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.utils.UIUtils;

public class RDReportUnitPage extends AResourcePage {

	public RDReportUnitPage(ANode parent, MReportUnit resource) {
		super(Messages.RDReportUnitPage_id, parent, resource);
		setTitle(Messages.RDReportUnitPage_title);
		setDescription(Messages.RDReportUnitPage_desc);
	}

	@Override
	protected void createTabs(TabFolder tabFolder) {
		super.createTabs(tabFolder);
		createReportUnit(tabFolder);

		new SelectorDatasource().createDatasource(tabFolder, parent, res);
		createQuery(tabFolder);
		createReportUnitControls(tabFolder);
	}

	protected void createReportUnit(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDReportUnitPage_reportunit);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		new SelectorJrxml().createControls(composite, parent, res);

		Label lbl = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		lbl.setLayoutData(gd);

		UIUtils.createLabel(composite, Messages.RDReportUnitPage_jspforrepview);

		Text jspview = new Text(composite, SWT.BORDER);
		jspview.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		jspview.setToolTipText(Messages.RDReportUnitPage_within);

		ReportProxy v = getProxy(res.getValue());
		bindingContext.bindValue(
				SWTObservables.observeText(jspview, SWT.Modify),
				PojoObservables.observeValue(v, "jspView")); //$NON-NLS-1$

		res.getChildren();
	}

	protected void createReportUnitControls(TabFolder tabFolder) {
		TabItem item = new TabItem(tabFolder, SWT.NONE);
		item.setText(Messages.RDReportUnitPage_inputcontrols);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		item.setControl(composite);

		UIUtils.createLabel(composite, Messages.RDReportUnitPage_controlslayout);

		Combo cictype = new Combo(composite, SWT.BORDER);
		cictype.setItems(new String[] { Messages.RDReportUnitPage_popupscreen, Messages.RDReportUnitPage_separatepage,
				Messages.RDReportUnitPage_topofpage, Messages.RDReportUnitPage_inpage });

		UIUtils.createLabel(composite, ""); //$NON-NLS-1$

		Button ispromp = new Button(composite, SWT.CHECK);
		ispromp.setText(Messages.RDReportUnitPage_alwaysprompt);
		ispromp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, Messages.RDReportUnitPage_jsptoruninputcontrol);

		Text jspic = new Text(composite, SWT.BORDER);
		jspic.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		jspic.setToolTipText(Messages.RDReportUnitPage_withintooltip);

		ReportProxy v = getProxy(res.getValue());
		bindingContext.bindValue(
				SWTObservables.observeSingleSelectionIndex(cictype),
				PojoObservables.observeValue(v, "layoutControl")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(jspic, SWT.Modify),
				PojoObservables.observeValue(v, "jspIC")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeSelection(ispromp),
				PojoObservables.observeValue(v, "allowPrompt")); //$NON-NLS-1$

		res.getChildren();
	}

	private ReportProxy getProxy(ResourceDescriptor rd) {
		proxy.setResourceDescriptor(rd);
		return proxy;
	}

	private ReportProxy proxy = new ReportProxy();

	class ReportProxy {
		private ResourceDescriptor rd;

		public void setResourceDescriptor(ResourceDescriptor rd) {
			this.rd = rd;
		}

		private int[] layouts = new int[] {
				ResourceDescriptor.RU_CONTROLS_LAYOUT_POPUP_SCREEN,
				ResourceDescriptor.RU_CONTROLS_LAYOUT_SEPARATE_PAGE,
				ResourceDescriptor.RU_CONTROLS_LAYOUT_TOP_OF_PAGE, 4 };

		public void setLayoutControl(int lang) {
			rd.setResourceProperty(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT,
					layouts[lang]);
		}

		public int getLayoutControl() {
			Integer v = rd
					.getResourcePropertyValueAsInteger(ResourceDescriptor.PROP_RU_CONTROLS_LAYOUT);
			int lc = v == null ? -1 : v;
			for (int i = 0; i < layouts.length; i++)
				if (layouts[i] == lc)
					return i;
			return -1;
		}

		public void setJspIC(String lang) {
			rd.setResourceProperty(
					ResourceDescriptor.PROP_RU_INPUTCONTROL_RENDERING_VIEW,
					lang);
		}

		public String getJspIC() {
			return rd
					.getResourcePropertyValue(ResourceDescriptor.PROP_RU_INPUTCONTROL_RENDERING_VIEW);
		}

		public void setJspView(String lang) {
			rd.setResourceProperty(
					ResourceDescriptor.PROP_RU_REPORT_RENDERING_VIEW, lang);
		}

		public String getJspView() {
			return rd
					.getResourcePropertyValue(ResourceDescriptor.PROP_RU_REPORT_RENDERING_VIEW);
		}

		public void setAllowPrompt(boolean lang) {
			rd.setResourceProperty(
					ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS, lang);
		}

		public boolean isAllowPrompt() {
			Boolean b = rd
					.getResourcePropertyValueAsBoolean(ResourceDescriptor.PROP_RU_ALWAYS_PROPMT_CONTROLS);
			return b != null && b;
		}
	}

	protected void createQuery(TabFolder tabFolder) {
		ResourceDescriptor rd = res.getValue();
		for (Object obj : rd.getChildren()) {
			ResourceDescriptor r = (ResourceDescriptor) obj;
			if (r.getWsType().equals(ResourceDescriptor.TYPE_QUERY)) {
				RDQueryPage.createDatasourceTab(bindingContext, tabFolder, r);
			}
		}
	}
}
