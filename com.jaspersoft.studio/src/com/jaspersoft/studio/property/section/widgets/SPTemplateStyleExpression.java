/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.section.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.jface.dialogs.StyleTemplateSelectionDialog;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRExpression;

/**
 * Widget used for the expression of the template style. In addition 
 * to the expression text and editor button it shown an additional 
 * button to open the resource selection dialog
 * 
 * @author Orlandin Marco
 *
 */
public class SPTemplateStyleExpression extends SPExpression {
	
	/**
	 * button used to open the dialog
	 */
	private Button btnOpenDialog;
	
	public SPTemplateStyleExpression(Composite parent, AbstractSection section, IPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}

	protected void createComponent(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout containerLayout = new GridLayout(2, false);
		containerLayout.marginWidth = 0;
		containerLayout.marginHeight = 0;
		containerLayout.verticalSpacing = 0;
		containerLayout.horizontalSpacing = 5;
		container.setLayout(containerLayout);
		container.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		super.createComponent(container);
		GridData expressioData = new GridData(GridData.FILL_HORIZONTAL);
		expr.setLayoutData(expressioData);
		
		btnOpenDialog = new Button(container, SWT.FLAT);
		btnOpenDialog.setImage(JaspersoftStudioPlugin.getInstance().getImage("icons/resources/eclipse/obj16/fldr_obj.gif"));
		btnOpenDialog.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				StyleTemplateSelectionDialog fsd = new StyleTemplateSelectionDialog(UIUtils.getShell());
				fsd.configureDialog(section.getElement().getJasperConfiguration());
				JRExpression currentExpression = expr.getExpression();
				if (currentExpression != null) {
					fsd.setInitialExpression(currentExpression.getText());
				}
				if (fsd.open() == Dialog.OK) {
					expr.setExpression(fsd.getFileExpression());
				}
			}
		});
	}

}
