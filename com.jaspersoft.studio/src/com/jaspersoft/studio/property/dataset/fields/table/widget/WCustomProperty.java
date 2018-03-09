/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.dialog.JRPropertyExpressionDialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;

public class WCustomProperty extends AWTextButton {
	public WCustomProperty(AWidget aw) {
		super(aw);
	}

	@Override
	protected void createButton(Composite cmp) {
		super.createButton(cmp);
		btn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				JRPropertyExpressionDialog d = new JRPropertyExpressionDialog(UIUtils.getShell());
				PropertyExpressionDTO dto = (PropertyExpressionDTO) aw.getValue();
				d.setValue(dto);
				if (d.open() == Dialog.OK)
					aw.setValue(dto);
			}
		});
	}

	@Override
	protected String getText() {
		PropertyExpressionDTO dto = (PropertyExpressionDTO) aw.getValue();
		if (dto.isExpression()) {
			JRExpression exp = dto.getValueAsExpression();
			return exp != null ? Misc.nvl(exp.getText()) : "";
		}
		return dto.getValue();
	}

}
