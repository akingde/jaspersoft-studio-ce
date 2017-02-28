/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.dataset.dialog;

import java.math.BigDecimal;
import java.util.List;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.property.dataset.preview.DataPreviewTable;
import com.jaspersoft.studio.property.dataset.preview.DataPreviewTable.DataPreviewBean;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.engine.design.JRDesignField;

public class CopyDataDialog extends ATitledDialog {
	private DataPreviewTable dpt;

	public CopyDataDialog(Shell parentShell, DataPreviewTable dpt) {
		super(parentShell);
		setTitle("Data");
		setDefaultSize(500, 400);
		this.dpt = dpt;
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);

		Text txt = new Text(cmp, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL);
		txt.setLayoutData(new GridData(GridData.FILL_BOTH));
		txt.setText(getSQL());

		return cmp;
	}

	private String getSQL() {
		List<JRDesignField> fields = dpt.getFields();
		if (fields.isEmpty())
			return "";
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		StringBuffer tcol = new StringBuffer();
		String del = "";
		for (JRDesignField f : fields) {
			tcol.append(del).append("\"" + f.getName().replaceAll("\"", "\"\"") + "\"");
			del = " , ";
		}
		sb.append(tcol).append("\nFROM \n(VALUES \n");
		del = "";
		for (DataPreviewBean it : dpt.getPreviewItems()) {
			sb.append(del).append("(");
			String rdel = "";
			for (int i = 0; i < fields.size(); i++) {
				sb.append(rdel);
				Class<?> vc = fields.get(i).getValueClass();
				String q = vc.isAssignableFrom(Integer.class) || vc.isAssignableFrom(Byte.class)
						|| vc.isAssignableFrom(Short.class) || vc.isAssignableFrom(Long.class) || vc.isAssignableFrom(Boolean.class)
								? "" : "'";
				Object v = it.getValue(i);
				if (v == null)
					sb.append("NULL");
				else if (v instanceof Number && q.equals("'"))
					sb.append(q).append(new BigDecimal(v.toString()).stripTrailingZeros().toPlainString()).append(q);
				else
					sb.append(q).append(v.toString().replaceAll("'", "''")).append(q);
				rdel = ", ";
			}
			sb.append(")");
			del = ",\n";
		}
		sb.append("\n) s(");
		sb.append(tcol).append(")");

		return sb.toString();
	}
}
