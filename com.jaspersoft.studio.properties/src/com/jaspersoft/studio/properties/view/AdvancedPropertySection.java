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
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);

		parent.setLayout(new RowLayout());

		final Composite composite = getWidgetFactory().createComposite(parent);
		FormLayout layout = new FormLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.spacing = 0;
		composite.setLayout(layout);

		page = new PropertySheetPage();

		page.createControl(composite);
		FormData data = new FormData();
		data.left = new FormAttachment(0, 0);
		data.right = new FormAttachment(100, 0);
		data.top = new FormAttachment(0, 0);
		data.bottom = new FormAttachment(100, 0);
		page.getControl().setLayoutData(data);

		page.getControl().addControlListener(new ControlAdapter() {

			public void controlResized(ControlEvent e) {
				setupComposite(atabbedPropertySheetPage, composite);
				atabbedPropertySheetPage.resizeScrolledComposite();
			}
		});

		parent.getParent().addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				setupComposite(atabbedPropertySheetPage, composite);
			}
		});
		setupComposite(atabbedPropertySheetPage, composite);
	}

	protected void setupComposite(TabbedPropertySheetPage atabbedPropertySheetPage, Composite composite) {
		Point size = composite.getParent().getSize();
		if (size.x == 0 && size.y == 0){
			size = atabbedPropertySheetPage.getTabbedPropertyComposite().getTabComposite().getSize();
		}
		RowData rd = new RowData();
		rd.width = size.x;
		composite.setLayoutData(rd);
		composite.getParent().layout(true,true);
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
