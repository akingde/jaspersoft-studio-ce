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
package com.jaspersoft.studio.properties.internal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.properties.view.TabbedPropertySheetWidgetFactory;

/**
 * Composite responsible for drawing the tabbed property sheet page.
 * 
 * @author Anthony Hunter
 */
public class TabbedPropertyComposite extends Composite {

	private TabbedPropertySheetWidgetFactory factory;

	private Composite mainComposite;

	private ScrolledComposite scrolledComposite;

	private Composite tabComposite;

	private TabbedPropertyTitle title;

	private TabbedPropertyList listComposite;

	private boolean displayTitle;

	/**
	 * Constructor for a TabbedPropertyComposite
	 * 
	 * @param parent
	 *            the parent widget.
	 * @param factory
	 *            the widget factory.
	 * @param displayTitle
	 *            if <code>true</code>, then the title bar will be displayed.
	 */
	public TabbedPropertyComposite(Composite parent,
			TabbedPropertySheetWidgetFactory factory, boolean displayTitle) {
		super(parent, SWT.NO_FOCUS);
		this.factory = factory;
		this.displayTitle = displayTitle;

		createMainComposite();
	}

	/**
	 * Create the main composite.
	 */
	protected void createMainComposite() {
		mainComposite = factory.createComposite(this, SWT.NO_FOCUS);
		mainComposite.setBackgroundMode(SWT.INHERIT_FORCE);

		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		mainComposite.setLayout(layout);

		FormData formData = new FormData();
		formData.left = new FormAttachment(0, 0);
		formData.right = new FormAttachment(100, 0);
		formData.top = new FormAttachment(0, 0);
		formData.bottom = new FormAttachment(100, 0);
		mainComposite.setLayoutData(formData);

		createMainContents();
	}

	/**
	 * Create the contents in the main composite.
	 */
	protected void createMainContents() {
		if (displayTitle) {
			title = new TabbedPropertyTitle(mainComposite, factory);
			title.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		}

		listComposite = new TabbedPropertyList(mainComposite);
		listComposite.getControl().setLayoutData(
				new GridData(GridData.FILL_HORIZONTAL));

		new Label(mainComposite, SWT.SEPARATOR | SWT.HORIZONTAL)
				.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		scrolledComposite = factory.createScrolledComposite(mainComposite,
				SWT.H_SCROLL | SWT.V_SCROLL | SWT.NO_FOCUS);
		scrolledComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		
		scrolledComposite.setAlwaysShowScrollBars(false);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setExpandHorizontal(true);

		tabComposite = factory.createComposite(scrolledComposite, SWT.NO_FOCUS);
		GridLayout layout2 = new GridLayout();
		layout2.marginWidth = 0;
		layout2.marginHeight = 0;
		layout2.makeColumnsEqualWidth = true;
		tabComposite.setLayout(layout2);
		
//		tabComposite.addControlListener(new ControlAdapter() {
//			@Override
//			public void controlResized(ControlEvent e) {
//				setupScrolledComposite();
//			}
//		});
		
		scrolledComposite.setContent(tabComposite);
//		setupScrolledComposite();
	}

	public void setupScrolledComposite() {
		//Point minSize = getScrolledBoxMinSize();
		//scrolledComposite.setMinSize(minSize.x, minSize.y);
		mainComposite.layout();
	}

	/*
	private Point getScrolledBoxMinSize() {
		for (Control c : tabComposite.getChildren()){
			if(c.isVisible()){
				Rectangle scrolledBounds = c.getBounds();
				Point defaultSize = c.computeSize(SWT.DEFAULT,SWT.DEFAULT);
				return c.computeSize(defaultSize.x,Math.max(scrolledBounds.height, defaultSize.y));
			}
		}
		return new Point(0,0);
	}
	*/

	/**
	 * Get the tabbed property list, which is the list of tabs on the left hand
	 * side of this composite.
	 * 
	 * @return the tabbed property list.
	 */
	public TabbedPropertyList getList() {
		return listComposite;
	}

	/**
	 * Get the tabbed property title bar.
	 * 
	 * @return the tabbed property title bar or <code>null</code> if not used.
	 */
	public TabbedPropertyTitle getTitle() {
		return title;
	}

	/**
	 * Get the tab composite where sections display their property contents.
	 * 
	 * @return the tab composite.
	 */
	public Composite getTabComposite() {
		return tabComposite;
	}

	/**
	 * Get the scrolled composite which surrounds the title bar and tab
	 * composite.
	 * 
	 * @return the scrolled composite.
	 */
	public ScrolledComposite getScrolledComposite() {
		return scrolledComposite;
	}

	/**
	 * Get the widget factory.
	 * 
	 * @return the widget factory.
	 */
	protected TabbedPropertySheetWidgetFactory getFactory() {
		return factory;
	}

	/**
	 * @see org.eclipse.swt.widgets.Widget#dispose()
	 */
	public void dispose() {
		listComposite.getControl().dispose();
		if (displayTitle) {
			title.dispose();
		}
		super.dispose();
	}
}
