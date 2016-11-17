/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.properties.view.validation.ValidationError;

/**
 * An abstract implementation of a section in a tab in the tabbed property sheet
 * page.
 * 
 * @author Anthony Hunter
 */
public abstract class AbstractPropertySection implements ISection {

	/**
	 * The standard label width when labels for sections line up on the left
	 * hand side of the composite.
	 */
	public static final int STANDARD_LABEL_WIDTH = 85;

	/**
	 * The tabbed property sheet page
	 */
	private TabbedPropertySheetPage tabbedPropertySheetPage;

	/**
	 * The current workbench selection.
	 */
	private ISelection selection;

	/**
	 * The current active workbench part.
	 */
	private IWorkbenchPart part;

	/**
	 * Get the widget factory for the property sheet page.
	 * 
	 * @return the widget factory.
	 */
	public TabbedPropertySheetWidgetFactory getWidgetFactory() {
		return tabbedPropertySheetPage.getWidgetFactory();
	}

	/**
	 * Get the current workbench selection.
	 * 
	 * @return the current workbench selection.
	 */
	public ISelection getSelection() {
		return selection;
	}

	/**
	 * @return Returns the part.
	 */
	public IWorkbenchPart getPart() {
		return part;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		this.tabbedPropertySheetPage = aTabbedPropertySheetPage;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		this.selection = selection;
		this.part = part;
		element = null;
		if (selection != null && selection instanceof IStructuredSelection)
			element = ((IStructuredSelection) selection).getFirstElement();
	}

	private Object element;

	@Override
	public Object getElement() {
		return element;
	}

	public TabbedPropertySheetPage getTabbedPropertySheetPage() {
		return tabbedPropertySheetPage;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		/* empty default implementation */
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
		/* empty default implementation */
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	public void dispose() {
		/* empty default implementation */
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#getMinimumHeight()
	 */
	public int getMinimumHeight() {
		return SWT.DEFAULT;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	public boolean shouldUseExtraSpace() {
		return false;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		/* empty default implementation */
	}

	protected void setPart(IWorkbenchPart part) {
		this.part = part;
	}

	protected void setSelection(ISelection selection) {
		this.selection = selection;
	}

	/**
	 * Return if the current section provide dynamic content. This will disable
	 * some optimization since even if the showed section is the same, since the
	 * content is dynamic the size of the page must be recalculated
	 * 
	 * @return true if the content shown in the section can change after its
	 *         creation false otherwise
	 */
	@Override
	public boolean hasDynamicContent() {
		return false;
	}

	public abstract void resetErrors();

	public abstract void showErrors(List<ValidationError> errors);
}
