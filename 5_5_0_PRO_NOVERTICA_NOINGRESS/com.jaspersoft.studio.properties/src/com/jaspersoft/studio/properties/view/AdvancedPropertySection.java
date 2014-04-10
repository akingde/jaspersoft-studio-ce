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
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
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

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);

		parent.setLayout(new RowLayout());

		composite = getWidgetFactory().createComposite(parent);
		composite.setLayout(new GridLayout(1,false));

		page = new PropertySheetPage();

		page.createControl(composite);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.grabExcessVerticalSpace = true;
		data.minimumHeight = 1;
		data.minimumWidth = 1;
		data.widthHint = SWT.DEFAULT;
		data.heightHint = SWT.DEFAULT;
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.FILL;
		page.getControl().setLayoutData(data);
		
		page.getControl().addControlListener(new ControlAdapter() {

			public void controlResized(ControlEvent e) {
				setupComposite();
				atabbedPropertySheetPage.resizeScrolledComposite();
			}
		});

		parent.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				setupComposite();
			}
		});
		setupComposite();
	}

	protected void setupComposite() {
		if (!composite.isDisposed()){
			Point size = getTabbedPropertySheetPage().getTabbedPropertyComposite().getScrolledComposite().getSize();
			RowData rd = new RowData();
			rd.width = size.x;
			rd.height = size.y - 25;
			composite.setLayoutData(rd);
			composite.getParent().layout();
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
