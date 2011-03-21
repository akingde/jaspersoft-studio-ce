/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
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
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class Obj2TextPage extends WizardPage {
	private CalculationEnum calculation;

	public Obj2TextPage() {
		super("obj2text"); //$NON-NLS-1$
		setTitle(Messages.Obj2TextPage_title);
		setDescription(Messages.Obj2TextPage_description);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		composite.setLayout(layout);
		setControl(composite);

		final List lst = new List(composite, SWT.BORDER);
		lst.setItems(EnumHelper.getEnumNames(CalculationEnum.values(), NullEnum.NOTNULL));
		lst.setLayoutData(new GridData(GridData.FILL_BOTH));
		lst.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int sel = lst.getSelectionIndex();
				calculation = CalculationEnum.getByValue((byte) sel);
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
