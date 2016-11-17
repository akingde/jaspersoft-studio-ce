/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.handlers;

import java.util.List;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.ContextHelpIDs;
import com.jaspersoft.studio.wizards.JSSWizardPage;

/**
 * 
 * Final page of the template export procedure, it check if the template is valid 
 * and display eventually one or more error messages, otherwise inform the user that the 
 * template can be exported. If there aren't error some congratulations messages 
 * are shown
 * 
 * @author Orlandin Marco
 *
 */
public class FinalPage extends JSSWizardPage {

	/**
	 * Stack layout used to show or the error message section or the congratulation section
	 */
	private StackLayout layout;
	
	/**
	 * Composite where the controls that display the congratulations messages are placed
	 */
	private Composite congratsComposite;
	
	/**
	 * Composite where the controls that display the error messages are placed
	 */
	private Composite errorsComposite;
	
	/**
	 * Parent area of the dialog
	 */
	private Composite parentArea;
	
	/**
	 * Styled text where the error messages returned from the validation are returned
	 */
	private StyledText errorsArea;
	
	public FinalPage(){
		super("export_template_final_page"); //$NON-NLS-1$
		setTitle(Messages.TemplateExporterWizard_congratTitle);
		setMessage(Messages.TemplateExporterWizard_congratDesc);
	}
	
	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		layout = new StackLayout();
		container.setLayout(layout);
		congratsComposite = createCongratsSection(container);
		errorsComposite = createErrorsSection(container);
		parentArea = container;
		setControl(container);
	}
	
	/**
	 * The wizard can be finished only when this page is visible
	 */
	@Override
	public boolean isPageComplete() {
		return getControl().isVisible();
	}
	
	/**
	 * Create the composite with the controls to show the errors
	 * 
	 * @param parent 
	 * @return the parent composite where the controls are contained
	 */
	private Composite createErrorsSection(Composite parent){
		final Composite container = new Composite(parent, SWT.NULL);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		GridLayout layout = new GridLayout(1,false);
		layout.marginTop = 20;
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		
		Label lblCongratulations = new Label(container, SWT.NONE);
		Font font = lblCongratulations.getFont();
		lblCongratulations.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(), 18, SWT.NORMAL));
		lblCongratulations.setForeground(ColorConstants.red);
		lblCongratulations.setText(Messages.FinalPage_errorTitle);
		lblCongratulations.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false));
    
	    Composite areaComp = new Composite(container, SWT.NONE);
	    areaComp.setLayoutData(new GridData(GridData.FILL_BOTH));
	    areaComp.setLayout(new GridLayout(1,false));
		errorsArea = new StyledText(areaComp, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		errorsArea.setEditable(false);
		errorsArea.setLayoutData(new GridData(GridData.FILL_BOTH));
		errorsArea.setBackground(areaComp.getBackground());
		
		Label lblPressFinishTo = new Label(container, SWT.WRAP);
		lblPressFinishTo.setText(Messages.FinalPage_errorConclusiveMessage);
		GridData finishData = new GridData(SWT.FILL, SWT.LEFT, true, false);
		finishData.widthHint = 170;
		finishData.heightHint = 130;
		lblPressFinishTo.setLayoutData(finishData);
	
		return container;
	}
	
	/**
	 * Create the composite with the controls to show the congratulation messages
	 * 
	 * @param parent 
	 * @return the parent composite where the controls are contained
	 */
	private Composite createCongratsSection(Composite parent){
		Composite container = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout(1,false);
		layout.marginTop = 46;
		layout.verticalSpacing = 10;
		container.setLayout(layout);
		
		Label lblCongratulations = new Label(container, SWT.NONE);
		Font font = lblCongratulations.getFont();
		lblCongratulations.setFont(SWTResourceManager.getFont(font.getFontData()[0].getName(), 18, SWT.NORMAL));
		lblCongratulations.setText(Messages.TemplateExporterWizard_congratCongratulations);
		lblCongratulations.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, true, false));
		
		Label lblAllTheInformation = new Label(container, SWT.WRAP);
		GridData informationData = new GridData(SWT.FILL, SWT.FILL, true, false);
		informationData.widthHint = 170;
		lblAllTheInformation.setLayoutData(informationData);
		lblAllTheInformation.setText(Messages.TemplateExporterWizard_congratMessage);
		
		Label lblPressFinishTo = new Label(container, SWT.WRAP);
		lblPressFinishTo.setText(Messages.TemplateExporterWizard_congrattoFinish);
		GridData finishData = new GridData(SWT.FILL, SWT.LEFT, true, false);
		finishData.widthHint = 170;
		finishData.heightHint = 130;
		lblPressFinishTo.setLayoutData(finishData);
		return container;
	}
	
	/**
	 * Return the context name for the help of this page
	 */
	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_CONFIGURATION_PAGE;
	}
	
	/**
	 * When the dialog became visible then the the report is validate with the specified
	 * template engine, and if there aren't error messages then the congratulation section
	 * is displayed, otherwise is displayed the error section with the error messages printed
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (getWizard() instanceof TemplateExporterWizard && visible){
			TemplateExporterWizard parentWizard = (TemplateExporterWizard)getWizard();
			List<String> validationErrors = parentWizard.getValidationErrors();
			if (validationErrors.size() == 0) layout.topControl = congratsComposite;
			else {
				errorsArea.setText(""); //$NON-NLS-1$
				for(String error : validationErrors){
					errorsArea.append(error+System.getProperty("line.separator")); //$NON-NLS-1$
				}
				layout.topControl = errorsComposite;
			}
			parentArea.layout(true, true);
		}
	}
	
}
