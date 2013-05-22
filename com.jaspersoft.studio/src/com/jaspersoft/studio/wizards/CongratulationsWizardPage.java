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
package com.jaspersoft.studio.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class CongratulationsWizardPage extends JSSWizardPage {

	private String textAllTheInformation;
	
	private String textPressFinishTo;
	
	private String textCongratulations;
	
	private GridData[] fieldData = new GridData[3];
	
	public CongratulationsWizardPage(String title, String message, String informationText, String finishText, String congratText) {
		super("congratulations_page"); //$NON-NLS-1$
		setTitle(title);
		setMessage(message);
		this.textAllTheInformation = informationText;
		this.textPressFinishTo = finishText;
		this.textCongratulations = congratText;
	}
	
	public void setAllInformationData(GridData newData){
		fieldData[1] = newData;
	}
	
	public void setCongratulationsData(GridData newData){
		fieldData[0] = newData;
	}
	
	public void setFinishData(GridData newData){
		fieldData[2] = newData;
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_CONFIGURATION_PAGE;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		GridLayout layout = new GridLayout(1,false);
		layout.marginTop = 46;
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		
		Label lblCongratulations = new Label(container, SWT.NONE);
		Font font = lblCongratulations.getFont();
		lblCongratulations.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(), 18, SWT.NORMAL));
		lblCongratulations.setText(textCongratulations);
		lblCongratulations.setLayoutData(fieldData[0] != null ? fieldData[0] : new GridData(SWT.FILL, SWT.LEFT, true, false));
		
		Label lblAllTheInformation = new Label(container, SWT.WRAP);
		lblAllTheInformation.setLayoutData(fieldData[1] != null ? fieldData[1] : new GridData(SWT.FILL, SWT.FILL, true, false));
		lblAllTheInformation.setText(textAllTheInformation);
		
		Label lblPressFinishTo = new Label(container, SWT.NONE);
		lblPressFinishTo.setText(textPressFinishTo);
		lblPressFinishTo.setLayoutData(fieldData[2] != null ? fieldData[2] : new GridData(SWT.FILL, SWT.LEFT, true, false));
		
	}

}
