package com.jaspersoft.studio.property.section;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;

import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.JRPropertySheetEntry;

public class JDAdvancedSection extends AdvancedPropertySection implements PropertyChangeListener {
	private EditDomain editDomain;
	private APropertyNode element;

	public JDAdvancedSection() {
		super();
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);

		EditorContributor provider = (EditorContributor) part.getAdapter(EditorContributor.class);
		if (provider != null)
			setEditDomain(provider.getEditDomain());

		Assert.isTrue(selection instanceof IStructuredSelection);
		Object input = ((IStructuredSelection) selection).getFirstElement();
		Assert.isTrue(input instanceof EditPart);
		Object model = ((EditPart) input).getModel();
		Assert.isTrue(model instanceof APropertyNode);

		JRPropertySheetEntry propertySheetEntry = new JRPropertySheetEntry(getEditDomain().getCommandStack(), (ANode) model);
		page.setRootEntry(propertySheetEntry);

		this.element = (APropertyNode) model;
	}

	public EditDomain getEditDomain() {
		return editDomain;
	}

	public void setEditDomain(EditDomain editDomain) {
		this.editDomain = editDomain;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		getElement().getPropertyChangeSupport().addPropertyChangeListener(this);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
		getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
	}

	/**
	 * Get the element.
	 * 
	 * @return the element.
	 */
	public APropertyNode getElement() {
		return element;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		refresh();
	}

}
