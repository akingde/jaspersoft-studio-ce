/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.jrQuery;

import net.sf.jasperreports.engine.design.JRDesignQuery;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.model.MQuery;
import com.jaspersoft.studio.model.dataset.MDataset;
import com.jaspersoft.studio.property.dataset.dialog.DatasetDialog;
import com.jaspersoft.studio.property.descriptor.ATextDialogRWCellEditor;
import com.jaspersoft.studio.property.descriptor.NullEnum;

public class JRQueryCellEditor extends ATextDialogRWCellEditor {

	/**
	 * Creates a new color cell editor parented under the given control. The cell editor value is black (
	 * <code>RGB(0,0,0)</code>) initially, and has no validator.
	 * 
	 * @param parent
	 *          the parent control
	 */
	public JRQueryCellEditor(Composite parent) {
		this(parent, SWT.NONE);
	}

	/**
	 * Creates a new color cell editor parented under the given control. The cell editor value is black (
	 * <code>RGB(0,0,0)</code>) initially, and has no validator.
	 * 
	 * @param parent
	 *          the parent control
	 * @param style
	 *          the style bits
	 */
	public JRQueryCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		MQuery mquery = (MQuery) getValue();
		MDataset mdataset = mquery.getMdataset();
		Shell shell = cellEditorWindow.getShell();
		DatasetDialog dlg = new DatasetDialog(shell, mdataset, mquery.getJasperConfiguration());
		if (dlg.open() == Window.OK)
			return dlg.getCommand();

		return null;
	}

	private LabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new JRQueryLabelProvider(NullEnum.NULL);
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}

	@Override
	protected Object doGetValue() {
		Object val = super.doGetValue();
		if (isDirty() && val instanceof MQuery) {
			final MQuery m = (MQuery) val;
			JRDesignQuery dexpr = (JRDesignQuery) m.getValue();
			if (dexpr == null) {
				dexpr = new JRDesignQuery();
				dexpr.setLanguage("sql");
				m.setValue(dexpr);
			}
			final JRDesignQuery e = dexpr;
			Display.getCurrent().asyncExec(new Runnable() {

				public void run() {
					// m.setPropertyValue(JRDesignExpression.PROPERTY_TEXT, text.getText());
					e.setText(text.getText());
				}
			});
			return new MQuery(dexpr, m.getMdataset());
		}
		return val;
	}

	@Override
	protected void doSetValue(Object value) {
		super.doSetValue(value);
		if (value instanceof MQuery) {
			MQuery expression = (MQuery) value;

			text.removeModifyListener(getModifyListener());
			String pvalue = (String) expression.getPropertyValue(JRDesignQuery.PROPERTY_TEXT);
			if (pvalue == null)
				pvalue = "";
			text.setText(pvalue);
			text.addModifyListener(getModifyListener());
		}
	}

}
