/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.fields.table.column;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionCellEditor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.design.JRDesignExpression;

public class ExpressionColumnSupport extends PropertyColumnSupport {
	private ExpressionContext expContext;

	public ExpressionColumnSupport(ColumnViewer viewer, TColumn c) {
		super(viewer, c);
		this.expContext = new ExpressionContext(((MDataset) c.getValue()).getJasperConfiguration());
	}

	protected CellEditor createCellEditor() {
		return new JRExpressionCellEditor((Composite) viewer.getControl(), expContext);
	}

	@Override
	public String getText(Object element) {
		JRDesignExpression expr = (JRDesignExpression) getValue(element);
		if (expr != null)
			return Misc.nvl(expr.getText());
		return "";
	}

}