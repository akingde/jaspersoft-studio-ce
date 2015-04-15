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
package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboInputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboParametersPage;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.InputParameterDialog;

/**
 * Page to edit the parameters of a subreport. In addition to the 
 * default page it offers a button to get all the parameters from the current report.
 * The parameter name can be chosen from a combo between the names of the parameters
 * inside the subreport or inserted manually 
 * 
 * @author Orlandin Marco
 *
 */
public class SubreportParameterPage extends ComboParametersPage {

	private JasperDesign jd;
	
	private SubreportParameterEditor parentEditor;
	
	public SubreportParameterPage(SubreportParameterEditor parentEditor) {
		super(null);
		this.parentEditor = parentEditor;
	}

	@Override
	protected void generateButtons(Composite bGroup) {
		super.generateButtons(bGroup);
		Button bMaster = new Button(bGroup, SWT.PUSH);
		bMaster.setText(Messages.SubreportParameterPage_copyFromMaster);
		bMaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (jd == null) return;
				for (JRParameter prm : jd.getParametersList()) {
					if (prm.isSystemDefined())
						continue;
					String name = prm.getName();
					boolean exists = false;
					for (GenericJSSParameter sp : values) {
						if (sp.getName().equals(name)) {
							exists = true;
							break;
						}
					}
					if (exists)
						return;

					JRDesignSubreportParameter param = new JRDesignSubreportParameter();
					param.setName(name);
					param.setExpression(ExprUtil.createExpression("$P{" + name + "}")); //$NON-NLS-1$ //$NON-NLS-2$
					values.add(new GenericJSSParameter(param));
				}
				tableViewer.refresh(true);
			}
		});
	}
	
	/**
	 * Return the input of the combo, a list of the parameter names of the original subreport file
	 * 
	 * @return the list of string displayed in the combo
	 */
	@Override
	protected List<String> createNameComboInput(){
		List<String> result = new ArrayList<String>();
		HashSet<String> usedParams = new HashSet<String>();
		for(GenericJSSParameter param : values){
				usedParams.add(param.getName());
		}
		if (parentEditor.getJasperDesign() != null){
			for (JRParameter param : parentEditor.getJasperDesign().getParameters()){
				if (!usedParams.contains(param.getName())){
						if (param.getName() != null) result.add(param.getName());
				}
			}
			Collections.sort(result);
		}
		return result;
	}
	
	public void setJasperDesign(JasperDesign jd){
		this.jd = jd;
	}
	
	@Override
	public String getPageDescription() {
		return Messages.SubreportPropertyPage_description;
	}
	
	@Override
	public String getPageTitle() {
		return Messages.common_subreport_parameters;
	}
	
	@Override
	protected InputParameterDialog getEditDialog(GenericJSSParameter editedParameter) {
		return new ComboInputParameterDialog(getShell(), createNameComboInput(), editedParameter, SWT.DROP_DOWN);
	}
}
