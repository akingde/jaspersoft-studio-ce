/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.editor.preview.view.control.VParameters;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;

import net.sf.jasperreports.eclipse.util.Misc;

public class PatternInput extends ADataInput {
	private Text txt;

	public boolean isForType(Class<?> valueClass) {
		return NumberFormat.class.isAssignableFrom(valueClass) || DateFormat.class.isAssignableFrom(valueClass);
	}

	@Override
	public void createInput(Composite parent, final IParameter param, final Map<String, Object> params) {
		super.createInput(parent, param, params);
		if (isForType(param.getValueClass())) {
			final Composite cmp = new Composite(parent, SWT.NONE);
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalIndent = 8;
			cmp.setLayoutData(gd);
			GridLayout layout = new GridLayout(2, false);
			layout.marginHeight = 0;
			layout.marginWidth = 0;
			cmp.setLayout(layout);

			txt = new Text(cmp, SWT.BORDER);
			txt.setToolTipText(VParameters.createToolTip(param));
			txt.addFocusListener(focusListener);
			txt.addTraverseListener(keyListener);
			txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			setMandatory(param, txt);

			ModifyListener listener = new ModifyListener() {

				public void modifyText(ModifyEvent e) {
					if (!isRefresh) {
						String tpattern = txt.getText();
						if (param.getValueClass().equals(NumberFormat.class))
							updateModel(tpattern == null ? null : new DecimalFormat(tpattern));
						else if (param.getValueClass().equals(DateFormat.class))
							updateModel(tpattern == null ? null : new SimpleDateFormat(txt.getText()));
					}
				}
			};
			txt.addModifyListener(listener);

			Button btn = new Button(cmp, SWT.PUSH);
			btn.setText("...");
			btn.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					PatternEditor wizard = new PatternEditor();
					wizard.setValue(txt.getText());
					if (param.getValueClass().equals(NumberFormat.class))
						wizard.setDatePatterns(false);
					else if (param.getValueClass().equals(DateFormat.class))
						wizard.setNumberPatterns(false);
					WizardDialog dialog = new WizardDialog(cmp.getShell(), wizard);
					dialog.create();
					if (dialog.open() == Dialog.OK) {
						String tpattern = wizard.getValue();
						txt.setText(Misc.nvl(tpattern));
						if (param.getValueClass().equals(NumberFormat.class))
							updateModel(tpattern == null ? null : new DecimalFormat(tpattern));
						else if (param.getValueClass().equals(DateFormat.class))
							updateModel(tpattern == null ? null : new SimpleDateFormat(txt.getText()));
					}
				}
			});

			updateInput();
			setNullable(param, txt);
		}
	}

	private boolean isRefresh = false;

	public void updateInput() {
		Object value = params.get(param.getName());
		if (value != null && value instanceof String)
			txt.setText((String) value);
		else {
			isRefresh = true;
			txt.setText(value == null ? "" : getPattern(value));
			isRefresh = false;
		}
		setDecoratorNullable(param);
	}

	private String getPattern(Object value) {
		if (value instanceof SimpleDateFormat)
			return ((SimpleDateFormat) value).toPattern();
		if (value instanceof DecimalFormat)
			return ((DecimalFormat) value).toPattern();
		return value.toString();
	}
}
