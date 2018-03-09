/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.widget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.swt.widgets.WTextExpression;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class WExpression extends AWControl {
	private WTextExpression expr;
	private ExpressionContext expContext;
	protected boolean refresh = false;

	public WExpression(AWidget aw) {
		super(aw);
		this.expContext = new ExpressionContext(((MDataset) aw.getTColumn().getValue()).getJasperConfiguration());
	}

	protected void createControl(Composite parent) {// here depending on the type we could have different widget
		expr = new WTextExpression(parent, SWT.NONE, 1);
		expr.addModifyListener(event -> {
			if (refresh)
				return;
			aw.setValue(expr.getExpression());
			expr.setToolTipText(aw.getToolTipText());
		});
		expr.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		expr.setExpressionContext(expContext);
	}

	protected void fillValue() {
		try {
			refresh = true;
			expr.setExpression((JRDesignExpression) aw.getValue());
		} finally {
			refresh = false;
		}
		expr.setExpressionContext(expContext);
		expr.setToolTipText(aw.getToolTipText());
	}

	@Override
	protected String getText() {
		JRExpression exp = (JRExpression) aw.getValue();
		if (exp != null)
			return Misc.nvl(exp.getText());
		return "";
	}

	@Override
	public void addDisposeListener(DisposeListener dlistener) {
		expr.addDisposeListener(dlistener);
	}

	@Override
	public void setEnabled(boolean en) {
		expr.setEnabled(en);
	}
}
