/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class CongratulationsWizardPage extends JSSWizardPage {

	protected CongratulationsWizardPage() {
		super("congratulations_page"); //$NON-NLS-1$
		setTitle("Finish");
		setMessage("We are ready to create your report");
	}

	@Override
	public void createControl(Composite parent) {
			Composite container = new Composite(parent, SWT.NULL);
			setControl(container);
			container.setLayout(new FormLayout());
			
			Label lblAllTheInformation = new Label(container, SWT.NONE);
			FormData fd_lblAllTheInformation = new FormData();
			fd_lblAllTheInformation.left = new FormAttachment(0, 10);
			lblAllTheInformation.setLayoutData(fd_lblAllTheInformation);
			lblAllTheInformation.setText("All the information to create your new report have been succesfully acquired.");
			
			Label lblPressFinishTo = new Label(container, SWT.NONE);
			lblPressFinishTo.setText("Press Finish to generate the report.");
			FormData fd_lblPressFinishTo = new FormData();
			fd_lblPressFinishTo.top = new FormAttachment(lblAllTheInformation, 6);
			fd_lblPressFinishTo.left = new FormAttachment(lblAllTheInformation, 0, SWT.LEFT);
			lblPressFinishTo.setLayoutData(fd_lblPressFinishTo);
			
			Label lblCongratulations = new Label(container, SWT.NONE);
			fd_lblAllTheInformation.top = new FormAttachment(lblCongratulations, 6);
			
			Font font = lblCongratulations.getFont();
			lblCongratulations.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(), 18, SWT.NORMAL));
			lblCongratulations.setText("Congratulations!");
			FormData fd_lblCongratulations = new FormData();
			fd_lblCongratulations.left = new FormAttachment(0, 10);
			fd_lblCongratulations.top = new FormAttachment(0, 46);
			lblCongratulations.setLayoutData(fd_lblCongratulations);
	}

}
