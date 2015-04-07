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
package com.jaspersoft.studio.kpi.dialog.pages;

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.kpi.dialog.AbstractKPIConfigurationPage;
import com.jaspersoft.studio.kpi.dialog.pages.widget.WidgetDefinition;
import com.jaspersoft.studio.kpi.messages.Messages;

/**
 * Configuration page used to define the widget type of a KPI
 * 
 * @author Orlandin Marco
 *
 */
public class WidgetPage extends AbstractKPIConfigurationPage {

	/**
	 * The name of the parameter inside the jasper design where the widget type is stored
	 */
	public static final String WIDGET_PARAMETER = "widget"; //$NON-NLS-1$
	
	/**
	 * Event called when a widget type button is selected. Call the method to update the 
	 * value of the parameter
	 */
	private SelectionAdapter buttonSelected = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent e) {
			writeValue( (String)e.widget.getData());
		};
	};
	
	/**
	 * Update the value of the widget type parameter by setting its default expression
	 * to the passed value. Since it is saved as expression the double quotes are added
	 * at the begin and at the end of the type to form a valid expression
	 * 
	 * @param value the value to set as widget type
	 */
	private void writeValue(String value){
		JRDesignParameter parameter = getParameter(WIDGET_PARAMETER);
		String exp = "\"" + value + "\""; //$NON-NLS-1$ //$NON-NLS-2$
		JRDesignExpression newExpression = new JRDesignExpression(exp);
		parameter.setDefaultValueExpression(newExpression);
	}

	/**
	 * Read the content of the widget type parameter from the JasperDesign and
	 * return it. Since it was saved as expression the double quotes are removed
	 * from the begin and the end of the type. If the parameter expression is null
	 * then it return the value "default"
	 * 
	 * @return a not null widget type
	 */
	private String getSelectedWidget(){
		JRDesignParameter parameter = getParameter(WIDGET_PARAMETER);
		JRExpression exp = parameter.getDefaultValueExpression();
		if (exp == null) return "default"; //$NON-NLS-1$
		String exp_text = exp.getText();
		if (exp_text.startsWith("\"")); exp_text = exp_text.substring(1); //$NON-NLS-1$
		if (exp_text.endsWith("\"")); exp_text = exp_text.substring(0, exp_text.length()-1); //$NON-NLS-1$
		return exp_text;
	}
	
	@Override
	protected Composite createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		container.setLayout(new GridLayout(1, false));
		
		ToolBar bar = new ToolBar(container, SWT.FLAT);
		bar.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		//Read and write the value of the parameter to set it if it to default
		//if it was null
		String selectedWidget = getSelectedWidget();
		writeValue(selectedWidget);
		
		//Create the tool button to select the type
		for(WidgetDefinition widget : WidgetDefinition.getWidgetTypes()){
			ToolItem newButton = new ToolItem(bar, SWT.RADIO );
			newButton.setImage(widget.getImage());
			newButton.setData(widget.getWidgetType());
			//set the currently selected widget when necessary
			if (widget.getWidgetType().equals(selectedWidget)){
				newButton.setSelection(true);
			}
			newButton.addSelectionListener(buttonSelected);
		}
		bar.pack ();
		return container;
	}
	
	@Override
	public String getTitle() {
		return Messages.WidgetPage_pageTitle;
	}
	
	@Override
	public String getName() {
		return Messages.WidgetPage_pageLabel;
	}
}

