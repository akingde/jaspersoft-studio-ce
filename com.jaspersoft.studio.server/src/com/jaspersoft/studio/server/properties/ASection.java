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
package com.jaspersoft.studio.server.properties;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.properties.action.EditCancelAction;
import com.jaspersoft.studio.server.properties.action.EditOkAction;
import com.jaspersoft.studio.server.properties.action.EditPropertyAction;
import com.jaspersoft.studio.utils.UIUtils;

public abstract class ASection extends AbstractPropertySection {
	public ASection() {
		super();
	}

	@Override
	public void createControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setBackground(parent.getDisplay().getSystemColor(
				SWT.COLOR_WHITE));

		createSectionControls(composite, aTabbedPropertySheetPage);
		createActions(aTabbedPropertySheetPage);
		bindingContext = new DataBindingContext();
	}

	protected IToolBarManager tb;
	private EditPropertyAction editAction;
	private EditOkAction saveAction;
	private EditCancelAction cancelAction;

	protected void createActions(
			TabbedPropertySheetPage aTabbedPropertySheetPage) {
		tb = aTabbedPropertySheetPage.getSite().getActionBars()
				.getToolBarManager();
		editAction = (EditPropertyAction) tb.find(EditPropertyAction.ID);
		if (editAction == null) {
			editAction = new EditPropertyAction();
			tb.add(editAction);
		}
		editAction.addSection(this);

		cancelAction = (EditCancelAction) tb.find(EditCancelAction.ID);
		if (cancelAction == null)
			cancelAction = new EditCancelAction();
		cancelAction.addSection(this);

		saveAction = (EditOkAction) tb.find(EditOkAction.ID);
		if (saveAction == null)
			saveAction = new EditOkAction();
		saveAction.addSection(this);
	}

	protected void removeActions() {
		tb.remove(EditPropertyAction.ID);
		tb.remove(EditCancelAction.ID);
		tb.remove(EditOkAction.ID);
	}

	@Override
	public void aboutToBeHidden() {
		removeActions();
		super.aboutToBeHidden();
	}

	protected abstract void createSectionControls(Composite parent,
			TabbedPropertySheetPage aTabbedPropertySheetPage);

	public abstract void enableFields(boolean enable);

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof MResource);
		this.res = (MResource) input;
		rebind();
		setEditMode(res.isEditMode());
	}

	protected void rebind() {
		Object[] bds = bindingContext.getBindings().toArray();
		for (Object obj : bds) {
			Binding b = (Binding) obj;
			bindingContext.removeBinding(b);
			b.dispose();
		}
		bind();
	}

	protected abstract void bind();

	@Override
	public void refresh() {
		bindingContext.updateTargets();
	}

	protected MResource res;
	protected DataBindingContext bindingContext;

	@Override
	public void aboutToBeShown() {
		if (res != null)
			setEditMode(res.isEditMode());
		super.aboutToBeShown();
	}

	public void editProperties() {
		setEditMode(true);
	}

	public void cancelEditProperties() {
		setEditMode(false);
	}

	public void saveProperties() {
		try {
			WSClientHelper.saveResource(res);
			setEditMode(false);
		} catch (Exception e) {
			UIUtils.showError(e);
		}
	}

	protected void setEditMode(boolean edit) {
		removeActions();
		if (edit) {
			tb.add(cancelAction);
			tb.add(saveAction);
		} else {
			tb.add(editAction);
		}
		tb.update(true);
		enableFields(edit);
		res.setEditMode(edit);
	}
}
