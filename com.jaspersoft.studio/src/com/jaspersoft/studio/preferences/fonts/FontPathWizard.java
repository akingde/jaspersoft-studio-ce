/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.engine.fonts.FontFamily;

public class FontPathWizard extends Wizard {
	private FontPathPage page0;
	protected List<FontFamily> fontFamilies = new ArrayList<FontFamily>();

	public FontPathWizard(List<FontFamily> fontFamilies) {
		super();
		setWindowTitle(Messages.FontPathWizard_0);
		setNeedsProgressMonitor(true);
		this.fontFamilies = fontFamilies;
	}

	public List<FontFamily> getFonts() {
		return fontFamilies;
	}

	@Override
	public void addPages() {
		page0 = new FontPathPage();
		addPage(page0);
	}

	public static File storage = ConfigurationManager.getStorage("fonts"); //$NON-NLS-1$

	@Override
	public boolean performFinish() {
		try {
			getContainer().run(true, true, new IRunnableWithProgress() {

				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					monitor.beginTask(Messages.FontPathWizard_2, IProgressMonitor.UNKNOWN);
					try {
						File path = new File(page0.getValue());
						if (path.exists())
							FontImporter.analyzeFolder(fontFamilies, path, monitor);
						else
							new InterruptedException(String.format(Messages.FontPathWizard_3, path.getAbsolutePath()));
					} catch (Exception e) {
						throw new InvocationTargetException(e);
					}
				}

			});
		} catch (InvocationTargetException e) {
			page0.setErrorMessage(e.getCause().getMessage());
			return false;
		} catch (InterruptedException e) {
			page0.setErrorMessage(e.getMessage());
			return false;
		}
		return true;
	}

}
