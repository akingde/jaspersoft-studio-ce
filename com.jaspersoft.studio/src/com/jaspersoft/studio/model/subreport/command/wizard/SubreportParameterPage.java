/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.subreport.command.wizard;

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
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ComboInputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.GenericJSSParameter;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.InputParameterDialog;
import com.jaspersoft.studio.property.descriptor.parameter.dialog.ParameterPage;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JRReport;
import net.sf.jasperreports.engine.design.JRDesignSubreportParameter;

public class SubreportParameterPage extends ParameterPage {
	private JRReport jd;

	@Override
	protected void generateButtons(Composite bGroup) {
		super.generateButtons(bGroup);
		Button bMaster = new Button(bGroup, SWT.PUSH);
		bMaster.setText(Messages.SubreportParameterPage_copyFromMaster);
		bMaster.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (jd == null)
					return;
				for (JRParameter prm : jd.getMainDataset().getParameters()) {
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
	 * Return the input of the combo, a list of the parameter names of the
	 * original subreport file
	 * 
	 * @return the list of string displayed in the combo
	 */
	protected List<String> createNameComboInput() {
		List<String> result = new ArrayList<>();
		HashSet<String> usedParams = new HashSet<>();
		for (GenericJSSParameter param : values) {
			usedParams.add(param.getName());
		}
		if (jd != null)
			for (JRParameter param : jd.getParameters()) {
				if (!usedParams.contains(param.getName())) {
					if (param.getName() != null)
						result.add(param.getName());
				}
			}
		Collections.sort(result);
		return result;
	}

	public void setJasperDesign(JRReport jd) {
		this.jd = jd;
	}

	@Override
	protected InputParameterDialog getEditDialog(GenericJSSParameter editedParameter) {
		return new ComboInputParameterDialog(getShell(), createNameComboInput(), editedParameter, SWT.DROP_DOWN);
	}
}
