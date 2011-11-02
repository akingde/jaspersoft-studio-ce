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

import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.server.WSClientHelper;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.server.MServerProfile;
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

	private IToolBarManager tb;
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

	private void removeActions() {
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
		INode n = res.getRoot();
		if (n != null && n instanceof MServerProfile) {
			try {
				WSClientHelper.saveResource(((MServerProfile) n).getWsClient(),
						res.getValue());
				setEditMode(false);
			} catch (Exception e) {
				UIUtils.showError(e);
			}
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
