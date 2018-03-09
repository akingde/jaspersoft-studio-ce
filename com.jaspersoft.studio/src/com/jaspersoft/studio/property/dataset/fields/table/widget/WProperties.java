/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionEditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class WProperties extends AWTextButton {
	public WProperties(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createButton(Composite cmp) {
		super.createButton(cmp);
		btn.addSelectionListener(new SelectionAdapter() {
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
			return formatLabel(size);
		} else if (aw.element instanceof JRDesignParameter) {
			JRDesignParameter field = (JRDesignParameter) aw.element;
			int size = 0;
			JRPropertiesMap pmap = field.getPropertiesMap();
			if (pmap != null && pmap.getPropertyNames() != null)
				size += pmap.getPropertyNames().length;
			return formatLabel(size);
		}
		return "";
	}

	protected String formatLabel(int size) {
		String lbl = size == 1 ? "1 Property" : size + " Properties";
		return size == 0 ? "" : lbl;
	}
}
