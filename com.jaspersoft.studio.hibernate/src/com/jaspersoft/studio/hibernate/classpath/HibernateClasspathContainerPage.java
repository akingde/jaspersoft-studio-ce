/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.hibernate.classpath;

import java.util.ArrayList;

import org.eclipse.core.runtime.IPath;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.hibernate.messages.Messages;

public class HibernateClasspathContainerPage extends WizardPage implements
		IClasspathContainerPage, IClasspathContainerPageExtension {

	private ArrayList<IPath> fUsedPaths;
	private IClasspathEntry containerEntry;
	
	public HibernateClasspathContainerPage() {
		super(Messages.HibernateClasspathContainerPage_Name);
		setTitle(Messages.HibernateClasspathContainerPage_Title);
		setDescription(Messages.HibernateClasspathContainerPage_Description);
		setPageComplete(true);
		fUsedPaths = new ArrayList<IPath>();
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText(Messages.HibernateClasspathContainerPage_Content);
		lbl.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_CENTER));

		setControl(composite);
	}

	@Override
	public void initialize(IJavaProject project,
			IClasspathEntry[] currentEntries) {
		for (int i = 0; i < currentEntries.length; i++) {
			IClasspathEntry curr = currentEntries[i];
			if (curr.getEntryKind() == IClasspathEntry.CPE_CONTAINER) {
				fUsedPaths.add(curr.getPath());
			}
		}
	}

	@Override
	public boolean finish() {
		return true;
	}

	@Override
	public IClasspathEntry getSelection() {
		if(containerEntry==null) {
			containerEntry = JavaCore.newContainerEntry(HibernateClasspathContainer.ID);
		}
		return containerEntry;
	}

	@Override
	public void setSelection(IClasspathEntry containerEntry) {
		this.containerEntry = containerEntry;
	}

}
