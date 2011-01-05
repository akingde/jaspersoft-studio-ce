/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.section;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

/**
 * Abstract class for a section in a tab in the properties view.
 * 
 */
public abstract class AbstractSection extends AbstractPropertySection implements PropertyChangeListener {

	private APropertyNode element;
	private EditDomain editDomain;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		setInputC(part, selection);
	}

	protected void setInputC(IWorkbenchPart part, ISelection selection) {
		if (selection instanceof IStructuredSelection) {
			Assert.isTrue(selection instanceof IStructuredSelection);
			Object input = ((IStructuredSelection) selection).getFirstElement();
			Assert.isTrue(input instanceof EditPart);
			Object model = ((EditPart) input).getModel();
			Assert.isTrue(model instanceof APropertyNode);

			EditorContributor provider = (EditorContributor) part.getAdapter(EditorContributor.class);
			if (provider != null)
				setEditDomain(provider.getEditDomain());
			if (getElement() != model) {
				if (getElement() != null)
					getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
				setElement((APropertyNode) model);
				getElement().getPropertyChangeSupport().addPropertyChangeListener(this);
			}
		}
	}

	public void setElement(APropertyNode element) {
		this.element = element;
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
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
	}

	@Override
	public void dispose() {
		if (getElement() != null)
			getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
		super.dispose();
	}

	/**
	 * Get the element.
	 * 
	 * @return the element.
	 */
	public APropertyNode getElement() {
		return element;
	}

	/**
	 * @see java.beans.PropertyChangeListener#propertyChange(java.beans.PropertyChangeEvent)
	 */
	public void propertyChange(PropertyChangeEvent evt) {
		isRefreshing = true;
		refresh();
		isRefreshing = false;
	}

	protected boolean isRefreshing = false;

	public void changeProperty(String property, Object newValue) {
		changeProperty(property, newValue, getElement());
	}

	public void changeProperty(String property, Object newValue, APropertyNode el) {
		if (!isRefreshing && getElement() != null) {
			Object oldValue = getElement().getPropertyValue(property);
			if (oldValue != null && !newValue.equals(oldValue) && getEditDomain() != null) {
				CommandStack cs = getEditDomain().getCommandStack();

				SetValueCommand setCommand = new SetValueCommand(getElement().getDisplayText());
				setCommand.setTarget(el);
				setCommand.setPropertyId(property);
				setCommand.setPropertyValue(newValue);
				cs.execute(setCommand);
			}
		}
	}
}
