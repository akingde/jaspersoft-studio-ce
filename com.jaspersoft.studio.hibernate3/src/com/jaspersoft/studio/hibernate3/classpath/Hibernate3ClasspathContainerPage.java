package com.jaspersoft.studio.hibernate3.classpath;

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

public class Hibernate3ClasspathContainerPage extends WizardPage implements
		IClasspathContainerPage, IClasspathContainerPageExtension {

	private ArrayList<IPath> fUsedPaths;
	private IClasspathEntry containerEntry;
	
	public Hibernate3ClasspathContainerPage() {
		super("Hibernate 3.2.0.GA Library");
		setTitle("Hibernate 3.2.0.GA Library Classpath Container");
		setDescription("Hibernate 3.2.0.GA Core and Annotations jars");
		setPageComplete(true);
		fUsedPaths = new ArrayList<IPath>();
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setText("Hibernate 3.2.0.GA Core and Annotations jars will be added to the project classpath once you click finish.");
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
			containerEntry = JavaCore.newContainerEntry(Hibernate3ClasspathContainer.ID);
		}
		return containerEntry;
	}

	@Override
	public void setSelection(IClasspathEntry containerEntry) {
		this.containerEntry = containerEntry;
	}

}
