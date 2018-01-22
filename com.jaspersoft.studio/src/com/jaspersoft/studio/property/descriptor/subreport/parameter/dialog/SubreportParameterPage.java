/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.subreport.parameter.dialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.subreport.MSubreport;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboInputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboParametersPage;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.InputParameterDialog;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Page to edit the parameters of a subreport. In addition to the 
 * default page it offers a button to get all the parameters from the current report,
 * in the dataset scope of the subreport.
 * The parameter name can be chosen from a combo between the names of the parameters
 * inside the subreport or inserted manually 
 * 
 * @author Orlandin Marco
 *
 */
public class SubreportParameterPage extends ComboParametersPage {

	private MSubreport subreportModel;

	private JasperDesign jd;
	
	public SubreportParameterPage(MSubreport subreportModel, JasperDesign jd) {
		super(null);
		this.subreportModel = subreportModel;
		this.jd = jd;
	}

	@Override
	protected void generateButtons(Composite bGroup) {
		super.generateButtons(bGroup);
		Button bMaster = new Button(bGroup, SWT.PUSH);
		bMaster.setText(Messages.SubreportParameterPage_copyFromMaster);
		bMaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRDataset dataset = ModelUtils.getFirstDatasetInHierarchy(subreportModel);
				if (dataset == null && jd != null) {
					dataset = jd.getMainDataset();
				}
				if (dataset == null) return;
				for (JRParameter prm : dataset.getParameters()) {
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
		JRDataset dataset = ModelUtils.getFirstDatasetInHierarchy(subreportModel);
		if (dataset == null && jd != null) {
			dataset = jd.getMainDataset();
		}
		if (dataset != null){
			for (JRParameter param :dataset.getParameters()){
				if (!usedParams.contains(param.getName())){
						if (param.getName() != null) result.add(param.getName());
				}
			}
			Collections.sort(result);
		}
		return result;
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
