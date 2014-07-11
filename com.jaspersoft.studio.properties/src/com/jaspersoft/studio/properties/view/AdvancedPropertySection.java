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
package com.jaspersoft.studio.properties.view;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;

/**
 * An advanced section that is intended to show the original table format
 * properties view provided by base Eclipse.
 * 
 * @author Anthony Hunter
 */
public class AdvancedPropertySection extends AbstractPropertySection {

	/**
	 * The Property Sheet Page.
	 */
	protected PropertySheetPage page;
	
	protected Composite composite;
	
	protected GridData treeData;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(1,false));
		GridData rd = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(rd);
		page = new PropertySheetPage();
		page.createControl(composite);
		treeData = new GridData();
		treeData.grabExcessHorizontalSpace = true;
		treeData.grabExcessVerticalSpace = true;
		treeData.minimumHeight = 1;
		treeData.minimumWidth = 1;
		treeData.widthHint = SWT.DEFAULT;
		treeData.heightHint = SWT.DEFAULT;
		treeData.horizontalAlignment = SWT.FILL;
		treeData.verticalAlignment = SWT.FILL;
		page.getControl().setLayoutData(treeData);
	}
	
	@Override
	public void aboutToBeShown() {
		setupComposite();
	}

	protected void setupComposite() {
		if (!composite.isDisposed()){
			Point size = getTabbedPropertySheetPage().getTabbedPropertyComposite().getScrolledComposite().getSize();
			//System.out.println(size);
			GridData rd = new GridData(GridData.FILL_HORIZONTAL);
			rd.heightHint = size.y - 30;
			composite.setLayoutData(rd);
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (page != null)
			page.selectionChanged(part, selection);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	public void dispose() {
		super.dispose();

		if (page != null) {
			page.dispose();
			page = null;
		}

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		if (page != null)
			page.refresh();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	public boolean shouldUseExtraSpace() {
		return true;
	}

}
