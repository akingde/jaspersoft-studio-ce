/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.widgets;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import com.jaspersoft.studio.data.designer.AQueryDesigner;
import com.jaspersoft.studio.data.designer.SelectParameterDialog;
import com.jaspersoft.studio.data.sql.model.query.operand.ParameterPOperand;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class ParameterWidget extends AOperandWidget<ParameterPOperand> {

	private Text txt;

	public ParameterWidget(Composite parent, ParameterPOperand operand, AQueryDesigner designer) {
		super(parent, SWT.BORDER, operand, designer);
	}

	@Override
	protected void createWidget(final Composite parent) {
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		layout.horizontalSpacing = 0;
		layout.verticalSpacing = 0;
		setLayout(layout);

		txt = new Text(this, SWT.READ_ONLY);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		txt.setLayoutData(gd);

		ToolBar buttons = new ToolBar(this, SWT.FLAT);

		ToolItem button = new ToolItem(buttons, SWT.PUSH);
		button.setText("..."); //$NON-NLS-1$
		button.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				SelectParameterDialog d = new SelectSQLParameterDialog(parent.getShell(), designer);
				if (d.open() == Dialog.OK)
					fillValue();
			}

		});
		button.setToolTipText(com.jaspersoft.studio.data.sql.messages.Messages.FieldWidget_0);
		buttons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));

		fillValue();
	}

	class SelectSQLParameterDialog extends SelectParameterDialog {
		public SelectSQLParameterDialog(Shell parentShell, AQueryDesigner designer) {
			super(parentShell, designer);
			if (getValue().getJrParameter() != null)
				pname = getValue().getJrParameter().getName();
		}

		@Override
		protected void commitValues() {
			super.commitValues();
			getValue().setJrParameter(prm);
			fillValue();
		}

		@Override
		protected void cancelPressed() {
			if (getValue().getJrParameter() == null)
				commitValues();
			super.cancelPressed();
		}

		@Override
		protected boolean isParameterCompatible(JRParameter p) {
			return true;
		}

		@Override
		protected String getDefaultParameterType() {
			if (prm != null)
				return prm.getValueClassName();
			return Object.class.getName();
		}
	};

	private void fillValue() {
		JRDesignParameter p = getValue().getJrParameter();
		if (p != null) {
			txt.setText(Misc.nvl(p.getName()));
			txt.setToolTipText(Misc.nvl(p.getName()));
		} else {
			SelectParameterDialog d = new SelectSQLParameterDialog(UIUtils.getShell(), designer);
			if (d.open() == Dialog.OK)
				fillValue();
		}
	}

}
