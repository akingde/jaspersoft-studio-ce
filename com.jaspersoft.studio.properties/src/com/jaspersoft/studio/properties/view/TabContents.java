/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.properties.layout.DynamicColumnLayout;

/**
 * A property tab is composed by one or more property sections and is used to
 * categorize sections.
 * 
 * @author Anthony Hunter
 * @since 3.4
 */
public final class TabContents {

	private ISection[] sections;
	
	private boolean controlsCreated;

	/**
	 * Flag used to store if the current tab has at least a section
	 * with dynamic contents
	 */
	private boolean dynamicContent = false;
	
	/**
     * 
     */
	public TabContents() {
		controlsCreated = false;
	}

	/**
	 * Retrieve a numbered index for the section.
	 * 
	 * @param section
	 *          the section.
	 * @return the section index.
	 */
	public int getSectionIndex(ISection section) {
		for (int i = 0; i < sections.length; i++) {
			if (section == sections[i]) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Retrieve the section at a numbered index.
	 * 
	 * @param i
	 *          a numbered index.
	 * @return the section.
	 */
	public ISection getSectionAtIndex(int i) {
		if (i >= 0 && i < sections.length) {
			return sections[i];
		}
		return null;
	}

	/**
	 * Retrieve the sections on the tab.
	 * 
	 * @return the sections on the tab.
	 */
	public ISection[] getSections() {
		return sections;
	}

	/**
	 * Creates page's sections controls.
	 * 
	 * @param parent
	 * @param page
	 */
	public void createControls(final Composite parent, final TabbedPropertySheetPage page) {
		//If there is only a section then a standard composite is used as container
		
		if (sections.length > 1){
			Composite pageComposite = page.getWidgetFactory().createComposite(parent, SWT.NO_FOCUS);
			DynamicColumnLayout layout = new DynamicColumnLayout(page);
			layout.leftMargin = 0;
			layout.topMargin = 0;
			layout.verticalSpacing = 0;
			pageComposite.setLayout(layout);
			pageComposite.setLayoutData(new GridData(GridData.FILL_BOTH));
			for (int i = 0; i < sections.length; i++) {
				final ISection section = sections[i];
				//Create a composite that allow an easy access to the page
				final Composite sectionComposite = page.getWidgetFactory().createSectionComposite(pageComposite, page, SWT.NO_FOCUS);
				sectionComposite.setLayout(new GridLayout());
				ISafeRunnable runnable = new SafeRunnable() {
	
					public void run() throws Exception {
						if (section.getElement() != null){
							section.createControls(sectionComposite, page);
						}
					}
				};
				SafeRunnable.run(runnable);
			}
		} else if (sections.length > 0){
			final ISection section = sections[0];
			final Composite sectionComposite = parent;
			ISafeRunnable runnable = new SafeRunnable() {

				public void run() throws Exception {
					if (section.getElement() != null){
						section.createControls(sectionComposite, page);
					}
				}
			};
			SafeRunnable.run(runnable);
		}
		controlsCreated = true;
	}

	/**
	 * Dispose of page's sections controls.
	 */
	public void dispose() {
		for (int i = 0; i < sections.length; i++) {
			final ISection section = sections[i];
			ISafeRunnable runnable = new SafeRunnable() {

				public void run() throws Exception {
					section.dispose();
				}
			};
			SafeRunnable.run(runnable);
		}
	}

	/**
	 * Sends the lifecycle event to the page's sections.
	 */
	public void aboutToBeShown() {
		for (int i = 0; i < sections.length; i++) {
			final ISection section = sections[i];
			ISafeRunnable runnable = new SafeRunnable() {

				public void run() throws Exception {
					section.aboutToBeShown();
				}
			};
			SafeRunnable.run(runnable);
		}
	}

	/**
	 * Sends the lifecycle event to the page's sections.
	 */
	public void aboutToBeHidden() {
		for (int i = 0; i < sections.length; i++) {
			final ISection section = sections[i];
			ISafeRunnable runnable = new SafeRunnable() {

				public void run() throws Exception {
					section.aboutToBeHidden();
				}
			};
			SafeRunnable.run(runnable);
		}
	}

	/**
	 * Sets page's sections input objects.
	 * 
	 * @param part
	 * @param selection
	 */
	public void setInput(final IWorkbenchPart part, final ISelection selection) {
		for (int i = 0; i < sections.length; i++) {
			final ISection section = sections[i];
			ISafeRunnable runnable = new SafeRunnable() {

				public void run() throws Exception {
					section.setInput(part, selection);
				}
			};
			SafeRunnable.run(runnable);
		}
	}

	/**
	 * Set the sections for the tab.
	 * 
	 * @param sections
	 *          the sections for the tab.
	 */
	public void setSections(ISection[] sections) {
		this.sections = sections;
		if (sections != null){
			for(ISection section : sections){
				if (section.hasDynamicContent()){
					dynamicContent = true;
					break;
				}
			}
		}
	}

	/**
	 * Determine if the controls have been created.
	 * 
	 * @return <code>true</code> if controls have been created.
	 */
	public boolean controlsHaveBeenCreated() {
		return controlsCreated;
	}

	/**
	 * If controls have been created, refresh all sections on the page.
	 */
	public void refresh() {
		if (controlsCreated) {
			for (int i = 0; i < sections.length; i++) {
				final ISection section = sections[i];
				ISafeRunnable runnable = new SafeRunnable() {

					public void run() throws Exception {
						section.refresh();
					}
				};
				SafeRunnable.run(runnable);
			}
		}
	}
	
	/**
	 * Return if a section of the current tab has some dynamic content
	 * 
	 * @return true if in the tab there is content that could change dynamically (even if
	 * the tab is the same), false otherwise
	 */
	public boolean hasDynamicContent(){
		return dynamicContent;
	}
}
