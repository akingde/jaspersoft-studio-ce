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
package com.jaspersoft.studio.data.sql.widgets.scalar;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.fieldassist.ControlDecorationSupport;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.sql.model.query.operand.ScalarOperand;

public class NumberWidget extends AScalarWidget {
	private Text txt;

	public NumberWidget(Composite parent, ScalarOperand<Number> operand) {
		super(parent, SWT.NONE, operand);

	}

	@Override
	protected void createWidget(Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 2;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		txt = new Text(this, SWT.BORDER);
		txt.setText(getValue().toSQLString());
		txt.setToolTipText(getValue().toSQLString());
		GridData gd = new GridData(GridData.HORIZONTAL_ALIGN_CENTER);
		gd.widthHint = 100;
		txt.setLayoutData(gd);

		DataBindingContext bindingContext = new DataBindingContext();

		IValidator numberValidator = new IValidator() {

			@Override
			public IStatus validate(Object value) {
				String s = String.valueOf(value);
				boolean matches = s
						.matches("^(-?0[.]\\d+)$|^(-?[1-9]+\\d*([.]\\d+)?)$|^0$");
				if (matches) {
					return ValidationStatus.ok();
				}
				return ValidationStatus.error("Only Number permitted");
			}
		};
		UpdateValueStrategy targetToModel = new UpdateValueStrategy();
		targetToModel.setBeforeSetValidator(numberValidator);
		Binding bindValue = bindingContext.bindValue(
				SWTObservables.observeText(txt, SWT.Modify),
				PojoObservables.observeValue(getValue(), "value"),
				targetToModel, null);

		ControlDecorationSupport.create(bindValue, SWT.TOP | SWT.LEFT);
	}
}
