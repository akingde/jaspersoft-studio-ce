/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionEditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class WProperties extends WText {
	public WProperties(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		cmp.setLayout(layout);
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		super.createControl(cmp);

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("...");
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRPropertyExpressionEditor wizard = new JRPropertyExpressionEditor();
				wizard.setShowExpression(aw.element instanceof JRDesignField);
				// clone the object to avoid side effect
				wizard.setValue(((PropertyExpressionsDTO) aw.getValue()).clone());
				WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
				if (dialog.open() == Dialog.OK)
					aw.setValue(wizard.getValue());
			}
		});
	}

	@Override
	protected void fillValue() {
		String v = getText();
		txt.setText(Misc.nvl(v, ""));
		txt.setToolTipText(aw.getToolTipText());
	}

	@Override
	public String getText() {
		if (aw.element instanceof JRDesignField) {
			JRDesignField field = (JRDesignField) aw.element;
			int size = 0;
			JRPropertiesMap pmap = field.getPropertiesMap();
			if (pmap != null && pmap.getPropertyNames() != null)
				size += pmap.getPropertyNames().length;
			JRPropertyExpression[] pexp = field.getPropertyExpressions();
			if (pexp != null)
				size += field.getPropertyExpressions().length;
			return size == 0 ? "" : (size == 1 ? "1 Property" : size + " Properties");
		} else if (aw.element instanceof JRDesignParameter) {
			JRDesignParameter field = (JRDesignParameter) aw.element;
			int size = 0;
			JRPropertiesMap pmap = field.getPropertiesMap();
			if (pmap != null && pmap.getPropertyNames() != null)
				size += pmap.getPropertyNames().length;
			return size == 0 ? "" : (size == 1 ? "1 Property" : size + " Properties");
		}
		return "";
	}
}
