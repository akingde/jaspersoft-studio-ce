/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.backward.wizard;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.builder.JRDefinition;

public class JRRuntimeURLWizard extends Wizard {
	private JRDefinition d;
	private JRRuntimeURLPage page0;

	public JRRuntimeURLWizard(JRDefinition d) {
		super();
		setWindowTitle(Messages.JRRuntimeURLWizard_0);
		this.d = d;
		setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		page0 = new JRRuntimeURLPage(d);
		addPage(page0);

	}

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.JRRuntimeURLWizard_1, IProgressMonitor.UNKNOWN);
					try {
						if (!JRBackwardManager.download(d, monitor))
							throw new InterruptedException();
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
				}
			});
		} catch (InvocationTargetException e) {
			page0.setErrorMessage(e.getCause().getMessage());
			return false;
		} catch (InterruptedException e) {
			return false;
		}
		return true;
	}

}
