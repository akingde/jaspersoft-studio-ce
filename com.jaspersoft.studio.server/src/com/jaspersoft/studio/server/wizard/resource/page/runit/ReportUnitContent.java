/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.server.wizard.resource.page.selector.SelectorJrxml;
import com.jaspersoft.studio.utils.UIUtils;

public class ReportUnitContent extends APageContent {

	public ReportUnitContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public ReportUnitContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getName() {
		return Messages.RDReportUnitPage_reportunit;
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.runit";
	}

	@Override
	public boolean isPageComplete() {
		if (res != null)
			return SelectorJrxml.getMainReport(res.getValue()) != null;
		return false;
	}

	@Override
	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		SelectorJrxml selectorJrxml = new SelectorJrxml();
		selectorJrxml.createControls(composite, pnode, res);
		selectorJrxml.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setPageComplete(isPageComplete());
			}
		});

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
		return composite;
	}

	protected ReportProxy getProxy(ResourceDescriptor rd) {
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
}
