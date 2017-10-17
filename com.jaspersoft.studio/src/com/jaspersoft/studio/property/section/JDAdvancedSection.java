/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.editor.report.EditorContributor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.properties.internal.IHighlightPropertyWidget;
import com.jaspersoft.studio.properties.internal.IWidgetsProviderSection;
import com.jaspersoft.studio.properties.internal.WidgetDescriptor;
import com.jaspersoft.studio.properties.view.AdvancedPropertySection;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.JRPropertySheetEntry;

/**
 * 
 * This class implements the IWidgetsProvider section, even if it dosen't provide any widgets, but the implementation
 * allow to return the selected element. It's pretty important that every section could return the selected element
 *
 */
public class JDAdvancedSection extends AdvancedPropertySection
		implements PropertyChangeListener, IWidgetsProviderSection {

	private EditDomain editDomain;

	private APropertyNode element;

	/**
	 * The last defined root entry
	 */
	private JRPropertySheetEntry rootEntry = null;

	public JDAdvancedSection() {
		super();
	}

	@Override
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		UpdatePageContent(getSelectionList(getSelection()));
	}

	private void UpdatePageContent(IStructuredSelection selection) {
		if (page != null && element != null && getEditDomain() != null) {
			page.selectionChanged(getPart(), selection);
			// Dispose the previous root entry (if one) before to create the new one
			disposeRootEntry();
			rootEntry = new JRPropertySheetEntry(getEditDomain().getCommandStack(), (ANode) element);
			page.setRootEntry(rootEntry);
		}
	}

	/**
	 * Dispose the root entry if it wasen't already disposed
	 */
	private void disposeRootEntry() {
		if (rootEntry != null) {
			rootEntry.dispose();
			rootEntry = null;
		}
	}

	/**
	 * Extract a selection of APropertyNode from the current selection
	 * 
	 * @param currentSelection
	 *          a not null IStructuredSelection
	 * @return a not null StructuredSelection composed only of AProperyNode
	 */
	private IStructuredSelection getSelectionList(ISelection currentSelection) {
		List<APropertyNode> result = new ArrayList<APropertyNode>();
		Assert.isTrue(currentSelection instanceof IStructuredSelection);
		IStructuredSelection selectionList = (IStructuredSelection) currentSelection;
		for (Object obj : selectionList.toArray()) {
			if (obj instanceof EditPart) {
				EditPart part = (EditPart) obj;
				if (part.getModel() instanceof APropertyNode) {
					result.add((APropertyNode) part.getModel());
				}
			}
		}
		return new StructuredSelection(result);
	}

	@Override
	public void setInput(IWorkbenchPart part, ISelection selection) {
		setPart(part);
		setSelection(selection);
		if (!(selection.isEmpty()) && selection instanceof IStructuredSelection) {
			EditorContributor provider = (EditorContributor) part.getAdapter(EditorContributor.class);
			if (provider != null)
				setEditDomain(provider.getEditDomain());

			this.element = null;
			IStructuredSelection selectionList = getSelectionList(selection);
			if (!selectionList.isEmpty()) {
				this.element = (APropertyNode) selectionList.getFirstElement();
				UpdatePageContent(selectionList);
			}
		}
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
		super.aboutToBeShown();
		TabbedPropertySheetPage tp = getTabbedPropertySheetPage();
		if (tp != null) {
			if (getElement() != null)
				getElement().getPropertyChangeSupport().addPropertyChangeListener(this);
			if (tp.getSite() != null) {
				IActionBars actionBars = tp.getSite().getActionBars();
				if (actionBars != null)
					actionBars.getToolBarManager().removeAll();
				page.makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(),
						actionBars.getStatusLineManager());
				actionBars.updateActionBars();
			}
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
		TabbedPropertySheetPage tp = getTabbedPropertySheetPage();
		if (tp != null) {
			if (getElement() != null)
				getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
			if (tp.getSite() != null) {
				IActionBars actionBars = tp.getSite().getActionBars();
				if (actionBars != null) {
					actionBars.getToolBarManager().removeAll();
					actionBars.updateActionBars();
				}
			}
		}
		//When the page is hidden dispose the root entry. Doing this the listener placed in the stack
		//by them are removed (and any other listeners). This will avoid to trigger listener when the page
		//is not visible, also the root entry will be recreated when the page is switched trough the 
		//setInput method
		disposeRootEntry();
	}

	/**
	 * Get the element.
	 * 
	 * @return the element.
	 */
	public APropertyNode getElement() {
		return element;
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if (getElement() != evt.getSource()) {
			getElement().getPropertyChangeSupport().removePropertyChangeListener(this);
			if (!JSSCompoundCommand.isRefreshEventsIgnored(getElement())) {
				refresh();
			}
			getElement().getPropertyChangeSupport().addPropertyChangeListener(this);
		}
	}

	private boolean isRefreshing = false;

	@Override
	public void refresh() {
		if (isRefreshing)
			return;
		isRefreshing = true;
		if (page != null) {
			// Must be executed inside a thread since it refresh widgets
			UIUtils.getDisplay().syncExec(new Runnable() {

				@Override
				public void run() {
					page.refresh();
				}
			});

		}
		isRefreshing = false;
	}

	@Override
	public Object getSelectedElement() {
		return getElement();
	}

	@Override
	public List<Object> getHandledProperties() {
		return new ArrayList<Object>();
	}

	@Override
	public IHighlightPropertyWidget getWidgetForProperty(Object propertyId) {
		return null;
	}

	@Override
	public WidgetDescriptor getPropertyInfo(Object propertyId) {
		return null;
	}

	@Override
	public void expandForProperty(Object propertyId) {
	}

}
