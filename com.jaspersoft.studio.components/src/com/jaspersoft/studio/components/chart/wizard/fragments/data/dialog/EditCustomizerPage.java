/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypeCellEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

import net.sf.jasperreports.engine.JRChartCustomizer;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * Page used to edit the properties of a customizer. There are two type of view, one
 * when using a full customizer definition so the dynamic controls will be created.
 * One when using a definition of a customizer composed of only a class. In this 
 * case only a text area for the class name is created. Some methods are abstract
 * because the behavior of this page is slightly different when editing a customizer
 * just created or when editing a previously created one
 * 
 * @author Orlandin Marco
 *
 */
public abstract class EditCustomizerPage extends JSSHelpWizardPage {

	/**
	 * Composite where the controls are created
	 */
	private Composite mainParent;
	
	/**
	 * Expression context for the dynamic controls
	 */
	private ExpressionContext ec;
	
	/**
	 * DTO of where the changes are stored and from where the value are read
	 */
	private CustomizerPropertyExpressionsDTO dto;
	
	/**
	 * {@link JasperReportsConfiguration} of the current report
	 */
	private JasperReportsConfiguration jConfig;
	
	/**
	 * Store the class name typed in the textArea, is used when the edited customizer
	 * is a raw class
	 */
	private String advancedClass = "";
	
	/**
	 * The text are where the class name can be typed 
	 */
	private Text textArea;
	
	/**
	 * The modify listener to update the class result when something in the text area changes
	 */
	private ModifyListener textModifyListener = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			String text = ((Text)e.widget).getText();
			advancedClass = text;
			validate();
		}
	};
	
	/**
	 * Create the page 
	 * 
	 * @param jConfig {@link JasperReportsConfiguration} of the current report
	 * @param ec {@link ExpressionContext} used for the dynamic widgets
	 * @param dto {@link CustomizerPropertyExpressionsDTO} from where the value are read and stored
	 */
	public EditCustomizerPage(JasperReportsConfiguration jConfig, ExpressionContext ec, CustomizerPropertyExpressionsDTO dto) {
		super("customizerEditPage");
		this.dto = dto;
		this.jConfig = jConfig;
		this.ec = ec;
		setMessage("Provide the configuration information of the customizer");
	}
	
	@Override
	public void createControl(Composite parent) {
		mainParent = new Composite(parent, SWT.NONE);
		setControl(mainParent);
	}
	
	/**
	 * When this step is visible reinitialize the controls
	 */
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if (visible){
			for(Control control : mainParent.getChildren()){
				control.dispose();
			}
			if (isUsingCustomDefinition()){
				mainParent.setLayout(new GridLayout(3, false));
				setTitle("User Defined Customizer");
				new Label(mainParent, SWT.NONE).setText("Customizer Class");
				textArea = new Text(mainParent, SWT.BORDER);
				textArea.addModifyListener(textModifyListener);
				textArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
				textArea.setText(advancedClass);
				
				Button browseButton = new Button(mainParent, SWT.PUSH);
				browseButton.setText("...");
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
			} else {		
				mainParent.setLayout(new GridLayout(2, false));
				setTitle(getCurrentDefinition().getDescriptor().getLabel());
				ChartCustomizerDefinition selectedDefinition = getCurrentDefinition();
				DtoPropertyEditor pEditor = new DtoPropertyEditor(selectedDefinition.getKey(), dto);
				WidgetsDescriptor cd = selectedDefinition.getDescriptor();
				for(WidgetPropertyDescriptor p : cd.getPlainWidgets()) {
					WidgetFactory.createLabelForProperty(mainParent, p);
					ItemPropertyDescription<?> descriptor = WidgetFactory.createItemPropertyDescriptor(cd, p, jConfig);
					WItemProperty widgetEditor = new WItemProperty(mainParent, SWT.NONE, 1, descriptor, pEditor);
					widgetEditor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
					widgetEditor.setExpressionContext(ec);
					widgetEditor.updateWidget();
				}
			}
			int compositeHeight = Math.max(mainParent.computeSize(500, SWT.DEFAULT).y + 200, 400);
			getShell().setSize(new Point(500, compositeHeight));
			mainParent.layout(true, true);
			validate();
		}
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
	protected void validate(){
		if (isUsingCustomDefinition()){
			String text = textArea.getText();
			if (text.trim().isEmpty()){
				setErrorMessage("The classname can not be empty");
				getContainer().updateButtons();
				return;
			} else {
				try{
					JRClassLoader.loadClassForName(text);
				}catch (Exception ex){
					setErrorMessage(null);
					setMessage("The selected class cannot be found", IMessageProvider.WARNING);
					getContainer().updateButtons();
					return;
				}
			}
		}
		setMessage("Provide the configuration information of the customizer");
		setErrorMessage(null);
		getContainer().updateButtons();
	}
	
	@Override
	public boolean isPageComplete() {
		return getErrorMessage() == null;
	}
	
	@Override
	protected String getContextName() {
		return null;
	}

	/**
	 * Return if the edited definition is a raw class definition or is based
	 * on a Customizer Definition file
	 * 
	 * @return true if the definition is based on raw class, false otherwise
	 */
	protected abstract boolean isUsingCustomDefinition();
	
	/**
	 * Return the edited definition. It is used to read the controls that will
	 * be created when it is based on a Customizer Definition file
	 * 
	 * @return the definition edited in this step
	 */
	protected abstract ChartCustomizerDefinition getCurrentDefinition();
}
