/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.wizards.obj2text;

import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.EnumHelper;

public class Obj2TextPage extends WizardPage {
	private CalculationEnum calculation;
	private String[] names;

	public Obj2TextPage(String[] names) {
		super("obj2text"); //$NON-NLS-1$
		setTitle(Messages.Obj2TextPage_title);
		setDescription(Messages.Obj2TextPage_description);
		this.names = names;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		final List lst = new List(composite, SWT.BORDER);
		lst.setItems(names);
		lst.setLayoutData(new GridData(GridData.FILL_BOTH));
		lst.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int sel = lst.getSelectionIndex();
				// recall that we are using TRANSLATED names!
				calculation = 
						(CalculationEnum) EnumHelper.getEnumByObjectValue(CalculationEnum.values(), names[sel]);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		lst.setSelection(0);

		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), "Jaspersoft.wizard");//$NON-NLS-1$
	}

	public CalculationEnum getCalculation() {
		return calculation;
	}

}
