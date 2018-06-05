/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.wizard.fragments.data.dialog;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.chart.ContextHelpIDs;
import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * Page used to edit the properties of a customizer. It is used when providing a customizer
 * raw class name, so only the widget to define the classname are shown
 * 
 * @author Orlandin Marco
 *
 */
public class EditClassPage extends JSSHelpWizardPage {
	
	/**
	 * The modify listener to update the class result when something in the text area changes
	 */
	private ModifyListener textModifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			String text = ((Text)e.widget).getText();
			advancedClass = text;
			//this will trigger the validate
			getContainer().updateButtons();
		}
	};
	
	/**
	 * Store the class name typed in the textArea, is used when the edited customizer
	 * is a raw class
	 */
	private String advancedClass = ""; //$NON-NLS-1$
	
	/**
	 * The number of chart customizers currently defined in the chart
	 */
	private int currentChartCustmizers = 0;

	/**
	 * Text area used to provide the classname
	 */
	private Text textArea;
	
	/**
	 * The {@link JasperReportsConfiguration} of the report
	 */
	private JasperReportsConfiguration jConfig;

	/**
	 * Create the page
	 * 
	 * @param dto the dto of the edited chart, must be not null
	 * @param jConfig the {@link JasperReportsConfiguration} of the report, should be not null
	 */
	public EditClassPage(CustomizerPropertyExpressionsDTO dto, JasperReportsConfiguration jConfig) {
		super("editClassPage"); //$NON-NLS-1$
		this.currentChartCustmizers = dto.getCustomizersNumber();
		this.jConfig = jConfig;
		setMessage(Messages.EditCustomizerPage_pageMessage);
	}

	@Override
	public void createControl(Composite parent) {
		Composite mainParent = new Composite(parent, SWT.NONE);
		mainParent.setLayout(new GridLayout(3, false));
		setTitle(Messages.EditCustomizerPage_pageTitle);
		new Label(mainParent, SWT.NONE).setText(Messages.EditCustomizerPage_customizerClassLabel);
		textArea = new Text(mainParent, SWT.BORDER);
		textArea.addModifyListener(textModifyListener);
		textArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		textArea.setText(advancedClass);
		
		Button browseButton = new Button(mainParent, SWT.PUSH);
		browseButton.setText("..."); //$NON-NLS-1$
		browseButton.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Class<?>> acceptedClasses = new ArrayList<Class<?>>();
				acceptedClasses.add(JRChartCustomizer.class);
				String classname = ClassTypeCellEditor.getJavaClassDialog(getShell(), acceptedClasses);
				if (classname != null){
					textArea.setText(classname);
				}
			}
			
		});	
		setControl(mainParent);
	}
	
	/**
	 * Return the raw class
	 * 
	 * @return return the rawclass typed in the text area
	 */
	public String getRawClass(){
		return advancedClass;
	}
	
	/**
	 * Set the raw class that will be shown in the text area
	 * 
	 * @param advancedClass the full classname
	 */
	public void setRawClass(String advancedClass){
		this.advancedClass = advancedClass;
	}
	
	/**
	 * Validate the input. Typically this validate only the classname when using a 
	 * raw class customizer. The only restriction is that the classname can't be empty. But
	 * if the name is not a valid classname then a warning message is shown
	 */
	protected boolean validate(){
		String text = textArea.getText();
		if (text.trim().isEmpty()){
			setErrorMessage(Messages.EditCustomizerPage_errorClassEmpty);
			return false;
		} else {
			try{
				if (jConfig != null) {
					jConfig.getClassLoader().loadClass(text);
				} else {
					JRClassLoader.loadClassForName(text);
				}
				if (currentChartCustmizers > 0){
					setMessage(Messages.EditClassPage_dialogMessageServer);
				} else {
					setMessage(Messages.EditClassPage_dialogMessage);
				}
			}catch (Exception ex){
				setMessage(Messages.EditCustomizerPage_warningClassNotFound, IMessageProvider.WARNING);
			}
		}
		setErrorMessage(null);
		return true;
	}
	
	@Override
	public boolean isPageComplete() {
		return validate();
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_SET_CUSTOMIZER_CLASS;
	}

}
