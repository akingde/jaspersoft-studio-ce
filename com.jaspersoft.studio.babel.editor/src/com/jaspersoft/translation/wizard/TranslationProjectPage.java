/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.translation.wizard;

import org.eclipse.babel.messages.Messages;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * Page to define the name of a new translation project
 * 
 * @author Orlandin Marco
 *
 */
public class TranslationProjectPage  extends WizardPage {
	
	public TranslationProjectPage() {
		super("JRPROJECTPAGE"); //$NON-NLS-1$
		setTitle(Messages.translation_wiz_title);
		setDescription(Messages.translation_wiz_description);
	}
	
	/**
	 * The name of the project
	 */
	private String name;

	/**
	 * Return the project name
	 */
	public String getName() {
		return name;
	}

	@Override
	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		new Label(composite, SWT.NONE).setText(Messages.translation_wiz_name);

		final Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		tname.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				name = tname.getText();
			}
		});

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL).setLayoutData(gd);
	}
}

